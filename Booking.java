import java.util.*;

public class Booking
{
    private Show show;
    private ArrayList<Seat> seats;
    
    public Booking(Show showToSet, ArrayList<Seat> seatsToSet)
    {
        show = showToSet;
        seats = seatsToSet;
        
        for (Seat s1 : show.getSeats()) {
            for (Seat s2 : seatsToSet) {
                if (s1.equals(s2)) {
                    s1.setReserved(true);
                }
            }
        }
    }
    
    public Show getShow() {
        return show;
    }
    
    public ArrayList<Seat> getSeats() {
        return seats;
    }
}
