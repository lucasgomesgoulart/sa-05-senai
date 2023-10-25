
package br.com.sa4.excecao;

public class BookNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String mensagemPersonalizada) {
        super(mensagemPersonalizada);
    }
}