## Getting Start

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
      key-store: /etc/letsencrypt/live/zikiza.duckdns.org/keystore.p12
      key-store-password:
      key-store-type: pkcs12
      key-alias: tomcat
      enabled: true
  ```
