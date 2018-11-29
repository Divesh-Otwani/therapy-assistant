# therapy-assistant
This is an app to assist with certain therapies by logging client exercises and
providing resources. The only therapy currently supported is CBT, cognitive 
behavioral therapy.


# Design

![Design Outline](/for-readme/main-activites.jpg)

Above we have the core activities that make the application.




# Active Development

## To Work on This Week

 * Divesh: Get the view pager working and use Brian's fragments. Also, make sure
   you use observers.
 * Yasmine: Make the right listadapters for the activities SelectExercise,
   Exercises, CBTResources (this last activity should be renamed to
   DisplayResources). Make sure things that are slow run on threads. Register
   observers to update the views.
 * Brian: Create simple fragments of different answer types and have these take
   text input, a dial input to 100 and a nice scale from 1 to 10.
 * Zach: Create an adapter for the grid view that displays resources.


## Things to do next week

 * Display real exercises and resources and go and see Professor Gordon.


## Developer Resources and Key SO Links

 * https://developer.android.com/guide/topics/ui/
 * https://developer.android.com/reference/android/util/JsonWriter
 * Excellent for design: https://material.io/


## Eventually, at the end

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


## Practical Testing

 * We should test this on two different phones

## Coding Style Rules

1. Functions should be no longer than 40 lines of code; ideally under 25.

2. Comments should be at the top of functions and classes and 
rarely in the middle of lines of code:


```java

/* Good descriptive comment */
int function(){
  x.do_something(); // this short comment is fine
  // avoid this line comment!!!
  y.dothis();
  return 0;
  }

```



