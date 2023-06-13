package com.example.crewpass_frontend.Home.Personal.List

import android.content.Context
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.Retrofit.Personal.Scrap.getResult
import com.example.crewpass_frontend.databinding.ItemAnnouncementPersonalBinding


class RecruitmentRVAdapter (private val recruitment_list: ArrayList<getResult>)
    : RecyclerView.Adapter<RecruitmentRVAdapter.ViewHolder>()
{
    var checkStatus = SparseBooleanArray()
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

    // view에 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recruitment_list[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(recruitment_list[position])
            notifyItemChanged(position)
        }
    }

    // 레이아웃 내 view 연결
    inner class ViewHolder(val binding: ItemAnnouncementPersonalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var scrap_id_get : Int = -1

        fun bind(recruitment: getResult) {
            binding.btnHeart.setBackgroundResource(R.drawable.img_heart_fill)
//            scrap_list.forEach {
//                if(recruitment.recruitment_id == it.recruitment_id) {
//                    binding.btnHeart.isSelected = true
//                    checkStatus.put(adapterPosition, true)
//                    binding.btnHeart.setBackgroundResource(R.drawable.img_heart_fill)
//                    scrap_id_get = it.scrap_id
//                }
//            }
//
//            binding.btnHeart.setOnClickListener {
//                binding.btnHeart.isSelected = !binding.btnHeart.isSelected
//                if(!binding.btnHeart.isSelected) { // 스크랩 취소
//                    val scrapService = ScrapService()
//                    scrapService.setScrapDeleteResult(this)
//                    scrapService.deleteScrap(scrap_id_get)
//                }
//                else { // 스크랩 등록
//                    // 버튼 클릭상태
//                    val scrapService = ScrapService()
//                    scrapService.setScrapPostResult(this)
//                    scrapService.postScrap(recruitment.recruitment_id)
//                }
//                notifyDataSetChanged()
//            }

            binding.itemAnnounceDetail.text = recruitment.crew_name
            binding.itemAnnounceTitle.text = recruitment.title
            binding.itemDayTxt.text = recruitment.d_day.toString()

            var hashTagList = ArrayList<String>()
            hashTagList.add(recruitment.field1)
            if(!recruitment.field2.equals("null"))
                hashTagList.add(recruitment.field2)

            var hashString = ""
            hashTagList.forEach {
                hashString += "#$it "
            }

            Glide.with(context).load(recruitment.crew_profile)
                .circleCrop()
                .into(binding.profileImg)
        }


//        // 스크랩 등록
//        override fun scrapPostSuccess(code: Int, scrap_id: Int) {
//            Log.d("스크랩 넣기" , "")
//            checkStatus.put(adapterPosition, true)
//            binding.btnHeart.setBackgroundResource(R.drawable.img_heart_fill)
//            scrap_id_get = scrap_id
//        }
//
//        override fun scrapPostFailure(code: Int) {
//            Log.d("스크랩 등록 실패" , "")
//        }
//
//        // 스크랩 취소
//        override fun scrapDeleteSuccess(code: Int, data: Any?) {
//            Log.d("스크랩 취소" , "")
//            checkStatus.put(adapterPosition, false)
//            binding.btnHeart.setBackgroundResource(R.drawable.img_heart_notfill)
//        }
//
//        override fun scrapDeleteFailure(code: Int) {
//            Log.d("스크랩 취소 실패" , "")
//        }
    }

    interface OnItemClickListener {
        fun onItemClick(recruitment: getResult)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener



}