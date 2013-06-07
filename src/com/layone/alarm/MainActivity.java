package com.layone.alarm;

import java.util.ArrayList;
import java.util.HashMap;

import com.layone.alarm.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends FragmentActivity {

	public DrawerLayout drawerLayout;
	public ListView leftList;
	public ArrayAdapter<String> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.getActionBar().hide();
		setContentView(R.layout.activity_main);
		
		initViews();
	}

	private void initViews() {
		drawerLayout = (DrawerLayout) findViewById(R.id.main_layout);
		leftList = (ListView) findViewById(R.id.left_drawer);
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
		//Add menu bar
        HashMap<String, Object> map = new HashMap<String, Object>();  
        map.put("MenuBar", R.drawable.add01); 
        map.put("MenuItem", "添加闹钟 ");  
        listItem.add(map);  
        HashMap<String, Object> map2 = new HashMap<String, Object>();  
        map2.put("MenuBar", R.drawable.add01); 
        map2.put("MenuItem", "设置 ");  
        listItem.add(map2);
        HashMap<String, Object> map3 = new HashMap<String, Object>();  
        map3.put("MenuBar", R.drawable.add01); 
        map3.put("MenuItem", "帮助 ");  
        listItem.add(map3);
        HashMap<String, Object> map4 = new HashMap<String, Object>();  
        map4.put("MenuBar", R.drawable.add01); 
        map4.put("MenuItem", "关于 ");  
        listItem.add(map4);
		SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,
	            R.layout.list_menu,  
	            new String[] {"MenuBar","MenuItem"},   
	            new int[] {R.id.MenuBar,R.id.MenuItem}  
	        );  
		leftList.setAdapter(listItemAdapter);
		leftList.setOnItemClickListener(new OnItemClickListener() {  
      	  
            @Override  
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
                    long arg3) {  
                if(arg2==0){
                	Intent intent=new Intent();
                	intent.setClass(MainActivity.this, Setting.class);
                	startActivity(intent);
                }
            }  
        }); 
		initFragments();
	}

	private void initFragments() {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		DrawerFragment fragment = new DrawerFragment();
		fragment.setDrawerLayout(drawerLayout, leftList);
		transaction.add(R.id.main_content, fragment);
		transaction.commit();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_MENU) {

			if (drawerLayout.isDrawerOpen(leftList)) {
				drawerLayout.closeDrawer(leftList);
			} else {
				drawerLayout.openDrawer(leftList);
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}
