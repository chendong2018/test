package com.jianwu.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZipTextureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public ZipTextureExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setStartRow(Integer startRow) {
        this.startRow=startRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setFields(String fields) {
        this.fields=fields;
    }

    public String getFields() {
        return fields;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTextureOrderIsNull() {
            addCriterion("texture_order is null");
            return (Criteria) this;
        }

        public Criteria andTextureOrderIsNotNull() {
            addCriterion("texture_order is not null");
            return (Criteria) this;
        }

        public Criteria andTextureOrderEqualTo(String value) {
            addCriterion("texture_order =", value, "textureOrder");
            return (Criteria) this;
        }

        public Criteria andTextureOrderNotEqualTo(String value) {
            addCriterion("texture_order <>", value, "textureOrder");
            return (Criteria) this;
        }

        public Criteria andTextureOrderGreaterThan(String value) {
            addCriterion("texture_order >", value, "textureOrder");
            return (Criteria) this;
        }

        public Criteria andTextureOrderGreaterThanOrEqualTo(String value) {
            addCriterion("texture_order >=", value, "textureOrder");
            return (Criteria) this;
        }

        public Criteria andTextureOrderLessThan(String value) {
            addCriterion("texture_order <", value, "textureOrder");
            return (Criteria) this;
        }

        public Criteria andTextureOrderLessThanOrEqualTo(String value) {
            addCriterion("texture_order <=", value, "textureOrder");
            return (Criteria) this;
        }

        public Criteria andTextureOrderLike(String value) {
            addCriterion("texture_order like", value, "textureOrder");
            return (Criteria) this;
        }

        public Criteria andTextureOrderNotLike(String value) {
            addCriterion("texture_order not like", value, "textureOrder");
            return (Criteria) this;
        }

        public Criteria andTextureOrderIn(List<String> values) {
            addCriterion("texture_order in", values, "textureOrder");
            return (Criteria) this;
        }

        public Criteria andTextureOrderNotIn(List<String> values) {
            addCriterion("texture_order not in", values, "textureOrder");
            return (Criteria) this;
        }

        public Criteria andTextureOrderBetween(String value1, String value2) {
            addCriterion("texture_order between", value1, value2, "textureOrder");
            return (Criteria) this;
        }

        public Criteria andTextureOrderNotBetween(String value1, String value2) {
            addCriterion("texture_order not between", value1, value2, "textureOrder");
            return (Criteria) this;
        }

        public Criteria andTextureNameIsNull() {
            addCriterion("texture_name is null");
            return (Criteria) this;
        }

        public Criteria andTextureNameIsNotNull() {
            addCriterion("texture_name is not null");
            return (Criteria) this;
        }

        public Criteria andTextureNameEqualTo(String value) {
            addCriterion("texture_name =", value, "textureName");
            return (Criteria) this;
        }

        public Criteria andTextureNameNotEqualTo(String value) {
            addCriterion("texture_name <>", value, "textureName");
            return (Criteria) this;
        }

        public Criteria andTextureNameGreaterThan(String value) {
            addCriterion("texture_name >", value, "textureName");
            return (Criteria) this;
        }

        public Criteria andTextureNameGreaterThanOrEqualTo(String value) {
            addCriterion("texture_name >=", value, "textureName");
            return (Criteria) this;
        }

        public Criteria andTextureNameLessThan(String value) {
            addCriterion("texture_name <", value, "textureName");
            return (Criteria) this;
        }

        public Criteria andTextureNameLessThanOrEqualTo(String value) {
            addCriterion("texture_name <=", value, "textureName");
            return (Criteria) this;
        }

        public Criteria andTextureNameLike(String value) {
            addCriterion("texture_name like", value, "textureName");
            return (Criteria) this;
        }

        public Criteria andTextureNameNotLike(String value) {
            addCriterion("texture_name not like", value, "textureName");
            return (Criteria) this;
        }

        public Criteria andTextureNameIn(List<String> values) {
            addCriterion("texture_name in", values, "textureName");
            return (Criteria) this;
        }

        public Criteria andTextureNameNotIn(List<String> values) {
            addCriterion("texture_name not in", values, "textureName");
            return (Criteria) this;
        }

        public Criteria andTextureNameBetween(String value1, String value2) {
            addCriterion("texture_name between", value1, value2, "textureName");
            return (Criteria) this;
        }

        public Criteria andTextureNameNotBetween(String value1, String value2) {
            addCriterion("texture_name not between", value1, value2, "textureName");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureIsNull() {
            addCriterion("back_lucency_picture is null");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureIsNotNull() {
            addCriterion("back_lucency_picture is not null");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureEqualTo(String value) {
            addCriterion("back_lucency_picture =", value, "backLucencyPicture");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureNotEqualTo(String value) {
            addCriterion("back_lucency_picture <>", value, "backLucencyPicture");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureGreaterThan(String value) {
            addCriterion("back_lucency_picture >", value, "backLucencyPicture");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureGreaterThanOrEqualTo(String value) {
            addCriterion("back_lucency_picture >=", value, "backLucencyPicture");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureLessThan(String value) {
            addCriterion("back_lucency_picture <", value, "backLucencyPicture");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureLessThanOrEqualTo(String value) {
            addCriterion("back_lucency_picture <=", value, "backLucencyPicture");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureLike(String value) {
            addCriterion("back_lucency_picture like", value, "backLucencyPicture");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureNotLike(String value) {
            addCriterion("back_lucency_picture not like", value, "backLucencyPicture");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureIn(List<String> values) {
            addCriterion("back_lucency_picture in", values, "backLucencyPicture");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureNotIn(List<String> values) {
            addCriterion("back_lucency_picture not in", values, "backLucencyPicture");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureBetween(String value1, String value2) {
            addCriterion("back_lucency_picture between", value1, value2, "backLucencyPicture");
            return (Criteria) this;
        }

        public Criteria andBackLucencyPictureNotBetween(String value1, String value2) {
            addCriterion("back_lucency_picture not between", value1, value2, "backLucencyPicture");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureIsNull() {
            addCriterion("lucency_picture is null");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureIsNotNull() {
            addCriterion("lucency_picture is not null");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureEqualTo(String value) {
            addCriterion("lucency_picture =", value, "lucencyPicture");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureNotEqualTo(String value) {
            addCriterion("lucency_picture <>", value, "lucencyPicture");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureGreaterThan(String value) {
            addCriterion("lucency_picture >", value, "lucencyPicture");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureGreaterThanOrEqualTo(String value) {
            addCriterion("lucency_picture >=", value, "lucencyPicture");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureLessThan(String value) {
            addCriterion("lucency_picture <", value, "lucencyPicture");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureLessThanOrEqualTo(String value) {
            addCriterion("lucency_picture <=", value, "lucencyPicture");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureLike(String value) {
            addCriterion("lucency_picture like", value, "lucencyPicture");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureNotLike(String value) {
            addCriterion("lucency_picture not like", value, "lucencyPicture");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureIn(List<String> values) {
            addCriterion("lucency_picture in", values, "lucencyPicture");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureNotIn(List<String> values) {
            addCriterion("lucency_picture not in", values, "lucencyPicture");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureBetween(String value1, String value2) {
            addCriterion("lucency_picture between", value1, value2, "lucencyPicture");
            return (Criteria) this;
        }

        public Criteria andLucencyPictureNotBetween(String value1, String value2) {
            addCriterion("lucency_picture not between", value1, value2, "lucencyPicture");
            return (Criteria) this;
        }

        public Criteria andFrontPictureIsNull() {
            addCriterion("front_picture is null");
            return (Criteria) this;
        }

        public Criteria andFrontPictureIsNotNull() {
            addCriterion("front_picture is not null");
            return (Criteria) this;
        }

        public Criteria andFrontPictureEqualTo(String value) {
            addCriterion("front_picture =", value, "frontPicture");
            return (Criteria) this;
        }

        public Criteria andFrontPictureNotEqualTo(String value) {
            addCriterion("front_picture <>", value, "frontPicture");
            return (Criteria) this;
        }

        public Criteria andFrontPictureGreaterThan(String value) {
            addCriterion("front_picture >", value, "frontPicture");
            return (Criteria) this;
        }

        public Criteria andFrontPictureGreaterThanOrEqualTo(String value) {
            addCriterion("front_picture >=", value, "frontPicture");
            return (Criteria) this;
        }

        public Criteria andFrontPictureLessThan(String value) {
            addCriterion("front_picture <", value, "frontPicture");
            return (Criteria) this;
        }

        public Criteria andFrontPictureLessThanOrEqualTo(String value) {
            addCriterion("front_picture <=", value, "frontPicture");
            return (Criteria) this;
        }

        public Criteria andFrontPictureLike(String value) {
            addCriterion("front_picture like", value, "frontPicture");
            return (Criteria) this;
        }

        public Criteria andFrontPictureNotLike(String value) {
            addCriterion("front_picture not like", value, "frontPicture");
            return (Criteria) this;
        }

        public Criteria andFrontPictureIn(List<String> values) {
            addCriterion("front_picture in", values, "frontPicture");
            return (Criteria) this;
        }

        public Criteria andFrontPictureNotIn(List<String> values) {
            addCriterion("front_picture not in", values, "frontPicture");
            return (Criteria) this;
        }

        public Criteria andFrontPictureBetween(String value1, String value2) {
            addCriterion("front_picture between", value1, value2, "frontPicture");
            return (Criteria) this;
        }

        public Criteria andFrontPictureNotBetween(String value1, String value2) {
            addCriterion("front_picture not between", value1, value2, "frontPicture");
            return (Criteria) this;
        }

        public Criteria andBackPictureIsNull() {
            addCriterion("back_picture is null");
            return (Criteria) this;
        }

        public Criteria andBackPictureIsNotNull() {
            addCriterion("back_picture is not null");
            return (Criteria) this;
        }

        public Criteria andBackPictureEqualTo(String value) {
            addCriterion("back_picture =", value, "backPicture");
            return (Criteria) this;
        }

        public Criteria andBackPictureNotEqualTo(String value) {
            addCriterion("back_picture <>", value, "backPicture");
            return (Criteria) this;
        }

        public Criteria andBackPictureGreaterThan(String value) {
            addCriterion("back_picture >", value, "backPicture");
            return (Criteria) this;
        }

        public Criteria andBackPictureGreaterThanOrEqualTo(String value) {
            addCriterion("back_picture >=", value, "backPicture");
            return (Criteria) this;
        }

        public Criteria andBackPictureLessThan(String value) {
            addCriterion("back_picture <", value, "backPicture");
            return (Criteria) this;
        }

        public Criteria andBackPictureLessThanOrEqualTo(String value) {
            addCriterion("back_picture <=", value, "backPicture");
            return (Criteria) this;
        }

        public Criteria andBackPictureLike(String value) {
            addCriterion("back_picture like", value, "backPicture");
            return (Criteria) this;
        }

        public Criteria andBackPictureNotLike(String value) {
            addCriterion("back_picture not like", value, "backPicture");
            return (Criteria) this;
        }

        public Criteria andBackPictureIn(List<String> values) {
            addCriterion("back_picture in", values, "backPicture");
            return (Criteria) this;
        }

        public Criteria andBackPictureNotIn(List<String> values) {
            addCriterion("back_picture not in", values, "backPicture");
            return (Criteria) this;
        }

        public Criteria andBackPictureBetween(String value1, String value2) {
            addCriterion("back_picture between", value1, value2, "backPicture");
            return (Criteria) this;
        }

        public Criteria andBackPictureNotBetween(String value1, String value2) {
            addCriterion("back_picture not between", value1, value2, "backPicture");
            return (Criteria) this;
        }

        public Criteria andSidePictureIsNull() {
            addCriterion("side_picture is null");
            return (Criteria) this;
        }

        public Criteria andSidePictureIsNotNull() {
            addCriterion("side_picture is not null");
            return (Criteria) this;
        }

        public Criteria andSidePictureEqualTo(String value) {
            addCriterion("side_picture =", value, "sidePicture");
            return (Criteria) this;
        }

        public Criteria andSidePictureNotEqualTo(String value) {
            addCriterion("side_picture <>", value, "sidePicture");
            return (Criteria) this;
        }

        public Criteria andSidePictureGreaterThan(String value) {
            addCriterion("side_picture >", value, "sidePicture");
            return (Criteria) this;
        }

        public Criteria andSidePictureGreaterThanOrEqualTo(String value) {
            addCriterion("side_picture >=", value, "sidePicture");
            return (Criteria) this;
        }

        public Criteria andSidePictureLessThan(String value) {
            addCriterion("side_picture <", value, "sidePicture");
            return (Criteria) this;
        }

        public Criteria andSidePictureLessThanOrEqualTo(String value) {
            addCriterion("side_picture <=", value, "sidePicture");
            return (Criteria) this;
        }

        public Criteria andSidePictureLike(String value) {
            addCriterion("side_picture like", value, "sidePicture");
            return (Criteria) this;
        }

        public Criteria andSidePictureNotLike(String value) {
            addCriterion("side_picture not like", value, "sidePicture");
            return (Criteria) this;
        }

        public Criteria andSidePictureIn(List<String> values) {
            addCriterion("side_picture in", values, "sidePicture");
            return (Criteria) this;
        }

        public Criteria andSidePictureNotIn(List<String> values) {
            addCriterion("side_picture not in", values, "sidePicture");
            return (Criteria) this;
        }

        public Criteria andSidePictureBetween(String value1, String value2) {
            addCriterion("side_picture between", value1, value2, "sidePicture");
            return (Criteria) this;
        }

        public Criteria andSidePictureNotBetween(String value1, String value2) {
            addCriterion("side_picture not between", value1, value2, "sidePicture");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureIsNull() {
            addCriterion("no_side_picture is null");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureIsNotNull() {
            addCriterion("no_side_picture is not null");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureEqualTo(String value) {
            addCriterion("no_side_picture =", value, "noSidePicture");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureNotEqualTo(String value) {
            addCriterion("no_side_picture <>", value, "noSidePicture");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureGreaterThan(String value) {
            addCriterion("no_side_picture >", value, "noSidePicture");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureGreaterThanOrEqualTo(String value) {
            addCriterion("no_side_picture >=", value, "noSidePicture");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureLessThan(String value) {
            addCriterion("no_side_picture <", value, "noSidePicture");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureLessThanOrEqualTo(String value) {
            addCriterion("no_side_picture <=", value, "noSidePicture");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureLike(String value) {
            addCriterion("no_side_picture like", value, "noSidePicture");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureNotLike(String value) {
            addCriterion("no_side_picture not like", value, "noSidePicture");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureIn(List<String> values) {
            addCriterion("no_side_picture in", values, "noSidePicture");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureNotIn(List<String> values) {
            addCriterion("no_side_picture not in", values, "noSidePicture");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureBetween(String value1, String value2) {
            addCriterion("no_side_picture between", value1, value2, "noSidePicture");
            return (Criteria) this;
        }

        public Criteria andNoSidePictureNotBetween(String value1, String value2) {
            addCriterion("no_side_picture not between", value1, value2, "noSidePicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureIsNull() {
            addCriterion("top_picture is null");
            return (Criteria) this;
        }

        public Criteria andTopPictureIsNotNull() {
            addCriterion("top_picture is not null");
            return (Criteria) this;
        }

        public Criteria andTopPictureEqualTo(String value) {
            addCriterion("top_picture =", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureNotEqualTo(String value) {
            addCriterion("top_picture <>", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureGreaterThan(String value) {
            addCriterion("top_picture >", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureGreaterThanOrEqualTo(String value) {
            addCriterion("top_picture >=", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureLessThan(String value) {
            addCriterion("top_picture <", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureLessThanOrEqualTo(String value) {
            addCriterion("top_picture <=", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureLike(String value) {
            addCriterion("top_picture like", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureNotLike(String value) {
            addCriterion("top_picture not like", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureIn(List<String> values) {
            addCriterion("top_picture in", values, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureNotIn(List<String> values) {
            addCriterion("top_picture not in", values, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureBetween(String value1, String value2) {
            addCriterion("top_picture between", value1, value2, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureNotBetween(String value1, String value2) {
            addCriterion("top_picture not between", value1, value2, "topPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureIsNull() {
            addCriterion("small_picture is null");
            return (Criteria) this;
        }

        public Criteria andSmallPictureIsNotNull() {
            addCriterion("small_picture is not null");
            return (Criteria) this;
        }

        public Criteria andSmallPictureEqualTo(String value) {
            addCriterion("small_picture =", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureNotEqualTo(String value) {
            addCriterion("small_picture <>", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureGreaterThan(String value) {
            addCriterion("small_picture >", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureGreaterThanOrEqualTo(String value) {
            addCriterion("small_picture >=", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureLessThan(String value) {
            addCriterion("small_picture <", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureLessThanOrEqualTo(String value) {
            addCriterion("small_picture <=", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureLike(String value) {
            addCriterion("small_picture like", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureNotLike(String value) {
            addCriterion("small_picture not like", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureIn(List<String> values) {
            addCriterion("small_picture in", values, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureNotIn(List<String> values) {
            addCriterion("small_picture not in", values, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureBetween(String value1, String value2) {
            addCriterion("small_picture between", value1, value2, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureNotBetween(String value1, String value2) {
            addCriterion("small_picture not between", value1, value2, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Double value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Double value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Double value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Double value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Double value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Double> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Double> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Double value1, Double value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Double value1, Double value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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