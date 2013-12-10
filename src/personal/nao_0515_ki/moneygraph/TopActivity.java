package personal.nao_0515_ki.moneygraph;

import personal.nao_0515_ki.onehundredyenperanhour.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class TopActivity extends Activity {

	TouchDB touchDB;
	Action action;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

		//get context
		Context context = getApplicationContext();
		
		//relate button click listener
		Button insertButton = (Button)findViewById(R.id.Done);
		Button removeLatestInput = (Button)findViewById(R.id.RemoveLatestInput);
		Button removeAllData = (Button)findViewById(R.id.Remove);
		insertButton.setTag("Done");
		removeLatestInput.setTag("RemoveLatestInput");
		removeAllData.setTag("Remove");
		insertButton.setOnClickListener(new ButtonClickListener());
		removeLatestInput.setOnClickListener(new ButtonClickListener());
		removeAllData.setOnClickListener(new ButtonClickListener());
		
		//DBopen
		touchDB = new TouchDB();
		touchDB.openDB(context);

		//TableDrop(for debug)
//		touchDB.dropTable();
		//TableCreate
		touchDB.createTable();

		//get info on view
		TextView yenPerHourOn1Hour = (TextView)findViewById(R.id.yenPerHourOn1Hour);
		TextView yenPerHourOn24Hour = (TextView)findViewById(R.id.yenPerHourOn24Hour);
		TextView forecast1day = (TextView)findViewById(R.id.forecast1day);
		TextView forecast1month = (TextView)findViewById(R.id.forecast1month);
		TextView input_money = (TextView)findViewById(R.id.input_money);

		//create Action Class
		action = new Action(context,touchDB,yenPerHourOn1Hour,yenPerHourOn24Hour,forecast1day,forecast1month,input_money);
		action.viewSpeed();

	}
		
	//method of done
	class ButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			// what button pushed 
			String buttonName = (String)arg0.getTag();
			Log.d("Button Clicked as :", buttonName);

			//get info for button pushed
			EditText money = (EditText)findViewById(R.id.input_money);
			//refactor later(input_money to input_memo)
			EditText memo = (EditText)findViewById(R.id.input_money);

			
			//Refactor
			//in the case of pushed Done
			if(buttonName.contains("Done")){
				action.doneAction(money.getText().toString(), memo.getText().toString());
			}else if(buttonName.contains("RemoveLatestInput")){
				action.removeLatestInput();
			}else if(buttonName.contains("Remove")) {
				touchDB.dropTable();
				touchDB.createTable();
				action.viewSpeed();
			}else{
			}
		}
	}
		
}

