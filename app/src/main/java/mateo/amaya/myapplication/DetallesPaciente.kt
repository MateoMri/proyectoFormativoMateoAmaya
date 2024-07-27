package mateo.amaya.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetallesPaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles_paciente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombre = intent.getStringExtra("nombre")
        val apellido= intent.getStringExtra("apellido")
        val enfermedad= intent.getStringExtra("enfermedad")
        val medicina= intent.getStringExtra("medicina")
        val habitacion= intent.getIntExtra("habitacion",0)
        val cama = intent.getIntExtra("cama",0)
        val fecha = intent.getStringExtra("fecha")
        val hora = intent.getStringExtra("hora")

        val btnDetalleRegresar = findViewById<Button>(R.id.btnDetalleRegresar)
        val tvNombrePaciente = findViewById<TextView>(R.id.tvNombrePaciente)
        val tvEnfermedadPaciente = findViewById<TextView>(R.id.tvEnfermedadPaciente)
        val tvMedicinaPaciente = findViewById<TextView>(R.id.tvMedicinaPaciente)
        val tvHabitacionPaciente = findViewById<TextView>(R.id.tvHabitacionPaciente)
        val tvFecha = findViewById<TextView>(R.id.tvFecha)
        btnDetalleRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        tvNombrePaciente.text = "Paciente: $nombre $apellido"
        tvEnfermedadPaciente.text = "Enfermedad: $enfermedad"
        tvMedicinaPaciente.text = "Medicina: $medicina a las: $hora"
        tvFecha.text = "Fecha nacimiento: $fecha"
        tvHabitacionPaciente.text = "Cama $cama en la habitacion $habitacion"
    }
}