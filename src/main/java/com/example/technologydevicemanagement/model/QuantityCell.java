package com.example.technologydevicemanagement.model;

import database.DaoDevice;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class QuantityCell extends TableCell<Device, Integer> {
    private final TextField textField = new TextField();
    private final Button addButton = new Button("+");
    private final Button minusButton = new Button("-");
    public QuantityCell() {

        textField.setPrefWidth(60);
        textField.setStyle("-fx-alignment: center");
        addButton.setPrefWidth(25);
        minusButton.setPrefWidth(25);
        addButton.setOnAction(event -> {
            int newValue = Integer.parseInt(textField.getText()) + 1;
            if(newValue<=getTableView().getItems().get(getIndex()).getQuantityInStock()&& newValue>0) {
                commitEdit(newValue);
            }
        });

        minusButton.setOnAction(event -> {
            int newValue = Integer.parseInt(textField.getText()) - 1;
            if(newValue<=getTableView().getItems().get(getIndex()).getQuantityInStock()&& newValue>0) {
                commitEdit(newValue);
            }
        });

        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    int newVal = Integer.parseInt(textField.getText());
                    if(newVal<=getTableView().getItems().get(getIndex()).getQuantityInStock()&& newVal>0) {
                        commitEdit(newVal);
                    }else{
                        cancelEdit();
                    }
                }
            }
        });
    }

    @Override
    protected void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        } else {

            textField.setText(item.toString());
            setGraphic(new HBox(3,minusButton , textField, addButton));

        }
    }


    @Override
    public void startEdit() {
        super.startEdit();
        textField.requestFocus();
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        textField.setText(1+"");
        setGraphic(new HBox(3,minusButton , textField, addButton));
    }
    @Override
    public void commitEdit(Integer newValue) {
        int  intialQuantityInStock = new DaoDevice().getById(getTableRow().getItem().getIdDevice()).getQuantityInStock();
        super.commitEdit(newValue);
        textField.setText(newValue+"");
        getTableRow().getItem().setQuantity(newValue);
        int quantityInstock = intialQuantityInStock-newValue;
        getTableRow().getItem().setQuantityInStock(quantityInstock);
        getTableRow().getItem().setQuantity(newValue);
        updateAmount(newValue);
        setGraphic(new HBox(3,minusButton , textField, addButton));;

    }
    private void updateAmount(Integer newValue){
        double amount = newValue * ((Device) getTableRow().getItem()).getPrice();
        System.out.println(amount);
        getTableRow().getItem().setAmount(amount);
        getTableView().refresh();

    }
}
