package ro.example.miniaplicatie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Match extends AppCompatActivity {
    Button start;
    ImageView avatarAles1;
    ImageView avatarAles2;
    RadioGroup radioGroupNivel;
    RadioButton easy;
    RadioButton medium;
    RadioButton hard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match);
        start = (Button) findViewById(R.id.btnStart);
        radioGroupNivel = findViewById(R.id.nivel);
        easy = findViewById(R.id.easy);
        medium = findViewById(R.id.medium);
        hard = findViewById(R.id.hard);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroupNivel.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                Log.d("RadioButton", "valoarea lui radioButton" + selectedId);
                if (selectedId != -1){
                    InGame.dificultate = radioButton.getText().toString();
                    Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InGame.questions = Match.getQuestions(InGame.dificultate);
                            Intent intent = new Intent(Match.this, InGame.class);
                            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    ;
                });
                t.start();
            } else {
                    Toast.makeText(getApplicationContext(), "Nu ai selectat niciun nivel!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        avatarAles1 = findViewById(R.id.avatarAles);
        avatarAles2 = findViewById(R.id.avatarAles2);
        Intent intent1 = getIntent();
        if (intent1 != null) {
            if (intent1.hasExtra("avatarDraw")) {
                byte[] b = intent1.getByteArrayExtra("avatarDraw");
                Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
                userBmp = bmp;
                avatarAles1.setImageBitmap(bmp);
                avatarAles2.setImageBitmap(bmp);
            } else if (userBmp != null) {
                avatarAles1.setImageBitmap(userBmp);
                avatarAles2.setImageBitmap(userBmp);
            } else {
                avatarAles1.setImageResource(intent1.getIntExtra("avatarId", R.drawable.game_over));
                avatarAles2.setImageResource(intent1.getIntExtra("avatarId", R.drawable.game_over));
            }
        }
    }
    static Bitmap userBmp;


    public static ArrayList<Question> getQuestions(String difficulty) throws IOException, JSONException {//Definesc o functie care primeste ia si parametru dificultatea si are ca si return value un array de obiecte definite de clasa mea custom
        ArrayList<Question> questions=new ArrayList<>();//Aici voi pune fiecare intrebare
        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();//Aici initiez http clientul okhttp
        String url = "https://opentdb.com/api.php?amount=10&category=20&difficulty="+difficulty+"&type=multiple";
        Log.d("Match", url);

        Request request = new Request.Builder()
                .url(url)
                .build();//Construiesc requestul
        Response response = client.newCall(request).execute();//Il rulez adica accesez acel url descarcand in variabila response raspunsul
        String questionsJSON=response.body().string();//Convertesc raspunsul in string
        JSONObject jsonObject = new JSONObject(questionsJSON);//Convertesc jsonul string in obiect
        JSONArray questionsJSONArray = jsonObject.getJSONArray("results");//Iau campul de raspunsuri si intrebari din acest obiect json
        for (int i=0;i<questionsJSONArray.length();i++) {//Parcurg intrebarile
            JSONObject questionObject=questionsJSONArray.getJSONObject(i);//Iau fiecare intrebare
            Log.d("TEST JSON DECODARE",questionObject.toString());//O afisez

            //Adaug in lista mea de intrebari fiecare intrebare construind-o folosind constructorul custom de intrebari
            questions.add(new Question(questionObject.getString("category"),questionObject.getString("type"),questionObject.getString("difficulty"),questionObject.getString("question"),questionObject.getString("correct_answer"),questionObject.getJSONArray("incorrect_answers")));
        }

        return questions;
    }

}
