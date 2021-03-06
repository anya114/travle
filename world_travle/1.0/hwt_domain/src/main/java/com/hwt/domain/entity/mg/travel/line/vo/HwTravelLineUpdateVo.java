package com.hwt.domain.entity.mg.travel.line.vo;

import java.io.Serializable;

/**
 * 旅行社线路
 * @author Administrator
 *
 */
public class HwTravelLineUpdateVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 线路id
	 */
	private Long line_id; 
	/**
	 * 线路名称
	 */
	private String line_name;
	
	/**
	 * 行程天数
	 */
	private Integer day_num;
	
	/**
	 * 封面图片
	 */
	private String line_cover;
	
	/**
	 * 价格  json字符串
	 */
	private String line_price;

	/**
	 * 是否下架  0-是 1-否
	 */
	private Integer is_hide;
	
	
	/**
	 * 修改时间
	 */
	private Long update_time;
	
	/**
	 * 线路分类id
	 */
	private Long line_type;
	
	/**
	 * 线路服务保障集  如 1,2,3
	 */
	private String line_service;
	
	/**
	 * 线路图片集
	 */
	private String line_images;
	
	/**
	 * 图文详情
	 */
	private String line_info;
	
	/**
	 * 景点关联集 如 z1zz2zz3z
	 */
	private String scenics;
	
	/**
	 * 交通信息
	 */
	private String line_traffic;
	
	/**
	 * 酒店住宿
	 */
	private String line_hotel;
	
	/**
	 * 行程介绍
	 */
	private String line_trip;
	
	/**
	 * 餐饮
	 */
	private String line_restaurant;
	
	/**
	 * 费用包含
	 */
	private String line_cost_info;
	
	/**
	 * 费用不含
	 */
	private String line_notcost_info;

	/**
	 * 自费项目
	 */
	private String line_self_expense;
	
	/**
	 * 预定须知
	 */
	private String line_predetermined_instructions;
	
	/**
	 * 保险说明
	 */
	private String line_insurance_description;
	
	/**
	 * 取消说明
	 */
	private String line_cancel_description;
	
	/**
	 * 安全提醒
	 */
	private String line_security_reminding;

	public Long getLine_id() {
		return line_id;
	}

	public void setLine_id(Long line_id) {
		this.line_id = line_id;
	}

	public String getLine_name() {
		return line_name;
	}

	public void setLine_name(String line_name) {
		this.line_name = line_name;
	}

	public Integer getDay_num() {
		return day_num;
	}

	public void setDay_num(Integer day_num) {
		this.day_num = day_num;
	}

	public String getLine_cover() {
		return line_cover;
	}

	public void setLine_cover(String line_cover) {
		this.line_cover = line_cover;
	}

	public String getLine_price() {
		return line_price;
	}

	public void setLine_price(String line_price) {
		this.line_price = line_price;
	}

	public Integer getIs_hide() {
		return is_hide;
	}

	public void setIs_hide(Integer is_hide) {
		this.is_hide = is_hide;
	}

	public Long getLine_type() {
		return line_type;
	}

	public void setLine_type(Long line_type) {
		this.line_type = line_type;
	}

	public String getLine_service() {
		return line_service;
	}

	public void setLine_service(String line_service) {
		this.line_service = line_service;
	}

	public String getLine_images() {
		return line_images;
	}

	public void setLine_images(String line_images) {
		this.line_images = line_images;
	}

	public String getLine_info() {
		return line_info;
	}

	public void setLine_info(String line_info) {
		this.line_info = line_info;
	}

	public String getScenics() {
		return scenics;
	}

	public void setScenics(String scenics) {
		this.scenics = scenics;
	}

	public String getLine_traffic() {
		return line_traffic;
	}

	public void setLine_traffic(String line_traffic) {
		this.line_traffic = line_traffic;
	}

	public String getLine_hotel() {
		return line_hotel;
	}

	public void setLine_hotel(String line_hotel) {
		this.line_hotel = line_hotel;
	}

	public String getLine_trip() {
		return line_trip;
	}

	public void setLine_trip(String line_trip) {
		this.line_trip = line_trip;
	}

	public String getLine_restaurant() {
		return line_restaurant;
	}

	public void setLine_restaurant(String line_restaurant) {
		this.line_restaurant = line_restaurant;
	}

	public String getLine_cost_info() {
		return line_cost_info;
	}

	public void setLine_cost_info(String line_cost_info) {
		this.line_cost_info = line_cost_info;
	}

	public String getLine_notcost_info() {
		return line_notcost_info;
	}

	public void setLine_notcost_info(String line_notcost_info) {
		this.line_notcost_info = line_notcost_info;
	}

	public String getLine_self_expense() {
		return line_self_expense;
	}

	public void setLine_self_expense(String line_self_expense) {
		this.line_self_expense = line_self_expense;
	}

	public String getLine_predetermined_instructions() {
		return line_predetermined_instructions;
	}

	public void setLine_predetermined_instructions(String line_predetermined_instructions) {
		this.line_predetermined_instructions = line_predetermined_instructions;
	}

	public String getLine_insurance_description() {
		return line_insurance_description;
	}

	public void setLine_insurance_description(String line_insurance_description) {
		this.line_insurance_description = line_insurance_description;
	}

	public String getLine_cancel_description() {
		return line_cancel_description;
	}

	public void setLine_cancel_description(String line_cancel_description) {
		this.line_cancel_description = line_cancel_description;
	}

	public String getLine_security_reminding() {
		return line_security_reminding;
	}

	public void setLine_security_reminding(String line_security_reminding) {
		this.line_security_reminding = line_security_reminding;
	}

	public Long getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Long update_time) {
		this.update_time = update_time;
	}

}
