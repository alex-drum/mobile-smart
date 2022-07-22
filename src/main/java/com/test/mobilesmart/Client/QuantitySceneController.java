package com.test.mobilesmart.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class QuantitySceneController implements Initializable {
    public Label labelProduct;
    public TextField quantityTextField;
    public Button enterQuantityButton;
    public AnchorPane quantityScene;
    private Socket socket;
    private final int PORT = 8189;
    private static DataInputStream is;
    private static DataOutputStream os;

    private String productName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            socket = new Socket("localhost", PORT);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
            quantityTextField.setOnKeyPressed( event -> {
                if( event.getCode() == KeyCode.ENTER ) {
                    try {
                        onQuantityButtonClick();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void onQuantityButtonClick() throws Exception {
        quantityTextField.setOnAction(this::sendMessage);
        String quantity = quantityTextField.getText();
        System.out.println(quantity);

        os.writeUTF("/setProductQuantity");
        os.writeUTF(quantity);

        AnchorPane pane;
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("DescriptionScene.fxml"));
            pane = loader.load();
            quantityScene.getChildren().setAll(pane);
            ((DescriptionSceneController) loader.getController()).getLabel().setText(productName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(ActionEvent actionEvent) {
        String message = quantityTextField.getText();
        try {
            os.writeUTF(message);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        quantityTextField.clear();
    }

    public final Label getLabel(){
        return labelProduct;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
}
