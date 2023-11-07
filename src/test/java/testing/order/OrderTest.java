package testing.order;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testing.Meal;
import testing.order.Order;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {
    private Order order;

    @BeforeEach
    void initializeOrder(){
        System.out.println("Inside @BeforeEach");
        order = new Order();
    }

    @AfterEach
    void cleanUp(){
        System.out.println("Inside @AfterEach");
        order.clear();
    }
    @Test
    void testAssertArrayEquals() {
        //given
        int[] ints1 = {1, 2, 3};
        int[] ints2 = {1, 2, 3};
        //when
        //then
        assertArrayEquals(ints1,ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder(){
        //given
        //when
        //then
        assertThat(order.getMeals(),empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        MatcherAssert.assertThat(order.getMeals(), emptyCollectionOf(Meal.class));

    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize(){
        //given
        Meal meal = new Meal(20, "Pizza");
        Meal meal2 = new Meal(25, "Pasta");
        //when
        order.addMealToOrder(meal);
        //then
        assertThat(order.getMeals(),hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize(){
        //given
        Meal meal = new Meal(20, "Pizza");
        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);
        //then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(meal)));
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThenToOrder(){
        //given
        Meal meal1 = new Meal(20, "Pizza");
        Meal meal2 = new Meal(25, "Pasta");
        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        //then
        assertThat(order.getMeals(), contains(meal1,meal2));
        assertThat(order.getMeals(), containsInAnyOrder(meal1,meal2));
    }

    @Test
    void testIfTwoMealListAreTheSame(){
        //given
        Meal meal1 = new Meal(20, "Pizza");
        Meal meal2 = new Meal(25, "Pasta");
        Meal meal3 = new Meal(10, "Sandwich");
        //when
        List<Meal> meals1 = Arrays.asList(meal1,meal2,meal3);
        List<Meal> meals2 = Arrays.asList(meal1,meal2,meal3);

        //then
        assertThat(meals1, is(meals2));
    }

    @Test
    void orderTotalPriceShouldNotExceedMaxIntValue(){
        //given
        Meal meal1 = new Meal(Integer.MAX_VALUE,"Pizza");
        Meal meal2 = new Meal(Integer.MAX_VALUE,"Pasta");
        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        //then
        assertThrows(IllegalStateException.class, () -> order.totalPrice());
    }

    @Test
    void emptyOrderTotalPriceShouldEqualZero() {
        //given
        //Order is created id @BeforeEach

        //when
        //then
        assertThat(order.totalPrice(), is(0));
    }

}