DiaryRoom Application
- 오늘의 날씨정보와 일기를 작성할 수 있는 어플
 
DiaryRoom 어플의 Tab은 총 3개의 Framgnet로 구성되어있다.
1. HomeFragment 2. DiaryFragment 3. SettingFragment


- HomeFragment
-> HomeFragment에서는 날씨정보(기온,습도,OOTD)를 확인 할 수 있다.
-> 날씨 정보는 data.go.kr의 api 정보를 기본으로 한다.
-> 날씨정보 뿐만아니라 해당 날씨에 맞는 옷차림을 추천을 해준다.

- DiaryFragment
-> DiaryFragment에서는 사용자가 일기를 작성할 수 있다.
-> 일기를 작성하기 위해 DiaryWrittingFragment 로 이동시 Title,Content이외에 이미지를 첨부할 수 있다.
-> 사용자는 날짜별로 자신이 쓴 일기를 DiaryFragment에서 확인할 수 있다.

- SettingFragment
-> 구상 진행 중 