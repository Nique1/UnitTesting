package testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MealTest {
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
    void referencesToTheSameObjectsShouldBeEqual(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;
        //when

        //then

        assertSame(meal1,meal2);
    }
    @Test
    void referencesToDifferentObjectsShouldNotBeEqual(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);
        //when
        //then
        assertNotSame(meal1,meal2);
    }
    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame(){
        //given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pizza");
        //when
        //then
        assertEquals(meal1,meal2);

    }

}