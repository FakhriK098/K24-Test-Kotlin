package id.fakhri_khairi.core.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import id.fakhri_khairi.core.R
import id.fakhri_khairi.core.base.BaseFragment
import id.fakhri_khairi.core.databinding.FragmentMemberListBinding
import id.fakhri_khairi.core.presentation.adapter.MemberAdapter
import id.fakhri_khairi.core.presentation.bottomsheet.detail.MemberBottomSheet
import id.fakhri_khairi.data.misc.Constants
import id.fakhri_khairi.domain.Member

class MemberListFragment : BaseFragment<FragmentMemberListBinding>() {

    private val memberAdapter = MemberAdapter()
    private val memberList : MutableList<Member> = mutableListOf()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMemberListBinding {
        return FragmentMemberListBinding.inflate(inflater, container, false)
    }

    override suspend fun FragmentMemberListBinding.setupEvent() {}

    override suspend fun FragmentMemberListBinding.setupState() {}

    override fun FragmentMemberListBinding.initBinding() {
        initAdapter()
        initListener()

        val memberl = listOf(
            Member(
                id = 0,
                name = "Fakhri",
                dateOfBirth = "",
                address = "",
                genre = "",
                username = "",
                password = ""
            ),
            Member(
                id = 0,
                name = "Fakhri",
                dateOfBirth = "",
                address = "",
                genre = "",
                username = "",
                password = ""
            ),
            Member(
                id = 0,
                name = "Fakhri",
                dateOfBirth = "",
                address = "",
                genre = "",
                username = "",
                password = ""
            ),
            Member(
                id = 0,
                name = "Fakhri",
                dateOfBirth = "",
                address = "",
                genre = "",
                username = "",
                password = ""
            )
        )

        renderMember(memberl)
    }

    private fun FragmentMemberListBinding.initAdapter() {
        rvMember.apply {
            layoutManager = LinearLayoutManager(root.context)
            isNestedScrollingEnabled = false
            itemAnimator = DefaultItemAnimator()
            adapter = memberAdapter
        }
    }

    private fun FragmentMemberListBinding.initListener() {
        memberAdapter.setOnMemberClicked { member ->
            val memberBottomSheet = MemberBottomSheet(member)
            memberBottomSheet.show(childFragmentManager, memberBottomSheet.tag)
        }
        memberListToolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.accountAction) {
                val bundle = bundleOf(Constants.MEMBER_ID to "1")
                findNavController().navigate(R.id.action_memberListFragment_to_memberDetailFragment, bundle)
            }
            true
        }
        svMemberName.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        fbAddMember.setOnClickListener {
            findNavController().navigate(R.id.action_memberListFragment_to_memberAddFragment)
        }
    }

    private fun renderMember(data: List<Member>) {
        memberList.clear()
        memberList.addAll(data)
        memberAdapter.setItems(memberList)
    }
}