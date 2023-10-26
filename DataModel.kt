
data class YourDataModel(val foodName :String?= null,
                         val foodPrice:String?=null,
                         val foodDisc :String?=null,
                         val foodImg :String?=null,
                         val foodIngred :String?= null)

/*
Data Model Concept :
In Firebase, a data model concept refers to how you structure and organize
 your data within the Firebase Realtime Database or Firestore to efficiently store,
  retrieve, and manage information for your Android app developed using Kotlin. 
  The data model concept in Firebase is crucial for creating a well-organized and 
  scalable database structure.
*/

/*
Why to apply them :
The reason is when we need data to be stored in fireabase we need to pass those data via 
some vairable and using variable in program can be tedious so we use class to store and pass data.
But in kotlin we have data class which just provide no need of geeeter and setter explicitly define
*/