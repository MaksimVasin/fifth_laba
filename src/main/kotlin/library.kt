class Library : LibraryService {
    private val users: MutableList<User> = mutableListOf()
    private val books: MutableMap<Book, Status> = mutableMapOf()
    private val countBooksPerUser: Int = 3

    override fun findBooks(substring: String): List<Book> {
        return getAllBooks().filter { it.title.contains(substring) }
    }

    override fun findBooks(author: Author): List<Book> {
        return getAllBooks().filter { it.author.contains(author) }
    }

    override fun findBooks(year: Int): List<Book> {
        return getAllBooks().filter { it.year == year }
    }

    override fun findBooks(genre: Genre): List<Book> {
        return getAllBooks().filter { it.genre == genre }
    }

    override fun findBooks(
        substring: String?,
        author: Author?,
        year: Int?,
        genre: Genre?,
        status: Status?,
    ): List<Book> {
        var booksList: List<Book> = getAllBooks()
        // change the list of books, if with each filter
        if (substring != null) {
            booksList = booksList.filter { it.title.contains(substring) }
        }
        if (author != null) {
            booksList = booksList.filter { it.author.contains(author) }
        }
        if (year != null) {
            booksList = booksList.filter { it.year == year }
        }
        if (genre != null) {
            booksList = booksList.filter { it.genre == genre }
        }
        if (status != null) {
            booksList = booksList.filter { books[it] == status }
        }
        return booksList
    }

    override fun getAllBooks(): List<Book> {
        return books.keys.toList()
    }

    override fun getAllUsers(): List<User> {
        return users.toList()
    }

    override fun getAllAvailableBooks(): List<Book> {
        return getAllBooks().filter { books[it] == Status.Available }
    }

    override fun getBookStatus(book: Book): Status {
        return books.getValue(book)
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        return books
    }

    override fun setBookStatus(book: Book, status: Status) {
        if (books.containsKey(book)) {
            books[book] = status
        }
        // did not find the book
        else {
            throw IllegalStateException("Did not find the book") //
        }
    }

    override fun addBook(book: Book, status: Status) {
        // find this book
        if (books.containsKey(book)) {
            throw IllegalStateException("Such a book is already in the library")
        } else {
            books[book] = status
        }
    }

    override fun registerUser(user: User) {
        // user found
        if (users.find { it == user } != null) {
            throw IllegalStateException("Such a user is already in the system")
        }
        // user is not found
        else {
            users.add(user)
        }
    }

    override fun unregisterUser(user: User) {
        // user found
        if (users.find { it == user } != null) {
            users.remove(user)
        }
        // user is not found
        else
            throw IllegalStateException("There is no such user in the system")
    }

    override fun takeBook(user: User, book: Book) {
        // user found
        if (users.find { it == user } != null) {
            if (books.filter { it.value == Status.UsedBy(user) }.size < countBooksPerUser) {
                books[book] = Status.UsedBy(user)
            } else
                throw IllegalStateException("You cannot give out more than $countBooksPerUser books to one user")
        }
        // user is not found
        else {
            throw IllegalStateException("There is no such user in the system")
        }
    }

    override fun returnBook(book: Book) {
        books[book] = Status.Available
    }
}