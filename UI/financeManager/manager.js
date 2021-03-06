let currentUser;

function addReimbursementToTableSafe(reimbursement) {

    // create the row element
    const row = document.createElement('tr');

    // create all the td elements and append them to the row
    const amountData = document.createElement('td');
    amountData.innerText = '$' + reimbursement.amount;
    row.appendChild(amountData);

    const dateSubmitData = document.createElement('td');
    dateSubmitData.innerText = new Date(reimbursement.dateSubmitted).toLocaleDateString("en-US");
    row.appendChild(dateSubmitData);

    const resolveDateData = document.createElement('td');
    //resolveDateData.innerText = new Date(reimbursement.resolveDate).toLocaleDateString("en-US");
    if (!reimbursement.resolveDate) {
        resolveDateData.innerText = "not resolved";
    }
    else {
        resolveDateData.innerText = new Date(reimbursement.resolveDate).toLocaleDateString("en-US");//reimbursement.resolveDate;
    }
    row.appendChild(resolveDateData);

    const memoData = document.createElement('td');
    memoData.innerText = reimbursement.description;
    row.appendChild(memoData);

    const authorData = document.createElement('td');
    authorData.innerText = reimbursement.author;
    row.appendChild(authorData);

    const resolverData = document.createElement('td');
    resolverData.innerText = reimbursement.resolver;
    if (reimbursement.resolver === 0 ) {
        resolverData.innerText = null;
    }
    row.appendChild(resolverData);

    const statusData = document.createElement('td');
    statusData.innerText = reimbursement.status;
    row.appendChild(statusData);

    const typeData = document.createElement('td');
    typeData.innerText = reimbursement.type;
    row.appendChild(typeData);

    if (reimbursement.status === 2) { //pending
        const approveData = document.createElement('button');
        approveData.innerText = 1;
        approveData.setAttribute('onclick', 'updateToApprove(this.value)');
        approveData.setAttribute('value', `${reimbursement.id}`);
        row.appendChild(approveData);

        const denyData = document.createElement('button');
        denyData.innerText = 0;
        denyData.setAttribute('onclick', 'updateToDeny(this.value)');
        denyData.setAttribute('value', `${reimbursement.id}`);
        row.appendChild(denyData);
    }

    // append the row into the table
    document.getElementById('reimbursement-table-body').appendChild(row);
}


//async function refreshTable() {
function refreshTable() {
    // const json = await
    // //string interpoloation fetch?
    // fetch('http://localhost:8080/ERS/reimbursements', {
    //     credentials: 'include',
    //     mode: 'cors',
    // })
    //     .then(res => res.json())
    //     // .then(data => {
    //     //     await data.forEach(addReimbursementToTableSafe)
    //     // })
    //     if (json && json.length) {
    //         json.forEach(addReimbursementToTableSafe);
    //     }
      //  .catch(console.log);

      fetch('http://localhost:8080/ERS/reimbursements')
      .then(res => res.json())
      .then(data => {
          //console.log(currentUser);
          data.forEach(addReimbursementToTableSafe)
      })
      .catch(console.log);
}

function getCurrentUserInfo() {
    fetch('http://localhost:8080/ERS/auth/session-user', {
        credentials: 'include'
    })
    .then(resp => resp.json())
    .then(data => {
       document.getElementById('reimbursement-table-body').innerText = '';
        refreshTable();
        currentUser = data;
        console.log(currentUser.username);
    })
    .catch(err => {
        console.log(err);
        window.location = '/login/login.html';
    })
}

function logout(event) {
    event.preventDefault();
    fetch('http://localhost:8080/ERS/auth/logout', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        mode: 'cors',
        credentials: 'include'
    })
    .then(resp => {
        getCurrentUserInfo();
    })
   // .catch(err => console.log(err));
}

/*async function updateToApprove() {
    let reimbId;
    const result = await
    fetch(`http://localhost:8080/ERS/reimbursements?status=1&resolver=${currentUser.id}&id=${reimbId}`, {
    method: 'PUT',       
    credentials: 'include',
           mode: 'cors',
       });
    if (result.ok) {
       await refreshTable();
    }
}*/

function updateToApprove(reimbId) {
    console.log(currentUser);
    fetch(`http://localhost:8080/ERS/reimbursements?status=1&resolver=${currentUser.id}&id=${reimbId}`,{
        method: 'PUT',
        headers: {
            'content-type': 'application/json'
        },
        mode: 'cors',
        credentials: 'include'
    })
    .then(resp => {
        console.log("approved");
        if (resp.status === 201) {
            document.getElementById('reimbursement-table-body').innerText = '';
            refreshTable();
        }
        else {
            console.log("error with approving reimbursement");
        }
    })
    .catch(err => console.log(err));
}

function updateToDeny(reimbId) {
    console.log(currentUser);
    fetch(`http://localhost:8080/ERS/reimbursements?status=0&resolver=${currentUser.id}&id=${reimbId}`,{
        method: 'PUT',
        headers: {
            'content-type': 'application/json'
        },
        mode: 'cors',
        credentials: 'include',
    })
    .then(resp =>{
        console.log("denied");
        if (resp.status === 201) {
            document.getElementById('reimbursement-table-body').innerText = '';
            refreshTable();
        }
        else {
            console.log("error with denying reimbursement");
        }
    })
    .catch(err => console.log(err));
}

function filterByStatus(event) {
    event.preventDefault();
    const status = document.getElementById('filter-by-status-select').value;
    console.log(status);
    if (status === "total") {
        fetch(`http://localhost:8080/ERS/reimbursements`,{
        method: 'GET',
        headers: {
            'content-type': 'application/json'
        },
        mode: 'cors',
        credentials: 'include',
    })
    .then(resp => resp.json())
    .then(data => {
        let numRows = document.getElementById('reimbursement-table-body').rows.length
            for (let i = numRows-1; i >= 0; i--) {
                document.getElementById('reimbursement-table-body').deleteRow(i)
            }
         data.forEach(addReimbursementToTableSafe);
         //addReimbursementToTableSafe(data);
    })
    .catch(err => console.log(err));
    } else {

    fetch(`http://localhost:8080/ERS/reimbursements/status/${status}`,{
        method: 'GET',
        headers: {
            'content-type': 'application/json'
        },
        mode: 'cors',
        credentials: 'include',
    })
    .then(resp => resp.json())
    .then(data => {
        let numRows = document.getElementById('reimbursement-table-body').rows.length
            for (let i = numRows-1; i >= 0; i--) {
                document.getElementById('reimbursement-table-body').deleteRow(i)
            }
         data.forEach(addReimbursementToTableSafe);
         //addReimbursementToTableSafe(data);
    })
    .catch(err => console.log(err));
    }
}

getCurrentUserInfo();