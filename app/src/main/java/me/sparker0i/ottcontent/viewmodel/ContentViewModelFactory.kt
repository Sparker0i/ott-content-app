package me.sparker0i.ottcontent.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.sparker0i.ottcontent.repository.ContentRepository

@Suppress("UNCHECKED_CAST")
class ContentViewModelFactory(
    private val contentRepository: ContentRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContentViewModel(contentRepository) as T
    }
}