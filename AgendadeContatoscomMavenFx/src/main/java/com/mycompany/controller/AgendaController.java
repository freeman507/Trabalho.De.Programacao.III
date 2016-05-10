/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.models.Agenda;
import com.mycompany.models.Contato;
import com.mycompany.models.Correio;
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
 *
 * @author freeman
 */
public class AgendaController implements Initializable {

    private Agenda agenda;
    private int indexes;
    private String novaFoto;

    @FXML
    private Button alterar;
    @FXML
    private Button criarContato;
    @FXML
    private ImageView novaFotoView;
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
    @FXML
    private TextField nome1;
    @FXML
    private TextField nome2;
    @FXML
    private TextField nome3;
    @FXML
    private TextField telefone1;
    @FXML
    private TextField telefone2;
    @FXML
    private TextField telefone3;
    @FXML
    private TextField email1;
    @FXML
    private TextField email2;
    @FXML
    private TextField email3;
    @FXML
    private ImageView foto1;
    @FXML
    private ImageView foto2;
    @FXML
    private ImageView foto3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.agenda = new Correio().receberAgenda();
        this.indexes = 0;
        atualizarBoxs();
        File file = new File("src/main/resources/images/NoPhoto.png");
        novaFotoView.setImage(new Image(file.toURI().toString()));
    }
    
    @FXML
    private void handleBtnAdicionarFoto(ActionEvent ev) {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(new Stage());
        novaFoto = file.getPath();
        novaFotoView.setImage(new Image(file.toURI().toString()));
    }

    @FXML
    private void handleBtnCriarContato(ActionEvent ev) {
        String nome = nomeTxtField.getText();
        String telefone = telefoneTxtField.getText();
        String email = emailTxtField.getText();
        String cidade = cidadeTxtField.getText();
        String rua = ruaTxtField.getText();
        String estado = estadoTxtField.getText();
        String lagradouro = lagradouroTxtField.getText();
        nomeTxtField.setText("");
        telefoneTxtField.setText("");
        emailTxtField.setText("");
        cidadeTxtField.setText("");
        ruaTxtField.setText("");
        estadoTxtField.setText("");
        lagradouroTxtField.setText("");
        File file = new File("src/main/resources/images/NoPhoto.png");
            novaFotoView.setImage(new Image(file.toURI().toString()));
        agenda.adicionaContato(new Contato(this.agenda.getContatos().size(),
                nome, telefone, email, cidade, rua, estado, lagradouro, novaFoto));
        atualizarBoxs();
    }

    @FXML
    private void handleBtnAlterar(ActionEvent ac) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) alterar.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/AlterarView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleBtnNext(ActionEvent ev) {
        if (this.indexes < this.agenda.getContatos().size() -3) {
            this.indexes += 3;
            atualizarBoxs();
        }
    }

    public void handleBtnBack(ActionEvent ev) {
        if (this.indexes >= 3) {
            this.indexes -= 3;
            atualizarBoxs();
        }
    }

    public void atualizarBoxs() {
        box1();
        box2();
        box3();
    }

    public void box1() {
        try {
            nome1.setText(this.agenda.getContatos().get(indexes).getNome());
            telefone1.setText(this.agenda.getContatos().get(indexes).getTelefone());
            email1.setText(this.agenda.getContatos().get(indexes).getEmail());
            File file = new File(this.agenda.getContatos().get(indexes).getFoto());
            foto1.setImage(new Image(file.toURI().toString()));
        } catch (IndexOutOfBoundsException ex) {
            nome1.setText("");
            telefone1.setText("");
            email1.setText("");
            File file = new File("src/main/resources/images/NoPhoto.png");
            foto1.setImage(new Image(file.toURI().toString()));
        }
    }

    public void box2() {
        try {
            nome2.setText(this.agenda.getContatos().get(indexes + 1).getNome());
            telefone2.setText(this.agenda.getContatos().get(indexes + 1).getTelefone());
            email2.setText(this.agenda.getContatos().get(indexes + 1).getEmail());
            File file = new File(this.agenda.getContatos().get(indexes + 1).getFoto());
            foto2.setImage(new Image(file.toURI().toString()));
        } catch (IndexOutOfBoundsException ex) {
            nome2.setText("");
            telefone2.setText("");
            email2.setText("");
            File file = new File("src/main/resources/images/NoPhoto.png");
            foto2.setImage(new Image(file.toURI().toString()));
        }
    }

    public void box3() {
        try {
            nome3.setText(this.agenda.getContatos().get(indexes + 2).getNome());
            telefone3.setText(this.agenda.getContatos().get(indexes + 2).getTelefone());
            email3.setText(this.agenda.getContatos().get(indexes + 2).getEmail());
            File file = new File(this.agenda.getContatos().get(indexes + 2).getFoto());
            foto3.setImage(new Image(file.toURI().toString()));
        } catch (IndexOutOfBoundsException ex) {
            nome3.setText("");
            telefone3.setText("");
            email3.setText("");
            File file = new File("src/main/resources/images/NoPhoto.png");
            foto3.setImage(new Image(file.toURI().toString()));
        }
    }
}
