package com.example.facebookjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CreatePostActivity extends AppCompatActivity {
    ImageView iv_close;
    ImageButton imbtn_edditText;
    MaterialButton btn_post;
    EditText et_text;
    NewAddedPost newAddedPost;
    FrameLayout content_for_et;
    ImageView iv_content_photo;
    TextView content_source;
    TextView content_title;

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
        imbtn_edditText = findViewById(R.id.imbtn_edditText);
        content_for_et = findViewById(R.id.content_for_et);
        iv_content_photo = findViewById(R.id.iv_content_photo);
        content_source = findViewById(R.id.tv_content_source);
        content_title = findViewById(R.id.tv_content_title);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_post.setBackgroundColor(getResources().getColor(R.color.btn_disabled));

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putParcelable("newPost", newAddedPost);

                Intent resultIntent = new Intent();
                resultIntent.putExtras(bundle);

                setResult(RESULT_OK, resultIntent);

                finish();
            }
        });


        et_text.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (charSequence.toString().equals("") || charSequence.toString() == null) {
//                    btn_post.setBackgroundColor(getResources().getColor(R.color.btn_disabled));
//                    btn_post.setEnabled(false);
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                new MyTask(editable.toString()).execute();
            }
        });

        imbtn_edditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content_for_et.setVisibility(View.GONE);
                et_text.setText("");
            }
        });
    }

    class MyTask extends AsyncTask<Void, Void, String> {

        String input = "";
        String title = "";
        String url = "";
        String contentSource = "";
        String imgUrl = "";

        public MyTask(String input) {
            this.input = input;

            String[] words = input.split(" ");

            for (String word : words) {
                if (word.contains("https://") || word.contains("http://")) {
                    url = word;
                }
            }
        }

        @SuppressLint("NewApi")
        @Override
        protected String doInBackground(Void... params) {
            Elements images;
            Document doc;

            try {
                if (url.contains("http://")) {
                    url = "https://" + url.substring(7);
                }
                doc = Jsoup.connect(url).get();

                try {
                    title = doc.title();
                } catch (Exception e) {
                    title = "";
                }

                if (!url.equals("")) {
                    String[] words = url.split("/");

                    contentSource = words[2];
                }

                images = doc.select("meta[property^=og:]");
                images.forEach(item -> {
                    if (item.attr("property").equals("og:image")) {
                        if (item.attr("content").contains("http://")) {
                            imgUrl = "https://" + item.attr("content").substring(7);
                        } else if (!item.attr("content").contains("https://")) {
                            imgUrl = url + item.attr("content");
                        } else {
                            imgUrl = item.attr("content");
                        }
                    }
                });
//                Log.d("@@@ABC", doc.head().html());
//                Log.d("@@@ABC1================",images.toString());
//                Log.d("@@@ABC2================",imgUrl[0]);


                newAddedPost = new NewAddedPost(et_text.getText().toString(), contentSource, title, imgUrl);

            } catch (Exception e) {

                Log.d("@@@@EXCEPTION", "Exception ishlavotti!");
                newAddedPost = new NewAddedPost(et_text.getText().toString(), contentSource, title, imgUrl);
                e.printStackTrace();
            }
            return title;
        }


        @Override
        protected void onPostExecute(String result) {

            if (imgUrl.equals("")) {
                iv_content_photo.setVisibility(View.GONE);
            } else {
                iv_content_photo.setVisibility(View.VISIBLE);
                Glide.with(getApplicationContext())
                        .load(imgUrl)
                        .into(iv_content_photo);
            }

            if (!title.equals("")) {
                content_source.setText(contentSource);
                content_title.setText(title);

                content_for_et.setVisibility(View.VISIBLE);
            }


            if (!et_text.getText().toString().equals("")) {
                btn_post.setBackgroundColor(Color.parseColor("#545BED"));
                btn_post.setEnabled(true);
            } else {
                btn_post.setBackgroundColor(getResources().getColor(R.color.btn_disabled));
                btn_post.setEnabled(false);
            }

        }
    }

}