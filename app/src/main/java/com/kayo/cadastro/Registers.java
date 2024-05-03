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
	private static final String TAG = "Registers";
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
			layout.addView(title);
		}

		Resources resources = getResources();

		for(int id = 0; id < lastId; id++){
			Log.d(TAG, String.format("Accessing shared preferences: %s, ID: %s", globalKey, lastId));

			SharedPreferences preferences = getSharedPreferences(globalKey + "-" + id, MODE_PRIVATE);

			Log.d(TAG, "Creating text views");

			TextView nomeTextview = new TextView(this),
			enderecoTextview = new TextView(this),
			numeroTextview = new TextView(this),
			cepTextview = new TextView(this),
			cidadeTextview = new TextView(this),
			estadoTextview = new TextView(this);

			Log.d(TAG, "Setting text views styles");

			nomeTextview.setTextSize(20);
			enderecoTextview.setTextSize(20);
			numeroTextview.setTextSize(20);
			cepTextview.setTextSize(20);
			cidadeTextview.setTextSize(20);
			estadoTextview.setTextSize(20);

			Log.d(TAG, "Setting text from shared preferences");

			nomeTextview.setText(resources.getString(R.string.name) + ": " + preferences.getString("nome", ""));
			enderecoTextview.setText(resources.getString(R.string.address) + ": " + preferences.getString("endereco", ""));
			numeroTextview.setText(resources.getString(R.string.phoneNumber) + ": " + preferences.getString("numero", ""));
			cepTextview.setText(resources.getString(R.string.zip) + ": " + preferences.getString("cep", ""));
			cidadeTextview.setText(resources.getString(R.string.city) + ": " + preferences.getString("cidade", ""));
			estadoTextview.setText(resources.getString(R.string.state) + ": " + preferences.getString("estado", ""));

			Log.d(TAG, "Adding view to the layout");

			layout.addView(nomeTextview);
			layout.addView(enderecoTextview);
			layout.addView(numeroTextview);
			layout.addView(cepTextview);
			layout.addView(cidadeTextview);
			layout.addView(estadoTextview);
		}
	}
}
