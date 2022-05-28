const val MAXIMUM_QUOTAS = 20

data class Parking(val vehicles: MutableSet<Vehicle>) {

    companion object {
        var totalEarnings: Pair<Int, Int> = Pair(0, 0)
    }

    fun addVehicle(vehicle: Vehicle): Boolean {
        val availableQuotas = when (vehicles.size < MAXIMUM_QUOTAS) {
            true -> vehicles.add(vehicle)
            else -> false
        }
        return availableQuotas
    }

    fun totalEarnings() = println("${totalEarnings.first} Los vehículos se han despachado y tienen ganancias de ${totalEarnings.second}")

    fun listVehicles(vehicle: MutableSet<Vehicle>) = vehicle.forEach { println(it.plate) }
}


/**
 *
 *
-- ¿Por qué se define Vehículo como un set?
Esto permite agregar elementos sin criterio de orden, y también ayuda a evitar la repetición de elementos.

-- ¿Puede cambiar el tipo de vehículo con el tiempo?
Sí, ya que podemos agregar o cambiar el tipo de vehículos que ingresan al estacionamiento con el tiempo.

-- ¿Debe definirse como variable o como constante en Vehicle?
Como variable, porque si se requiere agregar o editar más Vehículos ya existentes, la variable Tipo de Clase Vehículo debe ser una variable

-- ¿Dónde deben agregarse las propiedades checkInTime y discountCard, en ParkingSpace, Vehicle o ambos?
En la clase ParkingSpace, porque ya existe una instancia de Vehicle

-- ¿Cómo indicamos que un tipo de datos puede ser nulo en Kotlin?
Agregando el signo "?" para terminar la declaración
 *
 *
 */




