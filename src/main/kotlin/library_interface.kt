class Book(
    val title: String,
    val author: List<Author>,
    val year: Int,
    val genre: Genre,
)

open class Person(
    val firstName: String,
    val lastName: String,
)

class Author(firstName: String, lastName: String) : Person(firstName, lastName)
class User(firstName: String, lastName: String) : Person(firstName, lastName)

enum class Genre {
    Drama,
    Poems,
    Comedy,
    Stories,
    Novels,
    Tragedy,
}

sealed class Status {
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()
}

interface LibraryService {
    fun findBooks(substring: String): List<Book> // search for books by title
    fun findBooks(author: Author): List<Book> // search for books by author
    fun findBooks(year: Int): List<Book> // search for books by year
    fun findBooks(genre: Genre): List<Book> // search for books by genre
    //fun findBooks(): List<Book> // search for books by different parameters

    fun getAllBooks(): List<Book> // get a list of all books
    fun getAllUsers(): List<User> // get a list of all users
    fun getAllAvailableBooks(): List<Book> // get a list of available users

    fun getBookStatus(book: Book): Status // get the status of one book
    fun getAllBookStatuses(): Map<Book, Status> // get all status

    fun setBookStatus(book: Book, status: Status)

    fun addBook(book: Book, status: Status = Status.Available)

    fun registerUser(user: User)
    fun unregisterUser(user: User)

    fun takeBook(user: User, book: Book) // take book
    fun returnBook(book: Book) // return book
}

