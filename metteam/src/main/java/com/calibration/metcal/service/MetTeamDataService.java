package com.calibration.metcal.service;


import com.calibration.metcal.domain.MultimeterDto;

public interface MetTeamDataService {
    MultimeterDto getMultimeterCalibrationData(String callSheetId);

}
