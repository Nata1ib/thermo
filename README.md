# Реализация задачи теплопроводности 2D с помощью Java Concurrency
## Описание задачи
Задача теплопроводности - это задача математического моделирования. 

На вход дается температура в исходном пространстве (сетке), а на выходе - температура через некоторое время. 

Математическое представление:

![image](https://github.com/coockie273/temperatureDistribution/assets/103525603/89a042ca-1ce2-4cc0-983c-14a58922cf9a)

где

- $x_i$ - координаты в декартовом пространстве

- $\partial t$ - дельта времени

- $\partial u$ - изменения температуры в каждой точке

- $\lambda$ - коэффициент теплопроводности

Изменение температуры на слое через какое-то количество времени равно сумме изменений температуры по каждому направлению с учетом коэффициента теплопроводности.
В данной реализации выбрана сетка 10x10 с изолированными границами.

## Метод решения задачи
Для решения задачи теплопроводности был выбран численный метод конечных разностей. Этот метод разбивает пространство на сетку из узловых точек и аппроксимирует производные уравнения с помощью разностных приближений.
## Реализация задачи с помощью Java Concurrency
Для решения задачи теплопроводимости была написана функция distribution класса TemperatureDistribution. При высчитывании каждого состояния сетки по каждому направлению, результаты вычисления не зависит друг от друга, исходя из этого эти вычисления можно распараллелить. Функция distribution создает 4 потока, которые параллельно высчитывают новое значение каждого состояния.

Для проверки реализации решения задачи теплопроводности 2D был аналитически создан набор тестов для разных данных - кол-во итераций и коэффициент теплопроводности. 



