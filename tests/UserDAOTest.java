package tests;

import com.healthcare.dao.UserDAO;
import com.healthcare.dao.UserDAOImpl;
import com.healthcare.model.User;
import org.junit.Assert;
import org.junit.Test;

public class UserDAOTest {

    private UserDAO userDAO = new UserDAOImpl();

    @Test
    public void testRegisterAndLogin() throws Exception {
        User user = new User("Test User", "testuser@example.com",
                "test123", "PATIENT", "9999999999");
        boolean registered = userDAO.registerUser(user);
        Assert.assertTrue(registered);

        User loggedIn = userDAO.login("testuser@example.com", "test123");
        Assert.assertNotNull(loggedIn);
        Assert.assertEquals("Test User", loggedIn.getName());
    }
}

