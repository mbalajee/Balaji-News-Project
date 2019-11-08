package `in`.learn.balaji_news_project.news.data

import `in`.learn.balaji_news_project.service.NewsService
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRemoteSource : NewsDataSource {

    override fun getNews(country: String, category: String, callBack: DataSourceCallBack<List<Article>>)
    {
        val call = NewsService.service.getNewsHeadlines(country, category)
        call.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                callBack(null, t.message ?: "Error occurred while fetching articles")
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()?.articles
                Log.d("NEWS_RESPONSE", news.toString())
                callBack(news, null) // Create suitable error message based on response.errorBody()
            }
        })

    }

    override fun saveNews(list: List<Article>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}