package space.springbok.punkbeerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.springbok.punkbeerservice.entities.MaltItem;

public interface MaltItemRepository extends JpaRepository<MaltItem, Long> {
}
