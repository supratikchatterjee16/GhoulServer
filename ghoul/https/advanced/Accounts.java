package ghoul.https.advanced;

import java.util.Scanner;

public class Accounts{
    private static String iplist[][][]=new String[256][256][256];
    public Accounts(){}
    public static boolean setAccount(String ip,String account){
        boolean flag=false;
        try{
        String address=ip.substring(0,ip.indexOf(":"));
        Scanner sc=new Scanner(address).useDelimiter(".");
        int f=sc.nextInt(),s=sc.nextInt(),t=sc.nextInt();
        iplist[f][s][t]=account;
        flag=true;}catch(Exception e){e.printStackTrace();}
        return flag;
    }
    public static String getAccount(String ip){
        String str="";
        try{
            String address=ip.substring(0,ip.indexOf(":"));
            Scanner sc=new Scanner(address).useDelimiter(".");
            int f=sc.nextInt(),s=sc.nextInt(),t=sc.nextInt();
            str = iplist[f][s][t];
        }catch(Exception e){e.printStackTrace();}
        return str;
    }
    public static boolean isLinked(String ip){
        boolean flag=false;
        try{
            String address=ip.substring(0,ip.indexOf(":"));
            Scanner sc=new Scanner(address).useDelimiter(".");
            int f=sc.nextInt(),s=sc.nextInt(),t=sc.nextInt();
            if(iplist[f][s][t]!=null)flag=true;
        }catch(Exception e){e.printStackTrace();}
        return flag;
    }
}
