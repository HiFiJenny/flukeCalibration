package com.calibration.metcal.service.impl;


import com.calibration.metcal.domain.CalibrationData;
import com.calibration.metcal.domain.MultimeterDto;
import com.calibration.metcal.mapper.MetTeamDto;
import com.calibration.metcal.mapper.MetTeamMapper;
import com.calibration.metcal.service.MetTeamDataService;
import com.calibration.metcal.utils.BeanUtils;
import com.calibration.metcal.utils.StringUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MetTeamDataServiceImpl implements MetTeamDataService {


    private final MetTeamMapper metTeamMapper;

    public MetTeamDataServiceImpl(MetTeamMapper metTeamMapper) {
        this.metTeamMapper = metTeamMapper;
    }

    @Override
    public MultimeterDto getMultimeterCalibrationData(String callSheetId) {
        List<MetTeamDto> metTeamDtos = metTeamMapper.getMetTeamData(callSheetId);
        metTeamDtos.sort((o1, o2) -> {
            if (o1.getRow_num() > o2.getRow_num()) {
                return 1;
            } else if (o1.getRow_num() < o2.getRow_num()) {
                return -1;
            } else {
                return 0;
            }
        });
        if (metTeamDtos == null) return null;
        MultimeterDto multimeterDto = new MultimeterDto();
        multimeterDto.setCalibrationData(new LinkedList<>());
        BeanUtils.copyBeanProp(multimeterDto, metTeamDtos.get(0));
        metTeamDtos.forEach(metTeamDto -> {
            if (metTeamDto.getRange().contains("测量不确定度")) {
                metTeamDto.setUpperLimit(metTeamDto.getRange().trim());
                metTeamDto.setRange("");
            }
            if (!StringUtils.isAllEmpty(metTeamDto.getNominal(), metTeamDto.getMeasured(), metTeamDto.getUpperLimit(), metTeamDto.getLowerLimit(), metTeamDto.getRange(), metTeamDto.getStatus())) {
                multimeterDto.getCalibrationData().add(new CalibrationData(metTeamDto.getRow_num(), metTeamDto.getNominal(), metTeamDto.getMeasured(), metTeamDto.getUpperLimit(), metTeamDto.getLowerLimit(), metTeamDto.getRange(), metTeamDto.getStatus()));
            }
        });
        return multimeterDto;
    }

    public Map<String,List<MetTeamDto>> getCalibrationData(String callSheetId) {
        List<MetTeamDto> metTeamDtos = metTeamMapper.getMetTeamData(callSheetId);
        if (metTeamDtos == null) {
            return null;
        }
        metTeamDtos = metTeamDtos.stream().sorted(Comparator.comparing(MetTeamDto::getRow_num)).collect(Collectors.toList());
        Map<String,List<MetTeamDto>> result = new HashMap<>();
        List<MetTeamDto> values = new LinkedList<>();
        for (MetTeamDto metTeamDto : metTeamDtos) {
            String val = metTeamDto.getRange();
            if (val.contains("测量不确定度")) {
                metTeamDto.setUpperLimit(val.substring(val.indexOf("为")+1).trim());
                metTeamDto.setRange("");
            }
            if(val.contains("Range")){
                val = val.substring(0,val.indexOf("Range"));
                metTeamDto.setRange(val);
            }
            if (StringUtils.isNotEmpty(val) && (val.contains("外观") || val.contains("通电"))) {
                continue;
            }
            //以 交流电压测试、直流电压测试、电阻测试 等字样分组
            if (StringUtils.isNotEmpty(val) && val.contains("测试") && val.contains("电")) {
                //只有一条数据或不足一条数据时，此时数组容器内还是空的（只有一条数据是针对第一行的特殊情况）
                if(values.size()>1){
                    values = new LinkedList<>();
                }
                result.put(val,values);
            }else{
                values.add(metTeamDto);
            }
        }
        return result;
    }
}
