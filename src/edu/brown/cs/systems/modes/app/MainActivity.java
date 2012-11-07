package edu.brown.cs.systems.modes.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;
import edu.brown.cs.systems.modes.lib.ModeManager;

public class MainActivity extends Activity {

	private ModeManager modeManager;
	private TextView message;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		modeManager = new ModeManager(this);
		modeManager.connectApplication();
		setContentView(R.layout.activity_main);
		message = (TextView) findViewById(R.id.message);
		LocalBroadcastManager.getInstance(this).registerReceiver(
				messageReceiver, new IntentFilter("setMode"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onDestroy() {
		LocalBroadcastManager.getInstance(this).unregisterReceiver(
				messageReceiver);
		modeManager.disconnectApplication();
		super.onDestroy();
	}

	private BroadcastReceiver messageReceiver = new BroadcastReceiver() {

		private static final String TAG = "messageReceiver";

		@Override
		public void onReceive(Context context, Intent intent) {
			// Get mode name include in the Intent
			String modeName = intent.getStringExtra("modeName");
			Log.d(TAG, "Got message: " + modeName);

			if (modeName.equals(Modes.MODE1) || modeName.equals(Modes.MODE2)) {
				message.setText(modeName);
			} else if (modeName.equals(Modes.MODE2)) {

			} else {
				Toast.makeText(context, "Invalid mode", Toast.LENGTH_SHORT)
						.show();
			}
		}
	};
}
