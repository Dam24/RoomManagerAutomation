package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;
import ui.BaseResourcePropertiesPage;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/7/15
 * Time: 8:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceInfoPage extends BaseResourcePropertiesPage {



    @FindBy(xpath= "//div[@id='breadcrumb']/a[1]")
    WebElement buttonResourceInfo;

    public ResourceInfoPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(buttonResourceInfo));
    }


}
