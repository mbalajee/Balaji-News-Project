package `in`.learn.balaji_news_project

import `in`.learn.balaji_news_project.news.data.Article
import `in`.learn.balaji_news_project.news.view.NewsRecyclerAdapter
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
//    Picasso.get().load(url).into(view)
    Glide.with(view).load(url).into(view)
}

@BindingAdapter("app:items")
fun setItems(list: RecyclerView, items: List<Article>) {
    val adapter = list.adapter as NewsRecyclerAdapter
    adapter.update(items)
}