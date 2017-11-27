package cn.xyz.chaos.examples.demo.entity.criteria;

import java.util.ArrayList;
import java.util.List;

public class ProdSkuLinkCriteria {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    public ProdSkuLinkCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
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

        public Criteria andSkuLinkIdIsNull() {
            addCriterion("sku_link_id is null");
            return (Criteria) this;
        }

        public Criteria andSkuLinkIdIsNotNull() {
            addCriterion("sku_link_id is not null");
            return (Criteria) this;
        }

        public Criteria andSkuLinkIdEqualTo(Integer value) {
            addCriterion("sku_link_id =", value, "skuLinkId");
            return (Criteria) this;
        }

        public Criteria andSkuLinkIdNotEqualTo(Integer value) {
            addCriterion("sku_link_id <>", value, "skuLinkId");
            return (Criteria) this;
        }

        public Criteria andSkuLinkIdGreaterThan(Integer value) {
            addCriterion("sku_link_id >", value, "skuLinkId");
            return (Criteria) this;
        }

        public Criteria andSkuLinkIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sku_link_id >=", value, "skuLinkId");
            return (Criteria) this;
        }

        public Criteria andSkuLinkIdLessThan(Integer value) {
            addCriterion("sku_link_id <", value, "skuLinkId");
            return (Criteria) this;
        }

        public Criteria andSkuLinkIdLessThanOrEqualTo(Integer value) {
            addCriterion("sku_link_id <=", value, "skuLinkId");
            return (Criteria) this;
        }

        public Criteria andSkuLinkIdIn(List<Integer> values) {
            addCriterion("sku_link_id in", values, "skuLinkId");
            return (Criteria) this;
        }

        public Criteria andSkuLinkIdNotIn(List<Integer> values) {
            addCriterion("sku_link_id not in", values, "skuLinkId");
            return (Criteria) this;
        }

        public Criteria andSkuLinkIdBetween(Integer value1, Integer value2) {
            addCriterion("sku_link_id between", value1, value2, "skuLinkId");
            return (Criteria) this;
        }

        public Criteria andSkuLinkIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sku_link_id not between", value1, value2, "skuLinkId");
            return (Criteria) this;
        }

        public Criteria andProdIdIsNull() {
            addCriterion("prod_id is null");
            return (Criteria) this;
        }

        public Criteria andProdIdIsNotNull() {
            addCriterion("prod_id is not null");
            return (Criteria) this;
        }

        public Criteria andProdIdEqualTo(Integer value) {
            addCriterion("prod_id =", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotEqualTo(Integer value) {
            addCriterion("prod_id <>", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdGreaterThan(Integer value) {
            addCriterion("prod_id >", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("prod_id >=", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLessThan(Integer value) {
            addCriterion("prod_id <", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLessThanOrEqualTo(Integer value) {
            addCriterion("prod_id <=", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdIn(List<Integer> values) {
            addCriterion("prod_id in", values, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotIn(List<Integer> values) {
            addCriterion("prod_id not in", values, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdBetween(Integer value1, Integer value2) {
            addCriterion("prod_id between", value1, value2, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotBetween(Integer value1, Integer value2) {
            addCriterion("prod_id not between", value1, value2, "prodId");
            return (Criteria) this;
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
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table prod_sku_link
     *
     * @mbggenerated do_not_delete_during_merge Thu Aug 28 10:51:25 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table prod_sku_link
     *
     * @mbggenerated Thu Aug 28 10:51:25 CST 2014
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