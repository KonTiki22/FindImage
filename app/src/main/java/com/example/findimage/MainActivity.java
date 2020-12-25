package com.example.findimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

public class MainActivity extends AppCompatActivity {

    String API_URL = "https://pixabay.com/";
    String q = "";
    String lang;
    String image_type;
    String category;
    String order;
    String key = "16115131-f2ac4e59ef4204b7d06f11215";
    Context context;
    RecyclerView recyclerView;

    public void search(View view)
    {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    interface PixabayAPI {
        @GET("/api")
        Call<Pixabay> search(@Query("key") String key, @Query("q") String q, @Query("lang") String lang, @Query("image_type") String image_type, @Query("order") String order, @Query("category") String category);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Bundle args = getIntent().getExtras();
        if(args != null) {
            q = args.get("q").toString();
            lang = args.get("lang").toString();
            image_type = args.get("image_type").toString();
            category = args.get("category").toString();
            if(category == "all") category = null;
            order = args.get("order").toString();
            startSearch(q);
        }
    }

    public void startSearch(String text) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PixabayAPI api = retrofit.create(PixabayAPI.class);

        Call<Pixabay> call = api.search(key, q, lang, image_type, order, category);

        Callback<Pixabay> callback = new Callback<Pixabay>() {
            @Override
            public void onResponse(Response<Pixabay> response, Retrofit retrofit) {

                Pixabay result = response.body();
                if(result.hits != null) {
                    recyclerView = findViewById(R.id.recList);
                    MyAdapter adapter = new MyAdapter(context, result);
                    recyclerView.addItemDecoration(new DividerItemDecoration(context,
                            DividerItemDecoration.VERTICAL));
                    recyclerView.setAdapter(adapter);
                }
                Log.d("mytag", "hits:" + result.hits.length);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("mytag", "Error: " + t.getLocalizedMessage());
            }
        };
        call.enqueue(callback);

    }

}