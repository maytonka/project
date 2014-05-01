package com.example.aede;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;

@SuppressLint("NewApi")
public class SubMember extends Activity implements OnResponseListener
{

    private SimpleAdapter arrayAdapter;
    private ConnectServer connectServer;
	private ListView listV;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.submem);
	        Bundle object = getIntent().getExtras();	        
	        String sId = object.getString("StudentID");	        
	        //เชื่อม listView กับ View
			listV = (ListView) findViewById (R.id.listView1); 
	        //สร้างตัวเชื่อมต่อกับ Server ไปที่ URL ที่กำหนด
	        connectServer = new ConnectServer(this, "http://10.0.2.2/profiletest1/innerUser.php", this);
	        connectServer.addValue("StudentID", sId);
	        //เชื่อมต่อกับ Server
	        connectServer.execute();			
			listV.setOnItemClickListener(new OnItemClickListener()
			{
				@SuppressWarnings("unchecked")
				@Override
				public void onItemClick(AdapterView<?> adapter, View view, int position,long id) 
				{
					HashMap<String, String> object  = (HashMap<String, String>) adapter.getItemAtPosition(position);			
					Intent intent = new Intent(SubMember.this,Profile.class);				
					intent.putExtra("StudentID",object.get("StudentID"));
					startActivity(intent);
				}			
			});			
	    }
	 	//ถ้าดึงข้อมูลจาก Server เสร็จแล้ว จะมาทำงานที่ Function นี้

	    @Override
	    public void onResponse(ArrayList<HashMap<String, String>> list)
	    {
	    	String[] from = { "Name" };
	    	int[] to = { R.id.submem_name };
			arrayAdapter = new SimpleAdapter(this, list, R.layout.submem_item,from,to);
			listV.setAdapter(arrayAdapter);
	    }
}
		 