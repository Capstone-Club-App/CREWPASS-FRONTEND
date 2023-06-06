package com.example.crewpass_frontend.MyPage.Club

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.Club.Club.ClubPutResult
import com.example.crewpass_frontend.Retrofit.Club.Club.ClubService
import com.example.crewpass_frontend.databinding.ActivityClubEditInfoBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ClubEditInfoActivity : AppCompatActivity(), ClubPutResult {
    lateinit var binding: ActivityClubEditInfoBinding
    var region_list = ArrayList<String>()
    var field_list = ArrayList<String>()
    var btn_count_field = 0
    var btn_count_region = 0
    var field2 = ""
    var region2 = ""

    var fieldMap = HashMap<String, AppCompatButton>()
    var regionMap = HashMap<String, AppCompatButton>()

    private var PICK_IMAGE = 1
    var picture: MultipartBody.Part? = null

    var imagePath : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubEditInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(intent.getStringExtra("profile"))
            .centerCrop()
            .into(binding.profileImg)
        binding.edittextId.setText(intent.getStringExtra("crewLoginId"))
        binding.edittextPassword.setText(intent.getStringExtra("crewPw"))
        binding.edittextName.setText(intent.getStringExtra("crewName"))
        binding.edittextChairman.setText(intent.getStringExtra("crewMasterEmail"))
        binding.edittextViceChairman.setText(intent.getStringExtra("crewSubEmail"))

        fieldMap = hashMapOf(
            "문화,예술,교양" to binding.btnAllRegion,
            "봉사,사회활동" to binding.btnVolunteer,
            "학술,교양" to binding.btnResearch,
            "창업,취업" to binding.btnEmployment,
            "어학" to binding.btnLanguage,
            "체육" to binding.btnSports,
            "친목" to binding.btnFriend,
            "기타" to binding.btnEtc
        )

        regionMap = hashMapOf(
            "전체" to binding.btnAllRegion,
            "서울" to binding.btnSeoul,
            "경기도" to binding.btnGyeongi,
            "강원도" to binding.btnGangwon,
            "충청남도" to binding.btnChungcheonNam,
            "충청북도" to binding.btnChungcheonBook,
            "전라남도" to binding.btnJeollaNam,
            "전라북도" to binding.btnJeollaBook,
            "경상남도" to binding.btnGyeonsangNam,
            "경상북도" to binding.btnGyeonsangBook,
            "제주도" to binding.btnJeju
        )


        var region1_key = intent.getStringExtra("region1")
        for(i in regionMap){
            if(i.key.equals(region1_key))
                i.value.isSelected = true
        }

        var region2_key =intent.getStringExtra("region2")
        for(i in regionMap){
            if(i.key.equals(region2_key))
                i.value.isSelected = true
        }

        var field1_key =intent.getStringExtra("field1")
        for(i in regionMap){
            if(i.key.equals(field1_key))
                i.value.isSelected = true
        }

        var field2_key =intent.getStringExtra("field2")
        for(i in regionMap){
            if(i.key.equals(field2_key))
                i.value.isSelected = true
        }

        setRegion()
        setField()

        binding.btnProfile.setOnClickListener {
            getImage()
        }

        binding.btnSave.setOnClickListener {
            putClub()
        }
    }

    // 동아리 수정 저장
    fun putClub() {
        if(field_list.size != 1)
            field2 = field_list[1]
        else
            field2 = "null"

        if(region_list.size != 1)
            region2 = region_list[1]
        else
            region2 = "null"

        val pref = this.getPreferences(MODE_PRIVATE)
        if(imagePath == null){
            val file = File(absolutelyPath(Uri.parse(pref.getString("user_profile_uri","")), this))
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
            Log.d("파일 생성!! ======== ", file.name)
            picture = body
        }

        val clubService = ClubService()
        clubService.setClubPutResult(this)
        clubService.putClub(
            logined_id,
            binding.edittextName.text.toString(),
            binding.edittextPassword.text.toString(),
            region_list[0],
            region2,
            field_list[0],
            field2,
            binding.edittextChairman.text.toString(),
            binding.edittextViceChairman.text.toString(),
            picture!!
        )
    }

    override fun clubPutSuccess(code: Int) {
        Log.d("동아리 정보 수정 성공 ", "")
        finish()
    }

    override fun clubPutFailure(code: Int) {
        Log.d("동아리 정보 수정 실패 ", "")
    }

    // 이미지 가져오기
    fun getImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        intent.type = "image/*" // 모든 이미지
        startActivityForResult(intent, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 돌려받은 resultCode가 정상인지 체크
        if (resultCode == Activity.RESULT_OK) {
            // 사진 가져오는 부분
            imagePath = data!!.data

            val file = File(absolutelyPath(imagePath, this))
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
            Log.d("파일 생성!! ======== ", file.name)
            picture = body

            // sharedPreference에 기존 profile 저장해주기
            val sharedPreference = getSharedPreferences("crew_profile", MODE_PRIVATE)
            val editor : SharedPreferences.Editor = sharedPreference.edit()
            editor.putString("crew_profile_uri", imagePath.toString())
            editor.commit()

            Glide.with(this).load(imagePath)
                .circleCrop()
                .into(binding.profileImg)

        }
    }

    fun absolutelyPath(path: Uri?, context: Context): String {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result!!
    }

    // 버튼 이벤트 설정
    fun setRegion() {
        binding.btnAllRegion.setOnClickListener {
            binding.btnAllRegion.isSelected = !binding.btnAllRegion.isSelected

            if (binding.btnAllRegion.isSelected) {
                region_list.add("전체")
                if (binding.btnSeoul.isSelected || binding.btnGyeongi.isSelected || binding.btnGangwon.isSelected
                    || binding.btnChungcheonNam.isSelected || binding.btnChungcheonBook.isSelected
                    || binding.btnJeollaNam.isSelected || binding.btnJeollaBook.isSelected
                    || binding.btnGyeonsangNam.isSelected || binding.btnGyeonsangBook.isSelected || binding.btnJeju.isSelected
                ) {
                    btn_count_region = 1
                    binding.btnSeoul.isSelected = false
                    binding.btnGyeongi.isSelected = false
                    binding.btnGangwon.isSelected = false
                    binding.btnChungcheonNam.isSelected = false
                    binding.btnChungcheonBook.isSelected = false
                    binding.btnJeollaNam.isSelected = false
                    binding.btnJeollaBook.isSelected = false
                    binding.btnGyeonsangNam.isSelected = false
                    binding.btnGyeonsangBook.isSelected = false
                    binding.btnJeju.isSelected = false

                    region_list.remove("서울")
                    region_list.remove("경기")
                    region_list.remove("강원도")
                    region_list.remove("충청남도")
                    region_list.remove("충청북도")
                    region_list.remove("전라남도")
                    region_list.remove("전라북도")
                    region_list.remove("경상남도")
                    region_list.remove("경상북도")
                    region_list.remove("제주도")
                }
            } else {
                btn_count_region--
                region_list.remove("전체")
            }


        }

        binding.btnSeoul.setOnClickListener {
            binding.btnSeoul.isSelected = !binding.btnSeoul.isSelected
            if (binding.btnSeoul.isSelected) {
                if (btn_count_region == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnSeoul.isSelected = false
                } else {
                    btn_count_region++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count_region--
                    }
                    region_list.add("서울")
                }
            } else {
                btn_count_region--
                region_list.remove("서울")
            }
        }

        binding.btnGyeongi.setOnClickListener {
            binding.btnGyeongi.isSelected = !binding.btnGyeongi.isSelected
            if (binding.btnGyeongi.isSelected) {
                if (btn_count_region == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnGyeongi.isSelected = false
                } else {
                    btn_count_region++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count_region--
                    }
                    region_list.add("경기도")
                }
            } else {
                btn_count_region--
                region_list.remove("경기도")
            }
        }

        binding.btnGangwon.setOnClickListener {
            binding.btnGangwon.isSelected = !binding.btnGangwon.isSelected
            if (binding.btnGangwon.isSelected) {
                if (btn_count_region == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnGangwon.isSelected = false
                } else {
                    btn_count_region++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count_region--
                    }
                    region_list.add("강원도")
                }
            } else {
                btn_count_region--
                region_list.remove("강원도")
            }
        }

        binding.btnChungcheonNam.setOnClickListener {
            binding.btnChungcheonNam.isSelected = !binding.btnChungcheonNam.isSelected
            if (binding.btnChungcheonNam.isSelected) {
                if (btn_count_region == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnChungcheonNam.isSelected = false
                } else {
                    btn_count_region++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count_region--
                    }
                    region_list.add("충청남도")
                }
            } else {
                btn_count_region--
                region_list.remove("충청남도")
            }
        }

        binding.btnChungcheonBook.setOnClickListener {
            binding.btnChungcheonBook.isSelected = !binding.btnChungcheonBook.isSelected
            if (binding.btnChungcheonBook.isSelected) {
                if (btn_count_region == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnChungcheonBook.isSelected = false
                } else {
                    btn_count_region++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count_region--
                    }
                    region_list.add("충청북도")
                }
            } else {
                btn_count_region--
                region_list.remove("충청북도")
            }
        }

        binding.btnJeollaNam.setOnClickListener {
            binding.btnJeollaNam.isSelected = !binding.btnJeollaNam.isSelected
            if (binding.btnJeollaNam.isSelected) {
                if (btn_count_region == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnJeollaNam.isSelected = false
                } else {
                    btn_count_region++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count_region--
                    }
                    region_list.add("전라남도")
                }
            } else {
                btn_count_region--
                region_list.remove("전라남도")
            }
        }

        binding.btnJeollaBook.setOnClickListener {
            binding.btnJeollaBook.isSelected = !binding.btnJeollaBook.isSelected
            if (binding.btnJeollaBook.isSelected) {
                if (btn_count_region == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnJeollaBook.isSelected = false
                } else {
                    btn_count_region++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count_region--
                    }
                    region_list.add("전라북도")
                }
            } else {
                btn_count_region--
                region_list.remove("전라북도")
            }
        }

        binding.btnGyeonsangNam.setOnClickListener {
            binding.btnGyeonsangNam.isSelected = !binding.btnGyeonsangNam.isSelected
            if (binding.btnGyeonsangNam.isSelected) {
                if (btn_count_region == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnGyeonsangNam.isSelected = false
                } else {
                    btn_count_region++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count_region--
                    }
                    region_list.add("경상남도")
                }
            } else {
                btn_count_region--
                region_list.remove("경상남도")
            }
        }

        binding.btnGyeonsangBook.setOnClickListener {
            binding.btnGyeonsangBook.isSelected = !binding.btnGyeonsangBook.isSelected
            if (binding.btnGyeonsangBook.isSelected) {
                if (btn_count_region == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnGyeonsangBook.isSelected = false
                } else {
                    btn_count_region++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count_region--
                    }
                    region_list.add("경상북도")
                }
            } else {
                btn_count_region--
                region_list.remove("경상북도")
            }
        }

        binding.btnJeju.setOnClickListener {
            binding.btnJeju.isSelected = !binding.btnJeju.isSelected
            if (binding.btnJeju.isSelected) {
                if (btn_count_region == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnJeju.isSelected = false
                } else {
                    btn_count_region++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count_region--
                    }
                    region_list.add("제주도")
                }
            } else {
                btn_count_region--
                region_list.remove("제주도")
            }
        }
    }

    fun setField() {
        // 버튼 이벤트
        binding.btnArt.setOnClickListener {
            binding.btnArt.isSelected = !binding.btnArt.isSelected
            if (binding.btnArt.isSelected) {
                if (btn_count_field == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                } else {
                    btn_count_field++
                    field_list.add("문화,예술,교양")
                }
            } else {
                btn_count_field--
                field_list.remove("문화,예술,교양")
            }


        }

        binding.btnVolunteer.setOnClickListener {
            binding.btnVolunteer.isSelected = !binding.btnVolunteer.isSelected
            if (binding.btnVolunteer.isSelected) {
                if (btn_count_field == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                } else {
                    btn_count_field++
                    field_list.add("봉사,사회활동")
                }
            } else {
                btn_count_field--
                field_list.remove("봉사,사회활동")
            }
        }

        binding.btnResearch.setOnClickListener {

            binding.btnResearch.isSelected = !binding.btnResearch.isSelected
            if (binding.btnResearch.isSelected) {
                if (btn_count_field == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                } else {
                    btn_count_field++
                    field_list.add("학술,교양")
                }
            } else {
                btn_count_field--
                field_list.remove("학술,교양")
            }
        }

        binding.btnEmployment.setOnClickListener {
            binding.btnEmployment.isSelected = !binding.btnEmployment.isSelected
            if (binding.btnEmployment.isSelected) {
                if (btn_count_field == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                } else {
                    btn_count_field++
                    field_list.add("창업,취업")
                }
            } else {
                btn_count_field--
                field_list.remove("창업,취업")
            }
        }

        binding.btnLanguage.setOnClickListener {
            binding.btnLanguage.isSelected = !binding.btnLanguage.isSelected
            if (binding.btnLanguage.isSelected) {
                if (btn_count_field == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                } else {
                    btn_count_field++
                    field_list.add("어학")
                }
            } else {
                btn_count_field--
                field_list.remove("어학")
            }
        }

        binding.btnSports.setOnClickListener {
            binding.btnSports.isSelected = !binding.btnSports.isSelected
            if (binding.btnSports.isSelected) {
                if (btn_count_field == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                } else {
                    btn_count_field++
                    field_list.add("체육")
                }
            } else {
                btn_count_field--
                field_list.remove("체육")
            }
        }

        binding.btnFriend.setOnClickListener {
            binding.btnFriend.isSelected = !binding.btnFriend.isSelected
            if (binding.btnFriend.isSelected) {
                if (btn_count_field == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                } else {
                    btn_count_field++
                    field_list.add("친목")
                }
            } else {
                btn_count_field--
                field_list.remove("친목")
            }
        }

        binding.btnEtc.setOnClickListener {
            binding.btnEtc.isSelected = !binding.btnEtc.isSelected
            if (binding.btnEtc.isSelected) {
                if (btn_count_field == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                } else {
                    btn_count_field++
                    field_list.add("기타")
                }
            } else {
                btn_count_field--
                field_list.remove("기타")
            }
        }
    }


}