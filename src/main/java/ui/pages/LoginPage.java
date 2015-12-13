package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BaseMainPageObject;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/8/15
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginPage extends BasePageObject {

    @FindBy(id="loginUsername")
    WebElement inputUserName;

    @FindBy(id="loginPassword")
    WebElement inputPassword;

    @FindBy(xpath="//button[@type='submit']")
    WebElement buttonSignIn;

    @FindBy(linkText="sign out")
    WebElement buttonSignOut;

    public LoginPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(buttonSignIn));
    }

    public BaseMainPageObject signIn(String user,String password){
        inputUserName.sendKeys(user);
        inputPassword.sendKeys(password);
        buttonSignIn.click();
        return new BaseMainPageObject();
    }
}