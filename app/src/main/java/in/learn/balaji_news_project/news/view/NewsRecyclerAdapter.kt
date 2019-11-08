package `in`.learn.balaji_news_project.news.view

import `in`.learn.balaji_news_project.databinding.ListItemNewsBinding
import `in`.learn.balaji_news_project.news.data.Article
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.learn.balaji_news_project.news.view.NewsListFragment.NewsListItemClickListener


class NewsRecyclerAdapter(private val listener: NewsListItemClickListener?) : RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {

    private var news = mutableListOf<Article>()

    private val itemClickListener: View.OnClickListener

    init {
        itemClickListener = View.OnClickListener { v ->
            val item = v.tag as Article
            listener?.onClickNewsItem(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = news[position]
        with(holder.binding.root) {
            tag = item
            setOnClickListener(itemClickListener)
        }
        holder.binding.article = item
    }

    override fun getItemCount(): Int = news.size

    fun update(articles: List<Article>) {
        if (this.news.isNullOrEmpty()) {
            this.news = arrayListOf()
        }
        this.news.clear()
        this.news.addAll(articles)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ListItemNewsBinding): RecyclerView.ViewHolder(binding.root)
}
