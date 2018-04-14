Study Report for Practical Training
===

<!-- TOC -->

- [1. Vi](#1-vi)
  - [1.1. Basic operations in Vi](#11-basic-operations-in-vi)
    - [1.1.1. Get Started](#111-get-started)
    - [1.1.2. How to edit words?](#112-how-to-edit-words)
    - [1.1.3. Hey! The method above cannot remove an empty line!](#113-hey-the-method-above-cannot-remove-an-empty-line)
  - [1.2. More instructions](#12-more-instructions)
- [2. Java](#2-java)
  - [2.1. Introduction](#21-introduction)
    - [2.1.1. What is Java?](#211-what-is-java)
    - [2.1.2. Configure Your Java Environment](#212-configure-your-java-environment)
  - [2.2. Your First Java Program: Hello World](#22-your-first-java-program-hello-world)
  - [2.3. GUI in Java: Swing](#23-gui-in-java-swing)
    - [2.3.1. Get Started with Swing](#231-get-started-with-swing)
    - [2.3.2. Components in Swing](#232-components-in-swing)
    - [2.3.3. Arrange Components with GridLayout](#233-arrange-components-with-gridlayout)
    - [2.3.4. Write a Calculator!](#234-write-a-calculator)
- [3. Ant](#3-ant)
  - [3.1. Inrtoduction](#31-inrtoduction)
    - [3.1.1. What is Ant?](#311-what-is-ant)
    - [3.1.2. Configure the Environment](#312-configure-the-environment)
  - [3.2. Try Ant for the First Time!](#32-try-ant-for-the-first-time)
    - [3.2.1. Create Your Build.xml](#321-create-your-buildxml)
    - [3.2.2. Use Ant to Construct Your Project](#322-use-ant-to-construct-your-project)
- [4. JUnit](#4-junit)
  - [4.1. Introduction](#41-introduction)
    - [4.1.1. What is JUnit](#411-what-is-junit)
    - [4.1.2. Configure Environment](#412-configure-environment)
  - [4.2. Usage of JUnit with Ant](#42-usage-of-junit-with-ant)
    - [4.2.1. Create Test File](#421-create-test-file)
    - [4.2.2. Edit Your build.xml](#422-edit-your-buildxml)
    - [Edit Your HelloWorld.java](#edit-your-helloworldjava)
    - [4.2.3. Start your test!](#423-start-your-test)
    - [4.2.4. PAY ATTENTION!](#424-pay-attention)
  - [4.3. NO! I DISLIKE ANT! USE JUNIT ALONE!](#43-no-i-dislike-ant-use-junit-alone)

<!-- /TOC -->

*As Matrix's rendering of markdown is not so good, I strongly recommand you to read this file on VSCode!*

# 1. Vi

Vi is the standard and preinstalled editor in Unix and Unix-like OSs. It provides with the powerful functions for users to write and edit texture effectively.

Vi has three modes:

1. Command mode

    Control the moving of screen mark, deleting of characters, words and lines and switching to Insert and Last line mode.

1. Insert mode

    The only mode which is made for typing words. To return to command mode, press ESC.

1. Last line mode

    This mode is used to set editing enviornment, looking for strings and list the numbers of lines. Usually refered to be the same as command mode.

Those modes offer different functions, but for fresh men, it's too complex to uese without any guide. Thus let's step in the world of Vi/Vim together!

## 1.1. Basic operations in Vi

### 1.1.1. Get Started

To open a file with Vi, use the command below:

```bash
vi file
```

After executing this command, your Vi will open the file you specific in **Command mode**, which means you will have to switch to the **Insert mode** to start typing words. And all the things you need to do is **Pressing the Key 'i'**. If you want to switch back to Command mode, press the key 'ESC'.

Whenever you want to save the file and quit Vi, press 'ESC' to enter the command mode, then type ':' to enter the **Last line mode**, and type 'wq', in which 'w' means write, and 'q' means

### 1.1.2. How to edit words?

In linux's Vi/Vim, the key 'backspace' doesn't work as you expect - **they only change the position of the cursor**, while doing nothing to those words which has been typed in. Of course, you could type in new words to cover the old ones, but if you want to delete them, the key 'delete' is what you need.

But using that key introduce another problem that there's no way to delete those words before the cursor. In Vi/Vim, the method to solve this trouble is:

1. Press 'ESC' to enter the command mode.
1. Press 'X' (Pay attention! This character is the uppercase one.) to delete the very first word before the cursor.
1. If you want to delete more than one word, type in '#X', in which # is the number of words you want to delete, to delete those words.
1. The key 'x' (lowercase) works the same as the key 'Delete'.

### 1.1.3. Hey! The method above cannot remove an empty line!

Yep. Using 'Delete' or 'X' or something like that cannot remove an line. In order to do that, you're supposed to **type in 'dd' under the command mode**.

## 1.2. More instructions

[Introduction to Vi/Vim](http://www.runoob.com/linux/linux-vim.html)

# 2. Java

## 2.1. Introduction

### 2.1.1. What is Java?

Java is the high-level language created and maintained by Sun Microsystems company. Programs written in Java will be compiled into Bytecode and executed on Java Virtual Machine. Thanks to this feature, one Java program is able to execute on any platform with Java Runtime Environment without any edition. As an acient language, Java enjoys huge and useful offical and third-party libaries, which could help developers to constuct their application fast and well.

### 2.1.2. Configure Your Java Environment

- Package Manager

    In Ubuntu, the **apt-get package manager** is always a good choice to help you install and configure a new program. Thus, in order to construct a JDK (Jaca Development Kit), just run the command below:

    ```bash
    sudo apt-get opensdk-9-sdk
    sudo apt-get update
    ```

    But, in some cases, this method will failed with errors. To solve that, try:

    ```bash
    sudo apt-get -o Dpkg::Options::="--force-overwrite" install openjdk-9-jdk
    ```

    To check the status of your JDK installing, use

    ```bash
    javac -version
    ```

    If it is installed correctly, you'll see:

    ```bash
    javac 9-internal
    ```

- Manual Installing

    1. **Download JDK from the offical site**
    1. **Install JDK**
        - Give execution permission
        
        ```bash
        chmod +x [your JDK filename]
        ```

        - Execute the installing program
        
        ```bash
        ./[your JDK filename]
        ```
        
        - Move the generated floder into the /opt

        ```bash
        mv ./[generated floder] /opt
        ```
        
    1. **Configure**

        There's three ways to configure the enviroment variable:
        
        - edit the /etc/profile file (Globally effective)
            
            After opening the file with Vi or other editor, add lines below to the tail of the file:

            ```profile
            JAVA_HOME=/opt/[generated floder]
            PATH=$JAVA_HOME/bin:$PATH
            CLASSPATH=.:JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
            export JAVA_HOME PATH CLASSPATH
            ```

            Then, set source to this file and reboot the terminal.

            ```bash
            source /etc/profile
            reset
            ```
        
        - edit the .bashrc (Only effective for present user)
            
            After opening /home/seagar/,bashrc, add lines below to the tail of the file

            ```bash
            JAVA_HOME=/opt/[generated floder]
            PATH=$JAVA_HOME/bin:$PATH
            CLASSPATH=.$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
            export JAVA_HOME
            export PATH
            export CLASSPATH
            ```

        - Edit in the terminal (Only effective in present terminal)

            ```bash
            export JAVA_HOME=/opt/[generated floder]
            export PATH=$JAVA_HOME/bin:$PATH
            export CLASSPATH=.$JAVA_HOME/lib/dt.jar: $JAVA_HOME/lib/tools.jar
            ```

## 2.2. Your First Java Program: Hello World

Create a file **HelloWorld.java** (Attention: filename must be the same as the class name defined in the file), then type in the code below:

```JAVA
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World");
    }
}
```

Compile your program: (This step will generate the bytecode file HelloWorld.class)

```bash
javac HelloWorld.java
```

Execute your program:

```bash
java HelloWorld
```

Then if everything goes OK, you'll see:

```bash
Hello, World
```

**Welcome to the world of JAVA!**

## 2.3. GUI in Java: Swing

As an acient language, Java also has libaries to construct programs with GUI. AWT and Swing is one of the oldest ones. But AWT is so old that it can hardly meet the requirement of modern software developing. Swing is better than it, but still too acient to use. The only reason that I choose to import Swing is that it is included in the Java's standard libaries, which means I need to configure nothing more on the platform with JRE.

### 2.3.1. Get Started with Swing

Noting is more comvincing than writing by yourself. So follow the code below and try yourself!

```JAVA
import javax.swing.*;

public class HelloSwing {
    private static void CreateAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true); // Set a look style for your GUI

        JFrame frame = new JFrame("HelloWorldSwing"); // Create a frame as your Window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close event handler

        JLabel label = new JLabel("Hello, Swing!"); // Create a label
        frame.getContentPane().add(label); // Add the label into the content

        frame.pack(); 
        frame.setVisible(true); // Show the window
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
```

### 2.3.2. Components in Swing

I'll only introduce those components and their methods used in this project. For more information, you could visit [This page](https://www.yiibai.com/swing/swing_controls.html).

1. JLabel

    A component to place your texture. You can change the text in the label with 'setText(String)'.

1. JTextField

    A component which provides with a filed for users to type in and edit one-line texture. You can get text inside it with 'getText()'.

1. JButton

    A component to receive mouse's click and fire the click event. To handle this event, you could follow the code below:(This listener is contained in java.awt.event. You'd better import it first.)

    ```JAVA
    button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {     
            /* What should we do? */       
        }
    }); 
    ```

### 2.3.3. Arrange Components with GridLayout

GridLayout is able to arrage your components in grids, which is similar to Grid in C\# and Table in HTML. Follow the code below to construct your own program. (This layout is contained in java.awt. You'd better import it first.)

```JAVA
import javax.swing.*;
import java.awt.*;

public class GridLayoutDemo {
    private JFrame mainframe;
    private JPanel mainpanel;

    private JTextField myNumber1;
    private JLabel myOperator;
    private JTextField myNumber2;
    private JLabel myEquation;
    private JLabel myResult;

    private JButton myAdd;
    private JButton myMinus;
    private JButton myTimes;
    private JButton myDiv;
    private JButton myOK;

    public GridLayoutDemo () {
        prepareGUI();
    }

    public static void main(String[] args) {
        GridLayoutDemo gridlayoutdemo = new GridLayoutDemo();
    }

    private void prepareGUI() {
        mainframe = new JFrame("Calculator");
        mainframe.setSize(500, 200);
        mainframe.setLayout(new GridLayout(2, 5));

        myNumber1 = new JTextField();
        myNumber1.setHorizontalAlignment(JTextField.CENTER);
        myNumber2 = new JTextField();
        myNumber2.setHorizontalAlignment(JTextField.CENTER);
        
        myOperator = new JLabel();
        myEquation = new JLabel("=", JLabel.CENTER);
        myResult = new JLabel();

        myAdd = new JButton("+");
        myMinus = new JButton("-");
        myTimes = new JButton("*");
        myDiv = new JButton("/");
        myOK = new JButton("OK");

        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        mainframe.add(myNumber1);
        mainframe.add(myOperator);
        mainframe.add(myNumber2);
        mainframe.add(myEquation);
        mainframe.add(myResult);
        mainframe.add(myAdd);
        mainframe.add(myMinus);
        mainframe.add(myTimes);
        mainframe.add(myDiv);
        mainframe.add(myOK);
        mainframe.setVisible(true);
    }
}
```

### 2.3.4. Write a Calculator!

Based on the code in 2.3.3, thought adding event handler to buttons, we could construct a simple calculator!

```JAVA
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    private JFrame mainframe;

    private JTextField myNumber1;
    private JLabel myOperator;
    private JTextField myNumber2;
    private JLabel myEquation;
    private JLabel myResult;
    // blocks in line 1

    private JButton myAdd;
    private JButton myMinus;
    private JButton myTimes;
    private JButton myDiv;
    private JButton myOK;
    // blocks in line 2

    public Calculator () {
        prepareGUI(); // Construct GUI
    } // Constructor

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    } // Construct instance

    private void prepareGUI() {
        mainframe = new JFrame("Calculator");
        mainframe.setSize(500, 200);
        mainframe.setLayout(new GridLayout(2, 5));
        // Set size and layout of mainframe

        myNumber1 = new JTextField();
        myNumber1.setHorizontalAlignment(JTextField.CENTER);
        myNumber2 = new JTextField();
        myNumber2.setHorizontalAlignment(JTextField.CENTER);
        // Set alignment of two JTextField
        
        myOperator = new JLabel("", JLabel.CENTER);
        myEquation = new JLabel("=", JLabel.CENTER);
        myResult = new JLabel("", JLabel.CENTER);
        // Set content and alignment of JLabels

        myAdd = new JButton("+");
        myMinus = new JButton("-");
        myTimes = new JButton("*");
        myDiv = new JButton("/");
        myOK = new JButton("OK");
        // Set content of JButtons

        myAdd.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {     
            myOperator.setText("+");
          }
        });
        myMinus.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {     
            myOperator.setText("-");  
          }
        });
        myTimes.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {     
            myOperator.setText("*");
          }
        });
        myDiv.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {     
            myOperator.setText("/");
          }
        });
        myOK.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {     
            Operations();     
          }
        });
        // Bind Listeners to JButtons

        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        // Set close operation

        mainframe.add(myNumber1);
        mainframe.add(myOperator);
        mainframe.add(myNumber2);
        mainframe.add(myEquation);
        mainframe.add(myResult);
        mainframe.add(myAdd);
        mainframe.add(myMinus);
        mainframe.add(myTimes);
        mainframe.add(myDiv);
        mainframe.add(myOK);
        // Add blocks into the Grid

        mainframe.setVisible(true);
        // Show our window
    }

    public void Operations() {
      Double number1 = 0.0, number2 = 0.0, result; // Declare and initialize doubles
      try {
          number1 = Double.parseDouble(myNumber1.getText());
          number2 = Double.parseDouble(myNumber2.getText());
      } catch(NumberFormatException e) {
        myResult.setText("Invalid Format!");
        return;
      } // Parse numbers in the JTextFields. If not valid format, sent messages.

      switch(myOperator.getText()) {
        case "+":
          result = number1 + number2;
          myResult.setText(result.toString());
          break;
        case "-":
          result = number1 - number2;
          myResult.setText(result.toString());
          break;
        case "*":
          result = number1 * number2;
          myResult.setText(result.toString());
          break;
        case "/":
          if (number2 == 0)
            myResult.setText("Divided by 0!");
          else {
            result = number1 / number2;
            myResult.setText(result.toString());
          }
          break;
        default:
          myResult.setText("No operator!");
          break;
      }
    }
}
```

# 3. Ant

## 3.1. Inrtoduction

### 3.1.1. What is Ant?

Ant is a fast, convinient and cross-platform **project constructing tool**. What is a project constructing tool? You have my word that you must have met them! Cmake for C/C++, gulp for WEB, vue-cli for vue... They are all project constructing tools! They are designed to help programers construct and manage their projects faster and better. And Ant is the one for Java.

### 3.1.2. Configure the Environment

Just like Java, there're also two ways to configure your Ant.

- Package Manager

    Two lines to solve all your problem:

    ```bash
    sudo apt-get install ant
    sudo apt-get update
    ```

    If you want to check if it is installed correctly, try:

    ```bash
    ant -version
    ```

    If everything goes OK, you'll see:

    ```bash
    Apache Ant(TM) version 1.9.6 compiled on July 8 2015
    ```

    **Nice work, Apache!**

- Manual Installing

    OK, although apt-get could help us solve this problem, we still need to know the hidden details inside this command. So here comes the manual guide!

    1. **Download the latest Ant** from [Ant's offcial website](http://ant.apache.org/bindownload.cgi)
    1. Unpack this file

        ```bash
        tar -xf [filename]
        ```

    1. Move files to /opt/:

        ```bash
        sudo mv [generated floder] /opt/
        ```

    1. Configure Environment Variable

        Add or edit lines followed by '#' to those files metioned in Java part:

        ```bash
        export ANT_HOME=/opt/apache-ant-1.8.2 #
        export JAVA_HOME=...
        export PATH=$JAVA_HOME/bin:$PATH:$ANT_HOME/bin #
        export CLASSPATH=...
        ```

    After those steps, you can also use code below to check if it is installed correctly:

    ```bash
    ant -version
    ```

    I hope you'll see:

    ```bash
    Apache Ant(TM) version 1.9.6 compiled on July 8 2015
    ```

## 3.2. Try Ant for the First Time!

We'll use our Hello World to construct an example. It is an easy job to compile and execute your program if you have only a file called HelloWorld.java, but **what if you have thousands of .java files**? Tools like Ant are designed to solve this problem. So let's get started!

### 3.2.1. Create Your Build.xml

To use Ant, you need to create a .xml file to figure out how Ant should work with your project. Name of this file matters nothing, but I strongly recommand you use 'build.xml', as it is the default name that Ant will recognize if you offer no argument. Now, I'll give the example file and explain it.

```XML
<?xml version="1.0" encoding="UTF-8"?>
<!--Mark file's XML version-->
<project name="HelloWorld" default="run" basedir=".">
<!--Declear a project, whose name is HelloWorld, and the default instruction is 'run'-->
    <property name="src" value="src"/>
    <property name="dest" value="dest"/>
    <property name="hello_jar" value="hello1.jar"/>
    <!--Set functional folder and path of target .jar (packed java program)-->
    
    <target name="init">
        <mkdir dir="${dest}"/>
    </target>
    <!--Set the behaviors of init instrution-->

    <target name="compile" depends="init">
        <javac srcdir="${src}" destdir="${dest}"/>
        <!--Equals to 'javac' + ${src} + ${dest}-->
    </target>
    <!--Set the behaviors of compile instruction-->

    <target name="build" depends="compile">
        <jar jarfile="${hello_jar}" basedir="${dest}"/>
        <!--Pack your program in ${dest} and generate a .jar file at ${hello_jar}-->
    </target>
    <!--Set the behaviors of build instruction-->

    <target name="run" depends="build">
        <java classname="HelloWorld" classpath="${hello_jar}"/>
        <!--Use java command to execute your program-->
    </target>
    <!--Set the behaviors of run instruction-->

    <target name="clean">
        <delete dir="${dest}"/>
        <delete dir="${hello_jar}"/>
        <!--Delete folders of generated files-->
    </target>
    <!--Set the behaviors of clean instruction-->

    <target name="rerun" depends="clean, run">
        <ant target="clean"/>
        <ant target="run"/>
    </target>
</project>
```

### 3.2.2. Use Ant to Construct Your Project

First, move your .java file into the src/ floder

```bash
mv HelloWorld.java src/HelloWorld.java
```

Then, give all the work to Ant!

```bash
ant
```

If you do a good job, what you'll see in your terminal is:

```bash
Buildfile: /home/siskon/Desktop/SE-2018/Section 1/build.xml

init:
    [mkdir] Created dir: /home/siskon/Desktop/SE-2018/Section 1/dest

compile:
    [javac] /home/siskon/Desktop/SE-2018/Section 1/build.xml:16: warning: 'includeantruntime' was not set, defaulting to build.sysclasspath=last; set to false for repeatable builds
    [javac] Compiling 1 source file to /home/siskon/Desktop/SE-2018/Section 1/dest

build:
      [jar] Building jar: /home/siskon/Desktop/SE-2018/Section 1/hello1.jar

run:
     [java] Hello,World

BUILD SUCCESSFUL
Total time: 0 seconds
```

**Let's start our journal of Java with Ant!**

# 4. JUnit

## 4.1. Introduction

### 4.1.1. What is JUnit

JUnit, also known as 'Java Unit Test', is a unit test tool designed for Java. I guess you'll ask that what is 'unit test'? I believe that you've ever used online judge systems before. And unit test is what really decides whether your program gives the correct answer or not. With unit test, developers're able to test their programs while developing. It could makes their code more stable and reliable.

### 4.1.2. Configure Environment

In this guide, we're going to discuss about **using JUnit and Ant at the same time**. So what you need to do is:

1. [**Download the Junit**](http://search.maven.org/#search%7Cgav%7C1%7Cg:%22junit%22%20AND%20a:%22junit%22)
1. Put it into your workspace.

    Take our Hello World as an example. Construct the floder tree to the below one:

    ```txt
    HelloWorld
    |- lib
    ||- junit.jar
    |- src
    ||- HelloWorld.java
    ||- TestHelloWorld.java
    |- test
    |- dest
    |- build.xml
    ```

    In which TestHelloWorld.java is the class used to test our program.

After doing that, you could edit your build.xml to use JUnit to test your code!

## 4.2. Usage of JUnit with Ant

### 4.2.1. Create Test File

We've created the file TestHelloWorld.java to test our program, so what should we write in it? Let's take a look:

```JAVA
import java.io.ByteArrayOutputStream; // As we need to capture the message in standard output stream, we need this class
import java.io.PrintStream; // Used to backup the standard output
import static org.junit.Assert.*; // Junit components

public class TestHelloWorld {
    PrintStream outstream = null; // Used to backup the standard output stream
    ByteArrayOutputStream mybytes = null; // Used to store the captured data
    HelloWorld helloworld; // Used to create an instace of our HelloWorld

    @org.junit.Before // prepare for test
    public void setUp() throws Exception {
        mybytes = new ByteArrayOutputStream(); // Instantiate the byte array
        outstream = System.out; // Bind outstream with the oringinal standard output
        System.setOut(new PrintStream(mybytes)); // Redirect output stream to our byte array
    }

    @org.junit.Test // Testing
    public void testResult() throws Exception {
        helloworld = new HelloWorld(); // Instantiate our class
        String str = new String("Hello,World\n"); // Expected output
        assertEquals(str, mybytes.toString()); // Assert: str == output
    }

    @org.junit.After
    public void tearDown() throws Exception {
        System.setOut(outstream); // Recovery the standard output
    }
}
```

### 4.2.2. Edit Your build.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--Mark file's XML version-->
<project name="HelloWorld" default="test" basedir=".">
<!--Declear a project, whose name is HelloWorld, and the default instruction is 'run'-->
    <property name="src" value="src"/>
    <property name="dest" value="dest"/>
    <property name="test" value="test"/>
    <!--Set functional folder and path of target .jar (packed java program)-->

    <property name="full-compile" value="true" />
    <!--Full compile-->

    <path id="classpath.base"/>
    <path id="classpath.test">
      <pathelement location="lib/junit.jar" />
      <pathelement location="${test}" />
      <pathelement location="${src}" />
      <path refid="classpath.base" />
   </path>    

    <target name="clean">
        <delete verbose="${full-compile}">
            <fileset dir="${test}" includes="**/*.class" />
        </delete>
        <!--Delete folders of generated files-->
    </target>
    <!--Set the behaviors of clean instruction-->

    <target name="compile" depends="clean">
      <javac srcdir="${src}" destdir="${test}" verbose="${full-compile}">
         <classpath refid="classpath.test"/>
      </javac>
   </target>
   <!--Special compiling for test-->

    <target name="test" depends="compile">
      <junit>
         <classpath refid="classpath.test" />
         <formatter type="brief" usefile="false" />
         <test name="TestHelloWorld" />
      </junit>
   </target>
   <!--Test!-->
  
</project>
```

### Edit Your HelloWorld.java

As main method should not appear in the class being tested, let it go~

```JAVA
public class HelloWorld {
    public HelloWorld() {
        System.out.println("Hello,World");
    }
    /*public static void main(String[] args) {
        System.out.println("Hello,World");
    }*/
}
```

### 4.2.3. Start your test!

```bash
ant

test:
    [junit] Testsuite: TestHelloWorld
    [junit] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.012 sec
    [junit]
```

### 4.2.4. PAY ATTENTION!

After junit 4.10, **hamcrest package is no longer included in junit.jar!** Thus, the code above might not work correctly. What I choose is to download the junit 4.9... Or you can download those package and import them into the classpath.

## 4.3. NO! I DISLIKE ANT! USE JUNIT ALONE!

That's OK, and it's quite simple!

1. Move your junit.jar, HelloWorld.java and TestHelloWorld.java into one floder.
1. Compile:

    ```bash
    javac -cp .:junit.jar TestHelloWorld.java
    ```

1. Run the test:

    ```bash
    java -cp .:junit.jar org.junit.runner.JUnitCore TestHelloWorld
    ```

1. You will get:

    ```bash
    JUnit version 4.9
    .
    Time: 0.006

    OK (1 test)
    ```

**Enjoy Using JUit~**