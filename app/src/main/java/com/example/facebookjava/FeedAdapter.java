package com.example.facebookjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Feed> items;

    private final int TYPE_ITEM_HEAD = 0;
    private final int TYPE_ITEM_STORY = 1;
    private final int TYPE_ITEM_POST = 2;
    private final int TYPE_ITEM_POST_EDIT = 3;
    private final int TYPE_ITEM_5_MORE = 4;


    public FeedAdapter(Context context, ArrayList<Feed> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        Feed feed = items.get(position);

        if (feed.isHeader()) {
            return TYPE_ITEM_HEAD;
        } else if (feed.getStories().size() > 0) {
            return TYPE_ITEM_STORY;
        } else {
            if (feed.getPost().isEditedView) {
                return TYPE_ITEM_POST_EDIT;
            } else if (feed.getPost().moreThan5) {
                return TYPE_ITEM_5_MORE;
            } else {
                return TYPE_ITEM_POST;
            }
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM_HEAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_head, parent, false);
            return new HeadViewHolder(view);
        } else if (viewType == TYPE_ITEM_STORY) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_story, parent, false);
            return new StoryViewHolder(context, view);
        } else if (viewType == TYPE_ITEM_POST_EDIT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_edited, parent, false);
            return new PostViewHolderEdited(view);
        } else if (viewType == TYPE_ITEM_5_MORE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_5_more_images, parent, false);
            return new PostMoreThan5VH(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
            return new PostViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Feed feed = items.get(position);

        if (holder instanceof HeadViewHolder) {

        }

        if (holder instanceof StoryViewHolder) {
            refreshAdapter(feed.getStories(), ((StoryViewHolder) holder).recyclerView);
        }

        if (holder instanceof PostViewHolder) {
            ((PostViewHolder) holder).iv_profile.setImageResource(feed.getPost().getProfile());
            ((PostViewHolder) holder).iv_photo.setImageResource(feed.getPost().getPhoto());
            ((PostViewHolder) holder).tv_fullname.setText(feed.getPost().getFullname());
        }
    }

    private void refreshAdapter(ArrayList<Story> stories, RecyclerView recyclerView) {
        StoryAdapter adapter = new StoryAdapter(context, stories);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class HeadViewHolder extends RecyclerView.ViewHolder {
        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {

        Context context;
        RecyclerView recyclerView;

        public StoryViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            recyclerView = itemView.findViewById(R.id.recyclerView);
            LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
            recyclerView.setLayoutManager(manager);
        }
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView iv_profile;
        ImageView iv_photo;
        TextView tv_fullname;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            iv_photo = itemView.findViewById(R.id.iv_photo);
            tv_fullname = itemView.findViewById(R.id.tv_fullname);
        }
    }

    public static class PostViewHolderEdited extends RecyclerView.ViewHolder {
        public PostViewHolderEdited(@NonNull View itemView) {
            super(itemView);

        }
    }

    public static class PostMoreThan5VH extends RecyclerView.ViewHolder {
        public PostMoreThan5VH(@NonNull View itemView) {
            super(itemView);

        }
    }
}
