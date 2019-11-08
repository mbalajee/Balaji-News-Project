package `in`.learn.balaji_news_project.news.data

import com.squareup.moshi.Json

/*

Response structure:
{
"status":"ok","totalResults":70,
"articles": [
"source": {
    "id": "fox-articles",
    "name": "Fox Article"
},
"author": "Viktoria Ristanovic",
"title": "",
"description": "",
"url": "",
"urlToImage": "",
"publishedAt": "2019-11-07T01:05:22Z",
"content": ""
]
}

*/

data class Article(private val source: Map<String, String>,
                   val author: String,
                   val title: String,
                   val description: String,
                   val url: String,
                   val urlToImage: String,
                   val publishedAt: String,
                   val content: String) {

    val sourceName: String
        get() = source["name"] ?: ""
}

data class News(val status: String, val totalResults: Int, val articles: List<Article>)