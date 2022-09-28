# What is the Activity Lifecycle? Why is it important? How do we handle the problems that may occur in our projects with Activity Lifecycle?

## What is the Activity?

First of all, I would like to start the article with a general explanation about what activity is. Activity is one of the basic building blocks used in android applications. We can say that there is no application without Activity. The Activity acts as the entry point to the app that raises a mobile app. Thanks to the Activity, the connection between the user and the application is established and the functions that work on the basis of the application are thus made available to the user. After learning about activity in general, let's take a look at what this Activity Lifecycle is and what it is not.

## What is the Activity Lifecycle?

The activity that lifts the application should also follow the following after this process. Where is the user currently in the activity, is he still using the app? Left the app? If it left the app, does the app stay in the background or is it completely closed? It transmits such information to the programmer via callback, and with this information obtained by the programmer, it obtains the necessary information to handle these situations, to terminate the function, to continue or to take a different action. Well that's fine, but why do we care so much about it? I use the onCreate() function in my application, I can do anything I want. Yes, you can do this, but we need to know the activity lifecycle logic and functions well in order to provide a performance process and to support the healthy operation of the application. If you say I don't know this logic well, let me explain it to you by giving a few small examples;

- The user filled the Edit texts well in the application, but suddenly a call came and he had to leave the application, made the call and came back, what is that!? What a frustrating situation for this user, all the information he filled in is gone..
- We download data, handle them and interact with the user, but when we need to clean this data, but because we do not know the lifecycle, we cannot handle them properly and cause performance problems on the ram side.
- The user is using the application and suddenly the application switched from potrait mode to landscape mode, and all the data on the screen disappeared in an instant. Yes, I know you encountered such an error. The main issue that caused this error is that we do not know the activity lifecycler exactly.

We shed some light on the small but annoying problems that we do not have enough knowledge about this subject, now let's look at the activity lifecycle principles that help us to eliminate these problems.

## What concepts does the activity lifecycle work principle consist of?

```
 onCreate() onStart() onResume() onPause() onStop() onDestroy()
 ```

Let me visually leave the hierarchy of these methods below for you, so that it fits better in our minds.

[![LdZTe1.md.png](https://iili.io/LdZTe1.md.png)](https://freeimage.host/i/LdZTe1)

As you can see in the picture, the work starts in the background as soon as an activity is running. These works give us the first signal with onCreate().

## What is the onCreate() method?

It is the first method that starts working in all activities and is important for us. This method runs once when the activity starts and is not called again unless the activity is destroyed. On this onCreate() we can handle the viewmodels we created. We can provide data exchange here. Here we set up the XML view that we will show to the user with the setContentView() method. While talking about the problems it caused us, even switching the phone to a landscape mode caused us to lose data. Do you remember that here, with the savedInstanceState data it takes to the constructor, it provides an opportunity for us to handle these data losses, don't worry, I will explain it to you better. Below I want to show you an onCreate() function.

```
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
}
```

As you can see, my application stood up, my activity worked, and the onCreate method started to do its job. When this method was taken, I made a dataBinding definition. At the same time, by handling the savedInstanceState bundle variable that I just mentioned, I have handled the data I sent from other methods. (I will better convey this logic in the onStop() method.)

## What is the onStart() method and how is it used?

When we open our application, it is the method that works after the onCreate() method. This is where the UI seen by the user is shown to be started. This method happens very quickly and leaves its place to the onResume() method.


## What is the onResume() method and how is it used?

This scope continues to run as long as the application is not exited. This callback waits behind when the application passes itself to the onPause() method, and this scope runs again when onPause() disappears. For example, you wrote a camera application. When you switch to the camera, the application is actually still in the background, in this case the activity falls on the onPause() method and when you return from the camera again, onResume() will handle the work. We can write the codes to activate the camera within this scope because we do not want the application to terminate.

## What is the onPause() method and how is it used?

The system reports that the application has somehow closed here. When the application is thrown into the background or when we switch to another application in our application, even when a dialog appears on the phone, such as a warning window, our application actually enters the paused mode with the communication cut off in the background. Here, we can consider the operations to be performed in our application under this scope, foreseeing such situations to occur. When the application is completely destroyed on the system side, it will hand over to the onStop() method.

## What is the onStop() method?
When we exit the application, onStop is thrown in front of us. In fact, when we look at the performance in terms of performance, it allows us to present a user-friendly application by helping to close unnecessary processes in the CPU.

## What is the onDestory() method and how is it used?
Yes… we now understand that the user has completely moved away from the application. I wish it had never left our application. Anyway, if we go back to our topic, we see that there is no need for the application under this scope anymore, and on this occasion, for example, we can find the opportunity to clean the data garbage in the memory of the retrofit operations we have done here.

Let's write a small program and see how our data is not lost when we switch from portrait mode to landscape mode with activity lifecycle. Let's create a project and write the following codes in our mainactivity.
When the activity stops working temporarily, the onSavedInstance method is called, where we pass our data to the savedInstance variable to save our static data. (Android doesn't save static data so it doesn't take up memory for performance.)

```
private lateinit var counter: TextView
private lateinit var counter_button:Button
private var counterCount = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counter = findViewById(R.id.counter)
        counter_button = findViewById(R.id.counter_button)
        counter_button.setOnClickListener {
            counterCount++
            counter.text = counterCount.toString()
        }
    }

// This callback is called only when there is a saved instance that is previously saved by using
// onSaveInstanceState(). We restore some state in onCreate(), while we can optionally restore
// other state here, possibly usable after onStart() has completed.
// The savedInstanceState Bundle is same as the one used in onCreate().

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter_count", counterCount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counterCount = savedInstanceState.getInt("counter_count")
        counter.text = counterCount.toString()
    }
}
```

[![LdtCjn.md.gif](https://iili.io/LdtCjn.md.gif)](https://freeimage.host/i/LdtCjn)

With these few lines of code, when we switch from portrait mode to landscape mode in our project, it allows us to continue our work without losing our data on the UI side.
