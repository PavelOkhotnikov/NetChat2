package com.company;

import javafx.fxml.Initializable;

import javax.swing.text.html.ListView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Ctrl implements Initializable {
    private static final int PORT = 8118;
    public javafx.scene.control.ListView<String> bigText;
    public javafx.scene.control.TextField littleText;
    private DataInputStream in;
    private DataOutputStream out;


    public void send(javafx.event.ActionEvent actionEvent) throws IOException {
        out.writeUTF(littleText.getText());
        out.flush();
        littleText.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            Socket socket = new Socket("localhost", 1705);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            new Thread(()->{
            try {
            while (true){
                    String message = in.readUTF();
                    bigText.getItems().add(message);
                }
                }catch (IOException ioException) {
                System.err.println("Опять незадача");
            }
            }).start();
        }catch (Exception n){
            System.err.println("Проблемы-с, в порту неполадки");
        }
    }
}
