package Model;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class RegistrationModel {

    public boolean isValidUsername(@NotNull String username) {
        return username.length()>=4;
    }

    public boolean isValidPassword(@NotNull String password) {
        return password.length() >= 4;
    }
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }


}
