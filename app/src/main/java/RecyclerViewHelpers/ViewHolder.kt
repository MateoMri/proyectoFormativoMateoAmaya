package RecyclerViewHelpers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mateo.amaya.myapplication.R

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    //En el ViewHolder mando a llamar a los elementos de la card
    val txtNombreCard = view.findViewById<TextView>(R.id.txtNombreCard)
    val btnEditarPaciente = view.findViewById<ImageView>(R.id.btnEditarPaciiente)
    val btnBorrarPaciente = view.findViewById<ImageView>(R.id.btnBorrarPaciente)

}