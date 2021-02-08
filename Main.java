package com.company;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {
    private static final int DEFAULT_PORT = 8118;
    private ConcurrentLinkedDeque<Client> clients;
    public Main(int port){
        clients = new ConcurrentLinkedDeque<>();
        System.out.println("*Отладочка* Вы начали свой путь в порту:"+port);
        try(ServerSocket server = new ServerSocket(port)){
            while (true){
               Socket socket = server.accept();
                System.out.println("*Отладочка* Ветер в спину");
               Client client = new Client(socket,this);
               addClient(client);
                   new Thread(client).start();
            }
        }catch (Exception n){
            System.err.println("Сервер пал смертью храбрых");
        }
    }
    public void addClient(Client client){
        clients.add(client);
    }
    public void removeClient(Client client){
        clients.remove(client);
    }

    public static void main(String[] args) {
        int port=-1;
        if(args!=null&&args.length==1){
            port=Integer.parseInt(args[0]);
        }
        if(port==-1){
            port=DEFAULT_PORT;
        }
    }
}
