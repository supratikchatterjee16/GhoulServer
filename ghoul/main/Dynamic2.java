//For testing purposes only.
package ghoul.main;
import java.util.*;
import java.net.*;
import java.lang.*;
import java.lang.reflect.*;
import java.io.*;
class Dynamic2{
    public static void main(String[] args){
        try{
            ClassLoader cl=Dynamic2.class.getClassLoader();
            Class cls=cl.loadClass("projects.Sample.Sample");
            System.out.println("Class found\t: "+cls.getName());
            System.out.println("Package\t\t : "+cls.getPackage());
        }catch(ClassNotFoundException e){}
    }
}
