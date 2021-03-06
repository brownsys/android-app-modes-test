package edu.brown.cs.systems.modes.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import edu.brown.cs.systems.modes.lib.data.ModeData;
import edu.brown.cs.systems.modes.lib.IModeService;

/**
 * @author Marcelo Martins <martins@cs.brown.edu>
 * 
 */
public class ModeProxyService extends Service {

    private static final String TAG = "ModeProxyService";

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

	private boolean sendModeBroadcast(String modeName) {
		Intent intent = new Intent("setMode");
		intent.putExtra("modeName", modeName);

		LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

		return true;
	}

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Service#onBind(android.content.Intent)
     */
    @Override
    public IBinder onBind(Intent intent) {
        return new IModeService.Stub() {

            public boolean setMode(String modeName) throws RemoteException {
                Log.d(TAG, String.format("setMode(): %s", modeName));
				return sendModeBroadcast(modeName);
            }

            public List<ModeData> getModes() throws RemoteException {
                Log.d(TAG, "getModes()");
                List<ModeData> modes = new ArrayList<ModeData>();

                ModeData data = new ModeData(Modes.MODE1, "Mode number one");
                modes.add(data);
                data = new ModeData(Modes.MODE2, "Mode number two");
                modes.add(data);
                return modes;
            }
        };
    }
}
