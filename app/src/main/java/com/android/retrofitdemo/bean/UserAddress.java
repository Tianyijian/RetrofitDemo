package com.android.retrofitdemo.bean;

public class UserAddress {

    private Integer addrId;

    private String name;

    private String gender;

    private String phone;

    private String addrInfo;

    private String addrHouse;

    private String label;

    private Boolean isDefault;

    private Integer userId;
    
    
    public UserAddress() {
        super();
    }

    public UserAddress(Integer addrId, String name, String gender, String phone, String addrInfo, String addrHouse,
            String label, Boolean isDefault, Integer userId) {
        super();
        this.addrId = addrId;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.addrInfo = addrInfo;
        this.addrHouse = addrHouse;
        this.label = label;
        this.isDefault = isDefault;
        this.userId = userId;
    }

    public Integer getAddrId() {
        return addrId;
    }

    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddrInfo() {
        return addrInfo;
    }

    public void setAddrInfo(String addrInfo) {
        this.addrInfo = addrInfo == null ? null : addrInfo.trim();
    }

    public String getAddrHouse() {
        return addrHouse;
    }

    public void setAddrHouse(String addrHouse) {
        this.addrHouse = addrHouse == null ? null : addrHouse.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    @Override
    public String toString() {
        return "UserAddress [addrId=" + addrId + ", name=" + name + ", gender=" + gender + ", phone=" + phone
                + ", addrInfo=" + addrInfo + ", addrHouse=" + addrHouse + ", label=" + label + ", isDefault="
                + isDefault + ", userId=" + userId + "]";
    }
}