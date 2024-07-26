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
                    val uuid = resultSet.getString("uuid")
                    val NombrePaciente = resultSet.getString("NombrePaciente")
                    val ApellidoPaciente = resultSet.getString("ApellidoPaciente")
                    val EdadPaciente = resultSet.getInt("EdadPaciente")
                    val EnfermedadPaciente = resultSet.getString("EnfermedadPaciente")
                    val NumeroHabitacion = resultSet.getString("NumeroHabitacion")
                    val NumeroCama = resultSet.getInt("NumeroCama")
                    val MedicamentoAsignado = resultSet.getString("MedicamentoAsignado")
                    val FechaNacimiento = resultSet.getString("FechaNacimiento")
                    val HoraAplicacion = resultSet.getString("HoraAplicacion")

                    val valoresJuntos = tbPacientePF(uuid, NombrePaciente, ApellidoPaciente, EdadPaciente, EnfermedadPaciente, NumeroHabitacion, NumeroCama, MedicamentoAsignado, FechaNacimiento, HoraAplicacion)

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