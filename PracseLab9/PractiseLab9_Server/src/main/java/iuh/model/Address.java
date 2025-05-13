package iuh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Setter
@NoArgsConstructor

@Embeddable
public class Address implements Serializable {
    private String street;
    private String city;
    private String state;
    @Column(name = "zip_code")
    private String zipCode;
}
