package co.edu.unimagdalena.apmoviles.universidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListadoActivity extends AppCompatActivity {

    ListView listado;
    EstudianteController estudianteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listado = findViewById(R.id.lstlistado);
        estudianteController = new EstudianteController(this);
        Cursor c = estudianteController.allEstudiantes2();
        EstudianteCursorAdapter ecursor = new EstudianteCursorAdapter(this, c, false);
        listado.setAdapter(ecursor);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView cod = view.findViewById(R.id.txtcodigo);
                Intent intent = new Intent(getApplicationContext(), EditarEstudiante.class);
                intent.putExtra("codigo_estudiante",cod.getText());
                startActivity(intent);
            }
        });
        listado.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView cod = view.findViewById(R.id.txtcodigo);
                estudianteController.Eliminar(cod.getText().toString());
                ActualizarLista();
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ActualizarLista();
    }

    private void ActualizarLista() {
        Cursor c = estudianteController.allEstudiantes2();
        EstudianteCursorAdapter ecursor = new EstudianteCursorAdapter(this, c, false);
        listado.setAdapter(ecursor);
    }
}
