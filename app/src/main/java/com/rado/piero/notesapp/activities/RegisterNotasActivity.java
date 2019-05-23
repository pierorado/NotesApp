package com.rado.piero.notesapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rado.piero.notesapp.R;
import com.rado.piero.notesapp.repositories.NotaRepository;

public class RegisterNotasActivity extends AppCompatActivity {

    private EditText tituloInput;
    private EditText contenidoInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_notas);

        tituloInput=findViewById(R.id.titulo_input);
        contenidoInput=findViewById(R.id.contenido_input);

    }
    public void callRegister(View view){
        String titulo = tituloInput.getText().toString();
        String contenido = contenidoInput.getText().toString();

        if(titulo.isEmpty() || contenido.isEmpty()){
            Toast.makeText(this, "complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        NotaRepository.create(titulo,contenido);

        finish();

    }

}
