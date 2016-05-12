/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import com.mongodb.MongoClient;
import com.mongodb.operation.UpdateOperation;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

/**
 *
 * @author freeman
 */
public class Correio {
    
    /**
     * Esta é classe responsável em fazer toda a transição com o Banco de Dados.
     */
    
    private final Morphia morphia;
    private final Datastore datastore;
    private Agenda agenda;
    private Contato contato;

    public Correio() {
        this.morphia = new Morphia();
        morphia.mapPackage("com.mycompany.models");
        datastore = morphia.createDatastore(new MongoClient(), "Agenda_de_Contatos");
        datastore.ensureIndexes();
    }

    public Correio(Agenda agenda) {
        
        this.morphia = new Morphia();
        morphia.mapPackage("com.mycompany.models");
        datastore = morphia.createDatastore(new MongoClient(), "Agenda_de_Contatos");
        datastore.ensureIndexes();
        
        this.agenda = agenda;
    }
    
    public Correio(Morphia morphia, Datastore datastore) {
        this.morphia = morphia;
        this.datastore = datastore;
    }

    public Correio(Morphia morphia, Datastore datastore, Agenda agenda, Contato contato) {
        this.morphia = morphia;
        this.datastore = datastore;
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
    
    /**
     * Cria a agenda e recupera todos os contatos que estão no Banco de dados.
     * @return 
     */
    public Agenda receberAgenda() {
        this.agenda = new Agenda();
        List<Contato> contatos = datastore.createQuery(Contato.class).asList();
        for(Contato c : contatos) {
            //this.agenda.adicionaContato(c);
            this.agenda.getContatos().add(c);
        }
        return this.agenda;
    }
    
    /**
     * Envia todos os contatos da agenda para o Banco de dados.
     */
    public void enviarAgenda() {
        ArrayList<Contato> contatos = agenda.getContatos();
        for(Contato c : contatos) {
            datastore.save(c);
        }
    }
    
    /**
     * Deleta o contato do Banco de dados
     * @param id identificação do Contato que vai ser deletado.
     */
    public void deletarContato(ObjectId id) {
        final Query<Contato> query = datastore.createQuery(Contato.class)
                .filter("_id ==", id);
        datastore.delete(query);
    }
    
    /**
     * Adiciona um contato ao Banco de dados
     * @param c referencia do contato ao ser adicionado.
     */
    public void enviarContato(Contato c) {
        datastore.save(c);
    }
    
    /**
     * Altera todos os dados do contato no Banco de Dados.
     * @param contato referência do contato a ser alterado
     */
    public void atualizarContato(Contato contato) {
        final Query<Contato> query = datastore.createQuery(Contato.class)
                .filter("_id ==", contato.getId());
        final UpdateOperations<Contato> updateOperations = datastore.createUpdateOperations(Contato.class)
                .set("nome", contato.getNome()).set("telefone", contato.getTelefone())
                .set("email", contato.getEmail()).set("cidade", contato.getCidade())
                .set("rua", contato.getRua()).set("uf", contato.getUf())
                .set("lagradouro", contato.getLagradouro()).set("foto", contato.getFoto());
        final UpdateResults results = datastore.update(query, updateOperations);
    }
}
