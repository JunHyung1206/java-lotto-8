## 결과를 출력하는 코드 리펙토링 필요
```java
    private static final WinningRank[] PRINT_ORDER = {
        WinningRank.FIFTH,   // 3개 일치
        WinningRank.FOURTH,  // 4개 일치
        WinningRank.THIRD,   // 5개 일치
        WinningRank.SECOND,  // 5개 + 보너스
        WinningRank.FIRST    // 6개 일치
    };

    public void printResult(ResultStatisticsDTO resultStatisticsDTO) {
    printResultHeader();
    DecimalFormat moneyFormat = new DecimalFormat("###,###");
    for (WinningRank rank : PRINT_ORDER) {
        long prize = rank.getPrize();
        int count = resultStatisticsDTO.getResult().getOrDefault(rank, 0);
        System.out.println(rank.getLabel() + " (" + moneyFormat.format(prize) + "원) - " + count + "개");
    }

    DecimalFormat percentFormat = new DecimalFormat("#,##0.0");
    System.out.println("총 수익률은 " + percentFormat.format(resultStatisticsDTO.getRateOfReturn()) + "%입니다.");
}
```
- 어느정도 DTO를 통해서 모델 값을 안보이도록 하였고, 함수 또한 어느정도 정제되었다.
- 하지만 WinningRank는 모델에서 사용되는 로직이기 때문에 View에서는 이를 안보이도록 하는 과정이 필요하다. 이를 어디서 처리를 해야할지 한번 고민을 해봐야할 것 같다.

<br/> 


## 예외 처리에 대한 고민
```java
public enum ErrorMessages {
    NOT_LOTTO_COUNT_ERROR("모든 번호는 " + LottoInfo.PICK_NUMBER_COUNT.getValue() + "개여야 합니다."),
    OUT_OF_RANGE_ERROR("로또 번호는 " + LottoInfo.MIN_VALUE.getValue() + "부터 " + LottoInfo.MAX_VALUE.getValue() + " 사이의 숫자여야 합니다."),
    DUPLICATE_ERROR("중복된 정수가 있습니다."),
    SALES_ERROR("로또는 " + LottoInfo.LOTTO_PRICE.getValue() + "원 단위로 판매합니다."),
    MIN_PAYMENT_ERROR("구매액은 1원 이상입니다."),
    INVALID_INPUT_ERROR("입력 형식이 올바르지 않습니다.");
    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}


public class LottoValidationException extends IllegalArgumentException {
    private static final String PREFIX = "[ERROR] ";
    private final ErrorMessages code;

    public LottoValidationException(ErrorMessages code) {
        super(PREFIX + code.getMessage());
        this.code = code;
    }

    @Override
    public String getMessage() {
        return PREFIX + code.getMessage();
    }
}
```
- 모든 처리가 현재 [ERROR]를 출력하고 다시 재입력을 시도하는 로직이기 때문에, Base Exception을 하나만 두고, 예외메시지를 상수로 처리하였다.
- 하지만 Payment(SALES_ERROR, MIN_PAYMENT_ERROR)에서 나는 오류, 형식 오류(INVALID_INPUT_ERROR), Lotto(그외)에서 나는 오류는 별개의 오류이기 때문에 분리가 필요해보인다.

<br/> 


## Enum 상수 처리에 대한 고민
```java
public enum LottoInfo {
    MIN_VALUE(1),
    MAX_VALUE(45),
    PICK_NUMBER_COUNT(6),
    LOTTO_PRICE(1000);

    private final int value;

    LottoInfo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
```
- WinningRank는 비슷한 특성을 가진 상수들이 모여서 이루고 있는 열거형이다. 하지만 위의 LottoInfo는 전부 다 다른 특성을 가진 상수 덩어리이다.
- 이를 Enum으로 처리하는 것이 맞을지 아니면 클래스를 생성해서 public final static을 써야할지 잘 모르겠다. MIN, MAX는 어느정도 동일하긴 하지만, 굳이 두개를 위해서 다른 정보를 쪼개고 싶진 않다.

<br/> 


## 컨트롤러에 대한 책임이 너무 많다.
```java
public class LottoController {
    ...

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
    
    ...
}
```
- run에서 하는 일이 너무 많은 것 같다.
- 입력을 받고, 이를 생성하고 처리해서 출력을 하는 내용이긴 한데, 로직이 매우 길고 복잡해서 잘 안읽힌다.
- MVC 패턴을 지향하고 있는 프레임워크들은 어떻게 처리하는지 확인해봐야할 것 같다.

우선 고민은 계속 생기지만 이 고민에 대해서 리펙토링을 하는 것을 목표로 하자.