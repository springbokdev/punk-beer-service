package space.springbok.punkbeerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.springbok.punkbeerservice.entities.HopsItem;

public interface HopsItemRepository extends JpaRepository<HopsItem, Long> {
}
