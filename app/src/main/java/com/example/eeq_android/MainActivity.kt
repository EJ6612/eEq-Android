package com.example.eeq_android

//import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }*/


        recyclerView = findViewById(R.id.home_button_list)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val data = listOf(
            HomeButtonsItem(R.drawable.area2d_nav, "2D Shapes", 1),
            HomeButtonsItem(R.drawable.basic_nav, "Basic Functions", 2),
            HomeButtonsItem(R.drawable.dice_nav, "Dice Rolling", 3),
            HomeButtonsItem(R.drawable.volume_nav, "3D Objects", 4)
        )

        val adapter = HomeButtonsAdapter(this, data)
        recyclerView.adapter = adapter

    }

    fun closePage(view: View) {
        val homePage = findViewById<LinearLayout>(R.id.homePage)
        when (view.id) {
            R.id.back_area2d_button -> {
                val page = findViewById<LinearLayout>(R.id.shapes2d_insert)
                //val fadeOut = ObjectAnimator.ofFloat(page, View.ALPHA, 1f, 0f)
                //fadeOut.duration = 500
                //fadeOut.start()
                page.visibility = View.GONE
                homePage.visibility = View.VISIBLE
            }
            R.id.back_basic_button -> {
                val page = findViewById<LinearLayout>(R.id.basic_insert)
                page.visibility = View.GONE
                homePage.visibility = View.VISIBLE
            }
            R.id.back_dice_button -> {
                val page = findViewById<LinearLayout>(R.id.dice_insert)
                page.visibility = View.GONE
                homePage.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        fun openPage(buttonId: Int, activity: Activity) {
            val homePage = activity.findViewById<LinearLayout>(R.id.homePage)

            when (buttonId) {
                1 -> {
                    val page = activity.findViewById<LinearLayout>(R.id.shapes2d_insert)
                    page.visibility = View.VISIBLE
                    homePage.visibility = View.GONE
                }

                2 -> {
                    val page = activity.findViewById<LinearLayout>(R.id.basic_insert)
                    page.visibility = View.VISIBLE
                    homePage.visibility = View.GONE
                }

                3 -> {
                    val page = activity.findViewById<LinearLayout>(R.id.dice_insert)
                    page.visibility = View.VISIBLE
                    homePage.visibility = View.GONE
                }

            }
        }
    }

}

data class HomeButtonsItem(val imageResourceId: Int, val text: String, val buttonId: Int)

class HomeButtonsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.home_button_image)
    val textView: TextView = itemView.findViewById(R.id.home_button_text)

}

class HomeButtonsAdapter(private val activity: Activity, private val data: List<HomeButtonsItem>) : RecyclerView.Adapter<HomeButtonsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeButtonsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.homebutton, parent, false)
        return HomeButtonsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeButtonsViewHolder, position: Int) {
        val item = data[position]
        holder.imageView.setImageResource(item.imageResourceId)
        holder.textView.text = item.text

        holder.itemView.setOnClickListener {
            //val pageId = "page_" + item.buttonId

            MainActivity.openPage(item.buttonId, activity)
        }
    }

    override fun getItemCount() = data.size
}
