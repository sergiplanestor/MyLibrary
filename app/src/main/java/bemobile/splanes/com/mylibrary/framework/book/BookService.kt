package bemobile.splanes.com.mylibrary.framework.book

class BookService(private val restApiRepository: RestApiRepository) : BookDataSource {
    
    private val bookCache: MutableList<Book> = listOf<Book>().toMutableList()
    private var nextUpdate: Long = 0L
    
    override suspend fun add(book: Book) {
    }
    
    override suspend fun fetchAll(): List<Book> {
        if (RestUtils.isDataExpirated<Book>(bookCache, nextUpdate)) {
            // TODO: Rest call
        } else {
            // TODO: Cache 
        }
    }
    
    override suspend fun fetch(id: Int): Book {
    }
    
    override suspend fun remove(book: Book) {
    }
    
    override suspend fun update(book: Book) {
    }
}
