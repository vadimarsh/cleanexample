# Приложение "Планировщик студента-должника"
## Функции приложения:
- Добавление нового предмета по которому имеется задолженность
- Добавление нового задания по предмету
- Просмотр списка заданий
  - Отметить задание выполненным
  - Изменение дедлайна для задания
- Просмотр незакрытых заданий
- Просмотр статуса долгов по предметам
- Изменение статуса предмета на основе наличия закрытых/незакрытых заданий по нему

## Архитектура:
### слой бизнес-логики
<details>
 <summary> domain </summary>

- [entity](src/domain/entity) - классы сущности предметной области
- [usecase](src/domain/usecase) - доступные в приложении функции над сущностями
- [port](src/domain/port) - порты(интерфейсы) для получения доступа к хранилищам данных из слоя data, без создания зависимости от деталей реализации хрпнилища 
- exceptions - исключения порождаемые бизнес-логикой
</details>

### слой доступа к даннным
<details>
<summary> data </summary>

- repository - высокоуровневые хранилища данных (оперируют сущностями) 
  + [infile](src/data/repository/infile) - пример реализации хранилища для работы с хранением в файле
  + [inmemory](src/data/repository/inmemory) - пример реализации тестового хранилища в оперативной памяти
- [storage](src/data/storage) - низкоуровневое хранилище данных (оперируют промежуточными моделями данных, специфичных для метода хранения)
  + [dto](src/data/storage/dto) - модели данных для файлового хранилища, умеют отображаться в сущности предметной области
  + InFileВGenerator - генератор ID для объектов классов-сущностей
  + InFileStorage - реализация хранилища данных для приложения на основе текстовых файлов
</details>

### слой представления
<details>
<summary> presentation </summary>

- [config](src/presentation/config) - объект "конфигурация", отвечает за поставку зависимостей в слой представления 
- [gui](src/presentation/gui) - все что касается GUI
  +  mainwindow - главное окно приложения
      - MainWindowView - создание и инициализация окна и раскладка компонентов
      - MainWindowController - контроллер главного окна, содержит обработчики событий для реализации логики интерфейса, а также связывает вид и модель данных для окна
      - MainTableModel - модель таблицы для главного окна
      - DisciplComboBoxModel - модель для комбобокса выбора предмета
  +  disciplwindow - окно для редактирования списка предметов
      - DisciplinesWindowView - создание и инициализация окна и раскладка компонентов
      - DisciplinesWindowController - контроллер окна просмотра предметов, содержит обработчики событий для реализации логики интерфейса, а также связывает вид и модель данных для окна
      - DisciplinesTableModel - модель таблицы для окна просмотра предметов
  
  + [components](src/presentation/gui/components) - кастомные дополнительные компоненты
    - JDateCellEditor - компонент для редактирования даты в таблице

</details>

**Слой data зависит только от domain, слой presentation зависит от domain и data, а слой domain не зависит ни от кого - он не знает ничего о других слоях**
![](cleanarch.svg)