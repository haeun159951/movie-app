package com.example.movies_haeun_hekim4.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movies_haeun_hekim4.models.Purchase;

import java.util.List;

// data access object
// specify the operations the application can perform on the purchase table
// insert, update, delete, retrieve data
@Dao
public interface PurchaseDAO {
    // insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTicket(Purchase pur);

    //retrieve
    @Query("SELECT * FROM purchase_table")
    List<Purchase> getAllTickets();

    // retrieve one
    @Query("SELECT * FROM purchase_table WHERE movieId = :movieId")
    Purchase  getTicketById(int movieId);

    // update one
    @Update
    void updateTicket(Purchase purchaseToUpdate);

    // update quantity using the specific id
    @Query("Update purchase_table SET quantity = quantity + 1 WHERE movieId= :movieId")
    void updateQuantity(int movieId);

    // delete one
    @Delete
    void deleteTicket(Purchase purchaseToDelete);
}
