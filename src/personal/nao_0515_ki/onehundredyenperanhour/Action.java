package personal.nao_0515_ki.onehundredyenperanhour;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;


public class Action {
	
	Context context;
	TouchDB touchDB;
	TextView yenPerHourOn1Hour;
	TextView yenPerHourOn24Hour;
	TextView forecast1day;
	TextView forecast1month;
	TextView input_money;
	Calc calc;
	
	public Action(Context context,TouchDB touchDB, TextView yenPerHourOn1Hour,TextView yenPerHourOn24Hour,
			TextView forecast1day, TextView forecast1month, TextView input_money){
		this.context = context;
		this.touchDB = touchDB;
		this.yenPerHourOn1Hour = yenPerHourOn1Hour;
		this.yenPerHourOn24Hour = yenPerHourOn24Hour;
		this.forecast1day = forecast1day;
		this.forecast1month = forecast1month;
		this.input_money = input_money;
		calc = new Calc(this.touchDB);
	}
	
	public void doneAction(String money, String memo){
		if (money.equalsIgnoreCase("")){
			Toast toast = Toast.makeText(context, "Enter a number", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.show();
		}else{
			touchDB.insert(money,memo);
		}
		input_money.setText("");
		viewSpeed();
	}
	
	public void removeLatestInput(){
		touchDB.delete();
		viewSpeed();
	}
	
	public void viewSpeed(){
		String speed1 = calc.calculate("1");
		String speed24 = calc.calculate("24");
		yenPerHourOn1Hour.setText(speed1);
		yenPerHourOn24Hour.setText(speed24);
		forecast1day.setText(String.valueOf(Integer.parseInt(speed1) * 24));
		forecast1month.setText(String.valueOf(Integer.parseInt(speed24) * 30));
	}
	

}
