package com.example.katarzyna.playtune.Model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DiscDAO {
    @Query("SELECT * FROM DiscModel")
    LiveData<List<DiscModel>> getAllDisc();

    @Query("SELECT * FROM DiscModel WHERE id = :id")
    DiscModel getDisc(int id);

    @Query("SELECT * FROM DISCMODEL WHERE genre || author || name LIKE :searchText")
    LiveData<List<DiscModel>> getDiscsFromDb(String searchText);

    @Query("SELECT * FROM DiscModel WHERE genre = :genre")
    LiveData<List<DiscModel>> getDiscsByGenres(String genre);

    @Query("SELECT * FROM DiscModel WHERE year BETWEEN :from AND :to  ")
    LiveData<List<DiscModel>> getDiscsByYear(Long from, Long to);

    @Query("SELECT * FROM DiscModel WHERE country LIKE :country")
    LiveData<List<DiscModel>> getDiscsByCountry(String country);

    @Query("SELECT * FROM DiscModel WHERE country LIKE :country  AND year BETWEEN :from AND :to  ")
    LiveData<List<DiscModel>> getDiscsByYearCountry(Long from, Long to, String country);

    @Query("DELETE  FROM DiscModel")
    void deleteAll();
    @Insert
    void insertDisc(DiscModel discModel);
    @Insert
    void insertAllDisc(DiscModel... discModels);
    @Update
    void updateDB(DiscModel discModel);

}
