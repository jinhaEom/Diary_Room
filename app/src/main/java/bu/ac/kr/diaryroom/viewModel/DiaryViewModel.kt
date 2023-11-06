package bu.ac.kr.diaryroom.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bu.ac.kr.diaryroom.diary.data.DiaryItem

class DiaryViewModel : ViewModel() {
    private val diaryItems = MutableLiveData<List<DiaryItem>>()

    fun getDiaryItems(): MutableLiveData<List<DiaryItem>> {
        return diaryItems
    }

    fun addDiaryItem(item: DiaryItem) {
        val currentList = diaryItems.value ?: emptyList()
        val updatedList = currentList.toMutableList()
        updatedList.add(item)
        diaryItems.value = updatedList
    }
}
