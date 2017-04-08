package br.com.moipstore.model.request;


public class HolderDomain {

    private String fullName;
    private String birthDate;
    private String phoneAreaCode;
    private String phoneNumber;
    private String taxDocument;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public void setPhoneAreaCode(String phoneAreaCode) {
        this.phoneAreaCode = phoneAreaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTaxDocument() {
        return taxDocument;
    }

    public void setTaxDocument(String taxDocument) {
        this.taxDocument = taxDocument;
    }
}
