package com.anstel.libUtilsScala

import scala.util.{Failure, Success, Try}

/**
 * Classe définissant l'objet DbServer
 *
 * @param name     : nom de l'environnement de travail
 * @param host     : nom ou adresse IP du serveur de base de données
 * @param port     : numéro du port
 * @param database : base de données
 * @param username : identifiant de l'utilisateur
 * @param password : mot de passe de l'utilisateur
 * @author Thierry Baribaud
 * @version 1.08
 */
case class DbServer2(name: String, host: String, port: Int, database: String, username: String, password: String)

object DbServer2 {
  def builder(serverType: String, service: String, applicationProperties: ApplicationProperties): Either[String, DbServer2] = for {
    name <- applicationProperties.getPropertyValue(makeKey(serverType, service, "name"))
    host <- applicationProperties.getPropertyValue(makeKey(serverType, service, "host"))
    port <- checkPortNumber(applicationProperties.getPropertyValue(makeKey(serverType, service, "port")))
    database <- applicationProperties.getPropertyValue(makeKey(serverType, service, "database"))
    username <- applicationProperties.getPropertyValue(makeKey(serverType, service, "username"))
    passwd <- applicationProperties.getPropertyValue(makeKey(serverType, service, "passwd"))
  } yield DbServer2(name, host, port, database, username, passwd)

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
    println("  database:" + applicationProperties.getPropertyValue(makeKey(serverType, service, "database")))
    println("  username:" + applicationProperties.getPropertyValue(makeKey(serverType, service, "username")))
    println("  passwd:" + applicationProperties.getPropertyValue(makeKey(serverType, service, "passwd")))

    val result = DbServer2.builder(serverType, service, applicationProperties)
    println("result:" + result)

    val dbServer2 = result match {
//      case Right(dbServer2) => poursuivreAvec(dbServer2)
      case Right(dbServer2) => dbServer2
      case Left(error) => println(error)
    }
    println(s"dbServer2:$dbServer2")
    println(s"dbServer2.getclass:${dbServer2.getClass}")
//    println(s"dbServer2.database:${dbServer2.database}")
    // Pourquoi y a-t-il une erreur ici ?

    val dbs2 = DbServer2("Env prod","eole", 1250, "fildb", "toto", "lito")
    println(s"dbs2:$dbs2")
    println(s"dbs2.database:${dbs2.database}")

  }

  def poursuivreAvec(dbServer2: DbServer2): Unit = {
    println(s"dbServer2.database:${dbServer2.database}")
  }
}
