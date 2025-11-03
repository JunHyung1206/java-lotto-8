package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class OutputViewTest {
    @Test
    @DisplayName("반올림 출력에 대한 테스트")
    void roundTest() {
        OutputView outputView = new OutputView();
        assertThat(outputView.round(1.4)).isEqualTo("1.4");
        assertThat(outputView.round(1.44)).isEqualTo("1.4");
        assertThat(outputView.round(1.49)).isEqualTo("1.5");
        assertThat(outputView.round(1.51)).isEqualTo("1.5");
        assertThat(outputView.round(1.54)).isEqualTo("1.5");
        assertThat(outputView.round(1.45)).isEqualTo("1.5");
        assertThat(outputView.round(1.4511111)).isEqualTo("1.5");
        assertThat(outputView.round(1.4499999)).isEqualTo("1.4");
    }
}