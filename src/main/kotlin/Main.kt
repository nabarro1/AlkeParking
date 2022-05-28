import java.util.*
class Main {
    companion object {
        private val parking = Parking(mutableSetOf())
        lateinit var parkingSpace: ParkingSpace

        @JvmStatic
        fun main(args: Array<String>) {
            showMenu()
        }

        private var option = 0

        private fun showMenu() {
            do {
                println("------------------------")
                println("Bienvenido a AlkeParking!! \n 1. Ingresa vehiculo \n 2. Elimina vehiculo \n 3. Lista de vehiculos\n 4. Ver ganancias \n 5. Salir")
                option = readLine()?.toInt()!!
                println("Elige una opción $option")
                when (option) {
                    1 -> {
                        println("Agrega tipo de vehiculo: \n 1.Auto \n 2.Motocicleta \n 3.Minibus \n 4.Bus")
                        val vehicleType = readLine()?.toInt()
                        println("Ingresar patente: ")
                        val vehiclePlate = readLine()?.toUpperCase()
                        println("Tienes una tarjeta de descuento?")
                        val discountCard = readLine()
                        addVehicle(vehiclePlate, vehicleType, discountCard)
                    }
                    2 -> {
                        println("Introducir la patente a retirar:")
                        val vehiclePlate = readLine()?.toUpperCase()
                        removeVehicle(vehiclePlate)
                    }
                    3 -> {
                        println("---- Patentes en AlkeParking ----")
                        parking.listVehicles(parking.vehicles)
                    }
                    4 -> parking.totalEarnings()
                }
            } while (option != 5)
        }

        /**
        Este método agrega los vehículos y, a su vez, valida que los vehículos no se repitan
        de lo contrario, devuelve un mensaje fallido

         */
        private fun addVehicle(vehiclePlate: String?, vehicleType: Int?, discountCard: String?) {

            val vehicleTypeInput = getVehicleType(vehicleType)
            val vehicle = vehiclePlate?.let { Vehicle(it, vehicleTypeInput, Calendar.getInstance(), discountCard) }
            when (vehicle?.let { parking.addVehicle(it) }) {
                true -> println("Bienvenido a AlkeParking!")
                else -> println("Lo sentimos, el check-in ha fallado")
            }
        }

        private fun getVehicleType(vehicleType: Int?): VehicleType {

            var vehicleTypeInput: VehicleType = VehicleType.BUS
            when (vehicleType) {
                1 -> vehicleTypeInput = VehicleType.CAR
                2 -> vehicleTypeInput = VehicleType.MOTORCYCLE
                3 -> vehicleTypeInput = VehicleType.MINIBUS
                4 -> vehicleTypeInput = VehicleType.BUS
            }
            return vehicleTypeInput
        }


        /**
        Este método retira los vehículos y calcula la tarifa que debe pagar, pero antes,
        validar que la placa del vehículo esté en el estacionamiento. Además, obtenemos el tipo de vehículo, la lista de vehículos,
        y el índice que ingresamos como parámetros en el método checkOutVehicle.
         */

        private fun removeVehicle(plate: String?) {

            try {
                val removeCar = parking.vehicles.find { it.plate == plate }!!
                parkingSpace = ParkingSpace(removeCar)
                val vehicleType = removeCar.type
                parkingSpace.setListVehicles(parking.vehicles)
                parkingSpace.checkOutVehicle(vehicleType)
            } catch (e: Exception) {
                println("Lo sentimos, el check-out ha fallado")
            }

        }


    }
}
/**
-- Lucas Nabarro
 */