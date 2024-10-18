Florent VASSEUR--BERLIOUX
INF-FI

<div align="center">
<img height="95" width="400" src="img/IUT_Velizy_Villacoublay_logo_2020_ecran.png" title="logo uvsq vélizy"/>

# Prog. Avancée - Compte rendu

</div>

Le document suivant est un résumé du cours de Programmation Avancée, illustrée par les exemples du TP.
Ce rapport expliquera aussi les choix effectuer dans les TP.

## 1. Utilisation basique des Threads

Dans le TP 1, l'objectif était de créer des carrés mobiles se déplaçant de gauche à droite puis de droite à gauche dans une fenêtre.
Pour réussir ce TP, un Thread a été implémenté pour le carré mobile.

De ce fait, le déplacement du carré mobile était géré par des variables décrivant sa vitesse à travers :
 - sonPas : distance parcourut par le mobile à chaque répétition de la boucle.
 - sonTemps : le temps d'arrêt à la fin de chaque répétion de la boucle.

La commande `sleep()` permettant de mettre un Thread en pause pour une durrée prédéterminé a donc été utilisé.
Lors de l'initialisation du Thread, la commande `start()` a aussi été utilisé pour lancé l'execution du Thread.

**NOTE :** Il existe d'autre commandes permettant de contrôler l'execution et l'état d'un Thread, on peut distinguer :
 - `start()` : Executer le Thread.
 - `sleep()` : Mettre en pause le Thread pour une durée prédéterminer.
 - `wait()` : Mettre en pause le Thread (nécessite `notifyAll()` pour le relancé).
 - `notifyAll()` : Relance un Thread mis en pause par `wait()`


## 2. Tentative d'utilisation de suspend() et resume()

