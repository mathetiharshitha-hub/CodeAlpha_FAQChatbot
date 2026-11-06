# CodeAlpha FAQ Chatbot

## Project Overview
The CodeAlpha FAQ Chatbot is a Java-based application that answers frequently asked questions by matching user input with predefined questions. It uses TF-IDF and Cosine Similarity to identify the most relevant FAQ and provide an appropriate response.

## Features
- Answers frequently asked questions.
- Uses TF-IDF for text representation.
- Uses Cosine Similarity to compare questions.
- Identifies unknown questions and displays a fallback response.
- Simple command-line interface.
- Supports continuous interaction until the user types `exit`.

## Technologies Used
- Java
- Core Java libraries
- TF-IDF
- Cosine Similarity
- Visual Studio Code

## Project Structure
```text
CodeAlpha_FAQChatbot/
├── FAQChatbot.java
└── README.md
```

## How to Run

### 1. Install Java
Install the Java Development Kit (JDK) and verify the installation:

```bash
java -version
javac -version
```

### 2. Compile the Program
Open the terminal in the project folder and run:

```bash
javac FAQChatbot.java
```

### 3. Execute the Program

```bash
java FAQChatbot
```

## Sample Output

```text
Welcome to College FAQ Chatbot!
Type 'exit' to stop.

You: where is the library?
Bot: Please check the campus map for the library location.

You: tell me about something unrelated
Bot: Sorry, I don't know that answer yet.
```

## How It Works
1. Stores predefined questions and their answers.
2. Converts text into TF-IDF vectors.
3. Calculates Cosine Similarity between the user's question and stored questions.
4. Selects the question with the highest similarity score.
5. Displays the corresponding answer when the score meets the threshold; otherwise, it displays a fallback response.

## Future Enhancements
- Add more FAQs and verified answers.
- Develop a graphical user interface.
- Improve language processing and question matching.
- Add more topics and categories.

## Internship
This project is developed as part of the CodeAlpha Artificial Intelligence Internship tasks.

## Author
Matheti Harshitha
