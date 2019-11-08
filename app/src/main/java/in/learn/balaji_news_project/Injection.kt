package `in`.learn.balaji_news_project

import `in`.learn.balaji_news_project.news.data.NewsLocalSource
import `in`.learn.balaji_news_project.news.data.NewsRemoteSource
import `in`.learn.balaji_news_project.news.data.NewsRepository
import android.content.Context

object Injection {

    fun provideNewsRepository(context: Context): NewsRepository {
        // TODO: Create DB for local data source
//        val database = ToDoDatabase.getInstance(context)

        return NewsRepository(NewsLocalSource(), NewsRemoteSource())
    }
}