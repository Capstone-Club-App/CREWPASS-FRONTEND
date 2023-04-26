package com.example.crewpass_frontend.SignUp.Personal.FindSchool

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crewpass_frontend.Retrofit.FindSchool.Data
import com.example.crewpass_frontend.databinding.ItemFindSchoolBinding

class SchoolRVAdapter () : RecyclerView.Adapter<SchoolRVAdapter.ViewHolder>() {

    lateinit var context: Context

    private var items : List<Data> = ArrayList()
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
    override fun getItemCount(): Int = items.size

    private var mSelectedItem = -1

    fun setItem(item: List<Data>) {
        items = item
        mSelectedItem = -1
        notifyDataSetChanged()
    }

    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(items[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemFindSchoolBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            binding.checkboxSchool.isChecked = adapterPosition == mSelectedItem
            binding.checkboxSchool.setOnClickListener {
                mSelectedItem = adapterPosition
                notifyItemRangeChanged(0, items.size)
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