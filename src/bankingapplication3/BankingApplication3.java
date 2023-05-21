package bankingapplication3;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author sirapob
 */
public class BankingApplication3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Bank bank = new Bank("XYZ");
        int option = 0;
        String accountType;
        String accountName;
        String name;
        double balance,amount;
        Account account;
        while (option != 6){
            System.out.println("Main Menu");
            System.out.println("1.Display All Accounts");
            System.out.println("2.Open new Account");
            System.out.println("3.Close Existing Account");
            System.out.println("4.Deposit");
            System.out.println("5.Withdraw");
            System.out.println("6.Exit");
            System.out.println();

            System.out.print("Enter your choice:");
            option = scan.nextInt();
            scan.nextLine();
            System.out.println();

            switch(option){
                case 1:
                    bank.listAccounts();
                    break;
                case 2:
                    int accountNumber = generateAccountNumber();
                    System.out.print("Enter Account Name:");
                    accountName = scan.nextLine();
                    System.out.print("Enter Initial Balance:");
                    balance = scan.nextDouble();
                    scan.nextLine();
                    System.out.println("Enter Account(s-> Saving or c-> Current):");
                    accountType = scan.nextLine();

                    if(accountType.toLowerCase().equals("s")){
                        account = new SavingsAccount(accountNumber,accountName,balance);
                    }else if(accountType.toLowerCase().equals("c")){
                        account = new CurrentAccount(accountNumber,accountName,balance);
                }else {
                        System.out.println("Invalid Account Type");
                        break;
                    }
                    bank.openAccount(account);
                    break;
                case 3:
                    System.out.print("Enter Account number");
                    accountNumber = scan.nextInt();
                    System.out.println("Enter Account(s-> Saving or c-> Current):");
                    accountType = scan.nextLine();
                    if(accountType.toLowerCase().equals("s")){
                        account = bank.getAccount(accountNumber,"SavingAccount");
                        bank.closeAccount(account);
                    }else if(accountType.toLowerCase().equals("c")){
                        account = bank.getAccount(accountNumber,"CurrentAccount");
                        bank.closeAccount(account);
                    }else {
                        System.out.println("Invalid Account Type");
                        break;
                    }
                    System.out.println("Account is deleted");
                    break;
                case 4:
                    System.out.print("Enter Account Number");
                    accountNumber = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter Amount: ");
                    amount = scan.nextDouble();
                    scan.nextLine();
                    System.out.println("Enter Account(s-> Saving or c-> Current):");
                    accountType = scan.nextLine();
                    if(accountType.toLowerCase().equals("s")){
                        account = bank.getAccount(accountNumber,"SavingAccount");
                        bank.depositMoney(account,amount);
                    }else if(accountType.toLowerCase().equals("c")){
                        account = bank.getAccount(accountNumber,"CurrentAccount");
                        bank.depositMoney(account,amount);
                    }else {
                        System.out.println("Invalid Account Type");
                        break;
                    }
                    System.out.println("Balance : " + account.getBalance());
                    break;
                case 5:
                    System.out.print("Enter Account Number");
                    accountNumber = scan.nextInt();
//                    account = bank.(accountNumber);
                    System.out.print("Enter Amount: ");
                    amount = scan.nextDouble();
//                    bank.withDrawMoney(account, amount);
                    break;

            }
        }
    }
    public static int generateAccountNumber(){
        Random random = new Random();
        int accNumber = 10000 + random.nextInt(900000);
        return accNumber;
    }

}
