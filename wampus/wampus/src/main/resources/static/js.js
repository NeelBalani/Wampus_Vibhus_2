const D = {
    questions: [
        {
            question: "What's my name?",
            answers: ["Bingus", "Pranav"],
            correctAnswer: "Pranav"
        },
        {
            question: "What?",
            answers: ["Huh", "Yes"],
            correctAnswer: "Yes"
        },
        {
            question: "Which is bad?",
            answers: ["unicorns", "disgusting lunch slop"],
            correctAnswer: "disgusting lunch slop"
        }
    ]
};

fetch("myUrl")
    .then(res=>{JSON.stringify})
    .then(getData);

    
const quizContainer = document.getElementById('quiz-container');
const templates = document.getElementById('templates');

const getData = function(d) {
    for (let i = 0; i < d.questions.length; i++) {
        const questionData = d.questions[i];
        const questionElement = templates.querySelector('.question-container').cloneNode(true);
        const questionTextElement = questionElement.querySelector('.question-text');
        const answersListElement = questionElement.querySelector('.answers-list');

        questionTextElement.textContent = questionData.question;
        answersListElement.innerHTML = ''; // Clear existing answers

        questionData.answers.forEach(answerText => {
            const answerItem = document.createElement('li');
            answerItem.classList.add('answer-item');
            answerItem.textContent = answerText;
            answerItem.dataset.answer = answerText; // Store the answer text
            answerItem.addEventListener('click', (event) => {
                doSomething(event, questionData);
            });
            answersListElement.appendChild(answerItem);
        });

        quizContainer.appendChild(questionElement);
    }
};

const doSomething = function(event, q) {
    const selectedAnswer = event.target.dataset.answer;
    const isCorrect = selectedAnswer === q.correctAnswer;

    if (isCorrect) {
        alert("You got it!");
    } else {
        alert("Nope!");
    }


    fetch('/api/submit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ question: q.question, userAnswer: selectedAnswer, correctAnswer: q.correctAnswer, correct: isCorrect })
    })
    .then(res => res.json())
    .then(serverResponse => {
        console.log("Server says:", serverResponse);
    })
    .catch(error => {
        console.error("Error submitting answer:", error);
    });
};

const doSomething = function(e,q,a){
    q.correct = a == q.answer;

    if(q.correct){
        alert("correct");
    }
    else {
        alert("incorrect");
    }

    fetch('api/submit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(q)
    })
}

getData(D);