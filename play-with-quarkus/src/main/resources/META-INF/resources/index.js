 let btnEle=document.getElementById("btn")
    btnEle.addEventListener('click',e=>{
        // AJAX
        fetch("http://localhost:8080/hello")
        .then(response=>response.text())
        .then(text=>{
            console.log(text)
        })
    })