package RecyclerViewHelpers

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mateo.amaya.myapplication.AgregarPaciente
import mateo.amaya.myapplication.R
import modelo.ClaseConexion
import modelo.tbPacientePF

class Adaptador(var Datos: List<tbPacientePF>): RecyclerView.Adapter<ViewHolder>(){



    fun eliminarPaciente(uuid: String, position: Int) {
        val listaDatos = Datos .toMutableList()
        listaDatos.removeAt(position)
        CoroutineScope(Dispatchers.IO).launch {
            val objConexion = ClaseConexion().cadenaConexion()
            val borrarPaciente = objConexion?.prepareStatement("delete from tbPacientePF where uuid = ?")!!
            borrarPaciente.setString(1, uuid)
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
        holder.txtNombreCard.text = item.NombrePaciente

        holder.btnBorrarPaciente.setOnClickListener {
            eliminarPaciente(item.uuid, position)
        }
        holder.btnEditarPaciente.setOnClickListener {
            //codigo para editar
        }
        holder.itemView.setOnClickListener {
            //aqui se pone lo que se hara al dar clic a la card

        }
    }


}