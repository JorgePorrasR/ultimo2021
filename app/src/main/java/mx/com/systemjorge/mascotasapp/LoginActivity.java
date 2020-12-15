package mx.com.systemjorge.mascotasapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import mx.com.systemjorge.mascotasapp.Activities.PrimerActivity;


public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail, txtPassword;
    private Button btnLogin;

    private FirebaseAuth mAuth;

    //Elementos para la animación de carga
    private Dialog mDialogLoading;
    private LottieAnimationView mAnimationLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.User);
        txtPassword = findViewById(R.id.Pass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        mDialogLoading = new Dialog(this);
        mDialogLoading.setContentView(R.layout.dialog_carga);
        mAnimationLoading = mDialogLoading.findViewById(R.id.animationLoading);
        mDialogLoading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    } // <!-- FIN DEL MÉTODO onCreate --> //

    @Override
    protected void onStart() {
        super.onStart();
        //Método para saber si ya hay una sesión activa
        if (mAuth.getCurrentUser() != null) { //Si hay una sesión activa...
            Intent intent = new Intent(LoginActivity.this, PrimerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void login() {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) { //Si el campo email y password no está vacío...
            if(password.length() >= 6) { //Si la cantidad de caracteres en la contraseña es mayor o igual a 6...
                mDialogLoading.show();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) { //Si la tarea fue exitosa (El login se realizó correctamente)...
                            Intent intent = new Intent(LoginActivity.this, PrimerActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "El correo y/o la contraseña son incorrectos", Toast.LENGTH_SHORT).show();
                        }
                        mDialogLoading.dismiss();
                    }
                   //broooo ya se logeo
                    //no solo la deje cargando pero al mostrar los recycler se cierra
                });






            } else {
                Toast.makeText(LoginActivity.this, "La contraseña debe tener al menos 6 caracteres.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "Debes de llenar todos los campos.", Toast.LENGTH_SHORT).show();
        }
    }
}