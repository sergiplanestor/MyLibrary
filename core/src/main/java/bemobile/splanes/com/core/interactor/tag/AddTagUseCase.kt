package bemobile.splanes.com.core.interactor.tag

import bemobile.splanes.com.core.data.repository.TagRepository
import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.core.domain.Tag

class AddTagUseCase(private val tagRepository: TagRepository) {
    suspend operator fun invoke(
        
        tags: List<Tag>,
        book: Book,
        onSuccess: (isTagAdded: Boolean) -> Unit,
        onError: (throwable: Throwable) -> Unit
    
    ) = tagRepository.addTag(tags = tags, book = book, onSuccess = onSuccess, onError = onError)
}
