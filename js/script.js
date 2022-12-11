const addBtn = document.querySelector(".add");

const inputs = document.querySelector(".inputs");

const array = ["ID", "UUID", "NAME", "FIRST_NAME", "LAST_NAME", "LOREM", "ADDRESS", "IPV4ADDRESS"];

let elementsByName = document.getElementsByName('fieldName');
console.log(elementsByName)

function removeInput(i) {
    console.log(i);
    const removeRow = document.getElementById(i);
    removeRow.remove();
}

function addInput() {
    let id = inputs.lastElementChild.id;
    console.log(id);

    const field = document.createElement("input");
    field.type = "text";
    field.name = "fieldName";
    field.className = "form-control";

    const select = document.createElement("select");
    select.name = "fieldType";
    select.className = "form-select";

    for (let i = 0; i < array.length; i++) {
        let option = document.createElement("option");
        option.value = array[i];
        option.text = array[i];
        select.appendChild(option);
    }

    const fieldNameDiv = document.createElement("div");
    fieldNameDiv.className = "col-4";
    fieldNameDiv.appendChild(field);

    const fieldTypeDiv = document.createElement("div");
    fieldTypeDiv.className = "col-4";
    fieldTypeDiv.appendChild(select);

    let newId = "row-" + (parseInt(id.substring(id.length - 1)) + 1);

    const aBtn = document.createElement("a");
    aBtn.text = "Remove";
    aBtn.href = "#";
    aBtn.className = "btn btn-dark remove";
    aBtn.setAttribute("onclick", "removeInput('" + newId + "')");

    aBtn.addEventListener("click", removeInput);

    const removeDiv = document.createElement("div");
    removeDiv.className = "col-4";
    removeDiv.appendChild(aBtn);

    const row = document.createElement("div");
    row.className = "row mb-3";
    row.id = newId;
    row.appendChild(fieldNameDiv);
    row.appendChild(fieldTypeDiv);
    row.appendChild(removeDiv);
    inputs.appendChild(row);
}

addBtn.addEventListener("click", addInput);