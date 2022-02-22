package com.example.facebookjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public FeedAdapter adapter;
    public ArrayList<Feed> feeds;
    private FeedAdapter.AddNewPostListener addNewPostListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    public void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {

                            Intent data = result.getData();
                            assert data != null;
                            Bundle bundle = data.getExtras();
                            feeds.add(2, new Feed((NewAddedPost) bundle.getParcelable("newPost")));

                            adapter.notifyItemInserted(feeds.size() - 1);

                        }
                    }
                });


        addNewPostListener = new FeedAdapter.AddNewPostListener() {
            @Override
            public void addNewPost(int position) {
                Intent intent = new Intent(getApplicationContext(), CreatePostActivity.class);
                someActivityResultLauncher.launch(intent);
            }
        };

        refreshAdapter(getAllFeeds());

    }

    public final void refreshAdapter(ArrayList<Feed> feeds) {
        adapter = new FeedAdapter(this, feeds, addNewPostListener);
        recyclerView.setAdapter(adapter);
    }

    public final ArrayList<Feed> getAllFeeds() {
        ArrayList<Story> stories = new ArrayList<>();
        stories.add(new Story());

        stories.add(new Story(R.drawable.profile1, "Mark Robinson"));
        stories.add(new Story(R.drawable.profile2, "Matthew TaxCollector"));
        stories.add(new Story(R.drawable.profile3, "Paul Wilbur"));
        stories.add(new Story(R.drawable.profile4, "Mary Magdalena"));
        stories.add(new Story(R.drawable.profile5, "Jorge Bush"));
        stories.add(new Story(R.drawable.james, "Jamshid Sobirov"));
        stories.add(new Story(R.drawable.profile1, "Mark Robinson"));
        stories.add(new Story(R.drawable.profile2, "Matthew TaxCollector"));
        stories.add(new Story(R.drawable.profile3, "Paul Wilbur"));
        stories.add(new Story(R.drawable.profile4, "Mary Magdalena"));
        stories.add(new Story(R.drawable.profile5, "Jorge Bush"));
        stories.add(new Story(R.drawable.james, "Jamshid Sobirov"));


        feeds = new ArrayList<>();


        feeds.add(new Feed()); //Header

        feeds.add(new Feed(stories));//Story


        feeds.add(new Feed(new Post()));

        feeds.add(new Feed(new Post(true)));

        feeds.add(new Feed(new Post(R.drawable.profile1, "Mark Robinson", R.drawable.photo)));
        feeds.add(new Feed(new Post(R.drawable.profile2, "Matthew Tax Collector", R.drawable.photo2)));
        feeds.add(new Feed(new Post(R.drawable.profile3, "Paul Wilbur", R.drawable.photo3)));
        feeds.add(new Feed(new Post(R.drawable.profile4, "Mary Magdalena", R.drawable.photo4)));
        feeds.add(new Feed(new Post(R.drawable.profile5, "Jorge Bush", R.drawable.photo5)));
        feeds.add(new Feed(new Post(R.drawable.james, "Sobirov Jamshid", R.drawable.photo6)));
        feeds.add(new Feed(new Post(R.drawable.profile1, "Mark Robinson", R.drawable.photo)));
        feeds.add(new Feed(new Post(R.drawable.profile2, "Matthew Tax Collector", R.drawable.photo2)));
        feeds.add(new Feed(new Post(R.drawable.profile3, "Paul Wilbur", R.drawable.photo3)));
        feeds.add(new Feed(new Post(R.drawable.profile4, "Mary Magdalena", R.drawable.photo4)));
        feeds.add(new Feed(new Post(R.drawable.profile5, "Jorge Bush", R.drawable.photo5)));
        feeds.add(new Feed(new Post(R.drawable.james, "Sobirov Jamshid", R.drawable.photo6)));
        feeds.add(new Feed(new Post(R.drawable.profile1, "Mark Robinson", R.drawable.photo)));
        feeds.add(new Feed(new Post(R.drawable.profile2, "Matthew Tax Collector", R.drawable.photo2)));
        feeds.add(new Feed(new Post(R.drawable.profile3, "Paul Wilbur", R.drawable.photo3)));
        feeds.add(new Feed(new Post(R.drawable.profile4, "Mary Magdalena", R.drawable.photo4)));
        feeds.add(new Feed(new Post(R.drawable.profile5, "Jorge Bush", R.drawable.photo5)));
        feeds.add(new Feed(new Post(R.drawable.james, "Sobirov Jamshid", R.drawable.photo6)));


        return feeds;
    }


}