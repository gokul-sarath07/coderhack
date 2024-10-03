# CoderHack Leaderboard API

This project is a RESTful API service built with Spring Boot that manages the leaderboard of a coding platform contest. The service allows CRUD operations for users and awards badges based on their scores. The API stores user data in MongoDB.

## Problem Statement

The goal is to develop a RESTful API service for managing the leaderboard of a specific contest. The platform only hosts one contest with a single leaderboard. Users can register, update their scores, and receive virtual awards (badges) based on their scores.

## Features

1. **CRUD Operations**: Users can be registered, updated, retrieved, and deleted from the leaderboard.
2. **Scoring System**: Users start with a score of `0` and can have their scores updated (within the range of `0-100`).
3. **Badge System**:
    - Based on the user's score, they can earn badges:
        - **Code Ninja**: Score between 1 and 29.
        - **Code Champ**: Score between 30 and 59.
        - **Code Master**: Score between 60 and 100.
    - Users can only have a maximum of 3 unique badges.

4. **Sorting**: The leaderboard is sorted by score in descending order with a time complexity of `O(nlogn)`.

## Badges

Users are awarded badges based on their score as follows:

- **1 <= Score < 30** → `Code Ninja`
- **30 <= Score < 60** → `Code Champ`
- **60 <= Score <= 100** → `Code Master`

### Badge Rules:
- Users can only have unique badges. The system automatically updates the badges when the score is updated.
- Valid badge sets:
    - `{Code Ninja}`
    - `{Code Ninja, Code Champ}`
    - `{Code Ninja, Code Champ, Code Master}`

## Requirements

1. **User Fields**:
    - `userId` (Unique Identifier)
    - `username`
    - `score` (0 <= Score <= 100)
    - `badges` (A list of badges based on the score)

2. **User Registration**:
    - On registration, the user's score must be set to `0` and badges must be an empty list.

3. **Score Updation**:
    - The score can be updated via a `PUT` request. Badges must be updated accordingly based on the user's score.

4. **Leaderboard Sorting**:
    - Users are retrieved in descending order of their scores, with sorting time complexity of `O(nlogn)`.

## Endpoints

| Method    | Endpoint            | Description                          |
|-----------|---------------------|--------------------------------------|
| `GET`     | `/users`            | Retrieve a list of all users         |
| `GET`     | `/users/{userId}`    | Retrieve details of a specific user  |
| `POST`    | `/users`            | Register a new user                 |
| `PUT`     | `/users/{userId}`    | Update a user's score               |
| `DELETE`  | `/users/{userId}`    | Deregister a user from the contest   |

## Running the Project

1. **Clone the repository**:
   - `git clone https://github.com/gokul-sarath07/coderhack.git`
2. **To build and run**:
   - `./gradlew bootRun`
3. **Import the postman collection**:
   - `CoderHack.postman_collection.json` in the root folder
4. **Test the app**:
   - Use the available api in the collection to test the app