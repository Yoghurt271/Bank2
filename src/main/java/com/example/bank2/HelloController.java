package com.example.bank2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class HelloController {
    //region addElement

    ArrayList<User> userArrayList = new ArrayList<User>();

    @FXML
    private Label labelPas, labelLog, resultEnter;

    @FXML
    private TextField textPas, textLog;

    @FXML
    private Button enter;

    @FXML
    private Pane panelEnter, panelMain, panelReg;
    //endregion

//    public boolean searchUser(){
////        String password, login;
////
////        password = textPas.getText();
////        login = textLog.getText();
////
////        if()
//    }



    @FXML
    protected void ButtonClick() {
        if(textLog.getText().equals("Админ") && textPas.getText().equals("111")){
            panelEnter.setVisible(false);
            panelMain.setVisible(true);
        }
        else {
            resultEnter.setText("Неверный логин или пароль");
        }
    }

    @FXML
    protected void regButtonClick() {
            panelEnter.setVisible(false);
            panelReg.setVisible(true);
    }
}