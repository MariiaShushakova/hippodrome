import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HippodromeTests {
    static Hippodrome hippodrome;
    static List<Horse> horseList = List.of(
            new Horse("Буцефал", 2.4, 1.0),
            new Horse("Туз Пик", 2.5, 2.0),
            new Horse("Зефир", 2.6, 3.0));

    @BeforeAll
    public static void initHippodromeObj(){
        hippodrome = new Hippodrome(horseList);
    }

    @Test
    public void nullHippodromeTest(){
        assertThrows(
                IllegalArgumentException.class,
                () -> { Hippodrome hippodrome = new Hippodrome(null);}
        );
    }

    @Test
    public void horsesCannotBeNullErrorMsgTest(){
        Throwable exc = assertThrows(
                IllegalArgumentException.class,
                () -> { Hippodrome h = new Hippodrome(null);}
        );
        assertEquals("Horses cannot be null.", exc.getMessage());
    }

    @Test
    public void emptyListHippodromeTest(){
        assertThrows(
                IllegalArgumentException.class,
                () -> { Hippodrome hippodrome = new Hippodrome(new ArrayList<>());}
        );
    }

    @Test
    public void emptyListHorsesCannotBeEmptyErrorMsgTest(){
        Throwable exc = assertThrows(
                IllegalArgumentException.class,
                () -> { Hippodrome h = new Hippodrome(new ArrayList<>());}
        );
        assertEquals("Horses cannot be empty.", exc.getMessage());
    }

    @Test
    public void getHorsesTest(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            horses.add(new Horse("Name"+ i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }


    @Test
    public void verifyMoveTest(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();

        //horses.forEach(Horse::move);
        for (Horse horse:horses) {
            verify(horse).move();
        }
    }

    @Test
    public void verifyGetWinnerTest(){
        Horse winnerHorse = hippodrome.getWinner();
        assertEquals(3.0, winnerHorse.getDistance());
    }

}
