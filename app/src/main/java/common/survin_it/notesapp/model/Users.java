package common.survin_it.notesapp.model;

import com.google.firebase.Timestamp;

import java.util.Date;

public class Users {

    private String mobile;
    private String name;
    private String district_id;
    private String town_id;
    private String state_id;
    private Integer status;

    private String created_at;
    private String updated_at;
    private String verified;
    private String deleted;
    private String lang_id;
    private String otp;

    private String subscriptionDuration;
    private String subscriptionEndDate;
    private String subscriptionPlanType;
    private String subscriptionType;
    private String addminitstratedBy;
    private String userType;

    public Users() {
    }

    public Users(String mobile, String name, String district_id, String town_id, String state_id, Integer status, String created_at, String updated_at, String verified, String deleted, String lang_id, String otp, String subscriptionDuration,
                 String subscriptionEndDate, String subscriptionPlanType, String subscriptionType, String addminitstratedBy, String userType) {
        this.mobile = mobile;
        this.name = name;
        this.district_id = district_id;
        this.town_id = town_id;
        this.state_id = state_id;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.verified = verified;
        this.deleted = deleted;
        this.lang_id = lang_id;
        this.otp = otp;
        this.subscriptionDuration = subscriptionDuration;
        this.subscriptionEndDate = subscriptionEndDate;
        this.subscriptionPlanType = subscriptionPlanType;
        this.subscriptionType = subscriptionType;
        this.addminitstratedBy = addminitstratedBy;
        this.userType = userType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getTown_id() {
        return town_id;
    }

    public void setTown_id(String town_id) {
        this.town_id = town_id;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getLang_id() {
        return lang_id;
    }

    public void setLang_id(String lang_id) {
        this.lang_id = lang_id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getSubscriptionDuration() {
        return subscriptionDuration;
    }

    public void setSubscriptionDuration(String subscriptionDuration) {
        this.subscriptionDuration = subscriptionDuration;
    }

    public String getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(String subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public String getSubscriptionPlanType() {
        return subscriptionPlanType;
    }

    public void setSubscriptionPlanType(String subscriptionPlanType) {
        this.subscriptionPlanType = subscriptionPlanType;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getAddminitstratedBy() {
        return addminitstratedBy;
    }

    public void setAddminitstratedBy(String addminitstratedBy) {
        this.addminitstratedBy = addminitstratedBy;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
