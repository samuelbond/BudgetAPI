package uk.co.platitech;
// Generated 26-Nov-2014 12:12:14 by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Apps generated by hbm2java
 */
@Entity
@Table(name="apps"
    ,catalog="budget_app"
)
public class AppsEntity implements java.io.Serializable {


     private String appId;
     private String appKey;

    public AppsEntity() {
    }

	
    public AppsEntity(String appId) {
        this.appId = appId;
    }
    public AppsEntity(String appId, String appKey) {
       this.appId = appId;
       this.appKey = appKey;
    }
   
     @Id 

    
    @Column(name="appId", unique=true, nullable=false)
    public String getAppId() {
        return this.appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }

    
    @Column(name="app_key")
    public String getAppKey() {
        return this.appKey;
    }
    
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }




}


