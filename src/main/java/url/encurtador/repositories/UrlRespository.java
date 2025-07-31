package url.encurtador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import url.encurtador.model.Url;
import java.util.Optional;


public interface UrlRespository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortCode(String shortCode);
}
