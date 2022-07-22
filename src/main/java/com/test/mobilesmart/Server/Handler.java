package com.test.mobilesmart.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Handler implements Runnable {

    private final DataInputStream is;
    private final DataOutputStream os;
    private final Server server;
    private final Socket socket;

    public Handler(Socket socket, Server server) throws IOException {
        this.server = server;
        this.socket = socket;
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
        System.out.println("Client handled: ip = " + socket.getInetAddress());

    }

    public void sendMessage(String message) throws IOException {
        os.writeUTF(message);
        os.flush();
    }

    public void run() {
        while (true) {
            System.out.println("Handler: Waiting for message");
            try {
                String message = is.readUTF();
                System.out.println("message: " + message);
                if (message.equals("/getProductName")) {
                    String barcode = is.readUTF();
                    String productName = server.getProductName(barcode);
                    sendMessage(productName);
                    server.productCard.setProductName(productName);
                }
                if (message.equals("/setProductQuantity")) {
                    String quantity = is.readUTF();
                    server.productCard.setProductQuantity(quantity);
                }
                if (message.equals("/setProductDescription")) {
                    String description = is.readUTF();
                    server.productCard.setProductDescription(description);
                    server.createProductCard();
                }
                if (message.equals("quit")) {
                    os.close();
                    is.close();
                    socket.close();
                    System.out.println("client " + socket.getInetAddress() + " disconnected");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




}
