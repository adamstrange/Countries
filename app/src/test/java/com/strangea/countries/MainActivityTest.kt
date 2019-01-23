package com.strangea.countries

import com.strangea.countries.activities.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert.*
import org.junit.Test
import org.robolectric.Robolectric

class MainActivityTest: BaseTest() {

    @Test
    fun testActivitySetup(){
        val mainActivity = Robolectric.setupActivity(MainActivity::class.java)

        assertNotNull(mainActivity.recyclerView)
        assertNotNull(mainActivity.recyclerView.adapter)
        assertEquals(mainActivity.recyclerView.adapter?.itemCount, 1)
    }
}