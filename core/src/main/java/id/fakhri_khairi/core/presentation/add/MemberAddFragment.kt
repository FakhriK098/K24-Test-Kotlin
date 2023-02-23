package id.fakhri_khairi.core.presentation.add

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import id.fakhri_khairi.core.R
import id.fakhri_khairi.core.base.BaseFragment
import id.fakhri_khairi.core.databinding.FragmentMemberAddBinding
import id.fakhri_khairi.core.misc.DateHelper
import id.fakhri_khairi.core.presentation.bottomsheet.gender.GenderBottomSheet
import id.fakhri_khairi.core.presentation.bottomsheet.wording.WordingBottomSheet
import id.fakhri_khairi.data.database.DataBaseManager
import id.fakhri_khairi.domain.Member
import java.util.*

class MemberAddFragment : BaseFragment<FragmentMemberAddBinding>() {

    private val calendar = Calendar.getInstance()

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMemberAddBinding {
        return FragmentMemberAddBinding.inflate(inflater, container, false)
    }

    override suspend fun FragmentMemberAddBinding.setupEvent() {}

    override suspend fun FragmentMemberAddBinding.setupState() {}

    override fun FragmentMemberAddBinding.initBinding() {
        initListener()
    }

    private fun FragmentMemberAddBinding.initListener() {
        memberAddToolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                etDateOfBirth.setText(DateHelper.format(calendar.time, "dd MMMM yyyy"))
            }

        etDateOfBirth.setOnClickListener {
            DatePickerDialog(
                requireActivity(),
                R.style.DatePickerTheme,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).apply {
                datePicker.maxDate = System.currentTimeMillis() - 1 * 24 * 60 * 60 * 1000
                show()
            }
        }

        etGender.setOnClickListener {
            val genderBottomSheet = GenderBottomSheet()
            genderBottomSheet.show(childFragmentManager, genderBottomSheet.tag)
            genderBottomSheet.setOnGenderClicked { gender ->
                etGender.setText(gender)
            }
        }

        btnRegister.setOnClickListener {
            val member = Member(
                name = etName.text.toString(),
                dateOfBirth = etDateOfBirth.text.toString(),
                address = etAddress.text.toString(),
                gender = etGender.text.toString(),
                username = etUsername.text.toString(),
                password = etPassword.text.toString()
            )
            if (validate(member)){
                val wordingBottomSheet = WordingBottomSheet(
                    wording = getString(R.string.title_wording_register_member),
                    onPositiveClick = {
                        val result = DataBaseManager(requireContext()).insert(member)
                        if (result > 0L){
                            findNavController().popBackStack()
                        }
                        Toast.makeText(requireContext(),"Gagal",Toast.LENGTH_SHORT)
                    })
                wordingBottomSheet.show(childFragmentManager, wordingBottomSheet.tag)
            }
        }
    }
    private fun validate(member: Member): Boolean {
        if (member.name.isEmpty() ||
            member.dateOfBirth.isEmpty() ||
            member.address.isEmpty() ||
            member.gender.isEmpty() ||
            member.username.isEmpty() ||
            member.password.isEmpty()
        ) {
            return false
        }

        return true
    }
}