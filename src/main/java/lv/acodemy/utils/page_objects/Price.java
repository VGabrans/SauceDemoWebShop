package lv.acodemy.utils.page_objects;

import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Price {


    public Price() {
        WebDriver driver = lv.workathome.utils.LocalDriverManager.getInstance();
        PageFactory.initElements(driver, this);
    }


    @Getter
    @FindBy(how = How.XPATH, xpath = "//div[@class='inventory_item_price' and text()='29.99']")
    WebElement price1;

    @Getter
    @FindBy(how = How.XPATH, xpath = "//div[@class='inventory_item_price' and text()='9.99']")
    WebElement price2;

    @Getter
    @FindBy(how = How.XPATH, xpath = "//div[@class='inventory_item_price' and text()='15.99']")
    WebElement price3;

    @Getter
    @FindBy(how = How.XPATH, xpath = "//div[@class='inventory_item_price' and text()='49.99']")
    WebElement price4;

    @Getter
    @FindBy(how = How.XPATH, xpath = "//div[@class='inventory_item_price' and text()='7.99']")
    WebElement price5;

    @Getter
    @FindBy(how = How.XPATH, xpath = "//div[@class='inventory_item_price' and text()='15.99']")
    WebElement price6;

    @Getter
    @FindBy(how = How.CLASS_NAME, className = "summary_subtotal_label")
    WebElement itemTotalPrice;


    @Getter
    @FindBy(how = How.CLASS_NAME, className = "summary_tax_label")
    WebElement itemTotalTax;

    @Getter
    @FindBy(how = How.XPATH,xpath = "//div[@class='summary_info_label summary_total_label' and text()='140.34']")
    WebElement itemTotalEndPrice;

}
