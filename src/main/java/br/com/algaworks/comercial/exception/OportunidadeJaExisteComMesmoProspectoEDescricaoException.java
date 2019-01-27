package br.com.algaworks.comercial.exception;

public class OportunidadeJaExisteComMesmoProspectoEDescricaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OportunidadeJaExisteComMesmoProspectoEDescricaoException() {
		super("Já existe uma oportunidade para este prospecto com a mesma descrição");
	}

}
