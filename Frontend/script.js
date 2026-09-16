// Section switch karne ke liye (Add/View/Update/Delete tabs)
function showSection(sectionId) {
    document.querySelectorAll('.section').forEach(sec => {
        sec.classList.remove('active');
    });
    document.getElementById(sectionId).classList.add('active');
}

// Abhi ke liye DUMMY data (baad mein Java backend se aayega)
let dummyBooks = [
    { id: 1, name: "Harry Potter", author: "J.K. Rowling", quantity: 5 },
    { id: 2, name: "Atomic Habits", author: "James Clear", quantity: 3 }
];

function renderTable() {
    const tbody = document.querySelector('#booksTable tbody');
    tbody.innerHTML = '';
    dummyBooks.forEach(book => {
        const row = `<tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.quantity}</td>
        </tr>`;
        tbody.innerHTML += row;
    });
}

// ADD BOOK (abhi sirf dummy array mein add hoga)
document.getElementById('addForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const name = document.getElementById('addBookName').value;
    const author = document.getElementById('addAuthor').value;
    const quantity = document.getElementById('addQuantity').value;

    const newBook = {
        id: dummyBooks.length + 1,
        name: name,
        author: author,
        quantity: quantity
    };
    dummyBooks.push(newBook);

    alert("Book added (abhi sirf dummy data mein, database se connect nahi hai)!");
    this.reset();
    renderTable();
});

// UPDATE BOOK (dummy)
document.getElementById('updateForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const id = parseInt(document.getElementById('updateId').value);
    const book = dummyBooks.find(b => b.id === id);

    if (book) {
        book.name = document.getElementById('updateBookName').value;
        book.author = document.getElementById('updateAuthor').value;
        book.quantity = document.getElementById('updateQuantity').value;
        alert("Book updated (dummy data mein)!");
    } else {
        alert("Book ID nahi mila!");
    }
    this.reset();
    renderTable();
});

// DELETE BOOK (dummy)
document.getElementById('deleteForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const id = parseInt(document.getElementById('deleteId').value);
    dummyBooks = dummyBooks.filter(b => b.id !== id);
    alert("Book deleted (dummy data mein)!");
    this.reset();
    renderTable();
});

// Page load hote hi table dikhao
renderTable();