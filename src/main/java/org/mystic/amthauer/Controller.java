package org.mystic.amthauer;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

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
//            out = new PrintWriter((userName.ge
//            tText() + (System.currentTimeMillis() % 100000)) + ".txt");
//            this.login = userName.getText();
//            out.println(login);
//            final int[] steps = {1};
//            startTestButton.setDisable(true);
//            instructionText.setVisible(false);
//            userName.setVisible(false);
//            final List<ImageView> images = new ArrayList<>(9);
//            goNextStep(steps, images);
        }
    }

    @FXML
    public void clearTextField() {
        userName.clear();
    }

    @FXML
    public void nextStep() {
        testName.setVisible(true);
        testName.setText("ЗАДАНИЯ 1-20");
        instructionText.setVisible(false);
        Pane parent = (Pane) testName.getParent();
        for (int i = 0; i < 20; ++i) {
           parent.getChildren().add(new Label(String.valueOf(i)));
        }
    }
}
