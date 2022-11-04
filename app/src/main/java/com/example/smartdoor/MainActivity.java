package com.example.smartdoor;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ImageButton permission,main,setting;
    Button ON, OFF;
    FirebaseAuth Fauth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    ImageView  spb;
    ProgressDialog ProgressDialog;
    Dialog logout;
    Button yes,no;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        ON = findViewById ( R.id.on );
        OFF = findViewById ( R.id.off );
        setting = findViewById ( R.id.setting );

        logout = new Dialog (MainActivity.this);
        logout.setContentView ( R.layout.logout );
        logout.getWindow ().setBackgroundDrawable ( getDrawable ( R.drawable.bg_logout) );
        logout.getWindow ().setLayout ( ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT );


        yes = logout.findViewById ( R.id.yes);
        no = logout.findViewById ( R.id.no);

        no.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText ( com.example.smartdoor.MainActivity.this , "Logout Canceled" , Toast.LENGTH_SHORT ).show ( );
                logout.dismiss ();
            }
        } );

        yes.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                    Toast.makeText ( com.example.smartdoor.MainActivity.this , "Logouting...." , Toast.LENGTH_SHORT ).show ( );
                    logout.dismiss ( );
                    startActivity ( new Intent ( getApplicationContext (),logp.class  ) );
            }
        } );

        setting.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                logout.show ();
            }
        } );




        ON.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                ProgressDialog = new ProgressDialog (MainActivity.this);
                ProgressDialog.show();
                ProgressDialog.setContentView ( R.layout.loadpg );
                ProgressDialog.getWindow ().setBackgroundDrawableResource (
                        android.R.color.transparent
                );


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Door_STATUS");//LED_STATUS is Firebase database LED_STATUS
                myRef.setValue(1).addOnCompleteListener ( new OnCompleteListener<Void> ( ) {
                    @Override
                    public void onComplete ( @NonNull Task<Void> task ) {
                        ProgressDialog.dismiss ();
                    }
                } );


            }
        } );

        OFF.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                ProgressDialog = new ProgressDialog(MainActivity.this);
                ProgressDialog.show();
                ProgressDialog.setContentView ( R.layout.loadpg );
                ProgressDialog.getWindow ().setBackgroundDrawableResource (
                        android.R.color.transparent
                );

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Door_STATUS");//LED_STATUS is Firebase database LED_STATUS
                myRef.setValue(0).addOnCompleteListener ( new OnCompleteListener<Void> ( ) {
                    @Override
                    public void onComplete ( @NonNull Task<Void> task ) {
                        ProgressDialog.dismiss ();
                    }
                } );

            }
        } );

        ImageButton permission= findViewById ( R.id.permission);
        permission.setOnClickListener ( v -> {
            Intent intent = new Intent ( this , permission.class );
            startActivity ( intent );
            Toast.makeText ( com.example.smartdoor.MainActivity.this , "Check Your Door Permission" , Toast.LENGTH_SHORT ).show ( );
        } );


    }
}