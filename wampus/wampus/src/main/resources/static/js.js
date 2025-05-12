const D = {
    questions:[
        {
            question:"whats my name?",
            answers:["Bingus", "Pranav"],
            correctAnswer:"Pranav"
        },
        {
            question:"what?",
            answers:["Huh", "Yes"],
            correctAnswer:"Yes"
        },
        {
            question: "which is bad?",
            answers:["unicorns", "disgusting lunch slop"],
            correctAnswer: "disgusting lunch slop"

        }
        
    ]
};


const getData = function(d){
    const tmp = document.querySelector("#templates > .question");

    for(var i = 0; i < d.questions.length; i++){
       
        let dom = tmp.cloneNode(true)

        console.log(d[i])
        let q = d.questions[i];
        dom.querySelector(".question").innerHTML = q.question;
        dom.querySelector(".answers").innerHTML = "";
        for(var j = 0; j < q.answers.length; j++){
            let answer = document.createElement("li");
            answer.innerHTML = q.answers[j];
            dom.querySelector(".answers").append("");
            
        }

        document.body.append(dom);
    }


    alert("data time");
}

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

alert("??");
