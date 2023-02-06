package Model;

import Database.Database;

public class AdminModel {
    private int userId=-1;
    private int adminId=-1;
    private String username;
    private Database db;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AdminModel(int id){
        this.userId=id;
        this.db =new Database();
        this.username=db.getName(id);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    public int getAdminId() {
        return adminId;
    }


}
