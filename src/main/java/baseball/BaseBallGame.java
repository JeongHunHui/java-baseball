package baseball;

import camp.nextstep.edu.missionutils.Console;

public class BaseBallGame {
  private Computer computer = new Computer();

  public void start() {
    gameInit();
    System.out.println(GameMessage.GAME_START_MESSAGE.getMessage());
    String inputData;
    GameNumber gameNumber;
    boolean isGameOver = false;
    while (!isGameOver) {
      inputData = getUserNumberInput();
      gameNumber = createBaseBallGameNumber(inputData);
      if (gameNumber == null) isGameOver = true;
      else {
        GameResult result = new GameResult();
        result.setResultByGameNumber(computer.getAnswerNumber(), gameNumber);
        System.out.println(result.getResultString());
        if (result.isAnswer()) {
          System.out.println(GameNumber.NUMBER_DIGIT + GameMessage.GAME_FINISH_MESSAGE.getMessage());
          isGameOver = !checkRestart();
        }
      }
    }
  }

  // 재시작 할 것인지 체크
  private boolean checkRestart() {
    System.out.println(GameMessage.CHECK_RESTART_MESSAGE.getMessage());
    while (true) {
      String input = Console.readLine();
      switch (input) {
        case "1":
          gameInit();
          return true;
        case "2":
          return false;
        default:
          System.out.println(GameMessage.IS_INVALID_VALUE.getMessage());
      }
    }
  }

  private void gameInit() {
    computer.setAnswerNumber();
  }

  private String getUserNumberInput() {
    System.out.print(GameMessage.START_INPUT_MESSAGE.getMessage());
    return Console.readLine();
  }

  public GameNumber createBaseBallGameNumber(String inputData) {
    GameNumber gameNumber = new GameNumber();
    gameNumber.setGameNumberByString(inputData);
    return gameNumber;
  }

  private enum GameMessage {
    GAME_START_MESSAGE("숫자 야구 게임을 시작합니다."),
    START_INPUT_MESSAGE("숫자를 입력해주세요: "),
    GAME_FINISH_MESSAGE("개의 숫자를 모두 맞히셨습니다! 게임 종료"),
    CHECK_RESTART_MESSAGE("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."),
    IS_INVALID_VALUE("잘못된 값을 입력하셨습니다. 다시 입력해주세요.");
    private final String message;

    GameMessage(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }
}
