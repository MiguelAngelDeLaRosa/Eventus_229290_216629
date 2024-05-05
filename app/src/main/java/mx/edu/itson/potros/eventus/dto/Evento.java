package mx.edu.itson.potros.eventus.dto;

import java.io.Serializable;
import java.util.Date;

public class Evento implements Serializable {

    private String nombreEvento;
    private String tipoEvento;
    private Paquete paquete;
    private Cliente cliente;
    private String fechaEvento;
    private Pago monto;
    private Horario horario;

    public Evento(String nombreEvento, String tipoEvento, String fechaEvento, Horario horario) {
        this.nombreEvento = nombreEvento;
        this.tipoEvento = tipoEvento;
        this.fechaEvento = fechaEvento;
        this.horario = horario;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Pago getMonto() {
        return monto;
    }

    public void setMonto(Pago monto) {
        this.monto = monto;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nombreEvento='" + nombreEvento + '\'' +
                ", tipoEvento='" + tipoEvento + '\'' +
                ", paquete=" + paquete +
                ", cliente=" + cliente +
                ", fechaEvento=" + fechaEvento +
                ", monto=" + monto +
                ", horario=" + horario +
                '}';
    }
}
