package com.kayo.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.widget.LinearLayout;
import android.graphics.Typeface;
import android.widget.TextView;
import android.os.Bundle;
import android.util.Log;

public class Registers extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registers);

		LinearLayout layout = findViewById(R.id.layout);
		Resources resources = getResources();

		addData(layout, resources.getString(R.string.mastersTitle), getGlobalClass(true), getGlobalId(true));
		addData(layout, resources.getString(R.string.studentsTitle), getGlobalClass(false), getGlobalId(false));

		layout.invalidate();
	}
	private String getGlobalClass(boolean isMaster){
		return (isMaster ? "master" : "student") + "_data";
	}
	private int getGlobalId(boolean isMaster){
		String globalKey = getGlobalClass(isMaster);
		SharedPreferences globalPreferences = getSharedPreferences(globalKey, MODE_PRIVATE);

		return globalPreferences.getInt("id", 0);
	}
	private void addData(LinearLayout layout, String titleString, String globalKey, int lastId){
		if(lastId > 0){
			TextView title = new TextView(this);
			title.setTextSize(24);
			title.setTypeface(null, Typeface.BOLD);
			title.setText(titleString);
			title.setPadding(60, 40, 0, 0);
			layout.addView(title);
		}

		Resources resources = getResources();

		for(int id = 0; id < lastId; id++){
			SharedPreferences preferences = getSharedPreferences(globalKey + "-" + id, MODE_PRIVATE);

			TextView nomeTextview = new TextView(this),
			enderecoTextview = new TextView(this),
			numeroTextview = new TextView(this),
			cepTextview = new TextView(this),
			cidadeTextview = new TextView(this),
			estadoTextview = new TextView(this);

			nomeTextview.setTextSize(20);
			enderecoTextview.setTextSize(20);
			numeroTextview.setTextSize(20);
			cepTextview.setTextSize(20);
			cidadeTextview.setTextSize(20);
			estadoTextview.setTextSize(20);

			nomeTextview.setPadding(60, 20, 0, 0);
			nomeTextview.setPadding(60, 0, 0, 0);
			enderecoTextview.setPadding(60, 0, 0, 0);
			numeroTextview.setPadding(60, 0, 0, 0);
			cepTextview.setPadding(60, 0, 0, 0);
			cidadeTextview.setPadding(60, 0, 0, 0);
			estadoTextview.setPadding(60, 0, 0, 40);

			nomeTextview.setText(resources.getString(R.string.name) + ": " + preferences.getString("nome", ""));
			enderecoTextview.setText(resources.getString(R.string.address) + ": " + preferences.getString("endereco", ""));
			numeroTextview.setText(resources.getString(R.string.phoneNumber) + ": " + preferences.getString("numero", ""));
			cepTextview.setText(resources.getString(R.string.zip) + ": " + preferences.getString("cep", ""));
			cidadeTextview.setText(resources.getString(R.string.city) + ": " + preferences.getString("cidade", ""));
			estadoTextview.setText(resources.getString(R.string.state) + ": " + preferences.getString("estado", ""));

			layout.addView(nomeTextview);
			layout.addView(enderecoTextview);
			layout.addView(numeroTextview);
			layout.addView(cepTextview);
			layout.addView(cidadeTextview);
			layout.addView(estadoTextview);
		}
	}
}
