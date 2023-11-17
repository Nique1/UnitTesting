package testing.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
    @Test
    void getAllActiveAccounts(){
        //given
        List<Account> accounts = prepareAccountData();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(accounts);
        //when
        List<Account> accountList = accountService.getAllActiveAccounts();
        //then
        assertThat(accountList, hasSize(2));
    }
    @Test
    void getNoActiveAccounts(){
        //given
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(Arrays.asList());
        //when
        List<Account> accountList = accountService.getAllActiveAccounts();
        //then
        assertThat(accountList, hasSize(0));
    }

    private List<Account> prepareAccountData(){
        Address address1 = new Address("Kocykowa", "21/3");
        Account account1 = new Account(address1);

        Account account2 = new Account();

        Address address3 = new Address("Limanowska", "31");
        Account account3 = new Account(address3);

        List<Account> listOfAccounts = List.of(account1,account2, account3);

        return listOfAccounts;
    }


}
