package uasz.sn.maquette_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import uasz.sn.maquette_backend.dto.UEDTO;
import uasz.sn.maquette_backend.modele.EC;
import uasz.sn.maquette_backend.modele.UE;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UEMapper {
    UEMapper INSTANCE = Mappers.getMapper(UEMapper.class);

    @Mapping(target = "ecsIds", expression = "java(mapECsToIds(ue.getEcs()))")
    UEDTO toDto(UE ue);

    @Mapping(target = "ecs", ignore = true)
    UE toEntity(UEDTO ueDTO);

    @Mapping(target = "ecs", ignore = true) // Éviter d'écraser la relation
    void updateEntity(UEDTO ueDTO, @MappingTarget UE ue);

    default List<Long> mapECsToIds(List<EC> ecs) {
        return ecs.stream().map(EC::getId).collect(Collectors.toList());
    }
}
