package pqdong.face.recognition.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pqdong.face.recognition.data.entity.PersonEntity;

/**
 * @author pqdong
 * @description
 * @date 2021-02-18
 */

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Query(nativeQuery = true, value = "select * from person where person_id in (?1)")
    List<PersonEntity> findAllByPersonIdIn(@Param("personIds") List<String> personIds);

    @Query(nativeQuery = true, value = "select * from person where person_id =?1")
    PersonEntity findAllByPersonId(@Param("personId") String personId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from person where person_id = :personId")
    void deleteByPersonId(@Param("personId") String personId);
}
