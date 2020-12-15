package mx.com.systemjorge.mascotasapp.Providers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PerritosProvider {

    private DatabaseReference mDatabase;

    public PerritosProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getPerritos() {
        return mDatabase;
    }

}
