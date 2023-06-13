package com.example.crewpass_frontend.Home.Club

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.Data.Recruitment
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentData
import com.example.crewpass_frontend.databinding.ItemAnnouncementClubBinding
import java.text.SimpleDateFormat

class ClubRecruitmentRVAdapter (private val recruitment_list: ArrayList<RecruitmentData>) : RecyclerView.Adapter<ClubRecruitmentRVAdapter.ViewHolder>() {

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
        fun bind(recruitment: RecruitmentData) {
            binding.itemAnnounceDetail.text = recruitment.crew_name
            binding.itemAnnounceTitle.text = recruitment.title


            var hashTagList = ArrayList<String>()
            hashTagList.add(recruitment.field1)
            if(!recruitment.field2.equals("null"))
                hashTagList.add(recruitment.field2)

            var hashString = ""
            hashTagList.forEach {
                hashString += "#$it "
            }

            binding.itemHashtags.text = hashString

            var sdf = SimpleDateFormat("yyyy.MM.dd HH:mm")
            var date = sdf.format(recruitment.register_time)
            binding.itemAnnounceTime.text = date

            Glide.with(context).load(recruitment.crew_profile)
                .circleCrop()
                .into(binding.profileImg)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(recruitment: RecruitmentData)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener
}