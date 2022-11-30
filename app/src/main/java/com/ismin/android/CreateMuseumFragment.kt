package com.ismin.android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment


class CreateMuseumFragment : Fragment() {

    private var listener: MuseumCreator? = null
    lateinit var edtNom: EditText
    lateinit var edtLatitude: EditText
    lateinit var edtLongitude: EditText
    lateinit var edtRegion: EditText
    lateinit var edtLieu: EditText
    lateinit var edtTelephone: EditText
    lateinit var edtDepartement: EditText
    lateinit var edtDate: EditText
    lateinit var edtURL: EditText
    lateinit var edtID: EditText
    lateinit var edtAdresse: EditText
    lateinit var edtFavori: EditText
    lateinit var btnSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_create_museum, container, false)

        edtNom = rootView.findViewById(R.id.f_create_book_edt_nom)
        edtLatitude = rootView.findViewById(R.id.f_create_book_edt_latitude)
        edtLongitude = rootView.findViewById(R.id.f_create_book_edt_longitude)
        edtRegion = rootView.findViewById(R.id.f_create_book_edt_region)
        edtLieu = rootView.findViewById(R.id.f_create_book_edt_lieu)
        edtTelephone = rootView.findViewById(R.id.f_create_book_edt_telephone)
        edtDepartement = rootView.findViewById(R.id.f_create_book_edt_departement)
        edtDate = rootView.findViewById(R.id.f_create_book_edt_dateappellation)
        edtURL = rootView.findViewById(R.id.f_create_book_edt_url)
        edtID = rootView.findViewById(R.id.f_create_book_edt_id)
        edtAdresse = rootView.findViewById(R.id.f_create_book_edt_adresse)
        edtFavori = rootView.findViewById(R.id.f_create_book_edt_favori)

        btnSave = rootView.findViewById(R.id.f_create_book_btn_save)

        btnSave.setOnClickListener {
            /**val title = edtTitle.text.toString()
            val author = edtAuthor.text.toString()
            val date = edtDate.text.toString()
            val book = Musee(title, author, date)*/
            //listener?.onMuseumCreated(book)
        }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MuseumCreator) {
            listener = context
        } else {
            throw RuntimeException("$context must implement MuseumCreator")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CreateMuseumFragment()
    }
}