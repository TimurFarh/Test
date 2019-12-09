package service;

import bankimitation.dao.TransactionDAO;
import bankimitation.model.Account;
import bankimitation.model.Client;
import bankimitation.model.Operations;
import bankimitation.model.Transaction;
import bankimitation.service.TransactionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
    @Mock
    private TransactionDAO transactionDAOMock;

    @Mock
    private Client client;

    @Mock
    private Account account;

    private Transaction transaction1 = new Transaction(Operations.Deposit, "Sberbank", "1", 5000, account, client);
    private Transaction transaction2 = new Transaction(Operations.Withdraw, "1", "Sberbank", 5000, account, client);

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void getAllByClientTest() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        Date after = Date.valueOf(LocalDate.of(2019, 10, 28));
        Date before = Date.valueOf(LocalDate.of(2019, 11, 30));
        transaction1.setDate(Date.valueOf(LocalDate.of(2019, 10, 29)));
        transaction2.setDate(Date.valueOf(LocalDate.of(2019, 11, 27)));
        when(transactionDAOMock.getAllByClient(client.getId())).thenReturn(new ArrayList<>(Arrays.asList(transaction1, transaction2)));
        List<Transaction> testTransactions = transactionService.getAllByClient(client.getId(), after, before);
        Assert.assertEquals(transactions, testTransactions);
        Assert.assertEquals(transactions.get(0).getFrom(), testTransactions.get(0).getFrom());

        before = Date.valueOf(LocalDate.of(2019, 11, 26));
        testTransactions = transactionService.getAllByClient(client.getId(), after, before);
        Assert.assertNotEquals(transactions, testTransactions);
        Assert.assertEquals(transactions.get(0).getFrom(), testTransactions.get(0).getFrom());
        verify(transactionDAOMock, times(2)).getAllByClient(client.getId());
    }

    @Test
    public void getAllByAccount() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        when(transactionDAOMock.getAllByAccount(account.getId())).thenReturn(Arrays.asList(transaction1, transaction2));
        List<Transaction> testTransactions = transactionService.getAllByAccount(account.getId());
        Assert.assertEquals(transactions, testTransactions);
        verify(transactionDAOMock).getAllByAccount(account.getId());
    }
}
