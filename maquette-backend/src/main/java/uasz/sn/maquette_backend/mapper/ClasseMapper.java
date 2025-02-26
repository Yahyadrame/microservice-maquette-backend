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
    void updateEntity(ClasseDTO classeDTO, @MappingTarget Classe classe);

    default List<Long> mapMaquettesToIds(List<Maquette> maquettes) {
        if (maquettes == null) {
            return List.of(); // Retourne une liste vide si maquettes est null
        }
        return maquettes.stream()
                .map(Maquette::getId) // Utilise la méthode getId() de Maquette
                .collect(Collectors.toList());
    }
}
