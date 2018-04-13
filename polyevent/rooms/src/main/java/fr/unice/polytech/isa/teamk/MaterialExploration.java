package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Material;

import java.util.List;

public interface MaterialExploration {

    List<Material> getAllMaterials();

    List<Material> getMaterialByType(String materialType);

}
