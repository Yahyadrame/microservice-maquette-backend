package uasz.sn.maquette_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import uasz.sn.maquette_backend.dto.EnseignementDTO;
import uasz.sn.maquette_backend.modele.Enseignement;

@Mapper
public interface EnseignementMapper {
    EnseignementMapper INSTANCE = Mappers.getMapper(EnseignementMapper.class);

    @Mapping(target = "utilisateurId", source = "utilisateur.id")
    @Mapping(target = "ecId", source = "ec.id")
    @Mapping(target = "classeId", source = "classe.id")
    EnseignementDTO toDto(Enseignement enseignement);

    @Mapping(target = "utilisateur", ignore = true)
    @Mapping(target = "ec", ignore = true)
    @Mapping(target = "classe", ignore = true)
    Enseignement toEntity(EnseignementDTO enseignementDTO);

    @Mapping(target = "utilisateur", ignore = true)
    @Mapping(target = "ec", ignore = true)
    @Mapping(target = "classe", ignore = true)
    void updateEntity(EnseignementDTO enseignementDTO, @MappingTarget Enseignement enseignement);
}
