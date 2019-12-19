package ro.example.miniaplicatie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class Register extends AppCompatActivity {
    Button signUp;
    int avatarId;
    int avatarViewId;
    ImageView avatar1;
    ImageView avatar2;
    ImageView avatar3;
    ImageView avatar4;
    ImageView avatar5;
    ImageView avatar6;
    LinearLayout firstLinear;
    EditText email, name, password;
    String pathToFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);
        signUp=(Button) findViewById(R.id.buttonSignUp);
        email=(EditText) findViewById(R.id.email);
        name=(EditText) findViewById(R.id.nume);
        password=(EditText) findViewById(R.id.password);
        avatar1=findViewById(R.id.avatar1);
        avatar2=findViewById(R.id.avatar2);
        avatar3=findViewById(R.id.avatar3);
        avatar4=findViewById(R.id.avatar4);
        avatar5=findViewById(R.id.avatar5);
        avatar6=findViewById(R.id.avatar6);
        firstLinear = findViewById(R.id.firstLinear);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (avatar!=null
                        && password.getText().toString().length() != 0
                        && password.getText() != null
                        && name.getText().toString().length() != 0
                        && name.getText() != null
                        && email.getText().toString().matches(
                        "^[a-zA-Z0-9._%+-]+@+[a-zA-Z0-9.-]+.+[a-zA-Z]{2,6}$"))
                {
                    Intent intent = new Intent(Register.this, Match.class);

                    intent.putExtra("avatarId", avatarViewId);
                    Log.d("Avatar", "Avatarul ales este " + avatarViewId);
                    Log.d("AvatarTest",""+avatar);
                    byte[] b;
                    if(avatarViewId % 10 == 6){
                        Bitmap bitmap = ((BitmapDrawable) avatar.getDrawable()).getBitmap();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                         b = baos.toByteArray();
                    } else {
                        Bitmap bitmap = ((BitmapDrawable) avatar.getDrawable()).getBitmap();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                         b = baos.toByteArray();
                    }
                    intent.putExtra("avatarDraw", b);
                    startActivity(intent);

                } else { checkInput();}
            }
        });




    }

    ImageView avatar;
    public void selectAvatar(View v){
        dimView(v);
        avatar = ((ImageView)v);
        Log.d("Imagine", "imagine avatar"+avatar);

    }
    public void selectAvatars(){
        avatar1.setAlpha(1f);
        avatar2.setAlpha(1f);
        avatar3.setAlpha(1f);
        avatar4.setAlpha(1f);
        avatar5.setAlpha(1f);
       // avatar6.setAlpha(1f);
    }
    private void dimView(View v) {

        avatarViewId = v.getId();
        Log.d("Avatar","avatar ales"+avatarViewId);
        if(avatarViewId % 10 == 6){
            Log.d("avatar", "da, l-am ales");
            if (Build.VERSION.SDK_INT >= 23) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE});
                Log.d("avatar", "da, am acces");
            }
            dispatchPictureTakerAction();
        }


        selectAvatars();
        v.setAlpha(0.5f);

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (avatar != null) {
            avatar.setAlpha(1f);
            avatar.invalidate();
        }
    }
    public void checkInput(){
        if (avatar==null) {
            Toast.makeText(getApplicationContext(), "Nu ai selectat niciun avatar!", Toast.LENGTH_SHORT).show();
        }
        if (password.getText().toString().length() == 0
                || password.getText() == null) {
            password.setError("Nu ai introdus parola");
        }
        if (name.getText().toString().length() == 0
                || name.getText() == null) {
            name.setError("Nu ai introdus numele!");
        }
        if (email.getText().toString().length() == 0
                || email.getText() == null) {
            email.setError("Nu ai introdus emailul!");
        } else if (!email.getText().toString().matches("^[a-zA-Z0-9._%+-]+@+[a-zA-Z0-9.-]+.+[a-zA-Z]{2,6}$")) {
            email.setError("Email incorect");
        }
    }
   private void requestPermissions(String[] strings) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
                avatar6.setImageBitmap(bitmap);
            }
        }
    }
    private void dispatchPictureTakerAction() {
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePic.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            photoFile = createPhotoFile();

            if (photoFile != null) {
                pathToFile = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(Register.this, "com.example.provider.cartoonprovider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePic, 1);
            }
        }
        Log.d("avatar", "da, am selectat imaginea");

    }
    private File createPhotoFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        avatar=(ImageView)findViewById(R.id.avatar6);


        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
//        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//////        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//////        File image = null;
//////        try {
//////            image = File.createTempFile(name, ".jpg", storageDir);
//////        } catch (IOException e) {
//////            Log.d("myLog", "Excep: " + e.toString());
//////        }
//////        return image;
    }
}
