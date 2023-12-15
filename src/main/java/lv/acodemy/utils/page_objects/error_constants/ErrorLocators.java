package lv.workathome.utils.page_objects.error_constants;

import lombok.Getter;
import lv.workathome.utils.LocalDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.How.XPATH;
public class ErrorLocators {
    public ErrorLocators(){
        WebDriver driver = LocalDriverManager.getInstance();
        PageFactory.initElements(driver,this);
    }

    @Getter
    @FindBy(how = XPATH, xpath = "//h3[@data-test='error']")
    WebElement userErrorMessage1;

    @Getter
    @FindBy(how = XPATH,xpath= "//h3[@data-test='error']")
    WebElement errorEmptyFields;
}
