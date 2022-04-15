package com.example.annuairedecontact;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.annuairedecontact.databinding.ActivityMain2Binding;
import com.example.annuairedecontact.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;
    RoomDB database;
    Contact contact;
    EditText fname;
    EditText lname;
    EditText job;
    EditText email;
    EditText phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        contact = (Contact) getIntent().getSerializableExtra("Contact");

        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbarV2);
        fname = findViewById(R.id.editTextTextPersonName);
        lname = findViewById(R.id.editTextTextPersonName2);
        job = findViewById(R.id.editTextTextPersonName3);
        email = findViewById(R.id.editTextTextPersonName5);
        phone = findViewById(R.id.editTextTextPersonName4);
        fname.setText(contact.getFirst_name());
        lname.setText(contact.getLast_name());
        job.setText(contact.getJob());
        email.setText(contact.getEmail());
        phone.setText(contact.getPhone());

        binding.fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInent=new Intent(view.getContext(),MainActivity.class);
                startActivity(myInent);
            }
        });
        database=RoomDB.getInstance(this);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_menu, menu);
        MenuItem save = menu.findItem(R.id.save);

        save.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                    Contact contact1 = new Contact(contact.getId(),fname.getText().toString(),lname.getText().toString(),job.getText().toString(),email.getText().toString(),phone.getText().toString());
                    database.contactDao().update(contact1);
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