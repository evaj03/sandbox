//package Sandbox;
//
//import Sandbox.CallableTest.CallableTest;
//import ZipFileUtility.ZipFileUtility;
//
//import javax.xml.bind.JAXBException;
//import java.io.IOException;
//import java.nio.file.FileSystems;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class GeneralMain {
//
//    public static void main(String[] args) throws IOException, JAXBException {
//        //ZipFileUtilityStuff();
//        CallableTests();
//    }
//
//
//    public static void CallableTests() {
//        try {
//            CallableTest callableTest = new CallableTest();
//
//            callableTest.testCallableString();
//            callableTest.testCallableBoolean();
//            callableTest.testCallableInt();
//            callableTest.testMyCallable();
//            callableTest.testMyRunnableCallable();
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//
//    public static void ZipFileUtilityStuff() throws IOException, JAXBException {
//        ZipFileUtility zipFileUtility = new ZipFileUtility();
//
//        zipFileUtility.sudoMain();
//    }
//
//
//    private void someStuff() {
//
//        String workingDir = System.getProperty("user.dir");
//
//        System.out.println( "Working Dir [" + workingDir + "]" );
//
//        System.out.println("File Systems Default [" + FileSystems.getDefault() + "]");
//
//        for ( Path path : FileSystems.getDefault().getRootDirectories() ) {
//            System.out.println("File System Root Dirs [" + path.toString() + "]");
//        }
//
//        Path path = FileSystems.getDefault().getPath("logs", "access.log" );
//
//        System.out.println( path.toString() );
//
//        String source = "c:\\Temp";
//
//        Path sourceFile = Paths.get(source);
//
//        System.out.println(sourceFile.toString());
//
//        if (Files.exists(sourceFile)) {
//            System.out.println("File " + source + " exists.");
//        }
//        else {
//            System.out.println("File " + source + " not exists.");
//        }
//
//
//        String source2 = "c:\\Temp\\subA\\subB\\fileNotExist.txt";
//        Path sourceFile2 = Paths.get(source2);
//
//        System.out.println("Filename: " + sourceFile2.getFileName());
//        System.out.println("Directory: " + sourceFile2.getParent());
//
//        String source3 = "c:\\Temp\\subA\\subB\\";
//        Path sourceFile3 = Paths.get(source3);
//
//        System.out.println("Filename: " + sourceFile3.getFileName());
//        System.out.println("Directory: " + sourceFile3.getParent());
//
//
//        System.out.println("Creating Duration Type");
//
//        for (EnumTest.DurationType durationType : EnumTest.DurationType.values()) {
//            System.out.println("Duration Type '" + durationType.getType() + "'");
//            System.out.println("\tHours: "   + durationType.getHours());
//            System.out.println("\tMinutes: " + durationType.getMinutes());
//            System.out.println("\tSeconds: " + durationType.getSeconds());
//        }
//
////        AlarmClock.log("Starting Alarm Clock");
////        AlarmClock alarmClock = new AlarmClock(3, 1, 20);
////        alarmClock.activateAlarmThenStop();
////        AlarmClock.log("Stopping Alarm Clock");
//
//        System.out.println("*****************************************************************");
//        String a = "<sometag>some stuff goes here</sometag><nexttag>next stuff goes here</nexttag>";
//
//        System.out.println("Length: " + a.length());
//        System.out.println("First of: " + a.indexOf("<sometag") + " = " + a.charAt(a.indexOf("<sometag")));
//        System.out.println("Last of: " + a.lastIndexOf("</sometag>") + " = " + a.charAt(a.lastIndexOf("</sometag>")));
//        System.out.println("Last of: " + (a.lastIndexOf("</sometag>") + 9)  + " = " + a.charAt(a.lastIndexOf("</sometag>") + 9));
//
//        String b = a.substring(0, a.indexOf("<sometag"));
//        String c = a.substring(a.lastIndexOf("</sometag>") + 10);
//        String d = b + c;
//
//        System.out.println("D: " + d);
//
//    }
//}
