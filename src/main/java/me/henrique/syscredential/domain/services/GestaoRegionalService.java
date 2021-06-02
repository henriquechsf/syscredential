package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.model.Regional;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GestaoRegionalService {
    List<Regional> getAll();

    Regional getById(Integer id);

    @Transactional
    Regional save(Regional regional);

    @Transactional
    Regional update(Integer id, Regional regional);

    @Transactional
    void delete(Integer id);
}
