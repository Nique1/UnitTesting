package testing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import testing.order.Order;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    @Disabled
    @Test
    void simulateLargeOrder() {
        //given
        Cart cart = new Cart();
        //when

        //then
        assertTimeout(Duration.ofMillis(4), () -> cart.simulateLargeOrder());
    }
    @Test
    void cartShouldNotBeEmptyAfterAddingOrderToCart(){
        //given
        Order order =  new Order();
        Cart cart = new Cart();
        //when
        cart.addOrderToCart(order);
        //then
        assertThat(cart.getOrders(), anyOf(
           notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        assertThat(cart.getOrders(), allOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        assertAll("A group of assertions",
                () -> assertThat(cart.getOrders(), notNullValue()),
                () -> assertThat(cart.getOrders(), is(not(empty())))
        );


    }
}