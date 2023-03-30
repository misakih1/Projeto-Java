/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import views.CadastroCliente;
import views.ConsultaCliente;
import models.Cliente;
import services.Bd;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ClienteController {

    public static void insert(Cliente cliente) {
        Connection conn = Bd.conectar();

        try {
            String sql = "INSERT INTO cliente (nome,cpf,telefone)"
                    + "VALUES (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, cliente.getTelefone());

            if (statement.executeUpdate() > 0) {
                System.out.println("\nCLIENTE CADASTRADO!");
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        Bd.fechar(conn);
    }

    public static void delete(int id) {
        Connection conn = Bd.conectar();

        try {
            String sql = "DELETE FROM cliente WHERE id = " + id;
            PreparedStatement statement = conn.prepareStatement(sql);

            if (statement.executeUpdate() > 0) {
                System.out.println("\nCLIENTE DELETADO");
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        Bd.fechar(conn);
    }

//    public static Cliente cadastrar() {
//        Cliente cliente = new Cliente();
//
//        System.out.print("Nome: ");
//        cliente.setNome(CadastroCliente.txtNome.getText());
//
//        System.out.print("Cpf: ");
//        cliente.setCpf(Receber.texto());
//
//        System.out.print("Telefone: ");
//        cliente.setTelefone(Receber.texto());
//        return cliente;
//    }

    public static void imprime(Cliente cliente) {
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Telefone: " + cliente.getTelefone());
    }

    public static ArrayList<Cliente> getAll() {
        Connection conn = Bd.conectar();
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        try {
            String sql = "SELECT * FROM cliente";
            Statement statement = conn.createStatement();

            ResultSet resultado = statement.executeQuery(sql);

            while (resultado.next()) {
                lista.add(new Cliente(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("cpf"),
                        resultado.getString("telefone"))
                );
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        Bd.fechar(conn);
        return lista;
    }
}
