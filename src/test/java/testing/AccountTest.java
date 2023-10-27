package testing;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AccountTest {
    @Test
    void myTest(){
        //given
        Account newAccount = new Account();
        //when

        //then
        assertFalse(newAccount.isActive(), "Account is inactive");
        //hamcrest
//        assertThat(newAccount.isActive(), equalTo(false));
//        assertThat(newAccount.isActive(), Matchers.is(false));

        //assertJ
        assertThat(newAccount.isActive()).isFalse();

    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet(){
        //given
        Account account = new Account();
        //when
        Address address = account.getDefaultDeliveryAddress();
        //then
        assertNull(address);

        //assertJ
        assertThat(address).isNull();
    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet(){
        //given
        Address address = new Address("Krakowska", "12B");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);
        //when
        Address defaultAddress = account.getDefaultDeliveryAddress();
        //then
        assertNotNull(defaultAddress);
    }

    @RepeatedTest(4)
    void newAccountWithNotNullAddressShouldBeActive(){
        //given
        Address address = new Address("Default", "12");
        //when
        Account account = new Account(address);
        //then
        assumingThat(address!= null, () -> assertTrue(account.isActive()));

    }
}