package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 11/10/15
 * Time: 6:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourcePage extends BasePageObject{
    private boolean exitsResource=false;
    private Actions action = new Actions(driver);

    @FindBy(xpath= "//button[@id='btnRemove']/preceding-sibling::button")
    WebElement buttonAddResource;

    @FindBy(id= "btnRemove")
    WebElement buttonRemoveResource;

    @FindBy(xpath= "//input[@ng-model='resourceNameFilter']")
    WebElement inputFilterResource;

    @FindBy(xpath= "//div[@class='ngCell centeredColumn col1 colt1']")
    WebElement columnIconResource;

    @FindBy(xpath= "//div[@class='ngCell centeredColumn col2 colt2']")
    WebElement columnNameResource;

    @FindBy(xpath= "//div[@class='ngCell centeredColumn col3 colt3']")
    WebElement columnCustomNameResource;

    public ResourcePage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(buttonAddResource));
    }

    public boolean existInColumnName(String resourceName){

        if(isPresent(By.xpath("//div[@class='ngCell centeredColumn col2 colt2']//span[text()='"+resourceName+"']"))) {
            exitsResource=true;
        }
        else {
            exitsResource=false;
        }
        return exitsResource;
    }

    public boolean existInColumnCustomName(String resourceCustomName){
        if(isPresent(By.xpath("//div[@class='ngCell centeredColumn col3 colt3']//span[text()='"+resourceCustomName+"']"))) {
            exitsResource=true;
        }
        else {
            exitsResource=false;
        }
        return exitsResource;
    }

    public ResourceInfoPage goToPropertyResource(String resourceName){
        action.moveToElement(columnCustomNameResource.findElement(By.xpath("//span[text()='"+resourceName+"']"))).doubleClick().build().perform();
        columnCustomNameResource.findElement(By.xpath("//span[text()='"+resourceName+"']")).click();
        return new ResourceInfoPage();
    }

    public AddResourcePage clickAddButton(){
        buttonAddResource.click();
        return new AddResourcePage();
    }
}