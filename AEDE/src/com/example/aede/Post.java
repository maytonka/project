package com.example.aede;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Post extends Fragment implements OnClickListener {
	Context context;
	Button post;

	public Post(Context context) {
		this.context = context;
	}

	private int[] image = { R.drawable.taeyeon, R.drawable.yoona,
			R.drawable.keroro, R.drawable.tamama };
	private String[] name = { "Taeyeon", "Yoona", "KERORO", "TAMAMA" };
	private String[] typepost = { "Freelance", "General", "Public Relations",
			"Freelance" };
	private String[] num = { "5", "8", "11", "2" };
	private String[] msg = { "There are two reasons", "A lady is a woman who ",
			"If, out of time, I could ", "When it rains, you" };

	private ListView listV;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.post, container, false);

		post = (Button) rootView.findViewById(R.id.button1);
		post.setOnClickListener(this);

		listV = (ListView) rootView.findViewById(R.id.listView1);
		ItemAdapter itemAdapter = new ItemAdapter(context, image, name,
				typepost, num, msg);
		listV.setAdapter(itemAdapter);

		listV.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {

				Intent intent = new Intent(context, SubPost.class);
				intent.putExtra("POSITION", position);
				context.startActivity(intent);
			}

		});
		return rootView;
	}

	public void onClick(View v) {
		if (v == post) {
			Intent intent = new Intent(context, Message.class);
			startActivity(intent);
		}

	}

	class ItemAdapter extends BaseAdapter {

		int[] image;
		String[] text_header;
		String[] text_des;
		String[] text_num;
		String[] text_msg;
		Context context;

		public ItemAdapter(Context context, int[] image, String[] text_header,
				String[] text_des, String[] text_num, String[] text_msg) {
			this.context = context;
			this.text_header = text_header;
			this.text_des = text_des;
			this.image = image;
			this.text_num = text_num;
			this.text_msg = text_msg;
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
			TextView mDescription;
			ImageView imageV;
			TextView nnum;
			TextView mg;
			if (view == null) {
				LayoutInflater vi = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = vi.inflate(R.layout.post_item, null);
			}
			imageV = (ImageView) view.findViewById(R.id.picture);
			mHeader = (TextView) view.findViewById(R.id.textview);
			mDescription = (TextView) view.findViewById(R.id.text_description);
			nnum = (TextView) view.findViewById(R.id.post_num);
			mg = (TextView) view.findViewById(R.id.text_msg);
			mHeader.setText(text_header[position]);
			mDescription.setText(text_des[position]);
			imageV.setImageResource(image[position]);
			nnum.setText(num[position]);
			mg.setText(msg[position]);
			return view;
		}

	}
}
