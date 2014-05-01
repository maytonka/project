package com.example.aede;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Company extends Fragment {
	Context context;
	private int[] image = { R.drawable.sm, R.drawable.fnc, R.drawable.jyp,
			R.drawable.yg };
	private String[] name = { "SM", "FNC", "JYP", "YG" };

	public Company(Context context) {
		this.context = context;
	}

	private ListView listV;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.company, container, false);

		listV = (ListView) rootView.findViewById(R.id.listView1);
		ItemAdapter itemAdapter = new ItemAdapter(context, image, name);
		listV.setAdapter(itemAdapter);

		listV.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {

				Intent intent = new Intent(context, SubCompany.class);
				intent.putExtra("POSITION", position);
				context.startActivity(intent);
			}

		});
		return rootView;
	}

	class ItemAdapter extends BaseAdapter {

		int[] image;
		String[] text_header;
		Context context;

		public ItemAdapter(Context context, int[] image, String[] text_header) {
			this.context = context;
			this.text_header = text_header;
			this.image = image;
		}

		@Override
		public int getCount() {

			return image.length;
		}

		@Override
		public Object getItem(int position) {

			return position;
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			TextView mHeader;
			ImageView imageV;
			if (view == null) {
				LayoutInflater vi = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = vi.inflate(R.layout.company_item, null);
			}
			imageV = (ImageView) view.findViewById(R.id.com_img);
			mHeader = (TextView) view.findViewById(R.id.com_name);
			mHeader.setText(text_header[position]);
			imageV.setImageResource(image[position]);
			return view;
		}

	}
}
