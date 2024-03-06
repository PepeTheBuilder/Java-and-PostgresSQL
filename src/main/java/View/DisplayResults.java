package View;
import java.sql.ResultSet;

interface DisplayResults {
    void display(ResultSet rs);
    /*
        Take as input a ResultSet and open a new window with all the information in rs in a table format
    */
}
