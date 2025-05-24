package com.cordy.bol.model;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class calculoHoras {

    public static long calcularHorasEnPuerto(Date fechaLlegada, Date fechaSalida) {
            // Validar que las fechas no sean nulas
            if (fechaLlegada == null || fechaSalida == null) {
                throw new IllegalArgumentException("Las fechas no pueden ser nulas");
            }

            // Calcular diferencia en milisegundos
            long diferenciaMs = fechaSalida.getTime() - fechaLlegada.getTime();

            // Convertir milisegundos a horas
            return TimeUnit.MILLISECONDS.toHours(diferenciaMs);
        }
}

