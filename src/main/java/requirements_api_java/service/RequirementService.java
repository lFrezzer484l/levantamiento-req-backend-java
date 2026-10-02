package requirements_api_java.service;
import java.util.List;

import org.springframework.stereotype.Service;
import requirements_api_java.model.Requirement;
import requirements_api_java.repository.RequirementRepository;

@Service
public class RequirementService {

    private final RequirementRepository requirementRepository;
    public RequirementService(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    public List<Requirement> listarTodos() {
        return requirementRepository.findAll();
    }

    public Requirement crear(Requirement requirement) {
        return requirementRepository.save(requirement);
    }

    public Requirement actualizar(Long id, Requirement datos) {

        Requirement requirement = requirementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requerimiento no encontrado"));

        requirement.setRequester(datos.getRequester());
        requirement.setEmail(datos.getEmail());
        requirement.setDescription(datos.getDescription());
        requirement.setRequirementType(datos.getRequirementType());
        requirement.setPriority(datos.getPriority());
        requirement.setTitulo(datos.getTitulo());

        return requirementRepository.save(requirement);
    }

    public void eliminar(Long id) {
        
        if (!requirementRepository.existsById(id)) {
            throw new RuntimeException("Requerimiento no encontrado");
        }
        
        requirementRepository.deleteById(id);
    }

    public Requirement obtenerPorId(Long id) {
        
        return requirementRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Requerimiento no encontrado"));
    }
}