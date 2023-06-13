package com.example.crewpass_frontend.AI.Club.Interview

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.Retrofit.Club.Application.ApplicationData
import com.example.crewpass_frontend.Retrofit.FindSchool.Data
import com.example.crewpass_frontend.Retrofit.Personal.Application.ApplicationGetResult
import com.example.crewpass_frontend.Retrofit.Personal.Application.ApplicationService
import com.example.crewpass_frontend.Timestamp_to_SDF
import com.example.crewpass_frontend.databinding.ItemClubApplicationCheckboxBinding

class AIApplicationRVAdapter (private val application_list: ArrayList<ApplicationData>, private val context: Context) : RecyclerView.Adapter<AIApplicationRVAdapter.ViewHolder>() {

    private var items : List<ApplicationData> = ArrayList()
    var user_name = ""
    var application_id_selected = -1

    // 아이템 레이아웃 결합
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemClubApplicationCheckboxBinding = ItemClubApplicationCheckboxBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        return ViewHolder(binding)
    }

    // 아이템 개수
    override fun getItemCount(): Int = application_list.size

    private var mSelectedItem = -1

    fun setItem(item: List<ApplicationData>) {
        items = item
        mSelectedItem = -1
        notifyDataSetChanged()
    }

    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(application_list[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(application_list[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemClubApplicationCheckboxBinding) :
        RecyclerView.ViewHolder(binding.root), ApplicationGetResult {
        fun bind(application: ApplicationData) {
            binding.txtClubName.text = application.user_name

            val applicationService = ApplicationService()
            applicationService.setApplicationGetResult(this)
            applicationService.getApplication(application.application_id)

            Glide.with(context).load(application.user_profile)
                .circleCrop()
                .into(binding.itemProfileImg)

            val timestampToSdf = Timestamp_to_SDF()
            binding.itemDateTxt.text = timestampToSdf.convert(application.submit_time)

            binding.itemCheckBox.setOnClickListener {
                mSelectedItem = adapterPosition
                if(binding.itemCheckBox.isChecked){
                    application_id_selected = application.application_id
                    user_name = application.user_name
                }
                notifyItemRangeChanged(0, items.size)
            }
        }

        override fun applicationGetSuccess(
            code: Int,
            data: ArrayList<com.example.crewpass_frontend.Retrofit.Personal.Application.ApplicationData>
        ) {
            binding.itemContentTxt.text = data[0].answer1
        }

        override fun applicationGetFailure(code: Int) {
            Log.d("상세 조회 실패", "")
        }
    }


    interface OnItemClickListener {
        fun onItemClick(application: ApplicationData)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener

}