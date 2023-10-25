package br.com.sa4.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.sa4.modelo.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {

}