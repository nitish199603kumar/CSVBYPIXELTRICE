package in.nitish.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import in.nitish.entity.*;


@Repository
public interface DeveloperTutorialRepository extends JpaRepository<DeveloperTutorial,Integer> {

}
