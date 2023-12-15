package lv.workathome.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lv.workathome.utils.page_objects.CheckOut;
import lv.workathome.utils.page_objects.SaucePageWithItems;
import lv.workathome.utils.page_objects.UserLogIn;
import lv.workathome.utils.page_objects.UserTakeItems;
import lv.workathome.utils.page_objects.error_constants.ErrorLocators;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static lv.workathome.utils.LocalDriverManager.getInstance;
import static lv.workathome.utils.page_objects.page_proof_text.PageAutorizationText.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class AutSteps {

    UserLogIn userLogIn = new UserLogIn();
    CheckOut checkOut = new CheckOut();
    SaucePageWithItems saucePageWithItems = new SaucePageWithItems();
    ErrorLocators errorLocators = new ErrorLocators();
    UserTakeItems userTakeItems = new UserTakeItems();
    WebDriverWait wait = new WebDriverWait(getInstance(), Duration.ofSeconds(5));


    @When("user logs in with credentials: {string} and {string}")
    public void loginPage(String username, String password) {
        userLogIn.userlogIn(username, password);
    }

    @Then("user is authenticated")
    public void userIsAuthenticatedInPage() {
        wait.until(visibilityOfAllElements(saucePageWithItems.getUserSeeItems()));
        Assertions.assertThat(saucePageWithItems.getUserSeeItems()).hasSize(6);

    }

    @Then("user see error message: {string}")
    public void userSeeHisErrorMessage(String errorMessage) {
        Assertions.assertThat(errorLocators.getUserErrorMessage1().getText()).isEqualTo(errorMessage);

    }

    @Then("user take Items")
    public void userTakeItems() {
        userTakeItems.TakeItems();
        userTakeItems.ClickShoppingCard();
    }

    @Then("user check item storage: User Added 6 Items")
    public void userCheckItemStorage() {
        Assertions.assertThat(userTakeItems.getYourCard().getText()).isEqualTo(YOU_CARD_TEXT);
        wait.until(visibilityOfAllElements(saucePageWithItems.getUserSeeCardItems()));
        Assertions.assertThat(saucePageWithItems.getUserSeeCardItems()).hasSize(6);
    }

    @Then("user see his items:{string} {string} {string} {string} {string} {string}")
    public void userSeeHisItems(String a, String b ,String c , String d , String e , String f) {
        Assertions.assertThat(userTakeItems.getSauceLabsBackpack().getText()).isEqualTo(SauceLabsBackpack,a);
        Assertions.assertThat(userTakeItems.getSauceLabsBikeLight().getText()).isEqualTo(SauceLabsBikeLight,b);
        Assertions.assertThat(userTakeItems.getSauceLabsBoltTShirt().getText()).isEqualTo(SauceLabsBoltTShirt,c);
        Assertions.assertThat(userTakeItems.getSauceLabsFleeceJacket().getText()).isEqualTo(SauceLabsFleeceJacket,d);
        Assertions.assertThat(userTakeItems.getSauceLabsOnesie().getText()).isEqualTo(SauceLabsOnesie,e);
        Assertions.assertThat(userTakeItems.getTheThingsTShirtRed().getText()).isEqualTo(TheThingsTShirtRed,f);
    }


    @Then("user select CheckOut Button")
    public void userSelectCheckOutButton() {
        checkOut.ClickCheckOutButton();
        Assertions.assertThat(checkOut.getCheckOutInfo().getText()).isEqualTo(CHECK_OUT_INFO);
    }

    @Then("user input delivery information:{string},{string},{string}")
    public void userInputDeliveryInformation(String UserName, String UserLastName,String UserPostalCode) {
        checkOut.CheckOutFieldsEmpty(UserName,UserLastName,UserPostalCode);
    }

    @Then("user click Continue button")
    public void userClickContinueButton() {
        checkOut.ContinueButton();
    }

    @Then("user click Finish button")
    public void userClickFinishButton() {
        checkOut.ClickFinishButton();
    }

    @Then("user see message in middle of page:{string},{string}")
    public void userSeeMessageInMiddleOfPage(String message1 , String message2) {
        Assertions.assertThat(checkOut.getThankYou().getText()).isEqualTo(THANK_YOU,message1);
        Assertions.assertThat(checkOut.getOrderDisp().getText()).isEqualTo(ORDER_DISP,message2);
    }


    @Then("user make mistake with check out fields: {string},{string},{string}")
    public void userMakeMistakeWithCheckOutFields(String Name, String LastName,String PostalCode) {
        checkOut.CheckOutFieldsEmpty(Name, LastName, PostalCode);
        checkOut.ContinueButton();
    }


    @Then("user see check out error message:{string}")
    public void userSeeCheckOutErrorMessage(String thisError) {
        Assertions.assertThat(errorLocators.getErrorEmptyFields().getText()).isEqualTo(thisError);
    }



}
