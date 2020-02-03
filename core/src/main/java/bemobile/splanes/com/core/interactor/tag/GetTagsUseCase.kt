package bemobile.splanes.com.core.interactor.tag

import bemobile.splanes.com.core.data.repository.TagRepository
import bemobile.splanes.com.core.domain.Book

class GetTagsUseCase(private val tagRepository: TagRepository) {
    suspend operator fun invoke(book: Book) = tagRepository.getTags(book)
}