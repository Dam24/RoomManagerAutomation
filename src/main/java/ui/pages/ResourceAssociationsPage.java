package ui.pages;

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

    @FindBy(xpath = "//div[legend[contains(text(),Available)]]/div/")
    @CacheLookup
    WebElement listAvailableResources;

    @FindBy(xpath = "//div[legend[contains(text(),Associated)]]")
    @CacheLookup
    WebElement listAssociatedResources;

    String quantity;

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

    private WebElement searchAddButton(String elementName){

        WebElement addButton=listAvailableResources.findElement(By.xpath("//div/div[div[2][span[contains(text(),'"+elementName+"')]]]"));
        return addButton;
    }






    public String getResourceQuantity(String RoomName){

      quantity= driver.findElement(By.xpath("//div[@class='ngCellText ng-scope col0 colt0']/span[text()='"+RoomName+"']/parent::div/parent::div/parent::div/following-sibling::div/"))
              .getText();
      System.out.println(quantity);
      quantity=quantity.substring(0,1);
      System.out.println(quantity);

       return quantity;

    }

    private ResourceAssociationsPage clickAddResourceToAssociate(String resourceName){

    }

    public ConferenceRoomsPage associateResource(){

    }

}
