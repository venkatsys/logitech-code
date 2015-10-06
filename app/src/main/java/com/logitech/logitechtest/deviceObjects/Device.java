package com.logitech.logitechtest.deviceObjects;

/**
 * Created by venkatesanr on 30/09/2015.
 */
public class Device {
    private String deviceType;
    private String model;
    private String name;

    public Device() {
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
