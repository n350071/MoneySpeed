package personal.nao_0515_ki.moneygraph;

import android.database.Cursor;
import android.util.Log;

public class Calc {

	TouchDB touchDB;
	
	public Calc(TouchDB touchDB){
		this.touchDB =touchDB;
	}
	
	public String calculate(String when){
		Integer totalMoney=0;
		
		Cursor cursor = touchDB.selectMoneyFromWhen(when);
		
		Log.d("count(*)",String.valueOf(cursor.getCount()));
		
		cursor.moveToFirst();
		for(int i = 0;i<cursor.getCount();i++){
//			Log.d("total in while",String.valueOf(totalMoney));
			totalMoney += cursor.getInt(0);
			cursor.moveToNext();
		}
//		while(cursor.moveToNext()){
//		Log.d("total in while",String.valueOf(totalMoney));
//		totalMoney += cursor.getInt(0);
//	}
//	
		
//		Log.d("total after",String.valueOf(totalMoney));
		totalMoney = totalMoney / Integer.parseInt(when);
		
		return totalMoney.toString();
	}
	
	

}
