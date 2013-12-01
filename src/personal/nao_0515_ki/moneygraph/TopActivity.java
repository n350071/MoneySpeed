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

		//Context�̎擾
		Context context = getApplicationContext();
		
		//�{�^���N���b�N���X�i�[�̊֘A�t��
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

		//��ʏ��̎擾
		TextView yenPerHourOn1Hour = (TextView)findViewById(R.id.yenPerHourOn1Hour);
		TextView yenPerHourOn24Hour = (TextView)findViewById(R.id.yenPerHourOn24Hour);
		TextView forecast1day = (TextView)findViewById(R.id.forecast1day);
		TextView forecast1month = (TextView)findViewById(R.id.forecast1month);
		TextView input_money = (TextView)findViewById(R.id.input_money);

		//Action�N���X����
		action = new Action(context,touchDB,yenPerHourOn1Hour,yenPerHourOn24Hour,forecast1day,forecast1month,input_money);
		action.viewSpeed();

	}
		
	//done�{�^���̃��\�b�h
	class ButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			// �ǂ̃{�^���������ꂽ����buttonName�ɋl�ߍ���
			String buttonName = (String)arg0.getTag();
			Log.d("Button Clicked as :", buttonName);

			//Button�������ꂽ�Ƃ��̏����擾
			EditText money = (EditText)findViewById(R.id.input_money);
			//���ƂŏC��(input_money to input_memo)
			EditText memo = (EditText)findViewById(R.id.input_money);

			
			//�����ꂽ�{�^���̓��e�ɂ����action��ς��適���̃��t�@�N�^�����O�ŕύX����
			//Done�������ꂽ�ꍇ
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

