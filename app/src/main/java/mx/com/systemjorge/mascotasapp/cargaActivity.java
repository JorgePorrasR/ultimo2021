package mx.com.systemjorge.mascotasapp;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class cargaActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_carga2);
        rotarImagen(imageView);
        imageView =  (ImageView) findViewById(R.id.Imagen7);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        rotarImagen(imageView);
    }

    private void rotarImagen(View view){
        RotateAnimation animation = new RotateAnimation(0, 25,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animation.setDuration(2000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);}
}
