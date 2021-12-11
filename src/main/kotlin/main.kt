fun main() {
    val library = Library()

    ////////////////// Creation of initial data //////////////////
    val usersList: MutableList<User> = mutableListOf(
        User("u1FirstName", "u1LastName"),
        User("u2FirstName", "u2LastName"),
        User("u3FirstName", "u3LastName"),
        User("u4FirstName", "u4LastName"),
        User("u5FirstName", "u5LastName"),
    )
    usersList.forEach { library.registerUser(it) }

    val author1 = Author("a1FirstName", "a1FirstName")
    val author2 = Author("a2FirstName", "a2FirstName")
    val author3 = Author("a3FirstName", "a3FirstName")

    val booksList: MutableMap<Book, Status> = mutableMapOf(
        Book("b1Title", listOf(author1), 1987, Genre.Drama) to Status.Available,
        Book("b2Title", listOf(author1, author2), 2000, Genre.Poems) to Status.Available,
        Book("b3Title", listOf(author3), 1987, Genre.Comedy) to Status.Available,
        Book("b4Title", listOf(author1), 1900, Genre.Comedy) to Status.ComingSoon,
        Book("b5Title", listOf(author1, author2, author3), 1987, Genre.Tragedy) to Status.Available,
        Book("b6Title", listOf(author2), 1203, Genre.Tragedy) to Status.Restoration,
        Book("b7Title", listOf(author2), 1203, Genre.Tragedy) to Status.Restoration,
    )
    booksList.forEach { library.addBook(it.key, it.value) }

    ////////////////// Get all books //////////////////
    println("Books:")
    library.getAllBooks().forEach { print(it.title + " ") }

    ////////////////// Get all available books //////////////////
    println("\n\nAvailable books:")
    library.getAllAvailableBooks().forEach { print(it.title + " ") }

    ////////////////// Get status //////////////////
    println("\n\nStatus: ")
    print("${library.getAllBooks()[0].title} = ${library.getBookStatus(library.getAllBooks()[0])}")

    ////////////////// Get all statuses //////////////////
    println("\n\nAll statuses: ")
    library.getAllBookStatuses().forEach { println("${it.key.title} = ${it.value}") }

    ////////////////// Set status book(s) //////////////////
    println("\nInitial status: ")
    library.findBooks(2000).forEach { print("${it.title} = ${library.getBookStatus(it)}") }
    library.findBooks(2000).forEach { library.setBookStatus(it, Status.ComingSoon) }
    println("\nChanged status: ")
    library.findBooks(2000).forEach { print("${it.title} = ${library.getBookStatus(it)}") }

    ////////////////// Unregister user //////////////////
    println("\n\nInitial users: ")
    (library.getAllUsers().forEach { print(it.firstName + " " + it.lastName + " | ") })
    library.unregisterUser(usersList[4])
    println("\nUnregister user: ")
    (library.getAllUsers().forEach { print(it.firstName + " " + it.lastName + " | ") })

    ////////////////// Take book //////////////////
    library.findBooks(1203).forEach { library.setBookStatus(it, Status.Available) }
    println("\n\nInitial statuses books: ")
    library.getAllBookStatuses().forEach { println("${it.key.title} = ${it.value}") }
    try {
        library.takeBook(usersList[0], library.getAllAvailableBooks()[0])
        library.takeBook(usersList[0], library.getAllAvailableBooks()[0])
        library.takeBook(usersList[0], library.getAllAvailableBooks()[0])
        library.takeBook(usersList[0], library.getAllAvailableBooks()[0])
    } catch (e: Exception) {
        println(e.message)
    }
    println("After take statuses books: ")
    library.getAllBookStatuses().forEach { println("${it.key.title} = ${it.value}") }

    ////////////////// Return book //////////////////
    println("\nReturn book: ")
    library.returnBook(library.getAllBooks()[0])
    println("After return statuses books: ")
    library.getAllBookStatuses().forEach { println("${it.key.title} = ${it.value}") }

    ///////////////// Find Book ////////////////////
    println("\nFind books by years : ")
    library.findBooks(1987).forEach { println("${it.title} ${it.year}") }

    println("\nFind books by genre : ")
    library.findBooks(Genre.Tragedy).forEach { println("${it.title} ${it.genre}") }

    println("\nFind books by author : ")
    library.findBooks(author1).forEach { println("${it.title} ${it.author}") }

    println("\nFind books by title : ")
    library.findBooks("b1Title").forEach { println(it.title) }

    println("\nFind books by status : ")
    library.findBooks(null, null, null, null, Status.Available).forEach { println(it.title) }

    println("\nFind books by year and genre : ")
    library.findBooks(null, null, 1987, Genre.Tragedy, null).forEach { println(it.title) }
}