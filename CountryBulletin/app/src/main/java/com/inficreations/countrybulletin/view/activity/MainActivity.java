package com.inficreations.countrybulletin.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.inficreations.countrybulletin.AppConstants;
import com.inficreations.countrybulletin.R;
import com.inficreations.countrybulletin.model.NewsModel;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static boolean isComingFromSearch;
    public static String searchKey;

    public static String categoryName;
    public static String countryCode;

    ArrayList<NewsModel> newsModelArrayList;
    ProgressBar progressBar;
    //Swipe left and right View
    private SwipePlaceHolderView swipePlaceHolderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        newsModelArrayList = new ArrayList<>();

        progressBar = findViewById(R.id.spin_kit);
        swipePlaceHolderView = findViewById(R.id.swipeView);

        notifyData();
        if (isComingFromSearch) {
            getNewsFromSearchAPI();
        } else
            getNewsFromCountryAPI();
    }

    private void showProgressBar() {
        Sprite wanderingCubes = new WanderingCubes();
        progressBar.setIndeterminateDrawable(wanderingCubes);
        progressBar.setVisibility(View.VISIBLE);

        swipePlaceHolderView.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        swipePlaceHolderView.setVisibility(View.VISIBLE);
    }

    private void getNewsFromSearchAPI() {
        showProgressBar();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConstants.getSearchURL(searchKey),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("status").equals("ok")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("articles");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    NewsModel newsModel = new NewsModel();
                                    newsModel.setNewsTitle(object.optString("title"));
                                    newsModel.setNewsDescription(object.optString("description"));
                                    newsModel.setNewsURL(object.optString("url"));
                                    newsModel.setNewsImageURL(object.optString("urlToImage"));
                                    newsModel.setNewsContent(object.optString("content"));
                                    newsModelArrayList.add(newsModel);
                                }
                                System.out.println("newsModelArrayList " + newsModelArrayList.size());
                                notifyData();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        hideProgressBar();
                        System.out.println("Response " + response);
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideProgressBar();
                        error.printStackTrace();
                        Toast.makeText(MainActivity.this,
                                "Error " + error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void getNewsFromCountryAPI() {
        showProgressBar();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConstants.getURL(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("status").equals("ok")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("articles");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    NewsModel newsModel = new NewsModel();
                                    newsModel.setNewsTitle(object.optString("title"));
                                    newsModel.setNewsDescription(object.optString("description"));
                                    newsModel.setNewsURL(object.optString("url"));
                                    newsModel.setNewsImageURL(object.optString("urlToImage"));
                                    newsModel.setNewsContent(object.optString("content"));
                                    newsModelArrayList.add(newsModel);
                                }
                                System.out.println("newsModelArrayList " + newsModelArrayList.size());
                                notifyData();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        hideProgressBar();
                        System.out.println("Response " + response);
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideProgressBar();
                        error.printStackTrace();
                        Toast.makeText(MainActivity.this,
                                "Error " + error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    private void notifyData() {
        swipePlaceHolderView.removeAllViews();
        for (NewsModel model : newsModelArrayList) {
            swipePlaceHolderView.addView(new SwipeClass(this, model));
        }
    }

    //Tinder card style class and listener
    @Layout(R.layout.card_list_item)
    public class SwipeClass {
        Context context;
        NewsModel model;
        @com.mindorks.placeholderview.annotations.View(R.id.news_image)
        private ImageView newsImage;
        @com.mindorks.placeholderview.annotations.View(R.id.news_title)
        private TextView newsTitle;
        @com.mindorks.placeholderview.annotations.View(R.id.news_description)
        private TextView newsDescription;
        @com.mindorks.placeholderview.annotations.View(R.id.news_url)
        private TextView newsURL;
        @com.mindorks.placeholderview.annotations.View(R.id.news_content)
        private TextView newsContent;

        SwipeClass(Context context, NewsModel model) {
            this.context = context;
            this.model = model;

            System.out.println("SwipeClass class " + model.getNewsImageURL());
        }

        @Resolve
        private void onResolved() {
            Glide.with(context).load(model.getNewsImageURL())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.app_icon).into(newsImage);
            newsTitle.setText(model.getNewsTitle());
            newsDescription.setText(model.getNewsDescription());
            newsContent.setText(model.getNewsContent());
            newsURL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(model.getNewsURL())));
                }
            });
        }
    }

}
