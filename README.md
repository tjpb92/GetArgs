# Essai de modélisation en Scala de l'objet GetArgs et essai Git depuis IntelliJ
Ecrire un programme Scala permettant de lire les arguments en ligne de commande et de les stocker dans un objet.

Essai de Git depuis IntelliJ

## Utilisation:
```
scala GetArgs [-db db] [[-b début] [-e fin]|-n nbJour]
		[-cc callCenter] [-client client|-clientUuid uuid]
		[-p path] [-s suffixe] [-d] [-t]
```
où :
* ```-db db``` est la référence à la base de données, par défaut désigne la base de données de développement. Voir fichier *GetArgs.prop* (optionnel).
* ```-b début``` : date de début de l'extraction à 0h au format JJ/MM/AAAA. Amorcé à hier par défaut (paramètre optionnel).
* ```-e fin``` : date de fin de l'extraction à 0h au format JJ/MM/AAAA. Amorcé à aujourd'hui par défaut (paramètre optionnel).
* ```-n nbJour``` : précise le nombre de jour(s) à compter de la date courante. Non défini par défaut (paramètre optionnel).
* ```-cc callCenter``` est la référence au centre d'appel. Armorcé à vide par défaut (paramètre optionnel).
* ```-client client``` est la référence au client servant à filtrer les résultats. Désactivé par défaut (paramètre optionel).
* ```-clientUuid uuid``` est l'identifiant unique du client servant à filtrer les résultats. Désactivé par défaut (paramètre optionel).
* ```-p path``` est le répertoire vers lequel exporter les fichiers contenant les résultats. Par défaut c'est le répertoire courant du programme (paramètre optionnel).
* ```-s suffixe``` est le suffixe à ajouter au nom des fichiers. Par défaut il n'y a pas de suffixe (paramètre optionnel).
* ```-d``` le programme s'exécute en mode débug, il est beaucoup plus verbeux. Désactivé par défaut (paramètre optionnel).
* ```-t``` le programme s'exécute en mode test, les transactions en base de données ne sont pas faites. Désactivé par défaut (paramètre optionnel).

## Pré-requis :
- Scala 2.13.2 ou supérieur
- Java 14 ou supérieur.
- Driver MongoDb

## Fichier des paramètres : 
Ce fichier permet de spécifier les paramètres d'accès aux différentes bases de données.

A adapter selon les implémentations locales.

Ce fichier est nommé : *GetArgs.prop*.

Le fichier *GetArgs_Example.prop* est fourni à titre d'exemple.

## Références:
- [A tour of Scala](https://docs.scala-lang.org/tour/tour-of-scala.html)
- [Scala Book](https://docs.scala-lang.org/overviews/scala-book/introduction.html)
- [Jenkov's Scala tutorial (version2.8.0)](http://tutorials.jenkov.com/scala/index.html)
- [Data Flair Scala tutorials](https://data-flair.training/blogs/scala-tutorials-home/)
- [Javatpoint Scala tutorial](https://www.javatpoint.com/scala-tutorial)