package br.com.moipstore.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="HolderDomain", description="Represents the cardholder data that will be received by the server in the Rest API")
public class HolderDomain {

    @ApiModelProperty(value = "Full name",dataType = "string", required = true)
    private String fullName;

    @ApiModelProperty(value = "Birth Date",dataType = "string", required = true, example = "1983-10-10")
    private String birthDate;

    @ApiModelProperty(value = "Phone area code",dataType = "string", required = true, example = "457")
    private String phoneAreaCode;

    @ApiModelProperty(value = "Phone number",dataType = "string", required = true, example = "4587-7841")
    private String phoneNumber;

    @ApiModelProperty(value = "Cardholder main document",dataType = "string", required = true, example = "222.222.222-94")
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HolderDomain{");
            sb.append("fullName='").append(fullName).append('\'');
            sb.append(", birthDate='").append(birthDate).append('\'');
            sb.append(", phoneAreaCode='").append(phoneAreaCode).append('\'');
            sb.append(", phoneNumber='").append(phoneNumber).append('\'');
            sb.append(", taxDocument='").append(taxDocument).append('\'');
            sb.append('}');
        return sb.toString();
    }
}
