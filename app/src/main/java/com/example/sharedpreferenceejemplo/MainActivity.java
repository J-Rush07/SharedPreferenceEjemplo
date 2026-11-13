package com.example.sharedpreferenceejemplo;

import static android.app.ProgressDialog.show;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String dataUserCache = "dataUser";
    private static final int modo_private = Context.MODE_PRIVATE;

    SharedPreferences sharedPreferences;

    LinearLayout main;
    Button btnverde, btnazul, btnrojo, btnguardar;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnverde = findViewById(R.id.btnverde);
        main = findViewById(R.id.main);
        btnrojo = findViewById(R.id.btnrojo);
        btnazul = findViewById(R.id.btnazul);
        btnguardar = findViewById(R.id.btnguardar);

        sharedPreferences = getSharedPreferences(dataUserCache, modo_private);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        btnverde.setOnClickListener(v -> {
            editor.putString("color", "#4CAF50");
            editor.apply();
        });

        btnazul.setOnClickListener(v -> {
            editor.putString("color", "#2196F3");
            editor.apply();
        });

        btnrojo.setOnClickListener(v -> {
            editor.putString("color", "#F44336");
            editor.apply();
        });

        btnguardar.setOnClickListener(v -> {
            guardar();
            Toast.makeText(MainActivity.this, "Se guardo el color de la pantalla", Toast.LENGTH_LONG).show();
            if (token != null && !token.isEmpty()) {
                try {
                    main.setBackgroundColor(Color.parseColor(token));
                } catch (IllegalArgumentException e) {
                  Toast.makeText(MainActivity.this, "Error en la capa 8", Toast.LENGTH_LONG).show();
                }
            }
        });

        guardar();
        if (token != null && !token.isEmpty()) {
            try {
                main.setBackgroundColor(Color.parseColor(token));
            } catch (IllegalArgumentException e) {
                Toast.makeText(MainActivity.this, "Error en los colores", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void guardar() {
        token = sharedPreferences.getString("color", "#FFFFFF");
    }
}
