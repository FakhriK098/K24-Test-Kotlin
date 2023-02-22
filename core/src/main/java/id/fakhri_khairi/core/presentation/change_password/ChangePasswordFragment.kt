package id.fakhri_khairi.core.presentation.change_password

import android.view.LayoutInflater
import android.view.ViewGroup
import id.fakhri_khairi.core.R
import id.fakhri_khairi.core.base.BaseFragment
import id.fakhri_khairi.core.databinding.FragmentChangePasswordBinding
import id.fakhri_khairi.core.presentation.bottomsheet.wording.WordingBottomSheet

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChangePasswordBinding {
        return FragmentChangePasswordBinding.inflate(inflater, container, false)
    }

    override suspend fun FragmentChangePasswordBinding.setupEvent() {}

    override suspend fun FragmentChangePasswordBinding.setupState() {}

    override fun FragmentChangePasswordBinding.initBinding() {
        initListener()
    }

    private fun FragmentChangePasswordBinding.initListener() {
        changePasswordToolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        btnChangePassword.setOnClickListener {
            val wordingBottomSheet = WordingBottomSheet(
                wording = getString(R.string.title_wording_change_password),
                onPositiveClick = {}
            )
            wordingBottomSheet.show(childFragmentManager, wordingBottomSheet.tag)
        }
    }
}