function findReimbursement() {
    const id = document.getElementById('reimbursement-id-input').value;
    fetch(`https://ers.co/api/v2/reimbursement/${id}`)
        .then(resp => resp.json())
        .then(addReimbursementToList)
        .catch(err => {
            $('#error-toast').toast('show');
        });

}

function addReimbursementToList(reimbursement) {
    const list = document.getElementById('reimbursement-list');

    const reimbursementItem = document.createElement('li');
    reimbursementItem.innerText = reimbursement.amount;
    list.appendChild(reimbursementItem);
}