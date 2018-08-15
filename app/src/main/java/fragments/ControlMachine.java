package fragments;

import  android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.example.dikshantmanocha.machinecontrolingsystem.R;

import utilities.FirebaseHelper;
import utilities.Tools;

/**
 * Created by Dikshant Manocha on 13-03-2018.
 */

@SuppressLint("ValidFragment")
public class ControlMachine extends Fragment {
    private Context context;
    private ToggleButton red_button,yellow_button,Green_button,allOn_button;
    private String loginedUser;
    private FirebaseHelper firebaseHelper;
    private TelephonyManager telephonyManager;
    private   int  PERMISSION_READ_STATE=1;
    private String deviceID;

    public ControlMachine(Context context,String loginedUser ) {
        this.context = context;
        this.loginedUser=loginedUser;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_machine_control, container, false);
        firebaseHelper=new FirebaseHelper();
        red_button=(ToggleButton)rootView.findViewById(R.id.red_button);
        yellow_button=(ToggleButton)rootView.findViewById(R.id.yellow_button);
        Green_button=(ToggleButton)rootView.findViewById(R.id.Green_button);
        allOn_button=(ToggleButton)rootView.findViewById(R.id.allOn_button);
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        deviceID=telephonyManager.getDeviceId();


        red_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(red_button.isChecked())
                {
                    if(yellow_button.isChecked() && Green_button.isChecked())
                    {
                        allOn();
                    }
                    else if(yellow_button.isChecked())
                    {
                        redYellow();
                    }
                    else if(Green_button.isChecked())
                    {
                        redGreen();
                    }
                    else
                    {
                        red();
                    }

                    firebaseHelper.ledOn("red",loginedUser,deviceID);
                }
                else
                {
                    if(yellow_button.isChecked() && Green_button.isChecked())
                    {
                        greenYellow();
                    }
                    else if(yellow_button.isChecked())
                    {
                        yellow();
                    }
                    else if(Green_button.isChecked())
                    {
                        green();
                    }
                    else
                    {
                        allOff();
                    }
                    firebaseHelper.ledOff("red",loginedUser,deviceID);
                }
            }
        });
        yellow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(yellow_button.isChecked())
                {
                    if(Green_button.isChecked() && red_button.isChecked())
                    {
                        allOn();
                    }
                    else if(Green_button.isChecked())
                    {
                        greenYellow();
                    }
                    else if(red_button.isChecked())
                    {
                        redYellow();
                    }
                    else
                    {
                        yellow();
                    }
                    firebaseHelper.ledOn("yellow",loginedUser,deviceID);
                }
                else
                {
                    if(red_button.isChecked() && Green_button.isChecked())
                    {
                        redGreen();
                    }
                    else if(red_button.isChecked())
                    {
                        red();
                    }
                    else if(Green_button.isChecked())
                    {
                        green();
                    }
                    else
                    {
                        allOff();
                    }
                    firebaseHelper.ledOff("yellow",loginedUser,deviceID);
                }
            }
        });
        Green_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Green_button.isChecked())
                {
                    if(yellow_button.isChecked() && red_button.isChecked())
                    {
                        allOn();
                    }
                    else if(yellow_button.isChecked())
                    {
                        greenYellow();
                    }
                    else if(red_button.isChecked())
                    {
                        redGreen();
                    }
                    else
                    {
                        green();
                    }
                    firebaseHelper.ledOn("green",loginedUser,deviceID);
                }
                else
                {
                    if(red_button.isChecked() && yellow_button.isChecked())
                    {
                        redYellow();
                    }
                    else if(red_button.isChecked())
                    {
                        red();
                    }
                    else if(yellow_button.isChecked())
                    {
                        yellow();
                    }
                    else
                    {
                        allOff();
                    }
                    firebaseHelper.ledOff("green",loginedUser,deviceID);
                }
            }
        });
        allOn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allOn_button.isChecked())
                {
                    allOn();
                    firebaseHelper.ledOn("all",loginedUser,deviceID);
                }
                else
                {
                    allOff();
                    firebaseHelper.ledOff("all",loginedUser,deviceID);
                }
            }
        });



        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Tools.MY_PERMISSIONS_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    deviceID=telephonyManager.getDeviceId();

                } else {

                }
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public  void allOn()
    {
        yellow_button.setChecked(true);
        Green_button.setChecked(true);
        red_button.setChecked(true);
        allOn_button.setChecked(true);

    }
    public void allOff()
    {
        yellow_button.setChecked(false);
        Green_button.setChecked(false);
        red_button.setChecked(false);
        allOn_button.setChecked(false);

    }
    public void red()
    {

        yellow_button.setChecked(false);
        Green_button.setChecked(false);
        allOn_button.setChecked(false);

    }
    public void green()
    {

        yellow_button.setChecked(false);
        red_button.setChecked(false);
        allOn_button.setChecked(false);
    }
    public void yellow()
    {

        red_button.setChecked(false);
        Green_button.setChecked(false);
        allOn_button.setChecked(false);
    }
    public void redGreen()
    {

        yellow_button.setChecked(false);
        allOn_button.setChecked(false);
    }
    public void greenYellow()
    {

        red_button.setChecked(false);
        allOn_button.setChecked(false);
    }
    public void redYellow()
    {

        Green_button.setChecked(false);
        allOn_button.setChecked(false);
    }

}
