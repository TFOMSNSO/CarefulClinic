// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  BACKEND_URL: window.location.protocol + '//' + window.location.hostname + ':' + 8080,
  path_informing: '/content/report/informing',
  path_downloads: '/content/donwload',
  path_uploads: '/content/upload',
  title:'Карточка',
  tab1:'Данные РС ЕРЗ',
  tab2:'Данные АИС ГЭР',
  tab3:'Информирование',
  about:'Подробнее',
  no_expertise_found:'Данные об экспертизе отсутствуют',
  lpu_mkb:'ЛПУ МКБ',
  lpu_mes:'ЛПУ МЭС',
  exp_mes:'ЭКСП МЭС',
  lpu_ukmp:'ЛПУ УКМП',
  cod_exp:'Код экспертизы',
  kod_sp:'КОД СП',
  def_main:'Основной дефект снятия',
  exam_date:'Дата проведения экспертизы',
  exam_code:'Вид медицинской экспертизы',
  doct_name:'Врач',
  title_exp: 'Экспертиза',
  name_exp:'Эксперт',
  ser_polis:'Серия полиса',
  nom_polis:'Номер полиса',
  summa:'Сумма',
  shtraf:'Штраф',
  title_treat:'Лечение',
  date_begin: 'Дата начала',
  date_end: 'Дата окончания',
  treat_types: 'Типы лечения',
  treat_type1: 'Хирургическое лечение',
  treat_type2: 'Лекарственная противоопухолевая терапия',
  treat_type3: 'Лучевая терапия',
  treat_type4: 'Химиолучевая терапия',
  treat_type5: 'Неспецифическое лечение',
  treat_type6: 'Диагностика',
  mkb: 'МКБ',
  ot_profk:'Отделение/Кабинет',
  lpu_name:'Лечебное учреждение',
  no_treatment:'Типы лечения отсутствуют',
  mes_ksg: 'МЭС/КСГ',
  surname : 'Фамилия',
  firstname : 'Имя',
  lastname : 'Отчество',
  bithday : 'Дата рождения',
  telefon :'Телефон',
  t_years : 'Полных лет',
  start_date_etap1 : 'Дата начала первого этапа',
  end_date_etap1 : 'Дата окончания первого этапа',
  start_date_etap2 : 'Дата начала второго этапа',
  end_date_etap2 : 'Дата окончания второго этапа',
  ref_id_person : 'ИД пациента в ИС ГЭР',
  pm_god : 'Год прохождения диспансеризации',
  pm_kvartal : 'Квартал прохождения диспансеризации',
  PM_HOSPITAL_RESULT : 'Код ЛПУ',
  adress : 'Адрес пациента',
  tel : 'Телефон пациента',
  pm_result : 'Результат прохождения',
  close_card : 'Закрыть',
  nstage : 'Этап информирования',
  dinfo : 'Дата информирования',
  tinfo : 'Вид информирования',
  data_not_found: 'Данные не найдены',
  lists_insur: 'Списки застрахованных лиц',
  search_table: 'Поиск по таблице',
  list_insur_menu: ' Списки застрахованных',
  serch_form:'Поиск',
  field_is_required:'поле обязательно для заполнения',
  reset:'сбросить',
  cancel:'отменить',
  action_add_person: 'Застрахованное лицо найдено в РС ЕРЗ',
  add_table: 'Добавлен в список',
  bad_action_add_person : 'Данные не найдены в РС ЕРЗ или ЗЛ не является вашим клиентом',
  no_inform:'не проинформирован',
  one_part_inform:'информирован о первом этапе',
  second_part_inform:'информирован о втором этапе',
  secondory_inform:'повторное информирование',
  tinfo_1: 'sms',
  tinfo_2: 'Телефон',
  tinfo_3: 'Электронная почта',
  tinfo_4: 'Средствами почтовой связи',
  tinfo_5: 'Личное информирование',
  tinfo_6: 'Информирование невозможно',
  tinfo_7: 'Диспансеризация проведена',
  linksmo: 'Страховая компания',
  linksmo_2: 'ООО ВТБ МС',
  linksmo_1: 'ООО «СИМАЗ-МЕД»',
  linksmo_4: 'СК «Ингосстрах-М»',
  otkreplen: 'откреплен',
  exit_app:'Выйти из приложения',
  searchFIOD:'Поиск по ФИОД',
  searchKEYS:'Поиск по условию',
  exportExcel:'Экспорт в MS Excel',
  age:'Возраст',
  sexmans:'Мужской',
  sexmale:'Женский',
  ex:'От',
  through:'До',
  mo:'Медицинская организация',
  motype:'Тип МО',
  withtel:'С телефонами (РС ЕРЗ)',
  inform:'Информирование',
  I:'I этап',
  II:'II этап',
  III:'Информировать повторно',
  sms:'смс  ',
  telsearchkeys:'телефон',
  email:'эл. почта',
  mail:'почта',
  personaly:'лично',
  notfound:'невозможно',
  doneexamination:'Прошел диспансеризацию',
  periodinf:'Период информирования',
  dispanser:'Диспансеризация по данным из "ГЭР"',
  statedisp:'выберите статус',
  causenotes:'ограничение выборки',
  cleartable:'Очистить таблицу',
  survey:'Опрос',//опрос
  downloadtask:'Скачать',
  statsurvey1:'Нет свободного времени',
  statsurvey2:'Работодатель не отпускает',
  statsurvey3:'Находится за пределами города',
  statsurvey4:'Уход за иждивенцами (ребенок,  инвалид)',
  statsurvey5:'Пройду позже',
  statsurvey6:'Не подходит график работы МО для проведения ПМ (в т.ч. не работает в выходные дни)',
  statsurvey7:'Отдаленность МО от места проживания',
  statsurvey8:'Длительный срок прохождения ПМ',
  statsurvey9:'Ненадлежащее оказание ранее полученной медицинской помощи',
  statsurvey10:'Некорректное отношение медицинского персонала',
  statsurvey11:'Очередность в МО',
  statsurvey12:'Отсутствие оборудования/старое оборудование',
  statsurvey13:'Отсутствие выездных бригад',
  statsurvey14:'Находится на диспансерном учета',
  statsurvey15:'Находится на учете по беременности',
  statsurvey16:'Проходит профосмотр по месту работы',
  statsurvey17:'Недавно был на стационарном лечении /в дневном стационаре',
  statsurvey18:'Другое (пояснение в примечании)',
  statsurvey19:'Не хочу',
  statsurvey101:'Не проходил диспансеризацию',
  statsurvey102:'Полностью удовлетворен',
  statsurvey103:'Не удовлетворен длительным срок прохождения диспансеризации',
  statsurvey104:'Не удовлетворен качеством медицинской помощи при прохождении диспансеризации',
  statsurvey105:'Не удовлетворен за счет формального отношение в МО к пациенту',
  statsurvey106:'Не удовлетворен некорректным отношением медицинского персонала',
  statsurvey107:'Не удовлетворен очередностью в МО при диспансеризации',
  statsurvey108:'Не удовлетворен разрозненностью отделений МО для прохождения диспансеризации',
  statsurvey109:'Не удовлетворен не удобным графиком работы МО при диспансеризации',
  statsurvey110:'Не удовлетворен объемом медицинской помощи при диспансеризации',
  statsurvey111:'Затрудняюсь ответить',
  statsurvey20:'Не знал',
  statsurvey_result:'Результат опроса',
  statsurvey_date:'Дата опроса',
  titlesurvey:'Оглавление',
  maintitlesurvey:'Внести результат опроса',
  nonrespond:'Не опрошен',
  respond:'Опрошен',
  survey_stat:'релультат',
  kv1:'в плане I-го квартала',
  kv2:'в плане II-го квартала',
  kv3:'в плане III-го квартала',
  kv4:'в плане IV-го квартала',
  plandisp:'План информирования',
  infoI:'Информировать о I-м этапе',
  infoII:'Информировать о II-м этапе',
  infoIII:'Повторное информирование',
  uploadfile:'Загрузить данные',
  pull:'Пул загруженных данных и протоколов загрузки',
  drop:'Переместите сюда файл',
  list_inform_header:'Списки для информирования',
  menu_main_page:'Главная   страница',
  menu_report_page:'Отчеты',
  report_inform:'Первичное информирование',
  report_inform_note:'Выберите тип отчета',
  report_inform_plane:'План информирования за выбранный квартал',
  report_inform_btn_download:'Скачать',
  report_inform_all:'Подлежащие информированию за текущий год (п.211 правил)',
  report_inform_note_after:'Выбранный отчет',
  report_inform_select_kv:'Выберите квартал',
  report_reinform:'Повторное информирование',
  menu_admin:'Администрирование',
  menu_admin_inform_allyear:'Сформировать информирование за текущий год (п.211 правил)',
  admin_inform_mainsubmodul_title:'Администрирование блока данных о информировании',
  reestr_inform_all_year_download_file:'Реестр подлежащих информированию  на текущий год',
  reestr_file:'Файл',
  reestr_download:'скачать',
  report_inform_about_second_level:'Информирование о 2-м этапе',
  year_disp:'Выберите год прохождения диспансеризации:  ',
  main_title:'Единое информационное пространство Новосибирской области',
  title_sp3_exptise:'Отчеты / Страховой представитель 3 /',
  title_zno: 'Списки ЗНО',
  expertise_field1:'Контроль МКБ в 3а группе здоровья',
  expertise_field2:'Контроль МКБ в 1 и 3б группах здоровья',
  a3_3b_other:'Контроль 3 группы на назначение лечебно-профилактических мероприятий',
  a3_3b_other_noNazrNoGosp:'Контроль 3 группы на постановку на Д-учет',
  after_disp_3_group:'По итогам диспансеризации установлена 3 группа здоровья',
  list_resolved:'Сформированные отчеты',
  expertise_field1_1:'Выберите временной период по счетам пролеченных',
  sp3_menu:'Страховой представитель 3',
  sp3_menu_expertisa:'Экспертиза',
  profmedosmtr:'Профилактические мед.осмотры',
  statistics_inform_header:'Статистика информирования',
  title_stat_inform:'Отчеты / Статистика информирования',
  report_stat_inform :'Сведения об организации информирования застрахованных лиц о возможности прохождения диспансеризации',
  report_inform_plane_actual :'Актуальный план информирования за определенный квартал',
  title_stat_assent :'Отчеты / Статистика согласий',
  statistics_assent_header:'Статистика согласий',
  assent_field1_1:'Введите период',
  report_stat_assent:'Информация о количестве информационных согласий',
  sp3_menu_d_reestr:'Список ЗЛ, стоящих на Д-учете',
  info_d_reestr:'Формирование списка информирования ЗЛ, стоящих на Д-учете, на текущий период',
  info_d_reestr1:'Информирования ЗЛ, стоящих на Д-учете',
  telephone_surveys:'Телефонные опросы',
  error1:'один ответ из А в один месяц',
  error2:'один ответ из А в разные месяцы',
  error3:'разные ответы из А в один месяц',
  error4:'разные ответы из А в разные месяцы',
  error5:'один ответ из Б в один месяц',
  error6:'один ответ из Б в разные месяцы',
  error7:'разные ответы из Б (кромеме со 103 по 110) в один месяц',
  error8:'разные ответы из Б (кромеме со 103 по 110) в разные месяцы',
  error9:'ответ из А и Б =101, 111 в одну дату',
  error10:'пересечение ответов из Б 100-101, 100-102, 100-111, 101-102, 101-111, 102-111',
  error11:'ответ из Б=111 и Примечание = Не проходил',
  error12:'ответ из 100 - в разные даты опроса',
  resstat:'НЕ ПРОШЕЛ(А) ФЛК',
  table3:'Таблица 3. Отчет об информировании ЗЛ (1 раз в 3 года)',
  table4:'Таблица 4. Отчет об информировании ЗЛ (1 раз в 2 года)',
  table5:'Таблица 5. Отчет об информировании ЗЛ (2-ой этап)',
  table6:'Таблица 6. Отчет об информировании ЗЛ (стоят на Д-учете)',
  total3group:'Количество случаев, по которым по итогам диспансеризации установлена 3 группа здоровья',
  flk1:'Сведения о численности застрахованных лиц, принявших участие в телефонном опросе по вопросам прохождения диспансеризации',
  flk2:'Информация о форматно-логическом контроле при приеме информации от страховых медицинских организаций',
  flk3:'Информация о застрахованных лицах, сведения о которых не приняты для формирования отчетной формы',
  flk4:'Таблица 7. Сведения об организации телефонных опросов застрахованных лиц, включенных медицинскими организациями в списки для проведения диспансеризации.'
};
