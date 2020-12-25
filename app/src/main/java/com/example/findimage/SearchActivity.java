package com.example.findimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class SearchActivity extends AppCompatActivity {

    public void searchImages(View view)
    {
        String q = ((EditText) this.findViewById(R.id.text)).getText().toString();
        String lang = ((Spinner) this.findViewById(R.id.languages)).getSelectedItem().toString();
        String image_type = ((Spinner) this.findViewById(R.id.types)).getSelectedItem().toString();
        String category = ((Spinner) this.findViewById(R.id.categories)).getSelectedItem().toString();
        String order = ((Spinner) this.findViewById(R.id.order)).getSelectedItem().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("q", q);
        intent.putExtra("lang", lang);
        intent.putExtra("image_type", image_type);
        intent.putExtra("category", category);
        intent.putExtra("order", order);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}