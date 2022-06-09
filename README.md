# yanolja-clone
## 프로젝트 목표

- 야놀자 서비스 중 숙소 검색 및 예약 페이지의 API를 구현해보는 것
- JPA를 통해 DB와 연결하고, 서비스에 맞는 테이블 릴레이션 설정
- Front-end 개발자가 API를 사용하기 쉽게 일정한 형식의 Response(Error 메세지 포함) 리턴

## 테크니컬 이슈

- 숙소 검색(조회)는 트래픽이 많을 것으로 예상하여 Join을 하지 않도록 테이블 구성
- 체크인/체크아웃 날짜를 기반으로 숙소 검색 및 예약을 할 때 빈 방이 있는지 계산 (숙소 방 개수 정보와 해당 기간의 예약 내역을 비교하여 도출)
- 안전한 서비스 운영을 위해 최대한 방어적인 예외처리를 하거나, 비즈니스 로직에서 제한 설정(ex: 예약 가능한 기간은 최대 7일, 빈 방 검색 가능한 기간은 오늘로부터 한달 이내)
- 고객, 숙소, 예약내역, 지역코드 테이블의 N:1 관계를 JPA로 구현 (조회시 무한 참조 방지, insert 및 update 시 릴레이션 객체 생성 등)
- CommonResponse로 API의 응답 타입 형식화
- CustomException과 CommonCode로 에러 메세지 관리

## 유저 관점에서의 시나리오

![user-scenario](https://user-images.githubusercontent.com/56625356/172689392-02488524-10ca-42f9-9565-33a2fcd5ff47.png)


- Back-end API만 구현

## 사용 기술 및 환경

- Spring Boot
- JPA
- PostgreSql
- Java11

## DB 설계

![erd](https://user-images.githubusercontent.com/56625356/172689436-e04f4907-2955-4a84-a0da-960c641e4f9e.png)


## 브랜치 관리 전략

- Issue 생성 → Issue 번호를 딴 branch 생성
- 목적에 따라 feat, refact, debug 폴더로 branch 관리
- 개발 완료 후 main branch 내용을 current branch로 Merge한 후 Pull Request

## API Document

- [Reservation 기능 관련 API Link](https://documenter.getpostman.com/view/20884244/Uz5JHFG5)
- [Accommodation 기능 관련 API Link](https://documenter.getpostman.com/view/15580972/Uz5JHFBn)
