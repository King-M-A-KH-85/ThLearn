package ir.thlearn.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import ir.thlearn.models.User;

@Database(entities = User.class,version = 1)
public abstract class ChatDB extends RoomDatabase {
    public abstract DAO getDao();
    public static ChatDB instance;

    public static synchronized ChatDB getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, ChatDB.class, "chats_database").
                    fallbackToDestructiveMigration().
                    addCallback(callback).
                    build();

        return instance;
    }
    private static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InsertFirstValue(instance).execute(new User(0,"King M A","Welcome To Cheetah Messenger",1));
        }
    };

    private static class InsertFirstValue extends AsyncTask<User,Void,Void> {
        private DAO dao;

        public InsertFirstValue(ChatDB chatDB){
            dao = chatDB.getDao();
        }

        @Override
        protected Void doInBackground(User... people) {
            dao.InsertPerson(people[0]);
            return null;
        }
    }

}
