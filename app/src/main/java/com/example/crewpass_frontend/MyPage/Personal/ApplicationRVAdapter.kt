package com.example.crewpass_frontend.MyPage.Personal

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.Retrofit.Personal.Application.Application

import com.example.crewpass_frontend.databinding.ItemPrevBinding
import java.text.SimpleDateFormat

class ApplicationRVAdapter (private val application_list: ArrayList<Application>, var context: Context) : RecyclerView.Adapter<ApplicationRVAdapter.ViewHolder>() {

    // 아이템 레이아웃 결합
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPrevBinding = ItemPrevBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        return ViewHolder(binding)
    }

    // 아이템 개수
    override fun getItemCount(): Int = application_list.size


    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(application_list[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(application_list[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemPrevBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(application: Application) {
            binding.txtClubName.text = application.crew_name
            var sdf = SimpleDateFormat("yyyy.MM.dd HH:mm")
            var date = sdf.format(application.submit_time)
            binding.itemDateTimeTxt.text = date

            if(application.is_pass == 1){
                binding.viewPassNpass.setBackgroundColor(Color.parseColor("#FF5252"))
            }else if(application.is_pass == 0){
                binding.viewPassNpass.setBackgroundColor(Color.parseColor("#CCFF5252"))
            }

            Glide.with(context).load(application.crew_profile)
                .circleCrop()
                .into(binding.itemProfileImg)

            // 날짜 적용도 추가하기
            binding.btnEditApplication.setOnClickListener {
                val intent = Intent(context, ApplicationEditActivity::class.java)
                itemView.context.startActivity(intent)
            }
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