# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.


## 1단계 - 문자열 덧셈 계산기
### 기능 요구사항
- 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환 (예: “” => 0, "1,2" => 3, "1,2,3" => 6, “1,2:3” => 6)
- 앞의 기본 구분자(쉼표, 콜론)외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 “//”와 “\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다. 예를 들어 “//;\n1;2;3”과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
- 문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw한다.


## 2단계 - 로또(자동)
### 기능 요구사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.
```
구입금액을 입력해 주세요.
14000
14개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```

### 기능 구현 목록
**Domain**
- Lotto (6개의 로또번호를 지니는 클래스)
  - [X] 6개의 순서를 지닌 고유 번호를 지닌다.
  - [X] 같은 갯수의 당첨 번호를 받아 몇개가 일치하는지 반환한다.
    
- lottos (n개의 로또를 지니는 클래스)
  - [X] 로또의 집합을 보유한다.
  - [ ] Winning을 입력받아 몇 개가 당첨되었는지에 대한 로직을 수행하여 해당 winning을 반환한다.
  - [ ] 문자열로 반환시 보유중인 각 로또들의 번호목록을 구분자 \n로 두어 리스트의 문자열 형태로 반환한다.  

- Winning (당첨 번호와 당첨수를 지니는 클래스)
  - [X] 당첨되는 일치수를 보유한다. (3개, 4개, 5개, 6개 일치)
  - [X] 생성 초기화시 당첨번호를 할당한다.
  - [ ] 각 일치수를 증가시킬 수 있다.
  - [ ] 문자열로 반환시 각 일치하는 수와 상수값의 당첨금액, 그리고 해당 일치 갯수를 \를 구분자로 두는 문자열 형태로 반환한다.

- Rule (당첨 통계를 계산하는 유틸 클래스)
    - [ ] 구매 금액과 Winning을 바탕으로 수익률을 계산한다.
    
- LottoFactory (로또 생성을 관할하는 팩토리 클래스)
  - [ ] 랜덤한 번호를 가지는 로또를 생성하여 반환한다.
  - [ ] 생성할 로또의 개수를 입력시 해당 갯수만큼의 로또를 보유중인 로또리스트를 반환한다.
    

**UI**
- InputView
  - [ ] 구입 금액을 입력받는다.
  - [ ] 당첨 번호를 입력받는다.  
    
- ResultView
  - [ ] 구매한 로또의 번호를 출력한다.
  - [ ] 당첨통계를 출력한다.
    