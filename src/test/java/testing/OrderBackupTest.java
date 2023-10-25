package testing;

import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OrderBackupTest {
    private static OrderBackup orderBackup;

    @BeforeAll
    //method needs to static!!
    static void setup() throws FileNotFoundException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @BeforeEach
    void appendAtTheStartOfTheFile() throws IOException {
        orderBackup.getWriter().append("New order: ");
    }

    @Test
    void backupOrderWithOneMeal() throws IOException {
        //given
        Meal meal = new Meal(8, "Fries");
        Order order = new Order();
        order.addMealToOrder(meal);
        //when
        orderBackup.backupOrder(order);
        //then
        System.out.println("Order: " + order.toString() + " backed up");
    }
    
    @AfterEach
    void appendAtTheEndOfTheFile() throws IOException {
        orderBackup.getWriter().append("End of the order");
    }

    @AfterAll
    static void tearDown() throws IOException {
        orderBackup.closeFile();
    }

}