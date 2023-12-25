# 5장 웹 어댑터 구성하기, 6장 영속성 어댑터 구성하기

- adapter : 'application' 과 상호작용
  - 그럼 application 은 무엇이냐?
    - controller : _주로 사용자와의 상호작용. 입력 받아 처리하고(비즈니스 서비스로 라우팅) 적절한 응답 생성_
    - persistent : _데이터를 영구적으로 저장, 유지_


- mybatis : query를 xml로 따로 관리함 -> 가독성 up, DBA tuning 용이(JAVA 문법 X)
- jdbcTemplate : ??(query 따로 관리하는방법 찾아보기)


- package 구조 고민 더 해보기 (네카라쿠배... 패키지구조 어떻게 하고있는지)
- ModelMapper vs MapStructure


- Validation
  - 어디서 해야하는지? Controller vs ~~Service~~
  - 모델의 현재상태에 접근 여부? 
    - 접근 X : Syntactical (e.g. ID길이)
    - 접근 O : Semantic (e.g. ID중복여부)
  - @Validated vs @Valid
    - 둘 다 Request(DTO) 객체에 대한 유효성 검사 활성화 -> DTO 클래스에 정의된 유효성 검사 어노테이션 실행됨
    - @Valid : _@RequestBody 와 함께 사용되며(@Valid @RequestBody UserRequest),_ \
        _HTTP 요청 본문을 객체로 변환할 때 검사_
    - @Validated : _파라미터 or 메소드 레벨에서의 유효성 검사 활성화?? -> 메소드 파라미터에서 검증 수행_
      

- @Transactional 메서드가 아닌 클래스 상단에 두는 경우?


- JPA + jdbcTemplate or mybatis 사용
---
### TODO
1. mybatis 제거
2. jdbcTemplate 추가
   1. service, repository (port, adapter 구현)
   2. 파일로 분리
   3. 각 파일에서 쿼리 가져오기
3. 유효성 검사 로직 작성 (@Validated vs @Valid)
4. entity에 @Entity 설정 및 유효성 검사 어노테이션 작성
5. JPA 기능 추가
   1. @Qualifier 등을 사용해서 두가지 다 사용할 수 있게 하기
6. 패키지 구조?