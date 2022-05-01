package ir.thlearn.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "chat")
public class User extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "chat_name")
    private String name;

    @ColumnInfo(name = "chat_lastMessage")
    private String lastMessage;

    @ColumnInfo(name = "chat_unseen_count")
    private int chat_unseen_count;

    public User(int id, String name, String lastMessage, int chat_unseen_count) {
        this.id = id;
        this.name = name;
        this.lastMessage = lastMessage;
        this.chat_unseen_count = chat_unseen_count;
    }

    @Ignore
    public User(){

    }

    public int getId() {
        return id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
//        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
//        notifyPropertyChanged(BR.lastMessage);
    }

    @Bindable
    public int getChat_unseen_count() {
        return chat_unseen_count;
    }

    public void setChat_unseen_count(int chat_unseen_count) {
        this.chat_unseen_count = chat_unseen_count;
//        notifyPropertyChanged(BR.chat_unseen_count);
    }

    public static User newObject(){
        return new User();
    }
}
