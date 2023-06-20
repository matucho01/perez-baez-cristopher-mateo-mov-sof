import com.google.gson.Gson
import controlador.Controller
import vista.View

fun main(args: Array<String>) {

    val gson = Gson()
    val controller = Controller(gson)
    val view = View(controller)

    view.mostrarMenu()
}
