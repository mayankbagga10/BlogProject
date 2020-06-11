//*************************DONE****************************
package com.upgrad.blog.dao;

import com.upgrad.blog.db.DatabaseConnection;
import com.upgrad.blog.dto.PostDTO;
import com.upgrad.blog.interfaces.PostsCRUD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * TODO: 6.12. Implement the PostsCRUD interface.
 * TODO: 6.13. Define the following methods and return null for each of them. You will provide
 * a proper implementation for each of these methods, later in this project.
 * a. findByEmail
 * b. findByTag
 * c. findAllTags
 * d. deleteById
 * TODO: 6.14. create() method should take post details as input and insert these details
 * into the POSTS table. Return the same PostDTO object which was passed as an input argument.
 */

/**
 * TODO: 7.1. Implement findByEmail() method which takes email id as an input
 * parameter and returns all the posts corresponding to the email id from the POSTS
 * table defined in the database.
 */

/**
 * TODO: 7.6. Implement deleteById() method which takes post id and email id
 * as an input parameters and delete the corresponding post from the database. If
 * the post was deleted successfully, then return true, otherwise return false.
 */

/**
 * TODO: 7.10. Implement findAllTags() method which returns a set of all unique tags
 * in the POSTS table.
 * TODO: 7.11. Implement findByTag() method which takes "tag" as an input parameter and
 * returns all the posts corresponding to the input "tag" from the POSTS table defined
 * in the database.
 */
public class PostDAO implements PostsCRUD {
    /**
     *
     * @param emailId
     * @return
     * @throws SQLException
     */
    @Override
    public List<PostDTO> findByEmail(String emailId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM POSTS WHERE email_id='"+emailId+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        return getDataFromResultSet(resultSet);
    }

    /**
     *
     * @param postDTO
     * @return
     * @throws SQLException
     */
    @Override
    public PostDTO create(PostDTO postDTO) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        System.out.println(connection);
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO POSTS (email_id, title, description, tag, timestamp) VALUES ('"+postDTO.getEmailId()+"', '"+postDTO.getTitle()+"', '"+postDTO.getDescription()+"', '"+postDTO.getTag()+"', '"+postDTO.getTimestamp().toString()+"')";
        statement.executeUpdate(sql);
        return postDTO;
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public boolean deleteById(int id, String emailId) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        System.out.println(connection + "\n"+emailId);
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM POSTS WHERE email_id='"+emailId+"'AND id="+id ;
        int rowsAffected = statement.executeUpdate(sql);
        System.out.println(rowsAffected);
        return rowsAffected > 0;
    }

    /**
     *
     * @param tag
     * @return
     * @throws SQLException
     */
    @Override
    public List<PostDTO> findByTag(String tag) throws  SQLException{
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM POSTS WHERE tag='"+tag+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        return getDataFromResultSet(resultSet);
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public HashSet<String> findAllTags() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT tag FROM POSTS";
        ResultSet resultSet = statement.executeQuery(sql);
        HashSet<String> tagList = new HashSet<String>();
        while(resultSet.next()) {

            tagList.add(resultSet.getString("tag"));
        }
        return tagList;

    }

    /**
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public List<PostDTO> getDataFromResultSet(ResultSet resultSet) throws SQLException {
        List<PostDTO> postDTOS = new ArrayList<>();
        while(resultSet.next()) {

            PostDTO postDTO = new PostDTO();
            postDTO.setPostId(resultSet.getInt("id"));
            postDTO.setEmailId(resultSet.getString("email_id"));
            postDTO.setTitle(resultSet.getString("title"));
            postDTO.setDescription(resultSet.getString("description"));
            postDTO.setTag(resultSet.getString("tag"));
            postDTO.setTimestamp(LocalDateTime.parse(resultSet.getString("timestamp")));
            postDTOS.add(postDTO);
        }
        return postDTOS;
    }


}
