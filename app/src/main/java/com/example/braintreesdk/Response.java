package com.example.braintreesdk;

public class Response {
    private String payerEmail;
    private String paymentId;
    private String authorizationId;
    private String imageUrl;
    private String debugId;
    private String payeeId;
    private String payeeEmail;
    private String customField;
    private String payerId;
    private String payerFirstName;
    private String payerLastName;
    private String payerStatus;
    private String sellerProtectionStatus;
    private String captureId;
    private String refundId;
    private String transactionFeeAmount;
    private String transactionFeeCurrencyIsoCode;
    private String description;

    public Response () {

    }

    public Response(String payerEmail, String paymentId, String authorizationId, String imageUrl, String debugId, String payeeId, String payeeEmail, String customField, String payerId, String payerFirstName, String payerLastName, String payerStatus, String sellerProtectionStatus, String captureId, String refundId, String transactionFeeAmount, String transactionFeeCurrencyIsoCode, String description) {
        this.payerEmail = payerEmail;
        this.paymentId = paymentId;
        this.authorizationId = authorizationId;
        this.imageUrl = imageUrl;
        this.debugId = debugId;
        this.payeeId = payeeId;
        this.payeeEmail = payeeEmail;
        this.customField = customField;
        this.payerId = payerId;
        this.payerFirstName = payerFirstName;
        this.payerLastName = payerLastName;
        this.payerStatus = payerStatus;
        this.sellerProtectionStatus = sellerProtectionStatus;
        this.captureId = captureId;
        this.refundId = refundId;
        this.transactionFeeAmount = transactionFeeAmount;
        this.transactionFeeCurrencyIsoCode = transactionFeeCurrencyIsoCode;
        this.description = description;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAuthorizationId() {
        return authorizationId;
    }

    public void setAuthorizationId(String authorizationId) {
        this.authorizationId = authorizationId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDebugId() {
        return debugId;
    }

    public void setDebugId(String debugId) {
        this.debugId = debugId;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayeeEmail() {
        return payeeEmail;
    }

    public void setPayeeEmail(String payeeEmail) {
        this.payeeEmail = payeeEmail;
    }

    public String getCustomField() {
        return customField;
    }

    public void setCustomField(String customField) {
        this.customField = customField;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayerFirstName() {
        return payerFirstName;
    }

    public void setPayerFirstName(String payerFirstName) {
        this.payerFirstName = payerFirstName;
    }

    public String getPayerLastName() {
        return payerLastName;
    }

    public void setPayerLastName(String payerLastName) {
        this.payerLastName = payerLastName;
    }

    public String getPayerStatus() {
        return payerStatus;
    }

    public void setPayerStatus(String payerStatus) {
        this.payerStatus = payerStatus;
    }

    public String getSellerProtectionStatus() {
        return sellerProtectionStatus;
    }

    public void setSellerProtectionStatus(String sellerProtectionStatus) {
        this.sellerProtectionStatus = sellerProtectionStatus;
    }

    public String getCaptureId() {
        return captureId;
    }

    public void setCaptureId(String captureId) {
        this.captureId = captureId;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getTransactionFeeAmount() {
        return transactionFeeAmount;
    }

    public void setTransactionFeeAmount(String transactionFeeAmount) {
        this.transactionFeeAmount = transactionFeeAmount;
    }

    public String getTransactionFeeCurrencyIsoCode() {
        return transactionFeeCurrencyIsoCode;
    }

    public void setTransactionFeeCurrencyIsoCode(String transactionFeeCurrencyIsoCode) {
        this.transactionFeeCurrencyIsoCode = transactionFeeCurrencyIsoCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}


