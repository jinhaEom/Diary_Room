package bu.ac.kr.diaryroom.diary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.room.RoomDatabase
import bu.ac.kr.diaryroom.dao.DiaryItemDao

@Entity(tableName = "diary_items")
data class DiaryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,  // 자동 생성되는 기본 키
    val date: String,
    val title: String,
    val image: String,
    val content: String
)

@Database(entities = [DiaryItem::class], version = 1, exportSchema = false)
abstract class DiaryDatabase : RoomDatabase() {

    abstract fun diaryItemDao(): DiaryItemDao

    companion object {
        @Volatile
        private var INSTANCE: DiaryDatabase? = null

        fun getInstance(context: Context): DiaryDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DiaryDatabase::class.java,
                    "diary_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
