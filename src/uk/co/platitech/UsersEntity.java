package uk.co.platitech;
// Generated Nov 9, 2014 2:37:52 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name="users"
    ,catalog="budget_app"
    , uniqueConstraints = @UniqueConstraint(columnNames="email") 
)
public class UsersEntity implements java.io.Serializable {


     private String userId;
     private String email;
     private String fullname;
     private String password;
     private Set<BankAccountEntity> bankAccountEntities = new HashSet<BankAccountEntity>(0);

    public UsersEntity() {
    }

	
    public UsersEntity(String userId) {
        this.userId = userId;
    }
    public UsersEntity(String userId, String email, String fullname, String password, Set<BankAccountEntity> bankAccountEntities) {
       this.userId = userId;
       this.email = email;
       this.fullname = fullname;
       this.password = password;
       this.bankAccountEntities = bankAccountEntities;
    }
   
     @Id 

    
    @Column(name="user_id", unique=true, nullable=false)
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    @Column(name="email", unique=true)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="fullname")
    public String getFullname() {
        return this.fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    
    @Column(name="password")
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
    public Set<BankAccountEntity> getBankAccountEntities() {
        return this.bankAccountEntities;
    }
    
    public void setBankAccountEntities(Set<BankAccountEntity> bankAccountEntities) {
        this.bankAccountEntities = bankAccountEntities;
    }




}


