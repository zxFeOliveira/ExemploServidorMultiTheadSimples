package com.redes.aula2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    
    private static Socket conexao;
    
    private static DataInputStream entrada;
    private static DataOutputStream saida;
    
    public static void main(String[] args) {
        try {
            conexao = new Socket("localhost",52000);
            System.out.println("Digite uma frase");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String frase = br.readLine();
            saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF(frase);
            
            entrada = new DataInputStream(conexao.getInputStream());
            String resposta = entrada.readUTF();
            System.out.println("Resposta do servidor: " + resposta);
            conexao.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
            
    
}
