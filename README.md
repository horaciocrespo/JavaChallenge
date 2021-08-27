# Ten pin bowling score-sheet calculator and printer

This is a small command-line application to score a game of ten-pin bowling.

You provide a file with information about a player and the number of knocked down pins and the program will output a formatted version of the resulting score-sheet. Something like this:

![alt text](https://git.jobsity.com/jobsity/JavaChallenge/-/wikis/uploads/ddef13d61c61c1b30785d864f011b0ea/image2.jpg)

## Rules

You can find the detailed rules in the following link:

[Java Challenge Rules](https://git.jobsity.com/jobsity/JavaChallenge/-/wikis/Java%20Challenge)

## How to use it

### Clone the repository
Clone the repo to your local computer using `git clone https://git.jobsity.com/horaciocrespo/JavaChallenge.git`  

### Create executable jar with Maven

Now that you have all the source code it's time to compile it and generate the executable jar.

1. Navigate to the folder `JavaChallange`
2. Now just run `mvn package` if you have maven installed on your computer, or `mvnw package` if you're using windows
3. Notice a `target` directory was created inside `JavaChallange`

### Running the executable

To run it just navigate to the `target` folder using CMD, you'll see a `ten-pin-bowling-score-calculator-1.0-SNAPSHOT.jar` file.

1. Run the jar file passing the path to your .txt file, something like `java -jar ten-pin-bowling-score-calculator-1.0-SNAPSHOT.jar C:\Users\Horacio\IdeaProjects\JavaChallenge\src\test\resources\positive\scores.txt`
2. As soon as you press enter you'll get a nice score-sheet printed to you console.

## Validations

It's important to note that some scenarios are invalid and won't print the score-sheet. This is a list of some of them:

* A file that doesn't exist
* An empty file
* A file with negative scores
* A file with content in wrong format
* A file with score higher than 10
