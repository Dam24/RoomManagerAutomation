package ui.pages;

import ui.BasePageLocations;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/11/15
 * Time: 10:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class LocationInfoPage extends BasePageLocations {
    public LocationAssociationPage goToLocationAssociationPage(){
        return clickResourceAssociationsTab();
    }
}
