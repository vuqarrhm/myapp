package com.example.lafyuu.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lafyuu.R
import com.example.lafyuu.adapter.BookmarkAdapter
import com.example.lafyuu.databinding.FragmentBookmarkBinding
import com.example.lafyuu.databinding.ItemCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class BookmarkFragment : Fragment() {
    lateinit var binding: FragmentBookmarkBinding
    private lateinit var bookmarkAdapter: BookmarkAdapter
    private val bookmarkViewModel: BookmarkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookmarkAdapter = BookmarkAdapter { bookmark ->
            bookmarkViewModel.deleteBookmark(bookmark.id)
        }

        binding.rvbookmark.adapter = bookmarkAdapter
        binding.rvbookmark.layoutManager = LinearLayoutManager(requireContext())

        bookmarkViewModel.bookmarks().observe(viewLifecycleOwner) { bookmarks ->
            bookmarkAdapter.updateList(bookmarks)
        }
    }
}
