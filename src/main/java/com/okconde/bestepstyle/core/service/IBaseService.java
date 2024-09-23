package com.okconde.bestepstyle.core.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Trong Phu on 23/09/2024 22:07
 *
 * @author Trong Phu
 */
public interface IBaseService<E, ID, RQ, RP> {

    List<RP> getPage(Pageable pageable);

    List<RP> getAll();

    RP create(RQ rq);

    RP update(ID id, RQ rq);

    void delete(ID id);

    RP getById(ID id);

}
