import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ismin.android.FavoriteAdapter
import com.ismin.android.Musee
import com.ismin.android.MuseeAdapter
import com.ismin.android.R

class FavoriteMuseumsActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        val favoris = intent.getSerializableExtra("favoris")
        recyclerView = findViewById<RecyclerView>(R.id.f_museum_favorites_rcv_museums)
        adapter = FavoriteAdapter(favoris as ArrayList<Musee>)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    fun stopActivityAndReturnResult() {

        val returnIntent = Intent()

        finish()
    }

}