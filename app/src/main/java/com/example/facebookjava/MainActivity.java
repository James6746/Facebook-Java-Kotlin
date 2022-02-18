package com.example.facebookjava;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        refreshAdapter(getAllFeeds());

    }

    public final void refreshAdapter(ArrayList<Feed> feeds) {
        FeedAdapter adapter = new FeedAdapter(this, feeds);
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


        ArrayList<Feed> feeds = new ArrayList<>();

        feeds.add(new Feed());

        feeds.add(new Feed(stories));

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