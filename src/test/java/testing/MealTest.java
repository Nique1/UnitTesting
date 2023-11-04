package testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test cases for MealTest")
class MealTest {


    //method that is the source of the arguments for parametrizedTest above
    private static Stream<Arguments> createMealsWithNameAndPrice() {
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheesburger", 20)
        );
    }

    //method that is the source of the arguments for parametrizedTest above
    private static Stream<String> createCakeNames() {
        List<String> cakeNames = Arrays.asList("Cheesecake", "Cupcake", "Brownie");
        return cakeNames.stream();
    }

    @Test
    void shouldReturnDiscountedPrice() {
        //given
        Meal meal = new Meal(25);
        //when
        int discountedPrice = meal.getDiscountedPrice(7);
        //then
        assertEquals(18, discountedPrice);

    }

    //porownywanie referencji obiektu
    @Test
    @DisplayName("Object meal1 is equal to object meal2")
    void referencesToTheSameObjectsShouldBeEqual() {
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;
        //when

        //then
        assertSame(meal1, meal2);

        //assertJ
        assertThat(meal1).isSameAs(meal2);
    }


    @Test
    void referencesToDifferentObjectsShouldNotBeEqual() {
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);
        //when
        //then
        assertNotSame(meal1, meal2);
        //hamcrest
//        assertThat(meal1, not(sameInstance(meal2)));
    }

    @Tag("pizza")
    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame() {
        //given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pizza");
        //when
        //then
        assertEquals(meal1, meal2);

    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice() {
        //given
        Meal meal = new Meal(10, "Soup");
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(20));
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 15, 20, 40})
    void mealPricesShouldBeLowerThan50(int price) {
        assertThat(price, lessThan(50));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgersShouldHaveCorrectNameAndPrice(String name, int price) {
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }

    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNamesShouldEndWithCake(String name) {
        assertThat(name, notNullValue());
        assertThat(name, containsString("cake"));
    }

    //Dynamic tests

    private int calculatePrice(int price, int quantity) {
        return price * quantity;
    }

    @Tag("pizza")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices() {
        Order order = new Order();
        order.addMealToOrder(new Meal(20, "Pizza", 4));
        order.addMealToOrder(new Meal(15, "Pasta", 5));
        order.addMealToOrder(new Meal(9, "Cake", 2));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        for (int i = 0; i < order.getMeals().size(); i++) {
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> {
                assertThat(calculatePrice(price, quantity), lessThan(90));
            };
            String name = "Test name:  " + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);
        }
        return dynamicTests;


    }


}