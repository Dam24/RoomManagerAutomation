package steps.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: BrayanRosas
 * Date: 12/15/15
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */

public class FeatureHooks {
    private static String lastFeature;
    private static String currentFeature;
    private static Map<String, IHookMethod> featuresHooksMap = new HashMap<String, IHookMethod>();
    final static Logger logger = Logger.getLogger(FeatureHooks.class);
    private static boolean featureFlag = false;
//    private static boolean FeatureFlag2=false;
//    private static boolean featureFlag3=false;



    //****************************************************************
    //Hooks for any scenario
    //****************************************************************
    @Before(order=4)
    public void beforeFeature(Scenario scenario) throws SQLException, ClassNotFoundException {
        logger.info("Start scenario: " + scenario.getName());

        //To run the after hook methods in the last scenario of the features
        currentFeature = scenario.getId().split(";")[0];
        System.out.println(currentFeature);
        if (!currentFeature.equals(lastFeature)) {
            executeAfterHookMethod();
            lastFeature = currentFeature;
            featureFlag = false;
        }
    }

    public static void executeAfterHookMethod() {

        //Run the after hook method if the (feature, after hook method) was added to the map
        if (featuresHooksMap.get(lastFeature) != null) {
            featuresHooksMap.get(lastFeature).executeMethod();
        }
    }

    //****************************************************************
    //Hooks for @Resources feature
    //****************************************************************
    @Before("@Resources")
    public void beforeResourcesFeature() {
      featuresHooksMap.put("resources", () -> afterResourceFeature());       //Check the name of the feature obtained to use the same value as key
        if(!featureFlag){
            SetUpResources.beforeResourceFeature();
            featureFlag = true;
        }
    }

    public void afterResourceFeature() {
      SetUpResources.afterResourceFeature();
    }

    @Before("@Meetings")
    public void beforeMeetingsFeature() {
        featuresHooksMap.put("meetings", () -> afterMeetingsFeature());
        if (!featureFlag) {
            SetUpMeetings.beforeMeetingFeature();
            featureFlag = true;
        }
    }

    public void afterMeetingsFeature() {
        SetUpMeetings.afterMeetingsFeature();
    }

}