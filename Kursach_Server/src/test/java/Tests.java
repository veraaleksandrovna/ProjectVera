import Models.Entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class Tests extends Assert {

@Test
    public void LoginTest() throws SQLException{
        User user  = new User(1,"Admin","admin",0);
        assertEquals(1,user.getUserId());
}
    @Test
    public void PasswordTest() throws SQLException{
        User user  = new User(1,"Admin","admin",0);
        assertEquals("admin",user.getPassword());
    }
}
