CS4470 - Project 1, A Chat Application for Remote Message Exchange

[ CONTRIBUTIONS ]

Two team members, Pavit and Ana

Pavit Chawla
	- Initialized project (framework, general concept of functions)
	- Wrote initial version of functions.java
	- Wrote initial version of help.txt
	- Wrote Peer.java

Ana Guardado
	- Added additional features to functions.java
	- Updated/edited help.txt
	- Wrote chat.java
	- Wrote Makefile, README
	- Video demo

[ INSTALLATION ]

- Works best on Ubuntu 16.04
- Confirm that /etc/hosts file contains the correct private IP address
	- If Java code returns 127.0.1.1 for private, apply the following steps
	- When using VirtualBox, VMs returned loopback IP when asking for private
	- Use command "sudo nano /etc/hosts"
	- Type private IP address next to hostname
		- Example: "192.168.56.104 ubuntu-1604-1"
- Install Java on Ubuntu using command "sudo apt-get install openjdk-8-jdk"
- Compile Java files with "javac <filename>.java"
	- Apply this step to functions.java, chat.java, Peer.java
- Run Java file using command "java chat <port number>"
	- Will start terminal like mode that awaits commands
	- Port number used for local machine for incoming connections

[ RESOURCES ]

The following links were used in the development of this project:

- Installing Java and compiling code
	- https://askubuntu.com/questions/145748/how-to-compile-a-java-file-on-ubuntu
- Socket programming
	- http://beej.us/guide/bgnet/html/#client-server-background
	- https://stackoverflow.com/questions/27200158/inetaddress-getlocalhost-
gethostaddress-returning-unwanted-address-java
	- https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
	- https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
	- https://docs.oracle.com/javase/7/docs/api/java/net/ServerSocket.html
- Java Threads
	- https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html




