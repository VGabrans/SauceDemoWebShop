package lv.workathome.utils.page_objects;

import com.github.javafaker.Faker;
import lv.workathome.utils.LocalDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.How.*;

public class UserLogIn {

    Faker fakeUser = new Faker();
    public UserLogIn(){
        WebDriver driver = LocalDriverManager.getInstance();
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = XPATH, xpath = "//input[@data-test='username']")
    WebElement usernameField;
    @FindBy(how = ID, id = "password")
    WebElement passwordField;
    @FindBy(how = NAME, name = "login-button")
    WebElement loginButton;

    public void userlogIn(String username, String password){
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
    public void ErrorUserName(String password){
        usernameField.sendKeys(fakeUser.name().username());
        passwordField.sendKeys(password);
        loginButton.click();
    }
    public void ErrorPassword(String UserName){
        usernameField.sendKeys(UserName);
        passwordField.sendKeys(fakeUser.internet().password());
        loginButton.click();
    }
}
