package personal.nao_0515_ki.onehundredyenperanhour;

import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;
import android.content.*;

public class TouchDB  {
	
	DBOpenHelper dbOpenHelper;
	SQLiteDatabase database;

	public void openDB(Context context){
		dbOpenHelper = new DBOpenHelper(context);
		database = dbOpenHelper.getWritableDatabase();
	}
	
//	public void createTable(SQLiteDatabase database){
//		String sql 
//		 = "create table moneyDB(" +
//		 		"id integer primary key autoincrement," +
//		 		"dating datetime not null," +
//		 		"money integer not null," +
//		 		"memo text)";
//		
//		try{
//			database.execSQL(sql);
//			Log.e("sql",sql);
//			Log.e("SUCESS","Created table");
//		}catch(Exception e){
//			Log.e("ERROR",e.toString());
//		}
//	}
	
	public void createTable(){
		String sql 
		 = "create table moneyDB(" +
		 		"id integer primary key autoincrement," +
		 		"dating datetime not null," +
		 		"money integer not null," +
		 		"memo text)";
		
		try{
			database.execSQL(sql);
			Log.e("sql",sql);
			Log.e("SUCESS","Created table");
		}catch(Exception e){
			Log.e("ERROR",e.toString());
		}
	}
	
//	public void dropTable(SQLiteDatabase database){
//		String sql
//		= "drop table moneyDB";
//		
//		try{
//			database.execSQL(sql);
//			Log.e("SUCESS","Droped table");
//		}catch(Exception e){
//			Log.e("ERROR",e.toString());
//		}
//	}

	public void dropTable(){
		String sql
		= "drop table moneyDB";
		
		try{
			database.execSQL(sql);
			Log.e("SUCESS","Droped table");
		}catch(Exception e){
			Log.e("ERROR",e.toString());
		}
	}

	public void insert(String money, String memo){
		//トランザクション制御開始
		database.beginTransaction();
		
		String sql
			="Insert into moneyDB(dating,money,memo) " +
			"values(datetime('now'),'"+ money + "','" + memo + "');";
		Log.d("sql", sql);
		database.execSQL(sql);
		database.setTransactionSuccessful();

		//トランザクション制御終了
		database.endTransaction();
	}
	
	public Cursor selectMoneyFromWhen(String when){
		//トランザクション制御開始
		database.beginTransaction();

		String sql
			="Select money from moneyDB " +
					"Where dating > (select datetime('now', '-"+ when +" hours'));";
//		String sql
//		="Select * from moneyDB ;";
		Log.d("select", sql);
		
		Cursor cursor = database.rawQuery(sql,null);  
		
		//トランザクション制御終了
		database.endTransaction();
		
		return cursor;

	}

	public void delete(){
		String sql
		="delete from moneyDB " +
				"Where id = ( select max(id) from moneyDB);";
		
		Log.d("select", sql);
		try{
			database.beginTransaction();
			database.execSQL(sql);
			database.setTransactionSuccessful();
			database.endTransaction();
		}catch(Exception e){
			Log.d("error",e.getMessage());
		}
	}
}
