package van_jok.RestIpiHomeWork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import van_jok.RestIpiHomeWork.models.Notes;

public interface NotesRepositrory extends JpaRepository<Notes,Integer> {
}
