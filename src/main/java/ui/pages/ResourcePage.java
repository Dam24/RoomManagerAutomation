package ui.pages;

import entities.Resource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BaseMainPageObject;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 11/10/15
 * Time: 6:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourcePage extends BaseMainPageObject{
    private boolean exitsResource=false;
    private Actions action = new Actions(driver);

    @FindBy(xpath= "//button[@id='btnRemove']/preceding-sibling::button")
    private WebElement buttonAddResource;

    @FindBy(id= "btnRemove")
    private WebElement buttonRemoveResource;

    @FindBy(xpath= "//input[@ng-model='resourceNameFilter']")
    private WebElement inputFilterResource;

    @FindBy(xpath= "//div[@class='ngCell centeredColumn col1 colt1']")
    private WebElement columnIconResource;

    @FindBy(xpath= "//div[@class='ngCell centeredColumn col2 colt2']")
    private WebElement columnNameResource;

    @FindBy(xpath= "//div[@class='ngCell centeredColumn col3 colt3']")
    private WebElement columnCustomNameResource;

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

    public boolean moreThatTwoResourceSameName(Resource resource1){
        if((driver.findElements(By.xpath("//div[@class='ngCell centeredColumn col2 colt2']//span[text()='"+resource1.getName()+"']"))).size()==1){
                  exitsResource=false;
        }
        else {
            exitsResource=true;
        }
        return exitsResource;
    }

    public void filterResource(String searchCriteria){
        inputFilterResource.sendKeys(searchCriteria);
    }

    public int numOfResourcesFilter(){
        return driver.findElements(By.xpath("//div[contains(@class,'ng-scope ngRow')]")).size();
    }

    public void CheckOutResource(String resourceName){
        driver.findElement(By.xpath("//div[contains(@class,'ng-scope ngRow')]/div[contains(@class,'col2')]//span[text()='"+resourceName+"']/parent::div/parent::div/parent::div/preceding-sibling::div//input[@type='checkbox']")).click();
    }

    public void deleteResourceByName(String resourceName){
        CheckOutResource(resourceName);
        buttonRemoveResource.click();
    }
}