package com.example.crewpass_frontend.AI.Club.Interview

import com.example.crewpass_frontend.R
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.example.crewpass_frontend.Retrofit.Club.AI.AnalyzeApplicationResult
import com.example.crewpass_frontend.Retrofit.Club.AI.AnalyzeApplicationService
import com.example.crewpass_frontend.Retrofit.Club.AI.AnalyzeResult
import com.example.crewpass_frontend.databinding.ActivityAnalyzedApplicationBinding


class AnalyzedApplicationActivity: AppCompatActivity(), AnalyzeApplicationResult {
    lateinit var binding : ActivityAnalyzedApplicationBinding
    lateinit var progressDialog: AppCompatDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalyzedApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var user_name = intent.getStringExtra("user_name")
        var application_id = intent.getIntExtra("application_id", -1)
        Log.d("", "application_id : $application_id")

        if(application_id != -1){
            analyze_application(application_id)
        }
        binding.txtName.text = user_name

        initActionBar()
        progressON()

        binding.btnPdf.setOnClickListener {
        }
    }

    // 로딩화면 띄우기
    fun progressON(){
        progressDialog = AppCompatDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.setContentView(R.layout.dialog_loading)
        progressDialog.show()
        var img_loading_framge = progressDialog.findViewById<ImageView>(R.id.iv_frame_loading)
        var frameAnimation = img_loading_framge?.getBackground() as AnimationDrawable
        img_loading_framge?.post(object : Runnable{
            override fun run() {
                frameAnimation.start()
            }
        })
    }

    fun progressOFF(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss()
        }
    }

    // chatGPT 결과 가져오기
    fun analyze_application(application_id : Int){
        val analyzeApplicationService = AnalyzeApplicationService()
        analyzeApplicationService.setAnalyzeApplicationResult(this)
        analyzeApplicationService.analyzeApplication(application_id)

    }

    fun initActionBar() {
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 분석 결과"

        binding.innerPageTop.appbarBackBtn.setOnClickListener { onBackPressed() }
    }

    override fun analyzeApplicationSuccess(code: Int, data: AnalyzeResult) {
        progressOFF()
        val questionList = data.interview
        var question_string = ""
        questionList.forEachIndexed { index, item ->
            if(index != questionList.size - 1)
                question_string += item + "<br>"
            else
                question_string += item
        }
        binding.edittextQuestion.text = Html.fromHtml(question_string)

        val valueList1 = data.questionAnswerHashMap[1]
        binding.txtQuestion1.text = valueList1!![0]
        binding.edittextQuestion1Original.text = valueList1[1]
        binding.edittextQuestion1Short.text = data.summary[0]

        val valueList2 = data.questionAnswerHashMap[2]
        binding.txtQuestion2.text = valueList2!![0]
        binding.edittextQuestion2Original.text = valueList2[1]
        binding.edittextQuestion2Short.text = data.summary[1]

        val valueList3 = data.questionAnswerHashMap[3]
        binding.txtQuestion3.text = valueList3!![0]
        binding.edittextQuestion3Original.text = valueList3[1]
        binding.edittextQuestion3Short.text = data.summary[2]

        if (data.questionCount >= 4) {
            binding.layoutQuestion4.visibility = View.VISIBLE

            val valueList4 = data.questionAnswerHashMap[4]
            binding.txtQuestion4.text = valueList4!![0]
            binding.edittextQuestion4Original.text = valueList4[1]
            binding.edittextQuestion4Short.text = data.summary[3]

            if(data.questionCount >= 5){
                binding.layoutQuestion5.visibility = View.VISIBLE

                val valueList5 = data.questionAnswerHashMap[5]
                binding.txtQuestion5.text = valueList5!![0]
                binding.edittextQuestion5Original.text = valueList5[1]
                binding.edittextQuestion5Short.text = data.summary[4]

                if(data.questionCount >= 6){
                    binding.layoutQuestion6.visibility = View.VISIBLE

                    val valueList6 = data.questionAnswerHashMap[6]
                    binding.txtQuestion6.text = valueList6!![0]
                    binding.edittextQuestion6Original.text = valueList6[1]
                    binding.edittextQuestion6Short.text = data.summary[5]

                    if(data.questionCount >= 7){

                        val valueList7 = data.questionAnswerHashMap[7]
                        binding.txtQuestion7.text = valueList7!![0]
                        binding.edittextQuestion7Original.text = valueList7[1]
                        binding.edittextQuestion7Short.text = data.summary[6]

                        binding.layoutQuestion7.visibility = View.VISIBLE
                    }
                }
            }
        }

    }

    override fun analyzeApplicationFailure(code: Int) {
        progressOFF()
        Log.d("분석 실패", "")
    }

/*
    fun layoutToImage(){
        val bm : Bitmap = Bitmap.createBitmap(binding.wholeLayout.width,
        binding.wholeLayout.height, Bitmap.Config.ARGB_8888)
        val canvas : Canvas = Canvas(bm)
        val bgDrawable = binding.wholeLayout.background
        if(bgDrawable != null){
            bgDrawable.draw(canvas)
        }
        else{
            canvas.drawColor(Color.WHITE)
        }
        binding.wholeLayout.draw(canvas)
        val bytes : ByteArrayOutputStream = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

        //var fullPath = Environment.getExternalStorageDirectory().toString() + File.
        var fullPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        var fileName = "image.jpg"
        val f : File = File(fullPath, fileName)
        try{

            f.createNewFile()
            val fo : FileOutputStream = FileOutputStream(f)
            fo.write(bytes.toByteArray())

            val document : Document = Document()
            val dirpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
            val currentTimeMillis: Long = System.currentTimeMillis()
            PdfWriter.getInstance(document, FileOutputStream(
                dirpath + "/CrewPass_${currentTimeMillis}.pdf"
            ))
            Log.d("path : ", dirpath + "/CrewPass_${currentTimeMillis}.pdf")
            document.open()

            val image : Image = Image.getInstance(f.toString())
            val scaler : Float = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin() - 0) / image.getWidth()) * 100;
            image.scalePercent(scaler)
            image.alignment = Image.ALIGN_MIDDLE
            document.add(image)
            document.close()

            Toast.makeText(this, "PDF파일 저장 성공", Toast.LENGTH_SHORT)
            f.delete()
            finish()
        } catch (e : java.lang.Exception){
            e.printStackTrace()
        }

    } */
}