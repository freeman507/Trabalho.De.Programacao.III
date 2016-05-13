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
import javafx.scene.control.TabPane;
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
    private Contato contato1;
    private Contato contato2;
    private Contato contato3;

    @FXML
    private TabPane agendaView;
    @FXML
    private Button detalhesButton1;
    @FXML
    private Button detalhesButton2;
    @FXML
    private Button detalhesButton3;
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
     * Este metodo é chamado quando o usuário clica em um dos botões Detalhes,
     * o metodo carrega uma nova janela enviando o contato para que o usuário
     * vizualize o contato com todos os campos (rua, cidade...).
     * @param ac
     * @throws IOException 
     */
    @FXML
    private void handleBtnDetalhes(ActionEvent ac) throws IOException {
        String buttonTyped = ac.getSource().toString();
        Stage stage;
        
        //Prepara a tela de Detalhes do contato.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DetalhesView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        DetalhesController detalhesController = fxmlLoader.<DetalhesController>getController();
        
        //Captura qual botão Detalhes foi precissonado e encaminha o respectivo contato.
        if(buttonTyped.contains(detalhesButton1.getId())) {
            stage = (Stage) detalhesButton1.getScene().getWindow();
            detalhesController.setContato(contato1);
        }
        else if (buttonTyped.contains(detalhesButton2.getId())) {
            stage = (Stage) detalhesButton2.getScene().getWindow();
            detalhesController.setContato(contato2);
        }
        else {
            stage = (Stage) detalhesButton3.getScene().getWindow();
            detalhesController.setContato(contato3);
        }
        
        //finaliza e lança a janela Detalhes do Contato.
        detalhesController.carregarContato();
        Scene scene = new Scene(root);
        stage.setTitle("Detalhes");
        stage.setScene(scene);
        stage.show();
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

    /**
     * Este metodo é chamado quando o usuário clica em um dos botões Alterar, 
     * o metodo carrega uma nova janela enviando o contato para que o usuário
     * possa alterar os dados do contato.
     * @param ac
     * @throws IOException 
     */
    @FXML
    private void handleBtnAlterar(ActionEvent ac) throws IOException {
        String buttonTyped = ac.getSource().toString();
        Stage stage;
        
        //Prepara a tela de Alterar Contato
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AlterarView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AlterarController alterarController = fxmlLoader.<AlterarController>getController();
        alterarController.setAgenda(this.agenda);
        
        //Captura qual botão alterar foi precissonado e encaminha o respectivo contato.
        if(buttonTyped.contains(alterarButton1.getId())) {
            stage = (Stage) alterarButton1.getScene().getWindow();
            alterarController.setContato(contato1);
        }
        else if (buttonTyped.contains(alterarButton2.getId())) {
            stage = (Stage) alterarButton2.getScene().getWindow();
            alterarController.setContato(contato2);
        }
        else {
            stage = (Stage) alterarButton3.getScene().getWindow();
            alterarController.setContato(contato3);
        }
        
        //finaliza e lança a janela Alterar Contato.
        alterarController.carregarContato();
        Scene scene = new Scene(root);
        stage.setTitle("Alterar Contato");
        stage.setScene(scene);
        stage.show();
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
            detalhesButton1.setDisable(false);
            alterarButton1.setDisable(false);
            removerButton1.setDisable(false);
        } catch (IndexOutOfBoundsException ex) {
            nome1.setText("");
            telefone1.setText("");
            email1.setText("");
            File file = new File("src/main/resources/images/NoPhoto.png");
            foto1.setImage(new Image(file.toURI().toString()));
            detalhesButton1.setDisable(true);
            alterarButton1.setDisable(true);
            removerButton1.setDisable(true);
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
            detalhesButton2.setDisable(false);
            alterarButton2.setDisable(false);
            removerButton2.setDisable(false);
        } catch (IndexOutOfBoundsException ex) {
            nome2.setText("");
            telefone2.setText("");
            email2.setText("");
            File file = new File("src/main/resources/images/NoPhoto.png");
            foto2.setImage(new Image(file.toURI().toString()));
            detalhesButton2.setDisable(true);
            alterarButton2.setDisable(true);
            removerButton2.setDisable(true);
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
            detalhesButton3.setDisable(false);
            alterarButton3.setDisable(false);
            removerButton3.setDisable(false);
        } catch (IndexOutOfBoundsException ex) {
            nome3.setText("");
            telefone3.setText("");
            email3.setText("");
            File file = new File("src/main/resources/images/NoPhoto.png");
            foto3.setImage(new Image(file.toURI().toString()));
            detalhesButton3.setDisable(true);
            alterarButton3.setDisable(true);
            removerButton3.setDisable(true);
        }
    }
}
