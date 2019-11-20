let currentUser;
//get all rows on page load from db, then append row to table

function newReimbursementSubmit(event) {
    event.preventDefault(); // stop page from refreshing
    console.log('submitted');
    
    const reimbs = getReimbFromInputs();
    console.log(reimbs);

    fetch('http://localhost:8080/ERS/reimbursements/view-reimbursement', {
        method: 'POST',
        body: JSON.stringify(reimbs),
        headers: {
            'content-type': 'application/json'
        },
        mode: 'cors',
    })
    .then(res => res.json())
    .then(data => {
        addReimbursementToTableSafe(data);
        console.log(data);
    })
    .catch(err => console.log(err));
}

function addReimbursementToTableSafe(reimbursement) {

    // create the row element
    const row = document.createElement('tr');

    // create all the td elements and append them to the row
    const amountData = document.createElement('td');
    amountData.innerText = '$' + reimbursement.amount;
    row.appendChild(amountData);

    const dateSubmitData = document.createElement('td');
    dateSubmitData.innerText = new Date(reimbursement.dateSubmitted).toLocaleDateString("en-US");
    console.log(dateSubmitData);
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
    console.log(resolveDateData);

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

    // append the row into the table
    if (reimbursement.author === currentUser.id) {
        document.getElementById('reimbursement-table-body').appendChild(row);
    }
    console.log(reimbursement);
}

function getReimbFromInputs() {
    const reimbAmount = document.getElementById('reimbursement-amount-input').value;
    const reimbMemo = document.getElementById('reimbursement-memo-input').value;
    const reimbAuthor = currentUser.id;
    const reimbType = document.getElementById('reimbursement-type-select').value;
    let typeId;
    if (reimbType === 'lodging') typeId = 1;
    else if (reimbType === 'travel') typeId = 2;
    else if (reimbType === 'food') typeId = 3;
    else if (reimbType === 'other') typeId = 4;

    const reimbursement = {
        amount: reimbAmount,
        //dateSubmitted: new Date(),
       // resolveDate: newDate(),// null,
        description: reimbMemo,
        author: reimbAuthor,
        resolver: null,
        status: 2,
        type: typeId
    }
    //console.log(reimbursement);
    return reimbursement;
}


function refreshTable() {
    fetch('http://localhost:8080/ERS/reimbursements')
        .then(res => res.json())
        .then(data => {
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
        credentials: 'include',
    })
    .then(resp => {
        getCurrentUserInfo();
    })
}
getCurrentUserInfo();
