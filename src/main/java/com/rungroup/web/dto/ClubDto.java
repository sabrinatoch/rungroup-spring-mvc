package com.rungroup.web.dto;

import com.rungroup.web.models.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

// A DTO allows you to retrieve only the data you want
// (excluding sensitive/unnecessary data) from the object.

@Data
@Builder
public class ClubDto {
    private Long id;
    @NotEmpty(message = "Club title should not be empty")
    private String title;
    @NotEmpty(message = "Photo link should not be empty")
    private String photourl;
    @NotEmpty(message = "Content should not be empty")
    private String content;
    private UserEntity createdBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<EventDto> events;
}
