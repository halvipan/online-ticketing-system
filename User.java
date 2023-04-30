import javax.swing.*;

public class User
{
    private String username;
    private String pasword;

    /**
     * Constructor for objects of class User
     */
    public User(String usernameToSet, String passwordToSet)
    {
         username = usernameToSet;
         pasword = passwordToSet;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public Boolean validatePassword(String password)
    {
        return pasword.equals(password);
    }
}