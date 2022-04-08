package lecture4;

import lecture4.model.Person;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(MainConfiguration.class)
@ActiveProfiles({"PROD"})
public class PersonGreetingProductionProfileTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void testContextLoads() {
        assertNotNull(context);
    }

    @ParameterizedTest
    @MethodSource("greetingsProvider")
    public void testProductionProfilePersonGreeting(String name, String expectedResult)
    {
        Person person = context.getBean(Person.class);
        String greeting = person.great(name);
        assertEquals(expectedResult, greeting);
    }

    private static Stream<Arguments> greetingsProvider() {
        return Stream.of(
                Arguments.of("Ondra", "Hello Ondra"),
                Arguments.of("", "Hello ")
        );
    }
}
