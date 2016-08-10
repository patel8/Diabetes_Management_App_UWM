package com.example.sagar.diabetesmanagement;
import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * Created by Sagar on 7/26/2016.
 */
public class Activity_Information implements Serializable {


    private String date;
    private String time;
    private String label;
    private String value;
    private String startTime;
    private String endTime;
    private String apxCalory;
    private boolean fasting;
    private long id;

    public Activity_Information()
    {

    }

    public Activity_Information(String date, String time, String label, String value, String startTime, String endTime, String apxCalory) {
        this.date = date;
        this.time = time;
        this.label = label;
        this.value = value;
        this.startTime = startTime;
        this.endTime = endTime;
        this.apxCalory = apxCalory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getApxCalory() {
        return apxCalory;
    }

    public void setApxCalory(String apxCalory) {
        this.apxCalory = apxCalory;
    }

    public void setId(long id)
    {
        this.id = id;
    }
    public long getId()
    {
        return id;
    }

    public boolean fasting()
    {
        return this.fasting;
    }

    public void setFasting(boolean fasting)
    {
        this.fasting = fasting;
    }
    public boolean getFasting()
    {
        return this.fasting;
    }
}
