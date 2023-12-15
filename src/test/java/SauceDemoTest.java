import com.github.javafaker.Faker;
import lv.workathome.utils.ConfigurationProperties;
import lv.workathome.utils.LocalDriverManager;
import lv.workathome.utils.page_objects.CheckOut;
import lv.workathome.utils.page_objects.SaucePageWithItems;
import lv.workathome.utils.page_objects.UserLogIn;
import lv.workathome.utils.page_objects.UserTakeItems;
import lv.workathome.utils.page_objects.error_constants.ErrorLocators;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static lv.workathome.utils.LocalDriverManager.closeDriver;
import static lv.workathome.utils.page_objects.error_constants.ErrorMessage.*;
import static lv.workathome.utils.page_objects.page_proof_text.PageAutorizationText.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class SauceDemoTest {

    WebDriver driver;
    Faker faker;
    UserTakeItems userTakeItems;
    CheckOut checkOut;
    ErrorLocators errorLocators;
    UserLogIn userLogIn;
    SaucePageWithItems saucePageWithItems;
    Wait<WebDriver> wait;

    @BeforeMethod
    public void before() {
        driver = LocalDriverManager.getInstance();
        faker = new Faker();
        checkOut = new CheckOut();
        userTakeItems = new UserTakeItems();
        errorLocators = new ErrorLocators();
        userLogIn = new UserLogIn();
        saucePageWithItems = new SaucePageWithItems();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }


    @Test(description = "User successful login")
    public void UserLogIn() {
        driver.get(ConfigurationProperties.getConfiguration().getString("sauce.demo.url"));
        userLogIn.userlogIn("standard_user", "secret_sauce");
        wait.until(visibilityOfAllElements(saucePageWithItems.getUserSeeItems()));
        Assertions.assertThat(saucePageWithItems.getUserSeeItems()).hasSize(6);


    }
    //Log In
    @Test(description = "User Try LogIn without UserName and Password")
    public void UserWithOutUserPass() {
        driver.get(ConfigurationProperties.getConfiguration().getString("sauce.demo.url"));
        userLogIn.userlogIn("", "");
        Assertions.assertThat(errorLocators.getUserErrorMessage1().getText()).isEqualTo(USER_LOGIN_EMPTY_DATA);

    }

    @Test(description = "User Try LogIn without UserName")
    public void UserWithOutUserName() {
        driver.get(ConfigurationProperties.getConfiguration().getString("sauce.demo.url"));
        userLogIn.userlogIn("", "secret_sauce");
        Assertions.assertThat(errorLocators.getUserErrorMessage1().getText()).isEqualTo(USER_LOGIN_EMPTY_DATA);

    }

    @Test(description = "User Try LogIn without Password")
    public void UserWithOutPassword() {
        driver.get(ConfigurationProperties.getConfiguration().getString("sauce.demo.url"));
        userLogIn.userlogIn("standard_user", "");
        Assertions.assertThat(errorLocators.getUserErrorMessage1().getText()).isEqualTo(USER_LOGIN_EMPTY_PASSWORD);
    }

    @Test(description = "User Try LogIn with Incorrect UserName")
        public void UserLogInWithIncorrectUserName(){
        driver.get(ConfigurationProperties.getConfiguration().getString("sauce.demo.url"));
        userLogIn.ErrorUserName( "secret_sauce");
        Assertions.assertThat(errorLocators.getUserErrorMessage1().getText()).isEqualTo(ERROR_USER_NAME);
    }

    @Test(description = "User Try LogIn with Incorrect Password")
    public void UserLogInWithIncorrectPassword(){
        driver.get(ConfigurationProperties.getConfiguration().getString("sauce.demo.url"));
        userLogIn.ErrorPassword( "standard_user");
        Assertions.assertThat(errorLocators.getUserErrorMessage1().getText()).isEqualTo(ERROR_PASSWORD);
    }

    //Items Storage
    @Test(description = "User Take Items")
    public void UserLogInAndTakeItems() {
        driver.get(ConfigurationProperties.getConfiguration().getString("sauce.demo.url"));
        userLogIn.userlogIn("standard_user", "secret_sauce");
        wait.until(visibilityOfAllElements(saucePageWithItems.getUserSeeItems()));
        Assertions.assertThat(saucePageWithItems.getUserSeeItems()).hasSize(6);
        userTakeItems.TakeItems();
        userTakeItems.ClickShoppingCard();
        Assertions.assertThat(userTakeItems.getYourCard().getText()).isEqualTo(YOU_CARD_TEXT);
        wait.until(visibilityOfAllElements(saucePageWithItems.getUserSeeCardItems()));
        Assertions.assertThat(saucePageWithItems.getUserSeeCardItems()).hasSize(6);
        Assertions.assertThat(userTakeItems.getSauceLabsBackpack().getText()).isEqualTo(SauceLabsBackpack);
        Assertions.assertThat(userTakeItems.getSauceLabsBikeLight().getText()).isEqualTo(SauceLabsBikeLight);
        Assertions.assertThat(userTakeItems.getSauceLabsBoltTShirt().getText()).isEqualTo(SauceLabsBoltTShirt);
        Assertions.assertThat(userTakeItems.getSauceLabsFleeceJacket().getText()).isEqualTo(SauceLabsFleeceJacket);
        Assertions.assertThat(userTakeItems.getSauceLabsOnesie().getText()).isEqualTo(SauceLabsOnesie);
        Assertions.assertThat(userTakeItems.getTheThingsTShirtRed().getText()).isEqualTo(TheThingsTShirtRed);
    }

    @Test(description = "User CheckOut His Items")
    public void UserCheckOutHisItems() {
        driver.get(ConfigurationProperties.getConfiguration().getString("sauce.demo.url"));
        userLogIn.userlogIn("standard_user", "secret_sauce");
        wait.until(visibilityOfAllElements(saucePageWithItems.getUserSeeItems()));
        Assertions.assertThat(saucePageWithItems.getUserSeeItems()).hasSize(6);
        userTakeItems.TakeItems();
        userTakeItems.ClickShoppingCard();
        checkOut.ClickCheckOutButton();
        Assertions.assertThat(checkOut.getCheckOutInfo().getText()).isEqualTo(CHECK_OUT_INFO);
        checkOut.CheckOutFields();
        checkOut.ContinueButton();
        Assertions.assertThat(checkOut.getCheckOutInfo().getText()).isEqualTo(CHECK_OUT_WEV);
        wait.until(visibilityOfAllElements(saucePageWithItems.getUserSeeCardItems()));
        Assertions.assertThat(saucePageWithItems.getUserSeeCardItems()).hasSize(6);
        Assertions.assertThat(userTakeItems.getSauceLabsBackpack().getText()).isEqualTo(SauceLabsBackpack);
        Assertions.assertThat(userTakeItems.getSauceLabsBikeLight().getText()).isEqualTo(SauceLabsBikeLight);
        Assertions.assertThat(userTakeItems.getSauceLabsBoltTShirt().getText()).isEqualTo(SauceLabsBoltTShirt);
        Assertions.assertThat(userTakeItems.getSauceLabsFleeceJacket().getText()).isEqualTo(SauceLabsFleeceJacket);
        Assertions.assertThat(userTakeItems.getSauceLabsOnesie().getText()).isEqualTo(SauceLabsOnesie);
        Assertions.assertThat(userTakeItems.getTheThingsTShirtRed().getText()).isEqualTo(TheThingsTShirtRed);
        checkOut.ClickFinishButton();
        Assertions.assertThat(checkOut.getCheckOutInfo().getText()).isEqualTo(CHECK_OUT_COMPLETE);
        Assertions.assertThat(checkOut.getThankYou().getText()).isEqualTo(THANK_YOU);
        Assertions.assertThat(checkOut.getOrderDisp().getText()).isEqualTo(ORDER_DISP);

    }

    //Check Out Fields !!!!
    @Test(description = "User leave CheckOut Fields Empty ")
    public void UserCheckOutFieldsEmpty() {
        driver.get(ConfigurationProperties.getConfiguration().getString("sauce.demo.url"));
        userLogIn.userlogIn("standard_user", "secret_sauce");
        wait.until(visibilityOfAllElements(saucePageWithItems.getUserSeeItems()));
        Assertions.assertThat(saucePageWithItems.getUserSeeItems()).hasSize(6);
        userTakeItems.TakeItems();
        userTakeItems.ClickShoppingCard();
        checkOut.ClickCheckOutButton();
        Assertions.assertThat(checkOut.getCheckOutInfo().getText()).isEqualTo(CHECK_OUT_INFO);
        checkOut.CheckOutFieldsEmpty("", "", "");
        checkOut.ContinueButton();
        Assertions.assertThat(errorLocators.getErrorEmptyFields().getText()).isEqualTo(CHECK_OUT_EMPTY_FIELDS);

    }

    @Test(description = "User leave CheckOut Field Empty: Name Field !")
    public void UserNameFieldEmpty() {
        driver.get(ConfigurationProperties.getConfiguration().getString("sauce.demo.url"));
        userLogIn.userlogIn("standard_user", "secret_sauce");
        wait.until(visibilityOfAllElements(saucePageWithItems.getUserSeeItems()));
        Assertions.assertThat(saucePageWithItems.getUserSeeItems()).hasSize(6);
        userTakeItems.TakeItems();
        userTakeItems.ClickShoppingCard();
        checkOut.ClickCheckOutButton();
        Assertions.assertThat(checkOut.getCheckOutInfo().getText()).isEqualTo(CHECK_OUT_INFO);
        checkOut.NameFieldEmpty("");
        checkOut.ContinueButton();
        Assertions.assertThat(errorLocators.getErrorEmptyFields().getText()).isEqualTo(CHECK_OUT_EMPTY_FIELDS);


    }

    @Test(description = "User leave CheckOut Field Empty: Last Name Field !")
    public void UserLastNameFieldEmpty() {
        driver.get(ConfigurationProperties.getConfiguration().getString("sauce.demo.url"));
        userLogIn.userlogIn("standard_user", "secret_sauce");
        wait.until(visibilityOfAllElements(saucePageWithItems.getUserSeeItems()));
        Assertions.assertThat(saucePageWithItems.getUserSeeItems()).hasSize(6);
        userTakeItems.TakeItems();
        userTakeItems.ClickShoppingCard();
        checkOut.ClickCheckOutButton();
        Assertions.assertThat(checkOut.getCheckOutInfo().getText()).isEqualTo(CHECK_OUT_INFO);
        checkOut.LastNameFieldEmpty("");
        checkOut.ContinueButton();
        Assertions.assertThat(errorLocators.getErrorEmptyFields().getText()).isEqualTo(CHECK_OUT_LAST_NAME_FIELD);

    }

    @Test(description = "User leave CheckOut Field Empty: Postal Code Field !")
    public void PostalCodeFieldEmpty() {
        driver.get(ConfigurationProperties.getConfiguration().getString("sauce.demo.url"));
        userLogIn.userlogIn("standard_user", "secret_sauce");
        wait.until(visibilityOfAllElements(saucePageWithItems.getUserSeeItems()));
        Assertions.assertThat(saucePageWithItems.getUserSeeItems()).hasSize(6);
        userTakeItems.TakeItems();
        userTakeItems.ClickShoppingCard();
        checkOut.ClickCheckOutButton();
        Assertions.assertThat(checkOut.getCheckOutInfo().getText()).isEqualTo(CHECK_OUT_INFO);
        checkOut.PostalCodeFieldEmpty("");
        checkOut.ContinueButton();
        Assertions.assertThat(errorLocators.getErrorEmptyFields().getText()).isEqualTo(CHECK_OUT_POSTAL_CODE_FIELD);

    }






    @AfterMethod
    public void after() {
        closeDriver();
    }
}