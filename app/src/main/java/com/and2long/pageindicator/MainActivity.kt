package com.and2long.pageindicator

import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    val textSizeSelected = 20.0f
    val textSizeUnselected = 16.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titles = listOf("科技", "生活", "新闻", "体育", "娱乐", "财经")
        val bgs = listOf(
            R.drawable.shape_blue,
            R.drawable.shape_orange,
            R.drawable.shape_red,
            R.drawable.shape_purple,
            R.drawable.shape_green,
            R.drawable.shape_gray
        )
        val colors = listOf(
            ContextCompat.getColor(this, android.R.color.holo_blue_light),
            ContextCompat.getColor(this, android.R.color.holo_orange_light),
            ContextCompat.getColor(this, android.R.color.holo_red_light),
            ContextCompat.getColor(this, android.R.color.holo_purple),
            ContextCompat.getColor(this, android.R.color.holo_green_light),
            ContextCompat.getColor(this, android.R.color.darker_gray)
        )

        val fragments = mutableListOf<Fragment>()
        titles.forEach {
            fragments.add(PageFragment.create(it))
        }
        val mAdapter = object : FragmentPagerAdapter(supportFragmentManager, 0) {

            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }


            override fun getCount(): Int {
                return titles.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return titles[position]
            }

        }
        viewPager.adapter = mAdapter
        tabLayout.setupWithViewPager(viewPager)


        titles.forEachIndexed { index, s ->
            val tab = tabLayout.getTabAt(index)
            val view = layoutInflater.inflate(R.layout.item_tab, null)
            val tvTab = view.findViewById<TextView>(R.id.tab_text)
            val tabRoot = view.findViewById<RelativeLayout>(R.id.tab_root)
            tabRoot?.setBackgroundResource(bgs[index])
            if (index == 0) {
                tabRoot.layoutParams.height =
                    resources.getDimensionPixelSize(R.dimen.height_tab_selected)
                tvTab.textSize = textSizeSelected
            }
            tvTab.text = s
            tab?.customView = view
        }

        divider.setBackgroundColor(colors[0])
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.customView?.findViewById<RelativeLayout>(R.id.tab_root)?.apply {
                    layoutParams.height =
                        resources.getDimensionPixelSize(R.dimen.height_tab_unselected)
                }

                tab?.customView?.findViewById<TextView>(R.id.tab_text)?.apply {
                    textSize = textSizeUnselected
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val position = it.position
                    divider.setBackgroundColor(colors[position])
                }
                tab?.customView?.findViewById<RelativeLayout>(R.id.tab_root)?.apply {
                    layoutParams.height =
                        resources.getDimensionPixelSize(R.dimen.height_tab_selected)
                }

                tab?.customView?.findViewById<TextView>(R.id.tab_text)?.apply {
                    textSize = textSizeSelected
                }
            }

        })
    }
}
