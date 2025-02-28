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

    @Mapping(source = "ue.id", target = "ueId") // Mapper l'ID de l'UE
    @Mapping(source = "ue.libelle", target = "ueNom") // Mapper le nom de l'UE
    ECDTO toDto(EC ec);

    @Mapping(target = "ue", ignore = true) // Ignorer la relation UE lors de la conversion DTO -> Entité
    EC toEntity(ECDTO ecDTO);

    @Mapping(target = "ue", ignore = true) // Ignorer la relation UE lors de la mise à jour
    void updateEntity(ECDTO ecDTO, @MappingTarget EC ec);
}