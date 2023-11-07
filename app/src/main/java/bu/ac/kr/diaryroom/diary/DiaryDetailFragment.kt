package bu.ac.kr.diaryroom.diary

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.databinding.FragmentDiaryDetailBinding
import bu.ac.kr.diaryroom.diary.adapter.ImageAdapter
import bu.ac.kr.diaryroom.diary.data.DiaryDatabase
import bu.ac.kr.diaryroom.utils.setOnOneClickListener
import bu.ac.kr.diaryroom.viewModel.DiaryViewModel
import bu.ac.kr.diaryroom.viewModel.Factory.DiaryViewModelFactory

class DiaryDetailFragment(override val layoutResourceId : Int= R.layout.fragment_diary_detail) :
    BaseFragment<FragmentDiaryDetailBinding>() {
    private lateinit var diaryViewModel: DiaryViewModel

    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).diaryItemDao()
        val viewModelFactory = DiaryViewModelFactory(dataSource)
        diaryViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(DiaryViewModel::class.java)


        diaryViewModel.selectedDiaryItem.observe(viewLifecycleOwner) { diaryItem ->
            viewDataBinding.diaryTitle.text = diaryItem.title
            viewDataBinding.diaryContent.text = diaryItem.content

            // 이미지 처리
            val imageFilenames = diaryItem.image.split(",")
            val imageAdapter = ImageAdapter(imageFilenames)
            viewDataBinding.imageRecyclerView.adapter = imageAdapter
//            for (filename in imageFilenames) {
//                if (filename.isNotEmpty()) {
//                    val filePath = requireContext().filesDir.absolutePath + "/" + filename
//                    val bitmap = BitmapFactory.decodeFile(filePath)
//                    val imageView = ImageView(requireContext())
//                    imageView.setImageBitmap(bitmap)
//                    viewDataBinding.imageRecyclerView.addView(imageView)
//                }
//            }
        }


        viewDataBinding.toolbarLeftButton.setOnOneClickListener {
            findNavController().navigateUp()
        }
    }

    private fun loadImage(context: Context, filename: String): Bitmap {
        val inputStream = context.openFileInput(filename)
        return BitmapFactory.decodeStream(inputStream)
    }

    override fun observeData() {
    }
}