package id.fakhri_khairi.core.presentation.change_password

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import id.fakhri_khairi.core.R
import id.fakhri_khairi.core.base.BaseFragment
import id.fakhri_khairi.core.databinding.FragmentChangePasswordBinding
import id.fakhri_khairi.core.presentation.bottomsheet.wording.WordingBottomSheet
import id.fakhri_khairi.data.database.DataBaseManager
import id.fakhri_khairi.data.misc.Constants

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
            if (validate(
                    oldPassword = etOldPassword.text.toString(),
                    newPassword = etNewPassword.text.toString(),
                    confirmPassword = etConfirmation.text.toString()
            )) {
                val wordingBottomSheet = WordingBottomSheet(
                    wording = getString(R.string.title_wording_change_password),
                    onPositiveClick = {
                        val memberId = arguments?.getInt(Constants.MEMBER_ID)

                        memberId?.let {
                            val result = DataBaseManager(requireContext()).updatePassword(id = it, newPassword = etNewPassword.text.toString())
                            if (result>0) {
                                findNavController().popBackStack()
                            }
                        }
                    }
                )
                wordingBottomSheet.show(childFragmentManager, wordingBottomSheet.tag)
            }
        }
    }

    private fun validate(
        oldPassword: String,
        newPassword: String,
        confirmPassword: String
    ) : Boolean {
        if (oldPassword.isEmpty() ||
            newPassword.isEmpty() ||
            confirmPassword.isEmpty()
        ){
            Toast.makeText(requireContext(),"Data tidak boleh kosong", Toast.LENGTH_SHORT)
            return false
        }

        if (newPassword != confirmPassword) {
            Toast.makeText(requireContext(),"Password tidak sama", Toast.LENGTH_SHORT)
            return false
        }

        return true
    }
}