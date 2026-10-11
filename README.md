# Japanese Character Quiz

An Android app for practising Japanese Kanji, built with Kotlin and Jetpack Compose. Created for a university project.

## Tech Stack

| Language | Kotlin |
| UI | Jetpack Compose with Material 3 |
| Navigation | Navigation Compose |
| Architecture | ViewModels (`lifecycle-viewmodel-compose`) |
| Dependency injection | Hilt (Dagger) |
| Local storage | Room 3 |
| Testing | JUnit, Espresso, Compose UI tests, Hilt testing |

## Requirements

- Android Studio (a recent version with support for compile SDK 37)
- JDK 11 or newer
- An Android device or emulator running Android 7.1 (API 25) or higher

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/mtt-redd/japanese_character_quiz.git
   cd japanese_character_quiz
   ```

2. Open the project in Android Studio and let Gradle sync.
3. Select a device or emulator and press **Run**.

## Project Structure

```
japanese_character_quiz/
├── app/                                  
│   └── src/
│       ├── main/
│       │   ├── java/com/example/japanesecharacterquiz/
│       │   │   ├── Database/             
│       │   │   │   ├── question.kt
│       │   │   │   ├── questionDao.kt
│       │   │   │   ├── questionDatabase.kt
│       │   │   │   ├── questionRepository.kt
│       │   │   │   ├── user.kt
│       │   │   │   ├── user_dao.kt
│       │   │   │   ├── user_database.kt
│       │   │   │   └── user_repository.kt
│       │   │   ├── ui/theme/             
│       │   │   ├── View/                 
│       │   │   │   ├── Navigation.kt
│       │   │   │   ├── view_login.kt
│       │   │   │   ├── View_Question.kt
│       │   │   │   └── View_selection.kt
│       │   │   ├── View_Model/          
│       │   │   │   ├── viewModel_Question.kt
│       │   │   │   └── viewmodel_user.kt
│       │   │   ├── JapaneseQuizApp.kt    
│       │   │   ├── MainActivity.kt       
│       │   │   ├── questionDatabaseModule.kt  
│       │   │   └── userDatabaseModule.kt      
│       │   ├── res/                     
│       │   └── AndroidManifest.xml
│       └── androidTest/                  
├── gradle/                               
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

Instrumented tests use a custom `HiltTestRunner` for dependency injection.


The app uses a single-activity architecture: `MainActivity` sets up the theme and hands over to a Compose navigation graph.

## App Details

- **Package:** `com.example.japanesecharacterquiz`
- **Version:** 1.0
- **Min SDK:** 25
- **Target / compile SDK:** 37


## Maintainer

https://github.com/mtt-redd