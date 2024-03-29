const addBtn = document.querySelector(".add");

const inputs = document.querySelector(".inputs");

const array = ["ID", "UUID", "NAME", "FIRST_NAME", "LAST_NAME", "LOREM", "ADDRESS", "IPV4ADDRESS",
    "BOOLEAN", "JOB", "EMAIL", "DATE_NOW", "MD5", "NUMBER", "TIME", "UNIVERSITY"];

let elementsByName = document.getElementsByName('fieldName');
console.log(elementsByName)

for (const value of elementsByName.values()) {
    console.log(value.defaultValue);
}

function removeInput(i) {
    console.log(i);
    const removeRow = document.getElementById(i);
    removeRow.remove();
}

function sendRequest() {
    let json = {};
    let fields = [];
    let fieldName = [];
    let fieldType = [];
    let choose_format;
    let file_name;
    let row_count;

    let elementsFieldName = document.getElementsByName('fieldName');
    let elementsFieldType = document.getElementsByName('fieldType');
    let elementsFileName = document.getElementsByName('file_name');
    let elementsRowCount = document.getElementsByName('row_count');
    let elementsChooseFormat = document.getElementsByName('choose_format');

    for (let value of elementsFieldName.values()) {
        fieldName.push(value.value);
    }

    for (let value of elementsFieldType.values()) {
        fieldType.push(value.value);
    }

    for (let value of elementsFileName.values()) {
        file_name = value.value;
    }

    for (let value of elementsRowCount.values()) {
        row_count = value.value;
    }

    for (let value of elementsChooseFormat.values()) {
        choose_format = value.value;
    }

    for (let i = 0; i < fieldName.length; i++) {
        let field = {
            "fieldName": fieldName[i],
            "fieldType": fieldType[i]
        };
        fields.push(field);
    }

    json.fields = fields;
    json.fileType = choose_format;
    json.rowCount = row_count;
    json.tableName = file_name;
    console.log(json);

    let local = 'http://localhost:8080/';
    let url = 'https://mockdatagenerator-production.up.railway.app/';
    let post = 'data/generate';
    let get = 'data/get/';
    let urlLocal = local.concat(post);
    let downloadUrl = local.concat(get);
    let getUrl;

    sendData(urlLocal, json).then((data) => {
        console.log(data);
        getUrl = downloadUrl.concat(data.data);
        console.log(getUrl);

        const fileName = file_name.concat('.' + choose_format.toString().toLowerCase());
        console.log(fileName);
        const a = document.createElement("a");
        a.download = fileName;
        a.href = getUrl;
        a.click();

        // Soon!
        // downloadFile(getUrl, fileName);
    });
}

async function sendData(url = '', json = {}) {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(json)
    });
    return response.json();
}

function downloadFile(url, fileName) {
    fetch(url, {method: 'get', mode: 'no-cors', referrerPolicy: 'no-referrer'})
        .then(res => res.blob())
        .then(data => {
            let objectURL = URL.createObjectURL(data);
            const a = document.createElement("a");
            a.download = fileName;
            a.href = objectURL;
            a.click();
        });
}

function addInput() {
    let id = inputs.lastElementChild.id;
    // console.log(id);

    let elementsByName = document.getElementsByName('fieldName');
    console.log(elementsByName)

    const field = document.createElement("input");
    field.type = "text";
    field.name = "fieldName";
    field.className = "new-input";

    const select = document.createElement("select");
    select.name = "fieldType";
    select.className = "new-input";

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















