let currentUser;
//get all rows on page load from db, then append row to table

function addReimbursementToTableSafe(reimbursement) {

    // create the row element
    const row = document.createElement('tr');

    // create all the td elements and append them to the row
    const amountData = document.createElement('td');
    amountData.innerText = reimbursement.amount;
    row.appendChild(amountData);

    const dateSubmitData = document.createElement('td'); //get currDateTime
    dateSubmitData.innerText = reimbursement.dateSubmitted;//new Date(reimbursement.dateSubmitted);
    row.appendChild(dateSubmitData);

    const resolveDateData = document.createElement('td');
    resolveDateData.innerText = reimbursement.resolveDate;//new Date(reimbursement.resolveDateData);
    row.appendChild(resolveDateData);

    const memoData = document.createElement('td');
    memoData.innerText = reimbursement.description;
    row.appendChild(memoData);

    const authorData = document.createElement('td');
    authorData.innerText = reimbursement.author; //id
    row.appendChild(authorData);

    const resolverData = document.createElement('td');
    resolverData.innerText = reimbursement.resolver; //id
    if (reimbursement.resolver === 0 ) {
        resolverData.innerText = null;
    }
    row.appendChild(resolverData); //maybe don't need?

    const statusData = document.createElement('td');
    statusData.innerText = reimbursement.status; //if admin select
    row.appendChild(statusData); //if status === '2' set innertext 'pending'

    const typeData = document.createElement('td'); //select
    typeData.innerText = reimbursement.type;
    row.appendChild(typeData);

    if (reimbursement.status === 2) { //pending
        const approveData = document.createElement('button');
        approveData.innerText = 1;
        //approveData.onclick
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
    //console.log(reimbursement);
}


async function refreshTable() {
    const json = await
    //string interpoloation fetch?
    fetch('http://localhost:8080/ERS/reimbursements', {
        credentials: 'include',
        mode: 'cors',
    })
        .then(res => res.json())
        // .then(data => {
        //     await data.forEach(addReimbursementToTableSafe)
        // })
        if (json && json.length) {
            json.forEach(addReimbursementToTableSafe);
        }
      //  .catch(console.log);
}

function getCurrentUserInfo() {
     fetch('http://localhost:8080/ERS/auth/session-user', {
         credentials: 'include',
         mode: 'cors'
     })
     .then(resp => resp.json())
     .then(data => {
         //console.log(data.username);
      //   document.getElementById('users-name').innerText = data.username
         refreshTable();
         currentUser = data;
         //console.log(currentUser.username);
     })
     .catch(err => {
         console.log(err);
         //document.getElementById('error-id').innerText = err;
         window.location = '/login/login.html'; ///login/login.html
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
}

async function updateToApprove() {
    let reimbId;
    const result = await
    fetch(`http://localhost:8080/ERS/reimbursements?status=2&resolver=${currentUser.id}&id=${reimbId}`, {
    method: 'PUT',       
    credentials: 'include',
           mode: 'cors',
       });
    if (result.ok) {
       await refreshTable();
    }
}

/*function updateToApprove(reimbId) {
    fetch(`http://localhost:8080/ERS/reimbursements?status=2&resolver=${currentUser.id}&id=${reimbId}`,{ //update
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
            refreshTable();
        }
        else {
            console.log("error with approving reimbursement");
        }
    })
    .catch(err => console.log(err));
}*/

function updateToDeny(reimbId) {
    fetch(`http://localhost:8080/ERS/reimbursements?status=3&resolver=${currentUser.id}&id=${reimbId}`,{
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
    console.log("please");
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
//refreshTable();