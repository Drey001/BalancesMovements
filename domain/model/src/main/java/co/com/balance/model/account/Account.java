package co.com.balance.model.account;
import co.com.balance.model.balance.Balance;
import co.com.balance.model.movements.Movements;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Account {

    private Balance balance;
    private Movements movements;
}
