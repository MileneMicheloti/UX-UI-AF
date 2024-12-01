package login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Classe responsável por realizar operações relacionadas à autenticação de usuários,
 * incluindo a conexão com o banco de dados e a verificação de credenciais.
 */
public class User {
    /**
     * Estabelece uma conexão com o banco de dados MySQL utilizando o DriverManager.
     * Configura os parâmetros necessários para autenticar e conectar ao banco.
     *
     * @return Connection Retorna o objeto de conexão ativo com o banco de dados.
     * @throws SQLException Caso ocorra algum problema na conexão, uma exceção será lançada.
     */
    public Connection conectarBD() {
        Connection conn = null;
        try {
            // Inicializa o driver JDBC e define os dados da conexão
            Class.forName("com.mysql.Driver.Manager").newInstance();
            String url = "jbdc:mysql://127.0.0.1/test?user=lopes&password=123";
        } catch (Exception e) { }

        return conn;
    }

    /**
     * Armazena o nome do usuário obtido a partir da consulta realizada ao banco.
     */
    public String nome = "";

    /**
     * Representa o status da busca pelo usuário.
     * 'true' indica que o usuário foi localizado, 'false' caso contrário.
     */
    public boolean result = false;

    /**
     * Verifica se o usuário com as credenciais fornecidas existe no banco de dados.
     * Realiza uma consulta para recuperar o nome associado ao login e senha.
     *
     * @param login O nome de usuário informado.
     * @param senha A senha correspondente ao login.
     * @return boolean Retorna 'true' se o usuário for encontrado, ou 'false' se não for.
     */
    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        Connection conn = conectarBD();
        // Constrói a instrução SQL para buscar o usuário no banco
        sql += "select nome from usuarios ";
        sql += "where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "';";
        try {
            // Realiza a consulta e avalia os resultados
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                result = true; // O usuário foi encontrado
                nome = rs.getString("nome"); // Armazena o nome recuperado
            }
        } catch (Exception e) { }
        return result;
    }
} // fim da class
