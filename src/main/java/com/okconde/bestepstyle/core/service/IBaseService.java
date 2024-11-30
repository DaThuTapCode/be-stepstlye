package com.okconde.bestepstyle.core.service;

import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

/**
 * Created by Trong Phu on 23/09/2024 22:07
 *
 * @author Trong Phu
 */
public interface IBaseService<E, ID, RQ, RP> {

    //Lấy phân trang
    List<RP> getPage(Pageable pageable);

    //Lấy ra toàn bộ bản ghi
    List<RP> getAll();

    //Tạo bản ghi mới
    RP create(RQ rq) throws Exception;

    //Chỉnh sửa
    RP update(ID id, RQ rq);

    //Xóa
    void delete(ID id);

    //Lấy đối tượng theo id
    RP getById(ID id);

}
