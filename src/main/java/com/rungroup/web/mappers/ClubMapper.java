package com.rungroup.web.mappers;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;

import java.util.stream.Collectors;

import static com.rungroup.web.mappers.EventMapper.*;

public class ClubMapper {
    public static Club mapToClub(ClubDto clubDto) {
        Club club = Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .content(clubDto.getContent())
                .createdBy(clubDto.getCreatedBy())
                .photourl(clubDto.getPhotourl())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();
        return club;
    }

    public static ClubDto mapToClubDto(Club club) {
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photourl(club.getPhotourl())
                .content(club.getContent())
                .createdBy(club.getCreatedBy())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .events(club.getEvents().stream().map(event -> mapToEventDto(event)).collect(Collectors.toList()))
                .build();
        return clubDto;
    }
}
