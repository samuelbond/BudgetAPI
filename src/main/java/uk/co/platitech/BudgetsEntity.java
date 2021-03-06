package uk.co.platitech;
// Generated 26-Nov-2014 12:12:14 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Budgets generated by hbm2java
 */
@Entity
@Table(name="budgets"
    ,catalog="budget_app"
)
public class BudgetsEntity implements java.io.Serializable {


     private Integer budgetId;
     private BankAccountEntity bankAccount;
     private UsersEntity users;
     private String budgetName;
     private String budgetDescription;
     private long budgetMaxAmount;
     private Set<AccountTransactionsEntity> accountTransactionses = new HashSet<AccountTransactionsEntity>(0);

    public BudgetsEntity() {
    }

	
    public BudgetsEntity(BankAccountEntity bankAccount, UsersEntity users, String budgetName, String budgetDescription, long budgetMaxAmount) {
        this.bankAccount = bankAccount;
        this.users = users;
        this.budgetName = budgetName;
        this.budgetDescription = budgetDescription;
        this.budgetMaxAmount = budgetMaxAmount;
    }
    public BudgetsEntity(BankAccountEntity bankAccount, UsersEntity users, String budgetName, String budgetDescription, long budgetMaxAmount, Set<AccountTransactionsEntity> accountTransactionses) {
       this.bankAccount = bankAccount;
       this.users = users;
       this.budgetName = budgetName;
       this.budgetDescription = budgetDescription;
       this.budgetMaxAmount = budgetMaxAmount;
       this.accountTransactionses = accountTransactionses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="budget_id", unique=true, nullable=false)
    public Integer getBudgetId() {
        return this.budgetId;
    }
    
    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bank_account", nullable=false)
    public BankAccountEntity getBankAccount() {
        return this.bankAccount;
    }
    
    public void setBankAccount(BankAccountEntity bankAccount) {
        this.bankAccount = bankAccount;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    public UsersEntity getUsers() {
        return this.users;
    }
    
    public void setUsers(UsersEntity users) {
        this.users = users;
    }

    
    @Column(name="budget_name", nullable=false, length=45)
    public String getBudgetName() {
        return this.budgetName;
    }
    
    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    
    @Column(name="budget_description", nullable=false)
    public String getBudgetDescription() {
        return this.budgetDescription;
    }
    
    public void setBudgetDescription(String budgetDescription) {
        this.budgetDescription = budgetDescription;
    }

    
    @Column(name="budget_max_amount", nullable=false)
    public long getBudgetMaxAmount() {
        return this.budgetMaxAmount;
    }
    
    public void setBudgetMaxAmount(long budgetMaxAmount) {
        this.budgetMaxAmount = budgetMaxAmount;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy= "budgetEnty")
    public Set<AccountTransactionsEntity> getAccountTransactionses() {
        return this.accountTransactionses;
    }
    
    public void setAccountTransactionses(Set<AccountTransactionsEntity> accountTransactionses) {
        this.accountTransactionses = accountTransactionses;
    }




}


