package lv.workathome.utils.page_objects;

import lombok.Getter;
import lv.workathome.utils.LocalDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SaucePageWithItems {
    public SaucePageWithItems(){
        WebDriver driver = LocalDriverManager.getInstance();
        PageFactory.initElements(driver,this);
    }
    @Getter
    @FindBy(how = How.CLASS_NAME,className = "inventory_item")
    private List<WebElement> userSeeItems;

    @Getter
    @FindBy(how = How.CLASS_NAME,className = "cart_item")
    private List<WebElement>UserSeeCardItems;

}
