package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/7/15
 * Time: 8:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class SidebarMenuPage {
    @FindBy(xpath = "//ul[contains(@class, 'nav-stacked')]")
    private WebElement listMenu;
}
