package com.example.aede;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SubPost extends Activity{
	
	private int [] image = {R.drawable.taeyeon,R.drawable.yoona,R.drawable.keroro,R.drawable.tamama};
	private String[] name  = { "Taeyeon", "Yoona", "KERORO", "TAMAMA"};
	private String[] typepost  = { "Freelance", "General", "Public Relations", "Freelance"};
	private String[] des  = {
	"There are two reasons why I wake up in the morning: my alarm clock and you.",
	"A lady is a woman who makes a man behave like a gentleman. You're such a lady to me.",
	"If, out of time, I could pick one moment and keep it shining, always new, of all the days that I have lived, I'd pick the moment I met you.",
	"When it rains, you don't see the sun, but it's there. Hope we can be like that. We don't always see each other, but we will always be there for one another."};
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.subpost);
	        Bundle extras = getIntent().getExtras();
	        int position = extras.getInt("POSITION");
	        
	        ImageView img = (ImageView)findViewById(R.id.spost_img);
			TextView nname = (TextView)findViewById(R.id.spost_name);
			TextView ddes = (TextView)findViewById(R.id.spost_des);
			TextView type = (TextView)findViewById(R.id.spost_type);
			img.setImageResource(image[position]);
			nname.setText(name[position]);
			ddes.setText(des[position]);
	        type.setText(typepost[position]);
		}
}
