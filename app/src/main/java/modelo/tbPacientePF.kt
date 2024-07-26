package modelo

data class tbPacientePF(
    val uuid: String,
    val NombrePaciente: String,
    val ApellidoPaciente: String,
    val EdadPaciente: Int,
    val EnfermedadPaciente: String,
    val NumeroHabitacion: String,
    val NumeroCama: Int,
    val MedicamentoAsignado: String,
    val FechaNacimiento: String,
    val HoraAplicacion: String
)
