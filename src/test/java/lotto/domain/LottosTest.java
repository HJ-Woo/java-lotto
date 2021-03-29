package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static lotto.domain.WinningNumbersTest.WINNING_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    private static int LOTTO_LIST_SIZE = 10;
    private static int LOTTO_SIZE = 6;
    private static int LOTTO_BOUND = 45;

    private Lottos lottos;
    private List<Lotto> lottoList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        Random random = new Random();
        for (int i = 0; i < LOTTO_LIST_SIZE; i++) {
            List<LottoNumber> numbers = new ArrayList<>();
            for (int j = 0; j < LOTTO_SIZE; j++) {
                numbers.add(new LottoNumber(1 + random.nextInt(LOTTO_BOUND)));
            }
            lottoList.add(new Lotto(numbers));
        }

        lottos = new Lottos(lottoList);
    }

    @Test
    void createTest() {
        assertThat(lottos).isEqualTo(new Lottos(lottoList));
    }

    @Test
    void toStringTest() {
        lottos.lottoList().stream().forEach(lotto -> System.out.println(lotto.numbers()));
    }

    @Test
    @DisplayName("당첨번호를 확인해서 당첨 개수만큼 match 수를 늘리는지 확인하는 테스트")
    void checkWinningTest() {
        //given
        List<Integer> testNumbers = new ArrayList<>();
        for (int i = 1; i <= WINNING_SIZE; i++) {
            testNumbers.add(i);
        }
        List<LottoNumber> winningNumbers = testNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        WinningNumbers winning = new WinningNumbers(winningNumbers);
        WinningNumbers predictWinning = new WinningNumbers(winningNumbers);
        Lottos testLottos = makeWinningLottos(testNumbers);

        //when
        testLottos.checkWinning(winning);

        //then
        assertThat(winning).isEqualTo(predictWinning);
    }


    /*
     * 임의의 6개, 5개, 4개 당첨 로또를 만드는 메소드
     * */
    Lottos makeWinningLottos(List<Integer> testNumbers) {
        List<Lotto> testLottoList = new ArrayList<>();

        for (int i = 0; i <= 2; i++) {   //6개, 5개, 4개 당첨
            testLottoList.add(new Lotto(changeNumbers(testNumbers, i)));
        }

        return new Lottos(testLottoList);
    }

    /*
    * 번호를 하나씩 늘려주는 메소드 (로또번호 변경용으로 이용)
    * */
    List<LottoNumber> changeNumbers(List<Integer> numbers, int index) {
        return numbers.stream().map(num -> {
            return new LottoNumber(num + index);
        }).collect(Collectors.toList());
    }
}
