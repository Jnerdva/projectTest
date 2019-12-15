package com.example.testapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.model.NetworkData;
import com.example.testapp.model.Row;
import com.example.testapp.viewModel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  MainActivityViewModel mainActivityViewModel;
  DataAdapter dataAdapter;
  NetworkData networkData;
  RecyclerView recyclerView;
  LinearLayoutManager linearLayoutManager;
    Toolbar toolbar;
  private ProgressBar progressBar;
  private ArrayList<Row> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = findViewById(R.id.toolBar);
        networkData=new NetworkData();
        recyclerView = findViewById(R.id.rv);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        progressBar = findViewById(R.id.progressbar);
        SettingUpAdapter();
        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);
        FetchData();
        floatingActionButton.setOnClickListener(v -> FetchData());
    }

    private void FetchData() {
        if(dataList!=null) {
            mainActivityViewModel.getResponseLiveData().observe(this, responseLiveData -> {
                if (responseLiveData != null) {

                    progressBar.setVisibility(View.GONE);
                    Log.d("size", "Received data list size :" + responseLiveData.getRows().size());
                    toolbar.setTitle(responseLiveData.getTitle());
                    setSupportActionBar(toolbar);
                    dataList.addAll(responseLiveData.getRows());
                    dataAdapter.notifyDataSetChanged();

                }
                Log.d("TAG", "NUll NULL");
            });
        }
        dataAdapter.notifyDataSetChanged();
    }



    private void SettingUpAdapter() {
        dataAdapter = new DataAdapter(dataList, this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dataAdapter);
    }
}
