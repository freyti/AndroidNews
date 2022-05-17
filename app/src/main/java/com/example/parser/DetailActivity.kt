package com.example.parser

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException

class DetailActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var titleTextView: TextView
    lateinit var detailTextView: TextView
    lateinit var urlTextView: TextView
    lateinit var detailString: String
    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imageView = findViewById(R.id.imageView)
        titleTextView = findViewById(R.id.textView)
        detailTextView = findViewById(R.id.detailText)
        urlTextView = findViewById(R.id.urL)

        titleTextView.setText(intent.getStringExtra("title"))
        Picasso.get().load(intent.getStringExtra("image")).into(imageView)
        val content: Content = Content()
        content.execute()
    }

    private inner class Content : AsyncTask<Void?, Void?, Void?>() {
        //выполняется перед doInBackground(). Имеет доступ к UI
        override fun onPreExecute() {
            super.onPreExecute()

        }

        //Используйте его для обновления пользовательского интерфейса, как только ваша фоновая задача завершена.
        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            detailTextView.text = detailString
            urlTextView.text=url
        }


        override fun doInBackground(vararg p0: Void?): Void? {
            try {
                val detailUrl = intent.getStringExtra("detailUrl")
                if (detailUrl != null) {
                    url = detailUrl
                }
                val doc: org.jsoup.nodes.Document = Jsoup.connect(url).get()
                val data: Elements = doc.select("div.universal_content.clearfix")

                detailString = data.select("div").select("strong").text()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }

    }
}