package ac.cr.cenfotec.caso1.controllers;

import ac.cr.cenfotec.caso1.entities.Medicamento;
import ac.cr.cenfotec.caso1.response.GenericResponse;
import ac.cr.cenfotec.caso1.response.IdResponse;
import ac.cr.cenfotec.caso1.response.ResponseMetadata;
import ac.cr.cenfotec.caso1.services.MedicamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medicamento")

public class MedicamentoController {
    Logger logger = LoggerFactory.getLogger(MedicamentoController.class);

    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping
    public ResponseEntity<GenericResponse> createMedicamento(@RequestBody Medicamento medicamento) {
        IdResponse createdPlayerId = new IdResponse(medicamentoService.saveMedicamento(medicamento));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new GenericResponse(createdPlayerId));
    }

    @GetMapping
    public ResponseEntity<GenericResponse> getAllMedicamentos(
            @RequestParam(name="pagesize", required = false, defaultValue = "25") Integer pagesize,
            @RequestParam(name="startAt", required = false, defaultValue = "0") Integer startAt,
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "prin", required = false) String principioActivo
    ) {

        Long totalMedicamentos = medicamentoService.getMedicamentoCount();

        if (startAt >= totalMedicamentos) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        if (startAt + pagesize > totalMedicamentos) {
            pagesize = Math.toIntExact(totalMedicamentos - startAt);
        }

        List<Medicamento> medicamentos;

        if (nombre != null && !nombre.isEmpty()) {
            medicamentos = medicamentoService.getMedicamentosNameAprox(pagesize, startAt, nombre);
            totalMedicamentos = medicamentoService.getMedicamentosCountByName(nombre);
        } else if (principioActivo != null && !principioActivo.isEmpty()) {
            totalMedicamentos = medicamentoService.getMedicamentosCountByPrin(principioActivo);
            medicamentos = medicamentoService.getMedicamentosPrinAprox(pagesize, startAt, principioActivo);
        } else {
            medicamentos = medicamentoService.getMedicamentos(pagesize, startAt);
        }

        Long lastPage = (totalMedicamentos / pagesize) + 1L;
        Long currentPage = ((startAt + 1) / pagesize) + 1L;

        return ResponseEntity.status(HttpStatus.OK)
                .body(new GenericResponse(new ArrayList<>(medicamentos),
                        new ResponseMetadata(totalMedicamentos, currentPage, lastPage, pagesize, startAt)));
    }


    @DeleteMapping("/{id}")
    public boolean deleteMedicamento(@PathVariable Long id) {
        return medicamentoService.deleteMedicamento(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> findMedicamentoById(@PathVariable Long id) {
        Medicamento found = medicamentoService.getMedicamento(id);
        if ( found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GenericResponse(new ArrayList<Medicamento>()));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new GenericResponse(found));
        }
    }
}
