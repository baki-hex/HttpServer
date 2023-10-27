package in.mate.tango;

import java.net.*;
import java.io.*;
public class Serve{
    
    public static void sever(int port) throws IOException{
    ServerSocket ss = new ServerSocket(port);
    while( ss.isBound() && !ss.isClosed()){
    System.out.println("apache Started..../");
        Socket s = ss.accept();
    InputStream in =  s.getInputStream();
    OutputStream ot = s.getOutputStream();
    
 RequestParser.parser(in);
  
   String post = "";

switch(RequestParser.requestmethod){

    case "GET" :
        post ="HTTP/1.1 200 OK\n\rContent-length:"  + "html.getBytes().length\n\r\n\r" + "<html><head></head><body>GET method accepted</body></html>" +"\n\r\n\r" ;
    break;
    case "HEAD" :
     post = "HTTP/1.1 200 OK\n\rContent-length:"  + post.getBytes().length + "\n\r\n\r \n\r\n\r" ;
    break;
    default : 
     post = "HTTP/1.1 200 OK\n\rContent-length:"  + "html.getBytes().length\n\r\n\r" + "<html>><head></head> <body><h1> Method not allowed </h1></body></html>" +"\n\r\n\r" ;
}

switch(RequestParser.protocolversion){
    case "1.1" :
    break;
    default :
    post = "HTTP/1.1 200 OK\n\rContent-length:"  + "html.getBytes().length\n\r\n\r" + "<html>><head></head> <body><h1> HTTP version unkown</h1></body></html>" +"\n\r\n\r" ;
}

 ot.write(post.getBytes());
    ot.close();
    in.close();
    s.close(); 
}
ss.close();  

    }
}
