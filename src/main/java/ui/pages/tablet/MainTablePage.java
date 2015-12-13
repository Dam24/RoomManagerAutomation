package ui.pages.tablet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/12/15
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainTablePage extends BasePageObject {

    private boolean isResourceInTablet ;

    @FindBy(xpath= "//div[@class='header']")
    private WebElement headerTablePage;

    @FindBy(xpath = "//div[@id='timeline-container']/rm-vis/div")
    private WebElement buttonSchedule;

    @FindBy(xpath= "//div[@class='tile-button-search']")
    private WebElement buttonSearch;


    public MainTablePage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }
    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(buttonSchedule));
    }

    public boolean isResourceInRoomOfTablet(String resourceCustomName){
       if(isPresent(By.xpath("//div[@class='header']//div[@ng-bind='resource.name'][text()='"+resourceCustomName+"']"))) {
           isResourceInTablet=true;
       }
       else{
           isResourceInTablet=false;
       }
        return isResourceInTablet;
    }

    public ScheduleTabletPage clickScheduleButton(){
        buttonSchedule.click();

        return new ScheduleTabletPage();
    }
}