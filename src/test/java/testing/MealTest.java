package testing;

import org.junit.jupiter.api.Test;

//import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
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

        //assertJ
        assertThat(meal1).isSameAs(meal2);
    }
    @Test
    void referencesToDifferentObjectsShouldNotBeEqual(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);
        //when
        //then
        assertNotSame(meal1,meal2);
        //hamcrest
//        assertThat(meal1, not(sameInstance(meal2)));
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

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice(){
        //given
        Meal meal = new Meal(10, "Soup");
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(20));
    }

}