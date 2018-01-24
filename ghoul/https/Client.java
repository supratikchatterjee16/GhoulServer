package ghoul.https;

import ghoul.main.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;

import java.util.Date;
import java.util.Scanner;

import java.net.Socket;

import javax.net.ssl.SSLSocket;

class Client implements Runnable{
    
    //Section : Client Info
    Socket sock;
    String ip;
    //Section-End
    
    Client(Socket s){sock=s;ip=s.getRemoteSocketAddress().toString();}
    public String[] read(){
    
        //Section : Necessary Information
        int b[]=new int[4];
        BufferedReader br=null;
        String message[]=new String[3];//0 is head 1 is body
        StringBuffer sb=new StringBuffer();
        Scanner sc=null;
        //Section-End
        
        //Section : HEAD reading, Status : Completed
        try{br=new BufferedReader(new InputStreamReader(sock.getInputStream()));}catch(IOException e){e.printStackTrace();}
        boolean flag=true;
        while(flag){
            b[0]=b[1];b[1]=b[2];b[2]=b[3];
            try{b[3]=br.read();}catch(IOException e){flag=false;e.printStackTrace();}
            if(b[0]==b[2]&&b[2]==13&&b[1]==b[3]&&b[3]==10){flag=false;}
            sb.append((char)b[0]);
        }
        message[0]=sb.toString();
        //System.out.println("\n\nFinally : \n"+sb.toString());//Testing Segment
        //Section-End
        
        //Section : BODY reading, Status : Completed
        if(message[0].indexOf("GET")==-1){
            if(message[0].indexOf("POST")!=-1){//Post resolution activity
                message[2]="1";
                String str="";long h=0;
                sc=new Scanner(message[0]);
                while(str.indexOf("Content-Length")==-1)str=sc.nextLine();
                sc=new Scanner(str);
                sc.next();
                h=sc.nextLong();
                sb=new StringBuffer();
                try{for(long i=0;i<h;i++)sb.append((char)br.read());}catch(IOException e){e.printStackTrace();}
                message[1]=sb.toString();
            }
            else if(message[0].indexOf("HEAD")!=-1){
                message[2]="2";
            }
            else if(message[0].indexOf("CONNECT")!=-1){
                message[2]="3";
            }
            else if(message[0].indexOf("OPTIONS")!=-1){
                message[2]="4";
            }
            else if(message[0].indexOf("TRACE")!=-1){
                message[2]="5";
            }
        }
        else{message[2]="0";}
        //Section-End
        return message;
    }
    public String[] query(String[] arr){
        
        //Section : Main logic object
        Main m=new Main(arr,ip);
        String data[]=m.fetchData();
        m=null;
        System.gc();
        //Section-End
        return data;
    }
    public void reply(String[] data){
        //Section : Notes
        //data 0 should contain filename, if there is a file to be sent otherwise, it should have nothing. length = 0
        //data 1 should contain the mimetype to be sent....
        //data 2 should contain the response.
        //System.out.println(data[0]+", "+data[1]+", "+data[2]);//REception testing
        //Section-End
        
        //Section : Necessary Data
        PrintStream pw=null;
        Date d=new Date();
        boolean isFile=false;
        String len="0";
        try{len=Long.toString(data[2].length());}catch(Exception e){}
        File f=null;
        //Section-End
        
        //Section : Send Logic
        System.out.println(data[0]);
        try{
        if(data[0].length()!=0)
            isFile=true;
            }catch(Exception e){return;}
        try{
            pw=new PrintStream(new BufferedOutputStream(sock.getOutputStream()));
            if(isFile){
                f=new File(data[0]);
                if(f.exists())len=Long.toString(f.length());
                else{
                    pw.print("HTTP/1.1 404\r\nFNF\r\n\r\n");
                    System.out.println("Print Stream intialisation failure"+data[0]);
                }
            }
          
            //Section : Headers Settings
            pw.print("HTTP/1.1 200 OK\r\n");
            pw.print("");//Date: Mon,27 July 2009 12:28:53 GMT
            pw.print("Server: GRIMOIRE1\r\n");
            pw.print("");//Last-Modified: Same date format as above
            pw.print("");//E-Tag: Some-gibberish
            pw.print("");//Vary: Authorization,Accept 
            pw.print("Accept-Ranges: bytes\r\n");
            pw.print("Content-Length: "+len+"\r\n");//Content-Length: #
            pw.print("Content-Type: "+data[1]+"\r\n");
            pw.print("Connection: Closed\r\n\r\n");
            //Section-End
            
            //Section : BODY Generator
            if(isFile){
                int n=0;
                InputStream fr=new FileInputStream(data[0]);
                byte b[]=new byte[4096]; 
                while((n=fr.read(b))!=-1){pw.write(b,0,n);}
            }
            else{
                System.out.println(data[2]);
                pw.print(data[2]);
            }
            //Section-End
            
        }catch(IOException e){
            pw.print("Something went wrong.");
            e.printStackTrace();
            System.out.println("\nIn short, PrintStream not initialised");
        }finally{
            pw.close();
        }
        //Section-End
    }
    public void run(){reply(query(read()));}
}
