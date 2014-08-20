import org.junit.Test;
import ru.aksndr.model.User;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by aksndr on 11.08.14.
 */
public class UserTest {

    public final static String FIRSTNAME = "Ivan";
    public final static String LASTNAME = "Ivanov";
    public final static Long FLATID = 32L;


    @Test
    public static User testUser() throws Exception {
        User user = User.create().withFirstname(FIRSTNAME).withLastname(LASTNAME).build();
        assertEquals(user.getFirstname(), FIRSTNAME);
        assertEquals(user.getLastname(), LASTNAME);
        assertEquals(user.getFlatid(), FLATID);
        assertTrue(user.getId() == 0);
        return user;
    }
}
