public class Seat
{
    private Integer number;
    private Boolean reserved;
    
    public Seat(Integer numberToSet)
    {
        number = numberToSet;
        reserved = false;
    }
    
    public Integer getNumber() {
        return number;
    }
    
    public Boolean getReserved() {
        return reserved;
    }
    
    public void setReserved(Boolean state) {
        reserved = state;
    }
}
