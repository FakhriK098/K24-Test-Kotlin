package id.fakhri_khairi.core.presentation.bottomsheet.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import id.fakhri_khairi.core.base.BaseDialogFragment
import id.fakhri_khairi.core.databinding.BottomSheetMemberBinding
import id.fakhri_khairi.domain.Member

class MemberBottomSheet(
    private val member: Member
) : BaseDialogFragment<BottomSheetMemberBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BottomSheetMemberBinding {
        return BottomSheetMemberBinding.inflate(inflater, container, false)
    }

    override suspend fun BottomSheetMemberBinding.setupEvent() {}

    override suspend fun BottomSheetMemberBinding.setupState() {}

    override fun BottomSheetMemberBinding.initBinding() {
        ivClose.setOnClickListener { dismiss() }

        icCardMember.apply {
            tvValueName.text = member.name
            tvValueDateOfBirth.text = member.dateOfBirth
            tvValueAddress.text = member.address
            tvValueGenre.text = member.genre
            tvValueUsername.text = member.username
        }
    }
}