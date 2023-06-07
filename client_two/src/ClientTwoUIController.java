import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientTwoUIController {
    public TextArea txtAreaClient;
    public TextField txtClient;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message ="";
    String reply;

    public void initialize(){
        new Thread(()->{
            try{
                socket = new Socket("localhost",3001);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream =  new DataOutputStream(socket.getOutputStream());

                while (!message.equals("finish")){
                    message = dataInputStream.readUTF();
                    txtAreaClient.appendText("\n :"+message.trim());
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void btnSendClientOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtClient.getText().trim());
        reply=txtClient.getText();
        txtAreaClient.appendText("\n\t\t\t\t\t\t\tClient 2 :"+reply);
        dataOutputStream.flush();
        txtClient.clear();
    }

}
