public class W4D1part2 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Алексей", 12345, 10000);

        System.out.println("Владелец: " + account.getOwnerName());
        System.out.println("Номер: " + account.getAccountNumber());
        System.out.println("Баланс: " + account.getBalance());

        account.deposit(5000);
        System.out.println("После пополнения: " + account.getBalance());

        account.withdraw(3000);
        System.out.println("После снятия: " + account.getBalance());
    }
}
    class BankAccount {
        private String ownerName;
        private int accountNumber;
        private int balance;

        BankAccount(String ownerName, int accountNumber, int balance) {
            if (ownerName != null && !ownerName.isBlank()) {
                this.ownerName = ownerName;
            } else {
                throw new IllegalArgumentException("Имя аккаунта не может быть пустым");
            }
            if (accountNumber > 0) {
                this.accountNumber = accountNumber;
            } else {
                throw new IllegalArgumentException("Номер аккаунта не может быть нулевым или отрицательным");
            }
            if (balance < 0) {
                throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
            } else {
                this.balance = balance;
            }
        }

        public String getOwnerName() {
            return ownerName;
        }

        public int getAccountNumber() {
            return accountNumber;
        }

        public int getBalance() {
            return balance;
        }

        public void deposit(int amount) {
            if (amount > 0) {
                balance += amount;
            } else {
                throw new IllegalArgumentException("Нельзя пополнить баланс отрицательной суммой");
            }
        }
        public void withdraw(int amount) {
            if (balance < amount) {
                throw new IllegalArgumentException("На балансе недостаточно средств");
            }
            if (amount <= 0) {
                throw new IllegalArgumentException("Сумма снятия должна быть больше нуля");
            }
            balance -= amount;
        }

    }
