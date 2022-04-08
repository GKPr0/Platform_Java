package lecture4;

import lecture4.model.Person;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(MainConfiguration.class)
@ActiveProfiles({"TEST"})
public class PersonGreetingTestProfileTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void testContextLoads() {
        assertNotNull(context);
    }

    @ParameterizedTest
    @CsvSource(value = {"Ondra:Hi Ondra", "'':'Hi '"}, delimiter = ':')
    public void testTestProfilePersonGreeting(String name, String expectedResult)
    {
        Person person = context.getBean(Person.class);
        String greeting = person.great(name);
        assertEquals(expectedResult, greeting);
    }
}
