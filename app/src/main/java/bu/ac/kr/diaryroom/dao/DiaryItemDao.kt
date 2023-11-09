package bu.ac.kr.diaryroom.dao

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import bu.ac.kr.diaryroom.diary.data.DiaryDatabase
import bu.ac.kr.diaryroom.diary.data.DiaryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Dao
interface DiaryItemDao {
    @Query("SELECT * FROM diary_items")
    fun getAllDiaryItems(): Flow<List<DiaryItem>>

    @Insert
    suspend fun insertDiaryItem(diaryItem: DiaryItem)

    @Query("SELECT * FROM diary_items WHERE date = :todayDate LIMIT 1")
    fun getTodayDiary(todayDate: String): LiveData<DiaryItem?>

}

suspend fun saveDiaryItem(context: Context, diaryItem: DiaryItem) {
    withContext(Dispatchers.IO) {
        val database = DiaryDatabase.getInstance(context)
        val diaryItemDao = database.diaryItemDao()
        diaryItemDao.insertDiaryItem(diaryItem)
    }
}
