package ghoul.util;

import java.io.File;

import java.util.Date;
import java.util.Hashtable;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Factory{
    private static final String dir="./projects";
    private static File filesList[];
    private static int pcount=0;
    private static String started="", refreshed="";
    private static Hashtable<String, Integer> pnames=new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> list=new Hashtable<Integer, String>();
    public static void init(){
        Date d=new Date();
        started = d.toString();
        refactor();
    }
    public static void refactor(){
        Date d=new Date();
        refreshed=d.toString();
        File f=new File(dir);
        filesList=f.listFiles();
        System.out.println("\nChecking projects folder.\nIdentifying and adding possible project folders.");
        for(int i=0;i<filesList.length;i++){
            String name=filesList[i].toString();
            if(!filesList[i].isFile()){
                String pname = name.substring(name.lastIndexOf("/")+1);
                File f1=new File(name+"/"+pname+".class");
                File f2=new File(name+"/"+pname+".java");
                /**if(f1.exists()){
                    list.put(pcount, pname);
                    pnames.put(pname, pcount++);
                    System.out.println("Found : "+pname);
                }*/
                if(f2.exists()){
                    System.out.println("Project File found  for "+pname+"\nCompiling...");
                    String file=f2.toString();
                    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                    int compilationRes = compiler.run(null, null, null, file);
                    if(compilationRes == 0)System.out.println("Compiled");
                    else System.out.println("Failed");
                    if(f1.exists()){
                        list.put(pcount, pname);
                        pnames.put(pname, pcount++);
                        System.out.println("Found : "+pname);
                    }
                }
                else{System.out.println("Rejected : "+pname);}
            }
        }
    }
    public static String[] fetchList(){
        String res[]=new String[pcount];
        for(int i=0;i<pcount;i++){res[i]=list.get(i);}
        return res;
    }
    public static void create(String x){
        Thread t=new Thread(new PackageCreator(x));
        System.out.println("Starting creation of project : "+x);
        t.start();
    }
    public static void main(String[] args){init();}
}
