package lv.workathome.step_definitions;

import io.cucumber.java.en.Given;
import lv.workathome.utils.ConfigurationProperties;
import lv.workathome.utils.LocalDriverManager;
import org.openqa.selenium.WebDriver;

public class CommonStepsDefs {


    WebDriver driver = LocalDriverManager.getInstance();

    @Given("user open main page")
    public void open_His_Main_Page() {
        driver.get(ConfigurationProperties.getConfiguration().getString("sauce.demo.url"));
    }



}


