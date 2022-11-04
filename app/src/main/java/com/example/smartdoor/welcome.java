package com.example.smartdoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class welcome extends AppCompatActivity {
    FirebaseAuth fAuth;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_welcome );

        Button stbt = findViewById (R.id.stbt );
        stbt.setOnClickListener ( v -> {
            startActivity (  new Intent (this,logp.class) );
        } );

        fAuth = FirebaseAuth.getInstance ( );

        if (fAuth.getCurrentUser ( ) != null) {
            startActivity ( new Intent ( getApplicationContext ( ) , MainActivity.class ) );
            finish ( );
        }

    }
}