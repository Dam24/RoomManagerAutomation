package steps.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import framework.BrowserManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 11/24/15
 * Time: 8:40 PM
 * To change this template use File | Settings | File Templates.
 */


public class GlobalHooks {
    WebDriver webDriver = BrowserManager.getInstance().getDriver();
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenShot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenShot, "image/png");
            System.out.println("the Scenario: "+ scenario.getName()+" Failed!!!!");
        }
    }

}
