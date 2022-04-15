package com.example.annuairedecontact;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    // Initialize variable
    private List<Contact> dataList;
    private Activity context;
    private RoomDB database;

    //Create constructor
    public MainAdapter(Activity context, List<Contact> dataList)
    {
       this.context=context;
       this.dataList=dataList;
       notifyDataSetChanged();
    }
    {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Initialize view
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        // Initialize main data
        Contact data=dataList.get(position);

        // Initialize database
        database=RoomDB.getInstance(context);

        // Set text on text view
        holder.fname.setText(data.getFirst_name());
        holder.lname.setText(data.getLast_name());
        holder.job.setText(data.getJob());
        holder.email.setText(data.getEmail());
        holder.tel.setText(data.getPhone());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tel= v.findViewById(R.id.Tel);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+holder.tel.getText()));
                if (ActivityCompat.checkSelfPermission(context,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(context, android.Manifest.permission.CALL_PHONE)) {
                        context.startActivity(callIntent);
                    } else {
                        ActivityCompat.requestPermissions(context,
                                new String[]{android.Manifest.permission.CALL_PHONE},1);
                        context.startActivity(callIntent);
                    }
                }
                context.startActivity(callIntent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact d=dataList.get(holder.getAdapterPosition());
                Intent myInent=new Intent(context,MainActivity3.class);
                myInent.putExtra("Contact", d);
                context.startActivity(myInent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Contact d=dataList.get(holder.getAdapterPosition());
                        database.contactDao().delete(d);
                        dataList.clear();
                        dataList.addAll(database.contactDao().getAll());
                        notifyDataSetChanged();
                        Toast toast=new Toast(context);
                        toast.makeText(context,"the contact was successfully deleted",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("confirmation");
                alertDialog.setMessage("are you sure you want to delete this");
                alertDialog.show();

                return true;
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Initialize variable
        TextView fname;
        TextView lname;
        TextView job;
        TextView tel;
        TextView email;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Assign variable

            fname=itemView.findViewById(R.id.fname);
            lname=itemView.findViewById(R.id.lname);
            job=itemView.findViewById(R.id.job);
            tel=itemView.findViewById(R.id.Tel);
            email=itemView.findViewById(R.id.email);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
    public void searching(String s){
        dataList.clear();
        dataList=database.contactDao().getAllByname(s);
        System.out.println("*********************************************************************");
        System.out.println(s);
        System.out.println(dataList.size());
        System.out.println("*********************************************************************");
        notifyDataSetChanged();

    }
}
