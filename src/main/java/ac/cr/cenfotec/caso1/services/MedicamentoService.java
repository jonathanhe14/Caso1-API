package ac.cr.cenfotec.caso1.services;

import ac.cr.cenfotec.caso1.entities.Medicamento;

import java.util.List;

public interface MedicamentoService {
    public Long saveMedicamento(Medicamento medicamento);
    public Medicamento getMedicamento(Long id);
    public List<Medicamento> getMedicamentos(Integer pagesize, Integer startAt);
    public List<Medicamento> getMedicamentosNameAprox(Integer pagesize,Integer starAt,String name);

    public List<Medicamento> getMedicamentosPrinAprox(Integer pagesize,Integer starAt,String prin);
    public Boolean deleteMedicamento(Long id);
    public Long getMedicamentoCount();
    public Long  getMedicamentosCountByName(String name);
    public Long  getMedicamentosCountByPrin(String prin);


}
