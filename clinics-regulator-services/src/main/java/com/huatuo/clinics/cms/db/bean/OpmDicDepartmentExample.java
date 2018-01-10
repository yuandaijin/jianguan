package com.huatuo.clinics.cms.db.bean;

import java.util.ArrayList;
import java.util.List;

public class OpmDicDepartmentExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
     */
    public OpmDicDepartmentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
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
     * This method corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
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

        public Criteria andDepIdIsNull() {
            addCriterion("dep_id is null");
            return (Criteria) this;
        }

        public Criteria andDepIdIsNotNull() {
            addCriterion("dep_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepIdEqualTo(String value) {
            addCriterion("dep_id =", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdNotEqualTo(String value) {
            addCriterion("dep_id <>", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdGreaterThan(String value) {
            addCriterion("dep_id >", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdGreaterThanOrEqualTo(String value) {
            addCriterion("dep_id >=", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdLessThan(String value) {
            addCriterion("dep_id <", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdLessThanOrEqualTo(String value) {
            addCriterion("dep_id <=", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdLike(String value) {
            addCriterion("dep_id like", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdNotLike(String value) {
            addCriterion("dep_id not like", value, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdIn(List<String> values) {
            addCriterion("dep_id in", values, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdNotIn(List<String> values) {
            addCriterion("dep_id not in", values, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdBetween(String value1, String value2) {
            addCriterion("dep_id between", value1, value2, "depId");
            return (Criteria) this;
        }

        public Criteria andDepIdNotBetween(String value1, String value2) {
            addCriterion("dep_id not between", value1, value2, "depId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("org_id is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("org_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("org_id =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("org_id <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("org_id >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("org_id >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("org_id <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("org_id <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("org_id like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("org_id not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("org_id in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("org_id not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("org_id between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("org_id not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andDepNameIsNull() {
            addCriterion("dep_name is null");
            return (Criteria) this;
        }

        public Criteria andDepNameIsNotNull() {
            addCriterion("dep_name is not null");
            return (Criteria) this;
        }

        public Criteria andDepNameEqualTo(String value) {
            addCriterion("dep_name =", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameNotEqualTo(String value) {
            addCriterion("dep_name <>", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameGreaterThan(String value) {
            addCriterion("dep_name >", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameGreaterThanOrEqualTo(String value) {
            addCriterion("dep_name >=", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameLessThan(String value) {
            addCriterion("dep_name <", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameLessThanOrEqualTo(String value) {
            addCriterion("dep_name <=", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameLike(String value) {
            addCriterion("dep_name like", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameNotLike(String value) {
            addCriterion("dep_name not like", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameIn(List<String> values) {
            addCriterion("dep_name in", values, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameNotIn(List<String> values) {
            addCriterion("dep_name not in", values, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameBetween(String value1, String value2) {
            addCriterion("dep_name between", value1, value2, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameNotBetween(String value1, String value2) {
            addCriterion("dep_name not between", value1, value2, "depName");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNull() {
            addCriterion("sort_no is null");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNotNull() {
            addCriterion("sort_no is not null");
            return (Criteria) this;
        }

        public Criteria andSortNoEqualTo(int value) {
            addCriterion("sort_no =", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotEqualTo(int value) {
            addCriterion("sort_no <>", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThan(int value) {
            addCriterion("sort_no >", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThanOrEqualTo(int value) {
            addCriterion("sort_no >=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThan(int value) {
            addCriterion("sort_no <", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThanOrEqualTo(int value) {
            addCriterion("sort_no <=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoIn(List<Integer> values) {
            addCriterion("sort_no in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotIn(List<Integer> values) {
            addCriterion("sort_no not in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoBetween(int value1, int value2) {
            addCriterion("sort_no between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotBetween(int value1, int value2) {
            addCriterion("sort_no not between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeIsNull() {
            addCriterion("dep_pres_type is null");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeIsNotNull() {
            addCriterion("dep_pres_type is not null");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeEqualTo(String value) {
            addCriterion("dep_pres_type =", value, "depPresType");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeNotEqualTo(String value) {
            addCriterion("dep_pres_type <>", value, "depPresType");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeGreaterThan(String value) {
            addCriterion("dep_pres_type >", value, "depPresType");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeGreaterThanOrEqualTo(String value) {
            addCriterion("dep_pres_type >=", value, "depPresType");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeLessThan(String value) {
            addCriterion("dep_pres_type <", value, "depPresType");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeLessThanOrEqualTo(String value) {
            addCriterion("dep_pres_type <=", value, "depPresType");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeLike(String value) {
            addCriterion("dep_pres_type like", value, "depPresType");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeNotLike(String value) {
            addCriterion("dep_pres_type not like", value, "depPresType");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeIn(List<String> values) {
            addCriterion("dep_pres_type in", values, "depPresType");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeNotIn(List<String> values) {
            addCriterion("dep_pres_type not in", values, "depPresType");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeBetween(String value1, String value2) {
            addCriterion("dep_pres_type between", value1, value2, "depPresType");
            return (Criteria) this;
        }

        public Criteria andDepPresTypeNotBetween(String value1, String value2) {
            addCriterion("dep_pres_type not between", value1, value2, "depPresType");
            return (Criteria) this;
        }

        public Criteria andValidFlgIsNull() {
            addCriterion("valid_flg is null");
            return (Criteria) this;
        }

        public Criteria andValidFlgIsNotNull() {
            addCriterion("valid_flg is not null");
            return (Criteria) this;
        }

        public Criteria andValidFlgEqualTo(String value) {
            addCriterion("valid_flg =", value, "validFlg");
            return (Criteria) this;
        }

        public Criteria andValidFlgNotEqualTo(String value) {
            addCriterion("valid_flg <>", value, "validFlg");
            return (Criteria) this;
        }

        public Criteria andValidFlgGreaterThan(String value) {
            addCriterion("valid_flg >", value, "validFlg");
            return (Criteria) this;
        }

        public Criteria andValidFlgGreaterThanOrEqualTo(String value) {
            addCriterion("valid_flg >=", value, "validFlg");
            return (Criteria) this;
        }

        public Criteria andValidFlgLessThan(String value) {
            addCriterion("valid_flg <", value, "validFlg");
            return (Criteria) this;
        }

        public Criteria andValidFlgLessThanOrEqualTo(String value) {
            addCriterion("valid_flg <=", value, "validFlg");
            return (Criteria) this;
        }

        public Criteria andValidFlgLike(String value) {
            addCriterion("valid_flg like", value, "validFlg");
            return (Criteria) this;
        }

        public Criteria andValidFlgNotLike(String value) {
            addCriterion("valid_flg not like", value, "validFlg");
            return (Criteria) this;
        }

        public Criteria andValidFlgIn(List<String> values) {
            addCriterion("valid_flg in", values, "validFlg");
            return (Criteria) this;
        }

        public Criteria andValidFlgNotIn(List<String> values) {
            addCriterion("valid_flg not in", values, "validFlg");
            return (Criteria) this;
        }

        public Criteria andValidFlgBetween(String value1, String value2) {
            addCriterion("valid_flg between", value1, value2, "validFlg");
            return (Criteria) this;
        }

        public Criteria andValidFlgNotBetween(String value1, String value2) {
            addCriterion("valid_flg not between", value1, value2, "validFlg");
            return (Criteria) this;
        }

        public Criteria andDepTypeIsNull() {
            addCriterion("dep_type is null");
            return (Criteria) this;
        }

        public Criteria andDepTypeIsNotNull() {
            addCriterion("dep_type is not null");
            return (Criteria) this;
        }

        public Criteria andDepTypeEqualTo(String value) {
            addCriterion("dep_type =", value, "depType");
            return (Criteria) this;
        }

        public Criteria andDepTypeNotEqualTo(String value) {
            addCriterion("dep_type <>", value, "depType");
            return (Criteria) this;
        }

        public Criteria andDepTypeGreaterThan(String value) {
            addCriterion("dep_type >", value, "depType");
            return (Criteria) this;
        }

        public Criteria andDepTypeGreaterThanOrEqualTo(String value) {
            addCriterion("dep_type >=", value, "depType");
            return (Criteria) this;
        }

        public Criteria andDepTypeLessThan(String value) {
            addCriterion("dep_type <", value, "depType");
            return (Criteria) this;
        }

        public Criteria andDepTypeLessThanOrEqualTo(String value) {
            addCriterion("dep_type <=", value, "depType");
            return (Criteria) this;
        }

        public Criteria andDepTypeLike(String value) {
            addCriterion("dep_type like", value, "depType");
            return (Criteria) this;
        }

        public Criteria andDepTypeNotLike(String value) {
            addCriterion("dep_type not like", value, "depType");
            return (Criteria) this;
        }

        public Criteria andDepTypeIn(List<String> values) {
            addCriterion("dep_type in", values, "depType");
            return (Criteria) this;
        }

        public Criteria andDepTypeNotIn(List<String> values) {
            addCriterion("dep_type not in", values, "depType");
            return (Criteria) this;
        }

        public Criteria andDepTypeBetween(String value1, String value2) {
            addCriterion("dep_type between", value1, value2, "depType");
            return (Criteria) this;
        }

        public Criteria andDepTypeNotBetween(String value1, String value2) {
            addCriterion("dep_type not between", value1, value2, "depType");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table opm_dic_department
     *
     * @mbggenerated do_not_delete_during_merge Tue Mar 22 10:49:51 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table opm_dic_department
     *
     * @mbggenerated Tue Mar 22 10:49:51 CST 2016
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