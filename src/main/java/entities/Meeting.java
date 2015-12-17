package entities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/13/15
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Meeting {
    private String organizer;
    private String title;
    private String from;
    private String to;
    private String attendees;
    private String body;
    private String _id;

    public void setId(String id) {
        _id = id;
    }

    public void setOrganizer(String newOrganizer) {
        organizer = newOrganizer;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setFrom(String newFrom) {
        from = newFrom;
    }

    public void setTo(String newTo) {
        to = newTo;
    }

    public void setNow(String from, String to) {
        setFrom((Calendar.getInstance()
                        .get(Calendar.HOUR_OF_DAY))+":"+
                (Calendar.getInstance()
                            .get(Calendar.MINUTE))
        );

        setTo((Calendar.getInstance()
                        .get(Calendar.HOUR_OF_DAY))+":"+
                (Calendar.getInstance()
                         .get(Calendar.MINUTE)+5)
        );
    }

    public String getFromExchange() {
        Date localTime = new Date();
        SimpleDateFormat converter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Timestamp times = new Timestamp(localTime.getTime());
        String star = converter.format(times);
        String []change = star.split("T");
        String hour = (Integer.parseInt(from.split(":")[0])+4)+"";
        String min = from.split(":")[1];
        String seg = change[1].split(":")[2];
        return (change[0]+"T"+hour+":"+min+":"+seg);
    }

    public String getToExchange() {
        Date localTime = new Date();
        SimpleDateFormat converter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Timestamp times = new Timestamp(localTime.getTime());
        String star = converter.format(times);
        String []change = star.split("T");
        String hour = (Integer.parseInt(to.split(":")[0])+4)+"";
        String min = (Integer.parseInt(to.split(":")[1])+5)+"";
        String seg = change[1].split(":")[2];
        return (change[0]+"T"+hour+":"+min+":"+seg);
    }

    public void setAttendees(String newAttendees) {
        attendees = newAttendees;
    }

    public void setBody(String newBody) {
        body = newBody;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String setId() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getAttendees() {
        return attendees;
    }

    public String getBody() {
        return body;
    }
}