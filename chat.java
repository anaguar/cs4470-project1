import java.util.Scanner;
import java.util.Hashtable;
import java.util.Arrays;
import java.lang.Integer;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class chat {
    public static Integer port;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Set listening port
        try {
            // Confirm user port meets requiremetns
            if (args.length == 1) {
                Integer test_port = Integer.parseInt(args[0]);

                if ((test_port >= 1024) && (test_port <= 65535)) {
                    // Call function that enables user to select from available commands
                    port = test_port;
                    program();
                }
            }

            throw new IOException();
        } catch (IOException | NumberFormatException ex) {
            System.out.println("ERROR: Indicate a port number to start the program and initiate a connection.\n");
        }
    }

    public static void program() {
        String[] commands = {"help", "myip", "myport", "connect", "list", "terminate", "send", "exit"};

        try {
            // Start the thread where program waits for connection
            Thread listen = new Thread(new Runnable() {
                public void run() {
                    functions.listening(port);
                }
            });

            listen.start();

        } catch (RuntimeException ex) {
            System.out.println("SAD!");
        }

        boolean status = true;

        while (status) {
            Scanner input = new Scanner(System.in);

            // Get user parameters and confirm it meets the expected command/parameter count
            String[] user_command = input.nextLine().split(" ");
            
            switch (user_command[0]) {
                case "help":
                    if (user_command.length == 1) {
                        functions.showHelp();
                    } else {
                        System.out.println("ERROR: Wrong # of parameters. Try again.\n");
                    }

                    break;
                case "myip":
                    if (user_command.length == 1) {
                        try {
                            System.out.println("IP Address: " + functions.getMyIP() + "\n");
                        } catch (Exception e) {
                            System.out.println("ERROR: Cannot get IP address. \n");
                        }
                    } else {
                        System.out.println("ERROR: Wrong # of parameters. Try again. \n");
                    }

                    break;
                case "myport":
                    if (user_command.length == 1) {
                        System.out.println("Listening port: " + port + "\n");
                    } else {
                        System.out.println("ERROR: Wrong # of parameters. Try again. \n");
                    }

                    break;
                case "connect":
                    if (user_command.length == 3) {
                        try {
                            functions.connect(user_command[1], Integer.parseInt(user_command[2]));
                        } catch (Exception e) {
                            System.out.println("ERROR: Could not connect to " + user_command[1]);
                        }
                    } else {
                        System.out.println("ERROR: Wrong # of parameters. Try again.\n");
                    }

                    break;
                case "list":
                    if (user_command.length == 1) {
                        functions.listPeers();
                    } else {
                        System.out.println("ERROR: Wrong # of parameters. Try again.\n");
                    }

                    break;
                case "terminate":
                    if (user_command.length == 2) {
                        try {
                            functions.terminate(Integer.parseInt(user_command[1]));
                        } catch (Exception e) {
                            System.out.println("ERROR: Could not terminate connection with ID " + user_command[1] + "\n");
                        }
                    } else {
                        System.out.println("ERROR: Wrong # of parameters. Try again.\n");
                    }

                    break;
                case "send":
                    if (user_command.length >= 3) {
                        try {
                            Integer connection_id = Integer.parseInt(user_command[1]);
                            user_command[0] = "";
                            user_command[1] = "";

                            String user_command_str = String.join(" ", user_command);

                            functions.send(connection_id, user_command_str);
                        } catch (Exception e) {
                            System.out.println("ERROR: Could not connect to " + user_command[1]);
                        }
                    } else {
                        System.out.println("ERROR: Wrong # of parameters. Try again.\n");
                    }

                    break;
                case "exit":
                    if (user_command.length == 1) {
                        functions.exit();
                        status = false;
                        System.exit(0);
                    } else {
                        System.out.println("ERROR: Wrong # of parameters. Try again.\n");
                    }
                    break;
                default:
                    System.out.println("ERROR: Command not in list. Try again.\n");
            }
        }
    }
}