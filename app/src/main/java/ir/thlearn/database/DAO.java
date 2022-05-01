package ir.thlearn.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import ir.thlearn.models.User;

@Dao
public interface DAO {
    @Insert
    void InsertPerson(User contacts);

    @Update
    void UpdatePerson(User contacts);

    @Delete
    void DeletePerson(User contacts);

    @Query("select * from chat")
    LiveData<List<User>> SelectAllChats();

}
