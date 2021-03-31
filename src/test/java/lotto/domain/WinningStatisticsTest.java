package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.domain.Rank.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class WinningStatisticsTest {

    private static final int SIX_MATCH_NUM = 1;
    private static final int FIVE_MATCH_NUM = 3;
    private static final int buyNum = 10;

    @InjectMocks
    private WinningStatistics winningStatistics;

    @Mock
    private Lottos lottos;
    @Mock
    private WinningNumbers winningNumbers;
    private List<LottoNumber> winningList = new ArrayList<>();
    private Map<Rank, Integer> statistics = new HashMap<>();

    @BeforeEach
    void setUp() {
        List<Rank> ranks = new ArrayList<>();
        for (int i = 0; i < FIVE_MATCH_NUM; i++) {
            ranks.add(Rank.THIRD);
        }
        for (int i = 0; i < SIX_MATCH_NUM; i++) {
            ranks.add(Rank.FIRST);
        }
        given(lottos.checkWinning(any())).willReturn(ranks);
        winningStatistics = new WinningStatistics(lottos, winningNumbers);

    }

    @Test
    void createTest() {
        //given
        statistics.put(Rank.MISS, 0);
        statistics.put(Rank.FIFTH, 0);
        statistics.put(Rank.FOURTH, 0);
        statistics.put(Rank.THIRD, FIVE_MATCH_NUM);
        statistics.put(Rank.SECOND, 0);
        statistics.put(Rank.FIRST, SIX_MATCH_NUM);
        WinningStatistics testWinningStatistics = new WinningStatistics(statistics);


        //then
        assertThat(winningStatistics).isEqualTo(testWinningStatistics);
    }

    @Test
    void yieldTest() {
        //given
        Long totalMoney = (Rank.THIRD.getWinningMoney() * FIVE_MATCH_NUM) + (Rank.FIRST.getWinningMoney() * SIX_MATCH_NUM);
        double predict = totalMoney / (buyNum * LOTTO_PRICE);

        //when
        double result = winningStatistics.yield(buyNum);

        //then
        assertThat(result).isEqualTo(predict);
    }

    @Test
    void printTest() {
        winningStatistics.winningStatistics().entrySet().stream()
                .filter(e -> e.getKey().getMatchNo() > 0)
                .forEach(e -> System.out.println(e.getKey().getMatchNo() + "개" + e.getKey().getWinningMoney() + "원"));
    }
}
