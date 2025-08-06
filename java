<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>DHR Resource Tracker</title>

  <style>
    body {
      font-family: Arial, sans-serif;
      background: #f3f3f3;
      margin: 0;
      padding: 20px;
    }
    .container {
      max-width: 800px;
      margin: auto;
      background: #fff;
      padding: 20px;
      border-radius: 10px;
    }
    h1, h2 {
      color: #2d2d2d;
    }
    input[type="text"], input[type="file"] {
      width: 100%;
      padding: 8px;
      margin: 10px 0;
    }
    button {
      padding: 10px 15px;
      background: #1976d2;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    button:hover {
      background: #145ea8;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 15px;
    }
    table, th, td {
      border: 1px solid #ccc;
    }
    th, td {
      padding: 10px;
      text-align: left;
    }
    .hidden {
      display: none;
    }
  </style>
</head>
<body>
  <div class="container">
    <h1>DHR Resource Tracker</h1>

    <section>
      <label for="receiptInput">📸 Upload Receipt:</label>
      <input type="file" id="receiptInput" accept="image/*" />
      <button onclick="simulateOCR()">Scan Receipt</button>
    </section>

    <section id="scanResults" class="hidden">
      <h2>Scanned Items</h2>
      <ul id="itemList"></ul>

      <label for="childName">Assign to Child:</label>
      <input type="text" id="childName" placeholder="e.g., Sarah Johnson"/>
      <button onclick="assignToChild()">Assign</button>
    </section>

    <section id="history">
      <h2>📊 Expense History</h2>
      <table>
        <thead>
          <tr><th>Item</th><th>Amount</th><th>Child</th><th>Flag</th></tr>
        </thead>
        <tbody id="historyTable"></tbody>
      </table>
    </section>
  </div>

  <script>
    const dummyItems = [
      { item: 'Baby Formula', amount: 25.50 },
      { item: 'Diapers', amount: 18.75 },
      { item: 'Milk', amount: 3.20 },
      { item: 'Candy', amount: 5.00 }
    ];
    let currentScannedItems = [];

    function simulateOCR() {
      currentScannedItems = dummyItems;
      document.getElementById('itemList').innerHTML =
        dummyItems.map(item =>
          `<li>${item.item} - $${item.amount.toFixed(2)}</li>`
        ).join('');
      document.getElementById('scanResults').classList.remove('hidden');
    }

    function assignToChild() {
      const childName = document.getElementById('childName').value.trim();
      if (!childName) return alert('Please enter a child name');
      const tableBody = document.getElementById('historyTable');
      currentScannedItems.forEach(item => {
        const isSuspicious =
          item.item.toLowerCase().includes('candy') || item.amount > 50;
        const flag = isSuspicious ? '⚠️ Check Item' : '✔️ Valid';
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${item.item}</td>
          <td>$${item.amount.toFixed(2)}</td>
          <td>${childName}</td>
          <td>${flag}</td>
        `;
        tableBody.appendChild(row);
      });
      document.getElementById('scanResults').classList.add('hidden');
      document.getElementById('childName').value = '';
    }
  </script>
</body>
</html>
