# StackExchange

# The Challenge

For this challenge build an app that connects to the
StackExchange API and displays users as described below

## Description

The following wireframes describe the screens for the app.  
The ascii-drawings are for reference only.  
Just make sure that the required data is displayed.

### Main Screen

Display an input field and Button to search for users by name.  
Display up to 20 users alphabetically and show their reputation and username.  
When tapped, open a new `Activity` to display more information about the
user.

```
+--------------------+
|  AppName           |
|--------------------+
| __________  SEARCH | - input and button  
| ------------------ |
| 123  Username1     |
| ------------------ |
| 390  Username2     | -----*tap* -----> user details screen
| ------------------ |
|   0  Username3     |
| ------------------ |
| 275  Username4     |
| ------------------ |
| 122  Username5     |
+--------------------+
```

### User Details screen

Displays additional information about the user.

```
+--------------------+
| < User             | - up button and page title
|--------------------+
|                    |
|   +-----------+    |
|   |           |    |
|   |   Avatar  |    |
|   |           |    |
|   +-----------+    |
|   User Name        |
|   Reputation       |
|   Badges           |
|                    |
|   Location         |
|   Age              |
|   Creation Date    |
|                    |
+--------------------+
```

## Requirements and Constraints

  * Use a git repository to track your progress
  * Minimum Android API 19
  * The project must compile by executing gradle with `lint test assembleDebug`
  * Use RxJava(2) for handling async network calls
  * Use Retrofit2 for http requests
  * Use Kotlin instead of Java
  * Bonus points if you write any form of documentation (wiki, request for comments, readme, etc.)
  * Bonus points if you write any UI tests

  Of course you are allowed to use any resources available to you!

## Deliverables

Please provide a zip file containing your project files and git folder or
a link to your public repository.

## References

Retrofit2 - http://square.github.io/retrofit/  
RxJava - https://github.com/ReactiveX/RxJava  
StackExchange API - https://api.stackexchange.com/docs  


# The Solution
- MVVM design pattern: *Model* in this case being the data class we are interested in- `User`. *ViewModel* - easy one, this is the `ViewModel` class, in this case I've used the `UserListViewModel`. *View* - the fragments and associated layouts. Why is MVVM useful? Separation of concerns- clear separation of business logic from the view. The view doesn't need to know anything about the underlying data or process it, it just needs to display it. It's worth noting that I didn't add a VM for the `SelectedUserFragment`, for reasons which I'll explain later.
- Dagger Hilt- Gone are the days of copious amounts of Dagger boilerplate! Well, not quite, as Hilt is still in Alpha, but it's definitely a step in the right direction and I wanted an excuse to use it. The boilerplate of components and modules and binding it all together seem to have *mostly* evaporated- instead now you simply define `@AndroidEntryPoint`s and mark your application as a `@HiltAndroidApp` and you're good to go. Why do we even bother with DI? DI is good for decoupling various components, thereby ensuring the code is easy to write tests for, separation of concerns etc.
- Navigation Components- though the requirements implied to use an `Activity` per page, I figured a single activity app with each page represented by a `Fragment` was the best way forward. This leads to smoother transitions between pages, cleaner code by using Nav Components and reduces the cognitive load of having to worry about various different lifecycles. It also produces a nice nav graph so that a developer who is unfamiliar with the app would immediately be able to understand the app's navigation flow.
- Safe Args- following on from Nav Components, I used SafeArgs to send the selected user data from the user list fragment to the selected user fragment. I made the `User` class Parcelable via `@Parcelize` from Kotlin Android Extensions and sent it via safe args to the other fragment. This is beneficial over old school `Bundle` args as it ensures type safety, as well as being easy to see on the Nav Components nav graph. As this `SelectedUserFragment` was only displaying the user data the other fragment sent to it (and nothing else), adding an associated `ViewModel` for the sake of it seemed redundant.
- RxJava2 - for the `Repository` layer of the app, I've used RxJava as specified in the requirements. I've kept this relatively simple- if you check `UserRepository` one can see that I have simply used it for a `Single` API call. I've not had much hands on RxJava experience so I kept this on the more minimal and conservative side of things. Personally I've had more experience with Kotlin Co-routines. Why are RxJava and co routines necessary? It allows for asynchronous programming in such a manner that avoids the old school callback hell of Android days gone past.  
- LiveData - for the VM <-> View layers of the app I'm using LiveData. Why use this over RxJava for these layers? LiveData is lifecycle aware, meaning you don't need to manually cancel subscriptions between the view and the viewmodel. Why didn't I use LiveData throughout the entirety of the app then? LiveData provides no way of error handling and doesn't have asynchonrous capabilities like RxJava and co routines do. Using RxJava at the repo layer allows for greater control.
- Espresso tests- I've added a couple of Espresso tests to the app- these are mainly to check the navigation and that the basic functionality of the app works as intended. Given more time, these tests could be expanded upon, as well as adding the addition of unit tests. I've built the app in such a manner that it would be relatively trivial to add unit tests as it's already got DI set up and I've tried to be conscious regarding separation of concerns.
