package bu.ac.kr.diaryroom.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

import androidx.lifecycle.viewModelScope
import bu.ac.kr.diaryroom.dao.DiaryItemDao
import bu.ac.kr.diaryroom.diary.data.DiaryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DiaryViewModel(private val diaryItemDao: DiaryItemDao) : ViewModel() {
    private val _selectedDiaryItem = MutableLiveData<DiaryItem>()
    val selectedDiaryItem: LiveData<DiaryItem> get() = _selectedDiaryItem

    fun getAllDiaryItems(): Flow<List<DiaryItem>> {
        return diaryItemDao.getAllDiaryItems()
    }

    fun addDiaryItem(item: DiaryItem) {
        viewModelScope.launch {
            diaryItemDao.insertDiaryItem(item)
        }
    }
    fun selectDiaryItem(item: DiaryItem) {
        _selectedDiaryItem.value = item
    }
    val todayDate = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault()).format(Date())
    val todayDiary: LiveData<DiaryItem?> = diaryItemDao.getTodayDiary(todayDate)
}
