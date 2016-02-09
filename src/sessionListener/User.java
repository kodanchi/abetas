package sessionListener;

/**
 * Created by Mojahed on 2/9/2016.
 */
public class User {
    private int userId;
    private String username;
    private String email;
    private int userlvl;

    User(int id, String uname, String uemail, int ulvl){
        userId = id;
        username = uname;
        email = uemail;
        userlvl = ulvl;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getUserlvl() {
        return userlvl;
    }
}
