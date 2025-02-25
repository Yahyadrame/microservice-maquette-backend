package uasz.sn.maquette_backend.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import uasz.sn.maquette_backend.dto.ECDTO;
import uasz.sn.maquette_backend.modele.EC;

@Mapper
public interface ECMapper {
    ECMapper INSTANCE = Mappers.getMapper(ECMapper.class);

    @Mapping(target = "ueId", source = "ue.id")
    ECDTO toDto(EC ec);

    @Mapping(target = "ue", ignore = true)
    EC toEntity(ECDTO ecDTO);

    @Mapping(target = "ue", ignore = true) // Éviter de modifier directement l'UE
    void updateEntity(ECDTO ecDTO, @MappingTarget EC ec);
}
