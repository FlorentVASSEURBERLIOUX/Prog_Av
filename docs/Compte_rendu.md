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
Le TP principal se trouve dans la section SRC du GitHub.

De ce fait, le déplacement du carré mobile était géré par des variables décrivant sa vitesse à travers :
 - sonPas : distance parcourut par le mobile à chaque répétition de la boucle.
 - sonTemps : le temps d'arrêt à la fin de chaque répétion de la boucle.

La commande `sleep()` permettant de mettre un Thread en pause pour une durrée prédéterminé a donc été utilisé.
Lors de l'initialisation du Thread, la commande `start()` a aussi été utilisé pour lancé l'execution du Thread.

**NOTE :** Il existe d'autre commandes permettant de contrôler l'execution et l'état d'un Thread, on peut distinguer :
 - `start()` : Executer le Thread.
 - `sleep()` : Mettre en pause le Thread pour une durée prédéterminer.
 - `wait()` : Mettre en pause le Thread (nécessite `notifyAll()` pour le relancé).
 - `notifyAll()` : Relance un Thread mis en pause par `wait()`.


## 2. Tentative d'utilisation de suspend() et resume()

Pour la suite du TP, un bouton a été créer dans le but de mettre en pause tous les processus.
L'idée était aussi de pouvoir les relancer après la mise en pause, mais je n'ai pas réussis cette fonctionnalité.
Les fonctions `suspend()` et `resume()` devaient être utilisées, cependant ces dernières sont obsolètes car peu sécurisés.

Dans le TP, je mettait en pause un Thread que j'avais créer dans run.
L'instruction `suspend()` était aussi dans le run et, si une variable nommée `paused` était sur  `True`, cette instruction s'activait.

Le problème de cette technique était que, une fois le Thread suspendu, il était impossible de le relancer à nouveau puisque l'instruction `resume()` était elle-même dans le Thread.


En préparration de la suite du TP, plusieurs mobiles avec des vitesses variables ont été ajoutés sur la fenêtre.
Une boucle a donc été ajouté pour générés plusieurs mobiles et les ajoutés, un à un, à la fenêtre et au Thread.


## 3. Sémaphores et zone critique

### Ordre des lettres

Afin de s'entraîner à la maîtrise des sémaphores avant de les implémenter sur notre TP principal, un autre TP à été créer.
Il se trouve dans la séction SRC2 du GitHub.

L'objectif de ce TP était de gérer l'ordre d'execution d'un groupe de Thread executant des `sout()` dont chaque lettre est écrite une à une et séparer par des instruction `sleep()`.
Le but du TP est donc de placcé des verrous pour forcer les Threads à écrire les `sout()` en entier avant d'en commencer un autre. Il faut réguler les zones critiques.

Afin de résoudre ce problème de séction critique, on instancie un Sémaphore pour tous les Threads.
Lorsque un Thread s'exécute, il diminue la valeur initiale du Sémaphore et de ce fait empêche l'exécution des autres Threads.
A la fin de l'execution d'un Thread, il notifie le Sémaphore en réaugmentant la valeur initial de ce dernier. Ainsi, un nouveau Thread, jusque là en attente, est exécuté.

```
public class Main {
    public static void main(String[] args) {

        SemaphoreBinaireP sem = new SemaphoreBinaireP(1);

        Affichage TA = new Affichage("AAAA\n",sem);
        TA.start();

        Affichage TB = new Affichage("BBBBB\n",sem);
        TB.start();

        Affichage TC = new Affichage("CCCCCC\n",sem);
        TC.start();

        Affichage TD = new Affichage("DDDDDDD\n",sem);
        TD.start();
    }
}
```
__Code de la classe Main__
### Zones d'arrêt des mobiles
