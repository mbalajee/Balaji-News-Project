package `in`.learn.balaji_news_project.service

import `in`.learn.balaji_news_project.BuildConfig
import `in`.learn.balaji_news_project.news.data.News
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//API Endpoint: https://newsapi.org/v2/top-headlines?country=us&category=entertainment&apiKey=API_KEY

interface NewsService {

    @GET("top-headlines") //?country=us&category=entertainment
    fun getNewsHeadlines(@Query("country") country: String,
                         @Query("category") category: String) : Call<News>


    companion object {

        private val authInterceptor = Interceptor { chain ->

            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter("apiKey", BuildConfig.API_KEY)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }

        private val newsClient = OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        private fun retrofit() : Retrofit = Retrofit.Builder()
            .client(newsClient)
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


        val service : NewsService by lazy { retrofit().create(NewsService::class.java) }

    }
}