package bu.ac.kr.diaryroom.viewModel.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bu.ac.kr.diaryroom.dao.DiaryItemDao
import bu.ac.kr.diaryroom.viewModel.DiaryViewModel

class DiaryViewModelFactory(private val dataSource: DiaryItemDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiaryViewModel::class.java)) {
            return DiaryViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

