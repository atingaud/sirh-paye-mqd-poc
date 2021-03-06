package com.sirh.mqd.commons.utils;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.sirh.mqd.commons.utils.exception.TechnicalException;

/**
 * Classe utilitaire permet de serialiser et déserialiser un message JSON
 *
 * @author alexandre
 */
public class JsonUtils {

	/**
	 * Non constructeur.
	 *
	 * @throws InstantiationException
	 *             si tentative d'appel de ce constructeur.
	 */
	private JsonUtils() throws InstantiationException {
		throw new InstantiationException("Création non autorisée d'une instance de : " + JsonUtils.class.getName());
	}

	/**
	 * Méthode qui permet de transformer un message JSON en objet Java.
	 *
	 * @param jsonMessage
	 *            Message JSON à transformer.
	 * @param classObject
	 *            Classe de l'objet dans lequel le message sera transformé
	 * @return l'objet créé
	 * @throws TechnicalException
	 *             exception en cas de problème de serialisation
	 */
	public static <T> T deserializerJSON(final String jsonMessage, final Class<T> classObject)
			throws TechnicalException {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(jsonMessage, classObject);
		} catch (final IOException e) {
			throw new TechnicalException(e);
		}
	}

	/**
	 * Méthode qui permet de transformer un fichier contenant du JSON en objet
	 * Java.
	 *
	 * @param jsonFile
	 *            Fichier JSON à transformer.
	 * @param classObject
	 *            Classe de l'objet dans lequel le message sera transformé
	 * @return l'objet créé
	 * @throws TechnicalException
	 *             exception en cas de problème de serialisation
	 */
	public static <T> T deserializerJSON(final File jsonFile, final Class<T> classObject) throws TechnicalException {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(jsonFile, classObject);
		} catch (final IOException e) {
			throw new TechnicalException(e);
		}
	}

	/**
	 * Méthode qui permet de sérialiser en JSON un objet Java.
	 *
	 * @param object
	 *            Objet à sérialiser.
	 * @return Une chaîne de caractères représentant un objet sérialisé
	 * @throws TechnicalException
	 *             exception en cas de problème de serialisation
	 */
	public static <T> String serializerJSON(final T object) throws TechnicalException {
		try {
			if (object == null) {
				return null;
			}
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(object);
		} catch (final IOException e) {
			throw new TechnicalException(e);
		}
	}
}
