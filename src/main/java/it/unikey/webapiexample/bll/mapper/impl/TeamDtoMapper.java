package it.unikey.webapiexample.bll.mapper.impl;

import it.unikey.webapiexample.bll.dto.TeamDTO;
import it.unikey.webapiexample.bll.mapper.DtoMapper;
import it.unikey.webapiexample.dal.entity.Team;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamDtoMapper extends DtoMapper<Team, TeamDTO> {

    @Override
    TeamDTO toDto(Team entity);

    @Override
    Team toEntity(TeamDTO dto);

    @Override
    List<TeamDTO> toDtos(List<Team> entities);
}
