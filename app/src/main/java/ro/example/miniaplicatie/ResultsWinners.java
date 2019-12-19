package ro.example.miniaplicatie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultsWinners extends AppCompatActivity {
    Button nextMatch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_winners);
        nextMatch=(Button) findViewById(R.id.btnFirstLayout);
        nextMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsWinners.this, Match.class);
                startActivity(intent);
            }
        });
    }
}
