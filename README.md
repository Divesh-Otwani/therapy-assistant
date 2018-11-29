# therapy-assistant
This is an app to assist with certain therapies, starting with cognitive behavioral therapy.


# Coding Style Rules

1. Functions should be no longer than 40 lines of code

2. Comments at the top of functions and classes, rarely in the middle of lines
   of code. 

   This is ok though:

```java


int function(){
  x.do_something(); // this short comment is fine
  // avoid this line comment!!!
  y.dothis();
  return 0;
  }

```


# This week

 * Fix a tiny UI thing to look nice (if you have time)


# Things to do in next, next week

 * Get the core functionality to display at a basic level
 * Make an activity to display each type of resource (Or call another app on
   click): e.g. article resource v.s. video resource





# Developer Resources and Key SO Links


 * https://developer.android.com/guide/topics/ui/
 * https://developer.android.com/reference/android/util/JsonWriter
 * Excellent for design: https://material.io/

# TODO


## Urgent

 * Remove repeated code with SelectExercise Activity -- need a base activity
   eventually!!
 * Add a return home item in the menu

## Eventually


 * On the homepage, set a few resources and exercise as favorites
   * Maybe add buttons to star exercises and you can unstar them by long
     pressing
   * Add, for each resource (in listviews) buttons to star them
   * Add a FAQ page explaining these things
 * Make all the layouts relative to the screen size (not orientation, for now...)
 * Make things pretty
   * Add images
   * Add a nice theme
   * Add animations
   * Make the overflow menu's logo something different and nice
 * Make the Exercise home page more complex to be one continuous scroll but
   connect headers when you run out


# Practical Testing

 * We should test this on two different phones for prolonged use




