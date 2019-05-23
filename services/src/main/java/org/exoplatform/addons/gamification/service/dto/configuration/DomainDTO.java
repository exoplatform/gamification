package org.exoplatform.addons.gamification.service.dto.configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.exoplatform.addons.gamification.entities.domain.configuration.DomainEntity;
import org.exoplatform.addons.gamification.entities.domain.configuration.RuleEntity;

import java.io.Serializable;
import java.util.Date;

public class DomainDTO implements Serializable {


    protected Long id;

    protected String title;

    protected String description;

    private String createdBy;

    private Date createdDate;

    private String lastModifiedBy;

    private Date lastModifiedDate;



    public DomainDTO() {

    }

    public DomainDTO(DomainEntity domainEntity) {
        this.id = domainEntity.getId();
        this.title = domainEntity.getTitle();
        this.description = domainEntity.getDescription();
        this.createdBy = domainEntity.getCreatedBy();
        this.createdDate = domainEntity.getCreatedDate();
        this.lastModifiedBy = domainEntity.getLastModifiedBy();
        this.lastModifiedDate = domainEntity.getLastModifiedDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


    @Override
    public String toString() {
        return "RuleDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                "}";
    }
}
