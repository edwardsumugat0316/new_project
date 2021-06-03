package udemy.andriod.newproject.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import udemy.andriod.newproject.R
import udemy.andriod.newproject.repository.service.model.UsersJsonItem
import udemy.andriod.newproject.userInfo.UserInfoActivity

class RecyclerAdapter (var item: List<UsersJsonItem>, val context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.iv_avatar)
        val name: TextView = view.findViewById(R.id.tv_name)

        init {
            view.setOnClickListener{
                val position: Int = bindingAdapterPosition
                val login = item[position]
                val idNumber = login.id
                val userPlace = login.login
                Toast.makeText(view.context, "You clicked on item id $idNumber user $userPlace", Toast.LENGTH_LONG).show()
                val intent = Intent(context, UserInfoActivity::class.java).apply {
                    putExtra("Login", userPlace)
                }

                context.startActivity(intent)
            }

            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(layoutInflater)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usersItem = item [position]
        holder.name.text = usersItem.login

        Glide.with(holder.image)
            .load(usersItem.avatar_url)
            .into(holder.image)

    }

    override fun getItemCount(): Int {
        return item.size
    }

}