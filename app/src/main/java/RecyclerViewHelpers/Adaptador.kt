package RecyclerViewHelpers

import android.content.*
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mateo.amaya.myapplication.AgregarPaciente
import mateo.amaya.myapplication.DetallesPaciente
import mateo.amaya.myapplication.R
import modelo.ClaseConexion
import modelo.tbPacientePF

class Adaptador(var Datos: List<tbPacientePF>): RecyclerView.Adapter<ViewHolder>(){



    fun eliminarPaciente(id_paciente : Int, position: Int) {
        val listaDatos = Datos .toMutableList()
        listaDatos.removeAt(position)
        CoroutineScope(Dispatchers.IO).launch {
            val objConexion = ClaseConexion().cadenaConexion()
            val borrarPaciente = objConexion?.prepareStatement("delete from tbPacientePF where id_paciente = ?")!!
            borrarPaciente.setInt(1, id_paciente)
            borrarPaciente.executeUpdate()
            val commit = objConexion.prepareStatement( "commit")!!
            commit.executeUpdate()
        }
        Datos=listaDatos.toList()
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Datos[position]
        holder.txtNombreCard.text = "${item.nombres}  ${item.apellidos}"

        holder.btnBorrarPaciente.setOnClickListener {
            eliminarPaciente(item.id_paciente, position)
        }
        holder.btnEditarPaciente.setOnClickListener {
            //codigo para editar
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetallesPaciente::class.java)
            intent.putExtra("nombre", item.nombres)
            intent.putExtra("apellido",item.apellidos)
            intent.putExtra("enfermedad",item.enfermedad)
            intent.putExtra("medicina",item.medicina)
            intent.putExtra("habitacion", item.habitacion)
            intent.putExtra("cama",item.cama)
            intent.putExtra("fecha",item.fecha_nacimiento)
            intent.putExtra("hora",item.hora_aplicacion)
            holder.itemView.context.startActivity(intent)
        }
    }


}