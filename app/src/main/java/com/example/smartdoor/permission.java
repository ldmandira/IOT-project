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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class permission extends AppCompatActivity {
    

    ImageView spb,yper,noper,setting;
    ToggleButton toggle;
    ProgressDialog ProgressDialog;
    Dialog logout;
    Button yes,no;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_permission );

        yes = findViewById ( R.id.yes);
        no = findViewById ( R.id.no);
        yper = findViewById ( R.id.yper );
        noper = findViewById ( R.id.noper );




        yes.setOnClickListener ( new View.OnClickListener ( ) {
                                     @Override
                                     public void onClick ( View v ) {

                                         ProgressDialog = new ProgressDialog ( permission.this );
                                         ProgressDialog.show ( );
                                         ProgressDialog.setContentView ( R.layout.loadpg );
                                         ProgressDialog.getWindow ( ).setBackgroundDrawableResource (
                                                 android.R.color.transparent
                                         );


                                         noper.setVisibility ( View.INVISIBLE );
                                         FirebaseDatabase database = FirebaseDatabase.getInstance ( );
                                         DatabaseReference myRef = database.getReference ( "Permission" );//LED_STATUS is Firebase database LED_STATUS
                                         myRef.setValue ( 1 ).addOnCompleteListener ( new OnCompleteListener<Void> ( ) {
                                             @Override
                                             public void onComplete ( @NonNull Task<Void> task ) {

                                                 yper.setVisibility ( View.VISIBLE );
                                                 ProgressDialog.dismiss ( );
                                             }
                                         } );
                                     }
                                 });


                no.setOnClickListener ( new View.OnClickListener ( ) {
                    @Override
                    public void onClick ( View v ) {
                    ProgressDialog = new ProgressDialog(permission.this);
                    ProgressDialog.show();
                    ProgressDialog.setContentView ( R.layout.loadpg );
                    ProgressDialog.getWindow ().setBackgroundDrawableResource (
                            android.R.color.transparent
                    );


                    yper.setVisibility ( View.INVISIBLE );
                    FirebaseDatabase database = FirebaseDatabase.getInstance ( );
                    DatabaseReference myRef = database.getReference ( "Permission" );//LED_STATUS is Firebase database LED_STATUS
                    myRef.setValue ( 0 ).addOnCompleteListener ( new OnCompleteListener<Void> ( ) {
                        @Override
                        public void onComplete ( @NonNull Task<Void> task ) {
                            noper.setVisibility ( View.VISIBLE );
                            ProgressDialog.dismiss ();
                        }
                    } );
                }
            });


        ImageButton main = findViewById ( R.id.main);
        main.setOnClickListener ( v -> {
            Intent intent = new Intent ( this , MainActivity.class );
            startActivity ( intent );
            Toast.makeText ( com.example.smartdoor.permission.this , "Check Your Door Status" , Toast.LENGTH_SHORT ).show ( );
        } );
    }
}
