package id.fakhri_khairi.core.presentation.list

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.fakhri_khairi.core.R
import id.fakhri_khairi.core.base.BaseFragment
import id.fakhri_khairi.core.databinding.FragmentMemberListBinding
import id.fakhri_khairi.core.presentation.adapter.MemberAdapter
import id.fakhri_khairi.core.presentation.bottomsheet.detail.MemberBottomSheet
import id.fakhri_khairi.core.presentation.bottomsheet.wording.WordingBottomSheet
import id.fakhri_khairi.data.database.DataBaseManager
import id.fakhri_khairi.domain.Member
import javax.inject.Inject

@AndroidEntryPoint
class MemberListFragment : BaseFragment<FragmentMemberListBinding>() {

    private val memberAdapter = MemberAdapter()
    private val memberList : MutableList<Member> = mutableListOf()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

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

        val memberResult = DataBaseManager(requireContext()).fetchAll()
        renderMember(memberResult)
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
                val wordingBottomSheet = WordingBottomSheet(
                    wording = getString(R.string.title_wording_logout),
                    onPositiveClick = {
                        sharedPreferences.edit(commit = true) {
                            clear()
                        }
                        findNavController().navigate(R.id.action_memberListFragment_to_loginFragment)
                    }
                )
                wordingBottomSheet.show(childFragmentManager, wordingBottomSheet.tag)
            }
            true
        }

        svMemberName.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    val memberResult = DataBaseManager(requireContext()).fetchByName(query)
                    renderMember(memberResult)
                }
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