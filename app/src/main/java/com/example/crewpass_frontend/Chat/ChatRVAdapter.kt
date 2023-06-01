package com.example.crewpass_frontend.Chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.Data.Chat
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.Timestamp_to_SDF

class ChatRVAdapter(
    val context: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var chatList = mutableListOf<Chat>()
    lateinit var glide_context : Context

    interface MyItemClickListener {
        fun clickButton1(chat: Chat)
        fun clickButton2(chat: Chat)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    //처음에 화면에 보일 아이템뷰 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        glide_context = parent.context
        return when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_your_chat, parent, false
                )
                LeftViewHolder(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_my_chat, parent, false
                )
                RightViewHolder(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_time_date_txt, parent, false
                )
                CenterViewHolder(view)
            }

            else -> {
                throw RuntimeException("Error")
            }
        }
    }

    //뷰홀더에 데이터를 바인딩할때마다 호출되는 함수
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (chatList[position].viewType) {
            1 -> {
                (holder as LeftViewHolder).bind(chatList[position])
                holder.setIsRecyclable(false)
            }
            2 -> {
                (holder as RightViewHolder).bind(chatList[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as CenterViewHolder).bind(chatList[position])
                holder.setIsRecyclable(false)

            }

        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    fun addItem(chat: Chat) {
        chatList.add(chat)
    }

    //xml을 여러개 사용하려면 오버라이딩 해줘야 함
    override fun getItemViewType(position: Int): Int {
        return chatList[position].viewType
    }

    inner class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val content: AppCompatButton = view.findViewById(R.id.your_chat_iv)
        private val name : TextView = view.findViewById(R.id.your_name_txt)
        private val time : TextView = view.findViewById(R.id.your_chat_date_txt)
        private val image_time : TextView = view.findViewById(R.id.your_image_date_txt)
        private val picture : ImageView = view.findViewById(R.id.your_image_iv)

        fun bind(chat: Chat) {
            name.text = chat.senderName
            content.text = chat.content
            time.text = chat.sendTime

            if(chat.senderName.equals("성신한 집사들")){
                content.setBackgroundResource(R.drawable.crew_chat_box)
            }

            // 동아리 이름 오면 ui 바꿔주는 거 추가하기!!!
        }

    }



    inner class RightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val content: AppCompatButton = view.findViewById(R.id.my_chat_iv)
        private val time : TextView = view.findViewById(R.id.my_chat_date_txt)
        private val image_time : TextView = view.findViewById(R.id.my_image_date_txt)
        private val picture : ImageView = view.findViewById(R.id.my_image_iv)
        fun bind(chat: Chat) {
            content.text = chat.content
            time.text = chat.sendTime
        }
    }


    /// 시간, 나갔습니다, 들어왔습니다 등등
    inner class CenterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val time: TextView = view.findViewById(R.id.txt_center_time_date)

        fun bind(chat: Chat) {
            time.text = chat.sendTime
        }
    }
}