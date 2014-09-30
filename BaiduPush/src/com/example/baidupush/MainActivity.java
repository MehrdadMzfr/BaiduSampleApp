package com.example.baidupush;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.microsoft.windowsazure.messaging.NotificationHub;
//import com.baidu.push.example.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	static public NotificationHub mHub;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PushManager.startWork(getApplicationContext(),
                PushConstants.LOGIN_TYPE_API_KEY, ConfigurationSettings.API_KEY);
        
        // Creating notification hub.
        String notificationHubName = "TestnhBaidu";
        //String connectionString = "Endpoint=sb://memoznamespace.servicebus.windows-int.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=1o+bQy5hixqjNz6a8hncgt7c9iPOVRwGkOjurmNC9Ks=";
        
        String connectionString = "Endpoint=sb://testversionbaudisas.servicebus.windows-int.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=404hXawr9YH96QL6J7AIiCApzJJzpX4jAk1cFIYgQ74=";
        
        mHub = new NotificationHub(notificationHubName, connectionString, this);
    }

    static NotificationHub getNotificationHub(){
    	return mHub;
    }
  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
