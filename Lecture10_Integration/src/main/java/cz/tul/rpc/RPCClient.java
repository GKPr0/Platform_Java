package cz.tul.rpc;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.URL;

public class RPCClient {

    public static void main(String[] args){
        try {

            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://localhost:80"));
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            int result = (int) client.execute("Calculator.sum", new Object[]{2, 3});
            System.out.println(result);

        } catch (Exception exception) {
            System.err.println("JavaClient: " + exception);
        }
    }
}
