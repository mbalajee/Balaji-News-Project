package `in`.learn.balaji_news_project.news.data

class NewsLocalSource : NewsDataSource {

    override fun getNews(country: String, category: String, callBack: DataSourceCallBack<List<Article>>) {
        // Fetch from room
    }

    override fun saveNews(list: List<Article>) {
        // Save to room
    }
}