<p align="left">
  <a href="https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Logo_Mines_Saint-%C3%89tienne.svg/langfr-225px-Logo_Mines_Saint-%C3%89tienne.svg.png/" target="blank"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Logo_Mines_Saint-%C3%89tienne.svg/langfr-225px-Logo_Mines_Saint-%C3%89tienne.svg.png" align="left" width="80" alt="Nest Logo" /></a>
</p>
<p align="left">
  <a href="https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/langfr-144px-Android_logo_2019_%28stacked%29.svg.png" target="blank"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/langfr-144px-Android_logo_2019_%28stacked%29.svg.png" align="right" width="80" alt="Nest Logo" /></a>
</p> <br />
<br />
<br />
# Titre du projet


[![forthebadge](http://forthebadge.com/images/badges/built-with-love.svg)](http://forthebadge.com)  [![forthebadge](http://forthebadge.com/images/badges/powered-by-electricity.svg)](http://forthebadge.com)

Après la création de l'API avec les données des musées de France, nous passons à la création de
l'application. Cette application permet d'afficher la liste de tous les musées, de les mettre en favori et d'afficher ces favoris,
d'afficher tous les détails d'un musée en appuyant dessus,
d'afficher une map Google montrant tous les marqueurs et enfin une rubrique informations sur l'application.

## Fonctionnement

Cette application est composée de 3 activités.
L'activité principale comporte 3 fragments : Un fragment pour la liste des musées, un pour la map et un pour les informations sur l'application.
La deuxième activité montre les favoris de l'utilisateur quand il appuie sur un bouton du menu
La troisième activité montre les détails d'un musée quand on appuie dessus.
L'application démarre avec l'affichage de la liste complète de tous les musées, plus particulièrement leur nom,
le lieu où il est situé(château etc), sa région et une image du logo de la région. Cette liste s'affiche dans le fragment Museums List
et affiche des données sous forme de RecyclerView. Sur chaque Item et donc musée de la Liste il existe un onClickListner qui permettait le demarrage
de la seconde activité qui avait pour role de donner plus d'informations sur le musée sélectionné. Pour ne pas rester bloquer indéfiniment dans cette 
activité, Nous avons ajouté un bouton dans la toolBar. En cliquant sur ce bouton, on retournait sur le Fragment qui affiche la liste de tous les musée.
Toujours sur les items du recycleView on ajouter un bouton qui permet de mettre un musé en favoris.Le bouton en question est un ImaeButton et représenté 
de base comme un étoile vide. En actionnant ce bouton, on ajoute le musée concerné dans la liste de favoris
et l'étoile se remplit. Si l'utilisateur veut consulter ses musées favoris, on a ajouté un bouton qui 
affiche les favoris en RecyclerView. 
Dans la toolbar de la MainActivity, on a ajouté une barre de recherche qui filtre les musées par nom, par région ou encore par département.
On a aussi ajouté un bouton "Refresh" dans la toolbar pour refresh le fragment dans lequel se trouve l'utilisateur.

On a créé un second fragment qui affiche la carte du monde avec les marqueurs désignant les musées de France (Métropolitaine et DOM-TOM). 
La carte nous situe directement à Gardanne (Bonus). On peut zoomer dans la carte et les marqueurs donnent le nom du musée.
Finalement, un troisième fragment donne des informations sur l'application, notamment les données utilisées, leur source, leur explication ou encore les 
librairies utilisées dans sa conception. 

Parmi les librairies qu'on a utilisé, on a appelé la librairie Picasso pour charger des images sur le web,
la librairie spécifique à Google Maps pour l'affichage de la map et des marqueurs. 

### Exécution
Au vu de la taille du dataset, il se peut que l'émulateur intégré dans Android Studio prenne beaucoup de 
temps pour extraire les données de l'API donc il est conseillé d'essayer l'application sur un téléphone 
android si possible. Sinon, il faudra attendre un peu plus. 

## Auteurs
- [Chedly Boukhris](https://github.com/Chedly25)
- [Kouassi Jean Raïce Kouakou ](https://github.com/jeanraice)

