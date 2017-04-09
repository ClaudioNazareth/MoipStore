package br.com.moipstore.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ErrorInfo", description="Represents the error ms that will be return from the API em case of errors ")
public class ErrorInfo {

    @ApiModelProperty(value = "API URL",dataType = "string", required = true)
    public final String url;

    @ApiModelProperty(value = "Exception message",dataType = "string", required = true)
    public final String ex;

    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorInfo{");
            sb.append("url='").append(url).append('\'');
            sb.append(", ex='").append(ex).append('\'');
            sb.append('}');
        return sb.toString();
    }
}
