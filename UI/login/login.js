function login(event) {
    event.preventDefault();

    const username = document.getElementById('inputUsername').value;
    const password = document.getElementById('inputPassword').value;
    const credential = {
        username,
        password
    };

    fetch('http://localhost:8080/ERS/auth/login', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        mode: 'cors',
        credentials: 'include', // put credentials: 'include' on every request to use session info
        body: JSON.stringify(credential)
    })
     .then(resp => resp.json())
     .then(data => {
     //.then(resp => {
       //  data = currentUser;
         //if(resp.status === 201) {
            //redirect
      //       console.log('navigate to reimbursements')
      //           console.log();
        if (data.role === 0) {
            console.log(data);
             window.location = '/financeManager/manager.html';
         } else if (data.role === 1) {
            console.log(data);
            window.location = '/reimbursement/view-reimbursement/view-reimb.html';
         } 
         else {
             document.getElementById('error-message').innerText = 'Failed to login';
         }
        //}
     })
}
