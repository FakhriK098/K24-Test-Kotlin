package id.fakhri_khairi.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.fakhri_khairi.core.base.BaseAdapter
import id.fakhri_khairi.core.databinding.ItemMemberBinding
import id.fakhri_khairi.data.misc.Constants
import id.fakhri_khairi.domain.Member

class MemberAdapter : BaseAdapter<Member>() {
    private var onMemberClick: (Member) -> Unit = { _ -> }

    private class MemberViewHolder(
        val binding: ItemMemberBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private fun ItemMemberBinding.bind(member: Member) {
        tvName.text = member.name

        root.setOnClickListener { onMemberClick(member) }
    }

    fun setOnMemberClicked(OnMemberClick: (Member) -> Unit) {
        onMemberClick = OnMemberClick
    }

    override fun getViewType(position: Int): Int = Constants.BASE_VIEW_TYPE

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MemberViewHolder(
            ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MemberViewHolder) {
            val member = items[position]
            member?.let { holder.binding.bind(it) }
        }
    }
}