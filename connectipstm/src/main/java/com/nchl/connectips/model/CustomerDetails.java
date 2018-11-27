package com.nchl.connectips.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nchl.connectips.configuration.CryptoConverter;

@Entity
@Table(name="customer_details_table")
public class CustomerDetails implements Serializable{

	private static final long serialVersionUID = -963138547220347406L;

	@Id
	@Column(name="cust_id", length=20)
	private String custId;
	
	@Column(name="cust_name", length=200)
	private String custName;
	
	@Column(name="virtual_private_address", length=50)
	private String virtualPrivateAddress;
	
	@JsonIgnore
	@Column(name="password", length=100)
	private String password;
	
	@Column(name="email_id", length=200)
	@Convert(converter=CryptoConverter.class)
	private String emailId;
	
	@Column(name="mobile_no", length=200)
	@Convert(converter=CryptoConverter.class)
	private String mobileNo;

	@Column (name="role_code")
	private int roleCode;
	
	@Column (name="account_non_expired")
	private int accountNonExpired;
	
	@Column (name="account_non_locked")
	private int accountNonLocked;
	
	@Column (name="credential_non_expired")
	private int credentialNonExpired;
	
	@Column (name="enabled")
	private int enabled;
	
	@Column (name="no_of_login_attempts")
	private Integer noOfLoginAttempts;
	
	@Column(name="pwd_lchg_date")
	@Temporal(TemporalType.DATE)
	private Date pwdLchgDate;
	
	@JsonIgnore
	@Column (name="secret_key", length=20)
	private String secretKey;
	
	@Column(name="is_first_login", length=1)
	private String isFirstLogin;
	
	@Column (name="mobile_verify_count")
	private int mobileVerifyCount;
	
	@Column (name="email_verify_count")
	private int emailVerifyCount;
	
	@Column (name="mpin", length=100)
	private String mpin;
	
	@Column(name="tpwd_lchg_date")
	@Temporal(TemporalType.DATE)
	private Date tpwdLchgDate;
	
	@Column (name="no_of_tpwd_attempts")
	private Integer noOfTpwdAttempts;
	
	@Column (name="tpwd_non_locked")
	private int tpwdNonLocked;
	
	@Column(name="login_status", length=100)
	private String loginStatus;
	
	@Transient
	private String repeatPassword;

	@Transient
	private String otp;
	
	/*
	 * User Related Variables
	 */
	
	@Column(name="rcre_user_id", length=20)
	private String rcreUserId;
	
	@Column(name="rcre_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date rcreTime;
	
	@Column(name="lchg_user_id", length=20)
	private String lchgUserId;
	
	@Column(name="lchg_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lchgTime;
	
	@Column(name="entity_cre_flg", length=1)
	private String entityCreFlg;
	
	@Column(name="entity_cre_user_id", length=20)
	private String entityCreUserId;
	
	@Column(name="entity_cre_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date entityCreTime;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getVirtualPrivateAddress() {
		return virtualPrivateAddress;
	}

	public void setVirtualPrivateAddress(String virtualPrivateAddress) {
		this.virtualPrivateAddress = virtualPrivateAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(int roleCode) {
		this.roleCode = roleCode;
	}

	public int getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(int accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public int getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(int accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public int getCredentialNonExpired() {
		return credentialNonExpired;
	}

	public void setCredentialNonExpired(int credentialNonExpired) {
		this.credentialNonExpired = credentialNonExpired;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Integer getNoOfLoginAttempts() {
		return noOfLoginAttempts;
	}

	public void setNoOfLoginAttempts(Integer noOfLoginAttempts) {
		this.noOfLoginAttempts = noOfLoginAttempts;
	}

	public Date getPwdLchgDate() {
		return pwdLchgDate;
	}

	public void setPwdLchgDate(Date pwdLchgDate) {
		this.pwdLchgDate = pwdLchgDate;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(String isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public int getMobileVerifyCount() {
		return mobileVerifyCount;
	}

	public void setMobileVerifyCount(int mobileVerifyCount) {
		this.mobileVerifyCount = mobileVerifyCount;
	}

	public int getEmailVerifyCount() {
		return emailVerifyCount;
	}

	public void setEmailVerifyCount(int emailVerifyCount) {
		this.emailVerifyCount = emailVerifyCount;
	}

	public String getMpin() {
		return mpin;
	}

	public void setMpin(String mpin) {
		this.mpin = mpin;
	}

	public Date getTpwdLchgDate() {
		return tpwdLchgDate;
	}

	public void setTpwdLchgDate(Date tpwdLchgDate) {
		this.tpwdLchgDate = tpwdLchgDate;
	}

	public Integer getNoOfTpwdAttempts() {
		return noOfTpwdAttempts;
	}

	public void setNoOfTpwdAttempts(Integer noOfTpwdAttempts) {
		this.noOfTpwdAttempts = noOfTpwdAttempts;
	}

	public int getTpwdNonLocked() {
		return tpwdNonLocked;
	}

	public void setTpwdNonLocked(int tpwdNonLocked) {
		this.tpwdNonLocked = tpwdNonLocked;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getRcreUserId() {
		return rcreUserId;
	}

	public void setRcreUserId(String rcreUserId) {
		this.rcreUserId = rcreUserId;
	}

	public Date getRcreTime() {
		return rcreTime;
	}

	public void setRcreTime(Date rcreTime) {
		this.rcreTime = rcreTime;
	}

	public String getLchgUserId() {
		return lchgUserId;
	}

	public void setLchgUserId(String lchgUserId) {
		this.lchgUserId = lchgUserId;
	}

	public Date getLchgTime() {
		return lchgTime;
	}

	public void setLchgTime(Date lchgTime) {
		this.lchgTime = lchgTime;
	}

	public String getEntityCreFlg() {
		return entityCreFlg;
	}

	public void setEntityCreFlg(String entityCreFlg) {
		this.entityCreFlg = entityCreFlg;
	}

	public String getEntityCreUserId() {
		return entityCreUserId;
	}

	public void setEntityCreUserId(String entityCreUserId) {
		this.entityCreUserId = entityCreUserId;
	}

	public Date getEntityCreTime() {
		return entityCreTime;
	}

	public void setEntityCreTime(Date entityCreTime) {
		this.entityCreTime = entityCreTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
