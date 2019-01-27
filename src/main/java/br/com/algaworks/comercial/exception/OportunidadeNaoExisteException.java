package br.com.algaworks.comercial.exception;

public class OportunidadeNaoExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OportunidadeNaoExisteException() {
		super("Não existe uma oportunidade com o id informado");
	}

}
