package com.anstel.libUtilsScala

import scala.util.{Failure, Success, Try}

/**
 * Classe définissant l'objet DbServer
 *
 * @param serverType            : type de serveur de base de données  dev, prod, ...
 * @param service               : service demandé : mongo, informix, ...
 * @param applicationProperties : paramètres de l'application
 * @author Thierry Baribaud
 * @version 1.07
 */
class DbServer(serverType: String, service: String, applicationProperties: ApplicationProperties) {

  //  println("key:" + makeKey(serverType, service, "name"))
  //  println("value:" + applicationProperties.getPropertyValue(makeKey(serverType, service, "name")))

  /**
   * Nom de l'environnement de travail
   */
  var name: String = applicationProperties.getPropertyValue(makeKey(serverType, service, "name")) match {
    case Left(value) => "erreur"
    case Right(value) => value
  }

  /**
   * Nom du serveur
   */
  var host: String = applicationProperties.getPropertyValue(makeKey(serverType, service, "host")) match {
    case Left(value) => "erreur"
    case Right(value) => value
  }

  /**
   * Numéro de port
   */
  var portNumber: Int = applicationProperties.getPropertyValue(makeKey(serverType, service, "port")) match {
    case Left(value) => -1
    case Right(value) => this.toInt(value) match {
      case Failure(exception) => -1
      case Success(value) => value
    }
  }

  /**
   * Nom de la base de données
   */
  var database: String = applicationProperties.getPropertyValue(makeKey(serverType, service, "database")) match {
    case Left(value) => "erreur"
    case Right(value) => value
  }

  /**
   * Identifiant de l'utilisateur
   */
  var username: String = applicationProperties.getPropertyValue(makeKey(serverType, service, "username")) match {
    case Left(value) => "erreur"
    case Right(value) => value
  }

  /**
   * Mot de passe de l'utilisateur
   */
  var passwd: String = applicationProperties.getPropertyValue(makeKey(serverType, service, "passwd")) match {
    case Left(value) => "erreur"
    case Right(value) => value
  }

  /**
   * Fonction servant à définir une clé d'une propriété de l'apllication
   *
   * @return la lé d'une propriété de l'apllication
   */
  private def makeKey(serverType: String, service: String, property: String) =
    s"$serverType.$service.$property"

  /**
   * Fonction qui converti une chaine de caractères en entier long
   *
   * @param s chaine à traduire en entier long
   * @return entier long traduit ou message d'erreur
   */
  def toInt(s: String): Try[Int] = Try(Integer.parseInt(s.trim))

  /**
   * Retourne l'objet sous forme textuelle
   *
   * @return l'objet sous forme textuelle
   */
  override
  def toString: String = s"DbServer:{" +
    s"name:$name" +
    s", host:$host" +
    s", portNumber:$portNumber" +
    s", database:$database" +
    s", username:$username" +
    ", passwd:******" +
    "}"
}
