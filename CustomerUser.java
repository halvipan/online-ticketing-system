import java.awt.*;
import javax.swing.*;
import java.util.*;

public class CustomerUser extends User
{
    private ArrayList<Booking> bookings;
    
    public CustomerUser(String username, String password)
    {
        super(username, password);
        bookings = new ArrayList<Booking>();
    }
    
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
    
    public ArrayList<Booking> getBookings() {
        return bookings;
    }
}