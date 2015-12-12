package ui.pages;

import common.EnumOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BaseMainPageObject;
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
    BaseMainPageObject mainPage=new BaseMainPageObject();

    @FindBy(xpath = "//div[legend[contains(text(),Available)]]/div/")
    @CacheLookup
    WebElement listAvailableResources;

    @FindBy(xpath = "//div[legend[contains(text(),Associated)]]")
    @CacheLookup
    WebElement listAssociatedResources;

    @FindBy(xpath= "//div[@class='modal-footer ng-scope']/div/button[@class='btn-clear']")
    WebElement buttonCancel;

    @FindBy(xpath= "//div[@class='modal-footer ng-scope']/div/button[@class='info']")
    WebElement buttonDelete;

    @FindBy(xpath= "//div[@class='modal-body ng-scope']")
    WebElement bodyResourceAssociated;

    @FindBy(xpath ="//div[@class='list-group']")
    WebElement emailServer;



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

    public ResourcePage deleteButtonConfirm(){
        buttonDelete.click();
        isDisplayed(By.xpath("//div[@class='modal-content']"))  ;
       // wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(buttonDelete)));
       // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='modal-footer ng-scope']/div/button[@class='info']")));
        mainPage.getSideBarMenu().clickOption(EnumOptions.SERVER.option);
        wait.until(ExpectedConditions.visibilityOf(emailServer));
        mainPage.getSideBarMenu().clickOptionResource();
        return new ResourcePage();

    }

}
