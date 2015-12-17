package common;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/17/15
 * Time: 9:16 AM
 * To change this template use File | Settings | File Templates.
 */
public enum EnumKeyMeeting {



    MEETING_kEY ("meetings","organizer","title","start","end","location","roomEmail","resources","attendees","_id","serviceId");

    public final String nameCollection;
    public final String organizer;
    public final String title;
    public final String start;
    public final String end;
    public final String location;
    public final String roomEmail;
    public final String resources;
    public final String attendees;
    public final String _id;
    public final String serviceId;


  private EnumKeyMeeting(String nameCollection, String organizer, String title, String start, String end,String location,String roomEmail,String resources,String attendees,String _id,String serviceId){
      this.nameCollection=nameCollection;
      this.organizer=organizer;
      this.title=title;
      this.start=start;
      this.end=end;
      this.location=location;
      this.roomEmail=roomEmail;
      this.resources=resources;
      this.attendees=attendees;
      this._id=_id;
      this.serviceId=serviceId;
    }
}
