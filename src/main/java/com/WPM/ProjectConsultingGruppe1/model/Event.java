package com.WPM.ProjectConsultingGruppe1.model;

/**
 * Created by Dany on 04/04/2023.
 */
import lombok.Data;
import java.io.Serializable;

@Data
public class Event implements Serializable {
    private Long id;
    private String type;
    private Long userId;
    private Object action;
}
