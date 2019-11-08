package `in`.learn.balaji_news_project.news.view

import `in`.learn.balaji_news_project.databinding.FragmentNewsListBinding
import `in`.learn.balaji_news_project.news.data.Article
import `in`.learn.balaji_news_project.news.viewmodel.NewsViewModel
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class NewsListFragment : Fragment() {

    private var listener: NewsListItemClickListener? = null

    private var binding: FragmentNewsListBinding? = null

    private var viewModel: NewsViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)

        viewModel = NewsActivity.obtainViewModel(activity!!)

        binding!!.viewModel = this.viewModel

        binding!!.listNews.adapter = NewsRecyclerAdapter(listener)

        loadNews()

        return binding!!.root
    }

    private fun loadNews() {
        viewModel?.getNews("us", "entertainment")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewsListItemClickListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement NewsListItemClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface NewsListItemClickListener {
        fun onClickNewsItem(item: Article)
    }
}
