package com.codexo.notes.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codexo.notes.R
import com.codexo.notes.data.Note
import com.codexo.notes.databinding.FragmentDetailBinding
import com.codexo.notes.ui.SharedViewModel
import com.codexo.notes.utils.HideKeyboard.Companion.hideKeyboard
import com.google.android.material.snackbar.Snackbar

class AddFragment : Fragment(R.layout.fragment_detail) {
    private val viewModel: SharedViewModel by viewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        binding.apply { }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_save) {
            addNewNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addNewNote() {
        val title = binding?.etAddTitle?.text.toString()
        val note = binding?.etAddNote?.text.toString()
        if (title.isEmpty() && note.isEmpty()) {
            val snackbar = Snackbar.make(requireView(), "Please fill out one of the fields", Snackbar.LENGTH_LONG)
            snackbar.show()
        } else {
            val newNote = Note(title = title, note = note)
            viewModel.insertNote(newNote)
            val snackbar = Snackbar.make(requireView(), "'$title' saved!", Snackbar.LENGTH_LONG)
            snackbar.show()
            findNavController().navigate(R.id.action_addFragment_to_notesFragment)
        }
        hideKeyboard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}