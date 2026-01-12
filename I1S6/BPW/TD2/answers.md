# TD2 : Javascript

## Exercice 1

```js
const pano = document.getElementById('panoramique');
console.log(pano);
```
1. D'après les instructions ci-dessus, la fonction `getElementById` semble
retourner l'élément d'identifiant `pano`, étant dans notre cas l'image de
légende "La mer en largeur".

2. _J'ai obtenu un URI pointant vers la source de l'image.
3. L'instruction `pano.src = 'images/panoramique2.jpg';` a effectivement
téléchargé et affiché l'image.
4. L'identifiant de la première image est `lamer`.

5. On peut constater que seul le texte de l'élément a été récupéré, en omettant
les balises telles que `<em>` ou `<a>`.
6. Cette fois-ci, les éléments enfants sont conservés.

## Exercice 2
1. Le texte est désormais entièrement en gras.

## Exercice 4
1. Ce sélecteur retient les deux conteneurs de texte de la section d'identifiant
`ajoncs`.
2. Le deuxième élément retenu par le sélecteur a désormais pour fond la 
délicieuse couleur verte.
3. Ce sélecteur récupère pour tous les conteneurs `div` ayant la classe `par`,
les paragraphes `p` en deuxième position par rapport aux autres du même type.