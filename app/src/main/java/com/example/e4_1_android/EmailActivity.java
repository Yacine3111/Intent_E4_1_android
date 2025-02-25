package com.example.e4_1_android;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EmailActivity extends AppCompatActivity {
    Intent emailIntent;
    Button button;
    EditText editTextEmail,editTextTitle,editTextMSG;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_email);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.email_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        emailIntent=new Intent(Intent.ACTION_SENDTO);
        button=findViewById(R.id.button_email);

        editTextEmail=findViewById(R.id.editTextEmailAddress);
        editTextTitle=findViewById(R.id.editTextTitre);
        editTextMSG=findViewById(R.id.editTextBody);

        button.setOnClickListener(v->{
            String subjectSrt=editTextTitle.getText().toString();
            String bodySrt=editTextMSG.getText().toString();

            uri=Uri.parse("mailto:"+editTextEmail.getText().toString());

            emailIntent.setData(uri);

            //marche pas
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,subjectSrt);
            emailIntent.putExtra(Intent.EXTRA_TEXT,bodySrt);

            try {
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e) {
                MainActivity.errorMessage(this);
            }
        });

    }
}
