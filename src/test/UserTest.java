package test;

import com.ynov.managers.UserManager;
import com.ynov.models.User;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void changePassword() {
        String newPassword = "n3w-p4ssw0rD!";
        User user = UserManager.loadUserById(1);
        user.changePassword(newPassword);

        assertEquals(user.getPassword(), newPassword);
    }

    @Test
    public void changePasswordTooShort() {
        String newPassword = "n3w";
        User user = UserManager.loadUserById(1);
        user.changePassword(newPassword);

        fail("Password too short.");
    }

    @Test
    public void changePasswordNoNumber() {
        String newPassword = "new-passworD!";
        User user = UserManager.loadUserById(1);
        user.changePassword(newPassword);

        fail("Password must have a number.");
    }

    @Test
    public void changePasswordNoMaj() {
        String newPassword = "n3w-p4ssw0rd!";
        User user = UserManager.loadUserById(1);
        user.changePassword(newPassword);

        fail("Password must have a capital letter.");
    }

    @Test
    public void changePasswordNoSpecialChar() {
        String newPassword = "n3wp4ssw0rD";
        User user = UserManager.loadUserById(1);
        user.changePassword(newPassword);

        fail("Password must have a special character.");
    }

    @Test
    public void changePasswordWithAccent() {
        String newPassword = "n3w-pàssw0rD!";
        User user = UserManager.loadUserById(1);
        user.changePassword(newPassword);

        fail("Password must not have characters with accents.");
    }
}