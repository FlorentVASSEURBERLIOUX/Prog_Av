Florent VASSEUR--BERLIOUX
INF-FI3

<div align="center">
<img height="95" width="400" src="img/IUT_Velizy_Villacoublay_logo_2020_ecran.png" title="logo uvsq vélizy"/>

# Prog. Avancée - Compte rendu

</div>

Le document suivant est un résumé du cours de Programmation Avancée, illustrée par les exemples du TP.
Ce rapport expliquera aussi les choix effectuer dans les TP.

---

## Prémices

Nous avons travaillé sur plusieurs posts différents de l'IUT de Vélizy.
De ce fait, nous avons eu l'occasion de travaillé sur plusieurs architecture matérielles différentes.

Le matériel le plus récent sur lequel nous avons travaillé es celui présent en G24.
Il est constitué de 32 Go de RAM et d'un prcesseur de 8 coeurs.
D'autre ordinatreurs de cette même salle vont jusqu'à 64 Go de RAM.

Les coeurs du pprocesseur sont un élément essentiel qui permet l'execution de tâches.
L'exécution des tâches est le point centrale du contenu de ce compte rendu.
En effet, les Threads, Sémaphores et Moniteurs qui sont décrit dans ce rapport sont des moyens de gérer l'ordre d'exécution des tâches de la machine.

L'objectif des TP effectuées lors des séance de Programmation Avancée est d'acquérir des compétence et connaissances en programmation distribués et partéagés.
C'est au travers de ces concepts de gestion d'exécution des tâches que ces principes seront abordés.

## 1. Utilisation basique des Threads

Dans le TP 1, l'objectif était de créer des carrés mobiles se déplaçant de gauche à droite puis de droite à gauche dans une fenêtre.
Pour réussir ce TP, un Thread a été implémenté pour le carré mobile.
Le TP principal se trouve dans la section SRC du GitHub.

De ce fait, le déplacement du carré mobile était géré par des variables décrivant sa vitesse à travers :
 - sonPas : distance parcourut par le mobile à chaque répétition de la boucle.
 - sonTemps : le temps d'arrêt à la fin de chaque répétion de la boucle.

La commande `sleep()` permettant de mettre un Thread en pause pour une durrée prédéterminé a donc été utilisé.
Lors de l'initialisation du Thread, la commande `start()` a aussi été utilisé pour lancé l'execution du Thread.


### Tentative d'utilisation de suspend() et resume()

Pour la suite du TP, un bouton a été créer dans le but de mettre en pause tous les processus.
L'idée était aussi de pouvoir les relancer après la mise en pause, mais je n'ai pas réussis cette fonctionnalité.
Les fonctions `suspend()` et `resume()` devaient être utilisées, cependant ces dernières sont obsolètes car peu sécurisés.

Dans le TP, je mettait en pause un Thread que j'avais créer dans run.
L'instruction `suspend()` était aussi dans le run et, si une variable nommée `paused` était sur  `True`, cette instruction s'activait.

Le problème de cette technique était que, une fois le Thread suspendu, il était impossible de le relancer à nouveau puisque l'instruction `resume()` était elle-même dans le Thread.


En préparration de la suite du TP, plusieurs mobiles avec des vitesses variables ont été ajoutés sur la fenêtre.
Une boucle a donc été ajouté pour générés plusieurs mobiles et les ajoutés, un à un, à la fenêtre et au Thread.

---

### Cours sur les Threads

Un thread est un processus léger qui partage la même mémoire avec d'autres threads dans le même programme, mais chaque thread a son propre environnement d'exécution et sa pile.

Il existe d'autre commandes permettant de contrôler l'execution et l'état d'un Thread, on peut distinguer :
 - `start()` : Executer le Thread.
 - `sleep()` : Mettre en pause le Thread pour une durée prédéterminer.
 - `wait()` : Mettre en pause le Thread (nécessite `notifyAll()` pour le relancé).
 - `notifyAll()` : Relance un Thread mis en pause par `wait()`.

En Java, les threads peuvent être créés de deux manières principales :
- Implémentation de l'interface ``Runnable`` : L'objet implémente la méthode run() et un thread est créé en passant cet objet à la classe Thread.
- Héritage de la classe Thread : On crée une classe qui hérite de Thread et on redéfinit la méthode ``run()``.

---

## 2. Sémaphores et zone critique

### Ordre des lettres

Afin de s'entraîner à la maîtrise des sémaphores avant de les implémenter sur notre TP principal, un autre TP à été créer.
Il se trouve dans la séction SRC2 du GitHub.

L'objectif de ce TP était de gérer l'ordre d'execution d'un groupe de Thread executant des `sout()` dont chaque lettre est écrite une à une et séparer par des instruction `sleep()`.
Le but du TP est donc de placcé des verrous pour forcer les Threads à écrire les `sout()` en entier avant d'en commencer un autre. Il faut réguler les zones critiques.

Afin de résoudre ce problème de séction critique, on instancie un Sémaphore pour tous les Threads.
Lorsque un Thread s'exécute, il diminue la valeur initiale du Sémaphore et de ce fait empêche l'exécution des autres Threads.
A la fin de l'execution d'un Thread, il notifie le Sémaphore en réaugmentant la valeur initial de ce dernier. Ainsi, un nouveau Thread, jusque là en attente, est exécuté.

```java
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
*Code de la classe Main*

### Zones d'arrêt des mobiles

On réutilise les sémaphore dans le TP principal.
On cherche alors à créer une zone dans laquelle seul un mobile peut se déplacer à la fois, il s'agit de notre zone critique.

La fenêtre est alors scindé en 3 zones, cellle du milieu est celle dans laquelle seul 1 mobile peut se déplacer. Un sémaphore est donc implémenter, de façon similaire à l'exemple précédent. La zone critique est alors délimité autour des deux boucles gérant le déplacement des mobiles dans la zone centrale.

Le constructeur de la classe mobile a donc été modifié pour prendre en argument le sémaphore. Ainsi, seul un mobile à la fois pouvait se déplacer dans la zone centrale. En ajoutant la valeur initiale aux argument de la méthode générant la fenêtre, il est possible de choisir au lancement du programme, et le nombre de mobiles à lancé, et le nombre de module toléré dans la zone critique.

Pour plus de clareté, une couleur spécifique a été attribué aux mobiles présents dans la zone critique.

```java
semaphore.syncWait();

    for (sonDebDessin = saLargeur / 3; sonDebDessin < saLargeur / 3 * 2; sonDebDessin += sonPas) {
        if (paused) {
            th.suspend();
        }

        repaint();
        try {
            Thread.sleep(sonTemps);
        } catch (InterruptedException telleExcp) {
            telleExcp.printStackTrace();
        }
    }
    semaphore.syncSignal();
```
*Code de la classe Mobile, boucle de la zone critique*

---

### Cours sur les Sémaphores

Une ressource critique est une ressource qui ne peut être utilisée que par un seul processus à la fois (par exemple, une imprimante ou une zone mémoire partagée). La section critique est la portion de code qui ne peut être exécutée que par un thread à la fois. Le concept d'exclusion mutuelle garantit que si un processus accède à une ressource critique, il bloque l'accès aux autres processus.

Pour gérer cela en Java, on utilise des verrous (MUTEX) avec l'instruction ``synchronized``, qui permet de protéger une méthode ou un bloc de code.

Les sémaphores permettent également de contrôler l'accès à une ou plusieurs ressources, qu'il s'agisse d'un sémaphore binaire (accès unique) ou général (accès multiple). Deux primitives sont utilisées : ``wait()`` pour l'accès et ``signal()`` pour la libération de la ressource.

---

## 3. Moniteurs

Les moniteurs ont été expérimentés sur le TP3. Le but de l'exercice étant de modéliser un système de boîte aux lettres (BAL).
Ce TP se trouve dans la séction SRC3 du GitHub.

### Moniteurs simple

Le programme simule la BAL avec un Producteur et un Consommateur.
De ce fait, le Producteur livre une lettre dans la BAL et le Consommateur réccupère la lettre, si le consommateur ne réccupère pas la lettre, le Producteur est mis en attente. Dans le cas contraire, si le Consommateur cherche à reccupéré le contenu de la BAL alors qu'elle est vide, il est mis en attente.

La mise en attente des Consommateurs et Prtoducteurs s'effectuent en uitilisant un Thread, on se sert alors de ``wait()`` et ``notifyAll()``

```java
public synchronized String retirer() throws InterruptedException {
    if (!available){
        wait();
    }
    System.out.println("Consommateur a retiré une lettre : " + lettre);
    lettre = null;
    available = false;
    notifyAll();
    return lettre;
}
```
*Code de retrait de lettre avec mise en attente*

### Blocking Queue

Une version plus poussée de la BAL a été créé sur la variante des ``BlockingQueue``.

Cette version utilise un tableau de 5 threads pour les Producteurs et 5 threads pour les Consommateurs. Chaque thread dans les deux groupes exécute les tâches du producteur ou du consommateur.

La BlockingQueue gère la synchronisation, il n'est donc pas necessaire d'utiliser les verrous ou les blocs synchronized pour gérer la section critique.

```java
public class BisBAL {
    private BlockingQueue<Lettre> queue = new ArrayBlockingQueue<>(20);

    public boolean depose(Lettre lettre) throws InterruptedException {
        return queue.offer(lettre, 200, TimeUnit.MILLISECONDS);
    }

    public Lettre achete() throws InterruptedException {
        return queue.poll(200, TimeUnit.MILLISECONDS);
    }

    public int getStock() {
        return queue.size();
    }
}
```
*Code utiliosant les blockingQueue pour gérer la BAL*

---

### Cours sur les Moniteurs

Un moniteur est un mécanisme de synchronisation qui permet l’exclusion mutuelle et l’attente de conditions pour accéder à une ressource partagée. C’est un objet qui contient des méthodes servant de point d’entrée pour les threads, tout en assurant que seul un thread à la fois peut y accéder. Par exemple, le modèle Producteur-Consommateur est souvent géré par un moniteur, où un producteur ajoute des données dans un buffer, tandis qu’un consommateur les retire. Le moniteur assure que ces actions ne peuvent pas se faire simultanément, évitant ainsi les conflits.

Le principe clé d'un moniteur est qu'à un instant donné, un seul processus peut être actif dans le moniteur, et les autres doivent attendre leur tour.

---

### Outils utilisés

Ces TP ont été réalisés avec IntelliJ, et gérer via GitBash et GitHub.
Le rapport ci-dessus a été partiellement corrigé de ses fautes avec l'extension LanguageTool.
Les parties Cours du rapport ont été en partie isolées par ChatGPT en se basant sur le PDF "CM 2 - Programmation en mémoire partagée - les thread en javaFichier".

---
