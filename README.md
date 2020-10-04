# Capital One Coding Challenge Submission - Code Analyzer

To run the application:
`cd /comment-counter`
`javac CodeAnalyzer.java`
`javac Main.java`
`java Main`
*Select your java file to analyze, result will be in the command line*

Known issues:
* Sometimes after running the program the JFileChoser won't open, just terminate and re-run the program. Happened on my machine (OSX) about 1/20 times.

# Assumptions made
#### Assumption 1:
The code was written with the intention of only analyzing Java code. Although after some research I realize this *should* work for **Javascript, TypeScript, C/C++, C#**. Essentially any coding language that uses the same comment conventions as Java, although to me it makes more sense for this to be language specific as it was written in Java.
#### Assumption 2:
Lines in block comments count as **anything** between `/* */`, regardless if the line starts with a lone `*`, spans multiple lines, or spans one line. The following code would all count as 3 lines within block comments.
<code> /*
\* This is a comment
*/ </code>

<code> /*
This is a comment
*/

#### Assumption 3:
Similarly, if a comment is too long for the current line, and the IDE pushes it down one line, it now counts as **two lines** within block comments. The following code would count as 4 lines within block comments. This is not exactly how the PDF demonstrated it but it makes a lot more sense to me since a line becoming too long creates a new line in the IDE.
<code>/*
\* This is a very long comment so long it is going to reach the end of the ide and span a second line
*/

#### Assumption 4:
<code>/* A comment */ </code> This is not a single line comment, but a block comment.

#### Assumption 5:
``TODO`` should only appear in single line comments.
<code> //TODO: stuff </code>  This would be a valid TODO count.
<code> */ TODO */ </code> This would not be valid, although it is only on a single line Java defines this as a **block comment** (see above).

#### Assumption 6:
White space lines count as a line.