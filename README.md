# Read.me

<h4>API 설명</h4>
<ul>
  <li>콘텐츠 생성(제목, 내용) </li>
  <h5>- 제목 & 내용 필수입력(@Valid 활용)</h5>
  <li>콘텐츠 수정(제목, 내용) + ADMIN 수정 가능</li>
  <h5>- 제목 & 내용 필수 입력(@Valid 활용) + DTO를 통해 Role을 전달받아 Admin이면 수정 가능하도록 설계</h5>
  <li>콘텐츠 삭제 + ADMIN 삭제 가능</li>
  <h5>- contentId를 통해 원하는 콘텐츠 삭제 + Admin이면 삭제 가능</h5>
  <li>콘텐츠 목록 조회 (Page 처리, createdDate 기준 DESC 5개)</li>
  <h5>- Page처리 (기본값 : size=5, sort = createdDate, DESC) </h5>
  <li>콘텐츠 상세 조회 (회원만 가능)</li>
  <h5>- contentId를 통해 상세 조회</h5>
  <li>로그인</li>
  <h5>- id, password를 통해 로그인 가능</h5>
  <li>로그아웃</li>
  <h5>세션 만료 + 쿠키 삭제를 통한 로그아웃</h5>
  <li>회원가입</li>
  <h5>- id, password, nickname을 입력받은 후 기본 Role = Role.USER를 통해 회원가입 </h5>
  <h5>- id, nickname은 Validation을 통해 중복체크하여 예외 발생</h5>
</ul>
<ul>
  <li>참고 자료 : https://velog.io/@rungoat/SpringBoot-Custom-Exception-%EC%B2%98%EB%A6%AC</li>
  <li>AI : Gemini를 통한 Security 에러 해결</li>
</ul>

<h4>Swagger, Postman을 통한 로그아웃 시 쿠키 삭제 X, 개발자도구를 통한 삭제 필요</h4>
