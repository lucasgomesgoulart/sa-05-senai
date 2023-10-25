package br.com.sa4.controlador;

import java.util.List;

import br.com.sa4.modelo.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.sa4.excecao.BookNotFoundException;
import br.com.sa4.servico.LibraryService;

@Controller
@RequestMapping("/livro")
public class LibraryController {

    @Autowired
    private LibraryService service;

    @GetMapping("/")
    public String exibirPaginaInicial() {
        return "paginaInicio";
    }

    @GetMapping("/adicionar")
    public String exibirFormularioAdicao() {
        return "adicionarLivro";
    }

    @PostMapping("/salvar")
    public String salvarLivro(@ModelAttribute Library livro, Model modelo) {
        service.saveBook(livro);
        Long id = service.saveBook(livro).getId();
        String mensagem = "Livro com id: '" + id + "' salvo com sucesso!";
        modelo.addAttribute("message", mensagem);
        return "adicionarLivro";
    }

    @GetMapping("/listar")
    public String buscarLivros(@RequestParam(value = "message", required = false) String mensagem, Model modelo) {
        List<Library> livros = service.getAllBooks();
        modelo.addAttribute("listagem", livros);
        modelo.addAttribute("message", mensagem);
        return "listarLivros";
    }

    @GetMapping("/editar")
    public String exibirFormularioEdicao(Model modelo, RedirectAttributes atributos, @RequestParam Long idLivro) {
        String pagina = null;
        try {
            Library livro = service.findBookById(idLivro);
            modelo.addAttribute("livro", livro);
            pagina = "editarLivro";
        } catch (BookNotFoundException e) {
            e.printStackTrace();
            atributos.addAttribute("message", e.getMessage());
            pagina = "redirect:listar";
        }
        return pagina;
    }

    @PostMapping("/atualizar")
    public String atualizarLivro(@ModelAttribute Library livro, RedirectAttributes atributos) {
        service.refreshBooks(livro);
        Long id = livro.getId();
        atributos.addAttribute("message", "Livro com id: '" + id + "' atualizado com sucesso!");
        return "redirect:listar";
    }

    @GetMapping("/deletar")
    public String deletarLivroPorId(@RequestParam Long id, RedirectAttributes atributos) {
        try {
            service.deleteBook(id);
            atributos.addAttribute("message", "Livro com id: '" + id + "' excluído com sucesso!");
        } catch (BookNotFoundException e) {
            e.printStackTrace();
            atributos.addAttribute("message", e.getMessage());
        }
        return "redirect:listar";
    }
}
