package com.sbaygildin.taskpuller.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//@Dao
//interface ImageMetaDao {
//    @Query("SELECT * FROM image_meta WHERE dataset = :dataset")
//    suspend fun getImagesForDataset(dataset: String): List<ImageMeta>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(images: List<ImageMeta>)
//
//    @Query("DELETE FROM image_meta WHERE dataset = :dataset")
//    suspend fun deleteByDataset(dataset: String)
//}