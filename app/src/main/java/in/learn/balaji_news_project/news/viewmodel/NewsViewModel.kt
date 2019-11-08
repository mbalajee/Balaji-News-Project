package `in`.learn.balaji_news_project.news.viewmodel

import `in`.learn.balaji_news_project.news.data.Article
import `in`.learn.balaji_news_project.news.data.NewsRepository
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    val newsList = ObservableArrayList<Article>()

    val progress = ObservableBoolean(false)

    fun getNews(country: String, category: String) {

        progress.set(true)

        repository.getNews(country, category) { articles: List<Article>?, error: String? ->

            progress.set(false)

            if (articles.isNullOrEmpty()) {
                // May be use Toast to show the error
            } else {
                newsList.clear()
                newsList.addAll(articles)
            }
        }
    }

}