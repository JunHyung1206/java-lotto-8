package lotto.domain;

import lotto.exception.ErrorMessages;
import lotto.exception.WinningNumbersInvalidException;


public class WinningNumbers {
    private final Lotto mainNumbers;
    private final BonusNumber bonusNumber;

    public WinningNumbers(Lotto mainNumbers, BonusNumber bonusNumber) {
        validate(mainNumbers, bonusNumber);
        this.mainNumbers = mainNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto mainNumbers, BonusNumber bonusNumber) {
        if (mainNumbers == null || bonusNumber == null) {
            throw new WinningNumbersInvalidException(ErrorMessages.INVALID_INPUT_ERROR);
        }
        if (isDuplicate(mainNumbers, bonusNumber)) {
            throw new WinningNumbersInvalidException(ErrorMessages.DUPLICATE_ERROR);
        }
    }

    private boolean isDuplicate(Lotto mainNumbers, BonusNumber bonusNumber) {
        return mainNumbers.getNumbers().contains(bonusNumber.getValue());
    }

    public Lotto getMainNumbers() {
        return mainNumbers;
    }

    public BonusNumber getBonusNumber() {
        return bonusNumber;
    }
}
