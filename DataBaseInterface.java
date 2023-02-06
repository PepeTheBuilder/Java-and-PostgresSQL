package Database;

public interface DataBaseInterface {
    int loginDB(String username, String password);
    // checks if there exist a user with the same username and password
    // returns -1 if something went wrong, 0 if the password and username don't match, else an int which is the user id in the database;

    void insert(String table, String title);
    // insert a new manga or manhwa in the table

    boolean registerDB(String username, String password, String email);
    // insert into the database a new account into the user table

}
