import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    void throwExceptionNameNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 10.0, 10.0)
        );
    }
    @Test
    void throwExceptionCorrectMessageNameNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 10.0, 10.0)

        );
        assertEquals("Name cannot be null." , exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void throwExceptionBlankName(String blankName){
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(blankName, 10.0, 10.0));
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void throwExceptionCorrectMessageBlankName(String blankName) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(blankName, 10.0, 10.0)
        );
        assertEquals("Name cannot be blank." , exception.getMessage());
    }
    @Test
    void throwExceptionSpeedIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Name", -10.0, 10.0)
        );
    }
    @Test
    void throwExceptionCorrectMessageSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Name", -10.0, 10.0)

        );
        assertEquals("Speed cannot be negative." , exception.getMessage());
    }
    @Test
    void throwExceptionDistanceIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Name", 10.0, -10.0)
        );
    }
    @Test
    void throwExceptionCorrectMessageDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Name", 10.0, -10.0)
        );
        assertEquals("Distance cannot be negative." , exception.getMessage());
    }
    @Test
    void getName() {
        Horse horse = new Horse("Name", 10, 15);
        assertEquals("Name", horse.getName());
    }
    @Test
    void getSpeed() {
        Horse horse = new Horse("Name", 10, 15);
        assertEquals(10, horse.getSpeed());
    }
    @Test
    void getDistance() {
        Horse horse = new Horse("Name", 10, 15);
        assertEquals(15, horse.getDistance(),"Method should return distance");

        Horse horseWithoutDist = new Horse("Name", 10);
        assertEquals(0, horseWithoutDist.getDistance(),"Method should return 0");
    }

    @Test
    void moveWithGetRandomDouble() {
        try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            new Horse("Name" , 10, 15).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"15.0, 10.0, 0.5, 17.5",
                "5.0, 15.0, 0.6, 18.0"})
    void moveWithGetRandomDoubleRightValue(double speed, double distance, double randomValue, double expectedDist) {
        try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse("Name" , speed, distance);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(randomValue);
            horse.move();
            assertEquals(expectedDist, horse.getDistance());
        }
    }
}