package com.DBproject.DBproject.Domain_Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class estimation_pm implements Serializable {
    private String project;
    private int employee;

}
