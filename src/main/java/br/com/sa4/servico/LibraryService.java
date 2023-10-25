package br.com.sa4.servico;

import java.util.List;
import br.com.sa4.modelo.Library;

public interface LibraryService {

	public Library saveBook(Library livro);

	public List<Library> getAllBooks();

	public Library findBookById(Long id);

	public void deleteBook(Long id);

	public void refreshBooks(Library livro);

}