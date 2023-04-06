package com.example.crewpass_frontend.Home.Personal.List

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crewpass_frontend.Retrofit.FindSchool.Data
import com.example.crewpass_frontend.databinding.ItemAnnouncementPersonalBinding
import com.example.crewpass_frontend.databinding.ItemFindSchoolBinding

class AnnouncementRVAdapter (private val announcement_list: ArrayList<Announcement>) : RecyclerView.Adapter<AnnouncementRVAdapter.ViewHolder>() {

    lateinit var context: Context

    private val checkList = arrayListOf<CheckStatus>()

    // 아이템 레이아웃 결합
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemAnnouncementPersonalBinding = ItemAnnouncementPersonalBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        context = viewGroup.context
        return ViewHolder(binding)
    }

    // 아이템 개수
    override fun getItemCount(): Int = announcement_list.size

    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(announcement_list[position], checkList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(announcement_list[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemAnnouncementPersonalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(announcement: Announcement, checkList : CheckStatus) {
            binding.btnHeart.isSelected = checkList.isSelected
            binding.btnHeart.setOnClickListener {
                checkList.isSelected = binding.btnHeart.isSelected
                notifyItemChanged(adapterPosition)
            }

            binding.itemAnnounceDetail.text = announcement.content
            binding.itemAnnounceTitle.text = announcement.title
            // 날짜 적용도 추가하기
        }
    }

    interface OnItemClickListener {
        fun onItemClick(announcement: Announcement)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener

    data class CheckStatus(val position: Int, var isSelected : Boolean)
}