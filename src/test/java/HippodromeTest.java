import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class HippodromeTest {
    @Test
    void throwExceptionForNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
    }
    @Test
    void throwExceptionCorrectMessageForNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)

        );
        assertEquals("Horses cannot be null." , exception.getMessage());
    }
    @Test
    void throwExceptionBlankArgs(){
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList()));
    }
    @Test
    void throwExceptionCorrectMessageBlankArgs() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty." , exception.getMessage());
    }
    @Test
    void getHorsesReturnCorrectList() {
        List<Horse> horses = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            horses.add(new Horse("Horse " + i, i, i * 10));
        }
            Hippodrome hippodrome = new Hippodrome(horses);
            assertEquals(horses, hippodrome.getHorses());
    }
    @Test
    void moveAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse :horses){
            verify(horse).move();
        }
    }
    @Test
    void getHorseWithBiggestDistance() {
        Horse horse1 = new Horse("Name1", 10, 5);
        Horse horse2 = new Horse("Name2", 15, 2);
        Horse horse3 = new Horse("Name3", 18, 1);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3));
        assertSame(horse1, hippodrome.getWinner());
    }
}