package bu.ac.kr.diaryroom.diary

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.base.BaseFragment
import bu.ac.kr.diaryroom.dao.saveDiaryItem
import bu.ac.kr.diaryroom.databinding.FragmentDiaryWrittingBinding
import bu.ac.kr.diaryroom.diary.adapter.MultiImageAdapter
import bu.ac.kr.diaryroom.diary.data.DiaryItem
import bu.ac.kr.diaryroom.utils.setOnOneClickListener
import bu.ac.kr.diaryroom.viewModel.DiaryViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DiaryWrittingFragment(override val layoutResourceId: Int = R.layout.fragment_diary_writting) :
    BaseFragment<FragmentDiaryWrittingBinding>() {

    private val diaryViewModel: DiaryViewModel by activityViewModels()
    private val imageFilenames = ArrayList<String>()
    val list = ArrayList<Uri>()
    val multiAdapter = MultiImageAdapter(list,this)

    override fun aboutBinding() {
        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        viewDataBinding.apply{
            viewDataBinding.diarySaveButton.setOnClickListener {
                if (diaryTitle.text.isNullOrEmpty() || diaryContent.text.isNullOrEmpty()) {
                    Snackbar.make(requireView(), "내용을 입력해주세요.", Snackbar.LENGTH_SHORT).show()

                }else{
                    val currentTime =
                        SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault()).format(Date())
                    val title = viewDataBinding.diaryTitle.text.toString()
                    val content = viewDataBinding.diaryContent.text.toString()

                    val newItem = DiaryItem(0, currentTime, title, imageFilenames.joinToString(","), content)
                    lifecycleScope.launch {
                        saveDiaryItem(requireContext(), newItem)
                        Log.d("saveDiary","${newItem}")

                        findNavController().popBackStack()
                        Snackbar.make(requireView(), "일기가 저장되었어요.", Snackbar.LENGTH_SHORT).show()

                    }
                }



            }

        }


        viewDataBinding.toolbarLeftButton.setOnOneClickListener {
            findNavController().navigateUp()
        }
        viewDataBinding.imageAddButton.setOnOneClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
            intent.action = Intent.ACTION_GET_CONTENT

            startActivityForResult(intent,200)
            viewDataBinding.imageAddButton.visibility = View.GONE
            viewDataBinding.chooseImageTx.visibility = View.GONE

        }
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewDataBinding.imageRecyclerView.layoutManager = layoutManager

        viewDataBinding.imageRecyclerView.adapter = multiAdapter
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 200) {
            list.clear()

            if (data?.clipData != null) { // 사진 여러개 선택한 경우
                val count = data.clipData!!.itemCount
                if (count > 10) {
                    Toast.makeText(requireContext(), "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_LONG).show()
                    return
                }
                for (i in 0 until count) {
                    val imageUri = data.clipData!!.getItemAt(i).uri
                    val filename = "${System.currentTimeMillis()}.jpg"
                    saveImage(requireContext(), imageUri, filename)
                    imageFilenames.add(filename)
                    list.add(imageUri)
                }

            } else { // 단일 선택
                data?.data?.let { uri ->
                    val filename = "${System.currentTimeMillis()}.jpg"
                    saveImage(requireContext(), uri, filename)
                    imageFilenames.add(filename)

                    list.add(uri)
                }
            }
            multiAdapter.notifyDataSetChanged()
        }
    }

    private fun saveImage(context: Context, uri: Uri, filename: String) {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(File(context.filesDir, filename))
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
            Log.d("DiaryWrittingFragment", "Image saved: $filename")
        } catch (e: FileNotFoundException) {
            Log.e("DiaryWrittingFragment", "Failed to open file input stream", e)
        } catch (e: IOException) {
            Log.e("DiaryWrittingFragment", "Failed to save image", e)
        }
    }

    override fun observeData() {
    }
}
