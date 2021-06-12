package com.abhishek.internshipproject;

import android.provider.SyncStateContract;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.Objects;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int note_id;

    @ColumnInfo(name = "note_content") // column name will be "note_content" instead of "content" in table
    private String DateTimeLogin;

    public Note(int note_id, String content) {
        this.note_id = note_id;
        this.DateTimeLogin = content;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getDateTimeLogin() {
        return DateTimeLogin;
    }

    public void setDateTimeLogin(String dateTimeLogin) {
        DateTimeLogin = dateTimeLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        if (note_id != note.note_id) return false;
        return Objects.equals(DateTimeLogin, note.DateTimeLogin);
    }



    @Override
    public int hashCode() {
        int result = note_id;
        result = 31 * result + (DateTimeLogin != null ? DateTimeLogin.hashCode() : 0);
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return "Note{" +
                "note_id=" + note_id +
                ", DateTime='" + DateTimeLogin + '\'' +
                '}';
    }
}

@Dao
interface NoteDao {
    @Query("SELECT * FROM  "+ SyncStateContract.Constants.ACCOUNT_NAME)
    List<Note> getAll();

    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    void insert(Note note);

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void update(Note repos);

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void delete(Note note);

    /*
     * delete list of objects from database
     * @param note, array of objects to be deleted
     */
    @Delete
    void delete(Note... note);      // Note... is varargs, here note is an array

}