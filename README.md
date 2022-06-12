# yanolja-clone

## 사용 기술 및 환경
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=plastic&logo=Springboot&logoColor=white"/> <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=plastic&logo=PostgreSQL&logoColor=white"> <img src="https://img.shields.io/badge/JAVA11-F7901E?style=plastic&logo=Java&logoColor=white"/> <img src="https://img.shields.io/badge/JPA-6236FF?style=plastic&logo=JPA&logoColor=white"/>


## 프로젝트 목표

- 야놀자 서비스 중 숙소 검색 및 예약 페이지의 API를 구현해보는 것
- JPA를 통해 DB와 연결하고, 서비스에 맞는 테이블 릴레이션 설정
- Front-end 개발자가 API를 사용하기 쉽게 일정한 형식의 Response(Error 메세지 포함) 리턴


## 유저 관점에서의 시나리오

![yanolja-clone-scenario](https://user-images.githubusercontent.com/56625356/173225097-422b97a4-cdab-4539-9cdb-f3edc65a2434.png)


- Back-end API만 구현


## DB 설계(ERD)

![erd](https://user-images.githubusercontent.com/56625356/172689436-e04f4907-2955-4a84-a0da-960c641e4f9e.png)


## 브랜치 관리 전략

- Issue 생성 → Issue 번호를 딴 branch 생성
- 목적에 따라 feat, refact, debug 폴더로 branch 관리
 ```java
keyword
- feat : 새로운 기능이 추가 됨
- refact : 기능 혹은 성능 개선
- debug : 버그 수정
```
- 개발 완료 후 main branch 내용을 current branch로 Merge한 후 Pull Request

## ISSUE 및 해결방안

- 직접쿼리 작성 시 결과값에 대한 convert 이슈
    - JPQL 쿼리 작성 및 DTO로 결과 반환
- 예약 Entity 양방향 관계로 인한 StackOverFlow 이슈
    - Json으로 직렬화할 속성 무시 (@JsonIgnore)
    - 직렬화할 대상 결과객체 외 다른객체 제외 (@ToString(exclude =...))
- postsql 예약어 사용으로 인한 Syntax 이슈
    - User테이블 명 변경(User → Customer)
- OneToMany 릴레이션으로 DAO 생성했으나 숙소 조회(select)할 때 마다 매번 Join을 해야해서 성능 저하 발생
    - 릴레이션 해제하고, Detail한 정보 필요시에만 쿼리를 두번(N+1) 실행하여 조회
- 호텔 측에 남아있는 방의 개수를 초과해서 예약을 할 수 있는 문제
    - 체크인/체크아웃 날짜를 기반으로 숙소 검색 및 예약을 할 때 빈 방이 있는지 계산 (숙소 방 개수 정보와 해당 기간의 예약 내역을 비교하여 도출)
- 체크인- 체크아웃 기간을 지나치게 길게 설정하여 빈 방을 검색하는 경우 loop가 길어져 부하를 주는 문제
    - 비즈니스 로직에서 제한 설정 (ex: 예약 가능한 기간은 최대 7일, 빈 방 검색 가능한 기간은 오늘로부터 한달 이내)
    - 이에 따른 유효성 검증 및 예외처리 추가
    - 상대적으로 빈 방 계산 정확도가 덜 중요한 숙소 검색 기능에서는 체크인 날짜만 체크, 정확도가 중요한 예약시에는 체크인 - 체크아웃 기간의 모든 날짜의 빈방 확인
- Front-end에서 API Response를 받아 처리할 때 데이터 형식이 제각각이면 일관성 있는 처리가 어려운 문제
    - CommonResponse로 API의 응답 타입 형식화
- 일일이 커스텀 예외 클래스를 만들면 지나치케 클래스가 많아지는 문제
    - CustomException과 CommonCode로 에러 메세지 공통 관리


## API Document

- [Reservation 기능 관련 API Link](https://documenter.getpostman.com/view/20884244/Uz5JHFG5)
- [Accommodation 기능 관련 API Link](https://documenter.getpostman.com/view/15580972/Uz5JHFBn)
