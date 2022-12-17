import java.util.Scanner;
class AtmDetails {
    int accno;
    String name;
    long balance;
    Scanner sc = new Scanner(System.in);
    //no need for constructor with all data because we take it with openAcc
    //and assign it with customers[i].openAcc
    public void openAcc(){
        //adds a new customer
        System.out.println("Enter name: ");
        name = sc.nextLine();
        System.out.println("Enter balance");
        balance = sc.nextLong();
        System.out.println();
    }

    public void showAccount(){
        //shows all the info of a customer
        System.out.println("Name: "+ name);
        System.out.println("Account number: " + accno);
        System.out.println("Balance: " + balance);
        System.out.println();
    }

    public void deposit(){
        //deposits an amount of money in the balance
        System.out.println("Enter the value you want to deposit: ");
        long depositValue = sc.nextLong();
        balance+=depositValue;
    }

    public void withdraw(long withdrawValue){
        //withdraws money if it is smaller than the balance
        if(balance>=withdrawValue){
            balance-=withdrawValue;
        }else {
            System.out.println("Balance too low. You can't withdraw "+ withdrawValue + " with a "+ balance + " balance.");
        }
    }

    public boolean search(int accNumInput){
        //returns a boolean if the acc number exists
        return accno == accNumInput;
    }

}