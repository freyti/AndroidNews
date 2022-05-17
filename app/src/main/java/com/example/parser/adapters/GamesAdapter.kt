package com.example.parser.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parser.DetailActivity
import com.example.parser.models.Game
import com.example.parser.R
import com.example.parser.fragments.InfoFragment
import com.squareup.picasso.Picasso

class GamesAdapter (parseItems: ArrayList<Game>, context: InfoFragment) :
    RecyclerView.Adapter<GamesAdapter.ViewHolder>() {
    private var games: ArrayList<Game> = parseItems
    private var context: InfoFragment = context
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
        val games: Game = games[position]
        holder.textView.text = games.title1
        if (games.imgUrl1=="")
            return
        else
            Picasso.get().load(games.imgUrl1).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        var imageView: ImageView
        var textView: TextView

        override fun onClick(view: View) {
            val position = adapterPosition
            val parseItem: Game = games[position]
            val intent = Intent(context.context, DetailActivity::class.java)
            intent.putExtra("title", parseItem.title1)
            intent.putExtra("image", parseItem.imgUrl1)
            intent.putExtra("detailUrl", parseItem.detailUrl1)
            context.startActivity(intent)
        }

        init {
            imageView = view.findViewById(R.id.imageView)
            textView = view.findViewById(R.id.textView)
            view.setOnClickListener(this)
        }
    }

}