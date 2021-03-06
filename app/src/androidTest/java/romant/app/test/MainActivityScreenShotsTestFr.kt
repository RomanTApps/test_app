package romant.app.test

import android.view.View
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.rule.ActivityTestRule
import com.facebook.testing.screenshot.Screenshot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import romant.app.test.fakeactivities.TestActivityFr


class MainActivityScreenShotsTestFr {

    @get:Rule
    var activityTestRule = ActivityTestRule(TestActivityFr::class.java, false, false)

    @Before

    @Test
    fun test_Snapshot_With_Activity_item1_fr() {
        val activity = activityTestRule.launchActivity(null)
        Screenshot.snapActivity(activity).setName("Item 1").record()
    }


    @Test
    fun test_Snapshot_With_Activity_item2_fr() {

        val activity = activityTestRule.launchActivity(null)
        val item1 = activity.findViewById<View>(R.id.item1)
        val item2 = activity.findViewById<View>(R.id.item2)

        runOnUiThread {
            item1.visibility = View.GONE
            item2.visibility = View.VISIBLE
        }
        Screenshot.snapActivity(activity).setName("Item 2").record()
    }

    @Test
    fun test_Snapshot_With_Activity_item3_fr() {
        val activity = activityTestRule.launchActivity(null)
        val item1 = activity.findViewById<View>(R.id.item1)
        val item3 = activity.findViewById<View>(R.id.item3)

        runOnUiThread {
            item1.visibility = View.GONE
            item3.visibility = View.VISIBLE
        }
        Screenshot.snapActivity(activity).setName("Item 3").record()
    }

    @Test
    fun test_Snapshot_With_Activity_item4_fr() {
        val activity = activityTestRule.launchActivity(null)
        val item1 = activity.findViewById<View>(R.id.item1)
        val item4 = activity.findViewById<View>(R.id.item4)

        runOnUiThread {
            item1.visibility = View.GONE
            item4.visibility = View.VISIBLE
        }
        Screenshot.snapActivity(activity).setName("Item 4").record()
    }

    @Test
    fun test_Snapshot_With_Activity_item5_fr() {
        val activity = activityTestRule.launchActivity(null)
        val item1 = activity.findViewById<View>(R.id.item1)
        val item5 = activity.findViewById<View>(R.id.item5)

        runOnUiThread {
            item1.visibility = View.GONE
            item5.visibility = View.VISIBLE
        }
        Screenshot.snapActivity(activity).setName("Item 5").record()
    }

    @Test
    fun test_Snapshot_With_Activity_item6_fr() {
        val activity = activityTestRule.launchActivity(null)
        val item1 = activity.findViewById<View>(R.id.item1)
        val item2 = activity.findViewById<View>(R.id.item6)

        runOnUiThread {
            item1.visibility = View.GONE
            item2.visibility = View.VISIBLE
        }
        Screenshot.snapActivity(activity).setName("Item 6").record()
    }

}