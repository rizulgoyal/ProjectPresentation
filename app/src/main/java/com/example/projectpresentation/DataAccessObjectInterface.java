package com.example.projectpresentation;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface DataAccessObjectInterface
{
    @Insert
    void insert(Customer customer);

    @Delete
    void delete(Customer customer);

    @Update
    void update(Customer customer);

    @Query("Select * from customer")
    LiveData<List<Customer>> getUserDetails();



    @Query("Select * from customer where id = :id LIMIT 1")
    LiveData<Customer> getCustomUserDetails(Integer id);

    @Query("Select count(id) from customer")
    Integer count();

}
