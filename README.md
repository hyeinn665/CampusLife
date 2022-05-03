# CampusLife 
🎮 <b>대학생과 교수를 위한 간편하고 깔끔한 커뮤니티</b>
<br><br>
제작기간 : 2022.04.01 ~ 2022.04.18<br>
제작인원 : 5명
<br><br>
## 📖 제작의도
* 필요한 정보와 기능만 담긴 대학교 커뮤니티를 제작하고자 하였습니다.  
* 깔끔한 UI를 더해 직관성을 높여 사용자의 편의성을 제공하고자 하였습니다.
<br><br>
## 🛠️ 사용 기술 및 라이브러리
- JAVA, Oracle, Apache
- HTML5, CSS
- Java Script, jQuery
- JSP, Ajax
<br><br>
## 💻 담당한 기능
- DB구조 설계
- 중고 책 거래 게시판 글 작성 및 수정, 삭제 기능
- 검색 시 키워드에 맞는 자동 완성 기능
- 댓글, 대댓글 기능
- 비밀 댓글(익명) 체크 기능
<br><br>
## 📖 상세 내용
- 필요한 정보를 직관적으로 확인 가능한 깔끔한 UI의 대학교 커뮤니티 프로젝트
- 시간표에 강의를 추가하고 수정할 수 있고 수강했던 수업의 리뷰를 쓸 수 있는 게시판
- 학생들끼리 자유롭게 글을 써 의견을 나눌 수 있는 자유 게시판
- 지난 학기에 사용했던 책을 자유롭게 거래할 수 있는 게시판
- 학교의 공지사항를 확인할 수 있는 게시판
<br><br>
## 🗨️ 후기
- git 사용을 모르는 팀원들이 있어 github 사용을 하지 못했고 그로 인해 파일을 직접 합쳐야 했다. 그러다보니 오류가 너무 많아서 git, github의 중요성을 깨달았다. 다음 프로젝트 전까지 열심히 공부해서 꼭 사용해보고 싶다.
- 비밀 댓글 기능을 구현하고자 하여 쿼리를 수정하고 jsp파일에서 조건문을 수정했다. 작동을 시켜보니 Null Point Exception 에러가 발생하였고 코드오류임을 깨닫고 코드를 확인한 결과, 비밀댓글임을 알려주는 데이터로 회원에 따라 달리 보이게 하는 조건문에서 오류가 난 것을 확인했다. 글쓴이와 댓글작성자, 관리자만 보이게 하도록 하는 조건문에서 로그인하지않는 경우를 추가하여 코드를 수정하여 해결하였다.
- 글작성 페이지에서 과목을 필수 선택으로 구현하고자 과목 DB에서 외래키로 받아오는 과정에서 과목의 ID가 아닌 기본키인 고유번호에서 과목ID로 수정하고자 했다. 과목담당인 팀원에게 ID에 unique 제약조건을 걸어줄 것을 요청했으나 같은 과목명으로 여러 교수가 담당할 수도 있기에 적용해 줄 수 없다고 하였다. 고민해본 결과 고유번호를 Key, 과목명을 value로 가지는 HashMap를 통해 구현할 수 있었다.
- 실시간 쪽지 기능을 넣어 거래기능을 강화시키고 싶었지만 시간 부족으로 비밀댓글로 대체했다. 다음 버전에서 업데이트할 예정이다.
<br><br>
## 💡 구현 화면
<img src="https://user-images.githubusercontent.com/96910427/165787856-9112e623-42dc-41f1-b25d-b1668120f09f.png" width="800" height="1000" style="border: 1px solid #ccc;">
<img src="https://user-images.githubusercontent.com/96910427/165790079-4cedb11f-7cc0-40d5-8968-07c89d712bc3.png" width="800" height="1000">
<img src="https://user-images.githubusercontent.com/96910427/165790991-b6fca963-e450-458e-b51c-69debaec9f2f.png" width="800" height="1500" style="border: 1px solid #ccc;">
<img src="https://user-images.githubusercontent.com/96910427/165793290-8c81bbaa-a421-4383-9cae-657c7812610d.gif">
<br><br>
## 👀 ERD
<img src="https://user-images.githubusercontent.com/96910427/166250744-98c85951-3997-4c8d-8bc3-f15439ac30fe.png">
