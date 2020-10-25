CS4470 - Project 1, A Chat Application for Remote Message Exchange

[ CONTRIBUTIONS ]

Two team members, Pavit and Ana

Pavit Chawla
	- Initialized project (framework, general concept of functions)
	- Wrote initial version of functions.java
	- Wrote help.txt
	- Wrote Peer.java

Ana Guardado
	- Added additional features to functions.java
	- Wrote chat.java
	- Wrote Makefile, README
	- Video demo

[ INSTALLATION ]

- Works best on Ubuntu 16.04
- The makefile, Java files, and txt file must all be in the same directory
- Confirm that /etc/hosts file contains the correct private IP address
	- If Java code returns 127.0.1.1 for private, apply the following steps
	- When using VirtualBox, VMs returned loopback IP when asking for private
	- Use command "sudo nano /etc/hosts"
	- Type private IP address next to hostname
		- Example: "192.168.56.104 ubuntu-1604-1"
- Ensure that the machines involved in this program can communicate with eachother
	- Use the ping command against the IPs/hostnames
		- If there is a response, the machines can be used in this program
	- Machines will need to be on the same network
- Install Java on Ubuntu using command "sudo apt-get install openjdk-8-jdk"
- Compile Java files
	- Compile Java files with "javac <filename>.java"
		- Apply this step to functions.java, chat.java, Peer.java
	- Alternatively, use the command "make" and use the makefile available with this project
- After compiled, run Java chat file using command "java chat <port number>"
	- Will start in terminal-like mode that awaits commands
	- Replace <port number> with positive Integer between 1024 and 65535
	- Port number is used for local machine for incoming connections

[ RESOURCES ]

The following links were used in the development of this project:

- Installing Java and compiling code
	- https://askubuntu.com/questions/145748/how-to-compile-a-java-file-on-ubuntu
- Socket programming
	- http://beej.us/guide/bgnet/html/#client-server-background
	- https://stackoverflow.com/questions/27200158/inetaddress-getlocalhost-gethostaddress-returning-unwanted-address-java
	- https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
	- https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
	- https://docs.oracle.com/javase/7/docs/api/java/net/ServerSocket.html
- Java Threads
	- https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html
- Makefile guide
	- https://www.cs.swarthmore.edu/~newhall/unixhelp/javamakefiles.html

[ HELP TEXT ]

The following text is printed to the console when the command "help" is used:

CS4470
Project #1, Chat Application 

These shell commands are defined internally. Type `help' to see this list.

/help
	- Display the command manual or user interface options
 
/myip
	- Show the IP address of the laptop that runs the program

/myport
	- Display the port # that the program is running on

/connect <destination> <port no>
	- Establish TCP connection to destination with specified port
	- Both peers are notified of successful and failed connections using suitable messages

/list
	- List all the connected peers
	- Output contains IP address and listening port of all peers currently connected to
 
/terminate <connection id>
	- Terminate a connection
	- Both peers are notified of terminated connections

/send <connection id> <message>
	- Message sent to peer using connection id
	- Peer receives a message notification with the sender IP

 /exit
	- Quit program
	- Terminate all connections
	- All peers notified of terminated connections
	




