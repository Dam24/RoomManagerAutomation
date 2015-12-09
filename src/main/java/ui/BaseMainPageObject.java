package ui;

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
public class BaseMainPageObject {
    private HeaderMenuPage headerMenu;
    private SidebarMenuPage sidebarMenu;

    public BaseMainPageObject() {
        headerMenu = new HeaderMenuPage();
        sidebarMenu = new SidebarMenuPage();
    }

    public LoginPage clickSignOutSuccessfully(){
        System.out.println("Entered to click Sign out");
        return headerMenu.clickSignOutSuccessfully();
    }
}