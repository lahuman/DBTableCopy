# DBTableCopy

특정 DBMS의 테이블의 데이터를 다른 DBMS 테이블로 복사하는 간단한 프로그램

## Getting Started

lahuman.Migration 파일의 10번째 라인과 11번째 라인의 DB 접속 정보를 입력 한다.

```
//Line 10~11
Connection originalDB = DriverManager.getConnection("URL", "ID", "PW"); //원본 DBMS
Connection targetDB = DriverManager.getConnection("URL", "ID", "PW"); //복제예정 DBMS
```
lahuman.Migration 파일의 13번째 라인의 SQL을 작성한다.
이때 작성하는 SELECT SQL과 INSERT SQL의 Column 순서는 맞아야 한다.
 
```
//LINE 13
copyTableUsingSQL(originalDB, targetDB, "select A, B, C from T1", "insert into T2(A,B,C)values(?,?,?)");
```

### Prerequisites

Gradle 가 설치 되어 있으면 된다.

JDK 1.8 이상을 권장 한다.

### Installing

Gradle을 이용한 빌드가 기본이다.

```
gradle build
```

jar 형식 배포를 하려고 한다면 다음과 같이 처리 한다.

```
gradle jar
```



## Deployment

설치를 위하여 필요한 모듈 정보

## Built With

* [Java](https://java.com/ko/download/) - The Java Deployment Kit 
* [Gradle](https://gradle.org/) - Dependency Management


## License

This project is licensed under the MIT License - see the [LICENSE](https://opensource.org/licenses/MIT) file for details