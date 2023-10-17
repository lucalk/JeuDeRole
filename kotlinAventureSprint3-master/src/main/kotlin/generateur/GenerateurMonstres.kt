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
import armures
import bombe
import model.item.Item
import potion

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

            val inventaire = mutableListOf<Item>()
            val ligneObjet = listeObjCSV[i].split(";")


            val listeNomArme= ligneObjet[9].split(".")
            for(nomArme in listeNomArme ){
                inventaire.add(armes[nomArme]!!)
            }

            val listeNomArmure= ligneObjet[10].split(".")
            for(nomArmure in listeNomArmure ){
                inventaire.add(armures[nomArmure]!!)
            }

            val listeNomBombe= ligneObjet[11].split(".")
            for(nomBombe in listeNomBombe ){
                inventaire.add(bombe[nomBombe]!!)
            }

            val listeNomPotion= ligneObjet[12].split(".")
            for(nomPotion in listeNomPotion){
                inventaire.add(potion[nomPotion]!!)
            }



            val cle = ligneObjet[0].lowercase()
            val objet = Personnage(nom = ligneObjet[0], pointDeVie = ligneObjet[1].toInt(), pointDeVieMax = ligneObjet[2].toInt(),
                attaque = ligneObjet[3].toInt(), defense = ligneObjet[4].toInt(), endurance = ligneObjet[5].toInt(),
                vitesse = ligneObjet[6].toInt(), armeEquipee = armes[ligneObjet[7]]!!, armureEquipee = armures[ligneObjet[8]]!!,
                inventaire =  inventaire,)
            mapObjets[cle] = objet
        }
        return mapObjets
    }
}

