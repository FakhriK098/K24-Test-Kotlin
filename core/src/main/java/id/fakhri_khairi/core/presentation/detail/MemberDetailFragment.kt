package id.fakhri_khairi.core.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import id.fakhri_khairi.core.R
import id.fakhri_khairi.core.base.BaseFragment
import id.fakhri_khairi.core.databinding.FragmentMemberDetailBinding
import id.fakhri_khairi.core.presentation.bottomsheet.wording.WordingBottomSheet
import id.fakhri_khairi.data.misc.Constants

class MemberDetailFragment : BaseFragment<FragmentMemberDetailBinding>() {

    private var memberId: String? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMemberDetailBinding {
        return FragmentMemberDetailBinding.inflate(inflater, container, false)
    }

    override suspend fun FragmentMemberDetailBinding.setupEvent() {}

    override suspend fun FragmentMemberDetailBinding.setupState() {}

    override fun FragmentMemberDetailBinding.initBinding() {
        memberId = arguments?.getString(Constants.MEMBER_ID)
        memberDetailToolbar.apply {
            setNavigationOnClickListener { requireActivity().onBackPressed() }
            setOnMenuItemClickListener {
                if (it.itemId == R.id.actionSetting) {
                    findNavController().navigate(R.id.action_memberDetailFragment_to_changePasswordFragment)
                }
                true
            }
        }

        btnLogout.setOnClickListener {
            val wordingBottomSheet = WordingBottomSheet(
                wording = getString(R.string.title_wording_logout),
                onPositiveClick = {}
            )
            wordingBottomSheet.show(childFragmentManager, wordingBottomSheet.tag)
        }
    }
}