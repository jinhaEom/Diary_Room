[![Android CI](https://github.com/jinhaEom/Diary_Room/actions/workflows/androidCi.yml/badge.svg)](https://github.com/jinhaEom/Diary_Room/actions/workflows/androidCi.yml)

DiaryRoom Application
- 오늘의 날씨정보를 알 수 있고 일기를 작성할 수 있는 어플

DiaryRoom 어플의 Tab은 총 3개의 Framgnet로 구성되어있다.
1. HomeFragment 2. DiaryFragment 3. SettingFragment

|화면|이미지|설명|
|:---|---:|:---:|
|HomeFragment|  <img src="https://github.com/jinhaEom/Diary_Room/assets/84216838/03a49769-7958-4c77-8f52-a12ec9fbf93f"  width="190" height="430"/> &nbsp;<img src="https://github.com/jinhaEom/Diary_Room/assets/84216838/c8ea560c-a7da-4ef9-bcae-fbe899241b48"  width="190" height="430"/>|날씨및 OOTD|
|DiaryFragment|  <img src="https://github.com/jinhaEom/Diary_Room/assets/84216838/591d95da-e684-4961-904c-d39226c1926e"  width="190" height="430"/> &nbsp; <img src="https://github.com/jinhaEom/Diary_Room/assets/84216838/e6aac755-973b-402d-981e-13d0770b185c" width="190" height="430"/> &nbsp; <img src="https://github.com/jinhaEom/Diary_Room/assets/84216838/f54cca45-265b-4bd4-b520-62227ec90a48"  width="190" height="430"/>|다이어리 작성|
|SettingFragment|구상 진행 중|구상 진행 중|
- HomeFragment
  + HomeFragment에서는 날씨정보(기온,습도),오늘의 추천의상을 확인 할 수 있다.
  + 날씨 정보는 data.go.kr의 api 정보를 기본으로 한다.
  + 오늘의 날씨정보와 일기를 쓰도록 하는 추천문구를 나타낸다.
  + Today's OOTD를 통해 오늘의 의상을 추천해준다.
  <br>
    <br>
  &nbsp;
- DiaryFragment
  + DiaryFragment에서는 사용자가 일기를 작성할 수 있다.
  + 일기를 작성하기 위해 DiaryWrittingFragment 로 이동시 Title,Content이외에 이미지를 첨부할 수 있다.
  + 사용자는 날짜별로 자신이 쓴 일기를 DiaryFragment에서 확인할 수 있다.

- SettingFragment
  + 구상 진행 중 
