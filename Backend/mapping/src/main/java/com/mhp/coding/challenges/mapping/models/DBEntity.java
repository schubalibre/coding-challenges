package com.mhp.coding.challenges.mapping.models;

import java.util.Date;

/**
 * @author Asdren Hoxha (MHP) - 17.09.18.
 */
public class DBEntity {

    private Long id;

    private Date lastModified;

    private String lastModifiedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
