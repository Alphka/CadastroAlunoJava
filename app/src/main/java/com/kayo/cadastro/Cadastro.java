package com.kayo.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;

public class Cadastro extends AppCompatActivity {
	String title;
	boolean isMaster;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();

		title = intent.getStringExtra("ACTIVITY_TITLE");
		isMaster = intent.getStringExtra("TYPE").equals("master");

		String globalKey = (isMaster ? "master" : "student") + "_data";
		SharedPreferences globalPreferences = getSharedPreferences(globalKey, MODE_PRIVATE);
		SharedPreferences.Editor globalEditor = globalPreferences.edit();

		final int[] userId = { globalPreferences.getInt("id", 0) };

		setContentView(R.layout.activity_cadastro);
		getSupportActionBar().setTitle(title);

		Resources resources = getResources();

		TextView id = findViewById(R.id.id);

		id.setText("ID: " + userId[0]);

		EditText nome = findViewById(R.id.nomeInput),
		endereco = findViewById(R.id.enderecoInput),
		numero = findViewById(R.id.numeroInput),
		cep = findViewById(R.id.cepInput),
		cidade = findViewById(R.id.cidadeInput),
		estado = findViewById(R.id.estadoInput);

		Button limpar = findViewById(R.id.limpar),
		salvar = findViewById(R.id.salvar);

		Runnable limparCampos = () -> {
			nome.setText("");
			endereco.setText("");
			numero.setText("");
			cep.setText("");
			cidade.setText("");
			estado.setText("");
		};

		limpar.setOnClickListener(view -> {
			limparCampos.run();
			nome.requestFocus();

			Toast.makeText(this, resources.getString(R.string.clearedFields), Toast.LENGTH_SHORT).show();
		});

		salvar.setOnClickListener(view -> {
			String nomeString = nome.getText().toString(),
			enderecoString = endereco.getText().toString(),
			numeroString = numero.getText().toString(),
			cepString = cep.getText().toString(),
			cidadeString = cidade.getText().toString(),
			estadoString = estado.getText().toString();

			int currentUserId = userId[0]++;

			SharedPreferences preferences = getSharedPreferences(globalKey + "-" + currentUserId, MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();

			editor.putString("nome", nomeString);
			editor.putString("endereco", enderecoString);
			editor.putString("numero", numeroString);
			editor.putString("cep", cepString);
			editor.putString("cidade", cidadeString);
			editor.putString("estado", estadoString);
			editor.apply();

			globalEditor.putInt("id", userId[0]);
			globalEditor.apply();

			id.setText("ID: " + userId[0]);

			limparCampos.run();

			Toast.makeText(this, resources.getString(R.string.addedUser), Toast.LENGTH_SHORT).show();
		});
	}
}
