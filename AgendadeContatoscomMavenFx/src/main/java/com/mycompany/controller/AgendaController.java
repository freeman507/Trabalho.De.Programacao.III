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
import java.io.InputStream;
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
import javax.imageio.ImageIO;

/**
 *
 * @author freeman
 */
public class AgendaController implements Initializable {

    private Agenda agenda;
    private int pagina;

    @FXML
    private Button alterar;
    @FXML
    private Button criarContato;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.agenda = new Correio().receberAgenda();
        this.agenda.mostrarAgenda();
        this.pagina = 1;
        atualizarBoxs();
        File file = new File("src/main/resources/images/Yoda2.jpg");
        foto1.setImage(new Image(file.toURI().toString()));
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
        agenda.adicionaContato(new Contato(nome, telefone, email, cidade, rua, rua, lagradouro, ""));
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
    
    public void atualizarBoxs() {
        try {
            box1(this.agenda.getContatos().get(0));
            box2(this.agenda.getContatos().get(1));
            box3(this.agenda.getContatos().get(2));
        } catch (IndexOutOfBoundsException ex) {
        }
    }

    public void box1(Contato contato) {
        nome1.setText(contato.getNome());
        telefone1.setText(contato.getTelefone());
        email1.setText(contato.getEmail());
    }

    public void box2(Contato contato) {
        nome2.setText(contato.getNome());
        telefone2.setText(contato.getTelefone());
        email2.setText(contato.getEmail());
    }

    public void box3(Contato contato) {
        nome3.setText(contato.getNome());
        telefone3.setText(contato.getTelefone());
        email3.setText(contato.getEmail());
    }
}
