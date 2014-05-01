package com.example.aede;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SubCompany extends Activity{
	
	private int [] image = {R.drawable.sm,R.drawable.fnc,R.drawable.jyp,R.drawable.yg};
	private String[] name  = { "SM", "FNC", "JYP", "YG"};
	private String[] website  = { "www.smtown.com", "www.fncent.com", "www.jype.com", "www.ygfamily.com"};
	private String[] address  = { "Seoul, Gangnam-gu, Apgujeong-2dong, 521, SM Entertainment",
			"Address: 111, Cheongdam-dong, Gangnam-gu, Seoul, South Korea",
			"Seoul, Gangnam-gu, Cheongdam-dong, 123-50, JYP Entertainment",
			"Seoul, Mapo-gu, Hapjeong-dong, 397-5, YG Entertainment"};
	private String[] tel  = { "058455545", "5285258252", "75289240044", "45270278305"};
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.subcompany);
	        Bundle extras = getIntent().getExtras();
	        int position = extras.getInt("POSITION");
	        
	        ImageView img = (ImageView)findViewById(R.id.comp_img);
			TextView nname = (TextView)findViewById(R.id.comp_name);
			TextView web = (TextView)findViewById(R.id.comp_webs);
			TextView add = (TextView)findViewById(R.id.comp_addr);
			TextView tele = (TextView)findViewById(R.id.comp_tel);
			img.setImageResource(image[position]);
			nname.setText(name[position]);
			web.setText(website[position]);
			add.setText(address[position]);
			tele.setText(tel[position]);
	        
		}
}
