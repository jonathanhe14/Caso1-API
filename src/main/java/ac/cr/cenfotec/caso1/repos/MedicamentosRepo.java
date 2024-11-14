package ac.cr.cenfotec.caso1.repos;

import ac.cr.cenfotec.caso1.entities.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicamentosRepo extends JpaRepository<Medicamento,Long> {

    @Query(value="Select me from Medicamento me order by me.id limit :pageSize offset :startAt ")
    List<Medicamento> findAll(Integer pageSize, Integer startAt);
    @Query("SELECT me FROM Medicamento me WHERE LOWER(me.nombreComercial) LIKE LOWER(CONCAT('%', :nombreComercial, '%')) ORDER BY me.id LIMIT :pageSize OFFSET :startAt")
    List<Medicamento> findByNombreComercialAprox(String nombreComercial,Integer pageSize, Integer startAt);

    @Query("SELECT me FROM Medicamento me WHERE LOWER(me.principioActivo) LIKE LOWER(CONCAT('%', :principioActivo, '%')) ORDER BY me.id LIMIT :pageSize OFFSET :startAt")
    List<Medicamento> findByPrincipioActivoAprox(String principioActivo,Integer pageSize, Integer startAt);

    @Query("SELECT COUNT(me) FROM Medicamento me WHERE LOWER(me.nombreComercial) LIKE LOWER(CONCAT('%', :nombreComercial, '%'))")
    Long countByNombreComercialAprox(String nombreComercial);

    @Query("SELECT COUNT(me) FROM Medicamento me WHERE LOWER(me.principioActivo) LIKE LOWER(CONCAT('%', :principioActivo, '%'))")
    Long countByPrincipioActivoAprox(String principioActivo);



}
