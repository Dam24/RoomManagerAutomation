package ui.pages.tablet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/14/15
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class HeaderTabletPage extends BasePageObject {
    @FindBy(id = "go-home")
    private WebElement linkGoHome;

    @FindBy(id = "go-schedule")
    private WebElement linkGoSchedule;

    @FindBy(id = "go-search")
    private WebElement linkGoSearch;

    public ScheduleTabletPage clickGoScheduleTabletPage() {
        linkGoSchedule.click();
        return new ScheduleTabletPage();
    }

    public MainTablePage clickGoMainTabletPage() {
        linkGoHome.click();
        return new MainTablePage();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(linkGoHome));
    }
}
