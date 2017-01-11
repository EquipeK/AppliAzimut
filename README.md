# AppliAzimut

Pour installer depuis Android Studio

File > New > Project from Version Control > Github
Normalement il doit pas y avoir de problemes mais sinon verifié que vous avez bien toutes ces versions :
    
    com.android.tools.build:gradle:2.2.3

    compileSdkVersion 23
    buildToolsVersion "23.0.3"
    
    minSdkVersion 19
    targetSdkVersion 23
      
    En cas d'erreur verifier que vous avez bien compile 'com.android.support:multidex:1.0.1' dans votre app gradle

    Android Studio : 2.2.3
    Target : 4.4 KITKAT


Utilisation de l'app :

Login avec des valeurs brutes pour le moment
(login : foo, mdp : hello)

Arrivée sur la page de géoloc
Normalement détecte automatiquement le gps sur mobile sinon envoyer des coordonnées gps sur l'émulateur
Passage de la geoloc en Intent

Acceder a la google map
Récup des datas de l'api pour afficher les boutique revendeur
Passage de l'id du revendeur en Intent lors du clic sur l'info bulle du marqueur

Arrivée sur la liste de produits revendeur de Glenn qui récupère l'id revendeur en intent

Le reste est en construction ...
