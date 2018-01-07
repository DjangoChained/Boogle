# Boogle

## Compiler

Un fichier de build Ant, `build.xml`, a été fourni. Apache Ant est par conséquent requis. Saisissez directement `ant` sans paramètres, ou `ant jar` pour effectuer la compilation vers un JAR.

La compilation donne un dossier `jar/` comprenant un fichier JAR et les fichiers de configuration du jeu.

Pour ne faire que la compilation vers des fichiers `.class`, saisissez `ant compile`. Les fichiers seront dans le dossier `classes/`.

## Générer la documentation

Le même fichier de build Ant fournit une commande pour générer la Javadoc du projet.

Saisissez `ant doc` pour générer la Javadoc dans le dossier `doc/`.

## Régénérer tout de zéro

En cas de problème, pour effacer toute trace de compilation précédente, exécutez `ant clean`.

Pour effectuer simultanément l'effacement, la recompilation en JAR et la régénération de la documentation, exécutez `ant fullbuild`.

## Exécuter

Après avoir compilé vers un fichier JAR, entrez dans le dossier `jar/` et saisissez l'une des commandes suivantes :

```bash
# Interface graphique
java -jar Boogle.jar
# Interface en ligne de commande
java -jar Boogle.jar std
```
