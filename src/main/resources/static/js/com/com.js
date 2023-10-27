function test(){
    alert();
}

function readExcel() {
  const input = event.target;
  const reader = new FileReader();
  const ulNode = document.getElementById("list");

  // 이전에 표시된 데이터 삭제
  ulNode.innerHTML = "";
  // 출력
  document.getElementById("displayExcelTable").style.display ='block';	

  reader.onload = function () {
    const data = reader.result;
    const workBook = XLSX.read(data, { type: 'binary' });

    workBook.SheetNames.forEach(function (sheetName) {
      const rows = XLSX.utils.sheet_to_json(workBook.Sheets[sheetName]);
      const headers = Object.keys(rows[0]);

      const table = generateTable(headers, rows);
      ulNode.insertAdjacentHTML("afterbegin", table);
    });
  };

  reader.readAsBinaryString(input.files[0]);
}

function generateTable(headers, rows) {
  let tableHTML = "<table id='scrolltable' name='scrolltable' class='table table-hover scrolltable'>";
  tableHTML += "<thead><tr>";

  headers.forEach((header) => {
    tableHTML += `<th name='${header}' id='${header}'>${header}</th>`;
  });

  tableHTML += "</tr></thead>";
  tableHTML += "<tbody>";

  rows.forEach((row) => {
    tableHTML += "<tr>";

    headers.forEach((header) => {
      tableHTML += `<td name='breakdown_${header}' id='breakdown_${header}'>${row[header]}</td>`;
    });

    tableHTML += "</tr>";
  });

  tableHTML += "</tbody>";
  tableHTML += "</table>";

  return tableHTML;
}

function dataSend(){
  // HTML 테이블 요소 가져오기
var table = document.getElementById('scrolltable');

// 테이블 헤더 가져오기
var headers = ['agncy', 'date', 'briefs', 'detail', 'amnt'];

// 테이블 데이터를 JavaScript 객체로 변환
var tableData = [];
for (var i = 1; i < table.rows.length; i++) {
    var rowData = {};
    for (var j = 0; j < table.rows[i].cells.length; j++) {
        rowData[headers[j]] = table.rows[i].cells[j].textContent;
    }
    tableData.push(rowData);
}

// JavaScript 객체를 JSON 문자열로 변환
var jsonData = JSON.stringify(tableData);
console.log(jsonData);
   // 서버로 데이터 전송
   
   fetch("/views/board/sendDataToServer.do", {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
  },
  body: jsonData,
})
  .then((response) => response.json())
  .then((data) => console.log(data));
}