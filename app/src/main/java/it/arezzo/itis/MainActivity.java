package it.arezzo.itis;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button[][] sca = new Button[3][3];
    int[][] mat = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    Button[] vet = new Button[9];
    boolean qualeChr = true;  // true = X false 0
    boolean giocatore = true; // true Human false WOPR
    int r = 0;
    int c = 0;
    String gioca1;
    String gioca2;
    int pareggio = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        giocatore = Presentazione.inizia;
        Bundle dati = getIntent().getExtras();
        gioca1 = dati.getString("Gio1");
        gioca2 = dati.getString("Gio2");
        caricaButton();
        Button gioca = (Button) findViewById(R.id.ButtonGioca);
        if (giocatore) { gioca.setText("Tocca a: " + gioca1);
                         gioca.invalidate(); gioca.requestLayout();} else { gioca.setText("Tocca a" + gioca2); }

        for (int i = 0, k = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                sca[i][j] = vet[k];
                String app = "Bottone_" + i + "_" + j;
                sca[i][j].setTag(app);
                sca[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String app = (String) view.getTag();
                        String[] app1 = app.split("_");
                        r = Integer.parseInt(app1[1]);
                        c = Integer.parseInt(app1[2]);
                        if (!((sca[r][c].getText().toString().equals("O")) || (sca[r][c].getText().toString().equals("X")))) {
                            if (qualeChr) {
                                sca[r][c].setText("X");
                                qualeChr = false;
                            } else {
                                sca[r][c].setText("O");
                                qualeChr = true;
                            }
                        }
                        if (giocatore) {
                            mat[r][c] = 1;
                        } else {
                            mat[r][c] = 2;
                        }
                        if (controllo(giocatore)) {
                            if (giocatore) {
                                gioca.setText("Ha vinto : " + gioca1 + " !!!!");
                            } else {
                                gioca.setText("Ha vinto : " + gioca2 + " !!!!");
                            }
                        } else {
                            if (pareggio == 9) {  gioca.setText("Pareggio ! Tris di solito è così");
                            } else {
                                if (giocatore) {
                                    giocatore = false;
                                } else {
                                    giocatore = true;
                                }
                                if (giocatore) { gioca.setText("Tocca a: " + gioca1);
                                    } else { gioca.setText("Tocca a" + gioca2); }
                            }
                        }


                        // gioca.setText("Hai fatto click su button: i: " + app1[1] + " j: " + app1[2]);
                    }
                });
                k++;
            }
    }

    public void principale(View view) {
        Intent princip = new Intent(MainActivity.this, Presentazione.class);
        startActivity(princip);
    }

    public void caricaButton() {
        vet[0] = (Button) findViewById(R.id.button0);
        vet[1] = (Button) findViewById(R.id.button1);
        vet[2] = (Button) findViewById(R.id.button2);
        vet[3] = (Button) findViewById(R.id.button3);
        vet[4] = (Button) findViewById(R.id.button4);
        vet[5] = (Button) findViewById(R.id.button5);
        vet[6] = (Button) findViewById(R.id.button6);
        vet[7] = (Button) findViewById(R.id.button7);
        vet[8] = (Button) findViewById(R.id.button8);
    }


    public boolean controllo(boolean giocatore) {
        int chrControllo;
        if (giocatore) {
            chrControllo = 1;
        } else {
            chrControllo = 2;
        }
        boolean vitt = false;
        // Controllo Orizzontale
        int vittoria = 0;
        for (int i = 0; (i < mat.length) && !vitt; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (chrControllo == mat[i][j]) {
                    vittoria++;
                }
            }

            if (vittoria == 3) {
                vitt = true;
            } else {
                vittoria = 0;
            }
        }
        // Controllo Verticale
        for (int i = 0; (i < mat.length) && !vitt; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (chrControllo == mat[j][i]) {
                    vittoria++;
                }
            }
            if (vittoria == 3) {
                vitt = true;
            } else {
                vittoria = 0;
            }
        }
        // Controllo Diagonale Principale
        for (int i = 0; (i < mat.length) && !vitt; i++) {
            if (chrControllo == mat[i][i]) {
                vittoria++;
            }
        }
        if (vittoria == 3) {
            vitt = true;
        } else {
            vittoria = 0;
        }
        // Controllo Diagonale Secondaria
        for (int i = 0; (i < mat.length) && !vitt; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (i + j == mat.length - 1) {
                    if (chrControllo == mat[i][j]) {
                        vittoria++;
                    }
                }
            }
        }
        if (vittoria == 3) {
            vitt = true;
        } else {
            for (int i = 0; (i < mat.length) && !vitt; i++) {
                for (int j = 0; j < mat[0].length; j++) {

                    if (mat[i][j] == 0) pareggio++;
                }
            }
        }

        if (vitt) {
            return true;
        } else {
            return false;
        }

    }



}