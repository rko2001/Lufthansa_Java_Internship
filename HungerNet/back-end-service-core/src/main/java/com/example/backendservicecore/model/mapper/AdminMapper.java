package com.example.backendservicecore.model.mapper;


import com.example.backendservicecore.model.Admin;
import com.example.backendservicecore.model.Restaurant;
import com.example.backendservicecore.model.dto.AdminDTO;

public class AdminMapper implements Mapper<Admin, AdminDTO> {
    private static AdminMapper adminMapper = null;

    private AdminMapper() { }

    public static AdminMapper getInstance() {
        if (adminMapper == null) {
            adminMapper = new AdminMapper();
        }
        return adminMapper;
    }

    @Override
    public Admin convertFromDTO(AdminDTO adminDTO) {
        return null;
    }

    @Override
    public AdminDTO convertToDTO(Admin admin) {
        return AdminDTO.AdminDTOBuilder()
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .username(admin.getUsername())
                .password(admin.getPassword())
                .restaurant((Restaurant) admin.getRestaurants())
                .build();
    }
}
