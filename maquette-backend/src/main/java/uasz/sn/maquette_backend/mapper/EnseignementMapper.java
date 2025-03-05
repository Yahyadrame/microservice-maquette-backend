package uasz.sn.maquette_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uasz.sn.maquette_backend.dto.EnseignementDTO;
import uasz.sn.maquette_backend.modele.Enseignement;

@Mapper
public interface EnseignementMapper {
    EnseignementMapper INSTANCE = Mappers.getMapper(EnseignementMapper.class);

    @Mapping(source = "ec.id", target = "ecId")
    @Mapping(source = "classe.id", target = "classeId")
    @Mapping(source = "formation.id", target = "formationId")
    EnseignementDTO toDto(Enseignement enseignement);

    @Mapping(target = "ec", ignore = true)
    @Mapping(target = "classe", ignore = true)
    @Mapping(target = "formation", ignore = true)
    Enseignement toEntity(EnseignementDTO enseignementDTO);
}