package com.example.bank2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;

public class HelloController implements Initializable {
    //region addElement
    String url = "jdbc:mysql://localhost:3306/forBank";
    String user = "root";
    String password = "8910098-0m";

    private Connection connection;


    ArrayList<User> userArrayList = new ArrayList<User>();

    @FXML
    private Label labelPas, labelLog, resultEnter;

    @FXML
    private TextField textPas, textLog, regLog, regPass, regLastName, regFirstName, regDadName;

    @FXML
    private Button enter;

    @FXML
    private ComboBox regPol;

    @FXML
    private DatePicker regBirthday;

    @FXML
    private Pane panelEnter, panelMain, panelReg;
    //endregion




    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLException: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Подключение к базе данных успешно установлено!");
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных:");
            printSQLException(e);
        }

        regPol.getItems().addAll("Мужской", "Женский");
        regPol.getSelectionModel().select("Мужской");
    }

    @FXML
    protected void ButtonClick() {
        if (textLog.getText().equals("Админ") && textPas.getText().equals("111")) {
            panelEnter.setVisible(false);
            panelMain.setVisible(true);
        } else {
            resultEnter.setText("Неверный логин или пароль");
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Подключение к базе данных прошло успешно");
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных");
            printSQLException(e);

        }
    }


    @FXML
    protected void regButtonClick() {
        panelEnter.setVisible(false);
        panelReg.setVisible(true);
    }

    @FXML
    protected void regUserButtonClick() {
        String usLogin = regLog.getText();
        String usPassword = regPass.getText();
        String usLastName = regLastName.getText();
        String usFirstName = regFirstName.getText();
        String usDadName = regDadName.getText();
        String usPol = regPol.getValue().toString();
        LocalDate usBirthday = regBirthday.getValue();

        try {
            String quary = "insert userData values (?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(quary);
            preparedStatement.setString(1, usLogin);
            preparedStatement.setString(2, usPassword);
            preparedStatement.setString(3, usLastName);
            preparedStatement.setString(4, usFirstName);
            preparedStatement.setString(5, usDadName);
            preparedStatement.setString(6, usPol);
            preparedStatement.setDate(7, Date.valueOf(usBirthday));

            int rows = preparedStatement.executeUpdate();
            System.out.println("Успешно");
        } catch (SQLException e) {
            System.out.println("Неуспешно");
            printSQLException(e);
        }


    }
}