# JDBC-Demo
A JFrame-based software demo utilizing a Spring/Hibernate JDBC system. Also uses an improved code based on the last dynamic GUI I published on this site. It currently works with Objects of the Fruit superclass, currently standing at Apples and Oranges.

To create the tables required to make this program work, start by watching the following link.

https://www.youtube.com/watch?v=eR_JFtqyNL4&index=36&list=PLzQekfF9y7ZcXPmw1s4bVaZaPPQWOHb3-

1) First, download Apache Derby. Then open up command prompt (or it's Mac OS/Linux equivelants), set the "bin" file as the central directory then type "startNetworkServer". 
2) Then open another command-line instance again in another window, redirect to the same directory as before, but instead of "startNetworkServer", type in "ij".
3) In "ij" mode, type in "connect 'jdbc:derby://localhost:1527/db;create=true';".
4) Create two tables called "apple" and "orange". Both should have only 2 fields, an id (integer) and description (char, 50 characters)
5) Type in "exit;" to exit "ij" mode.
6) As soon as you are finished working on the table (as to access the program, the table network server must be started every time), type in "stopNetworkServer", then close both command-line windows.
