package id.fakhri_khairi.core.presentation.bottomsheet.wording

import android.view.LayoutInflater
import android.view.ViewGroup
import id.fakhri_khairi.core.base.BaseDialogFragment
import id.fakhri_khairi.core.databinding.BottomSheetWordingBinding

class WordingBottomSheet(
    private val wording: String,
    private val onPositiveClick: () -> Unit
) : BaseDialogFragment<BottomSheetWordingBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BottomSheetWordingBinding {
        return BottomSheetWordingBinding.inflate(inflater, container, false)
    }

    override suspend fun BottomSheetWordingBinding.setupEvent() {}

    override suspend fun BottomSheetWordingBinding.setupState() {}

    override fun BottomSheetWordingBinding.initBinding() {
        tvWording.text = wording
        btnNegative.setOnClickListener { dismiss() }
        btnPositive.setOnClickListener {
            onPositiveClick()
            dismiss()
        }
    }
}