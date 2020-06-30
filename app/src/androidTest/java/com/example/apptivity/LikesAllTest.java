package com.example.apptivity;


import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LikesAllTest {

    @Rule
    public ActivityTestRule<WelcomeScreen> mActivityTestRule = new ActivityTestRule<>(WelcomeScreen.class);

    @Test
    public void likesAllTest() {
        SystemClock.sleep(3000);
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.inputName),
                        childAtPosition(
                                allOf(withId(R.id.inputAlter2),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Thomas"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.inputAlter),
                        childAtPosition(
                                allOf(withId(R.id.inputAlter2),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("24"), closeSoftKeyboard());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.inputMale), withText("Männlich"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.inputAlter2),
                                        2),
                                1),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.inputName), withText("Thomas"),
                        childAtPosition(
                                allOf(withId(R.id.inputAlter2),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        editText.check(matches(withText("Thomas")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.inputAlter), withText("24"),
                        childAtPosition(
                                allOf(withId(R.id.inputAlter2),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        editText2.check(matches(withText("24")));

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.inputMale),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.inputAlter2),
                                        2),
                                1),
                        isDisplayed()));
        radioButton.check(matches(isDisplayed()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btPInfo), withText("Speichern und Weiter"),
                        childAtPosition(
                                allOf(withId(R.id.inputAlter2),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btOptions), withText("Einstellungen"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btNewPI), withText("Ändern"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.inputName),
                        childAtPosition(
                                allOf(withId(R.id.inputAlter2),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("Musterfrau"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.inputAlter),
                        childAtPosition(
                                allOf(withId(R.id.inputAlter2),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("30"), closeSoftKeyboard());

        ViewInteraction appCompatRadioButton2 = onView(
                allOf(withId(R.id.inputFemale), withText("Weiblich"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.inputAlter2),
                                        2),
                                0),
                        isDisplayed()));
        appCompatRadioButton2.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btPInfo), withText("Speichern und Weiter"),
                        childAtPosition(
                                allOf(withId(R.id.inputAlter2),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btFav), withText("Favoriten"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.btBackToHome), withText("Zurück zu Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.btSearch), withText("Neue Suche"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction checkBox = onView(
                allOf(withText("Action"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tagsLL),
                                        0),
                                0),
                        isDisplayed()));
        checkBox.perform(click());

        ViewInteraction checkBox2 = onView(
                allOf(withText("Filme"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tagsLL),
                                        0),
                                1),
                        isDisplayed()));
        checkBox2.perform(click());

        ViewInteraction checkBox3 = onView(
                allOf(withText("Musik"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tagsLL),
                                        1),
                                0),
                        isDisplayed()));
        checkBox3.perform(click());

        ViewInteraction checkBox4 = onView(
                allOf(withText("Natur"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tagsLL),
                                        1),
                                1),
                        isDisplayed()));
        checkBox4.perform(click());

        ViewInteraction checkBox5 = onView(
                allOf(withText("Party"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tagsLL),
                                        2),
                                0),
                        isDisplayed()));
        checkBox5.perform(click());

        ViewInteraction checkBox6 = onView(
                allOf(withText("Schnee"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tagsLL),
                                        2),
                                1),
                        isDisplayed()));
        checkBox6.perform(click());

        ViewInteraction checkBox7 = onView(
                allOf(withText("Sport"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tagsLL),
                                        3),
                                0),
                        isDisplayed()));
        checkBox7.perform(click());

        ViewInteraction checkBox8 = onView(
                allOf(withText("Wasser"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tagsLL),
                                        3),
                                1),
                        isDisplayed()));
        checkBox8.perform(click());

        ViewInteraction checkBox9 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tagsLL),
                                0),
                        0),
                        isDisplayed()));
        checkBox9.check(matches(isDisplayed()));

        ViewInteraction checkBox10 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tagsLL),
                                0),
                        1),
                        isDisplayed()));
        checkBox10.check(matches(isDisplayed()));

        ViewInteraction checkBox11 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tagsLL),
                                1),
                        0),
                        isDisplayed()));
        checkBox11.check(matches(isDisplayed()));

        ViewInteraction checkBox12 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tagsLL),
                                1),
                        1),
                        isDisplayed()));
        checkBox12.check(matches(isDisplayed()));

        ViewInteraction checkBox13 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tagsLL),
                                2),
                        0),
                        isDisplayed()));
        checkBox13.check(matches(isDisplayed()));

        ViewInteraction checkBox14 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tagsLL),
                                2),
                        1),
                        isDisplayed()));
        checkBox14.check(matches(isDisplayed()));

        ViewInteraction checkBox15 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tagsLL),
                                3),
                        0),
                        isDisplayed()));
        checkBox15.check(matches(isDisplayed()));

        ViewInteraction checkBox16 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tagsLL),
                                3),
                        1),
                        isDisplayed()));
        checkBox16.check(matches(isDisplayed()));

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.btBackHome), withText("Fertig"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.inputAlone), withText("Alleine"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatCheckBox.perform(click());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.inputPartner), withText("Partner(in)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatCheckBox2.perform(click());

        ViewInteraction appCompatCheckBox3 = onView(
                allOf(withId(R.id.inputFamily), withText("Familie"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatCheckBox3.perform(click());

        ViewInteraction appCompatCheckBox4 = onView(
                allOf(withId(R.id.inputFriend), withText("Freunden"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatCheckBox4.perform(click());

        ViewInteraction checkBox17 = onView(
                allOf(withId(R.id.inputAlone),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        checkBox17.check(matches(isDisplayed()));

        ViewInteraction checkBox18 = onView(
                allOf(withId(R.id.inputPartner),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        checkBox18.check(matches(isDisplayed()));

        ViewInteraction checkBox19 = onView(
                allOf(withId(R.id.inputFamily),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        checkBox19.check(matches(isDisplayed()));

        ViewInteraction checkBox20 = onView(
                allOf(withId(R.id.inputFriend),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        checkBox20.check(matches(isDisplayed()));

        ViewInteraction seekBar = onView(
                allOf(withId(R.id.inputBudget),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        seekBar.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.money), withText("0€ / 50€"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        textView.check(matches(withText("0€ / 50€")));

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.btSearch2), withText("Weiter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.inputTown),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("Landshut"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.inputPostal),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("84034"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.inputTown), withText("Landshut"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        editText3.check(matches(withText("Landshut")));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.inputPostal), withText("84034"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText4.check(matches(withText("84034")));

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.btSwipe), withText("Zeig mir was!"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton10.perform(click());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.btSwiping), withText("Zurück"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton11.perform(click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.btBackHome), withText("Zurück zu Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton12.perform(click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.btFav), withText("Favoriten"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton13.perform(click());

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.btResetMatches), withText("Alle Matches löschen"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton14.perform(click());

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(R.id.btBackToHome), withText("Zurück zu Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton15.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
