package com.huatuo.clinics.cms.db.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsOutpVisitExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    public CsOutpVisitExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
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
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
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

        public Criteria andVistiIdIsNull() {
            addCriterion("visti_id is null");
            return (Criteria) this;
        }

        public Criteria andVistiIdIsNotNull() {
            addCriterion("visti_id is not null");
            return (Criteria) this;
        }

        public Criteria andVistiIdEqualTo(String value) {
            addCriterion("visti_id =", value, "vistiId");
            return (Criteria) this;
        }

        public Criteria andVistiIdNotEqualTo(String value) {
            addCriterion("visti_id <>", value, "vistiId");
            return (Criteria) this;
        }

        public Criteria andVistiIdGreaterThan(String value) {
            addCriterion("visti_id >", value, "vistiId");
            return (Criteria) this;
        }

        public Criteria andVistiIdGreaterThanOrEqualTo(String value) {
            addCriterion("visti_id >=", value, "vistiId");
            return (Criteria) this;
        }

        public Criteria andVistiIdLessThan(String value) {
            addCriterion("visti_id <", value, "vistiId");
            return (Criteria) this;
        }

        public Criteria andVistiIdLessThanOrEqualTo(String value) {
            addCriterion("visti_id <=", value, "vistiId");
            return (Criteria) this;
        }

        public Criteria andVistiIdLike(String value) {
            addCriterion("visti_id like", value, "vistiId");
            return (Criteria) this;
        }

        public Criteria andVistiIdNotLike(String value) {
            addCriterion("visti_id not like", value, "vistiId");
            return (Criteria) this;
        }

        public Criteria andVistiIdIn(List<String> values) {
            addCriterion("visti_id in", values, "vistiId");
            return (Criteria) this;
        }

        public Criteria andVistiIdNotIn(List<String> values) {
            addCriterion("visti_id not in", values, "vistiId");
            return (Criteria) this;
        }

        public Criteria andVistiIdBetween(String value1, String value2) {
            addCriterion("visti_id between", value1, value2, "vistiId");
            return (Criteria) this;
        }

        public Criteria andVistiIdNotBetween(String value1, String value2) {
            addCriterion("visti_id not between", value1, value2, "vistiId");
            return (Criteria) this;
        }

        public Criteria andPatientIdIsNull() {
            addCriterion("patient_id is null");
            return (Criteria) this;
        }

        public Criteria andPatientIdIsNotNull() {
            addCriterion("patient_id is not null");
            return (Criteria) this;
        }

        public Criteria andPatientIdEqualTo(String value) {
            addCriterion("patient_id =", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotEqualTo(String value) {
            addCriterion("patient_id <>", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdGreaterThan(String value) {
            addCriterion("patient_id >", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdGreaterThanOrEqualTo(String value) {
            addCriterion("patient_id >=", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLessThan(String value) {
            addCriterion("patient_id <", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLessThanOrEqualTo(String value) {
            addCriterion("patient_id <=", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLike(String value) {
            addCriterion("patient_id like", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotLike(String value) {
            addCriterion("patient_id not like", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdIn(List<String> values) {
            addCriterion("patient_id in", values, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotIn(List<String> values) {
            addCriterion("patient_id not in", values, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdBetween(String value1, String value2) {
            addCriterion("patient_id between", value1, value2, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotBetween(String value1, String value2) {
            addCriterion("patient_id not between", value1, value2, "patientId");
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

        public Criteria andVistiNoIsNull() {
            addCriterion("visti_no is null");
            return (Criteria) this;
        }

        public Criteria andVistiNoIsNotNull() {
            addCriterion("visti_no is not null");
            return (Criteria) this;
        }

        public Criteria andVistiNoEqualTo(Integer value) {
            addCriterion("visti_no =", value, "vistiNo");
            return (Criteria) this;
        }

        public Criteria andVistiNoNotEqualTo(Integer value) {
            addCriterion("visti_no <>", value, "vistiNo");
            return (Criteria) this;
        }

        public Criteria andVistiNoGreaterThan(Integer value) {
            addCriterion("visti_no >", value, "vistiNo");
            return (Criteria) this;
        }

        public Criteria andVistiNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("visti_no >=", value, "vistiNo");
            return (Criteria) this;
        }

        public Criteria andVistiNoLessThan(Integer value) {
            addCriterion("visti_no <", value, "vistiNo");
            return (Criteria) this;
        }

        public Criteria andVistiNoLessThanOrEqualTo(Integer value) {
            addCriterion("visti_no <=", value, "vistiNo");
            return (Criteria) this;
        }

        public Criteria andVistiNoIn(List<Integer> values) {
            addCriterion("visti_no in", values, "vistiNo");
            return (Criteria) this;
        }

        public Criteria andVistiNoNotIn(List<Integer> values) {
            addCriterion("visti_no not in", values, "vistiNo");
            return (Criteria) this;
        }

        public Criteria andVistiNoBetween(Integer value1, Integer value2) {
            addCriterion("visti_no between", value1, value2, "vistiNo");
            return (Criteria) this;
        }

        public Criteria andVistiNoNotBetween(Integer value1, Integer value2) {
            addCriterion("visti_no not between", value1, value2, "vistiNo");
            return (Criteria) this;
        }

        public Criteria andVisitDateIsNull() {
            addCriterion("visit_date is null");
            return (Criteria) this;
        }

        public Criteria andVisitDateIsNotNull() {
            addCriterion("visit_date is not null");
            return (Criteria) this;
        }

        public Criteria andVisitDateEqualTo(Date value) {
            addCriterion("visit_date =", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateNotEqualTo(Date value) {
            addCriterion("visit_date <>", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateGreaterThan(Date value) {
            addCriterion("visit_date >", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateGreaterThanOrEqualTo(Date value) {
            addCriterion("visit_date >=", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateLessThan(Date value) {
            addCriterion("visit_date <", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateLessThanOrEqualTo(Date value) {
            addCriterion("visit_date <=", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateIn(List<Date> values) {
            addCriterion("visit_date in", values, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateNotIn(List<Date> values) {
            addCriterion("visit_date not in", values, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateBetween(Date value1, Date value2) {
            addCriterion("visit_date between", value1, value2, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateNotBetween(Date value1, Date value2) {
            addCriterion("visit_date not between", value1, value2, "visitDate");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdIsNull() {
            addCriterion("ord_dep_id is null");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdIsNotNull() {
            addCriterion("ord_dep_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdEqualTo(String value) {
            addCriterion("ord_dep_id =", value, "ordDepId");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdNotEqualTo(String value) {
            addCriterion("ord_dep_id <>", value, "ordDepId");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdGreaterThan(String value) {
            addCriterion("ord_dep_id >", value, "ordDepId");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdGreaterThanOrEqualTo(String value) {
            addCriterion("ord_dep_id >=", value, "ordDepId");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdLessThan(String value) {
            addCriterion("ord_dep_id <", value, "ordDepId");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdLessThanOrEqualTo(String value) {
            addCriterion("ord_dep_id <=", value, "ordDepId");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdLike(String value) {
            addCriterion("ord_dep_id like", value, "ordDepId");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdNotLike(String value) {
            addCriterion("ord_dep_id not like", value, "ordDepId");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdIn(List<String> values) {
            addCriterion("ord_dep_id in", values, "ordDepId");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdNotIn(List<String> values) {
            addCriterion("ord_dep_id not in", values, "ordDepId");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdBetween(String value1, String value2) {
            addCriterion("ord_dep_id between", value1, value2, "ordDepId");
            return (Criteria) this;
        }

        public Criteria andOrdDepIdNotBetween(String value1, String value2) {
            addCriterion("ord_dep_id not between", value1, value2, "ordDepId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIsNull() {
            addCriterion("doctor_id is null");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIsNotNull() {
            addCriterion("doctor_id is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorIdEqualTo(String value) {
            addCriterion("doctor_id =", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotEqualTo(String value) {
            addCriterion("doctor_id <>", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdGreaterThan(String value) {
            addCriterion("doctor_id >", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdGreaterThanOrEqualTo(String value) {
            addCriterion("doctor_id >=", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdLessThan(String value) {
            addCriterion("doctor_id <", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdLessThanOrEqualTo(String value) {
            addCriterion("doctor_id <=", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdLike(String value) {
            addCriterion("doctor_id like", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotLike(String value) {
            addCriterion("doctor_id not like", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIn(List<String> values) {
            addCriterion("doctor_id in", values, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotIn(List<String> values) {
            addCriterion("doctor_id not in", values, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdBetween(String value1, String value2) {
            addCriterion("doctor_id between", value1, value2, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotBetween(String value1, String value2) {
            addCriterion("doctor_id not between", value1, value2, "doctorId");
            return (Criteria) this;
        }

        public Criteria andAgeIsNull() {
            addCriterion("age is null");
            return (Criteria) this;
        }

        public Criteria andAgeIsNotNull() {
            addCriterion("age is not null");
            return (Criteria) this;
        }

        public Criteria andAgeEqualTo(String value) {
            addCriterion("age =", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotEqualTo(String value) {
            addCriterion("age <>", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThan(String value) {
            addCriterion("age >", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThanOrEqualTo(String value) {
            addCriterion("age >=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThan(String value) {
            addCriterion("age <", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThanOrEqualTo(String value) {
            addCriterion("age <=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLike(String value) {
            addCriterion("age like", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotLike(String value) {
            addCriterion("age not like", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeIn(List<String> values) {
            addCriterion("age in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotIn(List<String> values) {
            addCriterion("age not in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeBetween(String value1, String value2) {
            addCriterion("age between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotBetween(String value1, String value2) {
            addCriterion("age not between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andVisitStatusIsNull() {
            addCriterion("visit_status is null");
            return (Criteria) this;
        }

        public Criteria andVisitStatusIsNotNull() {
            addCriterion("visit_status is not null");
            return (Criteria) this;
        }

        public Criteria andVisitStatusEqualTo(String value) {
            addCriterion("visit_status =", value, "visitStatus");
            return (Criteria) this;
        }

        public Criteria andVisitStatusNotEqualTo(String value) {
            addCriterion("visit_status <>", value, "visitStatus");
            return (Criteria) this;
        }

        public Criteria andVisitStatusGreaterThan(String value) {
            addCriterion("visit_status >", value, "visitStatus");
            return (Criteria) this;
        }

        public Criteria andVisitStatusGreaterThanOrEqualTo(String value) {
            addCriterion("visit_status >=", value, "visitStatus");
            return (Criteria) this;
        }

        public Criteria andVisitStatusLessThan(String value) {
            addCriterion("visit_status <", value, "visitStatus");
            return (Criteria) this;
        }

        public Criteria andVisitStatusLessThanOrEqualTo(String value) {
            addCriterion("visit_status <=", value, "visitStatus");
            return (Criteria) this;
        }

        public Criteria andVisitStatusLike(String value) {
            addCriterion("visit_status like", value, "visitStatus");
            return (Criteria) this;
        }

        public Criteria andVisitStatusNotLike(String value) {
            addCriterion("visit_status not like", value, "visitStatus");
            return (Criteria) this;
        }

        public Criteria andVisitStatusIn(List<String> values) {
            addCriterion("visit_status in", values, "visitStatus");
            return (Criteria) this;
        }

        public Criteria andVisitStatusNotIn(List<String> values) {
            addCriterion("visit_status not in", values, "visitStatus");
            return (Criteria) this;
        }

        public Criteria andVisitStatusBetween(String value1, String value2) {
            addCriterion("visit_status between", value1, value2, "visitStatus");
            return (Criteria) this;
        }

        public Criteria andVisitStatusNotBetween(String value1, String value2) {
            addCriterion("visit_status not between", value1, value2, "visitStatus");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIsNull() {
            addCriterion("visit_type is null");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIsNotNull() {
            addCriterion("visit_type is not null");
            return (Criteria) this;
        }

        public Criteria andVisitTypeEqualTo(String value) {
            addCriterion("visit_type =", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotEqualTo(String value) {
            addCriterion("visit_type <>", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeGreaterThan(String value) {
            addCriterion("visit_type >", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeGreaterThanOrEqualTo(String value) {
            addCriterion("visit_type >=", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeLessThan(String value) {
            addCriterion("visit_type <", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeLessThanOrEqualTo(String value) {
            addCriterion("visit_type <=", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeLike(String value) {
            addCriterion("visit_type like", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotLike(String value) {
            addCriterion("visit_type not like", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIn(List<String> values) {
            addCriterion("visit_type in", values, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotIn(List<String> values) {
            addCriterion("visit_type not in", values, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeBetween(String value1, String value2) {
            addCriterion("visit_type between", value1, value2, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotBetween(String value1, String value2) {
            addCriterion("visit_type not between", value1, value2, "visitType");
            return (Criteria) this;
        }

        public Criteria andLedgerSnIsNull() {
            addCriterion("ledger_sn is null");
            return (Criteria) this;
        }

        public Criteria andLedgerSnIsNotNull() {
            addCriterion("ledger_sn is not null");
            return (Criteria) this;
        }

        public Criteria andLedgerSnEqualTo(Integer value) {
            addCriterion("ledger_sn =", value, "ledgerSn");
            return (Criteria) this;
        }

        public Criteria andLedgerSnNotEqualTo(Integer value) {
            addCriterion("ledger_sn <>", value, "ledgerSn");
            return (Criteria) this;
        }

        public Criteria andLedgerSnGreaterThan(Integer value) {
            addCriterion("ledger_sn >", value, "ledgerSn");
            return (Criteria) this;
        }

        public Criteria andLedgerSnGreaterThanOrEqualTo(Integer value) {
            addCriterion("ledger_sn >=", value, "ledgerSn");
            return (Criteria) this;
        }

        public Criteria andLedgerSnLessThan(Integer value) {
            addCriterion("ledger_sn <", value, "ledgerSn");
            return (Criteria) this;
        }

        public Criteria andLedgerSnLessThanOrEqualTo(Integer value) {
            addCriterion("ledger_sn <=", value, "ledgerSn");
            return (Criteria) this;
        }

        public Criteria andLedgerSnIn(List<Integer> values) {
            addCriterion("ledger_sn in", values, "ledgerSn");
            return (Criteria) this;
        }

        public Criteria andLedgerSnNotIn(List<Integer> values) {
            addCriterion("ledger_sn not in", values, "ledgerSn");
            return (Criteria) this;
        }

        public Criteria andLedgerSnBetween(Integer value1, Integer value2) {
            addCriterion("ledger_sn between", value1, value2, "ledgerSn");
            return (Criteria) this;
        }

        public Criteria andLedgerSnNotBetween(Integer value1, Integer value2) {
            addCriterion("ledger_sn not between", value1, value2, "ledgerSn");
            return (Criteria) this;
        }

        public Criteria andChargeTypeIsNull() {
            addCriterion("charge_type is null");
            return (Criteria) this;
        }

        public Criteria andChargeTypeIsNotNull() {
            addCriterion("charge_type is not null");
            return (Criteria) this;
        }

        public Criteria andChargeTypeEqualTo(Integer value) {
            addCriterion("charge_type =", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeNotEqualTo(Integer value) {
            addCriterion("charge_type <>", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeGreaterThan(Integer value) {
            addCriterion("charge_type >", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("charge_type >=", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeLessThan(Integer value) {
            addCriterion("charge_type <", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("charge_type <=", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeIn(List<Integer> values) {
            addCriterion("charge_type in", values, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeNotIn(List<Integer> values) {
            addCriterion("charge_type not in", values, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeBetween(Integer value1, Integer value2) {
            addCriterion("charge_type between", value1, value2, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("charge_type not between", value1, value2, "chargeType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeIsNull() {
            addCriterion("response_type is null");
            return (Criteria) this;
        }

        public Criteria andResponseTypeIsNotNull() {
            addCriterion("response_type is not null");
            return (Criteria) this;
        }

        public Criteria andResponseTypeEqualTo(Integer value) {
            addCriterion("response_type =", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeNotEqualTo(Integer value) {
            addCriterion("response_type <>", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeGreaterThan(Integer value) {
            addCriterion("response_type >", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("response_type >=", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeLessThan(Integer value) {
            addCriterion("response_type <", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeLessThanOrEqualTo(Integer value) {
            addCriterion("response_type <=", value, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeIn(List<Integer> values) {
            addCriterion("response_type in", values, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeNotIn(List<Integer> values) {
            addCriterion("response_type not in", values, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeBetween(Integer value1, Integer value2) {
            addCriterion("response_type between", value1, value2, "responseType");
            return (Criteria) this;
        }

        public Criteria andResponseTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("response_type not between", value1, value2, "responseType");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table cs_outp_visit
     *
     * @mbggenerated do_not_delete_during_merge Fri Aug 12 20:30:53 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
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