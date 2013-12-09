package personal.nao_0515_ki.moneygraph;

import android.content.Context;
import android.database.sqlite.*;

public class DBOpenHelper extends SQLiteOpenHelper {

	//define constracter
	public DBOpenHelper(Context context){
		super(context,"moneyDB",null,1);
	}
	
	//onCreate method
	public void onCreate(SQLiteDatabase database){
	}
	
	//onUpgrade medhod
	public void onUpgrade(SQLiteDatabase database, int oldversion, int newversion){
	}

}
