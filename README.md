# Lab 14.1 – Image Classification using ML Kit

This project is an Android app built in **Android Studio** that performs **image labeling** using **Google ML Kit**.  
The app loads a sample image from the `assets` folder and, when the user presses a button, it returns the most probable labels and their confidence scores.

---

## Features

- Loads a static image (`flower1.jpg`) from the **assets** folder.
- Uses **ML Kit Image Labeling** API.
- Displays:
  - The image in an `ImageView`.
  - A button (`Label Image`) to start the classification.
  - A `TextView` with the labels and confidence values.

---

## Tech Stack

- Language: **Kotlin**
- Min SDK / Target: as configured by Android Studio (API 33 emulator used in tests)
- UI:
  - `ConstraintLayout` + `LinearLayout`
  - `ImageView`, `Button`, `TextView`
- ML:
  - `com.google.mlkit:image-labeling`
- Support libraries:
  - `androidx.appcompat:appcompat`
  - `androidx.constraintlayout:constraintlayout`

---

## Project Structure (important parts)

```text
app/
 └── src/
     └── main/
         ├── java/com/example/imageclassifierstep1/
         │   └── MainActivity.kt
         ├── res/
         │   ├── layout/activity_main.xml
         │   └── values/...
         └── assets/
             └── flower1.jpg
