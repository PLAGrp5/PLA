Presentation

-Plateforme en labyrinthe, style PacMan (2D vu de dessus).
-Deux joueurs au clavier, (1 Vs 1).
-Chaque joueur possède un tank de couleur différente avec une barre de vie et de peinture, un déplacement entrainera la coloration de la case en cette couleur.
-Chaque joueur possède 2 sbires (controlé par une IA) avec une barre de vie.
-But : Posséder le plus de case de sa couleur ou vider la barre de vie de l'adversaire.

Regles

-Un tank recevra des dégats lorsqu'il est touché à l'arrière ou qu'il est touché par un sbire, il obtiendra le mode fantome pendant une certaine durée.
-Collision de deux tanks => ils se retournent afin d'être dans leurs directions opposés et perdent de la vie.
-Un sbire perd de la vie lorsqu'il est touché par un tir ou qu'il rentre dans un autre tank.

Les sbires et les joueurs peuvent utiliser Pop et Wizz.

Pop & Wizz : 

-Pop : C'est une capacité permettant de colorier la case où l'on est.
-Wizz : C'est une capacité qui permet d'enlever ou de poser un bloc.

Les sbires ne peuvent pas utiliser de bonus ou de malus, quand ils en récupèrent c'est le joueur qui en bénéficie.

Bonus et Malus : 

Les fixés :
-Une charge de vie
-Une charge de peinture
-Une mine qui fait perdre de la vie

Les variables (définies par des automates pouvant être modifiés) : 
-Plus grande portée de peinture (plus d'1 case)
-Blocage du joueur (freeze)
-Inversement des touches

