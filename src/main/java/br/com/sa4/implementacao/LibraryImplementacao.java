package br.com.sa4.implementacao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.sa4.excecao.BookNotFoundException;
import br.com.sa4.modelo.Library;
import br.com.sa4.repositorio.LibraryRepository;
import br.com.sa4.servico.LibraryService;

@Service
public class LibraryImplementacao implements LibraryService {

    @Autowired
    private LibraryRepository repositorio;

    @Override
    public Library saveBook(Library livro) {
        return repositorio.save(livro);
    }

    @Override
    public List<Library> getAllBooks() {
        return repositorio.findAll();
    }

    @Override
    public Library findBookById(Long id) {
        Optional<Library> opcional = repositorio.findById(id);
        if (opcional.isPresent()) {
            return opcional.get();
        } else {
            throw new BookNotFoundException("Livro com id: " + id + " não encontrada.");
        }
    }

    @Override
    public void deleteBook(Long id) {
        repositorio.delete(findBookById(id));
    }

    @Override
    public void refreshBooks(Library livro) {
        repositorio.save(livro);
    }
}
