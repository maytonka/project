package com.example.aede;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class Member extends Fragment implements OnResponseListener{
	
	Context context;
	private SimpleAdapter arrayAdapter;
    private ConnectServer connectServer;
	public Member(Context context){
		this.context = context;
	}
	private ListView listV; 
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.member, container, false);
			listV = (ListView) rootView.findViewById (R.id.listView1);		 
			//สร้างตัวเชื่อมต่อกับ Server ไปที่ URL ที่กำหนด
	        connectServer = new ConnectServer(context, "http://10.0.2.2/profiletest1/studentid.php", this);
	        //เชื่อมต่อกับ Server 
	        connectServer.execute();
			listV.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> adapter, View view, int position,long id) {				
					@SuppressWarnings("unchecked")
					HashMap<String, String> object  = (HashMap<String, String>) adapter.getItemAtPosition(position);
					Intent intent = new Intent(context,SubMember.class);
					intent.putExtra("StudentID",object.get("StudentID"));		
					context.startActivity(intent);
				}  
			});			
			return rootView;
		}	
	@Override
	public void onResponse(ArrayList<HashMap<String, String>> list) {		
		String[] from = { "StudentID" };
    	int[] to = { R.id.text_member };
		arrayAdapter = new SimpleAdapter(context, list, R.layout.member_item,from,to);
		listV.setAdapter(arrayAdapter);
	}
}
