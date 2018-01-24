package ghoul.util;

import java.io.File;
import java.io.IOException;

class PackageCreator implements Runnable{
    private String projectName;
    String[] filePaths,dirs;
    PackageCreator(String str){
        projectName=str;
        String a[]={
            "./projects/"+projectName+"/"+projectName+".java",
            "./projects/"+projectName+"/html/main.html",
            "./projects/"+projectName+"/styles/main.css",
            "./projects/"+projectName+"/scripts/main.js",
            "./projects/"+projectName+"/html/readme.txt",
            "./projects/"+projectName+"/styles/readme.txt",
            "./projects/"+projectName+"/scripts/readme.txt",
            "./projects/"+projectName+"/res/readme.txt"};
        String b[]={
            "./projects/"+projectName,
            "./projects/"+projectName+"/html/",
            "./projects/"+projectName+"/scripts/",
            "./projects/"+projectName+"/styles/",
            "./projects/"+projectName+"/res/"};
        filePaths=a;
        dirs=b;
    }
    public void createSamples(){
        //do nothing for now.
    }
    public void run(){
        File f=new File(filePaths[0]);
        try{
        if(f.exists())System.out.println("Project already exists.");
        for (String a : dirs){
            f=new File(a);
            f.mkdirs();
        }
        for (String a : filePaths){
            f=new File(a);
            f.createNewFile();
        }
        System.out.println("Creation of package "+projectName+" completed.");
        }catch(IOException e){e.printStackTrace();}
        createSamples();
    }
}
