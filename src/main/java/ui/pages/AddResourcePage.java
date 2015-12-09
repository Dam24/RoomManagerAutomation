package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/8/15
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddResourcePage extends BasePageObject {

    @FindBy(xpath= "//form[@class='ng-valid ng-dirty ng-valid-parse']")
    WebElement formAddResource;

    @FindBy(xpath= "//small[@class='inline-error ng-binding']")
    WebElement errorCreateResource;

    public AddResourcePage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(formAddResource));
    }
}
