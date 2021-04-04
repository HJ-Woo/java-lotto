package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static lotto.domain.LottoNumber.LOWER_LOTTONUMBER_BOUND;
import static lotto.domain.LottoNumbers.LOTTO_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    private Lotto lotto;
    private LottoNumbers lottoNumbers;
    private Set<LottoNumber> numbers = new LinkedHashSet<>();

    @BeforeEach
    void setUp() { //given
        for (int i = LOWER_LOTTONUMBER_BOUND; i <= LOTTO_SIZE; i++) {
            numbers.add(new LottoNumber(i));
        }
        lottoNumbers = new LottoNumbers(numbers);
        lotto = new Lotto(lottoNumbers);
    }

    @Test
    void createTest() {
        //then
        assertThat(lotto).isEqualTo(new Lotto(lottoNumbers));
    }

    @Test
    void validLottoSize() {
        //given
        numbers.remove(new LottoNumber(LOTTO_SIZE));
        LottoNumbers testLottoNumbers = new LottoNumbers(numbers);

        //when, then
        assertThatThrownBy(() -> {
            Lotto testLotto = new Lotto(testLottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void containsTest() {
        //given
        int matchNum = 5;
        Set<LottoNumber> matchNumbers = new LinkedHashSet<>();
        for (int i = LOWER_LOTTONUMBER_BOUND + 1; i <= LOTTO_SIZE + 1; i++) {
            matchNumbers.add(new LottoNumber(i));
        }

        //when
        int allCorrect = lotto.contains(lottoNumbers);
        int fiveCorrect = lotto.contains(new LottoNumbers(matchNumbers));

        //then
        assertThat(allCorrect).isEqualTo(LOTTO_SIZE);
        assertThat(fiveCorrect).isEqualTo(matchNum);
    }


    @Test
    void containBounsTest() {
        //given
        LottoNumber cotainBonus = new LottoNumber(LOTTO_SIZE);
        LottoNumber notInBonus = new LottoNumber(LOTTO_SIZE + 1);

        //when, then
        assertThat(lotto.containsBouns(cotainBonus)).isTrue();
        assertThat(lotto.containsBouns(notInBonus)).isFalse();
    }

    @Test
    void printTest() {
        System.out.println(lotto.numbers());
    }
}
