package com.example.e4_1_android;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PhotoActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_photo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.photoActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button=findViewById(R.id.btnPhoto);

        ActivityResultLauncher<String> requestPermissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(),isGranted->{
                    if(isGranted){
                        System.out.println("oui");
                    }else{
                        System.out.println("non");
                    }
                });

        button.setOnClickListener(v->{
//            if(checkPermission(Manifest.permission.CAMERA)){
//
//            }
        });
    }


    private boolean checkPermission(String permission){
        if(ContextCompat.checkSelfPermission(this,permission)== PackageManager.PERMISSION_GRANTED){
            System.out.println("oui");
            return true;
        }else {
            System.out.println("non");
            return false;
        }
    }
}
