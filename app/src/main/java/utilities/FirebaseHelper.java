package utilities;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import bean.MachineBean;

/**
 * Created by Dikshant Manocha on 14-03-2018.
 */

public class FirebaseHelper {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference dbRefStatus;
    private DatabaseReference dbRefLogs;
    private MachineBean ledSwitch;

    public FirebaseHelper() {

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        dbRefStatus = mFirebaseDatabase.getReference().child("LED_Status");
        dbRefLogs = mFirebaseDatabase.getReference().child("Logs");


    }

    public  void  ledOn(String color, String username, String deviceId)
    {
        ledSwitch=new MachineBean();
        ledSwitch.setColor(color);
        ledSwitch.setStatus("true");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        ledSwitch.setTime(formatter.format(date));
        ledSwitch.setUsername(username);
        ledSwitch.setDeviceId(deviceId);
        dbRefStatus.setValue(ledSwitch);
        dbRefLogs.push().setValue(ledSwitch);
    }



    public  void ledOff(String color,String username,String deviceId)
    {
        ledSwitch=new MachineBean();
        ledSwitch.setColor(color);
        ledSwitch.setStatus("false");


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        ledSwitch.setTime(formatter.format(date));
        ledSwitch.setUsername(username);
        ledSwitch.setDeviceId(deviceId);

        dbRefStatus.setValue(ledSwitch);
        dbRefLogs.push().setValue(ledSwitch);
    }


}
