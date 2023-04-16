package com.example.crewpass_frontend.Chat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crewpass_frontend.Data.ChatRoom
import com.example.crewpass_frontend.databinding.ItemChatRoomBinding

class ChatRoomRVAdapter (private val chatRoom_list: ArrayList<ChatRoom>) : RecyclerView.Adapter<ChatRoomRVAdapter.ViewHolder>() {
    lateinit var context: Context

    // 아이템 레이아웃 결합
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemChatRoomBinding = ItemChatRoomBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        context = viewGroup.context
        return ViewHolder(binding)
    }

    // 아이템 개수
    override fun getItemCount(): Int = chatRoom_list.size

    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(chatRoom_list[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(chatRoom_list[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemChatRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatRoom: ChatRoom) {
            binding.txtClubName.text = chatRoom.club_name
            binding.itemChatContentTxt.text = chatRoom.conent
            binding.itemDateTxt.text = chatRoom.time
        }
    }

    interface OnItemClickListener {
        fun onItemClick(announcement: ChatRoom)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener
}