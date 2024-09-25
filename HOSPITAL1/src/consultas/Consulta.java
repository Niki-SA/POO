package consultas;

import consultorios.Consultorio;
import medicos.Medico;
import paciente.Paciente;

import java.time.LocalDateTime;

public class Consulta {
    public int id;

    public LocalDateTime fechaHora;

    public Paciente paciente;

    public Medico medico;

    public Consultorio consultorio;

    public Consulta(int id, LocalDateTime fechaHora, Paciente paciente, Medico medico, Consultorio consultorio) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.medico = medico;
        this.consultorio = consultorio;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }
    public String mostrarConsulta() {
        return String.format("Fecha: %s, Paciente: %s, Medico: %s, Num.Consultorio: %d", fechaHora, paciente, medico, consultorio);
    }
}