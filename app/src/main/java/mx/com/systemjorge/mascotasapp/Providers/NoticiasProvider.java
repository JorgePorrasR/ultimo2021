package mx.com.systemjorge.mascotasapp.Providers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoticiasProvider {
    private DatabaseReference mDatabase;

    public NoticiasProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getNoticias() {
        return mDatabase;
    }
}
