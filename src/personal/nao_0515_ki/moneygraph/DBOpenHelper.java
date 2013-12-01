package personal.nao_0515_ki.moneygraph;

import android.content.Context;
import android.database.sqlite.*;

public class DBOpenHelper extends SQLiteOpenHelper {

	//コンストラクタ定義
	public DBOpenHelper(Context context){
		super(context,"moneyDB",null,1);
	}
	
	//onCreateメソッド
	public void onCreate(SQLiteDatabase database){
	}
	
	//onUpgradeメソッド
	public void onUpgrade(SQLiteDatabase database, int oldversion, int newversion){
	}

}
