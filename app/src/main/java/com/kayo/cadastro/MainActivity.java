package com.kayo.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		getSupportActionBar().hide();

		Button cadastrarAluno = findViewById(R.id.cadastrarAluno);

		cadastrarAluno.setOnClickListener(this::GotoStudentsView);

		Button cadastrarProfessor = findViewById(R.id.cadastrarProfessor);

		cadastrarProfessor.setOnClickListener(this::GotoMastersView);

		Button verCadastros = findViewById(R.id.verCadastros);

		verCadastros.setOnClickListener(view -> {
			Intent intent = new Intent(this, Registers.class);
			startActivity(intent);
		});
	}
	private void GotoRegisterView(View view, boolean isMaster){
		Resources resources = getResources();
		Intent intent = new Intent(this, Cadastro.class);

		intent.putExtra("ACTIVITY_TITLE", resources.getString(isMaster ? R.string.mastersActivityTitle : R.string.studentsActivityTitle));
		intent.putExtra("TYPE", isMaster ? "master" : "student");

		startActivity(intent);
	}
	private void GotoStudentsView(View view){
		this.GotoRegisterView(view, false);
	}
	private void GotoMastersView(View view){
		this.GotoRegisterView(view, true);
	}
}
