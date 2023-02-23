package id.fakhri_khairi.core.presentation.detail

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.fakhri_khairi.core.R
import id.fakhri_khairi.core.base.BaseFragment
import id.fakhri_khairi.core.databinding.FragmentMemberDetailBinding
import id.fakhri_khairi.core.presentation.bottomsheet.wording.WordingBottomSheet
import id.fakhri_khairi.data.database.DataBaseManager
import id.fakhri_khairi.data.misc.Constants
import javax.inject.Inject

@AndroidEntryPoint
class MemberDetailFragment : BaseFragment<FragmentMemberDetailBinding>() {

    private var memberId: Int? = null

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMemberDetailBinding {
        return FragmentMemberDetailBinding.inflate(inflater, container, false)
    }

    override suspend fun FragmentMemberDetailBinding.setupEvent() {}

    override suspend fun FragmentMemberDetailBinding.setupState() {}

    override fun FragmentMemberDetailBinding.initBinding() {
        memberId = arguments?.getInt(Constants.MEMBER_ID)

        memberId?.let {
            val member = DataBaseManager(requireContext()).fetchDetail(it)

            icCardMember.apply {
                tvValueName.text = member.name
                tvValueDateOfBirth.text = member.dateOfBirth
                tvValueGenre.text = member.gender
                tvValueUsername.text = member.username
                tvValueAddress.text = member.address
            }
        }
        initListener()
    }

    private fun FragmentMemberDetailBinding.initListener() {

        memberDetailToolbar.apply {
            setOnMenuItemClickListener {
                if (it.itemId == R.id.actionSetting) {
                    val bundle = bundleOf(Constants.MEMBER_ID to memberId)
                    findNavController().navigate(R.id.action_memberDetailFragment_to_changePasswordFragment, bundle)
                }
                true
            }
        }

        btnLogout.setOnClickListener {
            val wordingBottomSheet = WordingBottomSheet(
                wording = getString(R.string.title_wording_logout),
                onPositiveClick = {
                    sharedPreferences.edit(commit = true) {
                        clear()
                    }
                    findNavController().navigate(R.id.action_memberDetailFragment_to_loginFragment)
                }
            )
            wordingBottomSheet.show(childFragmentManager, wordingBottomSheet.tag)
        }
    }
}