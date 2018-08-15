package bean;


/**
 * Created by Dikshant Manocha on 14-03-2018.
 */

public class MachineBean {

    public String color;
    public String status;
    public String time;
    public String username;
    public String deviceId;

    public MachineBean()
    {}

    public String getColor() {
        return color;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

