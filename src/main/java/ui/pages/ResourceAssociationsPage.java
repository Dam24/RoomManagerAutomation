package ui.pages;

import entities.Resource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageConferenceRoom;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/7/15
 * Time: 4:02 PM

 * To change this template use File | Settings | File Templates.
 */
public class ResourceAssociationsPage extends BasePageConferenceRoom {

    String quantity;

    @FindBy(xpath = "//div[legend[contains(text(),'Available')]]")
    @CacheLookup
    WebElement listAvailableResources;

    @FindBy(xpath = "//div[legend[contains(text(),'Associated')]]")
    @CacheLookup
    WebElement listAssociatedResources;

    @FindBy(xpath= "//div[@class='modal-body ng-scope']")
    WebElement bodyResourceAssociated;

    public ResourceAssociationsPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(bodyResourceAssociated));
    }

    private ResourceAssociationsPage clickAddResource(String resourceName){

        WebElement addButton=listAvailableResources.findElement(By.xpath("//div/div[2][span[contains(text(),'"+resourceName+"')]]/following-sibling::div/button"));
        addButton.click();
        return this;
    }
    public ResourceAssociationsPage setQuantityResources(String resourceName, String  quantity){
        WebElement inputQuantity=listAssociatedResources.findElement(By.xpath("//div[div[2][span[contains(text(),'"+resourceName+"')]]]/div/input[@type='text']"));
        inputQuantity.clear();
        inputQuantity.sendKeys(quantity);
        return this;
    }

    public String getResourceQuantity(String RoomName){
      quantity= driver.findElement(By.xpath("//div[@class='ngCellText ng-scope col0 colt0']/span[text()='"+RoomName+"']/parent::div/parent::div/parent::div/following-sibling::div/"))
              .getText();
      System.out.println(quantity);
      quantity=quantity.substring(0,1);
      System.out.println(quantity);
      return quantity;
    }

    public ConferenceRoomsPage associateResource(Resource resource, String quantity){
        clickAddResource(resource.getDisplayName());
        setQuantityResources(resource.getDisplayName(),quantity);
        return clickSaveButton();
    }

}
