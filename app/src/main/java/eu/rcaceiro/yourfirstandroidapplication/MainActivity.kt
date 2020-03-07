package eu.rcaceiro.yourfirstandroidapplication

import android.os.Bundle
import android.view.View
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import eu.rcaceiro.yourfirstandroidapplication.api.ScoreService
import eu.rcaceiro.yourfirstandroidapplication.model.Score
import eu.rcaceiro.yourfirstandroidapplication.model.score
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        this.number = Random(System.currentTimeMillis()).nextInt(0, 20)
    }

    fun onClickSend(v: View) {
        val editText = this.findViewById<TextInputEditText>(R.id.editTextGuess)
        val textView = this.findViewById<MaterialTextView>(R.id.materialTextViewSuggestions)
        val insertedNumber = editText.text.toString().toInt(10)
        when {
            this.number > insertedNumber -> {
                textView.text = "The number is higher"
            }
            this.number < insertedNumber -> {
                textView.text = "The number is lower"
            }
            else -> {
                textView.text = "That's it"
                insertedNumber.score?.let {
                    this.sendToAPI(it)
                }
                //Start new activity
            }
        }
    }

    @WorkerThread
    private fun sendToAPI(score: Score) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ScoreService::class.java).apply {
            this.sendScore(score)
        }
    }
}