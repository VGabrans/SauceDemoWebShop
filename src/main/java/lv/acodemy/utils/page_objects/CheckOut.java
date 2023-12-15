package lv.workathome.utils.page_objects;

import com.github.javafaker.Faker;
import lombok.Getter;
import lv.workathome.utils.LocalDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CheckOut {
    Faker fakeUser = new Faker();
    public CheckOut(){

        WebDriver driver = LocalDriverManager.getInstance();
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.ID,id = "checkout")
    WebElement checkOutButton;
    @Getter
    @FindBy(how = How.CLASS_NAME,className = "header_secondary_container")
    WebElement checkOutInfo;
    @FindBy(how = How.ID,id = "continue")
    WebElement ContinueButton1;
    @FindBy(how = How.ID,id = "finish")
    WebElement finishButton;

    @Getter
    @FindBy(how = How.CLASS_NAME,className = "complete-header")
    WebElement thankYou;
    @Getter
    @FindBy(how = How.CLASS_NAME,className = "complete-text")
    WebElement OrderDisp;

    @FindBy(how = How.ID,id = "first-name") WebElement nameField;
    @FindBy(how = How.ID,id = "last-name")  WebElement lastNameField;
    @FindBy(how = How.ID,id = "postal-code")WebElement postalCodeField;



    public void ClickCheckOutButton(){
        checkOutButton.click();
    }
    public void CheckOutFields(){
        nameField.sendKeys(fakeUser.name().firstName());
        lastNameField.sendKeys(fakeUser.name().lastName());
        postalCodeField.sendKeys("LV - ", fakeUser.number().digits(4));
    }
    public void CheckOutFieldsEmpty(String name,String lastName, String postalCode){
        nameField.sendKeys(name);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);
    }
    public void NameFieldEmpty(String name){
        nameField.sendKeys(name);
        lastNameField.sendKeys(fakeUser.name().lastName());
        postalCodeField.sendKeys("LV - ", fakeUser.number().digits(4));
    }
    public void LastNameFieldEmpty(String LastName){
        nameField.sendKeys(fakeUser.name().name());
        lastNameField.sendKeys(LastName);
        postalCodeField.sendKeys("LV - ", fakeUser.number().digits(4));
    }
    public void PostalCodeFieldEmpty(String PostalCode){
        nameField.sendKeys(fakeUser.name().name());
        lastNameField.sendKeys(fakeUser.name().lastName());
        postalCodeField.sendKeys(PostalCode);
    }
    public void ContinueButton(){
        ContinueButton1.click();
    }
    public void ClickFinishButton(){
        finishButton.click();
    }
}
