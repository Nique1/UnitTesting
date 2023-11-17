package testing.account;

import java.util.List;

public class AccountRepositoryStub implements AccountRepository {

    @Override
    public List<Account> getAllAccounts() {
        Address address1 = new Address("Kocykowa", "21/3");
        Account account1 = new Account(address1);

        Account account2 = new Account();

        Address address3 = new Address("Limanowska", "31");
        Account account3 = new Account(address3);

        List<Account> listOfAccounts = List.of(account1,account2, account3);

        return listOfAccounts;
    }
}
