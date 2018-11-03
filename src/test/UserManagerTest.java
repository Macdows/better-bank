package test;

import com.ynov.managers.UserManager;
import com.ynov.models.User;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class UserManagerTest {

    @Test
    public void saveUser() {
        User user = new User("Test", "Man", "test.man@yes.man", "t3stm4n", "test", "01/10/1987", "0102345678", "156 Test avenue");

        assertEquals(user, UserManager.saveUser(user));

    }

    @Test
    public void loadUser() {
        User user = new User("Test", "Man", "test.man@yes.man", "t3stm4n", "test", "01/10/1987", "0102345678", "156 Test avenue");
        System.out.println("getId " + user.getId());

        assertEquals(user, UserManager.loadUserById(user.getId()));
    }
}