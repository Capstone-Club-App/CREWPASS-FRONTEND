package com.example.crewpass_frontend.Home.Club.Recent

import android.content.Context
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.Recruitment
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.databinding.ItemAnnouncementClubBinding
import com.example.crewpass_frontend.databinding.ItemAnnouncementPersonalBinding

class RecruitmentRVAdapter (private val recruitment_list: ArrayList<Recruitment>) : RecyclerView.Adapter<RecruitmentRVAdapter.ViewHolder>() {

    var announceCheck = SparseBooleanArray()
    lateinit var context: Context


    // 아이템 레이아웃 결합
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemAnnouncementClubBinding = ItemAnnouncementClubBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        context = viewGroup.context
        return ViewHolder(binding)
    }

    // 아이템 개수
    override fun getItemCount(): Int = recruitment_list.size

    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recruitment_list[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(recruitment_list[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemAnnouncementClubBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recruitment: Recruitment) {
//
//            binding.itemAnnounceDetail.text = recruitment.content
            binding.itemAnnounceTitle.text = recruitment.title
            // 날짜 적용도 추가하기
        }
    }


    interface OnItemClickListener {
        fun onItemClick(recruitment: Recruitment)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener


}