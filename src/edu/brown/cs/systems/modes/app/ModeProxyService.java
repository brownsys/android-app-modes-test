package edu.brown.cs.systems.modes.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import edu.brown.cs.systems.modes.lib.data.ModeData;
import edu.brown.cs.systems.modes.lib.IModeService;

/**
 * @author Marcelo Martins <martins@cs.brown.edu>
 * 
 */
public class ModeProxyService extends Service {

    private static final String TAG = "ModeService";
    private int uid;

    public void onCreate() {
        super.onCreate();
        uid = getApplicationInfo().uid;
        Log.d(TAG, "onCreate()");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Service#onBind(android.content.Intent)
     */
    @Override
    public IBinder onBind(Intent intent) {
        return new IModeService.Stub() {

            public boolean setMode(long modeId) throws RemoteException {
                Log.d(TAG, String.format("setMode(): %d", modeId));
                return false;
            }

            public List<ModeData> getModes() throws RemoteException {
                Log.d(TAG, "getModes()");
                List<ModeData> modes = new ArrayList<ModeData>();

                ModeData data = new ModeData(1, "Mode1", uid, "Mode number one");
                modes.add(data);
                data = new ModeData(2, "Mode2", uid, "Mode number two");
                modes.add(data);
                return modes;
            }
        };
    }
}
