import java.util.*;

public class Show
{
    private String name;
    private ArrayList<Seat> seats;
    private Integer maxSeatsPerPerson;
    
    public Show(String nameToSet, Integer numberOfSeats, Integer maxSeatsPerPersonToSet)
    {
        name = nameToSet;
        seats = new ArrayList<Seat>();
        createSeats(numberOfSeats);
        maxSeatsPerPerson = maxSeatsPerPersonToSet;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
    
    public ArrayList<Seat> getSeats() {
        return seats;
    }
    
    public ArrayList<Seat> getUnreservedSeats() {
        ArrayList<Seat> unreservedSeats = new ArrayList<Seat>();
        for (Seat s : seats) {
            if (!s.getReserved()) {
                unreservedSeats.add(s);
            }
        }
        return unreservedSeats;
    }

    public Integer getMaxSeatsPerPerson() {
        return maxSeatsPerPerson;
    }
    
    private void createSeats(Integer numberOfSeats) {
        for (int number = 1; number < numberOfSeats + 1; number++) {
            seats.add(new Seat(number));
        }
    }
}
