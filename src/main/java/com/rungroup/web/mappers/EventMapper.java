package com.rungroup.web.mappers;

import com.rungroup.web.dto.EventDto;
import com.rungroup.web.models.Event;

public class EventMapper {
    public static Event mapToEvent(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .type(eventDto.getType())
                .photourl(eventDto.getPhotourl())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .club(eventDto.getClub())
                .build();
    }
    public static EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .type(event.getType())
                .photourl(event.getPhotourl())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .club(event.getClub())
                .build();
    }
}
