package hospital;

import consultas.Consulta;
import consultorios.Consultorio;
import medicos.Medico;
import paciente.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Hospital {
    public ArrayList<Paciente> listaPacientes = new ArrayList<>();
    public ArrayList<Medico> listaMedicos = new ArrayList<>();
    public ArrayList<Consulta> listaConsultas = new ArrayList<>();
    public ArrayList<Consultorio> listaConsultorios = new ArrayList<>();
    private ValidadorHospital validador = new ValidadorHospital();


    public void registrarPaciente(Paciente paciente) {
        this.listaPacientes.add(paciente);
    }

    public void registrarMedico(Medico medico) {
        this.listaMedicos.add(medico);
    }

    public void registrarConsulta(Consulta consulta) {
        // paciente no tenga consulta en esa fecha

        if (!validador.validarDispolnibilidadEnFechaConsulta(consulta.getFechaHora(), consulta.getConsultorio().getNumeroConsultorio(), this.listaConsultas)) {
            System.out.println("Ya existe una consulta registrada par esa fecha");
            return;
        }


        if (!validador.validarDisponibilidadMedico(consulta.getFechaHora(), consulta.getMedico().getId(), this.listaConsultas)) {
            System.out.println("El medico no tiene disponibilidad en esa fecha");
            return;
        }

        this.listaConsultas.add(consulta);
    }


    public void registrarConsultorios(Consultorio consultorio) {
        this.listaConsultorios.add(consultorio);
    }

    public void mostrarPacientes() {
        for (Paciente paciente : this.listaPacientes) {
            System.out.println(paciente.mostrarDatos());
        }
    }

    public void mostrarMedicos() {
        for (Medico medico : this.listaMedicos) {
            System.out.println("ID: " + (medico.getId()));
            System.out.println("Nombre: " + (medico.getNombre()));
            System.out.println("Apellidos: " + (medico.getApellidos()));
            System.out.println("Fecha de nacimiento: " + (medico.getFechaNacimiento()));
            System.out.println("Telefono: " + (medico.getTelefono()));
            System.out.println("RFC: " + (medico.getRfc()));
            System.out.println(" ");
        }
    }

    public void mostrarConsultorios() {
        for (Consultorio consultorio : this.listaConsultorios) {
            System.out.println("ID: " + (consultorio.getId()));
            System.out.println("Piso: " + (consultorio.getPiso()));
            System.out.println("Numero de consultorio: " + (consultorio.getNumeroConsultorio()));
            System.out.println(" ");
        }
    }

    public String generarIDPaciente() {

        Random random = new Random();
        LocalDate fecha = LocalDate.now();
        int anoActual = fecha.getYear();
        int mesActual = fecha.getMonthValue();
        int longitudPacientesMasUno = this.listaPacientes.size() + 1;
        int numeroAleatorio = random.nextInt(100000)+1;

        String id = String.format("P%d%d%d%d", anoActual, mesActual, longitudPacientesMasUno, numeroAleatorio);
        return id;

    }

    public Paciente obtenerPacientePorId(String Paciente){
        return listaPacientes.stream().filter(paciente -> paciente.getId().equals(Paciente)).findFirst().orElse(null);
    }
    public void mostrarPacientePorId(String id) {

        for (Paciente paciente : this.listaPacientes) {
            if (paciente.getId().equals(id)) {
                System.out.println(paciente.mostrarDatos());
                return;
            }
        }
        System.out.println("Paciente no encontrado");

    }

    public String generarIDMedico(Medico medico) {
        //M-{Primeras 2 letras de su apellido} - {ultimo dígito de su año de nacimiento} - {año actual} - {numero aleatorio entre 50 y 700000} - {longitud de la lista de medicos + 1}
        LocalDate fecha = LocalDate.now();
        int anoActual = fecha.getYear();
        Random random = new Random();
        int numeroAleatorio = 50 + random.nextInt(700000 - 50);
        int longitudMedicosMasUno = this.listaMedicos.size() + 1;
        String anioEnCadena =  String.valueOf();
        char ultimoDigitoNacimiento = medico.fechaNacimiento.charAt(7);
        String primerasLetrasApellido = medico.apellidos.substring(0, 2).toUpperCase();
        String id = String.format("M%s%c%d%d%d", primerasLetrasApellido, ultimoDigitoNacimiento, anoActual, numeroAleatorio, longitudMedicosMasUno);
        return id;

    }

    public void mostrarMedicoPorID(String id) {
        for(Medico medico : this.listaMedicos){
            if (medico.getId().equals(id)) {
                System.out.println(medico.mostrarDatos());
                return;
            }
        }
        System.out.println("Medico no encontrado");
    }


    public String generarIDConsultorio(Consultorio consultorio){
        // C-{longitud de la lista de consultorios + 1}-{dia actual}-{año actual}-{numero aleatorio entre 1 y 500000}
        Random random = new Random();
        int numeroAleatorio = random.nextInt(500000)+1;
        LocalDate fecha = LocalDate.now();
        int diaActual = fecha.getDayOfMonth();
        int anoActual = fecha.getYear();
        int longitudConsultoriosMasUno = this.listaConsultorios.size() + 1;
        String id= String.format("C%d%d%d%d", longitudConsultoriosMasUno, diaActual, anoActual, numeroAleatorio);
        return id;

    }
    public void mostrarConsultorioPorID(String id) {
        for (Consultorio consultorio : this.listaConsultorios) {
            if (consultorio.getId().equals(id)) {
                System.out.println(consultorio.mostrarDatos());
                return;
            }
        }
        System.out.println("Consultorio no encontrado");
    }


}