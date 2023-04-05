package com.example.crewpass_frontend.SignUp.Personal.FindSchool

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crewpass_frontend.Retrofit.FindSchool.Data
import com.example.crewpass_frontend.databinding.ItemFindSchoolBinding

class SchoolRVAdapter (private val joinList: MutableList<Data>) : RecyclerView.Adapter<SchoolRVAdapter.ViewHolder>() {

    lateinit var context: Context

    private val checkList = arrayListOf<CheckStatus>()

    // 아이템 레이아웃 결합
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemFindSchoolBinding = ItemFindSchoolBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        context = viewGroup.context
        return ViewHolder(binding)
    }

    // 아이템 개수
    override fun getItemCount(): Int = joinList.size

    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(joinList[position], checkList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(joinList[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemFindSchoolBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data, checkList : CheckStatus) {
            binding.checkboxSchool.isChecked = checkList.isChecked
            binding.checkboxSchool.setOnClickListener {
                checkList.isChecked = binding.checkboxSchool.isChecked
                notifyItemChanged(adapterPosition)
            }
            binding.txtSchool.text = data.schoolName
        }
    }

    interface OnItemClickListener {
        fun onItemClick(data: Data)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener

    data class CheckStatus(val position: Int, var isChecked : Boolean)
}