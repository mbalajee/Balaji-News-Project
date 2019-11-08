package `in`.learn.balaji_news_project.news.view

import `in`.learn.balaji_news_project.R
import `in`.learn.balaji_news_project.ViewModelFactory
import `in`.learn.balaji_news_project.news.data.Article
import `in`.learn.balaji_news_project.news.viewmodel.NewsViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders

class NewsActivity : AppCompatActivity(), NewsListFragment.NewsListItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "News"
        addListFragment()
    }

    private fun addListFragment() {
        val fragment: NewsListFragment? = supportFragmentManager.findFragmentById(R.id.contentFrame) as? NewsListFragment
        if (fragment == null) {
            addFragment(supportFragmentManager, NewsListFragment(), R.id.contentFrame)
        }
    }

    private fun addDetailFragment(urlToNews: String, author: String) {
        val fragment: NewsDetailFragment? = supportFragmentManager.findFragmentById(R.id.contentFrame) as? NewsDetailFragment
        if (fragment == null) {
            addFragment(supportFragmentManager, NewsDetailFragment.instance(urlToNews, author), R.id.contentFrame)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun addFragment(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commit()
    }

    override fun onClickNewsItem(item: Article) {
        addDetailFragment(item.url, item.author)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            supportFragmentManager.popBackStack()
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            title = "News"
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun obtainViewModel(activity: FragmentActivity): NewsViewModel {
            val factory = ViewModelFactory.instance(activity.application)
            return ViewModelProviders.of(activity, factory).get(NewsViewModel::class.java)
        }
    }
}
