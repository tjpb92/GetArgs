package com.anstel.libUtilsScala

import scala.util.{Failure, Success, Try}

/**
 * Classe définissant l'objet Server
 *
 * @param name     : nom de l'environnement de travail
 * @param host     : nom ou adresse IP du serveur
 * @param port     : numéro du port
 * @param password : mot de passe de l'utilisateur
 * @author Thierry Baribaud
 * @version 1.08
 */
case class Server(name: String, host: String, port: Int, username: String, password: String)

object Server {
  def builder(serverType: String, service: String, applicationProperties: ApplicationProperties): Either[String, Server] = for {
    name <- applicationProperties.getPropertyValue(makeKey(serverType, service, "name"))
    host <- applicationProperties.getPropertyValue(makeKey(serverType, service, "host"))
    port <- checkPortNumber(applicationProperties.getPropertyValue(makeKey(serverType, service, "port")))
    username <- applicationProperties.getPropertyValue(makeKey(serverType, service, "username"))
    passwd <- applicationProperties.getPropertyValue(makeKey(serverType, service, "passwd"))
  } yield Server(name, host, port, username, passwd)

  /**
   * Fonction servant à définir une clé d'une propriété de l'apllication
   *
   * @param serverType  : type de serveur : dev, prod, ...
   * @param service  : service demandé : mongo, informix, mail, ...
   * @param property : nom de la propriété à retrouver : name, database, ...
   *
   * @return la clé d'une propriété de l'apllication
   */
  private def makeKey(serverType: String, service: String, property: String) =
    s"$serverType.$service.$property"

  /**
   * Fonction qui convertit un numéro de port contenu dans une chaine de caractères en entier long
   *
   * @param portAsString numéro de port à traduire en entier long
   * @return entier long traduit ou message d'erreur
   */
  def checkPortNumber(portAsString: Either[String, String]): Either[String, Int] = {
    portAsString match {
      case Left(value) => Left(value)
      case Right(value) => Try(Integer.parseInt(value)) match {
        case Failure(exception) => Left(s"ERREUR : le numéro de port '$value' n'est pas numérique")
        case Success(value) => Right(value)
      }
    }
  }

  def main(args: Array[String]) {
    val serverType = "dev"
    val service = "mongodb"

    val applicationProperties: ApplicationProperties = new ApplicationProperties("GetArgs_Example.prop")
    println("applicationProperties:" + applicationProperties)
    println("  name:" + applicationProperties.getPropertyValue(makeKey(serverType, service, "name")))
    println("  host:" + applicationProperties.getPropertyValue(makeKey(serverType, service, "host")))
    println("  port:" + applicationProperties.getPropertyValue(makeKey(serverType, service, "port")))
    println("  username:" + applicationProperties.getPropertyValue(makeKey(serverType, service, "username")))
    println("  passwd:" + applicationProperties.getPropertyValue(makeKey(serverType, service, "passwd")))

    val result = Server.builder(serverType, service, applicationProperties)
    println("result:" + result)

    val server = result match {
//      case Right(server) => poursuivreAvec(server)
      case Right(server) => server
      case Left(error) => println(error)
    }
    println(s"server:$server")
    println(s"server.getclass:${server.getClass}")
//    println(s"server.database:${server.username}") // Pourquoi y a-t-il une erreur ici ?

  }

  def poursuivreAvec(server: Server): Unit = {
    println(s"server.database:${server.username}")
  }
}
