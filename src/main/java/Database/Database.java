package Database;
import org.jetbrains.annotations.NotNull;
import View.QueryDisplay;

import javax.swing.*;
import java.sql.*;

public class Database implements DataBaseInterface {
    private Connection conn;
    public Database(){
        connect();
    }
    public boolean connect() {
        try {
            // Step 1: Load the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Step 2: Establish a connection
            String url = "jdbc:postgresql://localhost:5432/Site";
            conn = DriverManager.getConnection(url, "postgres", "0000");

            return true;
        } catch (SQLException e) {
            System.out.println("ERROR: SQLException connection: "+e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: ClassNotFoundException connection: "+e.getMessage());
            return false;
        }
    }
    @Override
    public int loginDB(String username, String password){
        try {
            // Step 3: Create a statement
            Statement stmt = conn.createStatement();
            // Step 4: Execute an insert
            String sql = "select id from users where username = '"+ username +"' and password ='"+ password+"' ";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
             int countResult=rs.getInt("id");
                 if(countResult == 0) {
                     rs.close();
                     stmt.close();
                     return 0;
                 }
                 else if(countResult >= 1) {
                     rs.close();
                     stmt.close();
                   return countResult;
                }
            }
            // Step 5: Close the resources
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("ERROR: SQLException loginDB "+e.getMessage());
        }
        return -1;
    }
    public void selectAll(){
        try {

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT title, manhwa_status as Status FROM manhwa  UNION SELECT title, manga_status FROM manga order by title;");

            QueryDisplay queryDisplay =new QueryDisplay();
            queryDisplay.display(rs);

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("ERROR: SQLException select EVERYTHING "+e.getMessage());
        }
    }
    public void selectManhwa() {
        try {

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT title, manhwa_status as status FROM manhwa order by id_manhwa");

            QueryDisplay queryDisplay =new QueryDisplay();
            queryDisplay.display(rs);

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("ERROR: SQLException select manhwa "+e.getMessage());
        }
    }
    public void selectManga() {
        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT title, manga_status as status FROM Manga order by id");

            QueryDisplay queryDisplay = new QueryDisplay();
            queryDisplay.display(rs);

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(" select manga "+e.getMessage());
        }
    }

    public void myManga(int idUser){
        try {
            String sql = "SELECT title, user_status as status, user_chapter as Current_Chapter, user_score as Your_score \n" +
                    "FROM manga \n" +
                    "JOIN bookmark_manga ON manga.id = bookmark_manga.id_manga \n" +
                    "WHERE id_user = ?\n" +
                    "ORDER BY user_status DESC ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idUser);
            ResultSet rs = stmt.executeQuery();

            QueryDisplay queryDisplay = new QueryDisplay();
            queryDisplay.display(rs);

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(" select MY manga "+e.getMessage());
        }
    }

    public void myManhwa(int idUser){
        try {
            String sql = "SELECT title, user_status as status, user_chapter as Current_Chapter, user_score as Your_score \n" +
                    "FROM manhwa \n" +
                    "JOIN bookmark_manhwa ON manhwa.id_manhwa = bookmark_manhwa.id_manhwa \n" +
                    "WHERE id_user = ?\n" +
                    "ORDER BY user_status DESC ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idUser);
            ResultSet rs = stmt.executeQuery();

            QueryDisplay queryDisplay = new QueryDisplay();
            queryDisplay.display(rs);

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(" select MY manga "+e.getMessage());
        }
    }

    public void selectFrom(String tableName){
            try {

                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName );

                QueryDisplay queryDisplay = new QueryDisplay();
                queryDisplay.display(rs);

                rs.close();
                stmt.close();
            }
             catch (SQLException e) {
                 System.out.println("ERROR: SQLException select manga "+e.getMessage());
            }
    }

    public void insert(String table, String title, String mangaStatus) {
        try {
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO "+ table +" ( title, manga_status) VALUES ('" + title +  "','"+ mangaStatus + "')";
            stmt.executeUpdate(sql);

            stmt.close();
        } catch (SQLException e) {
            System.out.println("ERROR: SQLException insert manga1"+e.getMessage());
        }
    }
    public void update(String title,int chapter ,String myStatus,String table, int idUser) {
        try {
            int id = getId(title, table);
            //TODO: check if you need to update or create a new raw
            String sql ="Select id_connection from bookmark_"+table+" WHERE id_" + table + " = "+id+" and id_user = "+ idUser;
            Statement statement= conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                PreparedStatement stmt = conn.prepareStatement("UPDATE bookmark_" + table + " SET user_chapter = ?, user_status = ? WHERE id_" + table + " = ? and id_user = ?");;
                if (id > 0) {
                    stmt.setInt(1, chapter);
                    stmt.setString(2, myStatus);
                    stmt.setInt(3, id);
                    stmt.setInt(4, idUser);

                    stmt.executeUpdate();
                }
                stmt.close();
            }
            else sql = "INSERT INTO  bookmark_" + table + "(id_user, id_" + table + ", user_chapter, user_status) VALUES ('" + idUser + "','"+  id+   "','"+chapter +"','"+ myStatus+"')";

            statement.executeUpdate(sql);
            statement.close();

        } catch (SQLException e) {
            System.out.println("ERROR: SQLException update chapters "+e.getMessage());
        }
    }
    public String whatTableBook(String title) {

        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT title FROM manhwa order title="+title;

            ResultSet rs= stmt.executeQuery(sql);
            if(rs.next()) {
                String test= rs.getString(1);
                if(test.contains(title)){
                    return "bookmark_manhwa";
                }
            }

            sql = "SELECT title FROM manga order title="+title;
            rs= stmt.executeQuery(sql);
            if(rs.next()) {
                String test= rs.getString(1);
                if(test.contains(title)){
                    return "bookmark_manga";
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("ERROR: SQLException insert manga1"+e.getMessage());
        }
        return "";
    }

    public String getName(int id){
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username FROM users where id='"+ id+ "'"  );
            if(rs.next()){
                String name=rs.getString(1);
                if(!name.isEmpty()) {
                    rs.close();
                    stmt.close();
                    return name;
                }
                else {
                    rs.close();
                    stmt.close();
                    return new String();
                }

            }


            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            System.out.println("ERROR: SQLException get name "+e.getMessage());
        }
        return new String();
    }
    public int getUserId(String name){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM users where username='"+ name+ "'"  );
            if(rs.next()){
                int id=rs.getInt(1);
                if(id!=0) {
                    rs.close();
                    stmt.close();
                    return id;
                }
                else {
                    rs.close();
                    stmt.close();
                    return 0;
                }

            }


            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            System.out.println("ERROR: SQLException get User id "+e.getMessage());
        }
        return 0;
    }
    public int getId(@NotNull String title, @NotNull String table){
        try {
            PreparedStatement stmt = null;
            if(table.equals("manga"))
                stmt = conn.prepareStatement("SELECT id FROM manga WHERE title=?");
            else if (table.equals("manhwa")) {
                stmt = conn.prepareStatement("SELECT id_manhwa FROM manhwa WHERE title=?");
            }
            else return -2;
            // bind title to the statement
            stmt.setString(1, title);
            // execute the statement
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                int result = rs.getInt(1);
                rs.close();
                stmt.close();
                return result;
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            System.out.println("ERROR: SQLException getId "+e.getMessage());
        }
        return -1;
    }

   /* public int getId(@NotNull String title, @NotNull String table){

        try {
            Statement stmt = conn.createStatement();
            String sql;
            if(table.equals("manga"))
                sql = "SELECT id_manga FROM manga WHERE title=" + title;
            else if (table.equals("manhwa")) {
                sql = "SELECT id FROM manhwa WHERE title=" + title;
            }
            else return -2;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                int result = rs.getInt(1);
                return result;
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            System.out.println("ERROR: SQLException getId "+e.getMessage());
        }

        return -1;
    }

    */
    @Override
    public void insert(String table, String title) {
        try {
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO "+ table +" (title) VALUES ('" + title + "')";
            stmt.executeUpdate(sql);

            stmt.close();
        } catch (SQLException e) {
            System.out.println("ERROR: SQLException insert @overdrive "+e.getMessage());
        }
    }
    public boolean checkUsername(String username){
        try {
            String sql = "SELECT username FROM users where username=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                System.out.println(username);
                String result=rs.getString("username");
                if(result.isEmpty() || result.length()<3 || result.equals("") || result.isBlank() || rs.wasNull()) {
                    rs.close();
                    stmt.close();
                    return true;
                }
                else {
                    rs.close();
                    stmt.close();
                    return false;
                }

            }
            else {
                rs.close();
                stmt.close();
                return true;
            }
        }
        catch (SQLException e){
            System.out.println("ERROR: SQLException check Username "+e.getMessage());
        }
        return false;
    }
    @Override
    public boolean registerDB(String username,String password, String email){
    try {
        String sql = "INSERT INTO users(username, password, email) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.setString(3, email);
        stmt.executeUpdate();
        stmt.close();
        return true;
    }
    catch (SQLException e){
        System.out.println("ERROR: SQLException register "+e.getMessage());
    }

        return false;
    }
    public void close() {
        // Step 6: Close the connection
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("ERROR: SQLException close"+e.getMessage());
        }
    }
    public void comboBox (JComboBox titleCombo){
        try {

            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select title from manga");
            while (resultSet.next()) {
                titleCombo.addItem(resultSet.getString(1));
            }

            resultSet = stmt.executeQuery("select title from manhwa");

            while (resultSet.next()) {
                titleCombo.addItem(resultSet.getString(1));
            }

            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("ERROR: SQLException comboBox "+e.getMessage());

        }
    }
    public boolean isAdmin(int id){
        try {
            String sql="SELECT id FROM admin where id_user= "+id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                int result = rs.getInt(1);
                return true;
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            System.out.println("ERROR: SQLException isAdmin "+e.getMessage());
        }
        return false;
    }
    public void deleteUser(int id) {
        try {
            Statement stmt = conn.createStatement();
            if(getName(id).length()>3) {
                String sql = "DELETE FROM users WHERE id = " + id;
                stmt.executeUpdate(sql);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error deleteUser "+ e.getMessage());
        }
    }
    public void deleteTitle(String table, String title, String bookmark) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM " + table + " WHERE title = ?;");
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM " + bookmark + " WHERE id_"+table+" = ?;");

            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery("SELECT id FROM " + table + " WHERE title = ?");
            int id = -1;
            if (rs.next()) {
                id = rs.getInt("id");
            }
            stmt.executeUpdate();
            stmt2.setInt(1, id);
            stmt2.executeUpdate();

            stmt.close();
            stmt2.close();
        } catch (SQLException e) {
            System.out.println("Error deleteTile "+e.getMessage());
        }
    }


}