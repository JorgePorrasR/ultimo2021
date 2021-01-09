package mx.com.systemjorge.mascotasapp.Providers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FechasProvider {
    private DatabaseReference mDatabase;

    public FechasProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("lugares");
    }

    public DatabaseReference getLugares() {
        return mDatabase;
    }
}
