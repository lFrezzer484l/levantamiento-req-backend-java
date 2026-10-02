package requirements_api_java.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import requirements_api_java.model.Requirement;
import requirements_api_java.service.RequirementService;

import java.util.List;

@RestController
@RequestMapping("/api/requirements")
@Tag(
        name = "Requirements",
        description = "Operaciones para gestionar requerimientos"
)
public class RequirementController {

    private final RequirementService requirementService;

    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    // ============================================================
    // LISTAR TODOS
    // ============================================================

    @Operation(
            summary = "Listar todos los requerimientos",
            description = "Obtiene todos los requerimientos registrados en la base de datos"
    )
    @GetMapping
    public ResponseEntity<List<Requirement>> listarTodos() {

        List<Requirement> requirements = requirementService.listarTodos();

        return ResponseEntity.ok(requirements);
    }

    // ============================================================
    // INSERTAR
    // ============================================================

    @Operation(
            summary = "Crear un requerimiento",
            description = "Registra un nuevo requerimiento en la base de datos"
    )
    @PostMapping("/create")
    public ResponseEntity<Requirement> crear(
            @RequestBody Requirement requirement) {

        Requirement nuevoRequirement =
                requirementService.crear(requirement);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoRequirement);
    }

    // ============================================================
    // ACTUALIZAR
    // ============================================================

    @Operation(
            summary = "Actualizar un requerimiento",
            description = "Actualiza los datos de un requerimiento existente mediante su ID"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Requirement> actualizar(
            @PathVariable Long id,
            @RequestBody Requirement requirement) {

        Requirement requirementActualizado =
                requirementService.actualizar(id, requirement);

        return ResponseEntity.ok(requirementActualizado);
    }

    // ============================================================
    // ELIMINAR
    // ============================================================

    @Operation(
            summary = "Eliminar un requerimiento",
            description = "Elimina un requerimiento existente mediante su ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @PathVariable Long id) {

        try {

            requirementService.eliminar(id);

            return ResponseEntity.ok(
                    "Requerimiento eliminado correctamente"
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // ============================================================
    // OBTENER POR ID
    // ============================================================

    @Operation(
            summary = "Obtener un requerimiento",
            description = "Obtiene un requerimiento específico mediante su ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Requirement> obtenerPorId(
            @PathVariable Long id) {

        Requirement requirement =
                requirementService.obtenerPorId(id);

        return ResponseEntity.ok(requirement);
    }
}