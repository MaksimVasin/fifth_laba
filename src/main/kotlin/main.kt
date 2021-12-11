fun main() {
    val library = Library()

    val usersList: MutableList<User> = mutableListOf(
        User("u1FirstName", "u1LastName"),
        User("u2FirstName", "u2LastName")
    )
    usersList.forEach { library.registerUser(it) }

    val author1 = Author("a1FirstName", "a1FirstName")

    val booksList: MutableMap<Book, Status> = mutableMapOf(
        Book("b1Title", listOf(author1), 1987, Genre.Drama) to Status.Available,
        Book("b2Title", listOf(author1), 1900, Genre.Comedy) to Status.ComingSoon
    )
    booksList.forEach { library.addBook(it.key, it.value) }

    library.getAllBooks().forEach { print(it.title + " ") }

    library.findBooks(2000).forEach { library.setBookStatus(it, Status.ComingSoon) }

    (library.getAllUsers().forEach { print(it.firstName + " " + it.lastName + " | ") })
}