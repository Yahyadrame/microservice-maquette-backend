package uasz.sn.maquette_backend.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uasz.sn.maquette_backend.dto.ClasseDTO;
import uasz.sn.maquette_backend.modele.Classe;
import uasz.sn.maquette_backend.modele.Maquette;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.MappingTarget;

@Mapper
public interface ClasseMapper {
    ClasseMapper INSTANCE = Mappers.getMapper(ClasseMapper.class);

    @Mapping(target = "maquettesIds", expression = "java(mapMaquettesToIds(classe.getMaquettes()))")
    ClasseDTO toDto(Classe classe);

    @Mapping(target = "maquettes", ignore = true)
    Classe toEntity(ClasseDTO classeDTO);

    // Nouvelle méthode pour mettre à jour une entité existante
    @Mapping(target = "id", ignore = true) // Empêche la modification de l'ID
    void updateEntity(ClasseDTO classeDTO, @MappingTarget Classe classe);

    default List<Long> mapMaquettesToIds(List<Maquette> maquettes) {
        return maquettes != null ? maquettes.stream().map(Maquette::getId).collect(Collectors.toList()) : null;
    }
}
