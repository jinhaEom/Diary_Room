package bu.ac.kr.diaryroom.diary

import DiaryAdapter
import android.widget.Toast
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
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date

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
        viewDataBinding.calendarImageView.setOnClickListener{
            showDatePicker()
        }
    }
    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        datePicker.show(childFragmentManager, "DatePicker")

        // Setting up the event for when ok is clicked
        datePicker.addOnPositiveButtonClickListener {
            // formatting date in dd-mm-yyyy format.
            val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
            val date = dateFormatter.format(Date(it))
            Toast.makeText(requireContext(), "$date is selected", Toast.LENGTH_LONG).show()

        }

        // Setting up the event for when cancelled is clicked
        datePicker.addOnNegativeButtonClickListener {
            Toast.makeText(requireContext(), "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG).show()
        }

        // Setting up the event for when back button is pressed
        datePicker.addOnCancelListener {
            Toast.makeText(requireContext(), "Date Picker Cancelled", Toast.LENGTH_LONG).show()
        }
    }

    override fun observeData() {
    }
}
