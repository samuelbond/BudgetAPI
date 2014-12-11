package uk.co.platitech;
// Generated 26-Nov-2014 12:12:14 by Hibernate Tools 3.6.0


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
     private Set<BudgetsEntity> budgetses = new HashSet<BudgetsEntity>(0);
     private Set<BankAccountEntity> bankAccounts = new HashSet<BankAccountEntity>(0);

    public UsersEntity() {
    }

	
    public UsersEntity(String userId) {
        this.userId = userId;
    }
    public UsersEntity(String userId, String email, String fullname, String password, Set<BudgetsEntity> budgetses, Set<BankAccountEntity> bankAccounts) {
       this.userId = userId;
       this.email = email;
       this.fullname = fullname;
       this.password = password;
       this.budgetses = budgetses;
       this.bankAccounts = bankAccounts;
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
    public Set<BudgetsEntity> getBudgetses() {
        return this.budgetses;
    }
    
    public void setBudgetses(Set<BudgetsEntity> budgetses) {
        this.budgetses = budgetses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
    public Set<BankAccountEntity> getBankAccounts() {
        return this.bankAccounts;
    }
    
    public void setBankAccounts(Set<BankAccountEntity> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }




}

