/*
 Event.java
 Store data of an event
 set/get functions of event 
 (set - user input)
 (get - display on screen)
 */

 package MeetUp;

 import java.io.Serializable;

 /*
  * @author yining_yang
  */


 public class Event implements Serializable

 {

    protected String name;
    protected String day;
    protected String time;
    protected String location; //?specific location & address 
    protected int yyyy, mm, dd;
    
    protected String host;     // Not from user input
    protected String going;    // added name if action "join" happened
    
    public Event(String evName, String evDay, String evTime, int evYY,
                 int evMM, int evDD, String evLoc )
    {
      name = evName;
      day = evDay;
      time = evTime;
      yyyy = evYY;
      mm = evMM;
      dd = evDD;
      location = evLoc;
    }


    public String getEvName()
    {
      return name;
    }

    public String getEvDay()
    {
      //return mm, dd, yyyy, day
    }

    public String getEvTime()
    {
      return time;
    }

    public String getHost()
    {
      return host;
    }

    public String getLocation()
    {
      return location;
    }

    public void setEvName( String evName )
    {
      this.name = evName;
    }

    public void setTime( String evTime )
    {
      this.time = evTime;
    }

    public void setDate( int evMM, int evDD, int evYY )
    {
      this.mm = evMM;
      this.dd = evDD;
      this.yy = evYY;
      //this.day = pulled from clendar

    }

    public void setLocation( String evLoc )
    {
      this.location = evLoc;
    }
    


}
