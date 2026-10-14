-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: helpdesk
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` text,
  `ativo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Hardware','Problemas com computadores, monitores, periféricos e componentes físicos.',1),(2,'Software','Instalação, erros e licenças de sistemas operacionais e programas.',1),(3,'Redes e Internet','Instabilidade de conexão, cabo desconectado, Wi-Fi e VPN.',1),(4,'Sistemas Internos','Erros e dúvidas no ERP, CRM e sistemas de gestão da empresa.',1),(5,'Acessos e Permissões','Reset de senha, criação de contas e permissões de pasta.',1),(7,'Sistemas Operacionais','SO que organiza processo numa maquina',1),(8,'Sistemas Operacionais','e agoraa?',1);
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interacoes`
--

DROP TABLE IF EXISTS `interacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interacoes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mensagem` text NOT NULL,
  `data_interacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `solicitacao_id` int NOT NULL,
  `usuario_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_interacoes_solicitacao` (`solicitacao_id`),
  KEY `fk_interacoes_usuario` (`usuario_id`),
  CONSTRAINT `fk_interacoes_solicitacao` FOREIGN KEY (`solicitacao_id`) REFERENCES `solicitacoes` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_interacoes_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interacoes`
--

LOCK TABLES `interacoes` WRITE;
/*!40000 ALTER TABLE `interacoes` DISABLE KEYS */;
INSERT INTO `interacoes` VALUES (1,'Olá Fernanda, tudo bem? Vou passar na sua sala em 15 minutos para testar a fonte do monitor.','2026-10-07 15:48:40',1,2),(2,'Perfeito Mariana, estarei aqui aguardando. Obrigada!','2026-10-07 15:48:40',1,4),(3,'Fonte de alimentação testada e queimada. Solicitei a substituição com o almoxarifado.','2026-10-07 15:48:40',1,2),(4,'Senha provisória gerada e enviada para o e-mail cadastrado. Por favor, altere no primeiro acesso.','2026-10-07 15:48:40',2,3),(5,'Consegui acessar e já fiz a alteração da senha. Pode fechar o chamado, muito obrigado!','2026-10-07 15:48:40',2,5);
/*!40000 ALTER TABLE `interacoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitacoes`
--

DROP TABLE IF EXISTS `solicitacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitacoes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(150) NOT NULL,
  `descricao` text NOT NULL,
  `local_ocorrencia` varchar(150) DEFAULT NULL,
  `prioridade` varchar(20) NOT NULL DEFAULT 'MEDIA',
  `status` varchar(30) NOT NULL DEFAULT 'ABERTO',
  `data_abertura` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `data_fechamento` datetime DEFAULT NULL,
  `usuario_id` int NOT NULL,
  `tecnico_id` int DEFAULT NULL,
  `categoria_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_solicitacoes_usuario` (`usuario_id`),
  KEY `fk_solicitacoes_tecnico` (`tecnico_id`),
  KEY `fk_solicitacoes_categoria` (`categoria_id`),
  CONSTRAINT `fk_solicitacoes_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`),
  CONSTRAINT `fk_solicitacoes_tecnico` FOREIGN KEY (`tecnico_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `fk_solicitacoes_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitacoes`
--

LOCK TABLES `solicitacoes` WRITE;
/*!40000 ALTER TABLE `solicitacoes` DISABLE KEYS */;
INSERT INTO `solicitacoes` VALUES (1,'Monitor não liga após picos de energia','Após a queda de luz de ontem à tarde, meu monitor secundário não dá nenhum sinal de vida.','Bloco A - Sala 204','ALTA','EM_ANDAMENTO','2026-10-07 15:48:31',NULL,4,2,1),(2,'Esqueci a senha do sistema ERP','Preciso de reset na senha do sistema financeiro para emitir as notas fiscais de hoje.','Bloco B - Sala 101','URGENTE','CONCLUIDO','2026-10-07 15:48:31',NULL,5,3,5),(3,'Wi-Fi caindo frequentemente na sala de reunião','Durante as chamadas de vídeo, a conexão do Wi-Fi corporativo cai a cada 10 minutos.','Bloco C - Sala de Reunião Principal','MEDIA','ABERTO','2026-10-07 15:48:31',NULL,4,NULL,3);
/*!40000 ALTER TABLE `solicitacoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `perfil` varchar(50) NOT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1',
  `data_cadastro` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (2,'Mariana Lima','mariana.tecnica@empresa.com','$2a$12$a1B2C3D4E5F6G7H8I9J0Ku1V2W3X4Y5Z6A7B8C9D0E1F2G3H4I5J6','TECNICO',0,'2026-10-07 15:48:18'),(3,'Lucas Rocha','lucas.tecnico@empresa.com','$2a$12$z9Y8X7W6V5U4T3S2R1Q0Pu9O8N7M6L5K4J3I2H1G0F9E8D7C6B5A4','TECNICO',0,'2026-10-07 15:48:18'),(4,'Fernanda Silva','fernanda.silva@empresa.com','$2a$12$q1W2E3R4T5Y6U7I8O9P0Zu1X2C3V4B5N6M7A8S9D0F1G2H3J4K5L6','CLIENTE',0,'2026-10-07 15:48:18'),(5,'Roberto Alves','roberto.alves@empresa.com','$2a$12$m1N2B3V4C5X6Z7A8S9D0Fu1G2H3J4K5L6P7O8I9U0Y1T2R3E4W5Q6','CLIENTE',0,'2026-10-07 15:48:18'),(6,'João Souza','joao.cliente@empresa.com','123456','CLIENTE',1,'2026-10-07 15:57:35'),(7,'Ana Paula','ana.tecnica@empresa.com','senha123','TECNICO',0,'2026-10-07 15:57:35'),(8,'Admin Teste','admin.teste@empresa.com','admin','ADMIN',1,'2026-10-07 15:57:35'),(9,'solicitante','solicitante@gmail.com','12345','SOLICITANTE',0,'2026-10-12 11:01:19'),(10,'Juliana Martins','juliana.cliente@empresa.com','123456','CLIENTE',1,'2026-10-12 12:22:00'),(11,'Pedro Santos','pedro.cliente@empresa.com','senha123','CLIENTE',0,'2026-10-12 12:22:00'),(12,'Camila Oliveira','camila.cliente@empresa.com','teste','CLIENTE',0,'2026-10-12 12:22:00'),(14,'Aline Ribeiro','aline.clientedenov@empresa.com','12345','CLIENTE',0,'2026-10-12 12:22:00'),(15,'Rafael Costa','rafael.cliente@empresa.com','123456','CLIENTE',0,'2026-10-12 12:26:00'),(16,'Larissa Mendes','larissa.cliente@empresa.com','senha123','CLIENTE',0,'2026-10-12 12:26:00'),(17,'Diego Pereira','diego.cliente@empresa.com','teste','CLIENTE',0,'2026-10-12 12:26:00'),(18,'Patrícia Lima','patricia.cliente@empresa.com','mudar123','CLIENTE',0,'2026-10-12 12:26:00'),(19,'Felipe Gomes','felipe.cliente@empresa.com','12345','CLIENTE',0,'2026-10-12 12:26:00');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'helpdesk'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-14 15:50:30
