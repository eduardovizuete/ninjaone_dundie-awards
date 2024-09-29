package com.ninjaone.dundie_awards.mapper;

import com.ninjaone.dundie_awards.dto.ActivityDTO;
import com.ninjaone.dundie_awards.model.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ActivityMapper {

    ActivityMapper MAPPER = Mappers.getMapper(ActivityMapper.class);

    Activity toActivity(ActivityDTO dto);

    ActivityDTO toActivityDTO(Activity activity);

    List<Activity> toActivityList(List<ActivityDTO> activitiesDTO);

    List<ActivityDTO> toActivityDTOList(List<Activity> activities);
    
}
