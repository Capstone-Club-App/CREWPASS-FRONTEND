package com.example.crewpass_frontend.Home.Personal.List.AnswerList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crewpass_frontend.databinding.ItemQuestionBinding

class QuestionEditRVAdapter(private val question_list : ArrayList<String>, private val question_marin_list : ArrayList<Int>,
                            private val answer_list : ArrayList<String>, private val answer_count_list : ArrayList<Int>) : RecyclerView.Adapter<QuestionEditRVAdapter.ViewHolder>() {

    lateinit var context: Context


    // 아이템 레이아웃 결합
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemQuestionBinding = ItemQuestionBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        context = viewGroup.context
        return ViewHolder(binding)
    }

    // 아이템 개수
    override fun getItemCount(): Int = question_list.size

    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(question_list[position], question_marin_list[position], position, answer_list[position], answer_count_list[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(question_list[position], question_marin_list[position], answer_list[position], answer_count_list[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(question: String, question_marin : Int, position: Int, answer : String, answer_count : Int) {
            binding.txtItemQuestionNum.text = (position+1).toString() + ". "  // 1. 생성
            binding.txtItemQuestion.text = question
            binding.txtItemMarginTextNum.text = question_marin.toString()

            binding.edittextItemAnswer.setText(answer)
            binding.txtItemCurrentTextNum.text = answer_count.toString()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(question: String, question_marin : Int, answer : String, answer_count: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener

}