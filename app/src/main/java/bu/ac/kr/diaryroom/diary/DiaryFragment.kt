package bu.ac.kr.diaryroom.diary

import DiaryAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.databinding.FragmentDiaryBinding
import bu.ac.kr.diaryroom.diary.data.DiaryDatabase
import bu.ac.kr.diaryroom.utils.getNavOptions
import bu.ac.kr.diaryroom.viewModel.DiaryViewModel
import bu.ac.kr.diaryroom.viewModel.Factory.DiaryViewModelFactory

class DiaryFragment(override val layoutResourceId: Int = R.layout.fragment_diary) :
    BaseFragment<FragmentDiaryBinding>() {

    private lateinit var diaryViewModel: DiaryViewModel
    private lateinit var diaryAdapter: DiaryAdapter

    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = this
        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).diaryItemDao()
        val viewModelFactory = DiaryViewModelFactory(dataSource)
        diaryViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(DiaryViewModel::class.java)
        diaryAdapter = DiaryAdapter(diaryViewModel)

        viewDataBinding.diaryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewDataBinding.diaryRecyclerView.adapter = diaryAdapter

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            diaryViewModel.getAllDiaryItems().collect { items ->
                diaryAdapter.submitList(items)
            }
        }

        viewDataBinding.homeToolbarRightButton.setOnClickListener {
            findNavController().navigate(
                DiaryFragmentDirections.actionDiaryWrittingFragment(),
                getNavOptions
            )
        }
    }

    override fun observeData() {
    }
}
