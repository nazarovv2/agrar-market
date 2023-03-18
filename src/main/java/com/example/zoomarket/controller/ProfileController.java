package com.example.zoomarket.controller;

import com.example.zoomarket.config.security.CustomUserDetails;
import com.example.zoomarket.dto.profile.ProfileDetailDTO;
import com.example.zoomarket.service.ProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@Tag(name = "Profile Controller", description = "This controller for authorization")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/detail")
    public ResponseEntity<ProfileDetailDTO> editFullName() {
        ProfileDetailDTO result = profileService.getDetail(getUserId());
        return ResponseEntity.ok(result);
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/full_name/{full_name}")
    public ResponseEntity<Boolean> editFullName(@PathVariable("full_name") String fullName) {
        Boolean result = profileService.editFullName(getUserId(), fullName);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/phone/{phone}")
    public ResponseEntity<Boolean> changePhone(@PathVariable("phone") String phone) {
        Boolean result = profileService.changePhone(getUserId(), phone);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/email/{email}")
    public ResponseEntity<Boolean> changeEmail(@PathVariable("email") String email) {
        Boolean result = profileService.changeEmail(getUserId(), email);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/photo/{photo_id}")
    public ResponseEntity<Boolean> changePhoto(@PathVariable("photo_id") String photoId) {
        Boolean result = profileService.changePhoto(getUserId(), photoId);
        return ResponseEntity.ok(result);
    }


    public Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        return user.getId();
    }
}
