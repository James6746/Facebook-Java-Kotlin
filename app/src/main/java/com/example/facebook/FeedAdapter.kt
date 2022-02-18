package com.example.facebook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookjava.StoryAdapter
import com.google.android.material.imageview.ShapeableImageView

class FeedAdapter(var context: Context, var items: ArrayList<Feed>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM_HEAD = 0
    private val TYPE_ITEM_STORY = 1
    private val TYPE_ITEM_POST = 2
    private val TYPE_ITEM_POST_EDIT = 3
    private val TYPE_ITEM_5_MORE = 4

    override fun getItemViewType(position: Int): Int {
        var feed = items[position]

        if (feed.isHeader)
            return TYPE_ITEM_HEAD
        else if (feed.stories.size > 0)
            return TYPE_ITEM_STORY
        else {
            return return if (feed.post!!.isEditedView) {
                TYPE_ITEM_POST_EDIT
            } else if (feed.post!!.moreThan5) {
                TYPE_ITEM_5_MORE
            } else {
                TYPE_ITEM_POST
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_HEAD) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_feed_head, parent, false)
            return HeadViewHolder(context, view)
        } else if (viewType == TYPE_ITEM_STORY) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_feed_story, parent, false)
            return StoryViewHolder(context, view)
        } else if (viewType == TYPE_ITEM_POST_EDIT) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed_edited, parent, false)
            return PostViewHolderEdited(view)
        } else if (viewType == TYPE_ITEM_5_MORE) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed_5_more_images, parent, false)
            return PostMoreThan5VH(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
            return PostViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = items[position]

        if (holder is HeadViewHolder) {

        }

        if (holder is StoryViewHolder) {
            refreshAdapter(feed.stories, holder.recyclerView)
        }

        if (holder is PostViewHolder) {
            holder.iv_profile.setImageResource(feed.post!!.profile)
            holder.iv_photo.setImageResource(feed.post!!.photo)
            holder.tv_fullname.text = feed.post!!.fullname

        }
    }

    fun refreshAdapter(stories: ArrayList<Story>, recyclerView: RecyclerView) {
        val adapter = StoryAdapter(context, stories)
        recyclerView.adapter = adapter
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class HeadViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view) {

    }

    class StoryViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view) {
        var recyclerView: RecyclerView

        init {
            recyclerView = view.findViewById(R.id.recyclerView)
            val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = manager
        }
    }

    class PostViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile: ShapeableImageView
        var iv_photo: ImageView
        var tv_fullname: TextView

        init {
            iv_profile = view.findViewById(R.id.iv_profile)
            iv_photo = view.findViewById(R.id.iv_photo)
            tv_fullname = view.findViewById(R.id.tv_fullname)
        }
    }

    class PostViewHolderEdited(itemView: View) : RecyclerView.ViewHolder(itemView)

    class PostMoreThan5VH(itemView: View) : RecyclerView.ViewHolder(itemView)
}