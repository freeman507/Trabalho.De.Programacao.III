/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import java.util.ArrayList;

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
    
    public void adicionaContato(Contato contato) {
        this.contatos.add(contato);
        correio.enviarContato(contato);
    }
    
    public void removeContato(int id) {
        this.contatos.remove(id);
    }
    
    public void alteraContato(Contato contato) {
        this.contatos.remove(contato.getId());
        this.contatos.add(contato);
    }
    
    public Contato pegaContato(int id) {
        return this.contatos.get(id);
    }
    
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
