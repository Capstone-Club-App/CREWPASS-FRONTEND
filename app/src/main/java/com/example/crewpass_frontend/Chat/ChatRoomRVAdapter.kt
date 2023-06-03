package com.example.crewpass_frontend.Chat

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crewpass_frontend.Data.ChatRoom
import com.example.crewpass_frontend.Retrofit.Chat.ChatData
import com.example.crewpass_frontend.Retrofit.Chat.ChatResult
import com.example.crewpass_frontend.Retrofit.Chat.ChatService
import com.example.crewpass_frontend.Retrofit.ChatRoom.ChatRoomData
import com.example.crewpass_frontend.Timestamp_to_SDF
import com.example.crewpass_frontend.databinding.ItemChatRoomBinding
import java.sql.Timestamp
import java.text.SimpleDateFormat

class ChatRoomRVAdapter (private val chatRoom_list: ArrayList<ChatRoomData>) : RecyclerView.Adapter<ChatRoomRVAdapter.ViewHolder>() {
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
        RecyclerView.ViewHolder(binding.root), ChatResult {
        fun bind(chatRoom: ChatRoomData) {
            binding.txtClubNameTitle.text = "[${chatRoom.crew_name}] ${chatRoom.title}"

            // 이전 채팅 내역 불러오기
            val chatService = ChatService()
            chatService.setChatResult(this)
            chatService.getChatAll(chatRoom.chat_room_id)
        }

        override fun getChatAllSuccess(code: Int, data: ArrayList<ChatData>) {
            if(data.size > 0){
                binding.itemChatContentTxt.text = data[data.count() - 1].content
                val sendTime = data[data.count() - 1].sendTime.substring(11 until 16)
                val hour = sendTime.substring(0 until 2)
                val minute = sendTime.subSequence(3 until 5)
                if(hour.toInt() > 12) {
                    binding.itemDateTxt.text = "오후 ${hour.toInt() - 12}:${minute}"
                }
                else
                    binding.itemDateTxt.text = "오전 ${hour}:${minute}"
            }
            else{
                binding.itemDateTxt.text = ""
                binding.itemChatContentTxt.text = "채팅을 시작해보세요"
            }
        }

        override fun getChatAllFailure(code: Int) {
            Log.d("채팅 내역 불러오기 실패", "")
        }
    }

    interface OnItemClickListener {
        fun onItemClick(announcement: ChatRoomData)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener
}