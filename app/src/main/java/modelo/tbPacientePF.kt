package modelo

data class tbPacientePF(
   val id_paciente : Int,
    var nombres : String,
    var apellidos : String,
    var fecha_nacimiento : String,
    var habitacion : Int,
    var cama : Int,
    var enfermedad : String,
    var medicina : String,
    var hora_aplicacion : String
)
