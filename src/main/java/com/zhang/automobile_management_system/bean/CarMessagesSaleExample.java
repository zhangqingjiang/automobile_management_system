package com.zhang.automobile_management_system.bean;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class CarMessagesSaleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    public CarMessagesSaleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCarIdIsNull() {
            addCriterion("car_id is null");
            return (Criteria) this;
        }

        public Criteria andCarIdIsNotNull() {
            addCriterion("car_id is not null");
            return (Criteria) this;
        }

        public Criteria andCarIdEqualTo(String value) {
            addCriterion("car_id =", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotEqualTo(String value) {
            addCriterion("car_id <>", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdGreaterThan(String value) {
            addCriterion("car_id >", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdGreaterThanOrEqualTo(String value) {
            addCriterion("car_id >=", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdLessThan(String value) {
            addCriterion("car_id <", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdLessThanOrEqualTo(String value) {
            addCriterion("car_id <=", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdLike(String value) {
            addCriterion("car_id like", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotLike(String value) {
            addCriterion("car_id not like", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdIn(List<String> values) {
            addCriterion("car_id in", values, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotIn(List<String> values) {
            addCriterion("car_id not in", values, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdBetween(String value1, String value2) {
            addCriterion("car_id between", value1, value2, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotBetween(String value1, String value2) {
            addCriterion("car_id not between", value1, value2, "carId");
            return (Criteria) this;
        }

        public Criteria andCarStockIdIsNull() {
            addCriterion("car_stock_id is null");
            return (Criteria) this;
        }

        public Criteria andCarStockIdIsNotNull() {
            addCriterion("car_stock_id is not null");
            return (Criteria) this;
        }

        public Criteria andCarStockIdEqualTo(String value) {
            addCriterion("car_stock_id =", value, "carStockId");
            return (Criteria) this;
        }

        public Criteria andCarStockIdNotEqualTo(String value) {
            addCriterion("car_stock_id <>", value, "carStockId");
            return (Criteria) this;
        }

        public Criteria andCarStockIdGreaterThan(String value) {
            addCriterion("car_stock_id >", value, "carStockId");
            return (Criteria) this;
        }

        public Criteria andCarStockIdGreaterThanOrEqualTo(String value) {
            addCriterion("car_stock_id >=", value, "carStockId");
            return (Criteria) this;
        }

        public Criteria andCarStockIdLessThan(String value) {
            addCriterion("car_stock_id <", value, "carStockId");
            return (Criteria) this;
        }

        public Criteria andCarStockIdLessThanOrEqualTo(String value) {
            addCriterion("car_stock_id <=", value, "carStockId");
            return (Criteria) this;
        }

        public Criteria andCarStockIdLike(String value) {
            addCriterion("car_stock_id like", value, "carStockId");
            return (Criteria) this;
        }

        public Criteria andCarStockIdNotLike(String value) {
            addCriterion("car_stock_id not like", value, "carStockId");
            return (Criteria) this;
        }

        public Criteria andCarStockIdIn(List<String> values) {
            addCriterion("car_stock_id in", values, "carStockId");
            return (Criteria) this;
        }

        public Criteria andCarStockIdNotIn(List<String> values) {
            addCriterion("car_stock_id not in", values, "carStockId");
            return (Criteria) this;
        }

        public Criteria andCarStockIdBetween(String value1, String value2) {
            addCriterion("car_stock_id between", value1, value2, "carStockId");
            return (Criteria) this;
        }

        public Criteria andCarStockIdNotBetween(String value1, String value2) {
            addCriterion("car_stock_id not between", value1, value2, "carStockId");
            return (Criteria) this;
        }

        public Criteria andCarNameIsNull() {
            addCriterion("car_name is null");
            return (Criteria) this;
        }

        public Criteria andCarNameIsNotNull() {
            addCriterion("car_name is not null");
            return (Criteria) this;
        }

        public Criteria andCarNameEqualTo(String value) {
            addCriterion("car_name =", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameNotEqualTo(String value) {
            addCriterion("car_name <>", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameGreaterThan(String value) {
            addCriterion("car_name >", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameGreaterThanOrEqualTo(String value) {
            addCriterion("car_name >=", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameLessThan(String value) {
            addCriterion("car_name <", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameLessThanOrEqualTo(String value) {
            addCriterion("car_name <=", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameLike(String value) {
            addCriterion("car_name like", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameNotLike(String value) {
            addCriterion("car_name not like", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameIn(List<String> values) {
            addCriterion("car_name in", values, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameNotIn(List<String> values) {
            addCriterion("car_name not in", values, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameBetween(String value1, String value2) {
            addCriterion("car_name between", value1, value2, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameNotBetween(String value1, String value2) {
            addCriterion("car_name not between", value1, value2, "carName");
            return (Criteria) this;
        }

        public Criteria andCarBrandIsNull() {
            addCriterion("car_brand is null");
            return (Criteria) this;
        }

        public Criteria andCarBrandIsNotNull() {
            addCriterion("car_brand is not null");
            return (Criteria) this;
        }

        public Criteria andCarBrandEqualTo(String value) {
            addCriterion("car_brand =", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandNotEqualTo(String value) {
            addCriterion("car_brand <>", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandGreaterThan(String value) {
            addCriterion("car_brand >", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandGreaterThanOrEqualTo(String value) {
            addCriterion("car_brand >=", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandLessThan(String value) {
            addCriterion("car_brand <", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandLessThanOrEqualTo(String value) {
            addCriterion("car_brand <=", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandLike(String value) {
            addCriterion("car_brand like", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandNotLike(String value) {
            addCriterion("car_brand not like", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandIn(List<String> values) {
            addCriterion("car_brand in", values, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandNotIn(List<String> values) {
            addCriterion("car_brand not in", values, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandBetween(String value1, String value2) {
            addCriterion("car_brand between", value1, value2, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandNotBetween(String value1, String value2) {
            addCriterion("car_brand not between", value1, value2, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarVersionIsNull() {
            addCriterion("car_version is null");
            return (Criteria) this;
        }

        public Criteria andCarVersionIsNotNull() {
            addCriterion("car_version is not null");
            return (Criteria) this;
        }

        public Criteria andCarVersionEqualTo(BigDecimal value) {
            addCriterion("car_version =", value, "carVersion");
            return (Criteria) this;
        }

        public Criteria andCarVersionNotEqualTo(BigDecimal value) {
            addCriterion("car_version <>", value, "carVersion");
            return (Criteria) this;
        }

        public Criteria andCarVersionGreaterThan(BigDecimal value) {
            addCriterion("car_version >", value, "carVersion");
            return (Criteria) this;
        }

        public Criteria andCarVersionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("car_version >=", value, "carVersion");
            return (Criteria) this;
        }

        public Criteria andCarVersionLessThan(BigDecimal value) {
            addCriterion("car_version <", value, "carVersion");
            return (Criteria) this;
        }

        public Criteria andCarVersionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("car_version <=", value, "carVersion");
            return (Criteria) this;
        }

        public Criteria andCarVersionIn(List<BigDecimal> values) {
            addCriterion("car_version in", values, "carVersion");
            return (Criteria) this;
        }

        public Criteria andCarVersionNotIn(List<BigDecimal> values) {
            addCriterion("car_version not in", values, "carVersion");
            return (Criteria) this;
        }

        public Criteria andCarVersionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("car_version between", value1, value2, "carVersion");
            return (Criteria) this;
        }

        public Criteria andCarVersionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("car_version not between", value1, value2, "carVersion");
            return (Criteria) this;
        }

        public Criteria andCarTypeIsNull() {
            addCriterion("car_type is null");
            return (Criteria) this;
        }

        public Criteria andCarTypeIsNotNull() {
            addCriterion("car_type is not null");
            return (Criteria) this;
        }

        public Criteria andCarTypeEqualTo(String value) {
            addCriterion("car_type =", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotEqualTo(String value) {
            addCriterion("car_type <>", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeGreaterThan(String value) {
            addCriterion("car_type >", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeGreaterThanOrEqualTo(String value) {
            addCriterion("car_type >=", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLessThan(String value) {
            addCriterion("car_type <", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLessThanOrEqualTo(String value) {
            addCriterion("car_type <=", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLike(String value) {
            addCriterion("car_type like", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotLike(String value) {
            addCriterion("car_type not like", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeIn(List<String> values) {
            addCriterion("car_type in", values, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotIn(List<String> values) {
            addCriterion("car_type not in", values, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeBetween(String value1, String value2) {
            addCriterion("car_type between", value1, value2, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotBetween(String value1, String value2) {
            addCriterion("car_type not between", value1, value2, "carType");
            return (Criteria) this;
        }

        public Criteria andCarDisplacementIsNull() {
            addCriterion("car_displacement is null");
            return (Criteria) this;
        }

        public Criteria andCarDisplacementIsNotNull() {
            addCriterion("car_displacement is not null");
            return (Criteria) this;
        }

        public Criteria andCarDisplacementEqualTo(BigDecimal value) {
            addCriterion("car_displacement =", value, "carDisplacement");
            return (Criteria) this;
        }

        public Criteria andCarDisplacementNotEqualTo(BigDecimal value) {
            addCriterion("car_displacement <>", value, "carDisplacement");
            return (Criteria) this;
        }

        public Criteria andCarDisplacementGreaterThan(BigDecimal value) {
            addCriterion("car_displacement >", value, "carDisplacement");
            return (Criteria) this;
        }

        public Criteria andCarDisplacementGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("car_displacement >=", value, "carDisplacement");
            return (Criteria) this;
        }

        public Criteria andCarDisplacementLessThan(BigDecimal value) {
            addCriterion("car_displacement <", value, "carDisplacement");
            return (Criteria) this;
        }

        public Criteria andCarDisplacementLessThanOrEqualTo(BigDecimal value) {
            addCriterion("car_displacement <=", value, "carDisplacement");
            return (Criteria) this;
        }

        public Criteria andCarDisplacementIn(List<BigDecimal> values) {
            addCriterion("car_displacement in", values, "carDisplacement");
            return (Criteria) this;
        }

        public Criteria andCarDisplacementNotIn(List<BigDecimal> values) {
            addCriterion("car_displacement not in", values, "carDisplacement");
            return (Criteria) this;
        }

        public Criteria andCarDisplacementBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("car_displacement between", value1, value2, "carDisplacement");
            return (Criteria) this;
        }

        public Criteria andCarDisplacementNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("car_displacement not between", value1, value2, "carDisplacement");
            return (Criteria) this;
        }

        public Criteria andCarGearboxIsNull() {
            addCriterion("car_gearbox is null");
            return (Criteria) this;
        }

        public Criteria andCarGearboxIsNotNull() {
            addCriterion("car_gearbox is not null");
            return (Criteria) this;
        }

        public Criteria andCarGearboxEqualTo(String value) {
            addCriterion("car_gearbox =", value, "carGearbox");
            return (Criteria) this;
        }

        public Criteria andCarGearboxNotEqualTo(String value) {
            addCriterion("car_gearbox <>", value, "carGearbox");
            return (Criteria) this;
        }

        public Criteria andCarGearboxGreaterThan(String value) {
            addCriterion("car_gearbox >", value, "carGearbox");
            return (Criteria) this;
        }

        public Criteria andCarGearboxGreaterThanOrEqualTo(String value) {
            addCriterion("car_gearbox >=", value, "carGearbox");
            return (Criteria) this;
        }

        public Criteria andCarGearboxLessThan(String value) {
            addCriterion("car_gearbox <", value, "carGearbox");
            return (Criteria) this;
        }

        public Criteria andCarGearboxLessThanOrEqualTo(String value) {
            addCriterion("car_gearbox <=", value, "carGearbox");
            return (Criteria) this;
        }

        public Criteria andCarGearboxLike(String value) {
            addCriterion("car_gearbox like", value, "carGearbox");
            return (Criteria) this;
        }

        public Criteria andCarGearboxNotLike(String value) {
            addCriterion("car_gearbox not like", value, "carGearbox");
            return (Criteria) this;
        }

        public Criteria andCarGearboxIn(List<String> values) {
            addCriterion("car_gearbox in", values, "carGearbox");
            return (Criteria) this;
        }

        public Criteria andCarGearboxNotIn(List<String> values) {
            addCriterion("car_gearbox not in", values, "carGearbox");
            return (Criteria) this;
        }

        public Criteria andCarGearboxBetween(String value1, String value2) {
            addCriterion("car_gearbox between", value1, value2, "carGearbox");
            return (Criteria) this;
        }

        public Criteria andCarGearboxNotBetween(String value1, String value2) {
            addCriterion("car_gearbox not between", value1, value2, "carGearbox");
            return (Criteria) this;
        }

        public Criteria andCarColorIsNull() {
            addCriterion("car_color is null");
            return (Criteria) this;
        }

        public Criteria andCarColorIsNotNull() {
            addCriterion("car_color is not null");
            return (Criteria) this;
        }

        public Criteria andCarColorEqualTo(String value) {
            addCriterion("car_color =", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorNotEqualTo(String value) {
            addCriterion("car_color <>", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorGreaterThan(String value) {
            addCriterion("car_color >", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorGreaterThanOrEqualTo(String value) {
            addCriterion("car_color >=", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorLessThan(String value) {
            addCriterion("car_color <", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorLessThanOrEqualTo(String value) {
            addCriterion("car_color <=", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorLike(String value) {
            addCriterion("car_color like", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorNotLike(String value) {
            addCriterion("car_color not like", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorIn(List<String> values) {
            addCriterion("car_color in", values, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorNotIn(List<String> values) {
            addCriterion("car_color not in", values, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorBetween(String value1, String value2) {
            addCriterion("car_color between", value1, value2, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorNotBetween(String value1, String value2) {
            addCriterion("car_color not between", value1, value2, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarPriceIsNull() {
            addCriterion("car_price is null");
            return (Criteria) this;
        }

        public Criteria andCarPriceIsNotNull() {
            addCriterion("car_price is not null");
            return (Criteria) this;
        }

        public Criteria andCarPriceEqualTo(BigDecimal value) {
            addCriterion("car_price =", value, "carPrice");
            return (Criteria) this;
        }

        public Criteria andCarPriceNotEqualTo(BigDecimal value) {
            addCriterion("car_price <>", value, "carPrice");
            return (Criteria) this;
        }

        public Criteria andCarPriceGreaterThan(BigDecimal value) {
            addCriterion("car_price >", value, "carPrice");
            return (Criteria) this;
        }

        public Criteria andCarPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("car_price >=", value, "carPrice");
            return (Criteria) this;
        }

        public Criteria andCarPriceLessThan(BigDecimal value) {
            addCriterion("car_price <", value, "carPrice");
            return (Criteria) this;
        }

        public Criteria andCarPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("car_price <=", value, "carPrice");
            return (Criteria) this;
        }

        public Criteria andCarPriceIn(List<BigDecimal> values) {
            addCriterion("car_price in", values, "carPrice");
            return (Criteria) this;
        }

        public Criteria andCarPriceNotIn(List<BigDecimal> values) {
            addCriterion("car_price not in", values, "carPrice");
            return (Criteria) this;
        }

        public Criteria andCarPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("car_price between", value1, value2, "carPrice");
            return (Criteria) this;
        }

        public Criteria andCarPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("car_price not between", value1, value2, "carPrice");
            return (Criteria) this;
        }

        public Criteria andCarEnergyIsNull() {
            addCriterion("car_energy is null");
            return (Criteria) this;
        }

        public Criteria andCarEnergyIsNotNull() {
            addCriterion("car_energy is not null");
            return (Criteria) this;
        }

        public Criteria andCarEnergyEqualTo(String value) {
            addCriterion("car_energy =", value, "carEnergy");
            return (Criteria) this;
        }

        public Criteria andCarEnergyNotEqualTo(String value) {
            addCriterion("car_energy <>", value, "carEnergy");
            return (Criteria) this;
        }

        public Criteria andCarEnergyGreaterThan(String value) {
            addCriterion("car_energy >", value, "carEnergy");
            return (Criteria) this;
        }

        public Criteria andCarEnergyGreaterThanOrEqualTo(String value) {
            addCriterion("car_energy >=", value, "carEnergy");
            return (Criteria) this;
        }

        public Criteria andCarEnergyLessThan(String value) {
            addCriterion("car_energy <", value, "carEnergy");
            return (Criteria) this;
        }

        public Criteria andCarEnergyLessThanOrEqualTo(String value) {
            addCriterion("car_energy <=", value, "carEnergy");
            return (Criteria) this;
        }

        public Criteria andCarEnergyLike(String value) {
            addCriterion("car_energy like", value, "carEnergy");
            return (Criteria) this;
        }

        public Criteria andCarEnergyNotLike(String value) {
            addCriterion("car_energy not like", value, "carEnergy");
            return (Criteria) this;
        }

        public Criteria andCarEnergyIn(List<String> values) {
            addCriterion("car_energy in", values, "carEnergy");
            return (Criteria) this;
        }

        public Criteria andCarEnergyNotIn(List<String> values) {
            addCriterion("car_energy not in", values, "carEnergy");
            return (Criteria) this;
        }

        public Criteria andCarEnergyBetween(String value1, String value2) {
            addCriterion("car_energy between", value1, value2, "carEnergy");
            return (Criteria) this;
        }

        public Criteria andCarEnergyNotBetween(String value1, String value2) {
            addCriterion("car_energy not between", value1, value2, "carEnergy");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeIsNull() {
            addCriterion("car_sale_employee is null");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeIsNotNull() {
            addCriterion("car_sale_employee is not null");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeEqualTo(String value) {
            addCriterion("car_sale_employee =", value, "carSaleEmployee");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeNotEqualTo(String value) {
            addCriterion("car_sale_employee <>", value, "carSaleEmployee");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeGreaterThan(String value) {
            addCriterion("car_sale_employee >", value, "carSaleEmployee");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeGreaterThanOrEqualTo(String value) {
            addCriterion("car_sale_employee >=", value, "carSaleEmployee");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeLessThan(String value) {
            addCriterion("car_sale_employee <", value, "carSaleEmployee");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeLessThanOrEqualTo(String value) {
            addCriterion("car_sale_employee <=", value, "carSaleEmployee");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeLike(String value) {
            addCriterion("car_sale_employee like", value, "carSaleEmployee");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeNotLike(String value) {
            addCriterion("car_sale_employee not like", value, "carSaleEmployee");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeIn(List<String> values) {
            addCriterion("car_sale_employee in", values, "carSaleEmployee");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeNotIn(List<String> values) {
            addCriterion("car_sale_employee not in", values, "carSaleEmployee");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeBetween(String value1, String value2) {
            addCriterion("car_sale_employee between", value1, value2, "carSaleEmployee");
            return (Criteria) this;
        }

        public Criteria andCarSaleEmployeeNotBetween(String value1, String value2) {
            addCriterion("car_sale_employee not between", value1, value2, "carSaleEmployee");
            return (Criteria) this;
        }

        public Criteria andCarProvideIsNull() {
            addCriterion("car_provide is null");
            return (Criteria) this;
        }

        public Criteria andCarProvideIsNotNull() {
            addCriterion("car_provide is not null");
            return (Criteria) this;
        }

        public Criteria andCarProvideEqualTo(String value) {
            addCriterion("car_provide =", value, "carProvide");
            return (Criteria) this;
        }

        public Criteria andCarProvideNotEqualTo(String value) {
            addCriterion("car_provide <>", value, "carProvide");
            return (Criteria) this;
        }

        public Criteria andCarProvideGreaterThan(String value) {
            addCriterion("car_provide >", value, "carProvide");
            return (Criteria) this;
        }

        public Criteria andCarProvideGreaterThanOrEqualTo(String value) {
            addCriterion("car_provide >=", value, "carProvide");
            return (Criteria) this;
        }

        public Criteria andCarProvideLessThan(String value) {
            addCriterion("car_provide <", value, "carProvide");
            return (Criteria) this;
        }

        public Criteria andCarProvideLessThanOrEqualTo(String value) {
            addCriterion("car_provide <=", value, "carProvide");
            return (Criteria) this;
        }

        public Criteria andCarProvideLike(String value) {
            addCriterion("car_provide like", value, "carProvide");
            return (Criteria) this;
        }

        public Criteria andCarProvideNotLike(String value) {
            addCriterion("car_provide not like", value, "carProvide");
            return (Criteria) this;
        }

        public Criteria andCarProvideIn(List<String> values) {
            addCriterion("car_provide in", values, "carProvide");
            return (Criteria) this;
        }

        public Criteria andCarProvideNotIn(List<String> values) {
            addCriterion("car_provide not in", values, "carProvide");
            return (Criteria) this;
        }

        public Criteria andCarProvideBetween(String value1, String value2) {
            addCriterion("car_provide between", value1, value2, "carProvide");
            return (Criteria) this;
        }

        public Criteria andCarProvideNotBetween(String value1, String value2) {
            addCriterion("car_provide not between", value1, value2, "carProvide");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table car_messages_sale
     *
     * @mbggenerated do_not_delete_during_merge Thu Apr 20 13:40:16 CST 2023
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table car_messages_sale
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}