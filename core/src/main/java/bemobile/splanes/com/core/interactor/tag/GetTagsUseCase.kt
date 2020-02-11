package bemobile.splanes.com.core.interactor.tag

import bemobile.splanes.com.core.data.repository.TagRepository
import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.core.domain.Tag

class GetTagsUseCase(private val tagRepository: TagRepository) {
    suspend operator fun invoke(

        book: Book,
        onSuccess: (tags: List<Tag>) -> Unit,
        onError: (throwable: Throwable) -> Unit
    
    ) = tagRepository.getTags(book = book, onSuccess = onSuccess, onError = onError)
}
