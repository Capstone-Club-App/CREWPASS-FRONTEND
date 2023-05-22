package com.example.crewpass_frontend.Home.Club.List.Check

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.Data.Application
import com.example.crewpass_frontend.Retrofit.Club.Application.ApplicationData
import com.example.crewpass_frontend.Timestamp_to_SDF
import com.example.crewpass_frontend.databinding.ItemPersonalApplicationCheckboxBinding

class ClubApplicationRVAdapter  (private val application_list: ArrayList<ApplicationData>) : RecyclerView.Adapter<ClubApplicationRVAdapter.ViewHolder>() {

//    private var items : List<ApplicationData> = ArrayList()
    lateinit var context : Context

    // 아이템 레이아웃 결합
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPersonalApplicationCheckboxBinding = ItemPersonalApplicationCheckboxBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        context = viewGroup.context
        return ViewHolder(binding)
    }

    // 아이템 개수
    override fun getItemCount(): Int = application_list.size

//    private var mSelectedItem = -1
//
//    fun setItem(item: List<ApplicationData>) {
//        items = item
//        mSelectedItem = -1
//        notifyDataSetChanged()
//    }

    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(application_list[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(application_list[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemPersonalApplicationCheckboxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(application: ApplicationData) {
            binding.txtClubName.text = application.user_name

            val timestampToSdf = Timestamp_to_SDF()
            binding.itemDateTxt.text = timestampToSdf.convert(application.submit_time)

            binding.itemCheckBox.setOnClickListener {
                if(binding.itemCheckBox.isChecked)
                    check_list.add(application.user_id.toString())
                else
                    check_list.remove(application.user_id.toString())
                notifyItemRangeChanged(0, application_list.size)
            }


            Glide.with(context).load(application.user_profile)
                .circleCrop()
                .into(binding.itemProfileImg)
        }
    }


    interface OnItemClickListener {
        fun onItemClick(application: ApplicationData)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

//    fun checkPass(check_list : ArrayList<String>){
//        for (application in application_list) {
//            if(check_list.contains(application.user_id.toString())){
//                var position = findItemPositionByName(application.user_id)
//
//            }
//        }
//    }
//
//    fun findItemPositionByName(user_id: Int): Int {
//        for (i in items.indices) {
//            if (items[i].user_id == user_id) {
//                return i
//            }
//        }
//        return -1 // 해당 아이템을 찾지 못한 경우 -1을 반환합니다.
//    }

    private lateinit var itemClickListener: OnItemClickListener
}