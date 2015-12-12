package ui.pages.tablet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/12/15
 * Time: 11:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginTablePage extends BasePageObject {

    @FindBy(xpath= "//input[@id='service-url-input']")
    private WebElement inputServiceUrl;

    @FindBy(xpath= "//input[@id='username']")
    private WebElement inputUserName;

    @FindBy(xpath= "//input[@id='password']")
    private WebElement inputPassword;

    @FindBy(xpath= "//div[contains(@class,'btn-primary')]")
    private WebElement buttonSignIn;

    /*
     New web element that is showed when a valid credentials are entered
     */

    @FindBy(xpath= "//div[@class='btn btn-default toggle-btn']")
    private WebElement buttonSelector;


    @FindBy(xpath= "//div[@class='item-box']")
    private WebElement listConferenceRooms;

    @FindBy(xpath= "//rm-select-item[@Selected='selectedRoom']")
    private WebElement inputSelectedRoom;

    @FindBy(xpath= "//button[contains(@class,'btn btn-primary')]")
    private WebElement buttonStartNow;





    @Override
    public void waitUntilPageObjectIsLoaded() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void sigInToTable(String serverUrl,String user,String password){

        inputServiceUrl.sendKeys(serverUrl);
        inputUserName.sendKeys(user);
        inputPassword.sendKeys(password);
        buttonSignIn.click();
        wait.until(ExpectedConditions.visibilityOf(inputSelectedRoom));

    }

    public MainTablePage selectSomeConferenceRooms(String conferenceRoomName){
        buttonSelector.click();
        listConferenceRooms.findElement(By.xpath("//strong[text()='"+conferenceRoomName+"']/parent::a")).click();
        wait.until(ExpectedConditions.visibilityOf(buttonStartNow));
        buttonStartNow.click();
        return  new MainTablePage();
        //div[@class='item-box']//strong[text()='Floor1Room6']/parent::a
    }

}
