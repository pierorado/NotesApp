package com.rado.piero.notesapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rado.piero.notesapp.R;
import com.rado.piero.notesapp.adapters.NotaAdapter;
import com.rado.piero.notesapp.models.Nota;
import com.rado.piero.notesapp.models.User;
import com.rado.piero.notesapp.repositories.NotaRepository;
import com.rado.piero.notesapp.repositories.UserRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REGISTER_FORM_REQUEST = 100;

    private RecyclerView notasList;

    private TextView fulnameText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fulnameText=findViewById(R.id.fullname_text);

        // Configure ReciclerView
        notasList = (RecyclerView) findViewById(R.id.nota_list);
        notasList.setLayoutManager(new LinearLayoutManager(this));

        // Set Data Adapter to ReciclerView
        List<Nota> notas = NotaRepository.list();
        notasList.setAdapter(new NotaAdapter(notas));


            /////usuario

        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        Long id=sp.getLong("id",0);

        User user= UserRepository.load(id);
        if (user==null){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
            return;
        }
        fulnameText.setText(user.getFullname());

    }


    public void callRegisterForm(View view){
        startActivityForResult(new Intent(this, RegisterNotasActivity.class), REGISTER_FORM_REQUEST);
    }

    // return from RegisterActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // refresh data
        NotaAdapter adapter = (NotaAdapter)notasList.getAdapter();

        List<Nota> notas = NotaRepository.list();
        adapter.setNotas(notas);
        adapter.notifyDataSetChanged();

    }

}
