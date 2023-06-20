import com.google.gson.Gson
import controlador.Crud

fun main(args: Array<String>) {

    val gson = Gson()
    val crud = Crud(gson)
    val console = Console(crud)

    console.mostrarMenuPrincipal()
}
