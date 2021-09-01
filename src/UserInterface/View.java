package UserInterface;

import Utils.Utility;

import java.util.Scanner;

public class View {
    private boolean loop = true;
    private String input;
    private ClientService clientService = new ClientService();
    private MessageClientService messageClientService=new MessageClientService();
    public static void main(String[] args) {
        new View().main_menu();
        System.out.println("Terminated");
    }
    public void main_menu() {

        while (loop) {

            System.out.println("\nWelcome");
            System.out.println("1.Login");
            System.out.println("9.Exit");
            System.out.print("Please enter ur choice:");
            input = Utility.readString(1);
            switch (input) {
                case "1":
                    System.out.print("Ur Id:");
                    String id= Utility.readString(50);
                    System.out.print("Password:");
                    String password=Utility.readString(50);
                    if(clientService.checkUser(id,password)){
                        System.out.println("Welcome "+id+" back");
                        while (loop){
                            System.out.println("\n"+id+" interface");
                            System.out.println("1.Show online users");
                            System.out.println("2.Message All Users");
                            System.out.println("3.Inbox Users");
                            System.out.println("9.Exit");
                            System.out.print("Ur choice:");
                            input=Utility.readString(1);
                            switch (input){
                                case"1":
                                    clientService.onlineFriendList();
                                    break;
                                case"2":
                                    System.out.println("What do u talk?");
                                    String s=Utility.readString(50);
                                    messageClientService.sendMessageToAll(s,id);
                                    break;
                                case"3":
                                    System.out.print("Who do u want to talk with?");
                                    String receiverid=Utility.readString(50);
                                    System.out.print("What do u want to say?");
                                    String content=Utility.readString(50);
                                    messageClientService.sendMessageToOne(content,id,receiverid);
                                    break;
                                case"9":
                                    clientService.logout();
                                    loop=false;
                                    break;
                            }
                        }
                    }
                    else{
                        System.out.println("Login failed");
                    }
                    break;
                case "9":
                    System.out.println("out");
                    loop = false;
                    break;
            }
        }
    }
}
