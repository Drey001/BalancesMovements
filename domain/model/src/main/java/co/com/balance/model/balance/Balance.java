package co.com.balance.model.balance;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Balance {

    private String available;
    private String availableOverdraftBalance;
    private String overdraftValue;
    private String availableOverdraftQuota;
    private String cash;
    private String unavailableClearing;
    private String receivable;
    private String blocked;
    private String unavailableStartDayClearingStartDay;
    private String cashStartDay;
    private String pockets;
    private String remittanceQuota;
    private String agreedRemittanceQuota;
    private String remittanceQuotaUsage;
    private String normalInterest;
    private String suspensionInterest;

}
