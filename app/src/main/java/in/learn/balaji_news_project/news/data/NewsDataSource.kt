package `in`.learn.balaji_news_project.news.data

typealias DataSourceCallBack<T> = (data: T?, error: String?) -> Unit

interface NewsDataSource {

    fun getNews(country: String, category: String, callBack: DataSourceCallBack<List<Article>>)
    fun saveNews(list: List<Article>)


}