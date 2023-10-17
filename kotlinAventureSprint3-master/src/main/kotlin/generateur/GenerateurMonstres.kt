package generateur
import model.item.Arme
import model.item.Armure
import model.personnage.Personnage
import java.nio.file.Paths
import java.nio.file.Files
import qualites
import typeArmes
import monstres
import armes

/**
 * La classe GenerateurArmes permet de générer des objets de type Arme à partir d'un fichier CSV.
 *
 * @param cheminFichier Le chemin vers le fichier CSV contenant les données des Armes.
 */
class GenerateurMonstres(val cheminFichier: String) {
    /**
     * Génère et retourne un mapping des qualités à partir des données contenues dans le fichier CSV.
     *
     * @return Un mapping des qualités où la clé est le nom de la qualité en minuscules et la valeur est un objet Qualite.
     */
    fun generer(): MutableMap<String,Personnage> {
        val mapObjets = mutableMapOf<String, Personnage>()

        // Lecture du fichier CSV, le contenu du fichier est stocké dans une liste mutable :
        val cheminCSV = Paths.get(this.cheminFichier)
        val listeObjCSV = Files.readAllLines(cheminCSV)

        // Instance des objets :
        for (i in 1..listeObjCSV.lastIndex) {
            val ligneObjet = listeObjCSV[i].split(";")
            val cle = ligneObjet[0].lowercase()
            val objet = Personnage(nom = ligneObjet[0], pointDeVie = ligneObjet[1].toInt(), pointDeVieMax = ligneObjet[2].toInt(),
                attaque = ligneObjet[3].toInt(), defense = ligneObjet[4].toInt(), endurance = ligneObjet[5].toInt(),
                vitesse = ligneObjet[6].toInt(), armeEquipee = Arme[ligneObjet[7]], armureEquipee = Armure[ligneObjet[8]]!!,
                inventaire = ligneObjet[9]
            mapObjets[cle] = objet
        }
        return mapObjets
    }
}

