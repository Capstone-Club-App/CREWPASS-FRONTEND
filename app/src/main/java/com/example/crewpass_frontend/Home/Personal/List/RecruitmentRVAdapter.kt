package com.example.crewpass_frontend.Home.Personal.List

import android.content.Context
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crewpass_frontend.Data.Recruitment
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.databinding.ItemAnnouncementPersonalBinding

class RecruitmentRVAdapter (private val recruitment_list: ArrayList<Recruitment>) : RecyclerView.Adapter<RecruitmentRVAdapter.ViewHolder>() {

    var announceCheck = SparseBooleanArray()
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
    override fun getItemCount(): Int = recruitment_list.size


    data class heartSelected(val position: Int, var isSelected : Boolean)

    fun isHeartSelected(position: Int) : Boolean{
        return announceCheck.get(position, false)
    }

    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recruitment_list[position])

        ////////// ** 이미지 변경해주는 부분 추가
        if(isHeartSelected(position)){
            holder.binding.btnHeart.setBackgroundResource(R.drawable.img_heart_fill)
        }else{
            holder.binding.btnHeart.setBackgroundResource(R.drawable.img_heart_notfill)
        }
        /////////

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(recruitment_list[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemAnnouncementPersonalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recruitment: Recruitment) {

            ////////// ** 처음 상태(sparseBooleanArray 해당 포지션 안에 아무것도 안 넣었을 때)
            if(announceCheck[adapterPosition] == null){
                announceCheck.put(adapterPosition, false)
            }
            //////////


            ////////// ** setOnClickListener 상태 변경
            binding.btnHeart.setOnClickListener {
                if(announceCheck.get(adapterPosition, false)) {
                    Log.d("파랑", announceCheck.get(adapterPosition, false).toString())
                    Log.d("adapterPosition : ", adapterPosition.toString())
                    announceCheck.put(adapterPosition, false)
                    binding.btnHeart.setBackgroundResource(R.drawable.img_heart_notfill)
                }
                else {
                    Log.d("하양", announceCheck.get(adapterPosition, false).toString())
                    Log.d("adapterPosition : ", adapterPosition.toString())
                    announceCheck.put(adapterPosition, true)
                    binding.btnHeart.setBackgroundResource(R.drawable.img_heart_fill)
                }
                notifyDataSetChanged()
            }

            //////////


            binding.itemAnnounceDetail.text = recruitment.content
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