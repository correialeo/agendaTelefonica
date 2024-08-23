package tests;

import dao.ContatoDao;
import models.Contato;

import java.util.Scanner;

public class TesteBuscaID {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        Contato contato = new Contato();
        ContatoDao dao = new ContatoDao();

        System.out.println("Digite o ID do contato");
        int id = leitor.nextInt();
        contato = dao.listarContato(id);

        System.out.println(contato.toString());
    }
}
