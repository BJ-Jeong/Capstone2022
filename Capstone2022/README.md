# Capstone2022
대림대학교 컴정과 3학년 1반 - 캡스톤 디자인 1학기 작품 개발

깃허브-안드로이드 연동 및 업데이트 되는지 ??

## 보안
http 접속 기본 허용됨

## 서버 연동
build.gradle(:app) 파일의 `buildConfigField("String", "CORONA_URL", "\"http://10.0.2.2:8080/rest/corona\"")` 부분을 수정해, 서버 URL 으로 변경할 것

## 라이브러리
Lombok, Gson, RxJava, Volley, LeakCanary

## toolbar 수정 (20220609)
19:25
toolbar와 오브젝트가 겹치지 않게 수정
뒤로가기 버튼이 더 유연하게 움직이도록 수정

22:46
연락처 상세정보 xml,java 파일 구현
