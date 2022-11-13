# [MultiCampus Team Project] 

<p align="center"><img src="https://user-images.githubusercontent.com/101630615/196031067-dcde0d77-fb77-4e4e-9ec4-a28267ca789a.png"></p>



> 예술을 보다 쉽게

---

<br>

<br>

![ezgif com-gif-maker](https://user-images.githubusercontent.com/101630615/201294104-515c4901-95b8-46fc-9bb9-a7ecacc96188.gif)

<br>

<br>



## 🖼 서비스 소개

“뭔가 예술은 어려워😵‍💫”, “정보수집이 끝나지 않아🙄”, “링크 공유는 일상!🥱”

**Ai를 활용한 전시회 추천 및 전시 코스를 공유하는 서비스**입니다.

---

<br>

<br>

## 👫 팀원 소개

|  팀원  |                  Github                  |
| :----: | :--------------------------------------: |
| 김상훈 |  [Github](https://github.com/ksh950609)  |
| 김유나 | [Github](https://github.com/astrophilex) |
| 김태수 |  [Github](https://github.com/seoli0179)  |
| 이새힘 |   [Github](https://github.com/new-pow)   |
| 최상종 |  [Github](https://github.com/xangjong)   |

<br>

<br>



## 📝 팀 규칙

**1. 말하기 전에 생각했나요**?

- 공격적인 언행은 삼가해요.
- 상호 존중적인 수평적 관계를 지향합니다.



**2. 1일 2미팅**

- 매일 작업 전, 오전과 오후 미팅을 진행합니다.
  - 스탠드 업 미팅 : 월~금 10시 
  - 에프터 미팅 : 월~금 6시
- 스탠드 업 미팅은 당일 작업 계획을 짧게 공유해요.
- 에프터 미팅은 당일 작업 내역, 현황등을 브리핑합니다.



**3. 주 마무리는 회고로**

- 서로 아쉬웠던 점이나 서운한 점을 얘기합니다.
- 마음에 쌓아두는 것보다 털어놓고 사이가 돈독해져요.



**4. 자연스러운 만남 추구**

- 주 1회 이상은 만나서 작업해요.
- 비대면 진행하면서 어려웠던 점을 공유하고 작업합니다.



**5. 질문은 제가 합니다**

- 모르는 내용은 같이 공유해요!

  

**6. 기억 보단 기록을**

- 많은 내용을 다 기억할 수 없으니 기록해요.



**7. "지각" == "커피"**

- 시간은 금입니다. 
- 다른 사람 시간도 소중하니 약속 시간을 엄수해요.

---

<br>

<br>

## 🔍 핵심 기능

- 전시 정보 검색
- 사용자 전시 열람 기록 기반 및 전시 추천
- 전시회 인근 식당 등 검색 및 코스 저장, 공유
- 전시회 관람 기록 및 리뷰

---

<br>

<br>

## 📆 수행 일정

<img width="800" alt="timeline" src="https://user-images.githubusercontent.com/101630615/196031069-9dea2254-828a-46e7-93dc-e2ba74b03094.png">



---

<br>

<br>



## 💻 프로젝트 구조

<img width="850" alt="architecture" src="https://user-images.githubusercontent.com/101630615/198191536-a2563bcf-17a4-4009-a736-a62dfbbc8325.png">

---

<br>

<br>



## 💡 기술 스택

<p align="center"><img width="550" alt="stack" src="https://user-images.githubusercontent.com/101630615/196031068-afefd93a-7de6-4f24-a2ba-2dc4e2d22625.png"></p>

---

<br>

<br>

## 📚 Entity 설계



<img width="800" alt="Entity2" src="https://user-images.githubusercontent.com/101630615/196031066-ba558000-1157-40db-9b92-3c73f4e17219.png">



![Entity](https://user-images.githubusercontent.com/101630615/196031065-5cd1ef27-e59a-49c5-ad72-d63313d6b683.png)



---

<br>

<br>



## ✏️ 업데이트 내역 (버그 리포트)

- ver 1.1 (2022. 10. 14)

  - 서버 이전 : Naver Cloud Platform → Oracle Cloud
  - API Key 수정
  - README 업데이트

- ver 1.0 (2022. 09. 21) 배포

- ver 0.3 (error_2021 .09. 20)

  - 버그 수정

    - DB에 exhbnPlaceUrl 값이 없을 시 내가 작성한 코스 내용이 보이지 않는 에러 수정

    ```java
    Optional int parameter 'courseId' is present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type.
    ```

    - course detail 페이지에서 삭제 버튼 클릭 시 400 에러 수정

- ver 0.2 (error_0920)

  - 기능 구현
    - 추천 콘텐츠(ai items) 기능 추가
    - 회원 정보 수정 유효성 검사 기능 구현
    - 비밀번호 보이는 버튼 구현
    - 비 로그인 시 게시물 수정 접근 제한 구현
  - 버그 수정
    - 코스 댓글을 삭제하면 코스 스크랩이 삭제 되는 에러 수정
    - 전시 스크랩 삭제하면 새 창으로 열리는 에러 수정
    - 댓글 작성 후, input창이 초기화되지 않는 에러 수정
    - 코스 좌표 수정시 마크 위치 값 반환되는 않는 에러 수정

- ver 0.1 (error_0916)

  - 기능 구현
    - review 게시판 좋아요 기능 구현
  - 버그 수정
    - 코스 DB 쿼리문 수정

  ```sql
  -- 수정 전
  courseTag varchar(10)
  -- 수정 후
  courseTag varchar(100)
  ```



---

