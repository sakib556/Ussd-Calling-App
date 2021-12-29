package com.example.dial_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.widget.SwitchCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner, spinner2;
    TelephonyManager telephonyManager;
    SwitchCompat aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.bank_spinner);
        spinner2 = (Spinner) findViewById(R.id.media_spinner);
        aSwitch=findViewById(R.id.switchid);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
             //   Boolean switchState = aSwitch.isChecked();
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"On",Toast.LENGTH_SHORT).show();
                    startUssd();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Off",Toast.LENGTH_SHORT).show();
                }
                // true if the switch is in the On position
            }
        });
        spin();
        spin2();
    }

    public void spin() {
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bank_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.....
        spinner.setAdapter(adapter);
    }

    public void spin2() {
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.media_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.....
        spinner2.setAdapter(adapter);
    }

    public void startUssd(){
        String UssdCode = "*247#";

//check if edittext is empty
        if (UssdCode.equalsIgnoreCase("")) {

            Toast.makeText(MainActivity.this, "Please Input ussd code", Toast.LENGTH_SHORT).show();
            return;
        }

//check if its a valid ussd code
        if (UssdCode.startsWith("*") && UssdCode.endsWith("#")) {

//we want to remove the last # from the ussd code as we need to encode it. so *555# becomes *555
            UssdCode = UssdCode.substring(0, UssdCode.length() - 1);

            String UssdCodeNew = UssdCode + Uri.encode("#");
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + UssdCodeNew)));
//request for permission
//            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
//
//            } else {
//dial Ussd code


        //    }


        } else {
            Toast.makeText(MainActivity.this, "Please enter a valid ussd code", Toast.LENGTH_SHORT).show();
        }
    }





}