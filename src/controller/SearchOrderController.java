package controller;

import com.jfoenix.controls.JFXTextField;
import dbpos.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utiltm.SearchOrder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchOrderController {
    public TableView<SearchOrder> tblSearchOrder;
    Connection connection;
    public JFXTextField txtSearch;
    public Label lbl_Back;
    public ObservableList<SearchOrder> items;

    public void initialize() {
        txtSearch.requestFocus();
        connection = DBConnection.getInstance().getConnection();
        tblSearchOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblSearchOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tblSearchOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblSearchOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblSearchOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        items = tblSearchOrder.getItems();
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    tblSearchOrder.getItems().clear();
                    return;
                } else {
                    tblSearchOrder.getItems().clear();
                    try {
                        PreparedStatement stmt1 = SearchOrderController.this.connection.prepareStatement("select o.id,o.date,o.customerId,c.name,sum((id.qty)*(id.unitPrice)) as Total from orders o, customer c,itemdetail id where o.customerId=c.id&& o.id=id.orderId group by id having o.id like ?");
                        stmt1.setString(1, newValue + "%");
                        ResultSet rst1;
                        rst1 = stmt1.executeQuery();
                        if (rst1.isBeforeFirst()) {
                            while (rst1.next()) {
                                items.add(new SearchOrder(rst1.getString(1), rst1.getString(2), rst1.getString(3), rst1.getString(4), rst1.getDouble(5)));
                            }
                        }
                        stmt1 = SearchOrderController.this.connection.prepareStatement("select o.id,o.date,o.customerId,c.name,sum((id.qty)*(id.unitPrice)) as Total from orders o, customer c,itemdetail id where o.customerId=c.id&& o.id=id.orderId group by id having o.date like ?");
                        stmt1.setString(1, newValue + "%");
                        rst1 = stmt1.executeQuery();
                        if (rst1.isBeforeFirst()) {
                            while (rst1.next()) {
                                items.add(new SearchOrder(rst1.getString(1), rst1.getString(2), rst1.getString(3), rst1.getString(4), rst1.getDouble(5)));
                            }
                        }
                        stmt1 = SearchOrderController.this.connection.prepareStatement("select o.id,o.date,o.customerId,c.name,sum((id.qty)*(id.unitPrice)) as Total from orders o, customer c,itemdetail id where o.customerId=c.id&& o.id=id.orderId group by id having o.customerId like ?");
                        stmt1.setString(1, newValue + "%");
                        rst1 = stmt1.executeQuery();
                        if (rst1.isBeforeFirst()) {
                            while (rst1.next()) {
                                items.add(new SearchOrder(rst1.getString(1), rst1.getString(2), rst1.getString(3), rst1.getString(4), rst1.getDouble(5)));
                            }
                        }
                        stmt1 = SearchOrderController.this.connection.prepareStatement("select o.id,o.date,o.customerId,c.name,sum((id.qty)*(id.unitPrice)) as Total from orders o, customer c,itemdetail id where o.customerId=c.id&& o.id=id.orderId group by id having c.name like ?");
                        stmt1.setString(1, newValue + "%");
                        rst1 = stmt1.executeQuery();
                        if (rst1.isBeforeFirst()) {
                            while (rst1.next()) {
                                items.add(new SearchOrder(rst1.getString(1), rst1.getString(2), rst1.getString(3), rst1.getString(4), rst1.getDouble(5)));
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void txtSearch(ActionEvent actionEvent) {
    }

    public void lbl_Back_Action(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/DashBoard.fxml"));
        Scene mainScene = new Scene(root);
        Stage dashStage = (Stage) lbl_Back.getScene().getWindow();
        dashStage.setScene(mainScene);
        dashStage.setTitle("DashBoard");
        dashStage.centerOnScreen();
        dashStage.show();

    }

}
