package com.example.aede;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends Activity implements OnClickListener,
		OnResponseListener {

	ImageView edit;

	private ConnectServer connectServer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);

		Bundle object = getIntent().getExtras();

		String sId = object.getString("StudentID");

		edit = (ImageView) findViewById(R.id.image_edit);
		edit.setOnClickListener(this);

		// สร้างตัวเชื่อมต่อกับ Server ไปที่ URL ที่กำหนด
		connectServer = new ConnectServer(this,
				"http://10.0.2.2/profiletest1/innerUser.php", this);
		// เพิ่มการส่งค่า age มีค่า 20 แบบ post
		// connectServer.addValue("age","20");
		connectServer.addValue("StudentID", sId);
		// เชื่อมต่อกับ Server
		connectServer.execute();

	}

	public void onClick(View v) {
		if (v == edit) {
			Intent intent = new Intent(Profile.this, EditPro.class);
			startActivity(intent);
		}

	}

	@Override
	public void onResponse(ArrayList<HashMap<String, String>> list) {
		HashMap<String, String> data = list.get(0);

		TextView nname = (TextView) findViewById(R.id.text_name);
		TextView Tel = (TextView) findViewById(R.id.text_tele);
		TextView emai = (TextView) findViewById(R.id.text_email);
		TextView age = (TextView) findViewById(R.id.text_agee);
		TextView work = (TextView) findViewById(R.id.text_office);
		TextView nickn = (TextView) findViewById(R.id.text_nick);
		TextView sId = (TextView) findViewById(R.id.text_sid);
		TextView skill = (TextView) findViewById(R.id.text_skil);
		TextView gpa = (TextView) findViewById(R.id.text_gpa);
		TextView address = (TextView) findViewById(R.id.text_address);
		TextView exp = (TextView) findViewById(R.id.text_exp);

		nname.setText(data.get("Name"));
		Tel.setText(data.get("Telephone"));
		emai.setText(data.get("Email"));
		age.setText(data.get("Age"));
		work.setText(data.get("WorkPlace"));
		nickn.setText(data.get("NickName"));
		sId.setText(data.get("StudentID"));
		skill.setText(data.get("Skill"));
		gpa.setText(data.get("GPA"));
		address.setText(data.get("Address"));
		exp.setText(data.get("Exp"));

	}
}
