package com.example.facebookjava;

import android.content.Context;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final ArrayList<Feed> items;
    AddNewPostListener addNewPostListener;

    HashMap<String, Parcelable> scrollStates = new HashMap<>();


    private final int TYPE_ITEM_HEAD = 0;
    private final int TYPE_ITEM_STORY = 1;
    private final int TYPE_ITEM_POST = 2;
    private final int TYPE_ITEM_POST_EDIT = 3;
    private final int TYPE_ITEM_5_MORE = 4;
    private final int TYPE_NEW_ADDED_POST = 5;


    public FeedAdapter(Context context, ArrayList<Feed> items, AddNewPostListener addNewPostListener) {
        this.context = context;
        this.items = items;
        this.addNewPostListener = addNewPostListener;
    }


//    private String getSectionID(int position) {
//        return items.get(position).getId();
//    }

//    @Override
//    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
//        super.onViewRecycled(holder);
//        if (holder instanceof StoryViewHolder) {
//            String key = getSectionID(holder.getAdapterPosition());
//            scrollStates.put(key, Objects.requireNonNull(((StoryViewHolder) holder).recyclerView.getLayoutManager()).onSaveInstanceState());
//        }
//    }

    @Override
    public int getItemViewType(int position) {
        Feed feed = items.get(position);

        if (feed.getNewAddedPost() != null) {
            return TYPE_NEW_ADDED_POST;
        }

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

        if (viewType == TYPE_NEW_ADDED_POST) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_new_post, parent, false);
            return new NewPostViewHolder(view);
        }

        if (viewType == TYPE_ITEM_HEAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_head, parent, false);
            return new HeadViewHolder(view);
        } else if (viewType == TYPE_ITEM_STORY) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_story, parent, false);
            return new StoryViewHolder(view);
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

        if (holder instanceof NewPostViewHolder) {

            if (items.get(position).getNewAddedPost().getContentImage().equals("")) {
                ((ImageView) holder.itemView.findViewById(R.id.iv_content_photo)).setVisibility(View.GONE);
            } else {
                ((ImageView) holder.itemView.findViewById(R.id.iv_content_photo)).setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(items.get(position).getNewAddedPost().getContentImage())
                        .into((ImageView) holder.itemView.findViewById(R.id.iv_content_photo));
            }

            if(items.get(position).getNewAddedPost().getContentTitle().equals("")){
                holder.itemView.findViewById(R.id.content_container).setVisibility(View.GONE);
            } else {
                holder.itemView.findViewById(R.id.content_container).setVisibility(View.VISIBLE);
            }

            ((TextView) holder.itemView.findViewById(R.id.tv_content_title)).setText(items.get(position).getNewAddedPost().getContentTitle());
            ((TextView) holder.itemView.findViewById(R.id.tv_content_source)).setText(items.get(position).getNewAddedPost().getContentSource());
            ((TextView) holder.itemView.findViewById(R.id.tv_content)).setText(items.get(position).getNewAddedPost().getContent());

        }


        if (holder instanceof HeadViewHolder) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addNewPostListener.addNewPost(holder.getLayoutPosition());
                }
            });

        }


//        if (holder instanceof StoryViewHolder) {
//            String key = getSectionID(holder.getLayoutPosition());
//            Parcelable state = scrollStates.get(key);
//
//            if (state != null) {
//                Objects.requireNonNull(((StoryViewHolder) holder).recyclerView.getLayoutManager()).onRestoreInstanceState(state);
//            } else {
////                Objects.requireNonNull(((StoryViewHolder) holder).recyclerView.getLayoutManager()).scrollToPosition(0);
//                refreshAdapter(feed.getStories(), ((StoryViewHolder) holder).recyclerView);
//            }
//
//
//        }

        if (holder instanceof StoryViewHolder) {
            if (((StoryViewHolder) holder).adapter == null) {

                Log.d("@@@ABC", "Refreshing....");
                ((StoryViewHolder) holder).adapter = new StoryAdapter(context, feed.getStories());
                ((StoryViewHolder) holder).recyclerView.setAdapter(((StoryViewHolder) holder).adapter);
            }
        }

        if (holder instanceof PostViewHolder) {
//            ((PostViewHolder) holder).iv_profile.setBackground(App.instance.getDrawable(R.drawable.photo3));
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
        StoryAdapter adapter;
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            LinearLayoutManager manager = new LinearLayoutManager(App.instance, RecyclerView.HORIZONTAL, false);
//            manager.setInitialPrefetchItemCount(4);
//            recyclerView.setRecycledViewPool(viewPool);
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setNestedScrollingEnabled(false);
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

    public static class NewPostViewHolder extends RecyclerView.ViewHolder {
        public NewPostViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class PostMoreThan5VH extends RecyclerView.ViewHolder {
        public PostMoreThan5VH(@NonNull View itemView) {
            super(itemView);

        }
    }

    public interface AddNewPostListener {
        void addNewPost(int position);
    }


}
