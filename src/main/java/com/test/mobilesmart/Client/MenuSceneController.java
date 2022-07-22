package com.test.mobilesmart.Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuSceneController implements Initializable {
    public Button startButton, browseButton, tempQuitButton, finishButton;
    public AnchorPane startScene;

    private final int PORT = 8189;
    private Socket socket;
    private static DataInputStream is;
    private static DataOutputStream os;

    public static void stop() {
        try {
            os.writeUTF("quit");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    protected void onStartButtonClick() throws Exception {
        AnchorPane pane;
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("BarcodeScene.fxml"));
            pane = loader.load();
            startScene.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
  /*  protected void onBarcodeButtonClick() throws IOException {
        barcodeTextField.setOnAction(this::sendMessage);
        String barcode = barcodeTextField.getText();

        os.writeUTF("/getProductName");
        os.writeUTF(barcode);
        System.out.println("Barcode is sent");

        String message = is.readUTF();
        System.out.println("64: " + message);
//        System.out.println(message);
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("QuantityScene.fxml")));
//        labelProduct.setText(message);
//        Stage stage = (Stage) enterBarcodeButton.getScene().getWindow();
//        stage.setScene(new Scene(root));
        AnchorPane pane;
        try {
            // Initiate the loader for fxml 2. Using this loader, you can get the controller instance of fxml 2
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("QuantityScene.fxml"));
            pane = loader.load();
            startScene.getChildren().setAll(pane);

//            String str = box.getValue();
//            if (str != null && !str.isEmpty()) {
                // Get the label from the controller and set the text.
                ((Controller3) loader.getController()).getLabel().setText("Наименование продукта");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }




    }*/

  /*  private void sendMessage(ActionEvent actionEvent) {
        String message = barcodeTextField.getText();
        try {
            os.writeUTF(message);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        barcodeTextField.clear();
    }*/






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket("localhost", PORT);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());

//            new Thread(() -> {
//                while (true) {
//                    try {
//                        String message = is.readUTF();
//                        System.out.println(message);
//                        if (message.equals("quit")) {
//                            break;
//                        }
////                        Platform.runLater(() -> labelProduct.setText(message));
////                        Platform.runLater(() -> labelProduct.setText("message"));
////                        labelProduct.setText("message");
////                        System.out.println("102:" + message);
////                        Platform.runLater(() -> textProductName.setText("Product Name"));
////                        Platform.runLater(() -> listView.getItems().add(message));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}