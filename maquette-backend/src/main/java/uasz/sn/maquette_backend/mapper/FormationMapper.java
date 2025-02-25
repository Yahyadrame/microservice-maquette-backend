package uasz.sn.maquette_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import uasz.sn.maquette_backend.dto.FormationDTO;
import uasz.sn.maquette_backend.modele.Classe;
import uasz.sn.maquette_backend.modele.Formation;
import uasz.sn.maquette_backend.modele.Maquette;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface FormationMapper {
    FormationMapper INSTANCE = Mappers.getMapper(FormationMapper.class);

    @Mapping(target = "classesIds", expression = "java(mapClassesToIds(formation.getClasses()))")
    @Mapping(target = "maquettesIds", expression = "java(mapMaquettesToIds(formation.getMaquettes()))")
    FormationDTO toDto(Formation formation);

    @Mapping(target = "classes", ignore = true)
    @Mapping(target = "maquettes", ignore = true)
    Formation toEntity(FormationDTO formationDTO);

    // Nouvelle méthode pour mettre à jour une entité existante
    @Mapping(target = "id", ignore = true) // Empêcher la modification de l'ID
    void updateEntity(FormationDTO formationDTO, @MappingTarget Formation formation);

    default List<Long> mapClassesToIds(List<Classe> classes) {
        return classes != null ? classes.stream().map(Classe::getId).collect(Collectors.toList()) : null;
    }

    default List<Long> mapMaquettesToIds(List<Maquette> maquettes) {
        return maquettes != null ? maquettes.stream().map(Maquette::getId).collect(Collectors.toList()) : null;
    }
}

