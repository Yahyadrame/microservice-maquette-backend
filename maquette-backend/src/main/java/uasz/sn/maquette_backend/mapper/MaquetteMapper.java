package uasz.sn.maquette_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import uasz.sn.maquette_backend.dto.ECDTO;
import uasz.sn.maquette_backend.dto.MaquetteDTO;
import uasz.sn.maquette_backend.dto.UEDTO;
import uasz.sn.maquette_backend.modele.EC;
import uasz.sn.maquette_backend.modele.Maquette;
import uasz.sn.maquette_backend.modele.UE;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface MaquetteMapper {
    MaquetteMapper INSTANCE = Mappers.getMapper(MaquetteMapper.class);

    @Mapping(source = "classe.id", target = "classeId")
    @Mapping(source = "classe.nom", target = "classeNom")
    @Mapping(source = "formation.id", target = "formationId")
    @Mapping(source = "formation.nom", target = "formationNom")
    @Mapping(target = "ues", expression = "java(mapUEsToDTOs(maquette.getUes()))")
    MaquetteDTO toDto(Maquette maquette);

    @Mapping(target = "classe", ignore = true)
    @Mapping(target = "formation", ignore = true)
    @Mapping(target = "ues", ignore = true)
    Maquette toEntity(MaquetteDTO maquetteDTO);

    @Mapping(target = "classe", ignore = true)
    @Mapping(target = "formation", ignore = true)
    @Mapping(target = "ues", ignore = true)
    @Mapping(target = "id", ignore = true) // Ignorer l'ID lors de la conversion vers l'entité
    void updateEntity(MaquetteDTO maquetteDTO, @MappingTarget Maquette maquette);

    default List<UEDTO> mapUEsToDTOs(List<UE> ues) {
        return ues.stream().map(ue -> {
            UEDTO ueDTO = new UEDTO();
            ueDTO.setId(ue.getId());
            ueDTO.setCode(ue.getCode());
            ueDTO.setLibelle(ue.getLibelle());
            ueDTO.setCredit(ue.getCredit());
            ueDTO.setCoefficient(ue.getCoefficient());
            ueDTO.setEcs(mapECsToDTOs(ue.getEcs())); // Mapper les ECs
            return ueDTO;
        }).collect(Collectors.toList());
    }

    default List<ECDTO> mapECsToDTOs(List<EC> ecs) {
        return ecs.stream().map(ec -> {
            ECDTO ecDTO = new ECDTO();
            ecDTO.setId(ec.getId());
            ecDTO.setLibelle(ec.getLibelle());
            ecDTO.setCm(ec.getCm());
            ecDTO.setTd(ec.getTd());
            ecDTO.setTp(ec.getTp());
            ecDTO.setTpe(ec.getTpe());
            ecDTO.setVht(ec.getVht());
            ecDTO.setCoefficient(ec.getCoefficient());
            return ecDTO;
        }).collect(Collectors.toList());
    }
}