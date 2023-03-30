/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.Veiculo;
import services.Bd;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class VeiculoController {

    public static void insert(Veiculo veiculo) {
        Connection conn = Bd.conectar();

        try {
            String sql = "INSERT INTO veiculo (modelo,placa,preco)"
                    + "VALUES (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, veiculo.getModelo());
            statement.setString(2, veiculo.getPlaca());
            statement.setString(3, veiculo.getPreco());

            if (statement.executeUpdate() > 0) {
                System.out.println("\nVEÍCULO CADASTRADO!");
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        Bd.fechar(conn);
    }

    public static void delete(int id) {
        Connection conn = Bd.conectar();

        try {
            String sql = "DELETE FROM veiculo WHERE id = " + id;
            PreparedStatement statement = conn.prepareStatement(sql);

            if (statement.executeUpdate() > 0) {
                System.out.println("\nVEÍCULO DELETADO");
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

    public static void imprime(Veiculo veiculo) {
        System.out.println("Modelo: " + veiculo.getModelo());
        System.out.println("Placa: " + veiculo.getPlaca());
        System.out.println("Preço: " + veiculo.getPreco());
    }

    public static ArrayList<Veiculo> getAll() {
        Connection conn = Bd.conectar();
        ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
        try {
            String sql = "SELECT * FROM veiculo";
            Statement statement = conn.createStatement();

            ResultSet resultado = statement.executeQuery(sql);

            while (resultado.next()) {
                lista.add(new Veiculo(
                        resultado.getInt("id"),
                        resultado.getString("modelo"),
                        resultado.getString("placa"),
                        resultado.getString("preco"))
                );
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        Bd.fechar(conn);
        return lista;
    }
}
