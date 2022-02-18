package com.example.facebookjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static int IS_ADD_STORY_VIEW = 0;
    public static int IS_COMMON_ITEM= 1;

    private Context context;
    private ArrayList<Story> stories;

    public StoryAdapter(Context context, ArrayList<Story> stories) {
        this.context = context;
        this.stories = stories;
    }

    @Override
    public int getItemViewType(int position) {
        if(stories.get(position).isAddStroyView){
            return IS_ADD_STORY_VIEW;
        } else {
            return IS_COMMON_ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == IS_ADD_STORY_VIEW){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story_add_view, parent, false);
            return new AddItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story_view, parent, false);
            return new StoryViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Story story = stories.get(position);

        if (holder instanceof StoryViewHolder) {
            ((StoryViewHolder) holder).iv_background.setImageResource(story.getProfile());
            ((StoryViewHolder) holder).iv_profile.setImageResource(story.getProfile());
            ((StoryViewHolder) holder).tv_fullname.setText(story.getFullname());
        }
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView iv_background;
        ShapeableImageView iv_profile;
        TextView tv_fullname;


        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_background = itemView.findViewById(R.id.iv_background);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            tv_fullname = itemView.findViewById(R.id.tv_fullname);
        }
    }

    public class AddItemViewHolder extends RecyclerView.ViewHolder{

        public AddItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
