package ghoul.https;

import ghoul.util.Factory;
import ghoul.util.FileManager;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Hashtable;
class Server{
    private int port = 80;
    private final String comList[]={"start","close","exit","help","refactor","list","create"};
    private Hashtable<String, Integer> command=new Hashtable<String, Integer>();
    private Thread manager;
    public Server(){ for(int i=0; i<comList.length; i++)command.put(comList[i], i);}
    public Server(int p){this();port = p;}
    public void changePort(int x){port=x;}
    public void start(){
        manager=new ServerManager(port);
        manager.setName("Manager");
        manager.start();
    }
    public void start(int p){
        this.port = p;
        this.start();
    }
    public void close(){
        System.out.println("Closing down system");
        ServerManager.close();
        return;
    }
    public static void main(String[] args){
        //Section : Variables
        int ctr=0;
        String str="";
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        //Section-End
        Server s=new Server(80);
        /**
        *All systems check codes go here
        */
        
        //Section : Init
        System.out.println("\nWelcome to the Ghoul Server.\nTo help you get started, basic commands are start, exit and help.\nFor other commands refer to the help page.");
        System.out.print("The default port that the server uses is 80.\nRefer to the help page should you want to change it.\n\n");
        Factory.refactor();
        System.out.print("\n\n>>");
        //Section-End
        
        //Section : Console Control code
        while(true){
            try{
            str="";
            str=br.readLine();
            System.out.print(">>");
            String order="",arg="";
            if(str.indexOf(" ")!=-1){
                order=str.substring(0, str.indexOf(" "));
                arg=str.substring(str.indexOf(" ")+1);
            }
            else order=str;
            ctr = s.command.get(order);
            switch(ctr){
                case 0 : {
                    //System Start up code
                    if(arg.length()>0&&Character.isDigit(arg.charAt(0)))s.start(Integer.parseInt(arg));
                    else s.start();    
                }; break;
                case 1 : {
                    //Server shutdown code
                    s.close();
                }; break;
                case 2 : {
                    // System exit code
                    System.out.println("Exiting command line");
                    System.exit(0);
                };break;
                case 3 : {
                    //Help message
                    System.out.print("Help Message\n\n"+FileManager.read("./manuals/help.txt")+">>");
                };break;
                case 4 : {
                    Factory.refactor();
                    System.out.print(">>");
                };break;
                case 5 : {
                    String[] names=Factory.fetchList();
                    System.out.println("");
                    for(int i=0;i<names.length;i++)System.out.println(names[i]);
                    System.out.print(">>");
                }; break;
                case 6 : {
                    Factory.create(arg);
                };break;
                default : System.out.print("Bad argument. Please check again.\n>>");
            }
            }catch(Exception e){System.out.print("Invalid Command\n>>");}
        }
        //Section-End
    }
}
