public class Customer {
    private final String name;
    private double balance = 2500;
    private final String accountType;
    private String currency = "R$";

    public Customer (String name, double initialBalance){
        this.name = name;
        this.balance = initialBalance;
        this.accountType = "Conta Corrente";
        this.currency = "R$";
    }

    private double formattedBalance(){
        return Math.round(this.balance * 100.0) / 100.0;
    }
    public double getBalance(){
        return this.formattedBalance();
    }

    public String getCurrency(){
        return this.currency;
    }

    public String getName(){
        return this.name;
    }

    public String getAccountType(){
        return this.accountType;
    }


    public double reduceBalance(double amount){
        this.balance -= amount;
        return this.formattedBalance();
    }

    public double addBalance(double amount){
        this.balance += amount;
        return this.formattedBalance();
    }

}