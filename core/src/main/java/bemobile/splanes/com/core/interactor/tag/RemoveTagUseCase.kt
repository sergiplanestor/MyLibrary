package bemobile.splanes.com.core.interactor.tag

import bemobile.splanes.com.core.data.repository.TagRepository
import bemobile.splanes.com.core.domain.Book
import bemobile.splanes.com.core.domain.Tag

class RemoveTagUseCase(private val tagRepository: TagRepository) {
    suspend operator fun invoke(tag: Tag, book: Book) = tagRepository.removeTag(tag, book)
}