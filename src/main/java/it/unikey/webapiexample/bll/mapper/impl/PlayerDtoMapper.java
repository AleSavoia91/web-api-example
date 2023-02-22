package it.unikey.webapiexample.bll.mapper.impl;

import it.unikey.webapiexample.bll.dto.PlayerDTO;
import it.unikey.webapiexample.bll.mapper.DtoMapper;
import it.unikey.webapiexample.dal.entity.Player;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerDtoMapper extends DtoMapper<Player, PlayerDTO> {

    @Override
    PlayerDTO toDto(Player entity);

    @Override
    Player toEntity(PlayerDTO dto);

    @Override
    List<PlayerDTO> toDtos(List<Player> entities);
}
