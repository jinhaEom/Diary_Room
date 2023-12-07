package bu.ac.kr.diaryroom.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import bu.ac.kr.diaryroom.R
import bu.ac.kr.diaryroom.diary.data.DiaryItem
import bu.ac.kr.diaryroom.viewModel.DiaryViewModel

class DialogHelper(private val context: Context, private val lifecycleOwner: LifecycleOwner, private val diaryViewModel: DiaryViewModel) {

    fun createDialog(showDatePicker: () -> Unit, submitList: (List<DiaryItem>) -> Unit): AlertDialog {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.diary_dialog, null)
        val builder = AlertDialog.Builder(context).setView(dialogView)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.background_white_radius12dp)

        dialogView.findViewById<Button>(R.id.dialogSpecDate)?.setOnClickListener {
            showDatePicker()
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.dialogAllDate)?.setOnClickListener {
            lifecycleOwner.lifecycleScope.launchWhenStarted {
                diaryViewModel.getAllDiaryItems().collect { items ->
                    submitList(items)
                }
            }
            dialog.dismiss()
        }
        return dialog
    }

    fun getDialogLayoutParams(dialog: AlertDialog): WindowManager.LayoutParams {
        val params = dialog.window?.attributes
        val displayMetrics = Resources.getSystem().displayMetrics
        val deviceWidth = displayMetrics.widthPixels
        val marginPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32f, displayMetrics)
        params?.width = (deviceWidth - 2 * marginPixels).toInt()
        return params!!
    }
}
