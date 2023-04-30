import java.awt.*;
import javax.swing.*;
import java.util.*;

public class OnlineTicketingSystem
{
    private JFrame frame;
    private ArrayList<User> users;
    private User user;
    private ArrayList<Show> shows;

    /**
     * Constructor for objects of class OnlineTicketingSystem
     */
    public OnlineTicketingSystem()
    {
        makeFrame();
        setLoginPanel();
        shows = new ArrayList<Show>();
        createShows();
        users = new ArrayList<User>();
        createUsers();
    }
    
    private void login(String username,String password)
    {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                if (u.validatePassword(password)) {
                    user = u;
                    setMainPanel();
                    break;
                }
            }
        }
    }
    
    private void makeFrame()
    {
        frame = new JFrame("Online Ticketing System");
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void createShows()
    {
        shows.add(new Show("sample show", 30, 10));
        shows.add(new Show("another sample show", 35, 15));
    }
    
    private void createUsers()
    {
        users.add(new VenueManagerUser("manager", "manager"));
        CustomerUser customer = new CustomerUser("customer", "customer");
        Show show = shows.get(0);
        ArrayList<Seat> seats = new ArrayList<Seat>();
        seats.add(show.getSeats().get(0));
        seats.add(show.getSeats().get(1));
        customer.addBooking(new Booking(show, seats));
        users.add(customer);
    }

    private void setLoginPanel()
    {
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(5,1));

        JLabel labelusername=new JLabel("username");
        TextField username=new TextField(20);
        
        JLabel labelpassword=new JLabel("password");
        TextField password=new TextField(20);

        JButton login=new JButton("Login");
        login.addActionListener(event -> login(username.getText(), password.getText()));

        panel.add(labelusername);
        panel.add(username);
        panel.add(labelpassword);
        panel.add(password);
        panel.add(login);
        
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 40, 40));
        
        frame.setContentPane(panel);
        frame.pack();
        frame.revalidate();
    }
    
    private void setMainPanel()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,2,25,1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(getShowsPanel());
        
        if (user instanceof CustomerUser) {
            mainPanel.add(getCreateBookingPanel((CustomerUser)user));
            mainPanel.add(getBookingsPanel((CustomerUser)user));
        }
        
        if (user instanceof VenueManagerUser) {
            mainPanel.add(getCreateShowPanel());
        }
        
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.revalidate();
    }
    
    private JPanel getShowsPanel()
    {
        JPanel panel= new JPanel();
        
        DefaultListModel<String> li = new DefaultListModel<>();  
        for (Show s : shows) {
            String showString = new String(
                s.toString() + " " +
                Integer.toString(s.getUnreservedSeats().size()) + "/" +
                Integer.toString(s.getSeats().size()) + " " +
                Integer.toString(s.getMaxSeatsPerPerson())
            );
            li.addElement(showString);
        }
        JList<String> list = new JList<>(li);
        list.setFont(new Font("Arial",Font.PLAIN,16));
        panel.add(list);
        
        return panel;
    }
    
    private JPanel getCreateShowPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7,1));
        
        JLabel labelName=new JLabel("Name");
        TextField name=new TextField(20);
        
        JLabel labelNumberOfSeats=new JLabel("Number of seats");
        TextField numberOfSeats=new TextField(20);
        
        JLabel labelMaxSeatsPerPerson=new JLabel("Max seats per person");
        TextField maxSeatsPerPerson=new TextField(20);

        JButton create=new JButton("Create Show");
        create.addActionListener(event -> 
            {
                shows.add(new Show(
                    name.getText(),
                    Integer.valueOf(numberOfSeats.getText()),
                    Integer.valueOf(maxSeatsPerPerson.getText())
                ));
                setMainPanel();
            }
        );

        panel.add(labelName);
        panel.add(name);
        panel.add(labelNumberOfSeats);
        panel.add(numberOfSeats);
        panel.add(labelMaxSeatsPerPerson);
        panel.add(maxSeatsPerPerson);
        panel.add(create);
        
        return panel;
    }
    
    private JPanel getBookingsPanel(CustomerUser user)
    {
        JPanel panel = new JPanel();
        
        DefaultListModel<String> li = new DefaultListModel<>();  
        for (Booking b : user.getBookings()) {
            String bookingString = new String(
                b.getShow().toString() + ", seats: "
            );
            for (Seat s : b.getSeats()) {
                bookingString+=Integer.toString(s.getNumber()) + " ";
            }
            li.addElement(bookingString);
        }
        JList<String> list = new JList<>(li);
        list.setFont(new Font("Arial",Font.PLAIN,16));
        panel.add(list);
        
        return panel;
    }
    
    private JPanel getCreateBookingPanel(CustomerUser user)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,1));
        
        JLabel labelshow=new JLabel("Show");
        TextField show=new TextField(20);
        
        JLabel labelSeats=new JLabel("seats");
        TextField seats=new TextField(20);

        JButton create=new JButton("Create Booking");
        create.addActionListener(event -> 
            {
                System.out.println(show.getText() + seats.getText());
                setMainPanel();
            }
        );

        panel.add(labelshow);
        panel.add(show);
        panel.add(labelSeats);
        panel.add(seats);
        panel.add(create);
        
        return panel;
    }
}
