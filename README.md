# What is ZIKIZA?

<img width="1000" alt="Goals" src="https://user-images.githubusercontent.com/26790710/228222572-7ca7d558-f39a-45c0-a3a2-6eade01174b6.png">

## Motivation

- We are living in an increasingly polluted environment. The fact that the flowering season is a month earlier in spring indicates a lot to us. 
- If the Earth does not become healthier, there will be no place for people to stay. That's why we have decided to provide solutions to protect the environment and address climate change.
- We believed that individual efforts alone would have limited impact on environmental action. Moreover, we thought that without reasonable conditions, personal commitments to environmental protection would gradually weaken over time.

- ZIKIZA(지키자) phrase in Korean has two meanings:
  - ***Protect** the earth by practicing challenges related to the environment as individuals.*
  - ***Complete** the challenge that we have participated in.*

## Server

### 1. Entity Relationship Diagram
<img width="450" src="https://user-images.githubusercontent.com/26790710/227912604-f8fcf316-f006-4236-9bae-25378942ce7e.png" />

> *figure 1: This illustrates the relational diagram of the our database.*

### 2. Google Firebase

<img width="350" src="https://user-images.githubusercontent.com/26790710/227830407-9ae81496-e7df-4f8e-9b97-5f17c4f6914d.png" />

> *figure 2: [Using firebase cloud messaging in Springboot applications](https://www.baeldung.com/spring-fcm)*

- We used Firebase for authentication / authorization handling.
- Firebase to easily perform the Google sign in process.

  <img width="350" src="https://user-images.githubusercontent.com/26790710/227884400-323ef69f-8a57-4ae7-96df-121829e29b73.png" />
</p>

> *figure 3: [Create a private key in the Firebase project service account](https://firework-ham.tistory.com/111)*

- It can be easily developed in a short development period, and login processing is easy on the web and app, so I used it.
- In addition, in order to save it to DB, you can receive the user's IDToken when you respond to the api, analyze it through the Firebase key, and save it.

<br />

### 3. Google Geocoding

<img width="550" src="https://user-images.githubusercontent.com/26790710/227831391-5db82a45-337c-4f4d-880b-0cf5f4a8b8cb.png" />

> *figure 4: [Geocoding API process](https://firework-ham.tistory.com/111)*

- This allows recycling stores or community locations to be stored as latitude and longitude and displayed as Google Map markers.
- Utilizing a structure that returns latitude and longitude when given an address input.
- Save the corresponding data to the project database to create a Google Maps marker.


### 4. Google Cloud Storage

<img width="350" src="https://user-images.githubusercontent.com/26790710/227833036-8f341d20-46ee-4eb8-b14c-3a540e6beabf.png" />

> *figure 5: [Google Cloud Storage bucket structure](https://firework-ham.tistory.com/111)*

- To successfully store challenge verification photos on the server, we use Google Cloud Storage.
- Google Cloud Storage is a service that stores objects in Google Cloud. At this point, the object refers to any form of file and stores the object in a container called a bucket.

<img width="350" src="https://user-images.githubusercontent.com/26790710/227883960-58b7c3cc-3d9e-4794-b396-280cecb3c3c6.png" />

> *figure 6: [Create a bucket](https://cloud.google.com/storage/docs/discover-object-storage-console)*
- All buckets are connected to a project and can also be set up to allow the desired user to access the data in storage through project permissions.



## Getting Started

| tool | version |
| :---: | :------: |
| mysql | 8.0.31 |
| java | 11.0.16 |
| springframework.boot | 2.7.8 |

1. After installing Mysql, create a database called 'solution_challenge'.
2. After generating the Firebase key, Google Geocode api key, and Google cloud storage IAM key, save it.
3. Run the "./gradlew build" command
4. Google cloud storage's IAM key is stored in a global variable called GOOGLE_APPLICATION_CREDENTIALS.
  ```shell
    # Example Code
    $ export GOOGLE_APPLICATION_CREDENTIALS="KEY_PATH"
  ```
5. Insert the received key into the jar command. It also contains the database password.
``` shell
nohup java -Dspring.datasource.password=[DBPASSWORD] -Dspring.google.api=[google Geocode api] -Dspring.firebase.key=[FIREBASEKEY] -jar [PROJECT NAME] 2>&1 &
```

- 🚫If you do not want to allow https, annotate this code in src/main/resources/application.yml.🚫
  ``` yml
  server:
    port: 8443
    ssl:
      key-store: /etc/letsencrypt/live/[your server url]/keystore.p12
      key-store-password:
      key-store-type: pkcs12
      key-alias: tomcat
      enabled: true
  ```



## Mobile
<p align="left">
 <img width="195" alt="Clay-Home" src="https://user-images.githubusercontent.com/26790710/228124787-38ae5381-cfa2-4125-a4b5-ca51cc3eaf24.png">
<img width="195" alt="Clay-Challenges" src="https://user-images.githubusercontent.com/26790710/228132199-f6a672c4-4520-4aa4-9d18-c0031b84f05c.png">
 <img width="195" alt="Clay-Explore" src="https://user-images.githubusercontent.com/26790710/228127158-a8f1ef4e-4680-4c21-a8b0-ebe805cf473a.png">
 <img width="195" alt="Clay-Profile" src="https://user-images.githubusercontent.com/26790710/228129369-a2629f3c-cb37-48d7-a8ff-debd83f81500.png">
</p>

 > *figure 1: The initial screen that appears when logging in with a Google account*,
 > *figure 2: Navigated to the challenges screen*,
 > *figure 3: Navigated to the explore screen*,
 > *figure 4: Navigated to the profile screen*
 
1. On the Home screen, you can submit photos for challenges you are participating in and view the details of challenges currently in progress.
2. Challenges screen, you can find available challenge and enroll it.
3. And you can find the location and information of the climate clubs.
4. Profile screen has show your history and sign out.

<p align="left">
   <img width="195" alt="Clay-Enroll" src="https://user-images.githubusercontent.com/26790710/228132038-4a6a82c6-41e9-44b3-8c76-faaca4daf0dd.png">
<img width="195" alt="Clay-Purchase" src="https://user-images.githubusercontent.com/26790710/228132263-dc1f40fa-d787-4eb6-86ac-13f93a6f406a.png">
  <img width="195" alt="Clay-TakeShot" src="https://user-images.githubusercontent.com/26790710/228128022-d2427d5a-750e-406e-aef1-4b8bce6da0c8.png">
<img width="195" alt="Clay-Submission" src="https://user-images.githubusercontent.com/26790710/228128059-974d40ad-80bc-426f-8e43-e3c4e9701ca1.png">
</p>

> *figure 5: enroll button is join the user this challenge*,
> *figure 6: Navigated to payment screen*,
> *figure 7: Take shot*,
> *figure 8: Submission*

5. The user pays a certain cost to participate in the challenge.
6. Once the challenge starts, users take and submit certification photos at designated intervals during a set period of time.
7. When the challenge begins, users need to take photos to confirm that they have completed the challenge at certain times throughout a specific period.
8. You can upload submission photo.

### How to run

- *We plan to conduct internal testing on Android devices. The status for iOS is undetermined due to the end of developer registration.*

#### Step 1. Download the project
```bash
$ git clone https://github.com/GDSC-SKHU/98developers-flutter-app.git
$ pwd | code .
```

#### Step 2-1. Run on QEMU
```bash
# Required install android minSDK version 31 or later
# Then, Open Android Studio

$ flutter pub get
$ flutter doctor -v
$ flutter run 
```

#### Step 2-2. Run on Simulator
```bash
# Required install iOS version 12.0 or later
# Then, Open iOS/Runner.xcworkspace

$ flutter pub get
$ flutter doctor -v
$ flutter run
```

[> Click here for more details](https://github.com/GDSC-SKHU/98developers-flutter-app)

## Web

### 1. Promotion website
> *This website has our project infomation*
<p align="left">
  <img width="500" src="https://user-images.githubusercontent.com/26790710/228415544-ef26197b-3d21-4b0b-871e-1e5461e1e09d.png" />
  <img width="500" src="https://user-images.githubusercontent.com/26790710/228424140-82c546d9-b454-4bf4-a380-1e361ead046e.png" />
</p>

> *figure left: This image introduce the challenge service*, 
> *figure right: This image promotion website footer*

### 2. Adminstrator website
> *This website has sign in only manager*

<p align="left">
  <img width="450" src="https://user-images.githubusercontent.com/26790710/228424159-d0894449-86a8-4ca2-8630-7d944a41abe2.png" />
  <img width="450" src="https://user-images.githubusercontent.com/26790710/228424586-3ee26965-0e48-43c8-8324-55e1134747fb.png" />
</p>

> *figure left: Sign in manager with Google account*, 
> *figure right: Users submission controll*

- The manager accesses the challenge website and logs in with a Google account.
- Access the challenge website and view the list of ongoing challenges, as well as the submission status of the participants, and proceed with the review.

[> Click here for more details](https://github.com/GDSC-SKHU/98developers-web)


## Members
|![yujinkim1](https://images.weserv.nl/?url=https://github.com/yujinkim1.png&h=150&w=150&fit=cover&mask=circle)|![devPark435](https://images.weserv.nl/?url=https://github.com/devpark435.png&h=150&w=150&fit=cover&mask=circle)|![LEEHYUNBOK](https://images.weserv.nl/?url=https://github.com/LEEHYUNBOK.png&h=150&w=150&fit=cover&mask=circle)|![yeeZinu](https://images.weserv.nl/?url=https://github.com/yeeZinu.png&h=150&w=150&fit=cover&mask=circle)|
|:---:|:---:|:---:|:---:|
|[Yujin Kim](https://github.com/yujinkim1)|[Hyunryeol Park](https://github.com/devPark435)|[Hyunbok Lee](https://github.com/LEEHYUNBOK)|[Jinwoo Lee](https://github.com/yeeZinu)|
|[Gmail](mailto:yujinkim1.dev@gmail.com)|[Gmail](mailto:devpark435@gmail.com)|[Gmail](mailto:l.hn.bk0905@gmail.com)|[Gmail](mailto:doglife222@gmail.com)|


