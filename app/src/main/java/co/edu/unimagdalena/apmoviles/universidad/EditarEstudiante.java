package co.edu.unimagdalena.apmoviles.universidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarEstudiante extends AppCompatActivity {

    Button btn_editar;
    EditText nombre_editar, programa_editar;
    EstudianteController estudianteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_estudiante);
        btn_editar = findViewById(R.id.btn_editar);
        nombre_editar = findViewById(R.id.editar_nombre_et);
        programa_editar = findViewById(R.id.editar_programa_et);
        estudianteController = new EstudianteController(this);
        final String codigo_estudiante = getIntent().getStringExtra("codigo_estudiante");

        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre_editar.getText().toString().isEmpty() || programa_editar.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Los campos no pueden ser vacíos", Toast.LENGTH_LONG).show();
                }else{
                    Estudiante estudiante = new Estudiante(codigo_estudiante,nombre_editar.getText().toString(),programa_editar.getText().toString());
                    estudianteController.ActualizarEstudiante(estudiante);
                    finish();
                }

            }
        });

    }



}