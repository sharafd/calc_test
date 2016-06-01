package tests;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;

import com.tngtech.java.junit.dataprovider.UseDataProvider;
import helpers.DataProviders;
import helpers.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.yandex.qatools.allure.annotations.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Damir.Sharafetdinov on 31.05.2016.
 */

@RunWith(DataProviderRunner.class)
@Features("Проверка корректности арифметических действий")
@Title("Проверка корретности выгрузки арифметических действий")
public class CalcTest{


    private int operand;
    private int firstOperand;
    private int secondOperand;
    private String operation;
    private double result;
    private static final double DELTA = 1e-15;

    /**
     * Проверяем операнд.
     *
     * @param ip_operand
     * @return если целое число - true.
     */
    private boolean checkOperand(String ip_operand) {

        boolean res = false;

        try {
            operand = Integer.valueOf(ip_operand);
            res = true;

        } catch (Exception e) {
            res = false;
        }

        return res;
    }

    /**
     * Проверяем корретность задания результата
     * действий над операндами.
     *
     * @param ip_result
     * @return если число - true.
     */
    private boolean checkResult(String ip_result) {

        boolean res = false;

        try {
            result = Double.valueOf(ip_result);
            res = true;

        } catch (Exception e) {
            res = false;
        }

        return res;
    }

    /**
     * Проверка корретности указания дейчствия над операндами
     * @param ip_operation
     * @return если действие корректно - true
     */
    private boolean checkOperation(String ip_operation) {

        boolean res;

        switch (ip_operation) {
            case "+":
                operation = ip_operation;
                res = true;
                break;
            case "-":
                operation = ip_operation;
                res = true;
                break;
            case "*":
                operation = ip_operation;
                res = true;
                break;
            case "/":
                operation = ip_operation;
                res = true;
                break;
            default:
                res = false;
                break;
        }
        return res;
    }

    @Step("Проверка корректности сложения")
    public void additionCheck(){
        assertEquals("Сложение выполнено неверно", result, (firstOperand + secondOperand), DELTA);
    }

    @Step("Проверка корректности вычитания")
    public void subtractionCheck(){
        assertEquals("Вычитание выполнено неверно", result, (firstOperand - secondOperand), DELTA);
    }

    @Step("Проверка корректности деления")
    public void divisionCheck(){
        assertEquals("Деление выполнено неверно", result, (firstOperand / secondOperand), DELTA);
    }

    @Step("Проверка корректности умножения")
    public void multiplicationCheck(){
        assertEquals("Умножение выполнено неверно", result, (firstOperand * secondOperand), DELTA);
    }

    @Step("Проверка корректности указания первого операнда")
    private void isFirstOperandOK(String ip_operand){

        assertTrue("Операнд 1 указан неверно", checkOperand(ip_operand));
        firstOperand = operand;
    }

    @Step("Проверка корректности  указания  второго операнда")
    private void isSecondOperandOK(String ip_operand){

        assertTrue("Операнд 2 указан неверно", checkOperand(ip_operand));
        secondOperand = operand;
    }

    @Step("Проверка корректности  указания действия над операндами")
    private void isOperationOK(String ip_operation){

        assertTrue("Операция указана неверно", checkOperation(ip_operation));
    }

    @Step("Проверка корректности  указания результата действия над операндами")
    private void isResultOK(String ip_result){

        assertTrue("Результат вычислен неверно", checkResult(ip_result));
    }

  // @Step("Проверка корректности результата действия над операндами")

    private void isCalcResultOK(String ip_operation){

        switch (ip_operation) {
            case "+":
                additionCheck();
                break;
            case "-":
                subtractionCheck();
                break;
            case "*":
                multiplicationCheck();
                break;
            case "/":
                divisionCheck();
                break;
        }
    }

    /**
     * Проверки переданных из файла данных  на корректность
     * @param ip_firstOperand - первый операнд
     * @param ip_secondOperand - второй операнд
     * @param ip_operation - операция
     * @param ip_result - результат
     */
    private void performChecks(String ip_firstOperand, String ip_secondOperand, String ip_operation, String ip_result) {
        isFirstOperandOK(ip_firstOperand);
        isSecondOperandOK(ip_secondOperand);
        isResultOK(ip_result);
        isCalcResultOK(ip_operation);
    }

    @Test
    @Stories("Сложение")
    @UseDataProvider(value = "dataSourceLoader", location = DataProviders.class)
    @DataSource(value = "/datafile", type = DataSource.Type.RESOURCE)
    public void additionTest(String ip_firstOperand, String ip_secondOperand, String ip_operation, String ip_result) throws IOException {

        isOperationOK(ip_operation);
        assertTrue("Операция не 'Сложение'", operation.equals("+"));
        performChecks(ip_firstOperand, ip_secondOperand, ip_operation, ip_result);

    }

    @Test
    @Stories("Вычитание")
    @UseDataProvider(value = "dataSourceLoader", location = DataProviders.class)
    @DataSource(value = "/datafile", type = DataSource.Type.RESOURCE)
    public void subtractionTest(String ip_firstOperand, String ip_secondOperand, String ip_operation, String ip_result) throws IOException {

        isOperationOK(ip_operation);
        assertTrue("Операция не 'Вычитание'", operation.equals("-"));
        performChecks(ip_firstOperand, ip_secondOperand, ip_operation, ip_result);

    }

    @Test
    @Stories("Деление")
    @UseDataProvider(value = "dataSourceLoader", location = DataProviders.class)
    @DataSource(value = "/datafile", type = DataSource.Type.RESOURCE)
    public void divisionTest(String ip_firstOperand, String ip_secondOperand, String ip_operation, String ip_result) throws IOException {

        isOperationOK(ip_operation);
        assertTrue("Операция не 'Деление'", operation.equals("/"));
        performChecks(ip_firstOperand, ip_secondOperand, ip_operation, ip_result);

    }

    @Test
    @Stories("Умножение")
    @UseDataProvider(value = "dataSourceLoader", location = DataProviders.class)
    @DataSource(value = "/datafile", type = DataSource.Type.RESOURCE)
    public void multiplicationTest(String ip_firstOperand, String ip_secondOperand, String ip_operation, String ip_result) throws IOException {

        isOperationOK( ip_operation);
        assertTrue("Операция не 'Умножение'",operation.equals("*"));
        performChecks(ip_firstOperand, ip_secondOperand, ip_operation, ip_result);

    }
}