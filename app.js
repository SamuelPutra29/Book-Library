const baseUrl = "http://localhost:8080/books"; // Your backend API URL

// Fetch and display all books
async function fetchBooks() {
    const response = await fetch(baseUrl);
    const books = await response.json();
    const bookList = document.getElementById("book-list");

    // Clear existing list
    bookList.innerHTML = "";

    books.forEach(book => {
        const bookItem = document.createElement("div");
        bookItem.className = "book-item";
        bookItem.innerHTML = `
            <span><strong>${book.title}</strong> by ${book.author} (${book.year})</span>
            <button onclick="deleteBook(${book.id})">Delete</button>
        `;
        bookList.appendChild(bookItem);
    });
}

// Add a new book
async function addBook() {
    const title = document.getElementById("title").value;
    const author = document.getElementById("author").value;
    const year = parseInt(document.getElementById("year").value);

    if (!title || !author || !year) {
        alert("Please fill in all fields!");
        return;
    }

    const newBook = { title, author, year };

    const response = await fetch(baseUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newBook),
    });

    if (response.ok) {
        alert("Book added successfully!");
        fetchBooks();
    } else {
        alert("Failed to add book.");
    }
}

// Delete a book
async function deleteBook(bookId) {
    const response = await fetch(`${baseUrl}/${bookId}`, {
        method: "DELETE",
    });

    if (response.ok) {
        alert("Book deleted successfully!");
        fetchBooks();
    } else {
        alert("Failed to delete book.");
    }
}

// Event Listener for Add Book Button
document.getElementById("add-book").addEventListener("click", addBook);

// Initial fetch of books
fetchBooks();
