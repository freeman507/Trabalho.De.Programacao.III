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
import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private Contato contato1;
    private Contato contato2;
    private Contato contato3;

    @FXML
    private TabPane agendaView;
    @FXML 
    private Button removerButton1;
    @FXML 
    private Button removerButton2;
    @FXML 
    private Button removerButton3;
    @FXML
    private Button alterarButton1;
    @FXML
    private Button alterarButton2;
    @FXML
    private Button alterarButton3;
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

    /**
     * Esse metodo inicializa a agenda a partir do correio.
     * Inicializa indexes em 0 usado no metodo atualizarBoxs para manipular 
     * os contatos que serão ixibidos na janela.
     * Inicializa a foto padrão para a aba de novo contato da janela.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.agenda = new Correio().receberAgenda();
        this.indexes = 0;
        atualizarBoxs();
        File file = new File("src/main/resources/images/NoPhoto.png");
        novaFotoView.setImage(new Image(file.toURI().toString()));
    }
    
    /**
     * este metodo é chamado quando o usuário clica em adicionar foto na aba de
     * novo contato, ele captura o caminho do diretorio da foto para o contato
     * e também mostra a foto na janela.
     * @param ev 
     */
    @FXML
    private void handleBtnAdicionarFoto(ActionEvent ev) {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(new Stage());
        novaFoto = file.getPath();
        novaFotoView.setImage(new Image(file.toURI().toString()));
    }

    /**
     * este metodo captura todos os campos de texo da aba novo contato, limpa os campos,
     * cria o contato, adiciona o contato na agenda e por fim atualiza os
     * mostrados na aba contatos.
     * @param ev 
     */
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
        agenda.adicionaContato(new Contato(nome, telefone, email, cidade,
                rua, estado, lagradouro, novaFoto));
        atualizarBoxs();
    }
    
    /**
     * Remove o contato de acordo com o botão precionado, ou seja, se o usuário
     * precissionar o "removerButton1" (Botão escrito Remover mais ao topo da 
     * aba Contatos) ele deleta da agenda o primeiro contato que está sendo ixi-
     * bido na janela, se precionar o "removerButton2" deleta o segundo contato...
     * @param ac 
     */
    @FXML
    private void handleBtnRemover(ActionEvent ac) {
        if(!this.agenda.getContatos().isEmpty()) {
            String buttonTyped = ac.getSource().toString();
            if(buttonTyped.contains(removerButton1.getId())) 
                this.agenda.removeContato(this.contato1);
            else if(buttonTyped.contains(removerButton2.getId()))
                this.agenda.removeContato(this.contato2);
            else
                this.agenda.removeContato(this.contato3);
        }
        atualizarBoxs();
    }

    @FXML
    private void handleBtnAlterar(ActionEvent ac) throws IOException {
        String buttonTyped = ac.getSource().toString();
        if(buttonTyped.contains(alterarButton1.getId())) {
            
        }
        else if (buttonTyped.contains(alterarButton2.getId())) {
            
        }
        else {
            
        }
    }
    
    /**
     * Este metodo é chamado quando o usuário clica no botão next (na janela
     * está como ">"), ele incrementa a variavel indexes(que controla os contatos
     * que são exibidos na janela) de 3 em 3 e não deixa que ultrapasse o numero
     * de contatos da agenda.
     * @param ev 
     */
    public void handleBtnNext(ActionEvent ev) {
        if (this.indexes < this.agenda.getContatos().size() -3) {
            this.indexes += 3;
            atualizarBoxs();
        }
    }
    
    /**
     * Este metodo é chamado quando o usuário clca no botão back (na janela está
     * como "<"), faz o mesmo que o metodo acima(handleBtnNext) mas ao invez de 
     * incrementar indexes ele decrementa de 3 em 3.
     * @param ev 
     */
    public void handleBtnBack(ActionEvent ev) {
        if (this.indexes >= 3) {
            this.indexes -= 3;
            atualizarBoxs();
        }
    }
    
    /**
     * Metodo responsavel em atualizar os contatos que são mostrados na aba
     * Contatos, ele é chamado sempre que há alguma alteração na agenda.
     */
    public void atualizarBoxs() {
        box1();
        box2();
        box3();
    }

    /**
     * Os metodos box1, box2 e box3, é aqui que a variavel indexes tem efeito.
     * Os metodos capturam os contatos atravez da variavel indexes e exibem eles
     * na aba Contatos.
     */
    public void box1() {
        try {
            this.contato1 = this.agenda.getContatos().get(indexes);
            nome1.setText(this.contato1.getNome());
            telefone1.setText(this.contato1.getTelefone());
            email1.setText(this.contato1.getEmail());
            File file = new File(this.contato1.getFoto());
            foto1.setImage(new Image(file.toURI().toString()));
        } catch (IndexOutOfBoundsException ex) {
            nome1.setText("");
            telefone1.setText("");
            email1.setText("");
            File file = new File("src/main/resources/images/NoPhoto.png");
            foto1.setImage(new Image(file.toURI().toString()));
        }
    }

    /**
     * veja comentario do metodo box1
     */
    public void box2() {
        try {
            this.contato2 = this.agenda.getContatos().get(indexes + 1);
            nome2.setText(this.contato2.getNome());
            telefone2.setText(this.contato2.getTelefone());
            email2.setText(this.contato2.getEmail());
            File file = new File(this.contato2.getFoto());
            foto2.setImage(new Image(file.toURI().toString()));
        } catch (IndexOutOfBoundsException ex) {
            nome2.setText("");
            telefone2.setText("");
            email2.setText("");
            File file = new File("src/main/resources/images/NoPhoto.png");
            foto2.setImage(new Image(file.toURI().toString()));
        } catch (NullPointerException ex){}
    }
    /**
     * veja comentario do metodo box1
     */
    public void box3() {
        try {
            this.contato3 = this.agenda.getContatos().get(indexes + 2);
            nome3.setText(this.contato3.getNome());
            telefone3.setText(this.contato3.getTelefone());
            email3.setText(this.contato3.getEmail());
            File file = new File(this.contato3.getFoto());
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
