package bankingapplication3;

import java.sql.*;

public class Bank {
    private String name;
    public Bank(String name){
        this.name = name;
    }
    public void listAccounts(){
        Connection con = BankConnection.connect();
        Statement statement = null;
        try {
            statement = con.createStatement();
            String sql = "select * from account";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                System.out.println(result.getString(1)+" "+result.getString(2)+" "
                        +result.getString(3)+result.getString(4));
            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void openAccount(Account account){
        Connection con = BankConnection.connect();
        String sql = "insert into account(accID,accName,accBalance,accType) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getAccountName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setString(4, account.getAccountType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeAccount(Account account){
        Connection con = BankConnection.connect();
        String sql = "delete  from account where accID = ? and accType = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,account.getAccountNumber());
            preparedStatement.setString(2,account.getAccountType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void depositMoney(Account account,double amount){
        account.deposit(amount);
        Connection con = BankConnection.connect();
        String sql = "update accounts set accBalance = ? where accID = ? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1,account.getBalance());
            preparedStatement.setInt(2,account.getAccountNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void withDrawMoney(Account account,double amount){
        account.withdraw(amount);
        Connection con = BankConnection.connect();
        String sql = "update accounts set accBalance = ? where accID = ? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1,account.getBalance());
            preparedStatement.setInt(2,account.getAccountNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getAccount(int number, String type) {
        Connection con = BankConnection.connect();
        String sql = "select * from accounts where accID = ? AND accType = ?";
        Account account;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            ResultSet result = preparedStatement.executeQuery();
            String accName = result.getString(2);
            double balance = result.getDouble(3);
            String accType = result.getString(4);
            if (accType.equals("Savings")) {
                account = new SavingsAccount(number, accName, balance);
            } else {
                account = new CurrentAccount(number, accName, balance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }
}
