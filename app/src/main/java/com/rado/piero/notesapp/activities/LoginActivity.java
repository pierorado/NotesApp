package com.rado.piero.notesapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rado.piero.notesapp.R;
import com.rado.piero.notesapp.models.User;
import com.rado.piero.notesapp.repositories.UserRepository;

public class LoginActivity extends AppCompatActivity {
    private EditText usuarioInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuarioInput=findViewById(R.id.usuario_input);
        passwordInput=findViewById(R.id.password_input);

        loginButton=findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallLogin();
            }
        });
        registerButton=findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegister();
            }
        });

        verifySession();

    }


    private void showRegister() {
        startActivity(new Intent(this,RegisterActivity.class));
    }
    private void CallLogin() {
        String usuario=usuarioInput.getText().toString();
        String password=passwordInput.getText().toString();
        if (usuario.isEmpty()||password.isEmpty()){
            Toast.makeText(this,"complete todo los campos",Toast.LENGTH_SHORT).show();
            return;
        }
        User user= UserRepository.Login(usuario,password);
        if (user == null){
            Toast.makeText(this,"usuario y/o password invalidos" , Toast.LENGTH_SHORT).show();
            return;
        }
        //remember session
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        boolean succes =sp.edit()
                    .putString("usuario",usuario)
                    .putLong("id",user.getId())
                    .putBoolean("isloggged",true)
        .commit();

        goMain();


    }
    private void verifySession() {
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        if (sp.getBoolean("islogged",false)){
            goMain();
        }
    }
    private void goMain(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }


}
