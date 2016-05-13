/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.models.Contato;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author freeman
 */
public class DetalhesController implements Initializable{

    private Contato contato;
    
    @FXML
    private ImageView fotoView;
    @FXML
    private Button voltar;
    @FXML
    private TextField nomeTxtField;
    @FXML
    private TextField telefoneTxtField;
    @FXML
    private TextField emailTxtField;
    @FXML
    private TextField cidadeTxtField;
    @FXML
    private TextField ruaTxtField;
    @FXML
    private TextField estadoTxtField;
    @FXML
    private TextField lagradouroTxtField;

    public void setContato(Contato contato) {
        this.contato = contato;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
    }
    
    public void carregarContato() {
        nomeTxtField.setText(this.contato.getNome());
        telefoneTxtField.setText(this.contato.getTelefone());
        emailTxtField.setText(this.contato.getEmail());
        cidadeTxtField.setText(this.contato.getCidade());
        ruaTxtField.setText(this.contato.getRua());
        estadoTxtField.setText(this.contato.getUf());
        lagradouroTxtField.setText(this.contato.getLagradouro());
        File file = new File(this.contato.getFoto());
        fotoView.setImage(new Image(file.toURI().toString()));
    }
    
    @FXML
    private void handleBtnVoltar(ActionEvent ac) throws IOException {
        Stage stage;
        Parent root;  
        stage = (Stage) voltar.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/AgendaView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
