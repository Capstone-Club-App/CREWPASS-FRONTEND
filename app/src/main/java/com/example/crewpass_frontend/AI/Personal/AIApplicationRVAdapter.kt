package com.example.crewpass_frontend.AI.Personal

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.Retrofit.Personal.Application.Application
import com.example.crewpass_frontend.Timestamp_to_SDF
import com.example.crewpass_frontend.databinding.ItemPersonalApplicationCheckboxBinding

class AIApplicationRVAdapter (private val application_list: ArrayList<Application>, private val context: Context) : RecyclerView.Adapter<AIApplicationRVAdapter.ViewHolder>() {

    private var items : List<Application> = ArrayList()
    var application_id_selected = -1

    // 아이템 레이아웃 결합
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPersonalApplicationCheckboxBinding = ItemPersonalApplicationCheckboxBinding.inflate(
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
    inner class ViewHolder(val binding: ItemPersonalApplicationCheckboxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(application: Application) {
            binding.txtClubName.text = application.crew_name

            val timestampToSdf = Timestamp_to_SDF()
            binding.itemDateTxt.text = timestampToSdf.convert(application.submit_time)

            Glide.with(context).load(application.crew_profile)
                .circleCrop()
                .into(binding.itemProfileImg)

            binding.itemCheckBox.setOnClickListener {
                mSelectedItem = adapterPosition
                if(binding.itemCheckBox.isChecked){
                    application_id_selected = application.application_id

                }
                notifyItemRangeChanged(0, items.size)
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