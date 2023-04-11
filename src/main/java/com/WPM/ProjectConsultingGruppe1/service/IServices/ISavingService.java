package com.WPM.ProjectConsultingGruppe1.service.IServices;

/**
 * Created by Dany on 04/04/2023.
 */
import java.util.HashMap;

public interface ISavingService<T, V> {
    HashMap<String, Object> save(T entity, V repository);
}
