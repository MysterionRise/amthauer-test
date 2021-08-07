package org.mystic.amthauer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.function.UnaryOperator;

public class Controller {

    @FXML
    Button nextStep;
    @FXML
    TextArea instructionText;
    @FXML
    Button startTestButton;
    @FXML
    TextField userName;
    @FXML
    Label testName;
    private String login;

    private int currentStep = 0;

    @FXML
    private void closeWindow() {
        System.exit(0);
    }

    @FXML
    public void startTest() {
        if (userName.getText().equalsIgnoreCase("Введите свое имя") || userName.getText().length() == 0) {

        } else {
            this.login = userName.getText();
            userName.setVisible(false);
            startTestButton.setDisable(true);
            instructionText.setText("Субтест 2\n" +
                    "Суть заданий, которые Вам будут предложены, состоит в том, что в ряду из пяти слов надо будет выделить «лишнее», не подходящее по смыслу к остальным четырем словам.\n" +
                    "Пример 1 \n" +
                    "1)\tстол   2)стул    3)синица    4)шкаф    5) кровать \n" +
                    "Ответ: 3) синица \n" +
                    "Четыре слова (стол, стул, шкаф, кровать) по смыслу подходят друг к другу, как предметы мебели, а слово «синица» является «лишним» в ряду этих слов.\n" +
                    "\n" +
                    "Пример 2 \n" +
                    "1)\tсидеть    2) лежать    3) стоять    4)идти    5) стоять на коленях\n" +
                    "Ответ: 4) идти \n" +
                    "Четыре суждения (сидеть, лежать, стоять, стоять на коленях) характеризуют неподвижность, а слово «идти» не подходит к ним, оказывается «лишним», так как характеризует движение.\n" +
                    "\n" +
                    "Заданий такого типа будет также двадцать. Отвечать на них надо в табличке1, в верхней части которой проставлены номера заданий 21—40, а в нижней части — пустые клетки для вписывания ответов. В каждом задании слова пронумерованы: 1, 2, 3, 4, 5. Номер «лишнего» слова и надо будет записать в пустой клеточке под номером соответствующего задания.\n" +
                    "Работать следует быстро. Время выполнения заданий ограничено. Если затрудняетесь в выборе ответа, задание можно пропустить (если останется время, Вы еще сможете к нему вернуться). Если ошиблись, можно вернуться и исправить ответ.\n" +
                    "Нажмите ДАЛЕЕ и начините работать, когда будете готовы. По истечении отведенного времени, форма выполнения заданий будет закрыта вне зависимости от того, успели Вы выполнить все задания или нет. Если Вы справитесь с заданиями быстрее, то в оставшееся время можно проверить свою работу или просто отдохнуть, но нельзя приступать к следующим заданиям. Переходить к следующим заданиям можно только по сигналу экспериментатора.\n");
            instructionText.setVisible(true);
            nextStep.setVisible(true);
        }
    }

    @FXML
    public void clearTextField() {
        userName.clear();
    }

    Map<Integer, String> tasks = Map.ofEntries(
            new SimpleEntry<>(1, "1. 1) писать 2) рубить З) ковать 4) шить 5) читать "),
            new SimpleEntry<>(2, "2. 1) скоро 2) вскоре 3) в скором времени 4) завтра 5) сейчас  "),
            new SimpleEntry<>(3, "3. 1) клиент 2) компаньон 3) подзащитный 4) покупатель 5) пациент"),
            new SimpleEntry<>(4, "4. 1) существенный 2) примечательный 3) важный 4) характерный 5) типичный  ")
    );

    @FXML
    public void nextStep() {
        currentStep++;
        switch (currentStep) {
            case 1:
                System.out.println("step 1");
                tasks120();
                break;
            case 2:
                System.out.println("step 2");
                tasks2140();
                break;
            case 3:
                System.out.println("step 3");
                tasks2140();
                break;
        }

    }

    private void tasks2140() {
        testName.setVisible(true);
        testName.setText("ЗАДАНИЯ 21-40");
        instructionText.setVisible(false);
        Pane parent = (Pane) testName.getParent();
        for (int i = 1; i <= 20; ++i) {
            Label e = new Label(String.valueOf(i));
            e.setPrefWidth(500);
            e.setLayoutX(50);
            e.setLayoutY(50 * i);
            e.setText(tasks.getOrDefault(i, String.valueOf(i)));
            parent.getChildren().add(e);
            TextField inputField = new TextField("");
            UnaryOperator<TextFormatter.Change> rejectChange = c -> {
                if (c.isContentChange()) {
                    for (char ch : c.getControlNewText().toCharArray()) {
                        if (!Character.isDigit(ch)) {
                            return null;
                        }
                    }
                    return c;
                }
                return c;
            };
            inputField.setTextFormatter(new TextFormatter(rejectChange));
            inputField.setId("task_" + i);
            inputField.setPrefWidth(300);
            inputField.setLayoutX(50);
            inputField.setLayoutY(50 * i + 25);
            parent.getChildren().add(inputField);
            final Timeline animation = new Timeline(
                    new KeyFrame(Duration.seconds(10),
                            actionEvent -> {
                                nextStep();
                            }
                    ));
            animation.setCycleCount(1);
            animation.play();
        }
    }

    private void tasks120() {
        testName.setVisible(true);
        testName.setText("ЗАДАНИЯ 1-20");
        instructionText.setVisible(false);
        Pane parent = (Pane) testName.getParent();
        for (int i = 1; i <= 20; ++i) {
            Label e = new Label(String.valueOf(i));
            e.setPrefWidth(500);
            e.setLayoutX(50);
            e.setLayoutY(50 * i);
            e.setText(tasks.getOrDefault(i, String.valueOf(i)));
            parent.getChildren().add(e);
            TextField inputField = new TextField("");
            UnaryOperator<TextFormatter.Change> rejectChange = c -> {
                if (c.isContentChange()) {
                    for (char ch : c.getControlNewText().toCharArray()) {
                        if (!Character.isDigit(ch)) {
                            return null;
                        }
                    }
                    return c;
                }
                return c;
            };
            inputField.setTextFormatter(new TextFormatter(rejectChange));
            inputField.setId("task_" + i);
            inputField.setPrefWidth(300);
            inputField.setLayoutX(50);
            inputField.setLayoutY(50 * i + 25);
            parent.getChildren().add(inputField);
            final Timeline animation = new Timeline(
                    new KeyFrame(Duration.seconds(5),
                            actionEvent -> {
                                nextStep();
                            }
                    ));
            animation.setCycleCount(1);
            animation.play();
        }
    }
}
