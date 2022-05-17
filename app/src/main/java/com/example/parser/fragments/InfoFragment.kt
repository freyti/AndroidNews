package com.example.parser.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parser.*
import com.example.parser.adapters.GamesAdapter
import com.example.parser.models.Game
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException

class InfoFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: GamesAdapter
    val parseItems: ArrayList<Game> = ArrayList<Game>()
    lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)
        recyclerView = view.findViewById(R.id.recyclerView)

        //В результате recyclerView не будет перерисовываться каждый раз,
        // когда в элементе списка обновятся данные, этот элемент перерисуется самостоятельно.
        recyclerView.setHasFixedSize(true)
        //LinearLayoutManager - дочерние элементы размещаются вертикально (как в ListView) или горизонтально
        recyclerView.setLayoutManager(LinearLayoutManager(context))
        adapter = GamesAdapter(parseItems, this)
        recyclerView.setAdapter(adapter)

        val content: InfoFragment.Content = Content()
        content.execute()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    private inner class Content : AsyncTask<Void?, Void?, Void?>() {
        //выполняется перед doInBackground(). Имеет доступ к UI
        override fun onPreExecute() {
            try {
                super.onPreExecute()
                progressBar.visibility = View.VISIBLE
                progressBar.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@InfoFragment.context,
                        android.R.anim.fade_in
                    )
                )
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
        //Используйте его для обновления пользовательского интерфейса, как только ваша фоновая задача завершена.
        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            progressBar.visibility = View.GONE
            progressBar.startAnimation(
                AnimationUtils.loadAnimation(
                    this@InfoFragment.context,
                    android.R.anim.fade_out
                )
            )
            adapter.notifyDataSetChanged()
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            try{
                val url = "https://www.igromania.ru/articles/"
                val doc:org.jsoup.nodes.Document = Jsoup.connect(url).get()
                val data: Elements = doc.select("div.aubl_item")
                //val span:Elements = doc.select("span.thumbnail")
                val size = data.size

                Log.d("data", "data: $data")
                Log.d("size", "" + size)
                for (i in 0..size){
                    val imgUrl:String = data.select("img").eq(i).attr("src")
                    val title:String = data.select("a.aubli_name").eq(i).text()
                    val anyInfo:String = data.select("div.aubli_data").select("a").eq(i).attr("href")
                    Log.d("Result", imgUrl+" "+title+" https://www.igromania.ru"+anyInfo+" ")
                    parseItems.add(Game(imgUrl,title,"https://www.igromania.ru"+anyInfo))
                }

            }catch (e: IOException){
                e.printStackTrace()
            }
            return null
        }
    }
}