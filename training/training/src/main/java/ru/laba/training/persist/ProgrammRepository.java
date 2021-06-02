package ru.laba.training.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.laba.training.persist.Model.Programm;

import java.util.List;

@Repository
public interface ProgrammRepository extends JpaRepository<Programm, Integer> {
    @Query("select p from Programm p where p.type= :type")
    List<Programm> findByType(@Param("type") String type);

    @Query("select p from Programm p where p.user.id=:id and p.type=:type")
    List<Programm> findByTypeForCreator(@Param("type") String type, @Param("id") long id);

    @Query("select p from Programm p where p.countOFsubscribers=(select max(p.countOFsubscribers) from p)")
    Programm findauto();
}
