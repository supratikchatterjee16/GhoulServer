package projects.Sample;

import projects.Sample.lib.*;
import java.util.Hashtable;
public class Sample{
    /**
    *Note : Making the application classes to be used with this server
    *This is an HTTP server that allows the hosting of java backends
    * However, as is the case with most tools, it comes with a few requirements of it's own
    *There are 6 methods that can be used for HTTP transactions
    * GET, POST, HEAD, CONNECT, OPTIONS and TRACE
    * Read up on them.
    * For most purposes, you would be making use of GET and POST methods.
    * This is an example of how to get the data into your main class and format the data to send.
    */
    String messageHead, messageBody;
    String resp[]=new String[3];
    Hashtable<String,String> mimes=new Hashtable<String,String>();
    public Sample(String a, String b){
        messageHead = a;
        messageBody = b;
    }
    //The appropriate method is selected by the server itself, so you need not bother about it
    //All you must do is perform the task that you wish to perform, in the proper places.
    //Meaning, the get method activities must be in the doGet method and so on.
    //Also, there are ways of sending files inbuilt as you'll understand if you study the doGet method below.
    
    //Section : Notes
        //The functions return type of the format data[] = {filename, mimetype, some random data (which is a String) }
        //data 0 should contain filename, if there is a file to be sent otherwise, it should have nothing. length = 0
        //data 1 should contain the mimetype to be sent....
        //data 2 should contain the response.
        //Neither is a mandatory field. i.e. it can be any permutation from { , , } to {BLAH, BLAH, BLAH}
        //Section-End
    //The next following functions are the functions that return the mandatory bits of information
    //Please use them as such
    public String fetchFile(){
        if(resp[0]==null&&resp[2]==null){
        System.out.println("Absolute exception. System block.");
        System.exit(0);
        }
        System.out.println(resp[0]);
        return resp[0];
        }
    public String fetchMimetype(){return resp[1];}
    public String fetchData(){return resp[2];}
    //The following functions are optional in nature, and depends upon your usage
    //While they're optional, I believe that you would be making extensive use of the doGet and doPost functions.
    public void doGet(){
        System.out.println(messageHead+"\n"+messageBody+"\n");
        String dir=messageHead.substring(messageHead.indexOf(" ")+1), mime="";
        dir=dir.substring(1,dir.indexOf(" ")+1);
        System.out.println(dir);        
        if(dir.equals(" "))dir="pages/main.html";
        mime=Mimes.get(dir.substring(dir.indexOf(".")+1));
        System.out.println(dir);
        resp[0]=dir;
        resp[1]=mime;
        resp[2]=null;
    }
    public void doPost(){}
    public void doHead(){}
    public void doConnect(){}
    public void doOptions(){}
    public void doTrace(){}
}
