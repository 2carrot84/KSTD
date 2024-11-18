### 개발 언어, 프레임워크, RDBMS
> java : openjdk 17.0.12 2024-07-16  
> spring boot : 3.3.5  
> gradle : Gradle 8.10.1  
> RDMBS : H2

- 실행 방법
> 프로젝트 루트에서 shell 접속
```shell
./gradlew bootRun
```
> swagger 접속 후 테스트 진행  
> http://localhost:8080/swagger-ui/index.html#/


### 데이터 설계
강연 테이블 과 강연 신청 1:N 관계로 설정

1. 강연 테이블
```java
@Entity
@Table(name = "lecture")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private LocalDateTime startDateTime;
	@Column
	private String speaker;
	@Column
	private String room;
	@Column
	private int maxApplicantCount;
	@Column
	private String content;
	@OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY)
	@Builder.Default
	private List<ApplyLectureEntity> applicants = new ArrayList<>();
}
```

2. 강연 신청 테이블
```java
@Entity
@Table(name = "apply_lecture")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyLectureEntity {
	@EmbeddedId
	private ApplyLectureId applyLectureId;
	@Column
	@CreationTimestamp
	private LocalDateTime applyDateTime;
	@MapsId("lectureId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lecture_id")
	private LectureEntity lecture;
}
```

### 그 밖에 고민 or 설명할 부분
1. 클린아키텍처 기반 설계
1. BackOffice 와 Front API 분리
   - bfb(Backend For BackOffice), bff(Backend For Front) 로 패키지 및 controller 분리
1. 강연신청 시 제약조건
   - 중복신청 불가는 강연 신청 테이블의 키를 복합키로(강연 ID, 사번)으로 제어
   - 강연신청 인원 초과 시 신청 불가 하도록 구현
   - 강연시작일시 지난 강연은 신청 불가 하도록 구현
1. 실시간 인기 강연 구현 방안 
    - 단순 쿼리 조회로 실시간성은 만족할 수 있으나, 장기적으로 성능 이슈가 있을 수 있어 아래와 같이 구현
    - embedded redis 의 sorted set 데이터 타입 이용하여 신청자 순으로 정렬
      - 강연 id 와 신청자 수만 redis 에 기록
      - redis 조회 후 상세 정보는 DB 조회 수행
    - 데이터 일관성을 위해 1시간 주기 전체 색인 로직 수행 
      - @Scheduled 사용하여 간략히 구현
      - DB 기준으로 데이터 일관성 유지
    - 실시간성 유지를 위해 강연 신청, 취소시 redis 데이터 즉시 반영 
      - 신청, 취소시 incrementScore 을 통해 빠른 연산 수행 
      - spring event 와 @Async 를 통해 비동기 처리
1. 동시성 이슈 
   - 특정 강의 신청에 트래픽이 몰릴 경우를 대비하여, redis 분산락을 통하여 동시성 제어
     - [참고자료](https://helloworld.kurly.com/blog/distributed-redisson-lock/)
1. 입력값 검증은 validation 을 이용하여, application 단에서 처리
1. 계층간 객체 변환은 Mapper 클래스 직접 구현
1. JPA N+1 이슈 방지를 위해 QueryDSL 을 이용한 fetch join 사용
   - 연관 테이블 조회 불필요한 경우 Mapper 클래스에서 해당 항목 제외
1. 테스트 코드
    - persistence adapter, service, domain 위주로 테스트 코드 작성
    - 특별한 로직 없이 계층간 메서드 호출, 데이터 전달만 하는 경우는 테스트 생략

### 테스트 방법
1. BackOffice
- 강연 목록 (전체 강연 목록)
  - [GET /bfb/lectures](http://localhost:8080/swagger-ui/index.html#/find-lectures-for-bfb-controller/findAll)
- 강연 등록 
  - [POST /bfb/lectures](http://localhost:8080/swagger-ui/index.html#/create-lecture-controller/create)
```json
{
"name": "신규강연",
"speaker": "강연자",
"room": "강의실",
"content": "강의내용",
"maxApplicantCount": 10,
"startDateTime": "2024-12-14 14:00"
}
```
- 강연 신청자 목록
  - [GET /bfb/lectures/3](http://localhost:8080/swagger-ui/index.html#/find-lectures-for-bfb-controller/findById)

2. Front
- 강연 목록
  - [GET /bff/lectures](http://localhost:8080/swagger-ui/index.html#/find-lectures-for-bff-controller/findAllBy)
- 강연 신청
  - [POST /bff/lectures/apply](http://localhost:8080/swagger-ui/index.html#/apply-lecture-controller/apply)
```json
{
   "lectureId": 3,
   "employeeId": "12345"
}
```
- 신청 내역 조회
  - [GET /bff/lectures/AAAAA/apply](http://localhost:8080/swagger-ui/index.html#/find-apply-lectures-controller/findBy)
- 신청한 강연 취소
  - [DELETE /bff/lectures/3/AAAAA/apply](http://localhost:8080/swagger-ui/index.html#/cancel-apply-lecture-controller/cancel)
- 실시간 인기 강연
  - [GET /bff/lectures/popular](http://localhost:8080/swagger-ui/index.html#/find-popular-lectures-controller/findPopularLectures)



