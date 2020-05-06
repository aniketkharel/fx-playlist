package sample.model;

import java.util.Date;

public class ActivitiesModel {
    private String username;
    private Date timeStamp;
    private String description;

    public ActivitiesModel(String username, Date timeStamp, String description) {
        this.username = username;
        this.timeStamp = timeStamp;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
