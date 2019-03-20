package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class ProjectDto {

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Currency")
    private String currency;

    @JsonProperty("SalesState")
    private Integer salesState;

    @JsonProperty("SalesStateText")
    private String salesStateText;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Id")
    private Integer id;

    public Project toProject() {
        return new Project(title, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getSalesState() {
        return salesState;
    }

    public void setSalesState(Integer salesState) {
        this.salesState = salesState;
    }

    public String getSalesStateText() {
        return salesStateText;
    }

    public void setSalesStateText(String salesStateText) {
        this.salesStateText = salesStateText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
