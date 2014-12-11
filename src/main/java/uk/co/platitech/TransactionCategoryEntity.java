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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TransactionCategory generated by hbm2java
 */
@Entity
@Table(name="transaction_category"
    ,catalog="budget_app"
)
public class TransactionCategoryEntity implements java.io.Serializable {


     private Integer id;
     private String categoryName;
     private Set<AccountTransactionsEntity> accountTransactionses = new HashSet<AccountTransactionsEntity>(0);

    public TransactionCategoryEntity() {
    }

    public TransactionCategoryEntity(String categoryName, Set<AccountTransactionsEntity> accountTransactionses) {
       this.categoryName = categoryName;
       this.accountTransactionses = accountTransactionses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="category_name")
    public String getCategoryName() {
        return this.categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="transactionCategory")
    public Set<AccountTransactionsEntity> getAccountTransactionses() {
        return this.accountTransactionses;
    }
    
    public void setAccountTransactionses(Set<AccountTransactionsEntity> accountTransactionses) {
        this.accountTransactionses = accountTransactionses;
    }




}

