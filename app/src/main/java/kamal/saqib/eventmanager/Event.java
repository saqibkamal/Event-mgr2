package kamal.saqib.eventmanager;

import java.util.ArrayList;
import java.util.Date;



/**
 * Created by Dell on 1/13/2018.
 */

public class Event {
    Employee organizer;
    ArrayList<Employee> presenter;
    String date;
    String address;

    public Event(){

    }

    public Employee getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Employee organizer) {
        this.organizer = organizer;
    }

    public ArrayList<Employee> getPresenter() {
        return presenter;
    }

    public void setPresenter(ArrayList<Employee> presenter) {
        this.presenter = presenter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
