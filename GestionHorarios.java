import java.util.List;
import java.util.ArrayList;

class Materia {
    public String nombre;
    public String codigo;
    public int creditos;
    public List<String> horariosDisponibles;

    public Materia(String nombre, String codigo, int creditos) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.horariosDisponibles = new ArrayList<>();
    }

    public void agregarHorario(String horario) {
        horariosDisponibles.add(horario);
    }

    public List<String> mostrarHorarioDispo() {
        return horariosDisponibles;
    }

    public String getName() {
        return nombre;
    }

    public String getCode() {
        return codigo;
    }

    public int getCredits() {
        return creditos;
    }
} 

class Estudiante {
    public String nombre;
    public String id;
    public List<MateriaInscrita> materiaInscritas;

    public Estudiante(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.materiaInscritas = new ArrayList<>();
    }

    public void incribirMateria(Materia materia, String horario) throws Exception {
        if (!materia.mostrarHorarioDispo().contains(horario)) {
            throw new Exception("El horario " + horario + " no está disponible para esta materia: " + materia.getName());
        }

        for (MateriaInscrita inscripcion : materiaInscritas) {
            if (inscripcion.getHorario().equals(horario)) {
                throw new Exception("El horario " + horario + " ya está ocupado por otra materia registrada");
            }
        }

        materiaInscritas.add(new MateriaInscrita(materia, horario));
    }

    public List<MateriaInscrita> mostrarHorarioComp() {
        return materiaInscritas;
    }

    public String getName() {
        return nombre;
    }

    public String getId() {
        return id;
    }
}

class MateriaInscrita {
    public Materia materia;
    public String horario;

    public MateriaInscrita(Materia materia, String horario) {
        this.materia = materia;
        this.horario = horario;
    }

    public Materia getMateria() {
        return materia;
    }

    public String getHorario() {
        return horario;
    }
}

class GestionHorario {
    public List<Materia> materias;
    public List<Estudiante> estudiantes;

    public GestionHorario() {
        this.materias = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }

    public void registrarMateria(Materia materia) {
        materias.add(materia);
    }

    public void inscribirEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }
    
    public List<Materia> getMaterias() {
        return materias;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }
}

public class GestionHorarios {
    public static void main(String[] args) {
        Materia matematicas = new Materia("Matematicas", "MT009", 4);
        Materia programacion = new Materia("Programacion", "PRG201", 3);

        matematicas.agregarHorario("Lunes 06:00 - 08:00");
        programacion.agregarHorario("Viernes 06:00 - 08:00");

        Estudiante juan = new Estudiante("Juan", "192404");

        GestionHorario gestion = new GestionHorario();
        gestion.registrarMateria(matematicas);
        gestion.registrarMateria(programacion);

        gestion.inscribirEstudiante(juan);
        
        try {
            juan.incribirMateria(matematicas, "Lunes 06:00 - 08:00");
            juan.incribirMateria(programacion, "Viernes 06:00 - 08:00");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (MateriaInscrita inscripcion : juan.mostrarHorarioComp()) {
            System.out.println("Materia: " + inscripcion.getMateria().getName() + " | Horario: " + inscripcion.getHorario());
        }
    }
    
}