package info.hongshu.quokka.helper;

import info.hongshu.quokka.model.Role;
import info.hongshu.quokka.model.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ReflectionHelperTest {
    @Nested
    public class CopyProperties {
        @Test
        public void should_copy_json_properties(){
            // setup
            Long id = 11l;
            String name = "tom";

            User user = new User();
            user.setName(name);

            Role role = new Role();
            role.setName("finance");
            user.setRoles(Arrays.asList(role));

            User existing = new User();

            // action
            ReflectionHelper.copyProperties(user, existing);

            // assert
            assertEquals(name, existing.getName());
            assertEquals("finance", existing.getRoles().get(0).getName());
        }


    }
}
