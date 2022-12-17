import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class AtmMachine {
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        //create initial accounts
        List<AtmDetails> lista = new ArrayList<>();

        //creates 100 memory slots for customer data
        //gets added by 1 for each account we add, adds accounts on that part of the array
        int ch;
        do {
            //print interface
            System.out.println("\n Banking System Application");
            System.out.println("1. Display all account details \n2. Search by Account number\n3. Deposit the amount \n4. Withdraw the amount \n5. Add a new account \n6. Transfer money to another account \n7. Exit ");
            System.out.println("Enter your choice: ");
            ch = 0;
            ch = exception(sc, ch);
            switch (ch){
                case 1: {
                    if(lista.size()==0){
                        System.out.println("0 accounts exist at the moment");
                    }else {
                        System.out.println(lista.size() + " accounts exist at the moment");
                        for (AtmDetails user : lista) {
                            user.showAccount(); //shows all the registered customers info
                        }
                    }
                    break;
                }
                case 2: {
                    //searches an account if it exists, raises a flag and shows it with showAccount() method
                    System.out.println("Account number to be searched: ");
                    int accNumInput = 0;
                    accNumInput = exception(sc, accNumInput);
                    boolean foundFlag = false;
                    for (AtmDetails user : lista) {
                        foundFlag = user.search(accNumInput); //since search returns a boolean
                        if (foundFlag) {
                            user.showAccount();
                            break;
                        }
                    }
                    if(!foundFlag){
                        System.out.println("Account number does not exist");
                    }
                    break;
                }
                case 3: {
                    //first we go inside the account based on account number so case 2 copied
                    System.out.println("Account number to be searched: ");
                    int accNumInput = 0;
                    accNumInput = exception(sc, accNumInput);
                    boolean foundFlag = false;
                    for (AtmDetails user : lista) {
                        foundFlag = user.search(accNumInput); //since search returns a boolean
                        if (foundFlag) {
                            user.showAccount();
                            //we deposit x amount of money to the customers[i].balance variable
                            user.deposit();
                            break;
                        }
                    }
                    if(!foundFlag){
                        System.out.println("Account number does not exist");
                    }
                    break;
                }
                case 4: {
                    //check if it exists
                    System.out.println("Account number to be searched: ");
                    int accNumInput = 0;
                    accNumInput = exception(sc, accNumInput);
                    boolean foundFlag = false;
                    for (AtmDetails user : lista) {
                        foundFlag = user.search(accNumInput); //we get true if it exists
                        if (foundFlag) {
                            user.showAccount(); //show the account, take the withdrawal value and remove it from the balance with .withdraw()
                            System.out.println("Enter the value you want to withdraw: ");
                            long withdrawValue = sc.nextLong();
                            user.withdraw(withdrawValue);
                            break;
                        }
                    }
                    if(!foundFlag){
                        System.out.println("Account number does not exist");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Account number to be added: ");
                    int accNumInput = 0;
                    accNumInput = exception(sc, accNumInput);
                    boolean foundFlag = false;
                    for (AtmDetails user : lista) {
                        foundFlag = user.search(accNumInput);
                        if (foundFlag) {
                            System.out.println("This account number already exists");
                            break;//we need unique account number ,so we check if it exists
                        }
                    }
                    if(!foundFlag){
                        AtmDetails user1 = new AtmDetails();
                        user1.accno = accNumInput;//creates a new object
                        user1.openAcc();
                        lista.add(user1);//takes the accNumInput if it is unique as customer data
                        //takes the rest of the data
                    }
                    break;
                }
                case 6: {
                    if(lista.size()==0){
                        System.out.println("No accounts exist at the moment");
                    }else if (lista.size()==1){
                        System.out.println("You need at least 2 accounts to transfer money");
                    } else {
                        System.out.println("Account number to be searched: ");
                        int accNumInput = 0;
                        accNumInput = exception(sc, accNumInput);
                        boolean foundFlag = false;
                        for (int i = 0; i < lista.size(); i++) {
                            AtmDetails user1 = lista.get(i);
                            foundFlag = user1.search(accNumInput);
                            if (foundFlag) {
                                //if account exists, we find if the account to transfer money to exists
                                System.out.println("Account number to transfer money to: ");
                                accNumInput = 0;
                                accNumInput = exception(sc, accNumInput);
                                boolean foundFlag2 = false; //raise another flag ,so they do not mess with their own values
                                for (AtmDetails user2 : lista) {
                                    foundFlag2 = user2.search(accNumInput);
                                    if (foundFlag2) { //if it finds the second account:
                                        System.out.println("Enter the value you want to transfer: ");
                                        long withdrawValue = sc.nextLong();
                                        user1.withdraw(withdrawValue); //remove the taken value from 1st account
                                        user2.balance += withdrawValue; //add it to 2nd account
                                    }
                                }
                                if (!foundFlag2) {
                                    System.out.println("Account number does not exist");
                                }
                                break;
                            }
                        }
                        if (!foundFlag) {
                            System.out.println("Account number does not exist");
                        }
                    }break;
                }
                case 7: {
                    System.out.println("Goodbye!");
                    break;
                }
                default: {
                    System.out.println("Invalid input.");
                }
            }
        }while (ch!=7);
    }

    public static int exception(Scanner sc, int input){
        boolean Error = false;
        while (!Error){
            try {
                input = sc.nextInt();
                Error=true;

            } catch (InputMismatchException ime){
                System.out.println("You did not enter an integer, please enter an integer value");
                sc.nextLine();// this consumes the invalid token
            }
        }
        return input;
    }
}
