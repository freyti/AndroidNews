package com.example.parser.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parser.DetailActivity
import com.example.parser.models.News
import com.example.parser.R
import com.example.parser.fragments.DashFragment
import com.squareup.picasso.Picasso

class NewsAdapter(parseItems: ArrayList<News>, context: DashFragment) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var news: ArrayList<News> = parseItems
    private var context: DashFragment = context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.parse_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val new: News = news[position]
        holder.textView.text = new.title
        if (new.imgUrl=="")
            return
        else
            Picasso.get().load(new.imgUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        var imageView: ImageView
        var textView: TextView

        override fun onClick(view: View) {
            val position = adapterPosition
            val parseItem: News = news[position]
            val intent = Intent(context.context, DetailActivity::class.java)
            intent.putExtra("title", parseItem.title)
            intent.putExtra("image", parseItem.imgUrl)
            intent.putExtra("detailUrl", parseItem.detailUrl)
            context.startActivity(intent)
        }

        init {
            imageView = view.findViewById(R.id.imageView)
            textView = view.findViewById(R.id.textView)
            view.setOnClickListener(this)
        }
    }

}