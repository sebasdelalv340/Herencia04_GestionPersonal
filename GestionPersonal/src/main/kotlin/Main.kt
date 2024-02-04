/**
 * Clase base que representa a una persona con un nombre y edad.
 *
 * @property nombre El nombre de la persona.
 * @property edad La edad de la persona.
 */
open class Persona(nombre: String, edad: Int) {
    open var nombre: String = nombre
    open var edad: Int = edad

    /**
     * Inicializa la clase Persona con un nombre y una edad.
     *
     * @throws 'IllegalArgumentException' Si la edad es negativa o el nombre está vacío.
     */
    init {
        require(edad >= 0) {"La edad no puede ser negativa"}
        require(nombre.isNotBlank()) {"El nombre no puede estar vacío"}
    }

    /**
     * Convierte la información de la persona a una cadena.
     *
     * @return Representación de la persona como cadena.
     */
    override fun toString() = "Nombre: $nombre, Edad: $edad"

    /**
     * Incrementa la edad de la persona y devuelve un mensaje de cumpleaños.
     *
     * @return Mensaje de cumpleaños.
     */
    open fun celebrarCumple(): String {
        edad++
        return "Feliz cumpleaños $nombre!, Ahora tienes $edad años."
    }
}


/**
 * Clase que representa a un empleado con salario base y porcentaje de impuestos.
 *
 * @property salarioBase El salario base del empleado.
 * @property porcentajeImpuestos El porcentaje de impuestos aplicado al salario base.
 */
open class Empleado(nombre: String, edad: Int, salarioBase: Double, porcentajeImpuestos: Double = 10.0): Persona(nombre, edad) {

    //Intento que acepte como argumento un Int pero no lo consigo
    open var salarioBase = salarioBase
        set(value) {
            field = value.toInt().toDouble()
        }

    open var porcentajeImpuestos: Double = porcentajeImpuestos
        set(value) {
            field = value.toInt().toDouble()
        }


    /**
     * Calcula el salario del empleado aplicando el porcentaje de impuestos al salario base.
     */
    open fun calcularSalario() {
        salarioBase -= ((salarioBase * porcentajeImpuestos) / 100)
    }

    /**
     * Formatea un valor double con dos decimales.
     *
     * @param salarioBase Valor double a formatear.
     * @return Valor formateado como cadena.
     */
    open fun formatDouble(salarioBase: Double): String {
        return String.format("%.2f", salarioBase)
    }

    /**
     * Convierte la información del empleado a una cadena.
     *
     * @return Representación del empleado como cadena.
     */
    override fun toString(): String {
        return "${super.toString()}, Salario: ${formatDouble(salarioBase)}"
    }

    /**
     * Realiza la tarea genérica de trabajo para un empleado.
     *
     * @return Mensaje de trabajo.
     */
    open fun trabajar() = "$nombre está trabajando en la empresa."
}


/**
 * Clase que representa a un gerente, que es un tipo especializado de empleado con un bonus y exención de impuestos.
 *
 * @property bonus El bonus adicional para el gerente.
 * @property exentoImpuestos Indica si el gerente está exento de impuestos.
 */
class Gerente(nombre: String, edad: Int, salarioBase: Double, porcentajeImpuestos: Double, private var bonus: Double, exentoImpuestos: Boolean): Empleado(nombre, edad, salarioBase, porcentajeImpuestos) {
    private var exentoImpuestos: Boolean = false

    /**
     * Porcentaje de impuestos específico para los gerentes.
     */
    override var porcentajeImpuestos: Double = 33.99

    /**
     * Calcula el salario del gerente, teniendo en cuenta el bonus y la exención de impuestos.
     */
    override fun calcularSalario() {
        if (exentoImpuestos) {
            salarioBase += bonus
        } else
            super.calcularSalario()
        salarioBase += bonus
    }

    /**
     * Convierte la información del gerente a una cadena.
     *
     * @return Representación del gerente como cadena.
     */
    override fun toString(): String {
        return "${super.toString()}, Gerente"
    }

    /**
     * Realiza la tarea específica de administrar la empresa para un gerente.
     *
     * @return Mensaje de trabajo del gerente.
     */
    override fun trabajar(): String = "$nombre está administrando la empresa."
}


fun main() {
    val persona = Persona("Sebas", 35)
    val empleado = Empleado("Jesús", 30, 1200.0, 19.0)
    val gerente = Gerente("Ana", 27, 1600.0, 25.0, 100.0, false)

    /**
     * Compruebo que crea la clase Persona y que la función celebrarCumple() se ejecuta correctamente.
     */
    println(persona)
    println(persona.celebrarCumple())

    /**
     * Compruebo que crea la clase Empleado y que la función calcularSalario() se ejecuta correctamente.
     */
    println(empleado)
    empleado.calcularSalario()
    println(empleado)

    /**
     * Compruebo que crea la clase Gerente y que la función calcularSalario() se ha sobreescrito de forma correcta.
     */
    gerente.calcularSalario()
    println(gerente)
}