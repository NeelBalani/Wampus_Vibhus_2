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

getData(D);

alert("??");
