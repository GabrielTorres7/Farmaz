DELETE FROM uf;
DELETE FROM cidade;
DELETE FROM produto;
DELETE FROM cliente;
DELETE FROM farmacia;
DELETE FROM uf;
DELETE FROM endereco;
DELETE FROM disponibilidade;

insert into uf(cod_uf, sigla, nome)
values
(7,'MS','Mato Grosso do Sul'),
(8,'MT','Mato Grosso'),
(9,'PI','Piauí'),
(10,'RN','Rio Grande do Norte'),
(11,'AM','Amazonas');

insert into cidade
(cod_cidade, nome, cod_uf)
values
(2,'Rio Branco', 1),
(3,'Rio de Janeiro', 3),
(4,'Sao Paulo', 4),
(5,'Salvador', 5),
(6,'Porto Alegre', 6),
(7,'Campo Grande', 7),
(8,'Cuiabá', 8),
(10,'Teresina', 9),
(11,'Natal', 10),
(12,'Manaus', 11),
(13,'Campinas', 4),
(14,'Ribeirão Preto', 4);

insert into produto
(seq_produto, nome, receita, descricao, laboratorio, cadastro_anvisa)
values
(3,'Neosaldina', false, 'Remedio para dor', 'JesusCristoPai', 456123),
(4,'Desloratadina', false, 'Alergia', 'NeymarLab', 476123),
(5,'Remedio do Allan', true, 'Remedio de doido', 'NeymarLab', 456783),
(6,'Bala Fini', false, 'Bala com acucar', 'Fini', 477723),
(7,'Kit Kat', false, 'Chocolate', 'Garoto', 456129),
(8,'Desodorante Rexona', false, 'Desodorante antitranspirante', 'Rexona', 456128),
(9,'DorFlex envelope', false, 'Dipirona 300mg', 'Sanofi Aventis', 456127),
(10,'Sabonete Pom Pom', false, 'Sabonete infantil', 'Pom Pom', 456126),
(11,'Sanctio Capilar', false, 'Reduz e previne a queda cabelo', 'Yeva cosmetiques', 456171),
(12,'Protetor Solar 30 FPS', false, 'Protetor solar', 'Vichy', 456573),
(13,'Olla camisinha', false, 'Camisinha', 'Olla',461723),
(14,'K-Y lubrificante', false, 'Gel Lubrificante', 'K-Y',465823),
(15,'Retalina', true, 'Antidepressivo', 'Novartis', 378523),
(16,'Codeína', true, 'Raffa Moreira mano', 'Raffa777', 134423);


insert into farmacia
(cadastro_prefeitura, cod_cidade, cnpj, nome, cep, bairro, rua, numero, cod_uf, email, senha)
values
('1111',4, '15.484.797', 'Araujo', '30441031', 'Campo Belo', 'Rua Constantino de Sousa', 1057, 4, 'araujo@johson.com', 'd5ac90ee70edc2d4a08818b6bfd1d5ef'),
('1112',4, '15.484.796', 'Drogarias Brasil', '35714031', 'Brooklin Paulista', 'Rua Luisiânia', 793, 4, 'alexturner@arcticmonkeys.com', 'd5ac90ee70edc2d4a08818b6bfd1d5ef'),
('1113',4, '15.484.795', 'Araujo', '36786031', 'Vila Olimpia', 'Rua Antônio Cardoso', 194, 76, 'araujo@johson.com', 'd5ac90ee70edc2d4a08818b6bfd1d5ef'),
('1114',1, '15.484.794', 'Drogas Raia', '30441031', 'Gutierrez', 'Rua Holanda lima', 194, 2, 'alex@turner.com', '38a32fc2ec8d7d6593381c185b330036'),
('1115',1, '15.484.793', 'Fluorescent Drugstore', '30468731', 'Barro Preto', 'Rua Juiz de Fora', 115, 2, 'arabella@johson.com', '38a32fc2ec8d7d6593381c185b330036'),
('1116',1, '15.484.792', 'Drugstore NA', '30447771', 'Barro Preto', 'Avenida do Contorno', 10500, 2, 'hotel@casino.com', '38a32fc2ec8d7d6593381c185b330036'),
('1117',3, '15.584.791', 'Arabella', '7777777', 'Barra da Tijuca,', 'Avenida Ayrton Senna', 2000, 3, 'tranquility@base.com', 'e88f533227897381ee04fba9fa103ee6'),
('1118',3, '16.447.797', 'R U Drugs', '36421476', 'Riachuelo', 'Rua Magalhães Castro', 201, 3, 'arcticmonkeys@gmail.com', 'e88f533227897381ee04fba9fa103ee6'),
('1119',10, '17.324.797', 'Do I Wanna Drugs', '36851721', 'Satélite', 'Rua Júpiter', 4010, 9, 'am@gmail.com', 'ffed0c4f7569b41ad21b9e4374aac372'),
('1110',8, '18.123.797', 'Araujo', '33877031', 'Quilombo', 'Avenida São Sebastião', 308, 8, 'araujo@johson.com', '60158098e4951cd1abb61bd9f76fd687');
/*
	São Paulo - arabella

	MG - arcticmonkeys

	MT - areyoumine?

	PI - torrestroll

	RJ - allanbobinho
*/


insert into cliente
(seq_cliente, email, nome, documento_identificacao, telefone, senha)
values
(1,'gabieltorres7@hotmail.com', 'GabrielT7', '10714632686', '03148415685', '1cafe41f3be93b91d829278f0fa670dc'),
(2,'arabella@am.com', 'Allan', '20714632686', '05548415456', '1cafe41f3be93b91d829278f0fa670dc'),
(3,'fofis@gmail.com', 'Arthur', '30714632686', '03467615789', '1cafe41f3be93b91d829278f0fa670dc'),
(4,'hiago@hotmail.com', 'Hiago', '40714632686', '01148415685', '1cafe41f3be93b91d829278f0fa670dc'),
(5,'raffamoreira@mano.com', 'Raffa Moreira', '50714632686', '77777777777', '1cafe41f3be93b91d829278f0fa670dc'),
(6,'fhcemail@hotmail.com', 'Luis Henrique', '60714632686', '03166615685', '1cafe41f3be93b91d829278f0fa670dc'),
(7,'cristianoronaldo@hotmail.com', 'Joaquim de Souza', '70714632686', '0311405685', '1cafe41f3be93b91d829278f0fa670dc'),
(11,'jaorenato@hotmail.com', 'Joao Silva', '80714632686', '03140215685', '1cafe41f3be93b91d829278f0fa670dc'),
(9,'am@hotmail.com', 'Alex Turner', '90714632686', '0314758485', '1cafe41f3be93b91d829278f0fa670dc'),
(10,'farbetter@gmail.com', 'Lucas fonseca', '10123632686', '03201815685', '1cafe41f3be93b91d829278f0fa670dc');
/* 
	Senha - jairbolsonaro 
*/



insert into endereco
(seq_endereco, seq_cliente, cod_cidade, cep, bairro, rua, numero, cod_uf)
values
(1, 1, 1, '30778031', 'Caiçaras', 'R. Itaguaí', 406, 2),
(2, 1, 1, '30645031', 'Saudade', 'Rua Juramento', 1464, 2),
(3, 2, 1, '30410431', 'Havaí', 'Rua Frutuoso Viana', 73, 2),
(9, 3, 3, '30773531', 'Barra da Tijuca', 'Av. Malibu', 143, 3),
(5, 5, 4, '77777777', 'Sumaré', 'Rua Havaí', 549, 4),
(6, 6, 8, '31404731', 'Araes', 'Avenida Mato Grosso', 188, 8),
(7, 6, 8, '30123431', 'Baú', 'Rua Pedro de Oliveira Guimarães', 100, 8),
(8, 11, 4, '30478531', 'Sumarezinho', 'Rua Heitor Penteado', 1840, 4);



insert into disponibilidade
(seq_disponibilidade, seq_produto, cadastro_prefeitura, estoque, preco, avaliacao)
values
(1, 16, '1111', 100, 50, 0),
(2, 1, '1111', 250, 40, 0),
(3, 2, '1111', 140, 55, 0),
(4, 3, '1111', 350, 39, 0),
(5, 4, '1111', 0, 29, 0),
(6, 5, '1111', 25, 50, 0),
(7, 6, '1111', 100, 50, 0),
(8, 7, '1111', 200, 54, 0),
(9, 8, '1111', 100, 30, 0),
(10, 9, '1111', 140, 50, 0),
(11, 10, '1111', 100, 19, 0),
(12, 11, '1111', 70, 90, 0),
(13, 12, '1111', 0, 10, 0),
(14, 13, '1111', 800, 5, 0),
(15, 14, '1111', 135, 5, 0),
(16, 15, '1111', 100, 35, 0),
(17, 16, '1114', 100, 39, 0),
(18, 1, '1114', 250, 45, 0),
(19, 2, '1114', 140, 55, 0),
(20, 3, '1114', 350, 33, 0),
(21, 4, '1114', 100, 18, 0),
(22, 5, '1114', 25, 89, 0),
(23, 6, '1114', 100, 57, 0),
(24, 7, '1114', 200, 35, 0),
(25, 8, '1114', 100, 10, 0),
(26, 9, '1114', 140, 17, 0),
(27, 10, '1114', 100, 15, 0),
(28, 11, '1114', 70, 150, 0),
(29, 12, '1114', 0, 13, 0),
(30, 13, '1114', 800, 8, 0),
(31, 14, '1114', 135, 8, 0),
(32, 15, '1114', 100, 39, 0),
(33, 16, '1117', 100, 50, 0),
(34, 1, '1117', 250, 39, 0),
(35, 2, '1117', 0, 60, 0),
(36, 3, '1117', 350, 30, 0),
(37, 4, '1117', 100, 49, 0),
(38, 5, '1117', 25, 55, 0),
(39, 6, '1117', 100, 54, 0),
(40, 7, '1117', 200, 38, 0),
(41, 8, '1117', 100, 69, 0),
(42, 9, '1117', 140, 59, 0),
(43, 10, '1117', 100, 17, 0),
(44, 11, '1117', 70, 130, 0),
(45, 12, '1117', 180, 9, 0),
(46, 13, '1117', 800, 7, 0),
(47, 14, '1117', 135, 7, 0),
(48, 15, '1117', 100, 39, 0),
(49, 16, '1118', 100, 53, 0),
(50, 1, '1118', 250, 49, 0),
(51, 2, '1118', 140, 45, 0),
(52, 3, '1118', 350, 29, 0),
(53, 4, '1118', 100, 19, 0),
(54, 5, '1118', 25, 45, 0),
(55, 6, '1118', 100, 57, 0),
(56, 7, '1118', 200, 59, 0),
(57, 8, '1118', 100, 32, 0),
(58, 9, '1118', 140, 49, 0),
(59, 10, '1118', 100, 26, 0),
(60, 11, '1118', 70, 79, 0),
(61, 12, '1118', 180, 19, 0),
(62, 13, '1118', 800, 8, 0),
(63, 14, '1118', 0, 3, 0),
(64, 15, '1118', 100, 39, 0),
(65, 16, '1110', 100, 40, 0),
(66, 1, '1110', 250, 30, 0),
(67, 2, '1110', 140, 59, 0),
(68, 3, '1110', 350, 37, 0),
(69, 4, '1110', 100, 27, 0),
(70, 5, '1110', 25, 43, 0),
(71, 6, '1110', 0, 70, 0),
(72, 7, '1110', 200, 17, 0),
(73, 8, '1110', 100, 28, 0),
(74, 9, '1110', 140, 47, 0),
(75, 10, '1110', 100, 25, 0),
(76, 11, '1110', 0, 85, 0),
(77, 12, '1110', 180, 13, 0),
(78, 13, '1110', 800, 2, 0),
(79, 14, '1110', 135, 8, 0),
(80, 15, '1110', 100, 37, 0),
(81, 16, '1115', 100, 69, 0),
(82, 1, '1115', 0, 76, 0),
(83, 2, '1115', 140, 59, 0),
(84, 3, '1115', 0, 47, 0),
(85, 4, '1115', 100, 28, 0),
(86, 5, '1115', 25, 17, 0),
(87, 6, '1115', 0, 49, 0),
(88, 7, '1115', 200, 27, 0),
(89, 8, '1115', 100, 35, 0),
(90, 9, '1115', 140, 47, 0),
(91, 10, '1115', 100, 17, 0),
(92, 11, '1115', 70, 99, 0),
(93, 12, '1115', 180, 19, 0),
(94, 13, '1115', 0, 8, 0),
(95, 14, '1115', 135, 3, 0),
(96, 15, '1115', 100, 75, 0);

















