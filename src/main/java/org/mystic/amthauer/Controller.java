package org.mystic.amthauer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;


public class Controller {

    private static final String TASK = "task_";
    private static final String LABEL = "label_";
    private static final int PREF_WIDTH = 700;
    private static final int OUTPUT_PREF_WIDTH = 300;
    private static final int LAYOUT_X = 50;
    private static final int LAYOUT_Y = 100;
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
    @FXML
    ImageView image1;
    @FXML
    TextArea instructionText2;
    @FXML
    ImageView image2;
    private String login;
    private Scene scene;
    private PrintWriter printWriter;

    private int currentStep = 0;


    private static final Map<Integer, String> TASKS_120 = Map.ofEntries(
            new SimpleEntry<>(1, "1.    1) писать 2) рубить З) ковать 4) шить 5) читать "),
            new SimpleEntry<>(2, "2.    1) скоро 2) вскоре 3) в скором времени 4) завтра 5) сейчас  "),
            new SimpleEntry<>(3, "3.    1) клиент 2) компаньон 3) подзащитный 4) покупатель 5) пациент"),
            new SimpleEntry<>(4, "4.    1) существенный 2) примечательный 3) важный 4) характерный 5) типичный  "),
            new SimpleEntry<>(5, "5.    1) различный 2) отличающийся 3) иной 4) неодинаковый 5) измененный   "),
            new SimpleEntry<>(6, "6.    1) туманный 2) морозный 3) ветреный 4) хмурый 5) дождливый  "),
            new SimpleEntry<>(7, "7.    1) разговор 2) доклад 3) дискуссия 4) конференция 5) совещание  "),
            new SimpleEntry<>(8, "8.    1) перелистывать 2) повернуть 3) перевернуть 4) переворачивать 5) обходить  "),
            new SimpleEntry<>(9, "9.    1) нервный 2) трепещущий 3) беспокойный 4) неуверенный 5) возбужденный  "),
            new SimpleEntry<>(10, "10.  1) лепить 2) ломать 3З) гнуть 4) тянуть 5) растягивать  "),
            new SimpleEntry<>(11, "11.  1) пересматривать 2) выглядеть 3) предусмотреть 4) обозревать 5) посмотреть  "),
            new SimpleEntry<>(12, "12.  1) большой 2) массивный 3) толстый 4) дородный 5) полный  "),
            new SimpleEntry<>(13, "13.  1) близкий 2) одинаковый 3) идентичный 4) похожий 5) совпадающий  "),
            new SimpleEntry<>(14, "14.  1)	стабильный 2) постоянный 3) непрерывный 4) устойчивый 5) константный  "),
            new SimpleEntry<>(15, "15.  1) подать 2) присвоить 3) давать 4) преподносить 5) вручать  "),
            new SimpleEntry<>(16, "16.  1) лифт 2) лестница 3) парашют 4) стремянка 5) вертолет  "),
            new SimpleEntry<>(17, "17.  1) общительный 2) дипломатичный 3) доброжелательный 4) разговорчивый 5) вежливый  "),
            new SimpleEntry<>(18, "18.  1) миграция 2) движение 3) развитие 4) переселение 5) изменение  "),
            new SimpleEntry<>(19, "19.  1) новатор 2) изобретатель 3) передовик 4) первопроходец 5) рационализатор  "),
            new SimpleEntry<>(20, "20.  1) распространенный 2) типичный 3) простой 4) привычный 5) обыденный  ")
    );

    private static final Map<Integer, String> TASKS_2140 = Map.ofEntries(
            new SimpleEntry<>(1, "21) Дорого — редко = дешево — ?\n" +
                    "1) недорого 2) прочно 3) доступно 4) обычно 5) часто"),
            new SimpleEntry<>(2, "22) Прямоугольник — эллипс = квадрат — ?\n" +
                    "1) треугольник 2) круг 3) шестиугольник 4) угол 5) конус"),
            new SimpleEntry<>(3, "23) Молекула — атом = фунт — ?\n" +
                    "1) вес 2) центнер З) грамм 4) груз 5) масса"),
            new SimpleEntry<>(4, "24 Наводнение — плотина = дождь — ?\n" +
                    "1) мокро 2) вода 3) водосток 4) капля 5) зонтик"),
            new SimpleEntry<>(5, "25) Пилить — клеить = просеивать — ?\n" +
                    "1) смешать 2) паять 3) лить 4) сыпать 5) фильтровать "),
            new SimpleEntry<>(6, "26) Хлеб — тесто = кокс — ?\n" +
                    "1) отопление 2) выплавка стали 3) уголь 4) коксование 5) подвал"),
            new SimpleEntry<>(7, "27) Принять во внимание — выпустить из вида = узнать — ?\n" +
                    "1) заметить 2) игнорировать 3) наблюдать 4) недооценивать 5) презирать"),
            new SimpleEntry<>(8, "28) Спортсмен — успех = предприниматель — ?\n" +
                    "1) роскошь 2) деньги 3) прибыль 4) оборот 5) движение"),
            new SimpleEntry<>(9, "29) Либеральный — радикальный = умеренный — ?\n" +
                    "1) терпимый 2) ангажированный 3) крайний 4) благосклонный 5) примирительный"),
            new SimpleEntry<>(10, "30) Число — дробь = постройка — ?\n" +
                    "1) комната 2) подвал 3) окно 4) сарай 5) этаж"),
            new SimpleEntry<>(11, "31) Платина — алюминий = алмаз — ?\n" +
                    "1) драгоценный камень 2) украшение 3) стекло 4) твердосплав 5) шлифовка"),
            new SimpleEntry<>(12, "32) Страница — книга = предложение — ?\n" +
                    "1) буква 2) слово 3) содержание 4) глава 5) название"),
            new SimpleEntry<>(13, "33) Размер — длина = нечестный — ?\n" +
                    "1) тюрьма 2) грешный 3) укравший 4) несчастный 5) ошибка"),
            new SimpleEntry<>(14, "34) Открытие — любопытство = действие — ?\n" +
                    "1) надежда 2) процесс 3) опыт 4) намерение 5) результат"),
            new SimpleEntry<>(15, "35) Пища — пряности = доклад — ?\n" +
                    "1) оскорбление 2) речь З) юмор 4) обращение 5) расчленение"),
            new SimpleEntry<>(16, "36) Язык — горечь = глаз — ?\n" +
                    "1) зрение 2) свет 3) яркость 4)красный 5) зоркий"),
            new SimpleEntry<>(17, "37) Гнев — аффект = печаль — ?\n" +
                    "1) радость 2) раздражение 3) настроение 4)ярость 5) потеря"),
            new SimpleEntry<>(18, "38) Пальто — пиджак = шерсть — ?\n" +
                    "1) материал 2) овца З) шелк 4) джемпер 5) текстильные изделия "),
            new SimpleEntry<>(19, "39) Наука — математика = издание — ?\n" +
                    "1) типография 2) рассказ 3) журнал 4) газета «Известия» 5) редакция"),
            new SimpleEntry<>(20, "40) Река — дельта = дерево — ?\n" +
                    "1) влага 2) ветви 3) корни 4) крона 5) ростки"));


    private static final Map<Integer, String> TASKS_4160 = Map.ofEntries(
            new SimpleEntry<>(1, "41.    1) бедность 2) опасность 3) голод 4) болезнь 5) страх 6) жажда"),
            new SimpleEntry<>(2, "42.    1) характер 2) симптом 3) система 4) желание 5) признак 6) диагноз  "),
            new SimpleEntry<>(3, "43.    1) море 2) водоросль 3) медуза 4) дельфин 5) мусор б) кит "),
            new SimpleEntry<>(4, "44.    1) прикосновение 2) духи З) нос 4) вкус 5) раздражение 6) аромат "),
            new SimpleEntry<>(5, "45.    1) точка 2) вершина З) луг 4) долина 5) башня 6) поле "),
            new SimpleEntry<>(6, "46.    1) крест 2) мечеть З) башня 4) алтарь 5) костел 6) колокол "),
            new SimpleEntry<>(7, "47.    1) канистра 2) пепельница 3) рюкзак 4) урна 5) ваза 6) кастрюля  "),
            new SimpleEntry<>(8, "48.    1) бегемот 2) бабочка 3) черепаха 4) дождевой червь 5) страус б) еж  "),
            new SimpleEntry<>(9, "49.    1) стирать 2) красить 3) складывать 4) полировать 5) чистить 6) сушить  "),
            new SimpleEntry<>(10, "50.   1) история 2) филология 3) биология 4) экономика 5) педагогика 6) физика  "),
            new SimpleEntry<>(11, "51.   1) самолет 2)пилот 3) камера хранения 4) багаж 5) стюардесса 6) машинист  "),
            new SimpleEntry<>(12, "52.   1) кабель 2) телефон З) кран 4) турбина 5) предохранитель 6) переключатель  "),
            new SimpleEntry<>(13, "53.   1) линейка 2)вакуум З)угол 4)температура 5)жара 6) градусник  "),
            new SimpleEntry<>(14, "54.   1)	предостережение 2)мир 3)эпоха 4)газета 5) школа 6) срок  "),
            new SimpleEntry<>(15, "55.   1) улов 2) удочка З) щука 4) невод 5) рыбацкая лодка 6) ракушка  "),
            new SimpleEntry<>(16, "56.   1) куртка 2) застежка-молния 3) дверная задвижка 4) связка ключей 5) оконное стекло 6) платяной шкаф  "),
            new SimpleEntry<>(17, "57.   1) мягкий 2) твердый 3) эластичный 4) круглый 5) теплый 6) жидкий  "),
            new SimpleEntry<>(18, "58.   1) масло 2) яйца 3) хлеб 4) рис 5) творог 6) сельдь  "),
            new SimpleEntry<>(19, "59.   1) сентиментальный 2) странный 3) поэтический 4) чувствительный 5) нежный 6) взбалмошный  "),
            new SimpleEntry<>(20, "60.   1) научиться 2) приспособиться 3) остаться 4) присмотреться 5) адаптироваться 6) отдохнуть  ")
    );

    private static final Map<Integer, String> CORRECT_ANSWERS = Map.<Integer, String>ofEntries(
            new SimpleEntry<>(1, "5"),
            new SimpleEntry<>(2, "5"),
            new SimpleEntry<>(3, "2"),
            new SimpleEntry<>(4, "3"),
            new SimpleEntry<>(5, "5"),
            new SimpleEntry<>(6, "4"),
            new SimpleEntry<>(7, "2"),
            new SimpleEntry<>(8, "5"),
            new SimpleEntry<>(9, "4"),
            new SimpleEntry<>(10, "2"),
            new SimpleEntry<>(11, "2"),
            new SimpleEntry<>(12, "1"),
            new SimpleEntry<>(13, "1"),
            new SimpleEntry<>(14, "3"),
            new SimpleEntry<>(15, "2"),
            new SimpleEntry<>(16, "3"),
            new SimpleEntry<>(17, "3"),
            new SimpleEntry<>(18, "3"),
            new SimpleEntry<>(19, "3"),
            new SimpleEntry<>(20, "3"),

            new SimpleEntry<>(21, "5"),
            new SimpleEntry<>(22, "2"),
            new SimpleEntry<>(23, "3"),
            new SimpleEntry<>(24, "5"),
            new SimpleEntry<>(25, "1"),
            new SimpleEntry<>(26, "3"),
            new SimpleEntry<>(27, "2"),
            new SimpleEntry<>(28, "3"),
            new SimpleEntry<>(29, "3"),
            new SimpleEntry<>(30, "4"),
            new SimpleEntry<>(31, "3"),
            new SimpleEntry<>(32, "4"),
            new SimpleEntry<>(33, "2"),
            new SimpleEntry<>(34, "4"),
            new SimpleEntry<>(35, "3"),
            new SimpleEntry<>(36, "4"),
            new SimpleEntry<>(37, "3"),
            new SimpleEntry<>(38, "3"),
            new SimpleEntry<>(39, "3"),
            new SimpleEntry<>(40, "4"),

            new SimpleEntry<>(41, "36"),
            new SimpleEntry<>(42, "25"),
            new SimpleEntry<>(43, "46"),
            new SimpleEntry<>(44, "46"),
            new SimpleEntry<>(45, "36"),
            new SimpleEntry<>(46, "25"),
            new SimpleEntry<>(47, "24"),
            new SimpleEntry<>(48, "16"),
            new SimpleEntry<>(49, "15"),
            new SimpleEntry<>(50, "36"),
            new SimpleEntry<>(51, "26"),
            new SimpleEntry<>(52, "36"),
            new SimpleEntry<>(53, "16"),
            new SimpleEntry<>(54, "36"),
            new SimpleEntry<>(55, "24"),
            new SimpleEntry<>(56, "23"),
            new SimpleEntry<>(57, "26"),
            new SimpleEntry<>(58, "15"),
            new SimpleEntry<>(59, "14"),
            new SimpleEntry<>(60, "25"),

            new SimpleEntry<>(121, "2"),
            new SimpleEntry<>(122, "5"),
            new SimpleEntry<>(123, "4"),
            new SimpleEntry<>(124, "1"),
            new SimpleEntry<>(125, "4"),
            new SimpleEntry<>(126, "5"),
            new SimpleEntry<>(127, "3"),
            new SimpleEntry<>(128, "5"),
            new SimpleEntry<>(129, "1"),
            new SimpleEntry<>(130, "3"),
            new SimpleEntry<>(131, "1"),
            new SimpleEntry<>(132, "4"),
            new SimpleEntry<>(133, "5"),
            new SimpleEntry<>(134, "2"),
            new SimpleEntry<>(135, "1"),
            new SimpleEntry<>(136, "2"),
            new SimpleEntry<>(137, "1"),
            new SimpleEntry<>(138, "5"),
            new SimpleEntry<>(139, "3"),
            new SimpleEntry<>(140, "3"),

            new SimpleEntry<>(141, "2"),
            new SimpleEntry<>(142, "4"),
            new SimpleEntry<>(143, "3"),
            new SimpleEntry<>(144, "1"),
            new SimpleEntry<>(145, "4"),
            new SimpleEntry<>(146, "1"),
            new SimpleEntry<>(147, "2"),
            new SimpleEntry<>(148, "5"),
            new SimpleEntry<>(149, "3"),
            new SimpleEntry<>(150, "4"),
            new SimpleEntry<>(151, "1"),
            new SimpleEntry<>(152, "2"),
            new SimpleEntry<>(153, "5"),
            new SimpleEntry<>(154, "4"),
            new SimpleEntry<>(155, "3"),
            new SimpleEntry<>(156, "2"),
            new SimpleEntry<>(157, "5"),
            new SimpleEntry<>(158, "1"),
            new SimpleEntry<>(159, "3"),
            new SimpleEntry<>(160, "5")
    );

    @FXML
    private void closeWindow() {
        if (this.printWriter != null) {
            this.printWriter.flush();
            this.printWriter.close();
        }
        System.exit(0);
    }

    @FXML
    public void startTest() throws FileNotFoundException {
        this.scene = nextStep.getScene();
        if (userName.getText().equalsIgnoreCase("Введите свое имя") || userName.getText().length() == 0) {

        } else {
            this.login = userName.getText();
            File csvOutputFile = new File("%s.csv".formatted(this.login));
            this.printWriter = new PrintWriter(csvOutputFile);
            this.printWriter.println("task_id,raw_response,is_correct");
            this.printWriter.flush();
            userName.setVisible(false);
            startTestButton.setDisable(true);
            instructionText.setText("""
                    Тест 1.
                    Суть заданий, которые Вам будут предложены, состоит в том, что в ряду из пяти слов надо будет выделить «лишнее», не подходящее по смыслу к остальным четырем словам.
                    Пример 1
                    1)      стол   2)стул    3)синица    4)шкаф    5) кровать
                    Ответ: 3) синица
                    Четыре слова (стол, стул, шкаф, кровать) по смыслу подходят друг к другу, как предметы мебели, а слово «синица» является «лишним» в ряду этих слов.
                                        
                                        
                    Пример 2
                    1)      сидеть    2) лежать    3) стоять    4)идти    5) стоять на коленях
                    Ответ: 4) идти
                    Четыре суждения (сидеть, лежать, стоять, стоять на коленях) характеризуют неподвижность, а слово «идти» не подходит к ним, оказывается «лишним», так как характеризует движение.
                    Заданий такого типа будет двадцать. Отвечать на них нужно в окошке, следующем непосредственно после каждого задания. В каждом задании слова пронумерованы: 1, 2, 3, 4, 5. Номер «лишнего» слова надо будет записать в пустом окошке под соответствующим заданием.
                    Работать следует быстро. Время выполнения заданий ограничено. Если затрудняетесь в выборе ответа, задание можно пропустить (если останется время, Вы еще сможете к нему вернуться). Если ошиблись, можно вернуться и исправить ответ.
                    Нажмите ДАЛЕЕ и начинайте работать, когда будете готовы. По истечении отведенного времени, форма выполнения заданий будет закрыта вне зависимости от того, успели Вы выполнить все задания или нет. Если Вы справитесь с заданиями быстрее, то в оставшееся время можно проверить свою работу или просто отдохнуть, но нельзя приступать к следующим заданиям. Переходить к следующему заданию осуществляется автоматически по истечению времени.
                               
                    """);
            instructionText.setVisible(true);
            nextStep.setVisible(true);
        }
    }

    @FXML
    public void clearTextField() {
        userName.clear();
    }

    @FXML
    public void nextStep() {
        callNextStep();
    }

    private void callNextStep() {
        currentStep++;
        if (currentStep == 1) {
            System.out.println("step 1");
            tasks120();
        } else if (currentStep == 2) {
            System.out.println("step 2");
            getResults(1, 20);
            testName.setVisible(false);
            nextStep.setVisible(true);
            instructionText.setText(
                    """
                            Тест 2.
                            Суть заданий, которые будут Вам предложены, такова: нужно понять правило, которым связаны первые два слова, и использовать его, чтобы к третьему слову подобрать подходящее из тех пяти, которые приведены ниже.
                            Пример 1
                            Лес — деревья= луг — ?
                            1) сено 2) куст 3) корм 4) трава 5) пастбище
                            Ответ: 4) трава.
                            Принцип связи первых двух слов (лес — деревья) можно сформулировать так: «в лесу растут деревья». Тогда по этому правилу к слову «луг» подойдет слово «трава», так как можно сказать, что на лугу растет трава.
                            Пример 2
                            Темный — светлый = мокрый — ?
                            1) влажный 2) дождливый 3) пасмурный 4) солнечный 5) сухой
                            Ответ:5) сухой.
                            Первые два слова (темный — светлый) антонимы, следовательно, по этому правилу к слову «мокрый» подойдет слово «сухой».
                            Заданий такого типа будет тоже двадцать. Отвечать на них нужно в окошке, следующем непосредственно после каждого задания. Основная задача будет состоять в том, чтобы понять, как связаны первые два слова (могут встречаться зависимости самых разных типов), и по этому правилу подобрать подходящее слово к третьему. В каждом задании слова пронумерованы: 1, 2, 3, 4, 5. Номер выбранного слова следует записать в пустом окошке под соответствующим заданием.
                            Если не знаете, какой ответ выбрать, можно эту задачку пропустить. К ней можно будет еще вернуться, если останется время. Если Вам кажется, что Вы ошиблись, то можете исправить, ответ, вернувшись к заданию на тот, что считаете более правильным. Работать надо быстро, так как время выполнения заданий ограничено.
                            Нажмите ДАЛЕЕ и начинайте работать, когда будете готовы. По истечении отведенного времени, форма выполнения заданий будет закрыта вне зависимости от того, успели Вы выполнить все задания или нет. Если Вы справитесь с заданиями быстрее, то в оставшееся время можно проверить свою работу или просто отдохнуть, но нельзя приступать к следующим заданиям. Переходить к следующему заданию осуществляется автоматически по истечению времени.
                            """
            );
            instructionText.setVisible(true);
        } else if (currentStep == 3) {
            System.out.println("step 3");
            tasks2140();
        } else if (currentStep == 4) {
            System.out.println("step 4");
            getResults(21, 40);
            testName.setVisible(false);
            nextStep.setVisible(true);
            instructionText.setText(
                    """
                            Тест 3.
                            Суть заданий, с которыми Вы встретитесь, будет состоять в том, что Вам нужно будет из шести слов выбрать два таких, которые можно объединить, так как они однородны, относятся к одной классификационной группе, могут быть подведены под одно, более общее понятие. Между словами не должно быть никаких других связей, например, функциональных , причинно-следственных и пр.
                            Пример 1
                            1) нож 2) яблоко З) газета 4) хлеб 5) сигара 6) браслет
                            Ответ: 24 (яблоко и хлеб)
                            Слова «яблоко» и «хлеб» однородны, относятся к одной группе «продукты питания», между собой никак не связаны. Нельзя, например, выбрать «нож» и «хлеб», так как эти слова связаны функционально (ножом режут хлеб), но не являются однородными, ни к какой общей группе их не отнести.
                            Пример 2
                            1) трава 2) рожь 3) пирог 4)мука 5)пшеница 6) дерево
                            Ответ:25 (рожь и пшеница)
                            Слова «рожь» и «пшеница» однородны, относятся к одной классификационной группе «зерновые растения», между собой никак не связаны. Нельзя, например, выбрать слова «мука и пирог», так как эти слова связаны функционально (из муки пекут пирог), но ни в какую общую классификационную группу не входят. Также не являются ответом слова «трава» и «дерево», хотя они и не связаны между собой, и входят в одну классификационную группу — «растения». Дело в том, что и рожь, и пшеница тоже растения, и получается, что в выделенную группу попадает четыре слова, а не два, как требуется. Всегда надо искать такую классификационную группу, чтобы в нее попадало только два слова.
                            Заданий такого типа будет тоже двадцать. Отвечать на них нужно в окошке, следующем непосредственно после каждого задания. В каждом задании слова пронумерованы: 1, 2, 3, 4, 5. Помните, обе цифры следует вписывать в одно окошко под соответствующим заданием не разделяя запятыми, точками или тире, как указано в примере.
                            Если не знаете, какой ответ выбрать, можно эту задачку пропустить. К ней можно будет вернуться, если останется время. Если Вам кажется, что Вы ошиблись, то можете исправить,  ответ на тот, который считаете более правильным. Надо стараться работать быстро, так как время выполнения заданий ограничено.
                            Нажмите ДАЛЕЕ и начинайте работать, когда будете готовы. По истечении отведенного времени, форма выполнения заданий будет закрыта вне зависимости от того, успели Вы выполнить все задания или нет. Если Вы справитесь с заданиями быстрее, то в оставшееся время можно проверить свою работу или просто отдохнуть, но нельзя приступать к следующим заданиям. Переходить к следующему заданию осуществляется автоматически по истечению времени.
                                                    
                            """
            );
            instructionText.setVisible(true);
        } else if (currentStep == 5) {
            System.out.println("step 5");
            tasks4160();
        } else if (currentStep == 6) {
            System.out.println("step 6");
            getResults(41, 60);
            testName.setVisible(false);
            nextStep.setVisible(true);
            instructionText.setText(
                    """
                            Тест 4.
                            Суть задания, которое будет Вам предложено, состоит в том, чтобы мысленно сложить
                            фигуру, разрезанную на кусочки, которые в свою очередь размещены на плоскости в случайном порядке.
                            """
            );
            instructionText.setVisible(true);
            instructionText.setPrefHeight(100f);
            Image example = new Image(Objects.requireNonNull(Controller.class.getResource("/subtest7/example.png")).toString());
            image1.setImage(example);
            image1.setVisible(true);
            image1.setLayoutY(200f);
            instructionText2.setText(
                    """
                            В верхнем ряду нарисованы фигуры-образцы. Они пронумерованы 1, 2, 3, 4, 5. Ниже нарисованы те же фигуры, но только разрезанные на кусочки. Вам надо из кусочков сложить какую-нибудь фигуру-образец. Из первых нижних кусочков получается фигура 1. Из вторых нижних кусочков получается фигура 5, из третьих — фигура 2, из четвертых — 4.
                            Пример очень простой, само задание будет несколько труднее, но принцип работы сохраняется тот же. Задание будет состоять из двух составных частей по 10 фигур (нужно будет прокрутить страницу до конца, чтобы увидеть все задания). В верхнем ряду также будут нарисованы фигуры-образцы. Они пронумерованы 1, 2, 3, 4, 5. Под ними два ряда кусочков, из которых надо пытаться получить какую-нибудь фигуру-образец. Далее будут следовать окошки для ответов для первой части заданий. Ниже будет изображен еще ряд фигур-образцов (тоже под номерами 1, 2, 3, 4,5), а под ними еще два ряда с вариантами кусочков и окошки для ответом. Ваша задача будет состоять в том, чтобы из каждого набора кусочков мысленно сложить какой-либо из образцов. При «составлении» образца обязательно надо использовать все кусочки, нельзя ограничиться только отдельными фрагментами.
                            Ответом будет являться номер фигуры-образца, которая, по Вашему мнению, получается из кусочков Номер этой фигуры-образца и следует записывать в окошки, соответствующие номерам заданий 121-140. Номера фигур-образцов, естественно, будут повторяться, потому что их всего пять, а «кусочков» — десять вариантов. Какой-то образец может получиться и два, и три раза, может быть и подряд. Пусть вас это Вас не смущает. Если сразу не видно, какая получается из кусочков фигура, то лучше пропустите и переходите к следующей. К ним можно будет еще раз вернуться, если останется время.
                            Помните, что работать надо быстро, так как время выполнения ограничено. Если Вам кажется, что Вы ошиблись, то можете исправить, и вписать тот ответ, который считаете более правильным. Нажмите ДАЛЕЕ и начинайте работать, когда будете готовы. По истечении отведенного времени, форма выполнения заданий будет закрыта вне зависимости от того, успели Вы выполнить все задания или нет. Если Вы справитесь с заданиями быстрее, то в оставшееся время можно проверить свою работу или просто отдохнуть, но нельзя приступать к следующим заданиям. Переход к следующему заданию осуществляется автоматически по истечению времени.
                             """
            );
            instructionText2.setVisible(true);
        } else if (currentStep == 7) {
            System.out.println("step 7");
            tasks121140();
        } else if (currentStep == 8) {
            System.out.println("step 8");
            getResults(121, 140);
            testName.setVisible(false);
            nextStep.setVisible(true);
            instructionText.setText(
                    """
                            Тест 5.
                            Задание, которое будет Вам предложено, немного похоже на предыдущее, только теперь в качестве образцов будут выступать кубики.
                            """
            );
            instructionText.setPrefHeight(100f);
            instructionText.setLayoutY(50f);
            instructionText.setVisible(true);
            Image example = new Image(Objects.requireNonNull(Controller.class.getResource("/subtest8/example.png")).toString());
            image1.setImage(example);
            image1.setVisible(true);
            image1.setLayoutY(150f);
            instructionText2.setText(
                    """
                            Верхний ряд кубиков  — это образцы, они пронумерованы 1, 2, 3, 4, 5. Кубики-образцы все разные, потому что по-разному разрисованы их грани (это не дырки, а нарисованные кружочки, квадратики и линии). Ниже нарисованы те же самые кубики, только в измененном положении. Они могут быть повернуты в горизонтальной или в вертикальной плоскости, или и одновременно в обеих плоскостях. Когда кубик поворачивается (один или несколько раз), внешний вид его меняется, он начинает выглядеть по-другому. Может одна грань исчезнуть и появиться новая, но две грани (из изображенных на образце) всегда остаются видны, хотя и иначе выглядят. Могут оставаться перед глазами и все три грани образца, только они будут даны в другом положении. Вам надо, сравнивая, как соотносятся рисунки на гранях, определить, с каким из образцов идентичен каждый кубик, который нарисован ниже во втором ряду.
                             Первый нижний кубик идентичен образцу 1. Второй нижний кубик представляет собой образец 5. Проследим подробнее за преобразованиями третьего нижнего кубика. Если его один раз повернуть в вертикальной плоскости против часовой стрелки, кружок из верхнего левого угла «опустится» в нижний левый, верхняя грань с кружком по середине спрячется и не будет видна, правая грань станет верхней и ее нижний дальний «уголок» поднимется наверх, а на ее месте появиться новая грань, которая есть на образце, но не была видна на кубике. В итоге мы получим образец 2. Четвертый нижний кубик представляет собой образец 3, пятый нижний кубик — образец 4.
                             Само задание будет точно таким же. В верхнем ряду будут расположены кубики-образцы (которые будут иметь номера 1, 2, 3, 4, 5), а ниже — ряды кубиков, которые нужно сравнивать с образцами и выбирать, на какой из образцов каждый из них похож. Ответ (то есть номер выбранного кубика-образца) следует записывать в окошках, предназначенных для каждого задания 141-160, под соответствующим номером кубика-задания. На каждый кубик-образец могут оказаться похожими несколько кубиков-заданий, так как образцов всего пять, а кубиков к ним двадцать. Следовательно, номера ответов будут повторяться, каждый может встретиться несколько раз, возможно и подряд. Если какой-то кубик не определить, то можете его пропустить.
                             Помните, что время выполнения задания ограничено. Желательно, чтобы за это время Вы успели просмотреть все кубики-задания. В конце могут оказаться более легкие задания, а Вы до них просто не успеете дойти. Если Вам кажется, что Вы ошиблись, то можете исправить, и указать ответ, который считаете более правильным.
                             Нажмите ДАЛЕЕ и начинайте работать, когда будете готовы. По истечении отведенного времени, форма выполнения заданий будет закрыта вне зависимости от того, успели Вы выполнить все задания или нет. Если Вы справитесь с заданиями быстрее, то в оставшееся время можно проверить свою работу или просто отдохнуть, но нельзя приступать к следующим заданиям. Переход к следующему заданию осуществляется автоматически по истечению времени.
                            """
            );
            instructionText2.setVisible(true);
            image2.setVisible(false);
        } else if (currentStep == 9) {
            System.out.println("step 9");
            tasks141160();
        } else if (currentStep == 10) {
            System.out.println("step 10");
            getResults(141, 160);
            closeWindow();
            this.printWriter.close();
        }
    }

    private void getResults(int start, int finish) {
        Pane parent = (Pane) testName.getParent();
        for (int i = start; i <= finish; ++i) {
            TextField textField = (TextField) parent.lookup("#" + TASK + i);
            this.printWriter.println("%s,%s,%s".formatted(i, textField.getText(), isAnswerCorrect(i, textField.getText())));
            textField.setDisable(true);
            textField.setVisible(false);
            parent.getChildren().remove(textField);
            Label l = (Label) parent.lookup("#" + LABEL + i);
            l.setDisable(true);
            l.setVisible(false);
            parent.getChildren().remove(l);
        }
        this.printWriter.flush();
    }

    private String isAnswerCorrect(int taskId, String rawAnswer) {
        if (CORRECT_ANSWERS.containsKey(taskId))
            return CORRECT_ANSWERS.get(taskId).equalsIgnoreCase(rawAnswer) ? "true" : "false";
        return "false";
    }

    private void tasks120() {
        testName.setVisible(true);
        testName.setText("ЗАДАНИЯ 1-20");
        instructionText.setVisible(false);
        nextStep.setVisible(false);
        Pane parent = (Pane) testName.getParent();
        Timeline animation = new Timeline(
                new KeyFrame(Duration.minutes(6),
                        actionEvent -> callNextStep()
                ));
        animation.setCycleCount(1);
        animation.play();
        for (int i = 1; i <= 20; ++i) {
            Label e = new Label(String.valueOf(i));
            e.setId(LABEL + i);
            e.setPrefWidth(PREF_WIDTH);
            e.setLayoutX(LAYOUT_X);
            e.setLayoutY(LAYOUT_Y * i);
            e.setText(TASKS_120.getOrDefault(i, String.valueOf(i)));
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
            inputField.setTextFormatter(new TextFormatter<>(rejectChange));
            inputField.setId(TASK + i);
            inputField.setPrefWidth(OUTPUT_PREF_WIDTH);
            inputField.setLayoutX(LAYOUT_X);
            inputField.setLayoutY(LAYOUT_Y * i + LAYOUT_X);
            parent.getChildren().add(inputField);
        }
    }

    private void tasks2140() {
        testName.setVisible(true);
        testName.setText("ЗАДАНИЯ 21-40");
        instructionText.setVisible(false);
        nextStep.setVisible(false);
        Pane parent = (Pane) testName.getParent();
        Timeline animation = new Timeline(
                new KeyFrame(Duration.minutes(7),
                        actionEvent -> callNextStep()
                ));
        animation.setCycleCount(1);
        animation.play();
        for (int i = 1; i <= 20; ++i) { // + 20
            int idx = i + 20;
            Label e = new Label(String.valueOf(idx));
            e.setId(LABEL + idx);
            e.setPrefWidth(PREF_WIDTH);
            e.setLayoutX(LAYOUT_X);
            e.setLayoutY(LAYOUT_Y * i);
            e.setText(TASKS_2140.getOrDefault(i, String.valueOf(i)));
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
            inputField.setTextFormatter(new TextFormatter<>(rejectChange));
            inputField.setId(TASK + idx);
            inputField.setPrefWidth(OUTPUT_PREF_WIDTH);
            inputField.setLayoutX(LAYOUT_X);
            inputField.setLayoutY(LAYOUT_Y * i + LAYOUT_X);
            parent.getChildren().add(inputField);
        }
    }

    private void tasks4160() {
        testName.setVisible(true);
        testName.setText("ЗАДАНИЯ 41-60");
        instructionText.setVisible(false);
        nextStep.setVisible(false);
        Pane parent = (Pane) testName.getParent();
        Timeline animation = new Timeline(
                new KeyFrame(Duration.minutes(8),
                        actionEvent -> callNextStep()
                ));
        animation.setCycleCount(1);
        animation.play();
        for (int i = 1; i <= 20; ++i) { // +40
            int idx = i + 40;
            Label e = new Label(String.valueOf(idx));
            e.setId(LABEL + idx);
            e.setPrefWidth(PREF_WIDTH);
            e.setLayoutX(LAYOUT_X);
            e.setLayoutY(LAYOUT_Y * i);
            e.setText(TASKS_4160.getOrDefault(i, String.valueOf(i)));
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
            inputField.setTextFormatter(new TextFormatter<>(rejectChange));
            inputField.setId(TASK + idx);
            inputField.setPrefWidth(OUTPUT_PREF_WIDTH);
            inputField.setLayoutX(LAYOUT_X);
            inputField.setLayoutY(LAYOUT_Y * i + LAYOUT_X);
            parent.getChildren().add(inputField);
        }
    }

    private void tasks121140() {
        testName.setVisible(true);
        testName.setText("ЗАДАНИЯ 121-140");
        instructionText.setVisible(false);
        instructionText2.setVisible(false);
        Image base = new Image(Objects.requireNonNull(Controller.class.getResource("/subtest7/121-130.png")).toString());
        image1.setImage(base);
        image1.setLayoutY(50f);
        image1.setFitHeight(500f);
        image1.setFitWidth(1000f);
        image1.setPreserveRatio(true);
        image1.setSmooth(true);
        image1.setVisible(true);
        nextStep.setVisible(false);
        Pane parent = (Pane) testName.getParent();
        Timeline animation = new Timeline(
                new KeyFrame(Duration.minutes(7),
                        actionEvent -> callNextStep()
                ));
        animation.setCycleCount(1);
        animation.play();
        for (int i = 1; i <= 10; ++i) { // +120
            int idx = i + 120;
            Label e = new Label(String.valueOf(idx));
            e.setId(LABEL + idx);
            e.setPrefWidth(PREF_WIDTH);
            e.setLayoutX(LAYOUT_X);
            e.setLayoutY(LAYOUT_Y * i + 450);
            e.setText(String.valueOf(idx));
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
            inputField.setTextFormatter(new TextFormatter<>(rejectChange));
            inputField.setId(TASK + idx);
            inputField.setPrefWidth(OUTPUT_PREF_WIDTH);
            inputField.setLayoutX(LAYOUT_X);
            inputField.setLayoutY(LAYOUT_Y * i + 450 + 25);
            parent.getChildren().add(inputField);
        }
        // add another image view
        Image base2 = new Image(Objects.requireNonNull(Controller.class.getResource("/subtest7/131-140.png")).toString());
        image2.setImage(base2);
        image2.setLayoutY(1500f);
        image2.setFitHeight(500f);
        image2.setFitWidth(1000f);
        image2.setPreserveRatio(true);
        image2.setSmooth(true);
        image2.setVisible(true);
        for (int i = 1; i <= 10; ++i) { // +130
            int idx = i + 130;
            Label e = new Label(String.valueOf(idx));
            e.setId(LABEL + idx);
            e.setPrefWidth(PREF_WIDTH);
            e.setLayoutX(LAYOUT_X);
            e.setLayoutY(LAYOUT_Y * i + 1900);
            e.setText(String.valueOf(idx));
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
            inputField.setTextFormatter(new TextFormatter<>(rejectChange));
            inputField.setId(TASK + idx);
            inputField.setPrefWidth(OUTPUT_PREF_WIDTH);
            inputField.setLayoutX(LAYOUT_X);
            inputField.setLayoutY(LAYOUT_Y * i + 1900 + 25);
            parent.getChildren().add(inputField);
        }
    }

    private void tasks141160() {
        testName.setVisible(true);
        testName.setText("ЗАДАНИЯ 141-160");
        instructionText.setVisible(false);
        instructionText2.setVisible(false);

        Image base = new Image(Objects.requireNonNull(Controller.class.getResource("/subtest8/base.png")).toString());
        image1.setImage(base);
        image1.setFitHeight(500f);
        image1.setFitWidth(1000f);
        image1.setLayoutY(50f);
        image1.setPreserveRatio(true);
        image1.setSmooth(true);
        image1.setVisible(true);
        Image tests = new Image(Objects.requireNonNull(Controller.class.getResource("/subtest8/141-160.png")).toString());
        image2.setImage(tests);
        image2.setFitHeight(500f);
        image2.setFitWidth(1000f);
        image2.setLayoutY(250f);
        image2.setPreserveRatio(true);
        image2.setSmooth(true);
        image2.setVisible(true);
        nextStep.setVisible(false);
        Pane parent = (Pane) testName.getParent();
        Timeline animation = new Timeline(
                new KeyFrame(Duration.minutes(9),
                        actionEvent -> callNextStep()
                ));
        animation.setCycleCount(1);
        animation.play();
        for (int i = 1; i <= 20; ++i) { // +140
            int idx = i + 140;
            Label e = new Label(String.valueOf(idx));
            e.setId(LABEL + idx);
            e.setPrefWidth(PREF_WIDTH);
            e.setLayoutX(LAYOUT_X);
            e.setLayoutY(LAYOUT_Y * i + 650);
            e.setText(String.valueOf(idx));
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
            inputField.setTextFormatter(new TextFormatter<>(rejectChange));
            inputField.setId(TASK + idx);
            inputField.setPrefWidth(OUTPUT_PREF_WIDTH);
            inputField.setLayoutX(LAYOUT_X);
            inputField.setLayoutY(LAYOUT_Y * i + 650 + 25);
            parent.getChildren().add(inputField);
        }

    }
}
