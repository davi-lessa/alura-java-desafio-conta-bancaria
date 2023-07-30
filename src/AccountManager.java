import java.util.Scanner;

public class AccountManager {
    private final Customer customer;
    private final Scanner scanner;
    private boolean running = true;

    public AccountManager() {
        this.customer = new Customer("José", 2500.00);
        this.scanner = new Scanner(System.in);;

        System.out.printf("""
                *******************************
                Bem-vindo(a), %s!
                
                Visão geral da sua conta:
                Tipo de conta: %s
                Saldo inicial: %,.2f
                *******************************%n
                """.trim(), this.customer.getName(), this.customer.getAccountType(), this.customer.getBalance());

        while(this.running) {
            System.out.printf("""
                %n%nOperações disponíveis:
                
                1 - Consultar saldo
                2 - Receber valor
                3 - Transferir valor
                4 - Sair
                
                Digite a opção desejada:
                """);

            int insertedCommand = this.scanner.nextInt();

            switch (insertedCommand) {
                case 1 -> this.getBalance();
                case 2 -> {
                    // Recebendo transferência de outra conta
                    Double valueToReceive = null;
                    String from = "";

                    // Definindo de quem vai enviar
                    System.out.println("Digite o nome de quem você planeja receber esse valor. ( 0 para retornar ).");
                    while (from.isEmpty()) {
                        from = this.scanner.next();
                    }
                    if (from.equals("0")) break;

                    // Definindo o valor a ser recebido
                    System.out.println("Insira o valor que deseja receber. ( 0 para retornar; use vírgula ).");
                    while (valueToReceive == null || valueToReceive < 0) {
                        valueToReceive = this.scanner.nextDouble();
                    }
                    if (valueToReceive == 0) break;
                    this.receiveMoney(from, valueToReceive);
                }
                case 3 -> {
                    // Transferindo para outra conta
                    Double valueToSend = null;
                    String to = "";

                    // Definindo de quem vai receber
                    System.out.println("Digite o nome de irá receber o valor. ( 0 para retornar ).");
                    while (to.isEmpty()) {
                        to = this.scanner.next();
                    }
                    if (to.equals("0")) break;

                    // Definindo o valor a ser enviado
                    System.out.println("Insira o valor que deseja enviar. ( 0 para retornar; use vírgula ).");
                    while (valueToSend == null || valueToSend < 0) {
                        valueToSend = this.scanner.nextDouble();
                    }
                    if (valueToSend == 0) break;
                    this.sendMoney(to, valueToSend);
                }
                case 4 -> this.running = false;
                default -> System.out.printf("Opção inválida!%n");
            }
        }
    }

    public void getBalance(){
        double currentBalance = this.customer.getBalance();
        System.out.printf("O seu saldo atual é de: %,.2f", currentBalance);
    }

    public void sendMoney(String to, double amount){
        if(amount > this.customer.getBalance()) {
            System.out.printf("# Operação inválida! #%nVocê não possui saldo suficiente para realizar a transferência solicitada.");
            return;
        }
        System.out.printf("Transferindo %s%,.2f para %s...", this.customer.getCurrency(), amount, to);
        double newBalance = this.customer.reduceBalance(amount);
        System.out.printf("%n# Transferência concluída. #%nVocê transferiu %s%,.2f para %s%nNovo saldo: %,.2f", this.customer.getCurrency(), amount, to, newBalance);
    }

    public void receiveMoney(String from, double amount){
        System.out.printf("Solicitação encaminhada. Aguardando transferência de: %s ...", from);
        double newBalance = this.customer.addBalance(amount);
        System.out.printf("%n# Nova transferência recebida #%nVocê recebeu %s%,.2f de %s.%nNovo saldo: %,.2f", this.customer.getCurrency(), amount, from, newBalance);
    }

    public void terminateSession(){
        this.running = false;
    }
}