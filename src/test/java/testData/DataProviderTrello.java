package testData;

import org.testng.annotations.DataProvider;

public class DataProviderTrello {

    @DataProvider(name = "firstTestProvider")
    public static Object[][] firstTestProvider() {
        String login_user = "testtesttest@mail.com";
        String password_user = "12345678";

        String login_manager = "yuliia@test.com";
        String password_manager = "12345678";

        return new Object[][]{{login_user, password_user}, {login_manager, password_manager}};
    }
}
