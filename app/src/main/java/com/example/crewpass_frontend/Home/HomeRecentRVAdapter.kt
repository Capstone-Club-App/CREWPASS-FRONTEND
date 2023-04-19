package com.example.crewpass_frontend.Home

import android.content.Context
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crewpass_frontend.Data.Announcement
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.databinding.ItemAnnouncementPersonalBinding

class HomeRecentRVAdapter (private val announcement_list: ArrayList<Announcement>) : RecyclerView.Adapter<HomeRecentRVAdapter.ViewHolder>() {

    var checkList = ArrayList<CheckStatus>()
    var checkStatus = SparseBooleanArray()
    lateinit var context: Context


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
        holder.bind(announcement_list[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(announcement_list[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemAnnouncementPersonalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(announcement: Announcement) {
            binding.btnHeart.setOnClickListener {
                binding.btnHeart.isSelected = !binding.btnHeart.isSelected
                if(!binding.btnHeart.isSelected) {
                    Log.d("check false", binding.btnHeart.isSelected.toString())
                    Log.d("adapterPosition : ", adapterPosition.toString())
                    checkStatus.put(adapterPosition, false)
                    binding.btnHeart.setBackgroundResource(R.drawable.img_heart_notfill)
                }
                else {
                    Log.d("check true", binding.btnHeart.isSelected.toString())
                    Log.d("adapterPosition : ", adapterPosition.toString())
                    checkStatus.put(adapterPosition, true)
                    binding.btnHeart.setBackgroundResource(R.drawable.img_heart_fill)
                }
                notifyDataSetChanged()
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