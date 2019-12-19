package ro.example.miniaplicatie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InGame extends AppCompatActivity {
    public static ArrayList<Question> questions;
    public static ArrayList<Boolean> answers;
    public int currentQ = 0;
    TextView intrebare;
    RadioButton raspuns1;
    RadioButton raspuns2;
    RadioButton raspuns3;
    RadioButton raspuns4;
    Button btnTrimite;
    RadioGroup radiogroupAnswers;
    TextView vieti;

    RadioButton radioButton;
    public int nr_r_corecte = 0;
    public int nr_r_gresite = 0;
    public static String dificultate;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        nr_r_corecte = nr_r_gresite = 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game);
        intrebare = findViewById(R.id.intrebare);
        raspuns1 = findViewById(R.id.raspuns1);
        raspuns2 = findViewById(R.id.raspuns2);
        raspuns3 = findViewById(R.id.raspuns3);
        raspuns4 = findViewById(R.id.raspuns4);
        btnTrimite = findViewById(R.id.btnTrimite);
        vieti = findViewById(R.id.vieti);
        radiogroupAnswers = (RadioGroup) findViewById(R.id.raspunsuri);

        answers = new ArrayList<>();
        vieti.setText("Numarul de vieti ramase: "+(3-nr_r_gresite));
        intrebare.setText(questions.get(currentQ).question);
        Random rand = new Random();
        int randomPosCorrectAnswer = rand.nextInt(4) + 1;
        if (randomPosCorrectAnswer == 1) {
            raspuns1.setText(questions.get(currentQ).correctAnswer);
            try {
                raspuns2.setText(questions.get(currentQ).wrongAnswers.getString(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                raspuns3.setText(questions.get(currentQ).wrongAnswers.getString(1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                raspuns4.setText(questions.get(currentQ).wrongAnswers.getString(2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (randomPosCorrectAnswer == 2) {
            raspuns2.setText(questions.get(currentQ).correctAnswer);
            try {
                raspuns1.setText(questions.get(currentQ).wrongAnswers.getString(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                raspuns3.setText(questions.get(currentQ).wrongAnswers.getString(1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                raspuns4.setText(questions.get(currentQ).wrongAnswers.getString(2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (randomPosCorrectAnswer == 3) {
            raspuns3.setText(questions.get(currentQ).correctAnswer);
            try {
                raspuns1.setText(questions.get(currentQ).wrongAnswers.getString(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                raspuns2.setText(questions.get(currentQ).wrongAnswers.getString(1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                raspuns4.setText(questions.get(currentQ).wrongAnswers.getString(2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (randomPosCorrectAnswer == 4) {
            raspuns4.setText(questions.get(currentQ).correctAnswer);
            try {
                raspuns1.setText(questions.get(currentQ).wrongAnswers.getString(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                raspuns2.setText(questions.get(currentQ).wrongAnswers.getString(1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                raspuns3.setText(questions.get(currentQ).wrongAnswers.getString(2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        btnTrimite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radiogroupAnswers.getCheckedRadioButtonId();

                radioButton = (RadioButton) findViewById(selectedId);

                answers.add(questions.get(currentQ).correctAnswer.equals(radioButton.getText().toString()));
                if (questions.get(currentQ).correctAnswer.equals(radioButton.getText().toString())) {
                    Context context = getApplicationContext();
                    CharSequence text = "Raspuns corect!";
                    int duration = Toast.LENGTH_SHORT;
                    nr_r_corecte++;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Raspuns gresit!!";
                    int duration = Toast.LENGTH_SHORT;
                    nr_r_gresite++;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

//                boolean esteBun = false;
//                for (int i = 0; i < answers.size(); i++) {
//                    if (answers.get(i) == true) {
//                        nr_r_corecte++;
//                        break;
//                    }
//                }
//                if (!esteBun) {
//                    nr_r_gresite++;
//                }
                vieti.setText("Numarul de vieti ramase: "+(3-nr_r_gresite));

                if (nr_r_gresite >= 3) {
                    Intent i = new Intent(InGame.this, GameOver.class);
                    startActivity(i);
                    return;
                }

                if (nr_r_corecte >= 9) {
                    Intent i = new Intent(InGame.this, ResultsWinners.class);
                    startActivity(i);
                    return;
                }

                Log.d("Trivia", "dificultate: " + dificultate);
                Log.d("Trivia", "avem corecte: " + nr_r_corecte);
                if (nr_r_corecte >= 3 && nr_r_corecte < 6 && dificultate.equals("easy")) {
                    Log.d("Trivia", "Crestem dificultate");
                    dificultate = "medium";
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("THREAD-MERGE", "DA");
                            try {
                                InGame.questions = Match.getQuestions(dificultate);
                                Log.d("MERGE BINE", "DAAAAAA");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    t.start();
                    try {
                        t.join();
                        Log.d("Trivia", "Am luat intrebari mai grele");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(nr_r_corecte>=6 && dificultate.equals("medium")){
                    Log.d("Trivia","Crestem la hard");
                    dificultate="hard";
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("THREAD-MERGE", "DA");
                            try {
                                InGame.questions = Match.getQuestions(dificultate);
                                Log.d("MERGE BINE", "DAAAAAA");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    t.start();
                    try {
                        t.join();
                        Log.d("Trivia", "Am luat intrebari mai grele cu hard");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                currentQ++;
                intrebare.setText(questions.get(currentQ).question);
                Random rand = new Random();
                int randomPosCorrectAnswer = rand.nextInt(4) + 1;
                if (randomPosCorrectAnswer == 1) {
                    raspuns1.setText(questions.get(currentQ).correctAnswer);
                    try {
                        raspuns2.setText(questions.get(currentQ).wrongAnswers.getString(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        raspuns3.setText(questions.get(currentQ).wrongAnswers.getString(1));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        raspuns4.setText(questions.get(currentQ).wrongAnswers.getString(2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (randomPosCorrectAnswer == 2) {
                    raspuns2.setText(questions.get(currentQ).correctAnswer);
                    try {
                        raspuns1.setText(questions.get(currentQ).wrongAnswers.getString(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        raspuns3.setText(questions.get(currentQ).wrongAnswers.getString(1));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        raspuns4.setText(questions.get(currentQ).wrongAnswers.getString(2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (randomPosCorrectAnswer == 3) {
                    raspuns3.setText(questions.get(currentQ).correctAnswer);
                    try {
                        raspuns1.setText(questions.get(currentQ).wrongAnswers.getString(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        raspuns2.setText(questions.get(currentQ).wrongAnswers.getString(1));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        raspuns4.setText(questions.get(currentQ).wrongAnswers.getString(2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (randomPosCorrectAnswer == 4) {
                    raspuns4.setText(questions.get(currentQ).correctAnswer);
                    try {
                        raspuns1.setText(questions.get(currentQ).wrongAnswers.getString(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        raspuns2.setText(questions.get(currentQ).wrongAnswers.getString(1));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        raspuns3.setText(questions.get(currentQ).wrongAnswers.getString(2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
