package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class Address implements Serializable {
    private String street;
    private String city;
    private String state;

    @Column(name = "zip_code")
    private String zipCode;
}
