package com.example.facebook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        refreshAdapter(getAllFeeds())
    }

    fun refreshAdapter(feeds: ArrayList<Feed>) {
        val adapter = FeedAdapter(this, feeds)
        recyclerView.adapter = adapter
    }

    fun getAllFeeds(): ArrayList<Feed> {

        val stories: ArrayList<Story> = ArrayList()

        stories.add(Story())

        stories.add(Story(R.drawable.profile1, "Mark Robinson"))
        stories.add(Story(R.drawable.profile2, "Matthew TaxCollector"))
        stories.add(Story(R.drawable.profile3, "Paul Wilbur"))
        stories.add(Story(R.drawable.profile4, "Mary Magdalena"))
        stories.add(Story(R.drawable.profile5, "Jorge Bush"))
        stories.add(Story(R.drawable.james, "Jamshid Sobirov"))
        stories.add(Story(R.drawable.profile1, "Mark Robinson"))
        stories.add(Story(R.drawable.profile2, "Matthew TaxCollector"))
        stories.add(Story(R.drawable.profile3, "Paul Wilbur"))
        stories.add(Story(R.drawable.profile4, "Mary Magdalena"))
        stories.add(Story(R.drawable.profile5, "Jorge Bush"))
        stories.add(Story(R.drawable.james, "Jamshid Sobirov"))

        val feeds: ArrayList<Feed> = ArrayList()

        feeds.add(Feed())

        feeds.add(Feed(stories))

        feeds.add(Feed(Post()))

        feeds.add(Feed(Post(true)))

        feeds.add(Feed(Post(R.drawable.profile1, "Mark Robinson", R.drawable.photo)))
        feeds.add(Feed(Post(R.drawable.profile2, "Matthew Tax Collector", R.drawable.photo2)))
        feeds.add(Feed(Post(R.drawable.profile3, "Paul Wilbur", R.drawable.photo3)))
        feeds.add(Feed(Post(R.drawable.profile4, "Mary Magdalena", R.drawable.photo4)))
        feeds.add(Feed(Post(R.drawable.profile5, "Jorge Bush", R.drawable.photo5)))
        feeds.add(Feed(Post(R.drawable.james, "Sobirov Jamshid", R.drawable.photo6)))
        feeds.add(Feed(Post(R.drawable.profile1, "Mark Robinson", R.drawable.photo)))
        feeds.add(Feed(Post(R.drawable.profile2, "Matthew Tax Collector", R.drawable.photo2)))
        feeds.add(Feed(Post(R.drawable.profile3, "Paul Wilbur", R.drawable.photo3)))
        feeds.add(Feed(Post(R.drawable.profile4, "Mary Magdalena", R.drawable.photo4)))
        feeds.add(Feed(Post(R.drawable.profile5, "Jorge Bush", R.drawable.photo5)))
        feeds.add(Feed(Post(R.drawable.james, "Sobirov Jamshid", R.drawable.photo6)))
        feeds.add(Feed(Post(R.drawable.profile1, "Mark Robinson", R.drawable.photo)))
        feeds.add(Feed(Post(R.drawable.profile2, "Matthew Tax Collector", R.drawable.photo2)))
        feeds.add(Feed(Post(R.drawable.profile3, "Paul Wilbur", R.drawable.photo3)))
        feeds.add(Feed(Post(R.drawable.profile4, "Mary Magdalena", R.drawable.photo4)))
        feeds.add(Feed(Post(R.drawable.profile5, "Jorge Bush", R.drawable.photo5)))
        feeds.add(Feed(Post(R.drawable.james, "Sobirov Jamshid", R.drawable.photo6)))

        return feeds;
    }
}