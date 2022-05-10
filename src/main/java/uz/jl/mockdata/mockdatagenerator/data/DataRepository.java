package uz.jl.mockdata.mockdatagenerator.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jl.mockdata.mockdatagenerator.data.entity.DataEntity;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Integer> {
}
