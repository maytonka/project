package com.example.aede;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Message extends Activity implements OnClickListener{
	Button post;
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.message);
	        post = (Button)findViewById(R.id.button1);
			post.setOnClickListener(this);
	}
	public void onClick(View v) {
		if(v == post)
		{
			Intent intent = new Intent(Message.this,Post.class);
			startActivity(intent);
		}
	}
}
