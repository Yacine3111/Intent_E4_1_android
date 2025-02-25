package com.example.e4_1_android;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ImageView imageView=findViewById(R.id.image_photo);

        ActivityResultLauncher<Intent> myActivityResultLauncher=
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),activityResult->{
                            Intent resultIntent= activityResult.getData();
                            if(activityResult.getResultCode()==RESULT_OK){
                                assert resultIntent != null;
                                Bitmap thumbnail=resultIntent.getParcelableExtra("data");
                                imageView.setImageBitmap(thumbnail);
                            }
                        });

        button.setOnClickListener(v->{
            if(checkPermission(android.Manifest.permission.CAMERA)){
                try{
                    myActivityResultLauncher.launch(intent);
                }catch (ActivityNotFoundException e){
                    System.out.println("non");
                }
            }
        });
    }


    private boolean checkPermission(String permission){
        if(ContextCompat.checkSelfPermission(this,permission)== PackageManager.PERMISSION_GRANTED){
            return true;
        }else if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            return false;
        } else {
            return false;
        }
    }
}
