package com.conestoga.mrcafe;

import android.os.Bundle;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductActivity extends AppCompatActivity {

    int[] imageArray = {
            R.drawable.slide1,
            R.drawable.slide2,
            R.drawable.slide3,
            R.drawable.slide4,
            R.drawable.slide1,
            R.drawable.slide2,
            R.drawable.slide3,
            R.drawable.slide4
    };

    String[] nameArray = {
            "CAFFÈ MOCHA",
            "Macchiato",
            "Latte Macchiato",
            "Latte",
            "CAFFÈ MOCHA",
            "Macchiato",
            "Latte Macchiato",
            "Latte"
    };

    String[] priceArray = {
            "2.99",
            "3.99",
            "4.49",
            "7.99",
            "2.99",
            "3.99",
            "4.49",
            "7.99"
    };

    String[] descriptionArray = {
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
    };

    Toolbar toolbar;
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    ProductAdapter mAdapter;
    ArrayList<DataModel> data;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Menu");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        data = new ArrayList<DataModel>();
        for (int i = 0; i < nameArray.length; i++) {
            data.add(new DataModel(imageArray[i], nameArray[i], priceArray[i], descriptionArray[i]));
        }

        mAdapter = new ProductAdapter(data, this);
        recyclerView.setAdapter(mAdapter);
    }
}