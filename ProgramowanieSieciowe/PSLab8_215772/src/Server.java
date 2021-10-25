import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) {
        int port = 8080;
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(port),0);
            System.out.println("server started at localhost:"+port);
            httpServer.createContext("/", new MagazynHandler(port));
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
