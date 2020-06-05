# adventOfCode
Advent of code exercises for 2016 (https://adventofcode.com/2016), days 1, 4, and 10. Each day has it's own solution java file and unit test cases for each method.

## How to Check the Results
### — If you use an IDE (ex: IntelliJ, Eclipse)

1. Make sure you have the jdk installed. Click [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) to download the JDK;
2. Also, make sure you have Git installed. Click [here](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) for Git;
3. Clone project to local by command:
  ```shell script
  git clone https://github.com/edng0827/adventOfCode.git
  ```
4. Open project using IDE. Find classes in directory `src`&#8594;`main`&#8594;`java` and run the main method of each class. The result will print out in the console.
5. For the tests, find the test cases in directory `src`&#8594;`test`&#8594;`java` and run it. Each test is written and can be run on the method level.

### — If you don't run the code in an IDE

1. Make sure your computer have maven building tool. Find it [here](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html);
2. Make sure you have the jdk installed. Click [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) to download the JDK;
3. Go to the project file by typing the command:
```shell script
  cd Advent of Coding
  ```
4. For each day's challenge run the commend as below: 
```shell script
java -cp target/Advent of Coding-1.0-SNAPSHOT.jar Day1
```
5. Change the command between `Day1`,`Day4`, and `Day10` to see each output result.
6. For the test cases, run the command below to see the overall results: 
```shell script
 mvn clean install
```
