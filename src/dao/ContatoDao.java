package dao;

import enums.TipoContatoEnum;
import models.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//É a classe responsável por manipular os dados no BD
//Implementando o CRUD (cadastro, consultas, alterações e exclusão de dados)
public class ContatoDao {
    private Connection conexao;

    public void cadastrarContato(Contato contato){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSQL = null;
        try{
            String sql = "insert into tbl_contato(ID_CONTATO, NOME_CONTATO, CELULAR_CONTATO, EMAIL_CONTATO," +
                    "INSTAGRAM, TIPO) values (?,?,?,?,?,?)";
            //O preparedStatement pega o comando sql e executa no servidor oracle
            //usando a conexao que foi configurada
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setInt(1, contato.getCodigo());
            comandoSQL.setString(2, contato.getNome());
            comandoSQL.setString(3, contato.getTelefone());
            comandoSQL.setString(4, contato.getEmail());
            comandoSQL.setString(5, contato.getInstagram());
            comandoSQL.setString(6, contato.getTipoContato().toString());
            comandoSQL.executeUpdate();
            comandoSQL.close();
            conexao.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Contato listarContato(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSQL = null;
        Contato contato = new Contato();
        try{
            String query = "select * from tbl_contato where id_contato=?";
            comandoSQL = conexao.prepareStatement(query);
            comandoSQL.setInt(1, id);
            ResultSet resultSet = comandoSQL.executeQuery();
            if (resultSet.next()){
                contato.setCodigo(resultSet.getInt(1));
                contato.setNome(resultSet.getString(2));
                contato.setTelefone(resultSet.getString(3));
                contato.setEmail(resultSet.getString(4));
                contato.setInstagram(resultSet.getString(5));
                contato.setTipoContato(TipoContatoEnum.valueOf(resultSet.getString(6)));
            }
            comandoSQL.close();
            conexao.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return contato;
    }
}
