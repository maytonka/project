package com.example.aede;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Register extends Activity implements OnClickListener{
	
	ImageView confrim;
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.register);
	        
	        confrim = (ImageView)findViewById(R.id.imgregis_confrim);
	        confrim.setOnClickListener(this);
	}
	public void onClick(View v) {
		if(v == confrim)
		{
			Intent intent = new Intent(Register.this,Login.class);
			startActivity(intent);
		}
		
	}
}
