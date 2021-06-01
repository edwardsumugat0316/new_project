package udemy.andriod.newproject.userInfo

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import udemy.andriod.newproject.repository.service.roomdatabase.Bio
import udemy.andriod.newproject.R
import udemy.andriod.newproject.base.BaseActivity
import udemy.andriod.newproject.main.MainActivity

import udemy.andriod.newproject.repository.service.model.UsersJsonItem

private const val TAG = "UserInfoActivity"
class UserInfoActivity : BaseActivity(){

    private val viewModel:UserInfoViewModel by viewModel{ parametersOf( intent.getStringExtra("Login"))}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_layout)

        val login = intent.getStringExtra("Login")
        val user = viewModel.getUserFromDb(login = login)
        val userBio = user?.let {
            viewModel.getBioFromDb(id = it.id ) }
        userInfo(user, userBio)
        makeApiRequest2(login, userBio)



        save_button.setOnClickListener {
            userBio?.let {
                it.bio  = et_notes.text.toString()
                viewModel.saveBio(it)
            } ?: run {
                user?.let {viewModel.saveBio(Bio(user.id, et_notes.text.toString()))
                }
            }
        }

        back_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun makeApiRequest2(userLogin: String, bio: Bio?) {

        viewModel.userInfo.observe(this) {
            viewModel.saveUser(it)
            lifecycleScope.launch {
                withContext(Dispatchers.Main) {
                    viewModel.getUserFromDb(login = userLogin)
                    userInfo(it, bio = bio )

                }
            }
        }

    }

    private fun userInfo(user: UsersJsonItem?, bio: Bio?) {

        tv_follower_result.text = user?.followersCount.toString()
        tv_following_result.text = user?.followingCount.toString()

        Glide.with(this)
                .load(user?.avatar_url)
                .into(iv_photo)

        tv_username_result.text = user?.login
        tv_company.text = user?.company
        tv_email.text = user?.email
        tv_blog.text = user?.blog
        tv_toolbar_name.text = user?.login
        et_notes.setText(bio?.bio)

    }

}