package com.company;

import com.sun.security.ntlm.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{

    private Socket socket;
    private Main server;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean running;

    public Client(Socket socket, Main server) {
        this.server = server;
        this.socket = socket;
        running = true;
    }

    @Override
    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            while (running){
                String message = in.readUTF();
                System.out.println("*Отладочка* челобитная от клиента: "+message);
            }

        }catch (Exception clientC){
            System.err.println("Клиент слегка поникнувшис=(");
        }
    }
    public void SendMessage(String message){
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            System.err.println("Почтальонъ оказался тунеядецъ");
        }
    }
}
