package testing.account;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @ParameterizedTest
    @CsvSource({
            "Limanowskiego, 15",
            "Armii Krajowej, 12",
            "'Bolka, Lolka',25"
    })

    void givenAddressesShouldNotBeEmpty(String street, String number){
        assertThat(street, notNullValue());
        assertThat(number, notNullValue());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")

    void givenAddressesFromFileShouldNotBeEmpty(String street, String number){
        assertThat(street, notNullValue());
        assertThat(number, notNullValue());
    }
}