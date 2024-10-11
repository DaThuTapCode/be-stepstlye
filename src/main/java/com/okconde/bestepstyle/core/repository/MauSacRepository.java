package com.okconde.bestepstyle.core.repository;

import com.okconde.bestepstyle.core.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created at 23/09/2024 by Ngo Tu
 *
 * @author: Ngo Tu
 */
public interface MauSacRepository extends JpaRepository<MauSac, Long> {

}
