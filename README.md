This is a Reflection API Java project for Advanced Programming Methods Course. 
The main goal is to go throught some java classes and find out how many declared 
and inherited fields or methods they have.

Basic Idea: I will try to use abstraction as much as possible. That's why, I have split the code
into 3 classes. 

Firstly, we have Main. Here I process the arguments so that the user will give something logical 
. The main job of main (ironic) is to open the input file and exit the output file. 
The input file is considered to be a txt files with one class in every line. For every class
name I use forName() to get an instance of this class. I then pass this instance to 
my ClassInfo. 

ClassInfo job is to set up the necessary methods that will calculate the number 
of methods and the number of fields of every class it receives as parameter. 

The whole logic of the program is included in ClassAnalyzer. Here I use Reflection to dynamically 
calculate the number of declared and inherited methods of every class given. I use java.lang.reflect 
to do this and the methods getDeclaredFields(), getFields(), getDeclaredMethods() and getMethods().
The method getDeclaredFields() returns the declared fields inside the class including private
fields. The method getFields() returns all inherited fields plus the public fields of the class.
So to calculate both inherited and declared fields I have combined the two arrays into one. 
I also made sure to count overloaded fields only once by using a Set<> that doesnt allow 
duplicate values. 