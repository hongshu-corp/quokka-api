FROM openjdk:8u181-jre

ENV TZ 'UTC'
ENV SPRING_PROFILES_ACTIVE 'prod'
ENV AWS_REGION 'us-east-1'
ENV SERVER_PORT 80

ENV DB_MYSQL_HOST ''
ENV DB_MYSQL_PORT 3306
ENV DB_MYSQL_DB 'packagebutler'
ENV DB_MYSQL_USER ''
ENV DB_MYSQL_PASSWORD ''

ENV PACKAGEBUTLER_BASEURL_LOGISTICS http://logistics.api.pb-internal
ENV PACKAGEBUTLER_BASEURL_MEMBER http://member.api.pb-internal
ENV PACKAGEBUTLER_BASEURL_ORDER http://order.api.pb-internal
ENV PACKAGEBUTLER_BASEURL_PAYMENT http://payment.api.pb-internal

CMD mkdir /app
WORKDIR /app

ADD app.jar /app/app.jar

EXPOSE 80

ENTRYPOINT ["java", "-jar", "app.jar"]
