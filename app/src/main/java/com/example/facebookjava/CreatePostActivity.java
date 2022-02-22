package com.example.facebookjava;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CreatePostActivity extends AppCompatActivity {
    ImageView iv_close;
    MaterialButton btn_post;
    EditText et_text;

//    private final String AGENT = "Mozilla";
//    private final String REFERRER = "http://www.google.com";
//    private final int TIMEOUT = 10000;
//    private final String DOC_SELECT_QUERY = "meta[property^=og:]";
//    private final String OPEN_GRAPH_KEY = "content";
//    private final String PROPERTY = "property";
//    private final String OG_IMAGE = "og:image";
//    private final String OG_DESCRIPTION = "og:description";
//    private final String OG_URL = "og:url";
//    private final String OG_TITLE = "og:title";
//    private final String OG_SITE_NAME = "og:site_name";
//    private final String OG_TYPE = "og:type";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        initViews();
    }


    private void initViews() {
        iv_close = findViewById(R.id.iv_close);
        btn_post = findViewById(R.id.btn_post);
        et_text = findViewById(R.id.et_text);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                Feed feed = new Feed(new NewAddedPost("This is the content!", "asdsdf", "sdsdf", 125));
                bundle.putParcelable("newPost", feed.getNewAddedPost());

                Intent resultIntent = new Intent();
                resultIntent.putExtras(bundle);

                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });



//        et_text.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void afterTextChanged(Editable editable) {
//                doMyWork(editable.toString());
//
//
//            }
//        });
    }

}