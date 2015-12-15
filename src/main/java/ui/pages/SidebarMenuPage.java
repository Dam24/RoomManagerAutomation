package ui.pages;

import common.EnumOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/7/15
 * Time: 8:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class SidebarMenuPage extends BasePageObject {
    @FindBy(xpath = "//ul[contains(@class, 'nav-stacked')]")
    WebElement listMenu;

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(listMenu));
    }

    public void clickOption(String option){
         driver.findElement(By.xpath("//ul[contains(@class, 'nav-stacked')]/li/a[text()='"+option+"']")).click();
    }

    public ResourcePage clickOptionResource(){
       clickOption(EnumOptions.RESOURCES.option);
        return new ResourcePage();
    }

    public ServerPage clickOptionServer(){
        clickOption(EnumOptions.SERVER.option);
        return new ServerPage();
    }

    public ConferenceRoomsPage goToConferenceRoomsPage(){
        listMenu.findElement(By.xpath("//ul[contains(@class, 'nav-stacked')]/li/a[text()='Conference Rooms']")).click();
        return new ConferenceRoomsPage();
    }

    public LocationsPage goToLocationPage(){
        listMenu.findElement(By.xpath("//ul[contains(@class, 'nav-stacked')]/li/a[text()='Locations']")).click();
        return new LocationsPage();
    }
}
