const userEmail = document.querySelector('#email')
const userPassword = document.querySelector('#password')
const submitBtn = document.querySelector('#main-btn')

fetch('/users')
    .then(res => {
        return res.json()
    })
    .catch(err => {
        console.log(err)
    })

    .then(json => {
        json.forEach(e => {
            loginUser(e)
        })
    })