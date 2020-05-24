-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2019 at 04:33 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `thibanglaixe`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `id` int(10) NOT NULL,
  `question_id` int(10) NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `question_id`, `content`, `status`, `created_time`, `updated_time`, `deleted`) VALUES
(17, 12, 'Gồm: Đường, cầu đường bộ.', 1, '2019-11-10 17:19:30', '2019-11-10 17:19:30', 0),
(18, 12, 'Gồm: Hầm đường bộ, bến phà đường bộ.', 1, '2019-11-10 17:20:23', '2019-11-10 17:20:23', 0),
(19, 12, 'Gồm: Đường, cầu đường bộ, hầm đường bộ, bến phà đường bộ và các công trình phụ trợ khác.', 0, '2019-11-10 17:22:27', '2019-11-10 17:22:27', 0),
(20, 13, 'Vạch kẻ đường là vạch chỉ sự phân chia làn đường, vị trí hoặc hướng đi, vị trí dừng lại.', 1, '2019-11-10 17:31:28', '2019-11-10 17:31:28', 0),
(21, 13, 'Vạch kẻ đường là vạch chỉ sự phân biệt vị trí dừng, đỗ trên đường.', 0, '2019-11-10 17:31:28', '2019-11-10 17:31:28', 0),
(22, 13, 'Tất cả các ý nêu trên.', 0, '2019-11-10 17:32:09', '2019-11-10 17:32:09', 0),
(23, 14, 'Là phần của đường bộ được sử dụng cho các phương tiện giao thông qua lại.', 1, '2019-11-10 17:37:48', '2019-11-10 17:37:48', 0),
(24, 14, 'Là phần đường bộ được sử dụng cho các phương tiện giao thông qua lại, dải đất dọc hai bên đường để đảm bảo an toàn giao thông.', 0, '2019-11-10 17:37:48', '2019-11-10 17:37:48', 0),
(25, 14, 'Là phần đường bộ được sử dụng cho các phương tiện giao thông qua lại, các công trình, thiết bị phụ trợ khác và dải đất dọc hai bên đường để đảm bảo an toàn giao thông.', 0, '2019-11-10 17:37:48', '2019-11-10 17:37:48', 0),
(26, 15, 'Là một phần của đường được chia theo chiều ngang của đường, có bề rộng đủ cho xe đỗ an toàn.', 0, '2019-11-10 17:42:05', '2019-11-10 17:42:05', 0),
(27, 15, 'Là một phần của đường xe chạy được chia theo chiều dọc của đường, có bề rộng đủ cho xe chạy an toàn.', 1, '2019-11-10 17:42:05', '2019-11-10 17:42:05', 0),
(28, 15, 'Cả 2 ý trên', 0, '2019-11-10 17:42:05', '2019-11-10 17:42:05', 0),
(29, 16, 'Đường phố là đường đô thị, gồm lòng đường và hè phố.', 1, '2019-11-10 17:45:34', '2019-11-10 17:45:34', 0),
(30, 16, 'Đường phố là đường bộ ngoài đô thị có lòng đường đủ rộng cho các phương tiện giao thông qua lại.', 0, '2019-11-10 17:45:34', '2019-11-10 17:45:34', 0),
(31, 16, 'Cả 2 ý nêu trên.', 0, '2019-11-10 17:45:34', '2019-11-10 17:45:34', 0),
(32, 17, 'Là bộ phận của đường để xác định ranh giới của đất dành cho đường bộ theo chiều ngang của đường.', 0, '2019-11-10 17:51:29', '2019-11-10 17:51:29', 0),
(33, 17, 'Là bộ phận của đường để ngăn cách không cho các loại xe vào những nơi không được phép.', 0, '2019-11-10 17:51:29', '2019-11-10 17:51:29', 0),
(34, 17, 'Là bộ phận của đường để phân chia mặt đường thành hai chiều xe chạy riêng biệt hoặc để phân chia phần đường của xe cơ giới với xe thô sơ.', 1, '2019-11-10 17:51:29', '2019-11-10 17:51:29', 0),
(35, 18, 'Đúng', 1, '2019-11-10 17:54:29', '2019-11-10 17:54:29', 0),
(36, 18, 'Sai', 0, '2019-11-10 17:54:29', '2019-11-10 17:54:29', 0),
(37, 19, 'Đường ưu tiên là đường mà trên đó phương tiện tham gia giao thông đường bộ phải nhường đường cho các phương tiện đến từ hướng khác khi qua nơi đường giao nhau, có thể được cắm biển đường ưu tiên.', 0, '2019-11-10 18:06:01', '2019-11-10 18:06:01', 0),
(38, 19, 'Đường ưu tiên là đường mà trên đó phương tiện tham gia giao thông đường bộ được các phương tiện đến từ hướng khác nhường đường khi qua nơi đường giao nhau được cắm biển báo hiệu đường ưu tiên.', 1, '2019-11-10 18:06:01', '2019-11-10 18:06:01', 0),
(39, 19, 'Đường ưu tiên là đường chỉ dành cho một số loại phương tiện tham gia giao thông, được cắm biển báo hiệu đường ưu tiên.', 0, '2019-11-10 18:06:01', '2019-11-10 18:06:01', 0),
(40, 20, 'Phương tiện giao thông cơ giới đường bộ.', 1, '2019-11-10 18:10:38', '2019-11-10 18:10:38', 0),
(41, 20, 'Phương tiện giao thông thô sơ đường bộ.', 1, '2019-11-10 18:10:38', '2019-11-10 18:10:38', 0),
(42, 20, 'Phương tiện giao thông cơ giới đường bộ, phương tiện giao thông thô sơ đường bộ và xe máy chuyên dùng', 0, '2019-11-10 18:10:38', '2019-11-10 18:10:38', 0),
(43, 21, 'Phá hoại đường, cầu, hầm, bến phà đường bộ, phá hoại đèn tín hiệu cọc tiêu, biển báo hiệu, gương cầu, dải phân cách.', 1, '2019-11-10 18:18:22', '2019-11-10 18:18:22', 0),
(44, 21, 'Phá hoại hệ thống thoát nước và các công trình, thiết bị khác thuộc kết cấu hạ tầng giao thông đường bộ.', 1, '2019-11-10 18:18:22', '2019-11-10 18:18:22', 0),
(45, 21, 'Đua xe, cổ vũ đua xe, tổ chức đua xe trái phép.', 1, '2019-11-10 18:18:22', '2019-11-10 18:18:22', 0),
(46, 21, 'Lạng lách, đánh võng.', 1, '2019-11-10 18:18:22', '2019-11-10 18:18:22', 0),
(47, 22, '50', 1, '2019-11-10 18:24:04', '2019-11-10 18:24:04', 0),
(48, 23, '0,25', 1, '2019-11-10 18:26:36', '2019-11-10 18:26:36', 0),
(49, 24, 'Đúng.', 0, '2019-11-10 18:45:10', '2019-11-10 18:45:10', 0),
(50, 24, 'Sai.', 1, '2019-11-10 18:45:10', '2019-11-10 18:45:10', 0),
(51, 25, 'Là người điều khiển phương tiện tham gia giao thông.', 0, '2019-11-10 18:50:20', '2019-11-10 18:50:20', 0),
(52, 25, 'Là người được giao nhiệm vụ hướng dẫn giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt.', 1, '2019-11-10 18:50:20', '2019-11-10 18:50:20', 0),
(53, 25, 'Là cảnh sát giao thông.', 1, '2019-11-10 18:50:20', '2019-11-10 18:50:20', 0),
(54, 25, 'Tất cả các ý nêu trên.', 0, '2019-11-10 18:50:20', '2019-11-10 18:50:20', 0),
(55, 26, '18', 1, '2019-11-10 18:56:57', '2019-11-10 18:56:57', 0),
(56, 27, '40', 1, '2019-11-10 18:59:01', '2019-11-10 18:59:01', 0),
(57, 28, 'Dừng ngay phương tiện, giữ nguyên hiện trường, cấp cứu người bị nạn và phải có mặt khi cơ quan có thẩm quyền yêu cầu, cung cấp thông tin xác thực về vụ tai nạn cho cơ quan có thẩm quyền.', 1, '2019-11-10 19:04:56', '2019-11-10 19:04:56', 0),
(58, 28, 'Ở lại nơi xảy ra tai nạn cho đến khi người của cơ quan công an đến, trừ trường hợp người điều khiển phương tiện cũng bị thương phải đưa đi cấp cứu hoặc phải đưa người bị nạn đi cấp cứu hoặc vì lý do đe dọa đến tính mạng nhưng phải đến trình báo ngay với cơ quan công an gần nhất.', 1, '2019-11-10 19:04:56', '2019-11-10 19:04:56', 0),
(59, 28, 'Rời khỏi hiện trường nơi xảy ra tai nạn giao thông.', 0, '2019-11-10 19:04:56', '2019-11-10 19:04:56', 0),
(60, 29, 'Điều khiển xe đi trên phần đường, làn đường có ít phương tiện tham gia giao thông, đội mũ bảo hiểm ở những nơi có biển báo bắt buộc đội mũ bảo hiểm.', 0, '2019-11-12 07:30:41', '2019-11-12 07:30:41', 0),
(61, 29, 'Chấp hành quy định về tốc độ, đèn tín hiệu, biển báo hiệu, vạch kẻ đường khi lái xe.', 1, '2019-11-12 07:30:41', '2019-11-12 07:30:41', 0),
(62, 29, 'Chấp hành hiệu lệnh, chỉ dẫn của người điều khiển giao thông, nhường đường cho người đi bộ, người già, trẻ em, người khuyết tật.', 1, '2019-11-12 07:30:41', '2019-11-12 07:30:41', 0),
(63, 29, 'Cả 3 ý nêu trên.', 0, '2019-11-12 07:30:41', '2019-11-12 07:30:41', 0),
(64, 30, 'Ô tô chở người đến 30 chỗ ngồi ( trừ ô tô buýt), ô tô tải có tải trọng dưới 3500 kg.', 0, '2019-11-12 07:34:20', '2019-11-12 07:34:20', 0),
(65, 30, 'Ô tô chở người trên 30 chỗ ngồi ( trừ ô tô buýt), ô tô tải có trọng tải dưới 3500 kg.', 0, '2019-11-12 07:34:20', '2019-11-12 07:34:20', 0),
(66, 30, 'Ô tô kéo rơ moóc, ô tô kéo xe khác, xe gắn máy.', 1, '2019-11-12 07:34:20', '2019-11-12 07:34:20', 0),
(67, 30, 'Ô tô buýt, ô tô sơ mi rơ moóc, ô tô chuyên dùng, xe mô tô', 0, '2019-11-12 07:34:20', '2019-11-12 07:34:20', 0),
(68, 31, '5', 1, '2019-11-12 07:36:44', '2019-11-12 07:36:44', 0),
(69, 32, 'Cơ quan quản lý giao thông vận tải.', 0, '2019-11-12 07:40:40', '2019-11-12 07:40:40', 0),
(70, 32, 'Uỷ ban nhân dân cấp tỉnh.', 1, '2019-11-12 07:40:40', '2019-11-12 07:40:40', 0),
(71, 32, 'Cơ quan cảnh sát giao thông đường bộ.', 0, '2019-11-12 07:40:40', '2019-11-12 07:40:40', 0),
(72, 32, 'Thanh tra giao thông đường bộ.', 0, '2019-11-12 07:40:40', '2019-11-12 07:40:40', 0),
(73, 33, '5', 1, '2019-11-12 07:42:59', '2019-11-12 07:42:59', 0),
(74, 34, 'Xe thô sơ phải đi làn đường bên trái trong cùng; xe cơ giới, xe máy chuyên dùng phải đi trên làn đường bên phải.', 0, '2019-11-12 07:47:51', '2019-11-12 07:47:51', 0),
(75, 34, 'Xe thô sơ phải đi trên làn đường bên phải trong cùng, xe cơ giới, xe máy chuyên dùng phải đi trên làn đường bên trái.', 1, '2019-11-12 07:47:51', '2019-11-12 07:47:51', 0),
(76, 34, 'Xe thô sơ phải đi trên làn đường phù hợp không gây cản trở giao thông, xe cơ giới, xe máy chuyên dùng đi trên làn đường bên phải.', 0, '2019-11-12 07:47:51', '2019-11-12 07:47:51', 0),
(77, 35, 'Trên đường có nhiều làn đường cho xe đi cùng chiều được phân biêt bằng vạch kẻ phân làn đường, người điều khiển phương tiện phải cho xe đi trong một làn đường và chỉ được chuyển làn đường ở những nơi cho phép; khi chuyển làn đường phải có tín hiệu báo trước và đảm bảo an toàn.', 1, '2019-11-12 07:50:56', '2019-11-12 07:50:56', 0),
(78, 35, 'Phương tiện tham gia giao thông đường bộ di chuyển tốc độ thấp hơn đi về bên trái.', 0, '2019-11-12 07:50:56', '2019-11-12 07:50:56', 0),
(79, 35, 'Trên đường một chiều có vạch kẻ phân làn đường, xe thô sơ phải đi trên làn đường bên phải trong cùng xe cơ giới, xe máy chuyên dùng đi trên làn đường bên trái, phương tiện tham gia giao thông đường bộ di chuyển tốc độ thấp hơn đi về bên phải.', 1, '2019-11-12 07:50:56', '2019-11-12 07:50:56', 0),
(80, 36, 'Hiệu lệnh của người điều khiển giao thông.', 1, '2019-11-12 07:57:50', '2019-11-12 07:57:50', 0),
(81, 36, 'Hiệu lệnh của đèn điều khiển giao thông.', 0, '2019-11-12 07:57:50', '2019-11-12 07:57:50', 0),
(82, 36, 'Hiệu lệnh của biển báo hiệu đường bộ.', 0, '2019-11-12 07:57:50', '2019-11-12 07:57:50', 0),
(83, 37, 'Đúng.', 1, '2019-11-12 08:00:31', '2019-11-12 08:00:31', 0),
(84, 37, 'Sai.', 0, '2019-11-12 08:00:31', '2019-11-12 08:00:31', 0),
(85, 38, 'Khi xe phía trước có tín hiệu rẽ trái hoặc đang rẽ trái; khi xe điện đang chạy giữa đường.', 1, '2019-11-12 08:05:02', '2019-11-12 08:05:02', 0),
(86, 38, 'Khi xe chuyên dùng đang làm việc trên đường mà không thể vượt bên trái được.', 1, '2019-11-12 08:05:02', '2019-11-12 08:05:02', 0),
(87, 38, 'Khi có đủ khoảng trống để vượt xe.', 0, '2019-11-12 08:05:02', '2019-11-12 08:05:02', 0),
(88, 39, 'Người điều khiển phương tiện phải tăng tốc độ và có tín hiệu báo hướng để rẽ.', 0, '2019-11-12 08:07:07', '2019-11-12 08:07:07', 0),
(89, 39, 'Người điều khiển phương tiện phải giảm tốc độ và có tín hiệu báo hướng rẽ, chỉ cho rẽ chuyển hướng khi quan sát thấy không gây trở ngại hoặc nguy hiểm cho người và phương tiện khác.', 1, '2019-11-12 08:07:07', '2019-11-12 08:07:07', 0),
(90, 39, 'Trong khi chuyển hướng, người lái xe, người điều khiển xe máy chuyên dùng phải nhường quyền đi trước cho người đi bộ, người đi xe đạp đang đi trên phần đường dành riêng cho họ, nhường đường cho các xe đi ngược chiều.', 1, '2019-11-12 08:07:07', '2019-11-12 08:07:07', 0),
(91, 40, 'Phương tiện nào bên phải không vướng.', 0, '2019-11-12 08:11:34', '2019-11-12 08:11:34', 0),
(92, 40, 'Phương tiện nào ra tín hiệu xin đường trước.', 0, '2019-11-12 08:11:34', '2019-11-12 08:11:34', 0),
(93, 40, 'Phương tiện giao thông đường sắt.', 1, '2019-11-12 08:11:34', '2019-11-12 08:11:34', 0),
(94, 41, 'Người tham gia giao thông ở phía trước và ở phía sau người điều khiển giao thông phải dừng lại; người tham gia giao thông ở phía bên phải và bên trái người điều khiển được đi.', 1, '2019-11-12 08:27:37', '2019-11-12 08:27:37', 0),
(95, 41, 'Người tham gia giao thông ở phía trước và ở phía sau người điều khiển giao thông được đi thẳng; người tham gia giao thông ở phía bên phải và bên trái người điều khiển giao thông được đi thẳng và rẽ phải.', 0, '2019-11-12 08:27:37', '2019-11-12 08:27:37', 0),
(96, 42, 'Người tham gia giao thông ở phía trước và ở phía sau người điều khiển giao thông phải dừng lại, người tham gia giao thông ở phía bên phải và bên trái người điều khiển được đi.', 0, '2019-11-12 08:29:29', '2019-11-12 08:29:29', 0),
(97, 42, 'Người tham gia giao thông ở các hướng phải dừng lại.', 1, '2019-11-12 08:29:29', '2019-11-12 08:29:29', 0),
(98, 42, 'Tất cả các trường hợp trên.', 0, '2019-11-12 08:29:29', '2019-11-12 08:29:29', 0),
(99, 43, 'Buông cả hai tay hoặc đi xe bằng một bánh đối với xe hai bánh, bằng hai bánh đối với xe ba bánh, chạy quá tốc độ quy định.', 1, '2019-11-12 08:33:23', '2019-11-12 08:33:23', 0),
(100, 43, 'Sử dụng xe để kéo, đẩy xe khác, vật khác và chở vật cồng kềnh, để chân chống quẹt xuống đất và cá hành vi khác gây mất trật tự an toàn giao thông.', 1, '2019-11-12 08:33:23', '2019-11-12 08:33:23', 0),
(101, 43, 'Chạy đúng tốc độ quy định và chấp hành đúng quy tắc giao thông đường bộ.', 0, '2019-11-12 08:33:23', '2019-11-12 08:33:23', 0),
(102, 44, 'Là trách nhiệm của ngành giao thông vận tải và ngành công an.', 0, '2019-11-12 08:34:39', '2019-11-12 08:34:39', 0),
(103, 44, 'Là trách nhiệm của cơ quan, tổ chức, cá nhân.', 1, '2019-11-12 08:34:39', '2019-11-12 08:34:39', 0),
(104, 44, 'Là trách nhiệm của cảnh sát giao thông.', 0, '2019-11-12 08:34:39', '2019-11-12 08:34:39', 0),
(105, 45, 'Biển 1', 0, '2019-11-17 08:40:24', '2019-11-17 08:40:24', 0),
(106, 45, 'Biển 1 và 3', 0, '2019-11-17 08:40:24', '2019-11-17 08:40:24', 0),
(107, 45, 'Biển 2', 1, '2019-11-17 08:40:24', '2019-11-17 08:40:24', 0),
(108, 45, 'Biển 2 và 3', 0, '2019-11-17 08:40:24', '2019-11-17 08:40:24', 0),
(109, 46, 'Biển 1', 1, '2019-11-17 08:41:57', '2019-11-17 08:41:57', 0),
(110, 46, 'Biển 3', 0, '2019-11-17 08:41:57', '2019-11-17 08:41:57', 0),
(111, 46, 'Biển 2', 0, '2019-11-17 08:41:57', '2019-11-17 08:41:57', 0),
(112, 47, 'Biển 1', 0, '2019-11-17 08:49:05', '2019-11-17 08:49:05', 0),
(113, 47, 'Biển 1 và 3', 0, '2019-11-17 08:49:05', '2019-11-17 08:49:05', 0),
(114, 47, 'Biển 3', 1, '2019-11-17 08:49:05', '2019-11-17 08:49:05', 0),
(115, 47, 'Cả 3 biển', 0, '2019-11-17 08:49:05', '2019-11-17 08:49:05', 0),
(116, 48, 'Biển 1', 1, '2019-11-17 08:50:39', '2019-11-17 08:50:39', 0),
(117, 48, 'Biển 2', 0, '2019-11-17 08:50:39', '2019-11-17 08:50:39', 0),
(118, 48, 'Biển 1 và 3', 0, '2019-11-17 08:50:39', '2019-11-17 08:50:39', 0),
(119, 48, 'Cả 3 biển', 1, '2019-11-17 08:50:39', '2019-11-17 08:50:39', 0),
(120, 49, 'Cả 3 biển', 0, '2019-11-17 08:51:56', '2019-11-17 08:51:56', 0),
(121, 49, 'Biển 2 và 3', 0, '2019-11-17 08:51:56', '2019-11-17 08:51:56', 0),
(122, 49, 'Biển 1 và 3', 0, '2019-11-17 08:51:56', '2019-11-17 08:51:56', 0),
(123, 49, 'Biển 1 và 2', 1, '2019-11-17 08:51:56', '2019-11-17 08:51:56', 0),
(124, 50, 'Biển 2 và 3', 1, '2019-11-17 08:53:13', '2019-11-17 08:53:13', 0),
(125, 50, 'Biển 1 và 3', 0, '2019-11-17 08:53:13', '2019-11-17 08:53:13', 0),
(126, 50, 'Biển 1', 0, '2019-11-17 08:53:13', '2019-11-17 08:53:13', 0),
(127, 50, 'Cả 3 biển', 0, '2019-11-17 08:53:13', '2019-11-17 08:53:13', 0),
(128, 51, 'Biển 1 và 2', 0, '2019-11-17 08:56:02', '2019-11-17 08:56:02', 0),
(129, 51, 'Biển 2 và 3', 1, '2019-11-17 08:56:02', '2019-11-17 08:56:02', 0),
(130, 51, 'Biển 1', 0, '2019-11-17 08:56:02', '2019-11-17 08:56:02', 0),
(131, 51, 'Cả 3 biển', 0, '2019-11-17 08:56:02', '2019-11-17 08:56:02', 0),
(132, 52, 'Biển 1', 1, '2019-11-17 09:00:16', '2019-11-17 09:00:16', 0),
(133, 52, 'Biển 2 và 3', 0, '2019-11-17 09:00:16', '2019-11-17 09:00:16', 0),
(134, 52, 'Biển 3', 0, '2019-11-17 09:00:16', '2019-11-17 09:00:16', 0),
(135, 53, 'Biển 2', 0, '2019-11-17 09:01:43', '2019-11-17 09:01:43', 0),
(136, 53, 'Biển 1', 0, '2019-11-17 09:01:43', '2019-11-17 09:01:43', 0),
(137, 53, 'Biển 3', 1, '2019-11-17 09:01:43', '2019-11-17 09:01:43', 0),
(138, 53, 'Cả 3 biển', 0, '2019-11-17 09:01:43', '2019-11-17 09:01:43', 0),
(139, 54, 'Biển 1 và 3', 1, '2019-11-17 09:03:29', '2019-11-17 09:03:29', 0),
(140, 54, 'Biển 1 và 2', 0, '2019-11-17 09:03:29', '2019-11-17 09:03:29', 0),
(141, 54, 'Biển 2 và 3', 0, '2019-11-17 09:03:29', '2019-11-17 09:03:29', 0),
(142, 54, 'Cả 3 biển', 0, '2019-11-17 09:03:29', '2019-11-17 09:03:29', 0),
(143, 55, 'Biển 1 và 2', 0, '2019-11-17 09:05:04', '2019-11-17 09:05:04', 0),
(144, 55, 'Biển 2 và 3', 1, '2019-11-17 09:05:04', '2019-11-17 09:05:04', 0),
(145, 55, 'Biển 1 và 3', 0, '2019-11-17 09:05:04', '2019-11-17 09:05:04', 0),
(146, 55, 'Cả 3 biển', 0, '2019-11-17 09:05:04', '2019-11-17 09:05:04', 0),
(147, 56, 'Biển 1', 0, '2019-11-17 09:05:53', '2019-11-17 09:05:53', 0),
(148, 56, 'Biển 2', 1, '2019-11-17 09:05:53', '2019-11-17 09:05:53', 0),
(149, 56, 'Biển 3', 0, '2019-11-17 09:05:53', '2019-11-17 09:05:53', 0),
(150, 57, 'Xe tải, xe lam, xe con, mô tô', 0, '2019-11-17 09:17:45', '2019-11-17 09:17:45', 0),
(151, 57, 'xe lam, xe tải, xe con, mô tô', 0, '2019-11-17 09:17:45', '2019-11-17 09:17:45', 0),
(152, 57, 'xe tải, mô tô, xe lam, xe con', 1, '2019-11-17 09:17:45', '2019-11-17 09:17:45', 0),
(153, 58, 'xe công an, xe con, xe tải, xe lam', 1, '2019-11-17 09:19:28', '2019-11-17 09:19:28', 0),
(154, 58, 'Xe lam, xe tải, xe con, xe công an', 0, '2019-11-17 09:19:28', '2019-11-17 09:19:28', 0),
(155, 58, 'Xe con, xe công an, xe lam, xe tải', 0, '2019-11-17 09:19:28', '2019-11-17 09:19:28', 0),
(156, 59, 'Xe tải, xe con, xe công an, xe khách', 0, '2019-11-17 09:22:06', '2019-11-17 09:22:06', 0),
(157, 59, 'Xe tải, xe con, xe khách, xe công an', 0, '2019-11-17 09:22:06', '2019-11-17 09:22:06', 0),
(158, 59, 'Xe công an, xe con, xe khách, xe tải', 0, '2019-11-17 09:22:06', '2019-11-17 09:22:06', 0),
(159, 59, 'Xe công an, xe tải, xe khách, xe con', 1, '2019-11-17 09:22:06', '2019-11-17 09:22:06', 0),
(160, 60, 'Mô tô, xe lam, xe tải', 1, '2019-11-17 09:23:46', '2019-11-17 09:23:46', 0),
(161, 60, 'Xe tải, xe lam, mô tô', 0, '2019-11-17 09:23:46', '2019-11-17 09:23:46', 0),
(162, 60, 'Xe lam, mô tô, xe tải', 0, '2019-11-17 09:23:46', '2019-11-17 09:23:46', 0),
(163, 61, 'Xe tải', 0, '2019-11-17 09:24:18', '2019-11-17 09:24:18', 0),
(164, 61, 'Xe con', 1, '2019-11-17 09:24:18', '2019-11-17 09:24:18', 0),
(165, 62, '1 màu', 0, '2019-11-26 06:15:43', '2019-11-26 06:15:43', 0),
(166, 62, '2 màu', 0, '2019-11-26 06:15:43', '2019-11-26 06:15:43', 0),
(167, 62, '3 màu', 1, '2019-11-26 06:15:43', '2019-11-26 06:15:43', 0),
(168, 63, '18', 1, '2019-11-26 06:20:59', '2019-11-26 06:20:59', 0),
(169, 64, 'Đúng', 0, '2019-11-26 06:22:01', '2019-11-26 06:22:01', 0),
(170, 64, 'Sai', 1, '2019-11-26 06:22:01', '2019-11-26 06:22:01', 0),
(171, 65, 'Ahihi', 0, '2019-11-26 06:23:27', '2019-11-26 06:23:27', 0),
(172, 65, 'CBC', 1, '2019-11-26 06:23:27', '2019-11-26 06:23:27', 0),
(173, 65, 'ád', 1, '2019-11-26 06:23:27', '2019-11-26 06:23:27', 0),
(174, 66, 'Đáp án 1', 1, '2019-11-26 06:38:26', '2019-11-26 06:38:26', 0),
(175, 66, 'Đáp án 2', 1, '2019-11-26 06:38:26', '2019-11-26 06:38:26', 0),
(176, 66, 'Đáp án 3', 0, '2019-11-26 06:38:26', '2019-11-26 06:38:26', 0),
(177, 66, 'Đáp án 4', 0, '2019-11-26 06:38:26', '2019-11-26 06:38:26', 0),
(178, 67, 'A', 1, '2019-11-26 10:17:21', '2019-11-26 10:17:21', 0),
(179, 67, 'B', 0, '2019-11-26 10:17:21', '2019-11-26 10:17:21', 0),
(180, 67, 'C', 0, '2019-11-26 10:17:21', '2019-11-26 10:17:21', 0),
(181, 67, 'D', 0, '2019-11-26 10:17:21', '2019-11-26 10:17:21', 0);

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `id` int(10) NOT NULL,
  `type_id` int(11) NOT NULL,
  `course_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'K000',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `type_id`, `course_name`, `created_time`, `updated_time`, `deleted`) VALUES
(3, 1, 'K-001', '2019-10-30 15:10:51', '2019-11-24 15:07:20', 0),
(4, 2, 'K-002', '2019-10-31 11:18:27', '2019-11-24 15:07:31', 0),
(5, 1, 'test', '2019-11-26 10:12:39', '2019-11-26 10:12:39', 0);

-- --------------------------------------------------------

--
-- Table structure for table `course_type`
--

CREATE TABLE `course_type` (
  `id` int(10) NOT NULL,
  `name` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `min_score` int(11) NOT NULL,
  `number_question` int(11) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `course_type`
--

INSERT INTO `course_type` (`id`, `name`, `created_time`, `updated_time`, `description`, `min_score`, `number_question`, `deleted`) VALUES
(1, 'B2', '2019-10-29 09:34:25', '2019-10-29 12:12:43', 'Bằng lái xe B2', 26, 30, 0),
(2, 'B1', '2019-10-29 12:06:03', '2019-10-29 12:06:03', 'Bắng lái xe B1', 26, 30, 0);

-- --------------------------------------------------------

--
-- Table structure for table `exam_schedule`
--

CREATE TABLE `exam_schedule` (
  `id` int(10) NOT NULL,
  `course_id` int(11) NOT NULL,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `note` text COLLATE utf8_unicode_ci,
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `exam_schedule`
--

INSERT INTO `exam_schedule` (`id`, `course_id`, `start_time`, `end_time`, `note`, `created_time`, `updated_time`, `deleted`) VALUES
(2, 3, '2019-11-01 07:00:00', '2019-11-01 07:20:00', '', '2019-10-30 16:07:14', '2019-11-26 06:16:32', 0),
(3, 4, '2019-11-23 21:06:00', '2019-11-23 20:34:00', '', '2019-11-24 09:10:31', '2019-11-24 09:10:31', 0),
(4, 5, '2019-11-26 10:15:00', '2019-11-26 10:45:00', '', '2019-11-26 10:13:53', '2019-11-26 10:13:53', 0);

-- --------------------------------------------------------

--
-- Table structure for table `log_history`
--

CREATE TABLE `log_history` (
  `id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `action` text COLLATE utf8_unicode_ci NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` int(10) NOT NULL,
  `question_type` int(5) NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `image_url` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `question_type`, `content`, `created_time`, `updated_time`, `image_url`, `deleted`) VALUES
(12, 2, 'Khái niệm \"Đường bộ\" được hiểu như thế nào là đúng?', '2019-11-10 17:15:49', '2019-11-10 17:15:49', NULL, 0),
(13, 2, '\"Vạch kẻ đường\" được hiểu thế nào là đúng?', '2019-11-10 17:23:30', '2019-11-10 17:23:30', NULL, 0),
(14, 2, 'Khái niệm \"Phần đường xe chạy\" được hiểu thế nào là đúng?', '2019-11-10 17:24:21', '2019-11-10 17:24:21', NULL, 0),
(15, 2, 'Khái niệm \"Làn đường\" được hiểu thế nào là đúng?', '2019-11-10 17:25:32', '2019-11-10 17:25:32', NULL, 0),
(16, 2, 'Khái niệm \"Đường phố\" được hiểu thế nào là đúng?', '2019-11-10 17:27:47', '2019-11-10 17:27:47', NULL, 0),
(17, 2, 'Khái niệm \"Dải phân cách\" được hiểu thế nào là đúng?', '2019-11-10 17:27:47', '2019-11-10 17:27:47', NULL, 0),
(18, 4, 'Dải phân cách trên đường bộ bao gồm 2 loại là loại cố định và loại di động?', '2019-11-10 17:53:25', '2019-11-10 17:53:25', NULL, 0),
(19, 2, 'Khái niệm \"Đường ưu tiên\" được hiểu như thế nào là đúng?', '2019-11-10 17:57:34', '2019-11-10 17:57:34', NULL, 0),
(20, 2, '\"Phương tiện giao thông đường bộ\" bao gồm những loại nào?', '2019-11-10 17:57:34', '2019-11-10 17:57:34', NULL, 0),
(21, 2, 'Những hành vi nào dưới đây bị nghiêm cấm?', '2019-11-10 18:13:02', '2019-11-10 18:13:02', NULL, 0),
(22, 3, 'Người điều khiển xe mô tô, xe gắn máy trên đường mà trong máu có nồng độ cồn vượt quá bao nhiêu thì bị cấm? (đơn vị miligam/100 mililit máu)', '2019-11-10 18:23:13', '2019-11-10 18:23:13', NULL, 0),
(23, 3, 'Người điều khiển xe mô tô, xe gắn máy trên đường mà trong khí thở có nồng độ cồn vượt quá bao nhiêu thì bị cấm? (đơn vị miligam/1 lít khí thở)', '2019-11-10 18:26:07', '2019-11-10 18:26:07', NULL, 0),
(24, 4, '\"Người tham gia giao thông đường bộ\" gồm: Người điều khiển, người sử dụng phương tiện tham gia giao thông đường bộ?', '2019-11-10 18:43:55', '2019-11-10 18:43:55', NULL, 0),
(25, 2, 'Khái niệm \"Người điều khiển giao thông\" được hiểu như thế nào là đúng?', '2019-11-10 18:46:45', '2019-11-10 18:46:45', NULL, 0),
(26, 3, 'Người đủ bao nhiêu tuổi trở lên thì được điều khiển xe mô tô 2 bánh, xe mô tô 3 bánh có dung tích xilanh từ 50 cm3 trở lên và các loại xe có kết cấu tương tự, xe ô tô tải, máy kéo có trọng tải dưới 3,5 tấn; xe ô tô chở người dưới 9 chỗ ngồi? (tuổi)', '2019-11-10 18:56:17', '2019-11-10 18:56:17', NULL, 0),
(27, 3, 'Trên đường bộ trong khu vực đông dân cư, xe mô tô hai bánh, xe gắn máy tham gia giao thông với tốc độ tối đa cho phép là bao nhiêu? (Km/h)', '2019-11-10 18:58:37', '2019-11-10 18:58:37', NULL, 0),
(28, 2, 'Người điều khiển phương tiện và những người liên quan trực tiếp đến vụ tai nạn có trách nhiệm gì?', '2019-11-10 19:01:11', '2019-11-10 19:01:11', NULL, 0),
(29, 2, 'Trong các hành vi dưới đây, người lái xe mô tô có văn hóa giao thông phải ứng xử như thế nào?', '2019-11-12 07:28:09', '2019-11-12 07:28:09', NULL, 0),
(30, 2, 'Trên đường bộ ngoài khu đông dân cư, loại xe nào tham gia giao thông với tốc độ tối đa cho phép là 50 km/h?', '2019-11-12 07:32:15', '2019-11-12 07:32:15', NULL, 0),
(31, 3, 'Khi phát hiện hành vi giả khai báo mất hoặc tẩy xóa, làm sai lệch các thông tin trên giấy phép lái xe; sử dụng giấy phép lái xe hoặc đổi hồ sơ giả; có hành vi cố tình gian dối để được đổi, cấp mới, cấp lại giấy phép lái xe, ngoài việc bị cơ quan quản lý giấy phép lái xe quyết định thu hồi giấy phép lái xe và hồ sơ gốc còn phải chịu trách nhiệm trước pháp luật, người lái xe không được cấp giấy phép lái xe trong thời gian bao nhiêu năm?', '2019-11-12 07:36:21', '2019-11-12 07:36:21', NULL, 0),
(32, 2, 'Cơ quan nào quy định các đoạn đường cấm đi, đường đi một chiều, nơi cấm dừng, cấm đỗ, cấm quay đầu xe, lắp đặt báo hiệu đường bộ thuộc địa phương quản lí?', '2019-11-12 07:38:25', '2019-11-12 07:38:25', NULL, 0),
(33, 3, 'Tại nơi đường bộ giao nhau cùng mức với đường sắt chỉ có đèn tín hiệu hoặc chuông báo hiệu, khi đèn tín hiệu màu đỏ đã bật sáng hoặc có tiếng chuông báo hiệu, người tham gia giao thông phải dừng lại ngay và giữ khoảng cách tối thiểu bao nhiêu mét tính từ ray gần nhất', '2019-11-12 07:42:26', '2019-11-12 07:42:26', NULL, 0),
(34, 2, 'Trên đường một chiều có vạch kẻ phân làn đường, xe thô sơ và xe cơ giới phải đi như thế nào là đúng quy tắc giao thông?', '2019-11-12 07:44:31', '2019-11-12 07:44:31', NULL, 0),
(35, 2, 'Trên đường có nhiều làn đường, người điều khiển phương tiện tham gia giao thông sử dụng làn đường như thế nào là đúng?', '2019-11-12 07:44:31', '2019-11-12 07:44:31', NULL, 0),
(36, 2, 'Trên đường giao thông, khi hiệu lệnh của người điều khiển giao thông trái với hiệu lệnh của đèn hoặc biển báo hiệu thì người tham gia giao thông phải chấp hành theo hiệu lệnh nào?', '2019-11-12 07:56:16', '2019-11-12 07:56:16', NULL, 0),
(37, 4, 'Tại nơi có biển báo hiệu cố định lại có báo hiệu tạm thời thì người tham gia giao thông phải chấp hành hiệu lệnh của báo hiệu tạm thời đúng hay sai?', '2019-11-12 07:59:45', '2019-11-12 07:59:45', NULL, 0),
(38, 2, 'Xe sau có thể vượt lên bên phải xe khác đang chạy phía trước trong trường hợp nào?', '2019-11-12 08:02:49', '2019-11-12 08:02:49', NULL, 0),
(39, 2, 'Khi muốn chuyển hướng, người lái xe phải thực hiện như thế nào?', '2019-11-12 08:02:49', '2019-11-12 08:02:49', NULL, 0),
(40, 2, 'Trên đoạn đường bộ giao nhau cùng mức với đường sắt, cầu đường bộ đi chung với đường sắt thì loại phương tiện nào được quyền ưu tiên đi trước?', '2019-11-12 08:09:19', '2019-11-12 08:09:19', NULL, 0),
(41, 2, 'Tại nơi đường giao nhau, khi người điều khiển giao thông ra hiệu lệnh bằng hai tay hoặc một tay giang ngang để báo hiệu thì người tham gia giao thông phải đi như thế nào là đúng quy tắc giao thông?', '2019-11-12 08:25:55', '2019-11-12 08:25:55', NULL, 0),
(42, 2, 'Tại nơi đường giao nhau, khi người điều khiển giao thông ra hiệu lệnh tay giơ thẳng đứng để báo hiệu thì người tham gia giao thông phải đi như thế nào?', '2019-11-12 08:25:55', '2019-11-12 08:25:55', NULL, 0),
(43, 2, 'Khi điều khiển xe mô tô hai bánh, mô tô ba bánh, xe gắn máy những hành vi nào không được phép?', '2019-11-12 08:31:47', '2019-11-12 08:31:47', NULL, 0),
(44, 2, 'Bảo đảm trật tự an toàn giao thông đường bộ là trách nhiệm của ai?', '2019-11-12 08:31:47', '2019-11-12 08:31:47', NULL, 0),
(45, 2, 'Biển nào cấm người đi bộ?', '2019-11-17 08:38:15', '2019-11-17 08:38:15', '256.jpg', 0),
(46, 2, 'Gặp biển nào người lái xe phải nhường đường cho người đi bộ?', '2019-11-17 08:38:15', '2019-11-17 08:38:15', '257.jpg', 0),
(47, 2, 'Biển nào chỉ đường dành cho người đi bộ, các loại xe không được đi vào khi gặp biển này?', '2019-11-17 08:46:48', '2019-11-17 08:46:48', '258.jpg', 0),
(48, 2, 'Biển nào cấm mọi loại xe cơ giới đi vào, trừ xe gắn máy mô tô 2 bánh và các loại xe ưu tiên theo luật định?', '2019-11-17 08:46:48', '2019-11-17 08:46:48', '259.jpg', 0),
(49, 2, 'Biển nào cấm ô tô tải? ', '2019-11-17 08:46:48', '2019-11-17 08:46:48', '260.jpg', 0),
(50, 2, 'Biển báo nào cấm máy kéo?', '2019-11-17 08:46:48', '2019-11-17 08:46:48', '261.jpg', 0),
(51, 2, 'Biển nào báo hiệu sắp đến chỗ giao nhau nguy hiểm?', '2019-11-17 08:46:48', '2019-11-17 08:46:48', '262.jpg', 0),
(52, 2, 'Biển nào báo hiệu sắp đến chỗ giao nhau với đường sắt có rào chắn?', '2019-11-17 08:59:05', '2019-11-17 08:59:05', '263.jpg', 0),
(53, 2, 'Biển nào báo hiệu giao nhau có tín hiệu đèn?', '2019-11-17 08:59:05', '2019-11-17 08:59:05', '264.jpg', 0),
(54, 2, 'Biển nào báo hiệu nguy hiểm giao nhau với đường sắt?', '2019-11-17 08:59:05', '2019-11-17 08:59:05', '265.jpg', 0),
(55, 2, 'Biển nào báo hiệu đường sắt giao nhau với đường bộ không có rào chắn?', '2019-11-17 08:59:05', '2019-11-17 08:59:05', '266.jpg', 0),
(56, 2, 'Biển nào báo hiệu cửa chui?', '2019-11-17 08:59:05', '2019-11-17 08:59:05', '267.jpg', 0),
(57, 4, 'Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?', '2019-11-17 09:15:16', '2019-11-17 09:15:16', '356.jpg', 0),
(58, 4, 'Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?', '2019-11-17 09:15:16', '2019-11-17 09:15:16', '357.jpg', 0),
(59, 4, 'Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?', '2019-11-17 09:15:16', '2019-11-17 09:15:16', '358.jpg', 0),
(60, 4, 'Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?', '2019-11-17 09:15:16', '2019-11-17 09:15:16', '359.jpg', 0),
(61, 4, 'Xe nào phải nhường đường trong trường hợp này?', '2019-11-17 09:15:16', '2019-11-17 09:15:16', '360.jpg', 0),
(62, 2, 'Cột đèn giao thông có mấy màu?', '2019-11-26 06:15:43', '2019-11-26 06:15:43', NULL, 0),
(63, 3, 'Độ tuổi được phép lái xe là bao nhiêu?', '2019-11-26 06:20:59', '2019-11-26 06:20:59', NULL, 1),
(64, 4, 'Người đi xe máy không cần đội mũ bảo hiểm. Như vậy là đúng hay sai?', '2019-11-26 06:22:01', '2019-11-26 06:22:01', NULL, 0),
(65, 2, 'Hình vẽ dưới đây là thế nào?', '2019-11-26 06:23:27', '2019-11-26 06:23:27', '1.jpg', 1),
(66, 2, 'Đáp án nào dưới đây là đúng?', '2019-11-26 06:38:26', '2019-11-26 06:38:26', '260.jpg', 1),
(67, 2, 'Câu hỏi', '2019-11-26 10:17:20', '2019-11-26 10:17:20', '265.jpg', 0);

-- --------------------------------------------------------

--
-- Table structure for table `question_type`
--

CREATE TABLE `question_type` (
  `id` int(5) NOT NULL,
  `code` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `question_type`
--

INSERT INTO `question_type` (`id`, `code`, `description`, `created_time`, `updated_time`, `deleted`) VALUES
(2, 'MC', 'Multiple Choice - Chọn nhiều đáp án', '2019-10-16 04:54:27', '2019-10-20 10:02:00', 0),
(3, 'SA', 'Short Answer', '2019-10-16 05:03:33', '2019-10-17 16:55:15', 0),
(4, 'TF', 'True or False', '2019-10-17 17:12:57', '2019-10-17 17:12:57', 0),
(5, 'DMQT', 'Demo question type', '2019-10-23 05:11:25', '2019-10-23 05:12:21', 1);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(10) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`, `created_time`, `updated_time`, `description`, `deleted`) VALUES
(1, 'ADMIN', '2019-09-19 17:00:00', '0000-00-00 00:00:00', 'Quản lý toàn bộ hệ thống', 0),
(2, 'MENTOR', '2019-09-20 09:12:07', '2019-09-20 09:12:07', 'Người quản lý học viên của từng khoá học', 0),
(4, 'STUDENT', '2019-09-20 09:41:42', '0000-00-00 00:00:00', 'Học viên tham gia vào hệ thống', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `role_id` int(10) NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `full_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `picture` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `role_id`, `username`, `password`, `full_name`, `email`, `phone`, `birthday`, `picture`, `created_time`, `updated_time`, `deleted`) VALUES
(3, 1, 'admin@demo.com', '$2a$10$StOdejmj7ifcKygSkuYJ2eUAVrad..pYp0oq5hoQ2UvtMLm3Gndsa', 'Admin', 'admin@demo.com', '212324215215', '1999-12-06', NULL, '2019-11-26 09:39:05', '2019-11-26 09:39:05', 0),
(7, 4, 'hoc@gmail.com', '$2a$10$OInZCg/LqfmLx0xpW4jG8.uxbwcIQskETOooieVT5MR.VWjRCm1bq', 'Vũ Văn Học', 'hoc@gmail.com', '343847478', NULL, NULL, '2019-11-24 09:14:47', '2019-11-24 09:14:47', 0),
(8, 4, 'huy@gmail.com', '$2a$10$wOWZeU7T5jDv5ECpdd0T9.0QkgFZqjUHMZZOW4p8nn5g45.huxaxq', 'Ngô Đức Huy', 'huy@gmail.com', '0123123123', NULL, NULL, '2019-11-24 15:04:45', '2019-11-24 15:04:45', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question_id` (`question_id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD KEY `type_id` (`type_id`);

--
-- Indexes for table `course_type`
--
ALTER TABLE `course_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `exam_schedule`
--
ALTER TABLE `exam_schedule`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `log_history`
--
ALTER TABLE `log_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question_type` (`question_type`);

--
-- Indexes for table `question_type`
--
ALTER TABLE `question_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=182;

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `course_type`
--
ALTER TABLE `course_type`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `exam_schedule`
--
ALTER TABLE `exam_schedule`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `log_history`
--
ALTER TABLE `log_history`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT for table `question_type`
--
ALTER TABLE `question_type`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `fk_answer_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `course_type` (`id`);

--
-- Constraints for table `log_history`
--
ALTER TABLE `log_history`
  ADD CONSTRAINT `log_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `fk_question_question_type` FOREIGN KEY (`question_type`) REFERENCES `question_type` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
