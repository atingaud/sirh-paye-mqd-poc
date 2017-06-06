package com.sirh.mqd.reporting.webapp.factory;

import com.sirh.mqd.commons.exchanges.dto.commentaire.CommentaireDTO;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.webapp.model.CommentaireModel;

/**
 * Factory de création des anomalies d'un dossier à manipuler côté IHM.
 *
 * @author khalil
 */

public final class CommentaireModelFactory {
	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private CommentaireModelFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + CommentaireModelFactory.class.getName());
	}

	public static CommentaireModel createCommentaireModel(final CommentaireDTO commentaireDTO) {
		final CommentaireModel commentaireModel = new CommentaireModel();
		commentaireModel.setDateCreation(DateUtils.clonerDate(commentaireDTO.getDateCreation()));
		commentaireModel.setContenu(commentaireDTO.getContenu());
		commentaireModel.setUtilisateur(commentaireDTO.getUtilisateur());
		return commentaireModel;
	}

	public static CommentaireDTO createCommentaireDTO(final CommentaireModel commentaireModel, final String payLot, final String matricule) {
		final CommentaireDTO commentaireDTO = new CommentaireDTO();
		commentaireDTO.setDateCreation(DateUtils.getCalendarInstance().getTime());
		commentaireDTO.setContenu(commentaireModel.getContenu());
		commentaireDTO.setUtilisateur(commentaireModel.getUtilisateur());
		commentaireDTO.setPayLot(payLot);
		commentaireDTO.setRenoiRHMatricule(matricule);
		return commentaireDTO;
	}

	public static CommentaireDTO createCommentaireDTO(final String commentaire, final String username, final String payLot, final String matricule) {
		final CommentaireDTO commentaireDTO = new CommentaireDTO();
		commentaireDTO.setDateCreation(DateUtils.getCalendarInstance().getTime());
		commentaireDTO.setContenu(commentaire);
		commentaireDTO.setUtilisateur(username);
		commentaireDTO.setPayLot(payLot);
		commentaireDTO.setRenoiRHMatricule(matricule);
		return commentaireDTO;
	}

}
