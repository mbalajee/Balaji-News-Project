package `in`.learn.balaji_news_project

import `in`.learn.balaji_news_project.news.data.NewsRepository
import `in`.learn.balaji_news_project.news.viewmodel.NewsViewModel
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class ViewModelFactory private constructor(private val app: Application,
                                           private val newsRepo: NewsRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {

        private var INSTANCE: ViewModelFactory? = null

        fun instance(application: Application): ViewModelFactory? {

            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(application, Injection.provideNewsRepository(application.applicationContext))
                    }
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(newsRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}