package com.example.crewpass_frontend.AI.Club.Interview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crewpass_frontend.Data.Application
import com.example.crewpass_frontend.Data.Recruitment
import com.example.crewpass_frontend.databinding.ItemRecruitmentClubCheckboxBinding


class RecruitmentRVAdapter (private val recruitment_list: ArrayList<Recruitment>) : RecyclerView.Adapter<RecruitmentRVAdapter.ViewHolder>() {

    private var items : List<Recruitment> = ArrayList()
    lateinit var context: Context


    // 아이템 레이아웃 결합
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemRecruitmentClubCheckboxBinding = ItemRecruitmentClubCheckboxBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        context = viewGroup.context
        return ViewHolder(binding)
    }

    // 아이템 개수
    override fun getItemCount(): Int = recruitment_list.size

    private var mSelectedItem = -1

    fun setItem(item: List<Recruitment>) {
        items = item
        mSelectedItem = -1
        notifyDataSetChanged()
    }

    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recruitment_list[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(items[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemRecruitmentClubCheckboxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recruitment: Recruitment) {
            binding.itemAnnounceDetail.text = recruitment.content
            binding.itemAnnounceTitle.text = recruitment.title
            // 날짜 적용도 추가하기


            binding.itemCheckBox.setOnClickListener {
                mSelectedItem = adapterPosition
                notifyItemRangeChanged(0, items.size)
            }
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