package uasz.sn.maquette_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import uasz.sn.maquette_backend.dto.MaquetteDTO;
import uasz.sn.maquette_backend.modele.Maquette;
import uasz.sn.maquette_backend.modele.UE;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface MaquetteMapper {
    MaquetteMapper INSTANCE = Mappers.getMapper(MaquetteMapper.class);

    @Mapping(target = "uesIds", expression = "java(mapUEsToIds(maquette.getUes()))")
    MaquetteDTO toDto(Maquette maquette);

    @Mapping(target = "ues", ignore = true)
    Maquette toEntity(MaquetteDTO maquetteDTO);

    @Mapping(target = "ues", ignore = true)
    void updateEntity(MaquetteDTO maquetteDTO, @MappingTarget Maquette maquette);

    default List<Long> mapUEsToIds(List<UE> ues) {
        return ues.stream().map(UE::getId).collect(Collectors.toList());
    }
}
