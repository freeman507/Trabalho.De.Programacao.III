/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import java.util.ArrayList;
import org.bson.types.ObjectId;

/**
 *
 * @author freeman
 */
public class Agenda {
    private ArrayList<Contato> contatos;
    private Correio correio;

    public Agenda() {
        this.contatos = new ArrayList<>();
        this.correio = new Correio();
    }

    public Agenda(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }
    
    /**
     * envia o contato para o correio e adiciona na lista da agenda.
     * @param contato 
     */
    public void adicionaContato(Contato contato) {
        correio.enviarContato(contato);
        this.contatos.add(contato);
    }
    
    /**
     * manda para o correio o contato para ser deletado do banco e remove o con-
     * tado da agenda.
     * @param contato 
     */
    public void removeContato(Contato contato) {
        this.contatos.remove(contato);
        this.correio.deletarContato(contato.getId());
    }
    
    /**
     * altera os dados do contato e manda as alterações para o correio
     * @param contato referência ao contato a ser alerado.
     * @param nome
     * @param telefone
     * @param email
     * @param cidade
     * @param rua
     * @param uf
     * @param lagradouro
     * @param foto 
     */
    public void alteraContato(Contato contato, String nome, String telefone,
            String email, String cidade, String rua, String uf, String lagradouro, String foto) {
        contato.setNome(nome);
        contato.setTelefone(telefone);
        contato.setEmail(email);
        contato.setCidade(cidade);
        contato.setRua(rua);
        contato.setUf(uf);
        contato.setLagradouro(lagradouro);
        contato.setFoto(foto);
        correio.atualizarContato(contato);
    }
    
    /**
     * Retorna o contato da lista de contatos pelo id da lista.
     * @param id
     * @return 
     */
    public Contato pegaContato(int id) {
        return this.contatos.get(id);
    }
    
    /**
     * exibe no terminal todos os contatos da lista.
     */
    public void mostrarAgenda() {
        System.out.println("Contatos: ");
        for(Contato contato : contatos) {
            System.out.println("id: "+contato.getId());
            System.out.println("nome: "+contato.getNome());
            System.out.println("telefone: "+contato.getTelefone());
            System.out.println("--------------------------------");
        }
    }
}
