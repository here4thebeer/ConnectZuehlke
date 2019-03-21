package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@JsonIgnoreProperties
@Entity
public class ProjectDto {

    @JsonProperty("Code")
    @Id
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

    @JsonProperty("CustomerId")
    private Integer customerId;

    @JsonProperty("To")
    private LocalDateTime to;


    public ProjectDto() {
        // JPA constructor
    }

    public ProjectDto(String code, String currency, Integer salesState, String salesStateText, String title, Integer id, Integer customerId) {
        this.code = code;
        this.currency = currency;
        this.salesState = salesState;
        this.salesStateText = salesStateText;
        this.title = title;
        this.id = id;
        this.customerId = customerId;
    }

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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "code='" + code + '\'' +
                ", currency='" + currency + '\'' +
                ", salesState=" + salesState +
                ", salesStateText='" + salesStateText + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                ", customerId=" + customerId +
                '}';
    }
}
