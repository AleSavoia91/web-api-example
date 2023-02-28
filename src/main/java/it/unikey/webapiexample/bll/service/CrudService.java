package it.unikey.webapiexample.bll.service;

import it.unikey.webapiexample.bll.exception.PlayerGenericException;

import java.util.List;

public interface CrudService<D> {

    D insert(D dto);

    List<D> getAll();

    D getById(Long id) throws PlayerGenericException;

    D update(Long playerId, D dto) throws PlayerGenericException;

    void deleteById(Long id) throws PlayerGenericException;

}
