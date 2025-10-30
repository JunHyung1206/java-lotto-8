## 컨트롤러에서 하는 일이 너무 많다
```java
        // 2. 해당하는 개수만큼 로또를 받는다.
        int salesLottoCount = payment.getValue() / 1000;

        List<Lotto> saleLottos= new ArrayList<>();

        for (int i = 0; i < salesLottoCount; i++) {
            saleLottos.add(new Lotto(Randoms.pickUniqueNumbersInRange(
                    LottoNumberInfo.MIN_VALUE.getValue(),
                    LottoNumberInfo.MAX_VALUE.getValue(),
                    LottoNumberInfo.PICK_NUMBER_COUNT.getValue()))
            );
        }

```
- 티켓을 사는 것인데 이러한 로또를 판매하는 클래스를 하나 두어야 할 것 같다.
- 컨트롤러에서는 직접적으로 참여하지 않고 조립만 하는 형태로 흐름만 진행해야한다.

<br/> 


## 결과를 생성할 때의 코드도 너무 지저분하다.
```java
    public Map<LottoInfo, Integer> getResult(){
        Map<LottoInfo, Integer> result = new HashMap<>();
        for (LottoInfo value : LottoInfo.values()) {
            result.put(value, 0);
        }
        for (Lotto lotto : salesLotto) {
            Set<Integer> intersection = new HashSet<>(lotto.getNumbers());
            intersection.retainAll(selectedLotto.getNumbers());
            if (intersection.size() == 6) {
                result.put(LottoInfo.FIRST, result.get(LottoInfo.FIRST) + 1);
            }
            if ((intersection.size() == 5) && lotto.getNumbers().contains(bonusNumber.getValue())) {
                result.put(LottoInfo.SECOND, result.get(LottoInfo.SECOND) + 1);
            }
            if (intersection.size() == 5 && !lotto.getNumbers().contains(bonusNumber.getValue())) {
                result.put(LottoInfo.THIRD, result.get(LottoInfo.THIRD) + 1);
            }
            if (intersection.size() == 4) {
                result.put(LottoInfo.FOURTH, result.get(LottoInfo.FOURTH) + 1);
            }
            if (intersection.size() == 3) {
                result.put(LottoInfo.FIFTH, result.get(LottoInfo.FIFTH) + 1);
            }
        }
        return result;
    }
```
- 6개의 상태를 전부 if문으로 감쌌고, 안에서 하는 동작들은 1 더하는 것이 전부 동일하다. 이를 좀 더 구조화할 수 있도록 하는 방안이 필요할 것 같다.
- 위의 코드는 또 초기화하는 동작이고 여러모로 지저분한 것 같다.

<br/> 

## 출력 코드도 매우 지저분하다.
```java
    public void printResult(Map<LottoInfo, Integer> result, Payment payment) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        for (LottoInfo lottoInfo : LottoInfo.values()) {
            System.out.println(lottoInfo.getDescription() + " - " + result.get(lottoInfo)+"개");
        }
        int sum = 0;
        for (LottoInfo lottoInfo : result.keySet()) {
            sum += result.get(lottoInfo) * lottoInfo.getMoney();
        }

        Double d = (double)sum / payment.getValue() * 100;
        System.out.println("총 수익률은 "+ d+"%입니다.");
    }
```
- 값을 도메인에서 받아와서 그대로 사용하고 있고, 수익률을 계산하는 과정도 그대로 노출되어 있다.
