package it.arezzo.itis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Presentazione extends AppCompatActivity {

    static boolean inizia = true; // true giocatore 1 false giocatore 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentazione);
        Button b2 = (Button) findViewById(R.id.bttGioca);
        b2.setEnabled(false);
        Button b1 = (Button) findViewById(R.id.BttEstrai);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText gio1 = (EditText) findViewById(R.id.EdtGiocatore1);
                if (gio1.getText().toString().equals("") || gio1.getText().toString().equals(null))
                {
                    gio1.setError(("Nome please...."));
                    return;
                }
                EditText gio2 = (EditText) findViewById(R.id.edtGiocatore2);
                if (gio2.getText().toString().equals("") || gio2.getText().toString().equals(null))
                {
                    gio2.setError(("Nome please...."));
                    return;
                }
                if (gio1.getText().toString().toUpperCase().equals(gio2.getText().toString().toUpperCase()))
                {
                    gio2.setError(("Nomi diversi please...."));
                    return;
                }

                gio1.setEnabled(false);
                gio2.setEnabled(false);
                int max = 2;
                int min = 1;
                int range = max - min + 1;
                int rand = 0;
                TextView ini1 = (TextView) findViewById(R.id.textView4);
                for (int i = 0; i < 50; i++)
                {
                    rand = (int)(Math.random() * range) + min;

                    if (rand == 1) { ini1.setText("Inizia: " + gio1.getText().toString() + " - chr: X"); }

                    else {
                        ini1.setText("Inizia: " + gio2.getText().toString() + " - chr: O");
                    }
                }
                if (rand==1) { inizia = true;} else {inizia = false;}
                b2.setEnabled(true);
            }
        });

    }

    public void lancia(View view)
    {
        EditText gio1 = (EditText) findViewById(R.id.EdtGiocatore1);
        EditText gio2 = (EditText) findViewById(R.id.edtGiocatore2);
        gio1.setEnabled(false);
        gio2.setEnabled(false);
        // definisco l'intenzione di aprire l'Activity "Page1.java"
        Intent openPage1 = new Intent(Presentazione.this,MainActivity.class);
        // passo all'attivazione dell'activity page1.java
        openPage1.putExtra("Gio1", gio1.getText().toString());
        openPage1.putExtra("Gio2", gio2.getText().toString());
        startActivity(openPage1);

    }


}