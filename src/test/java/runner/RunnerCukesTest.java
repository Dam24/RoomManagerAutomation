package runner;

import common.CommonMethod;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import framework.BrowserManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ui.PageTransporter;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/8/15
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */

@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        glue={"steps"},
        features = {"src/test/resources/features"},
        monochrome = true)

public class RunnerCukesTest extends AbstractTestNGCucumberTests {
    private static Logger log = Logger.getLogger("RunCukesTest");

    @AfterTest
    public void afterExecution() {
        try {
            if(CommonMethod.theUserIsLogIn()){
                CommonMethod.signOut();
                BrowserManager.getInstance().quitBrowser();
            }
        } catch (Exception e) {
            log.error("Unable to logout after execution", e);
        }
    }

    @BeforeTest
    public void beforeExecution(){
        try {
            CommonMethod.navigateLoginPage();
            CommonMethod.signInToMainPage();
        } catch (Exception e) {
            log.error("Unable to navigate to this page", e);
        }
    }
}