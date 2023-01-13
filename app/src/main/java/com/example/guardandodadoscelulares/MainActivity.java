package com.example.guardandodadoscelulares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText campNome;
    private Button btnSalvar;
    private TextView textResultado;
    private static final String ARQUIVO_PREFERENCES = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campNome = findViewById(R.id.edit_nome);
        btnSalvar = findViewById(R.id.btn_salvar);
        textResultado = findViewById(R.id.text_res);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCES, 0);
                SharedPreferences.Editor editor = preferences.edit();

                if (!campNome.getText().toString().isEmpty()){

                    String nome = campNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();
                    textResultado.setText("Olá, " + nome);

                }else{
                    Toast.makeText(getApplicationContext(), "Digite um nome", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //recuperar dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCES, 0);

        //Validar se temos o nome em preferences
        if (preferences.contains("nome")){

            String nome = preferences.getString("nome", "usuário não definido");
            textResultado.setText("Olá, " + nome);
        }else{
            textResultado.setText("Olá, usuário não definido");
        }


    }
}