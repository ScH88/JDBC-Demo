# JDBC-Demo
A JFrame-based software demo utilizing a Spring/Hibernate JDBC system. Uses a dynamic GUI system which I have refined over the past month. It currently works with Objects of the Fruit superclass, currently standing at Apples and Oranges.

To create the tables required to make this program work, start by watching the following link.

https://www.youtube.com/watch?v=eR_JFtqyNL4&index=36&list=PLzQekfF9y7ZcXPmw1s4bVaZaPPQWOHb3-

1) First, download Apache Derby. Then open up command prompt (or it's Mac OS/Linux equivelants), set the "bin" file as the central directory then type "startNetworkServer". 
2) Then open another command-line instance again in another window, redirect to the same directory as before, but instead of "startNetworkServer", type in "ij".
3) In "ij" mode, type in "connect 'jdbc:derby://localhost:1527/db;create=true';".
4) Create two tables called "apple" and "orange". Both should have only 2 fields, an id (integer) and description (char, 50 characters)
5) Type in "exit;" to exit "ij" mode.
6) As soon as you are finished working on the table (as to access the program, the table network server must be started every time), type in "stopNetworkServer", then close both command-line windows.

URLs of Images Used:

https://static1.squarespace.com/static/54ad5416e4b0867c63d22cc4/t/54ad5609e4b0f1bacf9f84ea/1420646020385/farmpic1.jpg?format=1500w

http://del.h-cdn.co/assets/16/15/1460645766-fruit-salad.png

http://del.h-cdn.co/assets/cm/15/11/54fdf58790a68-hawaiian-fresh-fruit-salad-recipe-ghk0913-sdzrag-xl.jpg

http://savorysweetlife.com/wp-content/uploads/2009/08/fruitsalad1.jpg

http://cf.houseofyumm.com/wp-content/uploads/2014/08/rainbow-salad-021_680.jpg
 
