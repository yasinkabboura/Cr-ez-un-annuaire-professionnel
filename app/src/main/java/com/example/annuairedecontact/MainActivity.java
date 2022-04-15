package com.example.annuairedecontact;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.annuairedecontact.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    RecyclerView recyclerView;

    List<Contact> dataList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInent=new Intent(view.getContext(),MainActivity2.class);
                startActivity(myInent);
            }
        });
        recyclerView=findViewById(R.id.recyclerV);

        // initialize database
        database=RoomDB.getInstance(this);
//        database.contactDao().insert(new Contact("yasin","Kabboura","ingenieur","yasin@gmail.com","0673451257"));
//        database.contactDao().insert(new Contact("Saad","Moustakim","ingenieur","yasin@gmail.com","0673451257"));
//        database.contactDao().insert(new Contact("Rihab","Ziani","ingenieur","yasin@gmail.com","0673451257"));
//        database.contactDao().insert(new Contact("Ouassime","Maarouf","ingenieur","yasin@gmail.com","0673451257"));
//        database.contactDao().insert(new Contact("Hajar ","Zarguan","ingenieur","yasin@gmail.com","0673451257"));
//        database.contactDao().insert(new Contact("yasin","Kabboura","ingenieur","yasin@gmail.com","0673451257"));
//        database.contactDao().insert(new Contact("yasin","Kabboura","ingenieur","yasin@gmail.com","0673451257"));
//        database.contactDao().insert(new Contact("yasin","Kabboura","ingenieur","yasin@gmail.com","0673451257"));
//        database.contactDao().insert(new Contact("yasin","Kabboura","ingenieur","yasin@gmail.com","0673451257"));
//        database.contactDao().insert(new Contact("yasin","Kabboura","ingenieur","yasin@gmail.com","0673451257"));
//        database.contactDao().insert(new Contact("yasin","Kabboura","ingenieur","yasin@gmail.com","0673451257"));
//        database.contactDao().insert(new Contact("yasin","Kabboura","ingenieur","yasin@gmail.com","0673451257"));
//        database.contactDao().insert(new Contact("yasin","Kabboura","ingenieur","yasin@gmail.com","0673451257"));
//        database.contactDao().insert(new Contact("yasin","Kabboura","ingenieur","yasin@gmail.com","0673451257"));

        dataList=database.contactDao().getAll();

        //Initialize linear layout manager
        linearLayoutManager=new LinearLayoutManager(this);

        // Set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);

        // Initialize adapter
        adapter=new MainAdapter(MainActivity.this,dataList);

        // set adapter

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setQueryHint("enter contact name");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapter.searching(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }


}