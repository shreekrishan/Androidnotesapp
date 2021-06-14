package common.survin_it.notesapp.common;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Common {
    // user temporary data
    public static String name,number,district,town;

    public static String userUniqueID;
    public static String selectedLanguage,isChangeLocation="0";

    public static String spaceType;

    // firebase integrate
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
}
