package `in`.learn.balaji_news_project.news.data

class NewsRepository(private val localSource: NewsLocalSource, private val remoteSource: NewsRemoteSource) : NewsDataSource {

    val forceUpdate = false

    override fun getNews(country: String, category: String, callBack: DataSourceCallBack<List<Article>>) {

        // Show the cached news
        localSource.getNews(country, category) { articles: List<Article>?, _: String? ->
            callBack(articles ?: listOf(), null)
        }

        // Refresh Room
        remoteSource.getNews(country, category) { articles: List<Article>?, error: String? ->
            if (articles != null) {
                localSource.saveNews(articles)
                callBack(articles, null)
            } else {
                callBack(null, error)
            }
        }
    }

    override fun saveNews(list: List<Article>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}