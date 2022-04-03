var Buttons = document.querySelectorAll('div.btn');
var txt = document.getElementById('view');

Buttons.forEach(butt => {
    butt.addEventListener('click', handleClick);
});

async function handleClick(e) {
    txt.setAttribute('placeholder', '');
    var button = e.target;

    switch (button.innerHTML) {
        case 'DEL': 
            renderInput("DELETE")
            break;
        case '+':
        case '-':
        case 'X':
        case '÷':
        case '%':
            renderInput(` ${button.innerHTML} `)
            break;
        case '=':
            await sendInput(txt.innerHTML);
            break;
        default:
            renderInput(button.innerHTML);
            break;
    }
};

function renderInput(str) {
    if (str == "DELETE") {
        txt.innerHTML= '';
        txt.setAttribute('placeholder', 'Put some number idk...');
    } else {
        txt.innerHTML+= str;
    } 
}

async function sendInput(input) {
    if(!input) return;
    
   	var op = input.split(' ');
   	
   	var response = await fetch("http://localhost:8080/calcServlet/CalcularServlet", {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Request-Methods" : "POST, OPTIONS"
        },
        body: JSON.stringify({
            "number1": op[0],
            "operation": op[1],
            "number2": op[2]
        })
    })

	console.log(response);
    var data = await response.json()
    console.log(data);
    txt.innerHTML = data.value;
};