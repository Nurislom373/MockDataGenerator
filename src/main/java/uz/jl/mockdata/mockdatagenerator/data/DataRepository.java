package uz.jl.mockdata.mockdatagenerator.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jl.mockdata.mockdatagenerator.data.entity.DataEntity;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Integer> {

    Optional<DataEntity> findByCode(UUID code);

    @Transactional
    void deleteByCode(UUID code);
}
