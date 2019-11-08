package `in`.learn.balaji_news_project.news.view

import `in`.learn.balaji_news_project.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import kotlinx.android.synthetic.main.list_item_news.*
import kotlinx.android.synthetic.main.news_detail_fragment.*

class NewsDetailFragment : Fragment() {

    private var urlToNews: String? = null
    private var author: String? = null

    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        urlToNews = arguments?.getString(ARG_URL_TO_NEWS)
        author = arguments?.getString(ARG_AUTHOR)

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                fragmentManager?.popBackStack()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = author
        webView = WebView(context)
        webView!!.loadUrl(urlToNews)
        return webView
    }

    companion object {

        private val ARG_URL_TO_NEWS = "urlToNews"
        private val ARG_AUTHOR = "author"

        fun instance(urlToNews: String, author: String): NewsDetailFragment {
            val fragment = NewsDetailFragment()
            val bundle = Bundle()
            bundle.putString(ARG_URL_TO_NEWS, urlToNews)
            bundle.putString(ARG_AUTHOR, author)
            fragment.arguments = bundle
            return fragment
        }

    }

}
