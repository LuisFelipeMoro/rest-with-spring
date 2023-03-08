package com.rest.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.dozer.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@JsonPropertyOrder({"key", "firstName", "lastName", "address", "gender"})
public class PersonDto extends RepresentationModel<PersonDto> implements Serializable {
        private static final long serialVersionUID = 1l;

        // Podesse usar @JsonIgnore para que o campo não apareca
        @JsonProperty("id")
        @Mapping("id")
        private long key;
        private String firstName;
        private String lastName;
        private String address;
        private String gender;

    }

