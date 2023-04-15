# 베이스 이미지 설정
FROM openjdk:17-alpine AS build

# 작업 디렉토리 설정
WORKDIR ./

# Gradle 빌드를 위해 필요한 파일 복사
COPY gradlew gradlew.bat settings.gradle ./
COPY gradle ./gradle
RUN ./gradlew --version

# 의존성 다운로드 및 빌드 실행
COPY build.gradle .
COPY src ./src
RUN ./gradlew clean build

# 빌드가 끝난 어플리케이션을 실행할 이미지 생성
FROM openjdk:17-alpine

# 작업 디렉토리 설정
WORKDIR ./

# 빌드된 어플리케이션 파일 복사
COPY --from=build /build/libs/*.jar app.jar

# 어플리케이션 실행
CMD ["java", "-jar", "app.jar"]