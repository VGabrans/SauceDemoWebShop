package lv.workathome.utils.page_objects;

import lombok.Getter;
import lv.workathome.utils.LocalDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class UserTakeItems {


    public UserTakeItems(){
        WebDriver driver = LocalDriverManager.getInstance();
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.ID,id = "add-to-cart-sauce-labs-backpack")
    WebElement backPack;
    @FindBy(how = How.ID,id = "add-to-cart-sauce-labs-bike-light")
    WebElement light;
    @FindBy(how = How.ID,id = "add-to-cart-sauce-labs-bolt-t-shirt")
    WebElement shirt;
    @FindBy(how = How.ID,id = "add-to-cart-sauce-labs-fleece-jacket")
    WebElement jacket;
    @FindBy(how = How.ID,id = "add-to-cart-sauce-labs-onesie")
    WebElement onesie;
    @FindBy(how = How.ID,id = "add-to-cart-test.allthethings()-t-shirt-(red)")
    WebElement shirtRed;
    @FindBy(how = How.CLASS_NAME,className = "shopping_cart_link")
    WebElement clickShoppingCard;





    //Added item text Proof
    @Getter
    @FindBy(how = How.CLASS_NAME,className = "header_secondary_container") WebElement YourCard;
    @Getter
    @FindBy(how = How.ID,id = "item_4_title_link") WebElement SauceLabsBackpack;
    @Getter
    @FindBy(how = How.ID,id = "item_0_title_link") WebElement SauceLabsBikeLight;
    @Getter
    @FindBy(how = How.ID,id = "item_1_title_link") WebElement SauceLabsBoltTShirt;
    @Getter
    @FindBy(how = How.ID,id = "item_5_title_link") WebElement SauceLabsFleeceJacket;
    @Getter
    @FindBy(how = How.ID,id = "item_2_title_link") WebElement SauceLabsOnesie;
    @Getter
    @FindBy(how = How.ID,id = "item_3_title_link") WebElement TheThingsTShirtRed;






    public void TakeItems(){
        backPack.click();
        light.click();
        shirt.click();
        jacket.click();
        onesie.click();
        shirtRed.click();
    }
    public void ClickShoppingCard(){
        clickShoppingCard.click();
    }

}
