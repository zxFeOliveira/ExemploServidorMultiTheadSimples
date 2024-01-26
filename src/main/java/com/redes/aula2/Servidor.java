package com.redes.aula2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Thread{
    
    private Socket conexao;
    
    public Servidor(){}
    
    public Servidor(Socket c){
        this.conexao = c;
    }
    
    @Override
    public void run(){
        try {
            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            String frase = entrada.readUTF();
            
            String novaFrase = frase.toUpperCase();
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF(novaFrase);
            conexao.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        try {
            ServerSocket servidor = new ServerSocket(52000);
            while(true){
                System.out.println("Aguardando conexao...");
                Socket conexao = servidor.accept();
                Servidor tServidor = new Servidor(conexao);
                tServidor.start();
            }
                    
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
