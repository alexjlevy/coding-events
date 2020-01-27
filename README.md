# coding-events

coding-events is an app that allows users to create and track different events and event categories
The app was developed using Java, Spring, and Hibernate 

Currently users are able to create events, edit them, and delete them. They can do the same with event categories
There is a persistent many-to-one relationship between the Events and EventCategories classes, with an inverse relationship as well

Plans for future development include creating a Person class, which would hold info about users of the app
The Person class would have a one-to-many relationship with the Event class
Its fields would have to include username and password as well as some basic info like name, email, and date of birth
There could also be fields like interests to allow users to be more easily matched to events that match their interests 
Methods would include getters and setters for username and a getter for password
