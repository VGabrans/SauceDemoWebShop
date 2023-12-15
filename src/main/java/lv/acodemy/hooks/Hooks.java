package lv.workathome.hooks;

import io.cucumber.java.After;
import lv.workathome.utils.LocalDriverManager;

public class Hooks {
    @After
    public static void afterTest() {
        LocalDriverManager.closeDriver();
    }
}


