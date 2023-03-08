package com.rest.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.dozer.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonPropertyOrder({"key", "title", "author", "price", "launchDate"})
public class BookDto extends RepresentationModel<BookDto> implements Serializable {

    private static final long serialVersionUID = 1l;
    // Podesse usar @JsonIgnore para que o campo não apareca
    @JsonProperty("id")
    @Mapping("id")
    private long key;
    private String author;
    @JsonProperty("launch_date")
    private Date launchDate;
    private Double price;
    private String title;

}
