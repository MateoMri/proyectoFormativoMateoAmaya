package mateo.amaya.myapplication

import RecyclerViewHelpers.Adaptador
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.tbPacientePF

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rcvPaciente = findViewById<RecyclerView>(R.id.rcvPaciente)
        val btnIrAgregar = findViewById<Button>(R.id.btnIrAgregar)

        //agrego un layout al recyclerview
        rcvPaciente.layoutManager = LinearLayoutManager(this)

        //////// TODO: MOSTRAR DATOS

        fun obtenerPaciente(): List<tbPacientePF>{
            //creo un objeto de la clase conexion
            try {
                val objConexion = ClaseConexion().cadenaConexion()

                //creo un statement
                val statement = objConexion?.createStatement()
                val resultSet = statement?.executeQuery("SELECT * FROM tbPacientePF")!!

                val listaPaciente = mutableListOf<tbPacientePF>()

                while (resultSet.next()) {
                    val id = resultSet.getInt("id_paciente")
                    val NombrePaciente = resultSet.getString("nombres")
                    val ApellidoPaciente = resultSet.getString("apellidos")
                    val fecha_nacimiento = resultSet.getString("fecha_nacimiento")
                    val habitacion = resultSet.getInt("habitacion")
                    val cama = resultSet.getInt("cama")
                    val enfermedad = resultSet.getString("enfermedad")
                    val MedicamentoAsignado = resultSet.getString("medicina")
                    val HoraAplicacion = resultSet.getString("Hora_Aplicacion")

                    val valoresJuntos = tbPacientePF(id, NombrePaciente, ApellidoPaciente, fecha_nacimiento, habitacion, cama, enfermedad, MedicamentoAsignado,HoraAplicacion)

                    listaPaciente.add(valoresJuntos)
                }
                return listaPaciente
            } catch (e: Exception){
                return emptyList()
            }
        }
        //asignarle el adaptador al recyclerView
        CoroutineScope(Dispatchers.IO).launch {
            val PacienteDB = obtenerPaciente()
            withContext(Dispatchers.Main){
                val adapter = Adaptador(PacienteDB)
                rcvPaciente.adapter = adapter
            }
        }
        btnIrAgregar.setOnClickListener {
            val intent = Intent(this, AgregarPaciente::class.java)
            startActivity(intent)
        }
    }
}