package com.anstel.libUtilsScala

/**
 * Classe définissant l'objet MailServer
 *
 * @param name        : nom de l'environnement de travail
 * @param host        : nom ou adresse IP du serveur de messagerie
 * @param port        : numéro du port
 * @param password    : mot de passe de l'utilisateur
 * @param fromAddress : adresse mail de l'émetteur
 * @param toAddress   : adresse mail du destinataire
 * @author Thierry Baribaud
 * @version 1.08
 */
case class MailServer(override val name: String, override val host: String, override val port: Int,
                      override val username: String, override val password: String,
                      fromAddress: String, toAddress: String) extends Server (name, host, port, username, password)


