package test;

import com.ynov.managers.UserManager;
import com.ynov.models.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

    @Test(expected=RuntimeException.class)
    public void changePasswordTooShort() {
        try
        {
            String newPassword = "n3w";
            User user = UserManager.loadUserById(1);
            user.changePassword(newPassword);
        }
        catch(RuntimeException re)
        {
            String message = "Password too short.";
            assertEquals(message, re.getMessage());
            throw re;
        }
        fail("Password too short exception did not throw!");
    }

    @Test(expected=RuntimeException.class)
    public void changePasswordNoNumber() {
        try {
            String newPassword = "new-passworD!";
            User user = UserManager.loadUserById(1);
            user.changePassword(newPassword);
        }
            catch(RuntimeException re)
        {
            String message = "Password must have a number.";
            assertEquals(message, re.getMessage());
            throw re;
        }

        fail("Password number exception did not throw!");
    }

    @Test(expected=RuntimeException.class)
    public void changePasswordNoMaj() {
        try {
            String newPassword = "n3w-p4ssw0rd!";
            User user = UserManager.loadUserById(1);
            user.changePassword(newPassword);
        }
        catch(RuntimeException re)
        {
            String message = "Password must have a capital letter.";
            assertEquals(message, re.getMessage());
            throw re;
        }

        fail("Password capital letter exception did not throw!");
    }

    @Test(expected=RuntimeException.class)
    public void changePasswordNoSpecialChar() {
        try {
            String newPassword = "n3wp4ssw0rD";
            User user = UserManager.loadUserById(1);
            user.changePassword(newPassword);
        }
        catch(RuntimeException re)
        {
            String message = "Password must have a special character.";
            assertEquals(message, re.getMessage());
            throw re;
        }
    }

    @Test(expected=RuntimeException.class)
    public void changePasswordWithAccent() {
        try {
            String newPassword = "n3w-pàssw0rD!";
            User user = UserManager.loadUserById(1);
            user.changePassword(newPassword);
        } catch (RuntimeException re) {
            String message = "Password must not have characters with accents.";
            assertEquals(message, re.getMessage());
            throw re;
        }

        fail("Password number exception did not throw!");
    }
}