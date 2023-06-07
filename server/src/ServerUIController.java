import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class ServerUIController {
    public TextArea txtAreaServer;
    public TextField txtServer;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message= "";

    ServerSocket serverSocket2;
    Socket socket2;
    DataInputStream dataInputStream2;
    DataOutputStream dataOutputStream2;
    String message2= "";

    ServerSocket serverSocket3;
    Socket socket3;
    DataInputStream dataInputStream3;
    DataOutputStream dataOutputStream3;
    String message3= "";

    public void initialize(){
        new Thread(()->{
            try {
                serverSocket = new ServerSocket(3005);
                txtAreaServer.appendText("Server Started !");
                socket = serverSocket.accept();
                txtAreaServer.appendText("\nClient Connected !");
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("finish")){
                    message = dataInputStream.readUTF();
                    txtAreaServer.appendText("\nClient :"+message.trim());

                    dataOutputStream2.writeUTF("Client1 :"+message.trim());
                    dataOutputStream3.writeUTF("Client1 :"+message.trim());

                    dataOutputStream2.flush();
                    dataOutputStream3.flush();

                }
            }catch (Exception e){
               throw new RuntimeException(e);
            }
        }).start();

        new Thread(()->{
            try{
                serverSocket2= new ServerSocket(3001);
                txtAreaServer.appendText("Server Start");
                socket2 = serverSocket2.accept();
                txtAreaServer.appendText("\n Client Start");
                dataInputStream2 = new DataInputStream(socket2.getInputStream());
                dataOutputStream2 = new DataOutputStream(socket2.getOutputStream());

                while (!message2.equals("finish")){
                    message2 = dataInputStream2.readUTF();
                    txtAreaServer.appendText("\nClient2 :"+message2.trim());

                    dataOutputStream.writeUTF("Client2 :"+message2.trim());
                    dataOutputStream3.writeUTF("Client2 :"+message2.trim());

                    dataOutputStream.flush();
                    dataOutputStream3.flush();

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try{
                serverSocket3= new ServerSocket(5001);
                txtAreaServer.appendText("Server Start");
                socket3 = serverSocket3.accept();
                txtAreaServer.appendText("\n Client Start");
                dataInputStream3 = new DataInputStream(socket3.getInputStream());
                dataOutputStream3 = new DataOutputStream(socket3.getOutputStream());

                while (!message3.equals("finish")){
                    message3 = dataInputStream3.readUTF();
                    txtAreaServer.appendText("\nClient3 :"+message3.trim());

                    dataOutputStream.writeUTF("Client3 :"+message3.trim());
                    dataOutputStream2.writeUTF("Client3 :"+message3.trim());

                    dataOutputStream.flush();
                    dataOutputStream2.flush();

                }
                dataInputStream3.close();
                dataOutputStream3.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }

    public void btnServerSendOnAction(ActionEvent actionEvent) throws IOException {
        try {
            dataOutputStream.writeUTF("server :"+txtServer.getText().trim());
            dataOutputStream.flush();


            dataOutputStream2.writeUTF("server :"+txtServer.getText().trim());
            dataOutputStream2.flush();

            dataOutputStream3.writeUTF("server :"+txtServer.getText().trim());
            dataOutputStream3.flush();
            txtServer.clear();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
