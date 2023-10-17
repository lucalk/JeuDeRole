package model.jeu.generateur

import model.item.Arme
import model.item.Armure
import java.nio.file.Files
import java.nio.file.Paths

import qualites
import typeArmures


/**
     * La classe GenerateurArmes permet de générer des objets de type Arme à partir d'un fichier CSV.
     *
     * @param cheminFichier Le chemin vers le fichier CSV contenant les données des Armes.
     */
    class GenerateurArmures(val cheminFichier: String) {
        /**
         * Génère et retourne un mapping des qualités à partir des données contenues dans le fichier CSV.
         *
         * @return Un mapping des qualités où la clé est le nom de la qualité en minuscules et la valeur est un objet Qualite.
         */
        fun generer(): MutableMap<String, Armure> {
            val mapObjets = mutableMapOf<String, Armure>()

            // Lecture du fichier CSV, le contenu du fichier est stocké dans une liste mutable :
            val cheminCSV = Paths.get(this.cheminFichier)
            val listeObjCSV = Files.readAllLines(cheminCSV)

            // Instance des objets :
            for (i in 1..listeObjCSV.lastIndex) {
                val ligneObjet = listeObjCSV[i].split(";")
                val cle = ligneObjet[0].lowercase()
                val objet = Armure(nom = ligneObjet[0], description = ligneObjet[1], typeArmure = typeArmures[ligneObjet[2]]!!, qualite = qualites[ligneObjet[3]]!!,)
                mapObjets[cle] = objet
            }
            return mapObjets
        }
    }





