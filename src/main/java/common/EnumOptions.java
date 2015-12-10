package common;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/10/15
 * Time: 2:02 PM
 * To change this template use File | Settings | File Templates.
 */
public enum EnumOptions {
    SERVER ("Email Servers"),
    IMPERSONATION("Impersonation"),
    ROOMS("Conference Rooms"),
    RESOURCES("Resources"),
    LOCATIONS("Locations"),
    TABLETS("Tablets"),
    SETTINGS("Settings");
    public final String option;

    private EnumOptions(String option) {
        this.option = option;
    }
}