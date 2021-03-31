/*
 * UI 입력을 담당하는 클래스
 * */
package lotto.ui;

import lotto.domain.LottoNumber;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import static lotto.domain.Rank.LOTTO_PRICE;
import static lotto.util.StringUtil.*;

public class InputView {

    public static final String BUYING_MESSAGE_BEFORE = "구입금액을 입력해 주세요.";
    public static final String BUYING_MESSAGE_AFTER = "개를 구매했습니다.";
    public static final String WINNING_MESSAGE_BEFORE = "지난주 당첨 번호를 입력해 주세요. (구분자는 ', ')";
    public static final String BONUS_MESSAGE_BEFORE = "보너스 볼을 입력해 주세요.";

    private static Scanner sc;

    /*
    * 구매할 로또 금액을 입력한다.
    * */
    public static int inputBuy() {
        openScanner();
        System.out.println(BUYING_MESSAGE_BEFORE);
        int result = sc.nextInt() / LOTTO_PRICE;
        System.out.println(result + BUYING_MESSAGE_AFTER);
        sc.nextLine();
        return result;
    }

    /*
    * 당첨 번호를 입력한다.
    * */
    public static Set<LottoNumber> inputWinning() {
        System.out.println(WINNING_MESSAGE_BEFORE);
        String[] result = sc.nextLine().split(DELIMITER);
        checkNumberSize(result);
        checkNumberOverlap(result);

        return Arrays.stream(result)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }


    /*
    * 보너스 볼을 입력한다.
    * */
    public static LottoNumber inputBonus() {
        System.out.println(BONUS_MESSAGE_BEFORE);
        int result = sc.nextInt();

        return new LottoNumber(result);
    }

    public static void openScanner() {
        if (sc == null) {
            sc = new Scanner(System.in);
        }
    }





}
