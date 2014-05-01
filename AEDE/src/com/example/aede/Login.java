package com.example.aede;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends Activity implements OnClickListener {

	TextView regis;
	ImageView login;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		login = (ImageView) findViewById(R.id.btn_login);
		regis = (TextView) findViewById(R.id.login_regis);
		regis.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v == login) {
			Intent intent = new Intent(Login.this, MainActivity.class);
			startActivity(intent);
		} else if (v == regis) {
			Intent intent = new Intent(Login.this, Register.class);
			startActivity(intent);
		}

	}
}
