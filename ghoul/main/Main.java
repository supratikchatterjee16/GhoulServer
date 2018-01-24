package ghoul.main;

import ghoul.https.advanced.Accounts;
import ghoul.util.Factory;

import java.lang.Class;
import java.lang.ClassLoader;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

import java.io.File;

import java.util.Hashtable;
import java.util.Arrays;
public class Main{

//Section : Data
private String arr[],ip,resp[];
Hashtable<String, String> mime;
Hashtable<Integer, String> methods;
//Section-End

//Section : Main Program
public  Main(){
    mime=new Hashtable<String, String>();
    resp=new String[3];
    mime.put("ico","image/png");
    mime.put("png","image/png");
    mime.put("jpeg","image/jpeg");
    mime.put("jpg","image/jpeg");
    mime.put("html","text/html");
    mime.put("htm","text/html");
    mime.put("css","text/css");
    mime.put("js","text/javascript");
    mime.put("svg","image/svg+xml");
    mime.put("ttf","font/ttf");
    
    methods=new Hashtable<Integer,String>();
    methods.put(0,"doGet");
    methods.put(1,"doPost");
    methods.put(2,"doHead");
    methods.put(3,"doConnect");
    methods.put(4,"doOptions");
    methods.put(5,"doTrace");
    
}
public  Main(String[] a){this();arr=a;}
public  Main(String[] a,String i){this();arr=a;ip=i;}
private synchronized void parse(){
    //System.out.println(arr[0]+"\n\n\n"+arr[1]+"\n\n\n"+arr[2]);
    String path="",mimetype="text/plain",data="";
    String pname="",fpath="";
    path=arr[0].substring(arr[0].indexOf("/"));path=path.substring(0,path.indexOf(" "));
    //Section : Project Identification
    int ctr=0;
    for(int i=0;i<path.length();i++){
        char ch=path.charAt(i);
        if(ch=='/')ctr++;
        if(ctr<=1)pname+=ch;
        else fpath+=ch;
    }
    if(ctr==1){if(pname.indexOf(".")!=-1||pname.length()<=1){fpath=pname;pname="/Sample";}}
    //System.out.println("Project : "+pname+"\npath : "+fpath);
    //Section-End
    
    //Section : Divert to project
    pname=pname.substring(1);
    System.out.println("pname : "+pname);
    String[] classList=Factory.fetchList();
    boolean found=true;
    for(int i=0;i<classList.length;i++){
        if(classList[i].equals(pname)){
            String rootDir="./projects/"+pname+"/";
            ClassLoader cl=Main.class.getClassLoader();
            try{
                Class cls=cl.loadClass("projects."+pname+"."+pname);
                //Constructor cons=cls.getConstructor(String.class,String.class);
                //Method m=cls.getDeclaredMethod("doGet");
                //Object obj1=cons.newInstance(arr[0], arr[1]);
                //Object obj=m.invoke(obj1);
                Constructor[] carr=cls.getConstructors();
                Method m1=cls.getDeclaredMethod("doGet");
                Method m2=cls.getDeclaredMethod("fetchFile");
                Method m3=cls.getDeclaredMethod("fetchMimetype");
                Method m4=cls.getDeclaredMethod("fetchData");
                Object obj1=carr[0].newInstance(arr[0], arr[1]);
                m1.invoke(obj1);
                resp[0]=rootDir+(String)m2.invoke(obj1);
                resp[1]=(String)m3.invoke(obj1);
                resp[2]=(String)m4.invoke(obj1);
            }catch(Exception e){System.out.println("Project loading and execution error for project "+pname);e.printStackTrace();}
            cl=null;
            System.gc();
        }
    }
    //Section-End
    //System.out.println(resp[0]+" "+resp[2]);
}
//Section-End

//Section : Visible Function
public String[] fetchData(){
    parse();
    return resp;
}
//Section-End

//Section : Test
public static void main(String[] args){}
//Section-End
}

