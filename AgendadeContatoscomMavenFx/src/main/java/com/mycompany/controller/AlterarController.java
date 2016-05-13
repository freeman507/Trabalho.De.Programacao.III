/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.models.Agenda;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author freeman
 */
public class AlterarController implements Initializable {
    
    private Agenda agenda;
    private Contato contato;
    private String novaFoto;
    
    @FXML
    private ImageView fotoView;
    @FXML
    private Button cancelar;
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

    public AlterarController() {
    }

    public AlterarController(Agenda agenda, Contato contato) {
        this.agenda = agenda;
        this.contato = contato;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    /**
     * Carrega os dados do Contato na Janela.
     */
    public void carregarContato() {
        nomeTxtField.setText(this.contato.getNome());
        telefoneTxtField.setText(this.contato.getTelefone());
        emailTxtField.setText(this.contato.getEmail());
        cidadeTxtField.setText(this.contato.getCidade());
        ruaTxtField.setText(this.contato.getRua());
        estadoTxtField.setText(this.contato.getUf());
        lagradouroTxtField.setText(this.contato.getLagradouro());
        this.novaFoto = this.contato.getFoto();
        File file = new File(this.novaFoto);
        fotoView.setImage(new Image(file.toURI().toString()));
    }
    
    /**
     * Este metodo altera a foto do contato, ele é chamado quando o usuário 
     * clica no botão Alterar foto.
     * @param ev 
     */
    @FXML
    private void handleBtnAdicionarFoto(ActionEvent ev) {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(new Stage());
        this.novaFoto = file.getPath();
        fotoView.setImage(new Image(file.toURI().toString()));
    }
    
    /**
     * Este método é chamado quando o usuário clica no botão alterar na janela,
     * o metodo captura o valor de todos os campos e o caminho da foto e altera
     * o contato, em seguida, fecha a janela de alterar.
     * @param ac
     * @throws IOException 
     */
    @FXML
    private void handleBtnAlterar(ActionEvent ac) throws IOException {
        String nome = nomeTxtField.getText();
        String telefone = telefoneTxtField.getText();
        String email = emailTxtField.getText();
        String cidade = cidadeTxtField.getText();
        String rua = ruaTxtField.getText();
        String estado = estadoTxtField.getText();
        String lagradouro = lagradouroTxtField.getText();
        this.agenda.alteraContato(contato, nome, telefone, email, cidade, rua, rua, lagradouro, this.novaFoto);
        handleBtnCancelar(ac);
    }
    
    /**
     * Este metodo é chamado quando o usuário clica no botão cancelar ou quando
     * é finalizada a alteração do contato. Este metodo fecha a janela de Alte-
     * rar contato e carrega a janela Agenda de Contatos.
     * @param ac
     * @throws IOException 
     */
    @FXML
    private void handleBtnCancelar(ActionEvent ac) throws IOException {
        Stage stage;
        Parent root;  
        stage = (Stage) cancelar.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/AgendaView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }   

    @FXML
    private void handleBtnVoltar(ActionEvent event) {
    }
}
