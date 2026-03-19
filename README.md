# Read.me

✔️ 사용자 API

##### 로그인
- URL : POST /auth/login
- 설명 : 아이디, 비밀번호를 통한 로그인
- Request
{
  "loginId": "TestId",
  "password": "test"
}

##### 로그아웃
- URL : POST /auth/logout
- 설명 : 로그아웃을 통한 세션 정리 + 쿠키 제거

##### 콘텐츠 생성
- URL : POST /contents
- 설명 : 제목(title), 내용(description)을 통한 콘텐츠 생성
- Request {
    "title" : "테스트",
    "description" : "테스트"
}

##### 콘텐츠 수정
- URL : PATCH /contents/patch
- 설명 : 제목, 내용을 수정하기 위한 기능(생성자만 수정 가능하지만 ADMIN도 수정 가능)
- Request {
    "id" : 1,
    "title" : "테스트",
    "description" : "테스트"
}

##### 콘텐츠 삭제
- URL : DELETE /contents/{contentId}
- 설명 : 생성한 콘텐츠를 삭제 (생성자만 삭제 가능하지만, ADMIN도 삭제 가능)

##### 콘텐츠 목록 조회
- URL : GET /contents/search
- 설명 : 생성된 콘텐츠들의 목록을 조회 (기본값 : size=5, sort = createdDate(생성시간), desc)

##### 콘텐츠 상세 조회
- URL : GET / contents/{contentId}
- 설명 : contentId의 콘텐츠를 상세 조회

✔️ 프로젝트 실행 방법
1. Application 실행을 통한 Local 서버 실행
2. localhost:8080/h2 와 http://localhost:8080/swagger-ui/index.html 를 통해 Swagger, h2 접속<br>
( JDBC URL : jdbc:h2:mem:testdb UserName : sa, Password : sa)
3. Swagger에 명시되어 있는 Controller들을 통한 실행 가능  (회원가입 -> 로그인 이후)
4. ADMIN 권한의 사용자가 수정 & 삭제기능은 /change controller를 통해 권한 변경 후 테스트 가능


✔️참고 자료 : https://velog.io/@rungoat/SpringBoot-Custom-Exception-%EC%B2%98%EB%A6%AC<br>
✔️AI : Gemini를 통한 Security 에러 해결

##### Swagger, Postman을 통한 로그아웃 시 쿠키 삭제 X, 개발자도구를 통한 삭제 필요

✔️로그인 구현 방식
- JWT 토큰을 사용하여 구현방식 = H2 데이터베이스에 Token 무제한 생성 ( Redis 추가 도입 필요 )
- Session = 세션메모리에 사용자 증가 시 세션데이터로 인한 부하가 생길 여지가 존재하지만, 현재 환경에서 가장 구현하기 적합하다고 생각했습니다.
- Cookie = SessionId, Memory = SessionData 방식으로 활용

