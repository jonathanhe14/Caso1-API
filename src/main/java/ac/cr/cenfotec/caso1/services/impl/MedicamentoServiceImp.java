package ac.cr.cenfotec.caso1.services.impl;

import ac.cr.cenfotec.caso1.entities.Medicamento;
import ac.cr.cenfotec.caso1.repos.MedicamentosRepo;
import ac.cr.cenfotec.caso1.services.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MedicamentoServiceImp implements MedicamentoService {

    @Autowired
    MedicamentosRepo medicamentosRepo;

    @Override
    public Long saveMedicamento(Medicamento jugador) {
        return medicamentosRepo.save(jugador).getId();
    }

    @Override
    public Medicamento getMedicamento(Long id) {
        Optional<Medicamento> medicamento = medicamentosRepo.findById(id);
        if (medicamento.isPresent()) {
            return medicamento.get();
        } else {
            return null;
        }

    }

    @Override
    public List<Medicamento> getMedicamentos(Integer pagesize, Integer startAt) {
        return medicamentosRepo.findAll(pagesize, startAt);
    }

    @Override
    public List<Medicamento> getMedicamentosNameAprox(Integer pagesize,Integer starAt,String name){
        return medicamentosRepo.findByNombreComercialAprox(name,pagesize,starAt);
    }

    @Override
    public List<Medicamento> getMedicamentosPrinAprox(Integer pagesize,Integer starAt,String prin){
        return medicamentosRepo.findByPrincipioActivoAprox(prin,pagesize,starAt);
    }


    @Override
    public Boolean deleteMedicamento(Long id) {
        try {
            medicamentosRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long getMedicamentoCount() {
        return medicamentosRepo.count();
    }
    @Override
    public Long getMedicamentosCountByName(String name) {
        return medicamentosRepo.countByNombreComercialAprox(name);
    }
    @Override
    public Long getMedicamentosCountByPrin(String prin) {
        return medicamentosRepo.countByPrincipioActivoAprox(prin);
    }

}
