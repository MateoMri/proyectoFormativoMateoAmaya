package mateo.amaya.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import modelo.ClaseConexion
import java.util.UUID

class AgregarPaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_paciente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
     //1- mando a llamar a todos los elementos que tengo en la vista
        val txtNombrePaciente = findViewById<TextView>(R.id.txtNombrePaciente)
        val txtApellidoPaciente = findViewById<TextView>(R.id.txtApellidoPaciente)
        val txtEdadPaciente = findViewById<TextView>(R.id.txtEdadPaciente)
        val txtEnfermedadPaciente = findViewById<TextView>(R.id.txtEnfermedadPaciente)
        val txtNumeroHabitacion = findViewById<TextView>(R.id.txtNumeroHabitacion)
        val txtNumeroCama = findViewById<TextView>(R.id.txtNumeroCama)
        val txtMedicamentoAsignado = findViewById<TextView>(R.id.txtMedicamentoAsignado)
        val txtFechaNacimiento = findViewById<TextView>(R.id.txtFechaNacimiento)
        val txtHoraAplicacion = findViewById<TextView>(R.id.txtHoraAplicacion)
        val btnLimpiarPaciente = findViewById<Button>(R.id.btnLimpiarPaciente)
        val btnSubirPaciente = findViewById<Button>(R.id.btnSubirPaciente)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)
        //2- programo el boton de subir
        btnSubirPaciente.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                //crear un objeto de la clase dconexion

                val objConexion = ClaseConexion().cadenaConexion()

                //Crear una variable que contenga un PrepareStatement

                val addPaciente = objConexion?.prepareStatement("insert into tbPacientePF (uuid, NombrePaciente, ApellidoPaciente, EdadPaciente, EnfermedadPaciente, NumeroHabitacion, NumeroCama, MedicamentoAsigando, FechaNacimiento, HoraAplicacion) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")!!
                addPaciente.setString(1, UUID.randomUUID().toString())
                addPaciente.setString(2, txtNombrePaciente.text.toString())
                addPaciente.setString(3, txtApellidoPaciente.text.toString())
                addPaciente.setInt(4, txtEdadPaciente.text.toString().toInt())
                addPaciente.setString(5, txtEnfermedadPaciente.text.toString())
                addPaciente.setString(6, txtNumeroHabitacion.text.toString())
                addPaciente.setInt(7, txtNumeroCama.text.toString().toInt())
                addPaciente.setString(8, txtMedicamentoAsignado.text.toString())
                addPaciente.setString(9, txtFechaNacimiento.text.toString())
                addPaciente.setString(10, txtHoraAplicacion.text.toString())
                addPaciente.executeUpdate()
                val commit = objConexion.prepareStatement("commit")
                commit.executeUpdate()
            }
        }
        btnRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}