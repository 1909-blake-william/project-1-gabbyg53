let currentUser;
function newReimbursementSubmit(event) {
    event.preventDefault(); // stop page from refreshing
    console.log('submitted');
    
    const reimbs = getReimbursementFromInputs();

    fetch('http://localhost:8080/ERS/reimbursements', {
        method: 'POST',
        body: JSON.stringify(reimbs),
        headers: {
            'content-type': 'application/json'
        }
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
    amountData.innerText = reimbursement.amount;
    row.appendChild(amountData);

    const dateSubmitData = document.createElement('td');
    dateSubmitData.innerText = reimbursement.dateSubmitted;
    row.appendChild(dateSubmitData);

    const resolveDateData = document.createElement('td');
    resolveDateData.innerText = reimbursement.resolveDateData;
    row.appendChild(resolveDateData);

    const memoData = document.createElement('td');
    memoData.innerText = reimbursement.memoData;
    row.appendChild(memoData);

    const authorData = document.createElement('td');
    authorData.innerText = reimbursement.author.username; //id
    row.appendChild(authorData);

    const resolverData = document.createElement('td');
    resolverData.innerText = reimbursement.resolver.username; //id
    row.appendChild(resolverData);

    const statusData = document.createElement('td');
    statusData.innerText = reimbursement.statusData; //if admin select
    row.appendChild(statusData);

    const typeData = document.createElement('td'); //select
    typeData.innerText = reimbursement.typeData;
    row.appendChild(typeData);

    // append the row into the table
    document.getElementById('reimb-table-body').appendChild(row);
}

function getReimbFromInputs() {
    const reimbAmount = document.getElementById('reimbursement-amount-input').value;
    const reimbDateSubmitted = document.getElementById('reimbursement-dateSumbit-input').value;
    const reimbResolveDate = document.getElementById('reimbursement-resolveDate-input').value;
    const reimbMemo = document.getElementById('reimbursement-memo-input').value;
    //const reimbAuthor = document.getElementById('reimbursement-author-input').value;
    //const reimbResolver = document.getElementById('reimbursement-resolver-input').value;
    const reimbStatus = document.getElementById('reimbursement-status-select').value;
    const reimbType = document.getElementById('reimbursement-type-select').value;

    const reimbursement = {
        amount: reimbAmount,
        dateSumbitted: reimbDateSubmitted,
        resolveDate: reimbResolveDate,
        memo: reimbMemo,
        status: {
            statusId: 5, // should probably find a way to get the correct id
            statusName: reimbStatus
        },
        type: {
            typeId: 5, // should probably find a way to get the correct id
            typeName: reimbType
        },
      //  if (currentUser.getCurrentUserInfo.) author: currentUser
    }
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
        document.getElementById('users-name').innerText = data.username
        refreshTable();
        currentUser = data;
    })
    .catch(err => {
        window.location = '/login/login.html';
    })
}

getCurrentUserInfo();