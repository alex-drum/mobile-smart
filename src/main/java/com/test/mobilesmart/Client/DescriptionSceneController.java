package com.test.mobilesmart.Client;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class DescriptionSceneController implements Initializable {
    public Label getLabel() {
        return labelProduct;
    }

    public Label labelProduct;
    public TextField descriptionTextField;
    public Button enterDescriptionButton;

    private Socket socket;
    private final int PORT = 8189;
    private static DataInputStream is;
    private static DataOutputStream os;

    public void onDescriptionButtonClick() throws Exception {
        descriptionTextField.setOnAction(this::sendMessage);
        String quantity = descriptionTextField.getText();
        System.out.println(quantity);

        os.writeUTF("/setProductDescription");
        os.writeUTF(quantity);
    }

    public void sendMessage(ActionEvent actionEvent) {
        String message = descriptionTextField.getText();
        try {
            os.writeUTF(message);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        descriptionTextField.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            socket = new Socket("localhost", PORT);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
            descriptionTextField.setOnKeyPressed( event -> {
                if( event.getCode() == KeyCode.ENTER ) {
                    try {
                        onDescriptionButtonClick();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
