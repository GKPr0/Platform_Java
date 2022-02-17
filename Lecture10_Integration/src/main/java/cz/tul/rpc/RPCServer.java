package cz.tul.rpc;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;

public class RPCServer {

    public int sum(int x, int y){
        return x + y;
    }

    public static void main (String [] args){

        try {
            WebServer webServer = new WebServer(80);

            XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();

            PropertyHandlerMapping phm = new PropertyHandlerMapping();
            phm.addHandler("Calculator", RPCServer.class);
            xmlRpcServer.setHandlerMapping(phm);

            webServer.start();

            System.out.println("Started successfully.");
            System.out.println("RPC Server - accepting requests");

        } catch (Exception exception){
            System.err.println("JavaServer: " + exception);
        }

    }
}
