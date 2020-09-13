package Services;

import com.google.android.material.snackbar.Snackbar;

public class Validators {
    public static boolean IsEmailFormatValid(String email){
        if(email.matches("[0-9a-zA-Z]+@[a-zA-Z]{3,}+.[a-zA-Z]+")){
            return true;
        }
        return false;
    }
}
