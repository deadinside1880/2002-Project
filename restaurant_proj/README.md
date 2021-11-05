# 2002-Project

Look at the files to see how the data is stored, lmk if y'all think something should be changed

Files are written as CSV but stored as .txt

I do not add new lines after each entry because when I read, I read everything into a list.
When I am writing though, if i wanted to add new lines, I would have to add it after different amount of elements for different files.
Example can be seen in menu.txt vs reservation.txt 
Menu has 3 elements but reservation has 4 elements.

Ultimately tho, it wont have much of an effect other than readability because when I read, its all going into a single list anyway.
The solution to this can be to make a list of lists but it gets a lil hard to handle (I think) So i just added a function to print specially

I took care of display menu and display reservations because they use a similar function (It is there in the main class start.java as teh function printStuff)
the reason for that is because of the way I read my files. I could add this print function into the file reader (that might make more sense) but we can always change it later

