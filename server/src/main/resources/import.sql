--
-- JBoss, Home of Professional Open Source
-- Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
insert into MemberHibernate4Demo (id, name) values (1, 'John Smith') 
insert into MemberHibernate4Demo (id, name) values (2, 'Madhumita Sadhukhan') 

insert into Questions (id, question) values (1, 'Возраст')
insert into Questions (id, question) values (2, 'Пол')
insert into Questions (id, question) values (3, 'Как часто Вам приходиться обращаться в медицинские организации за медицинской помощью?')

insert into TypeOfResponse (id, type_response,question_tab_id) values (1, 'до 18 лет','1')
insert into TypeOfResponse (id, type_response,question_tab_id) values (2, 'от 18 до 25 лет','1')
insert into TypeOfResponse (id, type_response,question_tab_id) values (3, 'от 26 до 35 лет','1')
insert into TypeOfResponse (id, type_response,question_tab_id) values (4, 'от 36 до 45 лет','1')
insert into TypeOfResponse (id, type_response,question_tab_id) values (5, 'от 46 до 60 лет','1')
insert into TypeOfResponse (id, type_response,question_tab_id) values (6, 'свыше 60 лет','1')

insert into TypeOfResponse (id, type_response,question_tab_id) values (7, 'мужской','2')
insert into TypeOfResponse (id, type_response,question_tab_id) values (8, 'женский','2')

insert into TypeOfResponse (id, type_response,question_tab_id) values (9, 'ежемесячно и чаще','3')
insert into TypeOfResponse (id, type_response,question_tab_id) values (10, 'несколько раз в год','3')
insert into TypeOfResponse (id, type_response,question_tab_id) values (11, 'редко','3')






