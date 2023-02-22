package id.fakhri_khairi.core.presentation.bottomsheet.gender

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import id.fakhri_khairi.core.base.BaseDialogFragment
import id.fakhri_khairi.core.databinding.BottomSheetGenderBinding

class GenderBottomSheet : BaseDialogFragment<BottomSheetGenderBinding>() {

    private var onGenderClick : (String) -> Unit = {_ ->}

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BottomSheetGenderBinding {
        return BottomSheetGenderBinding.inflate(inflater, container, false)
    }

    override suspend fun BottomSheetGenderBinding.setupEvent() {}

    override suspend fun BottomSheetGenderBinding.setupState() {}

    override fun BottomSheetGenderBinding.initBinding() {
        radioButtonGender.setOnCheckedChangeListener { group, checkedId ->
            val radioSelected = group.findViewById<RadioButton>(checkedId)
            onGenderClick(radioSelected.text.toString())
            dismiss()
        }
    }

    fun setOnGenderClicked(OnGenderClick: (String) -> Unit) {
        onGenderClick = OnGenderClick
    }
}