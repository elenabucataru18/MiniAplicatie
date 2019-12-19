package ro.example.miniaplicatie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class TitleScreen extends AppCompatActivity {
Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.title_screen);
        play=(Button) findViewById(R.id.btnFirstLayout);
       play.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(TitleScreen.this, Register.class);
               startActivity(intent);
           }
       });
        TextView t2 = (TextView) findViewById(R.id.txtLeaders);
        t2.setMovementMethod(LinkMovementMethod.getInstance());



    }
}
