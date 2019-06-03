package com;
import com.controller.AccountController;
import com.controller.TransferController;
import com.helper.AccountHelper;
import com.helper.TransferHelper;
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;


public class Boot{

    private final static int port = 9997;
    private final static String host="http://localhost/";

    public static void main(String[] args) {
        try {
            URI baseUri = UriBuilder.fromUri(host).port(port).build();
            ResourceConfig config = new ResourceConfig();
            config.register(AccountController.class);
            config.register(TransferController.class);
            HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);

        }catch (Exception e){
            e.printStackTrace();
        }
        // Load Account when start
        // Test Account get
        // Test multiple account gets adding a sleep for one
        //Test Put transfer
        // Test multiple pt transfers and test when one is updating other one should halt.
        // Test post transfer When one is updating get should work
    }

}
