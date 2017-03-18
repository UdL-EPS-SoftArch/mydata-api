package cat.udl.eps.entsoftarch.repository;

import cat.udl.eps.entsoftarch.domain.Field;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface FieldRepository extends PagingAndSortingRepository<Field, Long> {
    List<Field> findByTitle(@Param("title") String title);

}