# MADFit 

MADFit is a health & wellness mobile application that motivates users to take care of their health. The aim is to encourage people to watch their diet and exercise. The audience for our application would be for people interested in keeping fit and healthy. :muscle:

By:
* Balqis Kinanti Haldi Heryputri : S10204973
* Keith Toh : S10202764
* Lim Xuan Qing : S10207455

## Table of Contents
* [Design Process](https://github.com/Balqiskinanti/MADFit#design-process)
* [Features](https://github.com/Balqiskinanti/MADFit#features)
* [Contributions](https://github.com/Balqiskinanti/MADFit#contributions)
* [User Guide](https://github.com/Balqiskinanti/MADFit#user-guide)
* [Concepts Used](https://github.com/Balqiskinanti/MADFit#concepts-used)
* [Credits](https://github.com/Balqiskinanti/MADFit#credits)

## Design Process
### User Stories
* As a user, I want to calculate my BMI so that I will know whether I'm overweight or underweight and can make adjustments to my lifestyle.
* As a user, I want to store my BMI value so that I can refer to it in the future.
* As a user, I want to be able to listen to music while I'm exercising so that I can stay motivated.
* As a user, I want to keep track of my calorie intake so that I will know whether I'm consuming enough calories for the day.
* As a user, I want to be able to have a set of workout and exercise that can let me keep myself healthy and fit.
* As a user, I want to be able to keep track of the time I used for a set of workout.
* As a user, I want to be able to keep track of the progress of workout based on dates.
* As a user, I want to receive notifications and reminders so that I will remember to exercise.
* As a user, I want to subscribe to email services so that I can get the latest information about fitness.

### Prototype
https://xd.adobe.com/view/bbe237a4-5c01-440e-a3d7-17a2fb9658b5-eeb1/

## Features
### Existing Features
No | Feature | Description
------------ | ------------ | ------------
1 |__Exercise Guide__ | The guide consists of different workout tutorials, in gif format, that the users can learn and follow during their workout. This can be turned off in the workout settings.
2 | __Exercise Timer__ | After starting the workout on our app, there will be a countdown timer shown for each exercise to let users know the time left for that exercise before moving on to the next. User can modify the timing based on their comfort level, 10s for easy, 20s for medium, 30s for hard. The levels can be changed in the workout settings as well.
3 | __Exercise Total Time__ |  After users have completed their workout, there will be a page that displays the total time they have taken for the whole workout. The timing will also be shown in the home page of our app.
4 | __Exercise Progress Calendar__ | Users will be able to view their exercise progress based on dates on the calendar. 
5 | __Exercise Plan__ | Users can create their own workout plans to follow or work towards. They simply have to enter the title, description, type and duration of the workout. Users will also have the option to clear the plans they have created.
6 | __BMI Calculator__ | This feature allows users to enter their weight and height to calculate their BMI (Body Mass Index) and get their BMI Status. The status will let the user better understand their BMI and body weight. Users can also choose to store this calculation. If the user select 'yes', the BMI will be dislayed in the home page of our app, and the height, weight, BMI and status will be shown in the home page of this feature.
7 | __Calorie Dashboard__ | This dashbaord will display the user's calorie target, the total amount of calories that have been consumed, as well as calories consumed by meals for the day. There is also a circular progress bar for users to easily determine whether they have met their target for the day. Users will also be able to set their own daily targets below the dashboard.
8 | __Calorie Counter__ | The Calorie Counter will allow users to enter the food items they have consumed and the calories of the food according to the 4 categories: Breakfast, Lunch, Dinner and Others. The last category is for any informal meals such as snacks or tea break. The total calories for each category will be display in the dashboard in Feature 7.
9 | __Music Player__ | Users will be able to select music based on the type and length of their workout, such as 30 minutes long music for workout marathon or 3-10 minutes music for quick workout. The music that are available in our app is also further broken down to different categories such as cardio and chill run. 
10 | __Workout Reminder__ | Users can set reminders in our app to remind them to start or complete their workout by selecting their preferred time. At the selected timing, our app will send a reminder notification to the user. User can also choose to mute notification under the workout settings.
11 | __Email Notification__ | Users will get notified in their email for the latest app updates, interesting articles, etc. When the user enters their email in our app, a welcome email will be sent to them with a link to verify their subscription. 
12 | __Dark Mode__ | Users can turn on/off dark mode locally in our app for enhanced experience.

## Contributions
* __Xuan Qing:__ Exercise Home Page, Features 1-4
* __Keith:__ Home Page, Features 5-8
* __Balqis:__ App Logo, Splash Screen , Features 9-12
* __Everyone:__ Maintaining consistent UI, responsive layout, working intents

## User Guide
![madit-userguide](https://user-images.githubusercontent.com/72959939/127746610-190f793b-92b0-410a-a84c-41b76965b122.jpg)
[Download App Here](https://play.google.com/store/apps/details?id=sg.edu.np.mad.madfit)

## Concepts Used
* Responsive layout
* Multimedia
* RecyclerView
* Persistent Memory
* Notification
* Web-based content

## Credits
### Media
* Exercise Gif Image
  * https://drive.google.com/drive/folders/1c5dhFYL94yzHKpyAkjt9On8nNTSBz9-q
* Gif Image
  * https://gfycat.com/threadbareparallelemperorshrimp-vegetables-healthy-workout-muscle-gym
  * https://www.behance.net/gallery/40400159/Character-Fitness-Animation?tracking_source=search_projects_recommended%7CWorkoutanimation
* Calendar View
  * https://github.com/prolificinteractive/material-calendarview
* Notification llustrations
    * https://cdn1.vectorstock.com/i/thumb-large/90/05/cute-girl-doing-sport-exercise-adorable-kid-vector-28279005.jpg
    * https://cdn.dribbble.com/users/827359/screenshots/3773847/bg_copy_16.png?compress=1&resize=800x600
* Music Cover
    * https://i.pinimg.com/originals/82/d2/c3/82d2c39debf6d5b14ea57e292c85a6e2.jpg
    * https://i.pinimg.com/564x/35/d4/13/35d413daf57dc016804ba94997593abc.jpg
    * https://i.pinimg.com/236x/9b/f3/af/9bf3aff1ace51b2369170f4eb87f64d3.jpg
    * https://i1.wp.com/i.redd.it/q3varo58nxkz.jpg
* Icons
    * https://fonts.google.com/icons

### API & Other Resources Used
* Email Notification
    * https://javaee.github.io/javamail/docs/api/
    * https://login.mailchimp.com/login/post/
* Music
    * https://soundcloud.com/discover
