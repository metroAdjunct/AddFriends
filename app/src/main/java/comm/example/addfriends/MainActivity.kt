package comm.example.addfriends

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val linearLayout: LinearLayout
        get() = findViewById(R.id.linearLayout)
    var flag = false
    var idCount = 0
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val data = activityResult.data
            val fName = data?.getStringExtra("first_name") ?: ""
            val lName = data?.getStringExtra("last_name") ?: ""
            val phoneNumber = data?.getStringExtra("phone_number") ?: ""
            addFriendView(Friend(lName, fName, phoneNumber))
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FFF", "============= ENTER ONCREATE =========")
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun startSecond(v : View) {
        startForResult.launch(Intent(this,
            InputFriend::class.java))
    }
    fun addFriendView(f: Friend) {
        Log.d("FFF", "=============ENTERED ADDFRIENDVIEW =========")
        flag = !flag
        val textView = TextView(this)
        textView.text = f.toString()
        textView.setId(idCount++)
        if(flag) textView.setBackgroundColor(Color.rgb(255, 255, 100))
        else textView.setBackgroundColor(Color.rgb(100, 255, 100))

        textView.textSize = 14F
        textView.setTextColor(Color.BLUE)
        linearLayout.addView(textView)
    }
}
class Friend(val lName:String, val fName: String, val phone: String) {
    override fun toString () : String {
        var s = "<<" + this.fName + " " + this.lName + " " + this.phone + ">>"
        return s

    }
}
