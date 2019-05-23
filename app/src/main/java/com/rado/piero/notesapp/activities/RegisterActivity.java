package com.rado.piero.notesapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rado.piero.notesapp.R;
import com.rado.piero.notesapp.repositories.UserRepository;

public class RegisterActivity extends AppCompatActivity {
    private  EditText fullnameInput;
    private EditText usuarioInput;
    private EditText emailInput;
    private EditText passwordInput;
    private Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullnameInput=findViewById(R.id.fullname_input);
        emailInput=findViewById(R.id.email_input);
        usuarioInput=findViewById(R.id.usuario_input);
        passwordInput=findViewById(R.id.password_input);
        registerButton=findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallRegister();
            }
        });
    }

    private void CallRegister() {
        try {
            String fullname = fullnameInput.getText().toString();
            String usuario = usuarioInput.getText().toString();
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (fullname.isEmpty() || usuario.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "ingrese todo los campos ", Toast.LENGTH_SHORT).show();
                return;
            }
            UserRepository.create(fullname, usuario, email, password);
            Toast.makeText(this, "Registro satisfactorio", Toast.LENGTH_SHORT).show();
            finish();
        }catch (Exception e){
            Toast.makeText(this, "error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
