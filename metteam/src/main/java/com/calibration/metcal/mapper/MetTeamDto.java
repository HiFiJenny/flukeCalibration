package com.calibration.metcal.mapper;

import lombok.Data;

@Data
public class MetTeamDto {
    private int row_num;
    private String CSR;
    /**
     * 校准日期/发布日期
     */
    private String calibrationDate;
    /**
     * 建议复校日期
     */
    private String nextCalibrationDate;
    /**
     * 温度
     */
    private String temperature;
    /**
     * 湿度
     */
    private String humidity;
    /**
     * 接收日期
     */
    private String receiveDate;
    /**
     * 校准结论
     */
    private String calibrationResult;
    /**
     * 证书/报告编号
     */
    private String reportNo;
    /**
     * 委托单位
     */
    private String customer;
    /**
     * 委托单位英文
     */
    private String customerEn;
    /**
     * 委托单位地址
     */
    private String customerAddress;
    /**
     * 委托单位地址英文
     */
    private String customerAddressEn;
    /**
     * 登记号
     */
    private String registerNo;
    /**
     * 备注(客户要求)
     */
    private String remarks;
    /**
     * 设备编号
     */
    private String deviceNo;
    /**
     * 制造厂商
     */
    private String deviceManufacturer;
    /**
     * 型号/规格
     */
    private String deviceModel;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备名称英文
     */
    private String deviceNameEn;
    /**
     * 校准地点
     */
    private String calibrationAddress;
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
