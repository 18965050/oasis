package cn.xyz.chaos.examples.demo.entity.criteria;

import java.util.ArrayList;
import java.util.List;

public class ProdSkuOptionCriteria {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    public ProdSkuOptionCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
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

        public Criteria andSkuOptionIdIsNull() {
            addCriterion("sku_option_id is null");
            return (Criteria) this;
        }

        public Criteria andSkuOptionIdIsNotNull() {
            addCriterion("sku_option_id is not null");
            return (Criteria) this;
        }

        public Criteria andSkuOptionIdEqualTo(Integer value) {
            addCriterion("sku_option_id =", value, "skuOptionId");
            return (Criteria) this;
        }

        public Criteria andSkuOptionIdNotEqualTo(Integer value) {
            addCriterion("sku_option_id <>", value, "skuOptionId");
            return (Criteria) this;
        }

        public Criteria andSkuOptionIdGreaterThan(Integer value) {
            addCriterion("sku_option_id >", value, "skuOptionId");
            return (Criteria) this;
        }

        public Criteria andSkuOptionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sku_option_id >=", value, "skuOptionId");
            return (Criteria) this;
        }

        public Criteria andSkuOptionIdLessThan(Integer value) {
            addCriterion("sku_option_id <", value, "skuOptionId");
            return (Criteria) this;
        }

        public Criteria andSkuOptionIdLessThanOrEqualTo(Integer value) {
            addCriterion("sku_option_id <=", value, "skuOptionId");
            return (Criteria) this;
        }

        public Criteria andSkuOptionIdIn(List<Integer> values) {
            addCriterion("sku_option_id in", values, "skuOptionId");
            return (Criteria) this;
        }

        public Criteria andSkuOptionIdNotIn(List<Integer> values) {
            addCriterion("sku_option_id not in", values, "skuOptionId");
            return (Criteria) this;
        }

        public Criteria andSkuOptionIdBetween(Integer value1, Integer value2) {
            addCriterion("sku_option_id between", value1, value2, "skuOptionId");
            return (Criteria) this;
        }

        public Criteria andSkuOptionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sku_option_id not between", value1, value2, "skuOptionId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNull() {
            addCriterion("sku_id is null");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNotNull() {
            addCriterion("sku_id is not null");
            return (Criteria) this;
        }

        public Criteria andSkuIdEqualTo(Integer value) {
            addCriterion("sku_id =", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotEqualTo(Integer value) {
            addCriterion("sku_id <>", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThan(Integer value) {
            addCriterion("sku_id >", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sku_id >=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThan(Integer value) {
            addCriterion("sku_id <", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThanOrEqualTo(Integer value) {
            addCriterion("sku_id <=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIn(List<Integer> values) {
            addCriterion("sku_id in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotIn(List<Integer> values) {
            addCriterion("sku_id not in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdBetween(Integer value1, Integer value2) {
            addCriterion("sku_id between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sku_id not between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andOptionNameIsNull() {
            addCriterion("option_name is null");
            return (Criteria) this;
        }

        public Criteria andOptionNameIsNotNull() {
            addCriterion("option_name is not null");
            return (Criteria) this;
        }

        public Criteria andOptionNameEqualTo(String value) {
            addCriterion("option_name =", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameNotEqualTo(String value) {
            addCriterion("option_name <>", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameGreaterThan(String value) {
            addCriterion("option_name >", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameGreaterThanOrEqualTo(String value) {
            addCriterion("option_name >=", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameLessThan(String value) {
            addCriterion("option_name <", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameLessThanOrEqualTo(String value) {
            addCriterion("option_name <=", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameLike(String value) {
            addCriterion("option_name like", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameNotLike(String value) {
            addCriterion("option_name not like", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameIn(List<String> values) {
            addCriterion("option_name in", values, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameNotIn(List<String> values) {
            addCriterion("option_name not in", values, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameBetween(String value1, String value2) {
            addCriterion("option_name between", value1, value2, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameNotBetween(String value1, String value2) {
            addCriterion("option_name not between", value1, value2, "optionName");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Integer value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Integer value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Integer value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Integer value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Integer value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Integer> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Integer> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Integer value1, Integer value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table prod_sku_option
     *
     * @mbggenerated do_not_delete_during_merge Mon Aug 25 10:22:14 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table prod_sku_option
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
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