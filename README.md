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

# Things to do in next week


 * Divesh: Have 5 basic activities for exercises + home ready -- even if they
   just display text (but in the right spots with good extensible changeable
   layouts)
 * Brian: Pair program with Zach on (1) the several resources classes and (2)
   making 3 resource activities; really basic (display text but in the right
   places)
 * Yasmine: Implement pulling of resources.


# Things To Discuss

 * Adding articles and stuff via an app for the therapist (or maybe just an
   online interface?)
 * Divesh wants the functions to be smaller than 40 lines
 * Divesh thinks we should each write a design document explaining the purpose
   of our part of the code and the high level implementation; then combine these
   into a large design document.


# Later Features to Add


 * Make things pretty
   * Add images
   * Add a nice theme
   * Add animations



# Developer Resources and Key SO Links


 * https://developer.android.com/guide/topics/ui/
 * https://developer.android.com/reference/android/util/JsonWriter


# TODO


## Urgent

 * Remove repeated code with SelectExercise Activity -- need a base activity
   eventually!!

## Eventually


 * On the homepage, set a few resources and exercise as favorites
   * Maybe add buttons to star exercises and you can unstar them by long
     pressing
   * Add, for each resource (in listviews) buttons to star them
   * Add a FAQ page explaining these things
 * Make all the layouts relative to the screen size (not orientation, for now...)




