package com.example.facebookjava

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook.R
import com.example.facebook.Story
import com.google.android.material.imageview.ShapeableImageView

class StoryAdapter(private val context: Context, stories: ArrayList<Story>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val stories: ArrayList<Story>
    override fun getItemViewType(position: Int): Int {
        return if (stories[position].isAddStroyView) {
            IS_ADD_STORY_VIEW
        } else {
            IS_COMMON_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == IS_ADD_STORY_VIEW) {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_story_add_view, parent, false)
            AddItemViewHolder(view)
        } else {
            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_story_view, parent, false)
            StoryViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val story: Story = stories[position]
        if (holder is StoryViewHolder) {
            holder.iv_background.setImageResource(story.profile)
            holder.iv_profile.setImageResource(story.profile)
            holder.tv_fullname.setText(story.fullname)
        }
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    inner class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_background: ShapeableImageView
        var iv_profile: ShapeableImageView
        var tv_fullname: TextView

        init {
            iv_background = itemView.findViewById(R.id.iv_background)
            iv_profile = itemView.findViewById(R.id.iv_profile)
            tv_fullname = itemView.findViewById(R.id.tv_fullname)
        }
    }

    inner class AddItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    companion object {
        var IS_ADD_STORY_VIEW = 0
        var IS_COMMON_ITEM = 1
    }

    init {
        this.stories = stories
    }
}