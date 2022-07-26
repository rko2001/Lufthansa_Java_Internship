package com.example.backendservicecore.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true)
public class CustomerDTO extends UserDataDTO {

    @NonNull
    private String address;

    @NonNull
    private String name;


    @Builder(builderMethodName = "CustomerDTOBuilder")
    public CustomerDTO(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password, String address) {
        super(firstName, lastName, username, password);
        this.address = address;
    }
}
