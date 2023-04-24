import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class HorseTest {
    private static Horse horse;

    @BeforeAll
    public static void initHorseObj(){
        horse = new Horse("TestHorse", 1, 2);
    }

    @Test
    public void nullHorseTest(){
        assertThrows(
                IllegalArgumentException.class,
                () -> { Horse horse = new Horse(null ,1);}
        );
    }

    @Test
    public void nameCannotBeNullErrorMsgTest(){
        Throwable exc = assertThrows(
                IllegalArgumentException.class,
                () -> { Horse horse = new Horse(null ,1);}
        );
        assertEquals("Name cannot be null.", exc.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    public void nameCannotBeBlankErrorMsgTest(String arg){
        Throwable exc = assertThrows(
                IllegalArgumentException.class,
                () -> { Horse horse = new Horse(arg ,1);}
        );
        assertEquals("Name cannot be blank.", exc.getMessage());
    }

    @Test
    public void negativeSpeedHorseTest(){
        assertThrows(
                IllegalArgumentException.class,
                () -> { Horse horse = new Horse("Test" ,-1);}
        );
    }

    @Test
    public void speedCannotBeNegativeErrorMsgTest(){
        Throwable exc = assertThrows(
                IllegalArgumentException.class,
                () -> { Horse horse = new Horse("Test" ,-1);}
        );
        assertEquals("Speed cannot be negative.", exc.getMessage());
    }

    @Test
    public void thirdNegativeParameterHorseTest(){
        assertThrows(
                IllegalArgumentException.class,
                () -> { Horse horse = new Horse("Test" ,1, -1);}
        );
    }

    @Test
    public void distanceCannotBeNegativeErrorMsgTest(){
        Throwable exc = assertThrows(
                IllegalArgumentException.class,
                () -> { Horse horse = new Horse("Test" , 1,-1);}
        );
        assertEquals("Distance cannot be negative.", exc.getMessage());
    }

    @Test
    public void getNameTest(){
        assertEquals("TestHorse", horse.getName());
    }

    @Test
    public void getSpeedTest(){
        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void getDistanceTest(){
        assertEquals(2.5, horse.getDistance());
    }

    @Test
    public void getDistanceFromTwoParamHorseTest(){
        Horse horse2Param = new Horse("Horse", 1.5);
        assertEquals(0, horse2Param.getDistance());
    }

    @Test
    public void verifyRandomInsideMoveMethodTest(){
        try (MockedStatic<Horse> horseMockedStatic =  Mockito.mockStatic(Horse.class)) {
            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    public void verifyMoveFormulaTest(){
        try (MockedStatic<Horse> horseMockedStatic =  Mockito.mockStatic(Horse.class)) {
            //добавляем правило
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(0.5);

            horse.move();
            assertEquals(2.5, horse.getDistance());
        }
    }

}