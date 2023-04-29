package com.example.crewpass_frontend.Home.Club.List.Check

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crewpass_frontend.Data.Application
import com.example.crewpass_frontend.Timestamp_to_SDF
import com.example.crewpass_frontend.databinding.ItemApplicationBinding

class ClubApplicationRVAdapter  (private val application_list: ArrayList<Application>) : RecyclerView.Adapter<ClubApplicationRVAdapter.ViewHolder>() {

    private var items : List<Application> = ArrayList()

    // 아이템 레이아웃 결합
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemApplicationBinding = ItemApplicationBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        return ViewHolder(binding)
    }

    // 아이템 개수
    override fun getItemCount(): Int = application_list.size

    private var mSelectedItem = -1

    fun setItem(item: List<Application>) {
        items = item
        mSelectedItem = -1
        notifyDataSetChanged()
    }

    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(application_list[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(items[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemApplicationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(application: Application) {
            binding.txtClubName.text = "지원자 이름"

            val timestampToSdf = Timestamp_to_SDF()
            binding.itemDateTxt.text = timestampToSdf.convert(application.submit_time)

            binding.itemCheckBox.setOnClickListener {
                mSelectedItem = adapterPosition
                notifyItemRangeChanged(0, items.size)
            }
            // 날짜 적용도 추가하기
        }
    }


    interface OnItemClickListener {
        fun onItemClick(application: Application)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener

}