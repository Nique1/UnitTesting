package testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void myTest(){
        //given
        Account newAccount = new Account();
        //when

        //then
        assertFalse(newAccount.isActive(), "Account is inactive");
    }
}