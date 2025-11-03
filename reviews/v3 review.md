## v2의 의문점에 대한 해결
### 결과를 출력하는 코드 리펙토링 
- Mapper에 PrintOrder를 두기로 하고, DTO에서 이 순서대로 넣기로 하였다.
- 위에 따라 View는 전혀 도메인 정보를 모르고 결과 값만 가지고 출력을 할 수 있다.

### 예외 처리 리팩토링
- getMessage와 생성자 부분이 중복되어 구현되어 있어 이를 수정하였다.
- 요구하는 공통 에러는 두고, 이를 상속받아 각각의 상황에 맞는 에러를 반환하도록 하였다.
- ErrorMessage는 일괄적으로 처리하고 있지만, 나중에 프로젝트 규모가 더 커지고 logging이 중요한 시점일 때 분리를 할 예정이다.
- 아직 의문인 것은 InputView에서와 도메인에서 각각의 예외를 어떤 식으로 처리하는지 잘 모르겠다.
  - 문법 자체의 오류는 뷰에서 검증하고, 비즈니스 로직에서 오류는 도메인 객체내에서 검증하기로 하였다.

### Enum 상수 처리
- WinningRank는 비슷한 특성을 가진 상수들이 모여서 이루고 있는 열거형이다. 하지만 위의 LottoInfo는 전부 다 다른 특성을 가진 상수 덩어리이다.
- 비슷한 특성을 가진 상수들 ErrorMessage와 Rank등은 Enum으로 관리를 하고, 다른 값만을 가진 정보는 class에 static final을 사용하는 것이 가독성 측면과 응집도 측면에서 더 낫다고 생각하였다.

### 컨트롤러에 대한 책임 분리
- 리팩토링 전 
```java
public class LottoController {
    public void run() {
        Payment payment = getPayment();
        LottoSeller lottoSeller = new LottoSeller(new RandomLottoGenerator());
        List<Lotto> lottos = lottoSeller.sellLottos(payment);
        outputView.printSalesLotto(LottoMapper.toLottoDTO(lottos));
        Lotto mainNumbers = getSelectedLotto();
        BonusNumber bonusNumber = getBonusNumber(mainNumbers);
        WinningNumbers winningNumbers = new WinningNumbers(mainNumbers, bonusNumber);
        ResultStatistics resultStatistics = new ResultStatistics(lottos, winningNumbers);
        outputView.printResult(LottoMapper.toResultStatisticsDTO(resultStatistics));
    }
}
```
- 리팩토링 후
```java
    public void run() {
        Payment payment = getPayment();
        List<Lotto> purchasedLottos = lottoPurchaseService.purchase(payment);
        outputView.printPurchasedLotto(LottoMapper.toPurchasedLottoDTO(purchasedLottos));

        WinningNumbers winningNumbers = getWinningNumbers();
        WinningResult winningResult = lottoResultService.calculateResult(purchasedLottos, winningNumbers);
        ResultStatistics resultStatistics = lottoResultService.aggregate(winningResult);
        outputView.printResult(LottoMapper.toResultsDataDTO(winningResult, resultStatistics));
    }
```
- 컨트롤러가 생성을 하고, 이를 통해서 결과물을 만드는 책임을 Service계층을 두어 해결하였다.
- 각 서비스에서 유즈케이스를 담당하고, 입력 -> 흐름 -> 출력의 흐름만 컨트롤러가 담당하여, 컨트롤러가 객체를 생성하고 값을 할당하는 책임을 줄일 수 있었다.

## Enum의 효과
- 그 도메인의 규칙을 한 곳에서 넣어, 검증하고 바로 반환할 수 있어서 응집도가 높아졌다고 느꼈다.
- 규칙이 변하더라도 외부의 코드는 변화 없이 바로 사용할 수 있었다. Enum의 캡슐화 효과를 느낄 수 있었다.
- if문을 과도하게 많이 사용해서 의문이었는데 배열로 바로 count를 반환을 해서 불필요한 if문을 줄일 수 있었다.

## 테스트 코드 작성
- 단위 테스트 코드를 작성하면서, 각각의 예외에 대해서 계속 생각하고 확장할 수 있었다. 
- 프로그램 흐름에 맞게 실행된다면 Null값이 나올 수가 없는 구조인데도, 이때 null을 값으로 넣으면 이렇게 반환해야겠다는 생각이 많이 들었었다.
- ex) 수익률 반환을 할 때 만약 0장을 샀다면 어떻게 처리해야할지

## 서비스에서 동작을 책임질지, 도메인에서 책임을 질지는 아직 고민이다. 
- 처음에는 ResultStatistics에서 전부 값을 계산하고, 이를 반환하도록 하였었다. 
- 하지만 프로젝트를 계속 진행하면서 도메인에서는 불변 값만 가지고 있고 계산하는 로직은 Service에서 처리를 하는 것이 더 맞지 않을까라는 생각이 들었었다.
- 하지만 서비스에서 이를 처리하다 보니 getter 메소드가 많이 필요했었고, 가독성이 떨어지고, 응집도도 많이 떨어지는 것 같았다.
- 그래서 도메인에 일단 다시 넣어서 완성했었는데, 이러면 서비스가 행위를 해야하는게 맞지 않나라는 생각이 들었었다. 
- 우선 이부분에 대해서는 계산 책임은 각 도메인이 맡도록 하고, 이 흐름을 유지해서 유즈케이스를 담당하는 것은 Service에서 맡는 것이 흐름상 적절하다고 느껴졌다.