# Sử dụng image chính thức của Maven với JDK
FROM maven:3.9-eclipse-temurin-17 AS build

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép toàn bộ project vào container
COPY . .

# Chạy lệnh build Maven để tạo file JAR
RUN mvn clean package -DskipTests

# Sử dụng một image nhẹ để chạy ứng dụng
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép file JAR từ giai đoạn build
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# run app
ENTRYPOINT ["java", "-jar", "app.jar"]