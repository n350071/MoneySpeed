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

		//Contextの取得
		Context context = getApplicationContext();
		
		//ボタンクリックリスナーの関連付け
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

		//画面情報の取得
		TextView yenPerHourOn1Hour = (TextView)findViewById(R.id.yenPerHourOn1Hour);
		TextView yenPerHourOn24Hour = (TextView)findViewById(R.id.yenPerHourOn24Hour);
		TextView forecast1day = (TextView)findViewById(R.id.forecast1day);
		TextView forecast1month = (TextView)findViewById(R.id.forecast1month);
		TextView input_money = (TextView)findViewById(R.id.input_money);

		//Actionクラス生成
		action = new Action(context,touchDB,yenPerHourOn1Hour,yenPerHourOn24Hour,forecast1day,forecast1month,input_money);
		action.viewSpeed();

	}
		
	//doneボタンのメソッド
	class ButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			// どのボタンが押されたかをbuttonNameに詰め込む
			String buttonName = (String)arg0.getTag();
			Log.d("Button Clicked as :", buttonName);

			//Buttonが押されたときの情報を取得
			EditText money = (EditText)findViewById(R.id.input_money);
			//あとで修正(input_money to input_memo)
			EditText memo = (EditText)findViewById(R.id.input_money);

			
			//押されたボタンの内容によってactionを変える←次のリファクタリングで変更する
			//Doneが押された場合
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

