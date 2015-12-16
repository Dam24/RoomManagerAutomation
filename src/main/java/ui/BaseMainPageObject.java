package ui;

import org.openqa.selenium.support.PageFactory;
import ui.pages.HeaderMenuPage;
import ui.pages.LoginPage;
import ui.pages.SidebarMenuPage;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/7/15
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseMainPageObject extends BasePageObject {
    private HeaderMenuPage headerMenu;
    private SidebarMenuPage sidebarMenu;

    public BaseMainPageObject() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
        headerMenu = new HeaderMenuPage();
        sidebarMenu = new SidebarMenuPage();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public LoginPage clickSignOutSuccessfully(){
        return headerMenu.clickSignOutSuccessfully();
    }

    public HeaderMenuPage getHeaderMenu(){
        return headerMenu;
    }

    public SidebarMenuPage getSideBarMenu(){
        return sidebarMenu;
    }
}