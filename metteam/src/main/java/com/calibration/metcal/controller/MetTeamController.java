package com.calibration.metcal.controller;

import com.calibration.metcal.domain.MultimeterDto;
import com.calibration.metcal.service.MetTeamDataService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/MetMultimeter")
public class MetTeamController {

    private final MetTeamDataService metTeamDataService;

    public MetTeamController(MetTeamDataService metTeamDataService) {
        this.metTeamDataService = metTeamDataService;
    }

    @GetMapping("/queryForDetail/{callSheetId}")
    public MultimeterDto getMetTeamData(@PathVariable String callSheetId) {
        return metTeamDataService.getMultimeterCalibrationData(callSheetId);
    }
}
