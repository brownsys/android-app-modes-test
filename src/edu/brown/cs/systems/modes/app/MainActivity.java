package edu.brown.cs.systems.modes.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import edu.brown.cs.systems.modes.lib.Manager;

public class MainActivity extends Activity {

    private Manager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        manager = new Manager(this);

        super.onCreate(savedInstanceState);
        manager.connectApplication();
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onDestroy() {
        manager.disconnectApplication();
        super.onDestroy();
    }
}
