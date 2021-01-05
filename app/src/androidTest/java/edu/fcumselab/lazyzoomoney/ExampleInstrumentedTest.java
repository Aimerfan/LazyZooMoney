package edu.fcumselab.lazyzoomoney;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    /*@Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("edu.fcumselab.lazyzoomoney", appContext.getPackageName());
    }*/

    @Test
    /*Espresso 自動化測試*/
    // gradlew connectedAndroidTest
    public void test()
    {
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.username)).perform(typeText("test"));
        onView(withId(R.id.password)).perform(typeText("test"));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.plus)).perform(click());
    }
    /*Espresso 自動化測試*/

   // public void test2()
   // {
        //onView(withId(R.id.btn_login)).perform(click());
        /*onView(withId(R.id.username)).perform(typeText("test"));
        onView(withId(R.id.password)).perform(typeText("test"));
        onView(withId(R.id.button)).perform(click());*/

   // }




}