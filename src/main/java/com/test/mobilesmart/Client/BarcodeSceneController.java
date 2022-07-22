package com.test.mobilesmart.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class BarcodeSceneController implements Initializable {
    @FXML
    public TextField barcodeTextField;
    @FXML
    public Button enterBarcodeButton;
    @FXML
    public AnchorPane barcodeScene;

    private Socket socket;
    private static DataInputStream is;
    private static DataOutputStream os;

    private final int PORT = 8189;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            socket = new Socket("localhost", PORT);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
            barcodeTextField.setOnKeyPressed( event -> {
                if( event.getCode() == KeyCode.ENTER ) {
                    try {
                        onBarcodeButtonClick();
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
    protected void onBarcodeButtonClick() throws Exception {
        barcodeTextField.setOnAction(this::sendMessage);
        String barcode = barcodeTextField.getText();

        os.writeUTF("/getProductName");
        os.writeUTF(barcode);
        System.out.println("Barcode is sent");

        String productName = is.readUTF();

        AnchorPane pane;
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("QuantityScene.fxml"));
            pane = loader.load();
            barcodeScene.getChildren().setAll(pane);
            ((QuantitySceneController) loader.getController()).getLabel().setText(productName);
            ((QuantitySceneController) loader.getController()).setProductName(productName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

      public void sendMessage(ActionEvent actionEvent) {
        String message = barcodeTextField.getText();
        try {
            os.writeUTF(message);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        barcodeTextField.clear();
    }

}
