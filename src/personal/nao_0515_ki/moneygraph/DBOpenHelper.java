package personal.nao_0515_ki.moneygraph;

import android.content.Context;
import android.database.sqlite.*;

public class DBOpenHelper extends SQLiteOpenHelper {

	//�R���X�g���N�^��`
	public DBOpenHelper(Context context){
		super(context,"moneyDB",null,1);
	}
	
	//onCreate���\�b�h
	public void onCreate(SQLiteDatabase database){
	}
	
	//onUpgrade���\�b�h
	public void onUpgrade(SQLiteDatabase database, int oldversion, int newversion){
	}

}
