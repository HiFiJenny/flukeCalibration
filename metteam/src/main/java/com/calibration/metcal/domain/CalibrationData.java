package com.calibration.metcal.domain;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CalibrationData implements Serializable {
    private int row_num;
    /**
     * 标准值
     */
    private String nominal;
    /**
     * 测量值
     */
    private String measured;
    /**
     * 上限
     */
    private String upperLimit;
    /**
     * 下限
     */
    private String lowerLimit;
    /**
     * 量程
     */
    private String range;
    /**
     * 结论
     */
    private String status;
}
