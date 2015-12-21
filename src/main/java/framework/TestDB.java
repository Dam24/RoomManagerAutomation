package framework;

import database.DBQuery;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/10/15
 * Time: 9:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestDB {
    public static void main(String ar[]) {
//        System.out.println("ID: "+
//                DBQuery.getInstance()
//                .searchByValue("resourcemodels", "customName", "PC")
//        );

//        System.out.println("RESOURCES: " +
//                DBQuery.getInstance().getResourcesBySearchCriteria("DATADISPLAY").size());
//        System.out.println("DOCUMENTS - "+
//                DBQuery
//                        .getInstance()
//                        .getIdByKey("resourcemodels", "customName", "DATADISPLAY"));
//        DBManager.getInstance().closeMongoDB();

//        System.out.println("ROOM ID - "+DBQuery.getInstance().getIdByKey("rooms", "displayName",
//                CredentialsManager.getInstance()
//                        .getRoomName()));
//        DBManager.getInstance().closeMongoDB();

//        String roomId = DBQuery.getInstance().getRoomIdByName(CredentialsManager.getInstance()
//                .getRoomName());
//        System.out.println("room id - "+roomId);
//        String idMeeting = DBQuery.getInstance().getMeetingIdByName("TBBT #1");
//        System.out.println("meeting id - "+idMeeting);
//
//        System.out.println("ESTA O NO - "+APIManager.getInstance().isMeetingInTheRoom(idMeeting, roomId));


        String outOrder = DBQuery.getInstance().getIdByKey("outoforders", "roomId", "456");



//        //PRUEBA HORA Y MINUTOS
//        String hora = (Calendar.getInstance()
//                .get(Calendar.HOUR_OF_DAY)+":"+
//                Calendar.getInstance()
//                        .get(Calendar.MINUTE)
//        );
//        System.out.println("HORA - "+hora);
//        int min = Calendar.getInstance().get(Calendar.MINUTE);
//        System.out.println("MINUTOS SIN SUMAR - "+min);
//        System.out.println("MINUTOS CON SUMAR - "+
//                (Calendar
//                        .getInstance()
//                        .get(Calendar.MINUTE)+10));
//
//        String minutos = (Calendar.getInstance()
//                .get(Calendar.HOUR_OF_DAY)+":"+
//                (Calendar.getInstance()
//                        .get(Calendar.MINUTE)+10)
//        );
//        System.out.println("CONCATENANDO MINUTOS - "+minutos);
    }
}