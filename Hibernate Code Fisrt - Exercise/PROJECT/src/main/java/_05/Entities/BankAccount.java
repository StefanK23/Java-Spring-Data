package _05.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "bank_accounts")
public class BankAccount extends BillingDetail {

    @Column(name = "bank", length = 30)
    private String name;

    @Column(name = "swift", length = 30)
    private String swiftCode;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSwiftCode() {
        return this.swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
