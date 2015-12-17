package entities;

import java.util.Calendar;

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
                        .get(Calendar.HOUR_OF_DAY)+1)+":"+
                    Calendar.getInstance()
                            .get(Calendar.MINUTE)
        );

        setTo((Calendar.getInstance()
                        .get(Calendar.HOUR_OF_DAY)+1)+":"+
                (Calendar.getInstance()
                         .get(Calendar.MINUTE)+5)
        );
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