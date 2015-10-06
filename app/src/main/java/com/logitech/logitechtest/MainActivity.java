package com.logitech.logitechtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.logitech.logitechtest.Adapters.MyAdapter;
import com.logitech.logitechtest.deviceObjects.Device;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String DEVICE_TYPE = "deviceType";
    private String DEVICE_MODEL = "model";
    private String DEVICE_NAME = "name";
    private ListView devicelist;
    private RecyclerView recList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this.devicelist = (ListView) this.findViewById(R.id.list);
        recList = (RecyclerView) findViewById(R.id.deviceList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        Results results = new Results() {
            @Override
            public void onPostResults(String inpuJson) {
                createFromJSON(inpuJson);
            }
        };
        new ParseJson(this, results);
    }

    private void createFromJSON(String inputJson) {
        List<Device> deviceList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(inputJson);
            JSONArray deviceArray = jsonObject.getJSONArray("devices");
            for (int i = 0; i < deviceArray.length(); i++) {
                JSONObject resultobject = deviceArray.getJSONObject(i);
                Device device = new Device();
                device.setDeviceType(resultobject.getString(DEVICE_TYPE));
                device.setModel(resultobject.getString(DEVICE_MODEL));
                device.setName(resultobject.getString(DEVICE_NAME));
                deviceList.add(device);
            }
            displayDeviceList(deviceList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void displayDeviceList(final List<Device> deviceList) {
        /*
        Method 1 Implementation using Array Adater
        ArrayAdapter<Device> adapter = new ArrayAdapter<Device>(this, android.R.layout.simple_list_item_1, deviceList);
        this.devicelist.setAdapter(adapter);*/
        /**
         * Method 2 implementing using Recycler View
         */
        MyAdapter ca = new MyAdapter(this,deviceList);
        ca.SetonItemClickListener(new MyAdapter.onItemClickListerner() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(MainActivity.this,deviceList.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
        recList.setAdapter(ca);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
