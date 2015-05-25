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
	 // �洢���ݵ������б� 
	   ArrayList<HashMap<String, Object>> listData;
	 // ������ 
	   SimpleAdapter listItemAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.zytitle);

		//�õ���xml�ļ��ж����spinner        // ��ʼ���ؼ�
	      mSpinner1 = (Spinner)findViewById(R.id.spinner1);
	      TShow1 = (TextView)findViewById(R.id.TS1); 
	      TShow1.setText("Ŀ�ĵ�");

		 // mSpinner1.setPrompt("Ŀ�ĵ�");
		  mSpinner2 = (Spinner)findViewById(R.id.spinner2);
		  TShow2 = (TextView)findViewById(R.id.TS2); 
		  TShow2.setText("�г�����");
		//  mSpinner2.setPrompt("�г�����");
		  mSpinner3 = (Spinner)findViewById(R.id.spinner3);
		  TShow3 = (TextView)findViewById(R.id.TS3); 
		  TShow3.setText("���η�ʽ");
		  //mSpinner3.setPrompt("���η�ʽ");     

	      //׼��һ���������������������ʽ��ʹ��ϵͳ����ʽ    
		  ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(MainActivity.this, R.array.mdd, R.layout.myspinner);  
		       //���������б����ʽ  //�����б�����ʾ���Ϊ��ȫ��ʾ
		       adapter1.setDropDownViewResource(R.layout.spinneritem); 
		       //Ϊ�����б�����������  
		       mSpinner1.setAdapter(adapter1);
		       mSpinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
		            @Override
		            
		            public void onItemSelected(AdapterView<?> arg0, View arg1,  
		                    int arg2, long arg3){
	
		            	//����spinnerѡ��ѡ�к�����Textview
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
		 	 // ���������б����ʽ 
		 	   adapter2.setDropDownViewResource(R.layout.spinneritem); 
		 	  //Ϊ�����б�����������      //ʹ��������
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
			  //���������б����ʽ 
			   adapter3.setDropDownViewResource(R.layout.spinneritem); 
			   //Ϊ�����б�����������  
			   mSpinner3.setAdapter(adapter3);
			   mSpinner3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
		            @Override
		            public void onItemSelected(AdapterView<?> parent, View view, 
		                    int pos, long id) {
		            
		               // String[] cyfs = getResources().getStringArray(R.array.cyfs);
		                //Toast.makeText(ZymActivity.this, "��������:"+cyfs[pos], 2000).show();
		    				// TODO Auto-generated method stub
		    				//Object progressDialog = ProgressDialog.show(ZymActivity.this, 
		    					//"",	"������...", true, false);    
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
				listItemAdapter = new SimpleAdapter(MainActivity.this,listData,// ����Դ  
						R.layout.list_row,// ListItem��XMLʵ��  
						 // ��̬������ImageItem��Ӧ������  
						 new String[] { "image", "lxname", "cost" },  
						 // ImageItem��XML�ļ������һ��ImageView,����TextView ID  
						new int[] { R.id.image, R.id.lxname, R.id.cost });
				mylist.setAdapter(listItemAdapter);
			
				//list.setOnCreateContextMenuListener(listviewLongPress); 	
				
				mylist.setOnItemClickListener(new OnItemClickListener(){
					 
		            @Override
		            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		                    long arg3) {
		                // TODO Auto-generated method stub
		            	//���ѡ�����HashMap����   
		               // HashMap<String,String> map=(HashMap<String,String>) mylist.getItemAtPosition(arg2);   
		              //  String title=map.get("itemTitle");   
		              //  String content=map.get("itemContent");   
		               // Toast.makeText(getApplicationContext(),    
		                     //   "��ѡ���˵�"+arg2+"��Item��itemTitle��ֵ�ǣ�"+title+"itemContent��ֵ��:"+content,   
		                       // Toast.LENGTH_SHORT).show();   
		            	startActivity(new Intent(MainActivity.this,
		            			CsActivity.class));
		                }
		        });
				
		 } 
		          	 
	
	// ���ݿ������  
	  
			class SQLiteDatabaseDao {  
			  
			 public SQLiteDatabaseDao() {  
			 mDb = openOrCreateDatabase("mydb.db",  
			 SQLiteDatabase.CREATE_IF_NECESSARY, null);  
			 //��ʼ��������  
			 createTable(mDb, "luxianTable");
			 createTable(mDb, "luxianTable2");
			 //��ʼ����������  
			 insert(mDb, "luxianTable");  
			 insert(mDb, "luxianTable2");  
			 //��ʼ����ȡ�������ݱ�����  
			 getAllData("luxianTable");  
			 getAllData("luxianTable2");  
			 }  
			  
			 // ����һ�����ݿ�  
			 public void createTable(SQLiteDatabase mDb, String table) {  
				 try {  
					/* String sql = "create table LXTable(id integer primary key autoincrement, lxname text not null, cost text not null,image text,mdd varchar(100),xcts varchar(100),cyfs varchar(100))";
					  mDb.execSQL(sql);}
					  catch (SQLException e) {  
							 Toast.makeText(getApplicationContext(), "���ݱ���ʧ��",  
							 Toast.LENGTH_LONG).show();
					  }*/
				 mDb.execSQL( "create table if not exists "  
				 + table 
				 + " (id integer primary key autoincrement, "  
				 + "lxname text not null, cost text not null,image text,mdd text,xcts text,cyfs text)");  
				 } catch (SQLException e) {  
				 Toast.makeText(getApplicationContext(), "���ݱ���ʧ��",  
				 Toast.LENGTH_LONG).show();  
				 }
				 try {
				 mDb.execSQL( "create table if not exists "  
						 + table  
						 + " (id integer primary key autoincrement, "  
						 + "lxname text not null, lxts text not null,ckxc text,fysm text)");  
						 } catch (SQLException e1) {  
							 Toast.makeText(getApplicationContext(), "���ݱ���ʧ��",  
									 Toast.LENGTH_LONG).show(); 
						 }
			 }  
			  
			 // ��������  
			 public void insert(SQLiteDatabase mDb, String table) {  
			  
			 // ��ʼ������8������  
					ContentValues values = new ContentValues();
					values.put("lxname", "�������������������");
					values.put("cost", "���ѣ�480");
					values.put("image", R.drawable.luguhu);
					values.put("mdd", "�����");
					values.put("xcts", "4����");
				    values.put("cyfs", "������");
					mDb.insert(table, null, values);

					values.put("lxname", "�����ų�����һ����");
					values.put("cost", "���ѣ�200");
					values.put("image", R.drawable.lijianggucheng);
					values.put("mdd", "����");
					values.put("xcts", "1����");
					values.put("cyfs", "������");
					mDb.insert(table, null, values);

					values.put("lxname", "���������������������");
					values.put("cost", "���ѣ�580");
					values.put("image", R.drawable.xainggelila);
					values.put("mdd", "�������");
					values.put("xcts", "3����");
					values.put("cyfs", "������");
					mDb.insert(table, null, values);
					
					values.put("lxname", "�����ųǺ���̶���к�һ����");
					values.put("cost", "���ѣ�180");
					values.put("image", R.drawable.heilongtan);
					values.put("mdd", "���к�");
					values.put("xcts", "1����");
					values.put("cyfs", "������");
					mDb.insert(table, null, values);

					values.put("lxname", "�����ųǹ���Ͽһ����");
					values.put("cost", "���ѣ�280");
					values.put("image", R.drawable.guanyinxia);
					values.put("mdd", "����Ͽ");
					values.put("xcts", "1����");
					values.put("cyfs", "�Լ���");
					mDb.insert(table, null, values);

					values.put("lxname", "�����ų�����ѩɽ�����������������");
					values.put("cost", "���ѣ�880");
					values.put("image", R.drawable.xainggelila);
					values.put("mdd", "�������");
					values.put("xcts", "4����");
					values.put("cyfs", "������");
					mDb.insert(table, null, values);

					values.put("lxname", "��������ѩɽ���¹����������������");
					values.put("cost", "���ѣ�1080");
					values.put("image", R.drawable.lanyuegu);
					values.put("mdd", "�����");
					values.put("xcts", "5����");
					values.put("cyfs", "������");
					mDb.insert(table, null, values);

					values.put("lxname", "��ˮկ����Ͽ����һ����");
					values.put("cost", "���ѣ�280");
					values.put("image", R.drawable.hutiaoxia);
					values.put("mdd", "����Ͽ");
					values.put("xcts", "2����");
					values.put("cyfs", "������");
					mDb.insert(table, null, values);


				}



					//}

			 // ��ѯ��������  
		 public void getAllData(String table) { 
			 Cursor c = mDb.rawQuery("select * from " + table, null);  
			 int columnsSize = c.getColumnCount();  
			 listData = new ArrayList<HashMap<String, Object>>();  
			 // ��ȡ�������  
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
			 // ɾ��һ������  
			/* public boolean delete(SQLiteDatabase mDb, String table, int id) {  
			 String whereClause = "id=?";  
			 String[] whereArgs = new String[] { String.valueOf(id) };  
			 try {  
			 mDb.delete(table, whereClause, whereArgs);  
			 } catch (SQLException e) {  
			 Toast.makeText(getApplicationContext(), "ɾ�����ݿ�ʧ��",  
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

