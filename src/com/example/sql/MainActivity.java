package com.example.sql;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.app.Activity; 
import android.content.Context; 
import android.database.Cursor; 
import android.os.Bundle; 
import android.view.Menu; 
import android.view.MenuItem; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.AdapterView; 
import android.widget.BaseAdapter; 
import android.widget.EditText; 
import android.widget.ListView; 
import android.widget.TextView; 
import android.widget.Toast; 
import java.util.ArrayList;
import java.util.HashMap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity; 
import android.app.Dialog;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu; 
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner; 
import android.widget.TextView;
import android.widget.Toast;
import android.view.Window;     
import android.content.ContentValues;  
import android.content.Context; 
import android.content.Intent;
import android.database.Cursor;  
import android.database.SQLException;      
import android.widget.ListView;  
import android.widget.SimpleAdapter;  
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.widget.ProgressBar;
import android.util.Log;
public class MainActivity extends Activity {

	   private Spinner mSpinner1;
	   private Spinner mSpinner2; 
	   private Spinner mSpinner3; 
	   
	   private TextView TShow1;
	   private TextView TShow2;
	   private TextView TShow3;  
	   private int count = 1;;
	  
	   
       
	  SQLiteDatabase mDb;
	  SQLiteDatabaseDao dao;
	 // 存储数据的数组列表 
	   ArrayList<HashMap<String, Object>> listData;
	 // 适配器 
	   SimpleAdapter listItemAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.zytitle);

		//得到在xml文件中定义的spinner        // 初始化控件
	      mSpinner1 = (Spinner)findViewById(R.id.spinner1);
	      TShow1 = (TextView)findViewById(R.id.TS1); 
	      TShow1.setText("目的地");

		 // mSpinner1.setPrompt("目的地");
		  mSpinner2 = (Spinner)findViewById(R.id.spinner2);
		  TShow2 = (TextView)findViewById(R.id.TS2); 
		  TShow2.setText("行程天数");
		//  mSpinner2.setPrompt("行程天数");
		  mSpinner3 = (Spinner)findViewById(R.id.spinner3);
		  TShow3 = (TextView)findViewById(R.id.TS3); 
		  TShow3.setText("出游方式");
		  //mSpinner3.setPrompt("出游方式");     

	      //准备一个数组适配器，这里的样式是使用系统的样式    
		  ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(MainActivity.this, R.array.mdd, R.layout.myspinner);  
		       //设置下拉列表的样式  //设置列表项显示风格为完全显示
		       adapter1.setDropDownViewResource(R.layout.spinneritem); 
		       //为下拉列表设置适配器  
		       mSpinner1.setAdapter(adapter1);
		       mSpinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
		            @Override
		            
		            public void onItemSelected(AdapterView<?> arg0, View arg1,  
		                    int arg2, long arg3){
	
		            	//设置spinner选项选中后隐藏Textview
		             	if(count > 1){         
		 		        	   TShow1.setVisibility(View.INVISIBLE);
		 		            }
		 		           count ++;
		            
		            }     
		            @Override
		            public void onNothingSelected(AdapterView<?> parent) {
		                // Another interface callback
		            }
		            });
		 ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.xcts, R.layout.myspinner);  
		 	 // 设置下拉列表的样式 
		 	   adapter2.setDropDownViewResource(R.layout.spinneritem); 
		 	  //为下拉列表设置适配器      //使用适配器
		 	   mSpinner2.setAdapter(adapter2);
		 	   mSpinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
		            @Override
		            public void onItemSelected(AdapterView<?> parent, View view, 
		                    int pos, long id) {
		           
		            	if(count > 2){         
		 		        	   TShow2.setVisibility(View.INVISIBLE);
		 		            }
		 		           count ++;
		            }
		           
		            @Override
		            public void onNothingSelected(AdapterView<?> parent) {
		                // Another interface callback
		            }
		        });
		 ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(MainActivity.this, R.array.cyfs, R.layout.myspinner);  
			  //设置下拉列表的样式 
			   adapter3.setDropDownViewResource(R.layout.spinneritem); 
			   //为下拉列表设置适配器  
			   mSpinner3.setAdapter(adapter3);
			   mSpinner3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
		            @Override
		            public void onItemSelected(AdapterView<?> parent, View view, 
		                    int pos, long id) {
		            
		               // String[] cyfs = getResources().getStringArray(R.array.cyfs);
		                //Toast.makeText(ZymActivity.this, "你点击的是:"+cyfs[pos], 2000).show();
		    				// TODO Auto-generated method stub
		    				//Object progressDialog = ProgressDialog.show(ZymActivity.this, 
		    					//"",	"加载中...", true, false);    
		            	if(count > 3){         
		 		        	   TShow3.setVisibility(View.INVISIBLE);
		 		            }
		 		           count ++;
		            }
		                
		            @Override
		            public void onNothingSelected(AdapterView<?> parent) {
		                // Another interface callback
		            }
		        });
			   
			   dao = new SQLiteDatabaseDao();

				ListView mylist = (ListView) findViewById(R.id.LV1);
				listItemAdapter = new SimpleAdapter(MainActivity.this,listData,// 数据源  
						R.layout.list_row,// ListItem的XML实现  
						 // 动态数组与ImageItem对应的子项  
						 new String[] { "image", "lxname", "cost" },  
						 // ImageItem的XML文件里面的一个ImageView,两个TextView ID  
						new int[] { R.id.image, R.id.lxname, R.id.cost });
				mylist.setAdapter(listItemAdapter);
			
				//list.setOnCreateContextMenuListener(listviewLongPress); 	
				
				mylist.setOnItemClickListener(new OnItemClickListener(){
					 
		            @Override
		            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		                    long arg3) {
		                // TODO Auto-generated method stub
		            	//获得选中项的HashMap对象   
		               // HashMap<String,String> map=(HashMap<String,String>) mylist.getItemAtPosition(arg2);   
		              //  String title=map.get("itemTitle");   
		              //  String content=map.get("itemContent");   
		               // Toast.makeText(getApplicationContext(),    
		                     //   "你选择了第"+arg2+"个Item，itemTitle的值是："+title+"itemContent的值是:"+content,   
		                       // Toast.LENGTH_SHORT).show();   
		            	startActivity(new Intent(MainActivity.this,
		            			CsActivity.class));
		                }
		        });
				
		 } 
		          	 
	
	// 数据库操作类  
	  
			class SQLiteDatabaseDao {  
			  
			 public SQLiteDatabaseDao() {  
			 mDb = openOrCreateDatabase("mydb.db",  
			 SQLiteDatabase.CREATE_IF_NECESSARY, null);  
			 //初始化创建表  
			 createTable(mDb, "luxianTable");
			 createTable(mDb, "luxianTable2");
			 //初始化插入数据  
			 insert(mDb, "luxianTable");  
			 insert(mDb, "luxianTable2");  
			 //初始化获取所有数据表数据  
			 getAllData("luxianTable");  
			 getAllData("luxianTable2");  
			 }  
			  
			 // 创建一个数据库  
			 public void createTable(SQLiteDatabase mDb, String table) {  
				 try {  
					/* String sql = "create table LXTable(id integer primary key autoincrement, lxname text not null, cost text not null,image text,mdd varchar(100),xcts varchar(100),cyfs varchar(100))";
					  mDb.execSQL(sql);}
					  catch (SQLException e) {  
							 Toast.makeText(getApplicationContext(), "数据表创建失败",  
							 Toast.LENGTH_LONG).show();
					  }*/
				 mDb.execSQL( "create table if not exists "  
				 + table 
				 + " (id integer primary key autoincrement, "  
				 + "lxname text not null, cost text not null,image text,mdd text,xcts text,cyfs text)");  
				 } catch (SQLException e) {  
				 Toast.makeText(getApplicationContext(), "数据表创建失败",  
				 Toast.LENGTH_LONG).show();  
				 }
				 try {
				 mDb.execSQL( "create table if not exists "  
						 + table  
						 + " (id integer primary key autoincrement, "  
						 + "lxname text not null, lxts text not null,ckxc text,fysm text)");  
						 } catch (SQLException e1) {  
							 Toast.makeText(getApplicationContext(), "数据表创建失败",  
									 Toast.LENGTH_LONG).show(); 
						 }
			 }  
			  
			 // 插入数据  
			 public void insert(SQLiteDatabase mDb, String table) {  
			  
			 // 初始化插入8条数据  
					ContentValues values = new ContentValues();
					values.put("lxname", "丽江泸沽湖四天三晚游");
					values.put("cost", "花费：480");
					values.put("image", R.drawable.luguhu);
					values.put("mdd", "泸沽湖");
					values.put("xcts", "4日游");
				    values.put("cyfs", "跟团游");
					mDb.insert(table, null, values);

					values.put("lxname", "丽江古城束河一日游");
					values.put("cost", "花费：200");
					values.put("image", R.drawable.lijianggucheng);
					values.put("mdd", "束河");
					values.put("xcts", "1日游");
					values.put("cyfs", "跟团游");
					mDb.insert(table, null, values);

					values.put("lxname", "丽江香格里拉三天两晚游");
					values.put("cost", "花费：580");
					values.put("image", R.drawable.xainggelila);
					values.put("mdd", "香格里拉");
					values.put("xcts", "3日游");
					values.put("cyfs", "跟团游");
					mDb.insert(table, null, values);
					
					values.put("lxname", "丽江古城黑龙潭拉市海一日游");
					values.put("cost", "花费：180");
					values.put("image", R.drawable.heilongtan);
					values.put("mdd", "拉市海");
					values.put("xcts", "1日游");
					values.put("cyfs", "跟团游");
					mDb.insert(table, null, values);

					values.put("lxname", "丽江古城观音峡一日游");
					values.put("cost", "花费：280");
					values.put("image", R.drawable.guanyinxia);
					values.put("mdd", "观音峡");
					values.put("xcts", "1日游");
					values.put("cyfs", "自驾游");
					mDb.insert(table, null, values);

					values.put("lxname", "丽江古城玉龙雪山香格里拉四天三晚游");
					values.put("cost", "花费：880");
					values.put("image", R.drawable.xainggelila);
					values.put("mdd", "香格里拉");
					values.put("xcts", "4日游");
					values.put("cyfs", "跟团游");
					mDb.insert(table, null, values);

					values.put("lxname", "丽江玉龙雪山蓝月谷泸沽湖五天四晚游");
					values.put("cost", "花费：1080");
					values.put("image", R.drawable.lanyuegu);
					values.put("mdd", "泸沽湖");
					values.put("xcts", "5日游");
					values.put("cyfs", "跟团游");
					mDb.insert(table, null, values);

					values.put("lxname", "玉水寨虎跳峡两天一晚游");
					values.put("cost", "花费：280");
					values.put("image", R.drawable.hutiaoxia);
					values.put("mdd", "虎跳峡");
					values.put("xcts", "2日游");
					values.put("cyfs", "跟团游");
					mDb.insert(table, null, values);


				}



					//}

			 // 查询所有数据  
		 public void getAllData(String table) { 
			 Cursor c = mDb.rawQuery("select * from " + table, null);  
			 int columnsSize = c.getColumnCount();  
			 listData = new ArrayList<HashMap<String, Object>>();  
			 // 获取表的内容  
			 while (c.moveToNext()) {  
			 HashMap<String, Object> map = new HashMap<String, Object>();  
			//for (int i = 0; i < columnsSize; i++) {  
			 map.put("id", c.getString(0));  
			 map.put("lxname", c.getString(1));  
			 map.put("cost", c.getString(2));  
			 map.put("image", c.getString(3));  
			// map.put("mdd", c.getString(4));
			// map.put("xcts", c.getString(5));
			// map.put("cyfs", c.getString(6));
			 //}  
			 listData.add(map);  
			 }              
			 } 
			 // 删除一条数据  
			/* public boolean delete(SQLiteDatabase mDb, String table, int id) {  
			 String whereClause = "id=?";  
			 String[] whereArgs = new String[] { String.valueOf(id) };  
			 try {  
			 mDb.delete(table, whereClause, whereArgs);  
			 } catch (SQLException e) {  
			 Toast.makeText(getApplicationContext(), "删除数据库失败",  
			 Toast.LENGTH_LONG).show();  
			 return false;  
			 }  
			 return true;  
			 }  
			 }  
			 @Override  
				public void finish() {  
				        // TODO Auto-generated method stub  
				        super.finish();  
				        mDb.close();  
				    }

		       }*/
	}

}

