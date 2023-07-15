package com.calibration.metcal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MetTeamMapper {
    List<Map<String, Object>> getMetTeamData2(@Param("callSheetId") String callSheetId);

    List<MetTeamDto> getMetTeamData(@Param("callSheetId") String callSheetId);
}
