-- --------------------------------------------------------
-- Host:                         103.180.133.7
-- Server version:               8.0.32 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for hrm
CREATE DATABASE IF NOT EXISTS `hrm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hrm`;

-- Dumping structure for view hrm.all_position_name
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `all_position_name` (
	`employee_id` INT(10) NOT NULL,
	`position_name` TEXT NULL COLLATE 'utf8mb4_unicode_ci'
) ENGINE=MyISAM;

-- Dumping structure for table hrm.department
CREATE TABLE IF NOT EXISTS `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` date NOT NULL,
  `flag` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table hrm.department: ~10 rows (approximately)
INSERT IGNORE INTO `department` (`id`, `department_name`, `description`, `created_at`, `flag`) VALUES
	(1, 'Human Resoures', 'Responsible for recruitment, hiring, employee relations, benefits, and compliance with employment laws.', '2023-02-23', 0),
	(2, 'Finance and Accounting', 'Responsible for financial planning and analysis, accounting, financial reporting, budgeting, and forecasting.', '2023-02-23', 0),
	(3, 'Sales and Marketing', 'Responsible for promoting and selling the company\'s products or services, creating and implementing marketing campaigns, and building relationships with customers.', '2023-02-23', 0),
	(4, 'Operations', 'Responsible for managing the day-to-day activities of the company, including production, inventory, logistics, and supply chain management.', '2023-02-23', 0),
	(5, 'Information Technology', 'Responsible for managing the company\'s technology infrastructure, including hardware, software, and networks.', '2023-02-23', 0),
	(6, 'Customer Service', 'Responsible for handling customer inquiries, complaints, and support issues.', '2023-02-23', 0),
	(7, 'Research and Development', 'Responsible for developing new products or services, improving existing ones, and conducting market research.', '2023-02-23', 0),
	(8, 'Legal', 'Responsible for managing the company\'s legal affairs, including contract negotiation, intellectual property protection, and compliance with laws and regulations.', '2023-02-23', 0),
	(9, 'Administration', 'Responsible for managing the administrative functions of the company, including facilities management, office support, and records management.', '2023-02-23', 0),
	(10, 'Quality Assurance', 'Responsible for ensuring that the company\'s products or services meet quality standards and customer expectations.', '2023-02-23', 0);

-- Dumping structure for table hrm.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `department_id` int NOT NULL,
  `role_id` int NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `on_leave` int DEFAULT NULL,
  `last_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `middle_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `telephone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `avatar` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `dob` date DEFAULT NULL,
  `status` int DEFAULT '0',
  `hire_date` date DEFAULT NULL,
  `termination_date` date DEFAULT NULL,
  `flag` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_employee_department` (`department_id`),
  KEY `FK_employee_role` (`role_id`),
  CONSTRAINT `FK_employee_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_employee_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table hrm.employee: ~85 rows (approximately)
INSERT IGNORE INTO `employee` (`id`, `department_id`, `role_id`, `username`, `password`, `on_leave`, `last_name`, `middle_name`, `first_name`, `gender`, `telephone`, `email`, `address`, `avatar`, `description`, `dob`, `status`, `hire_date`, `termination_date`, `flag`) VALUES
	(2, 5, 1, 'admin', 'admin123', 0, 'Nguyen', 'Chinh', 'Nghia', 'Male', '0123456788', 'nghia@gmail.com', 'Bac Ninh', './com/hrm/assets/avatar/4c0562c3404963f90a16730397b5f950.jpg', NULL, '2003-11-28', 0, '2003-03-24', '2023-03-29', 0),
	(3, 3, 2, 'manager', 'manager123', 0, 'Nguyen', 'Anh', 'Tuan', 'Male', '0989365472', 'Tuan@gmail.com', 'Ha Noi', NULL, NULL, '2001-08-18', 0, '2021-12-01', '2023-03-28', 0),
	(4, 4, 3, 'staff', 'staff123', 0, 'Pham', 'Van', 'Hung', 'Male', '0365475366', 'hung@gmail.com', 'Hai Phong', './com/hrm/assets/avatar/1c0922f0f82b37a735e4d4e7e8611d24.jpg', NULL, '1998-07-18', 0, '2022-02-24', '2023-03-28', 0),
	(5, 6, 3, 'ntLam', 'ntLam123', 0, 'Nguyen', 'Tung', 'Lam', 'Male', '0124584446', 'lam@gmail.com', 'Sai gon', NULL, NULL, '1996-02-01', 0, '2023-01-24', '2023-03-28', 0),
	(6, 1, 2, 'vThuan', 'vThuan123', 0, 'Nguyen', 'Van', 'Thuan', 'Male', '0124544436', 'thuan@gmail.com', 'Ha Noi', NULL, NULL, '1998-02-01', 0, '2023-01-24', '2023-03-28', 0),
	(7, 3, 2, 'manager1', 'manager123', 0, 'Do', 'Tuan', 'Phong', 'Male', '0255466685', 'phong@gmail.com', 'Dong Anh', NULL, NULL, '2003-03-24', 0, '2023-01-24', '2023-03-28', 0),
	(8, 2, 3, 'staff1', 'staff123', 0, 'Nguyen ', 'Ngoc', 'Cuyen', 'Male', '0215449963', 'cuyen@gmai.com', 'Yen Vien', NULL, NULL, '2003-08-12', 1, '2023-01-24', '2023-04-28', 0),
	(9, 3, 3, 'staff2', 'staff123', 0, 'Nguyen', 'Anh', 'Tu', 'Male', '0214799658', 'tu@gmail.com', 'Ha Tinh', NULL, NULL, '2001-12-04', 0, '2023-02-24', '2023-03-28', 0),
	(10, 4, 3, 'staff3', 'staff123', 0, 'Cao', 'Viet', 'Dung', 'Male', '0321654998', 'dung@gmail.com', 'Hue', NULL, NULL, '2000-01-14', 0, '2023-02-24', '2023-03-28', 0),
	(11, 2, 3, 'staff4', 'staf123', 0, 'Bui', 'Dinh', 'Thanh', 'Male', '0916565465', 'thanh@gmai.com', 'Ha Noi', NULL, NULL, '1999-03-28', 1, '2023-02-28', '2023-05-28', 0),
	(12, 3, 3, 'tXuan', 'xuan123', 0, 'Dao', 'Thi', 'Xuan', 'Female', '0321656865', 'xuan@gmail.com', 'Ha Noi', NULL, NULL, '2002-03-28', 0, '2023-02-28', '2023-03-28', 0),
	(13, 3, 3, 'tLong', 'long123', 0, 'Dinh', 'Tien', 'Long', 'Female', '0221654789', 'long@gmail.com', 'Ha Noi', NULL, NULL, '2001-03-28', 1, '2023-04-28', '2023-05-28', 0),
	(14, 2, 3, 'kLien', 'lien123', 0, 'Do', 'Thi Kim', 'Lien', 'Female', '0365475556', 'lien@gmail.com', 'Ha Noi', NULL, NULL, '2000-03-28', 1, '2023-04-28', '2023-03-28', 0),
	(15, 7, 3, 'tDat', 'dat123', 0, 'Ha', 'Tien', 'Dat', 'Female', '0254756455', 'dat@gmail.com', 'Ha Noi', NULL, NULL, '2002-03-28', 1, '2023-04-28', '2023-09-28', 0),
	(16, 8, 3, 'vKy', 'ky123', 0, 'Hoang', 'Vu', 'Ky', 'Female', '0313256532', 'ky@gmail.com', 'Ha Noi', NULL, NULL, '2002-03-28', 0, '2023-05-28', '2023-03-28', 0),
	(17, 2, 3, 'kHuyen', 'huyen123', 0, 'Nguyen', 'Khanh', 'Huyen', 'Male', '0213354688', 'huyen@gmail.com', 'Ha Noi', NULL, NULL, '2003-05-02', 0, '2023-05-28', '2023-03-28', 0),
	(18, 5, 3, 'vNghia', 'nghia123', 0, 'Nguyen', 'Van', 'Nghia', 'Male', '0313264658', 'vNghia@gmail.com', 'Ha Long', NULL, NULL, '2002-03-28', 0, '2023-05-28', '2023-03-28', 0),
	(19, 10, 3, 'vDang', 'dang123', 0, 'Nguyen', 'Van', 'Dang', 'Male', '0323265654', 'dang@gmail.com', 'Bac Ninh', NULL, NULL, '2000-07-28', 1, '2023-06-28', '2023-08-28', 0),
	(20, 1, 3, 'tAnh', 'tuananh123', 0, 'Pham', 'Tuan', 'Anh', 'Female', '0698658555', 'tuananh@gmail.com', 'Hai Duong', NULL, NULL, '1997-09-01', 1, '2023-03-28', '2023-03-29', 0),
	(21, 2, 3, 'dtThanh', 'thanh123', 0, 'dinh', 'thanh', 'thanh', 'Male ', '094664646', 'thanh@gmail.com', '', NULL, '', '2023-04-05', 0, '2023-04-09', NULL, 1),
	(22, 10, 3, 'nlinh', 'bLinh123456@', 0, 'Bui', 'Nhat', 'Linh', 'Male ', '091365659', 'nlinh@gmail.com', 'Ha Noi', './com/hrm/assets/avatar/idiocracia.jpg', '', '2003-04-10', 0, '2023-04-14', NULL, 0),
	(23, 10, 3, 'vbnam', 'vanNam1234@', 0, 'Bui', 'Van', 'Nam', 'Female', '09788989', 'vNam@gmail.com', 'Bac Kan', './com/hrm/assets/avatar/idiocracia.jpg', '', '2010-04-08', 1, '2023-04-14', '2023-04-14', 0),
	(24, 10, 3, 'tHung', 'hung123', 0, 'Do ', 'Tien', 'Hung', 'Female', '097546488', 'hung123@gmail.com', 'Hai Duong', NULL, NULL, '2002-02-14', 0, '2023-04-14', '2023-05-14', 0),
	(25, 10, 3, 'qTuan', 'tuan123', NULL, 'Dang', 'Quoc', 'Tuan', 'Male', '031649988', 'qTuan@gmail.com', 'Ha noi', NULL, NULL, '1993-04-14', 0, '2023-04-14', '2023-04-14', 0),
	(26, 10, 3, 'mAnh', 'mAnh123', 0, 'Do ', 'Minh', 'Anh', 'Female', '036599887', 'mAnh@gmail.com', 'Ha Noi', NULL, NULL, '2002-04-14', 0, '2023-04-14', NULL, 0),
	(27, 10, 3, 'mTrang', 'mTrang123', 0, 'Do', 'Mai', 'Trang', 'Female', '091292299', 'mTrang@gmail.com', 'Hai Duong', NULL, NULL, '2004-04-14', 0, '2023-04-14', NULL, 0),
	(28, 10, 3, 'tTien', 'tTien123', 0, 'Do', 'Thuy', 'Tien', 'Female', '091292922', 'tTien@gmail.com', 'Ha Noi', NULL, NULL, '1999-04-14', 0, '2022-04-14', NULL, 0),
	(29, 10, 3, 'kHuyen', 'kHuyen123', 0, 'Doan', 'Khanh', 'Huyen', 'Female', '092893992', 'kHuyen@gmail.com', 'Ha Noi', NULL, NULL, '2001-04-14', 0, '2023-04-14', NULL, 0),
	(30, 10, 3, 'dHiep', 'dHiep123', 0, 'Nguyen', 'Duc', 'Hiep ', 'Male', '029323223', 'dHiep@gmail.com', 'Ha Noi', NULL, NULL, '2003-04-14', 1, '2023-02-14', '2023-04-14', 0),
	(31, 1, 3, 'dNguyen', 'tdNguyen123', 0, 'Tran', 'Duc', 'Nguyen', 'Male', '0929292999', 'dNguyen@gmail.com', 'Ha Noi', NULL, NULL, '2001-03-14', 0, '2022-12-14', NULL, 0),
	(32, 1, 3, 'mKiet', 'mKiet123', 0, 'Hoang', 'Minh', 'Kiet', 'Male', '0292938399', 'mKiet@gmail.com', 'Ha Noi', NULL, NULL, '2002-04-14', 0, '2023-04-14', '2023-04-14', 0),
	(33, 1, 3, 'hPhuc', 'hPhuc123', 0, 'Nguyen', 'Hong', 'Phuc', 'Female', '0929923888', 'hPhuc@gmail.com', 'Ha Noi', NULL, NULL, '2001-04-14', 0, '2023-04-14', NULL, 0),
	(34, 1, 3, 'tHung', 'tHung', 0, 'Le', 'Trung', 'Hung', 'Male', '0928839499', 'tHung@gmail.com', 'Hai Duong', NULL, NULL, '1992-04-14', 0, '2023-04-14', NULL, 0),
	(35, 1, 3, 'hGiang', 'hGiang1231', 0, 'Nguyen', 'Huong', 'Giang', 'Female', '0928393839', 'hGiang@gmail.com', 'Hue', NULL, NULL, '1997-04-14', 0, '2023-04-14', NULL, 0),
	(36, 1, 3, 'kNgan', 'kimNgan123', 0, 'Nguyen', 'Kim', 'Ngan', 'Female', '0505696755', 'kNgan@gmail.com', 'Ha Noi', NULL, NULL, '2001-04-14', 0, '2023-04-14', NULL, 0),
	(37, 1, 3, 'tKien', 'tKien12322', 0, 'Nguyen', 'Trung', 'Kien', 'Male', '0809989737', 'tKien@gmail.com', 'Ha Noi', NULL, NULL, '2001-04-14', 0, '2023-04-14', NULL, 0),
	(38, 2, 3, 'hViet', 'hviet1231', 0, 'Le', 'Hoang', 'Viet', 'Male', '0987678989', 'hViet@gmail.com', 'Ha Noi', NULL, NULL, '2000-04-14', 0, '2023-04-14', NULL, 0),
	(39, 2, 3, 'vTan', 'vTan1231', 0, 'Hoang', 'Viet', 'Tan', 'Male', '0292938499', 'vTan@gmail.com', 'Ha Noi', NULL, NULL, '2008-04-14', 0, '2023-04-14', NULL, 0),
	(40, 2, 3, 'mTan', 'mTan12312', 0, 'Le ', 'Minh ', 'Tan', 'Female', '0998284755', 'mTan@gmail.com', 'Hai Duong', NULL, NULL, '2002-04-14', 0, '2023-04-14', NULL, 0),
	(41, 2, 3, 'nBich', 'nBich1231', 0, 'Le', 'Ngoc', 'Bich', 'Female', '0929388443', 'nBich@gmail.com', 'Hai Duong', NULL, NULL, '1999-04-14', 0, '2023-04-14', '2023-04-14', 0),
	(42, 3, 3, 'tPhong', 'tPhong1231', 0, 'Le', 'Thanh', 'Phong', 'Male', '0984859987', 'tPhong123@gmail.com', 'Ha Noi ', NULL, NULL, '1994-04-14', 0, '2023-04-14', NULL, 0),
	(43, 3, 3, 'tDuye', 'tDuye1231', 0, 'Le', 'Tung', 'Duy', 'Male', '0998878778', 'tDuy123@gmail.com', 'Ha Noi', NULL, NULL, '2000-04-14', 0, '2023-04-14', NULL, 0),
	(44, 3, 3, 'tAn', 'tAn1231', 0, 'Luong', 'Thi', 'An', 'Female', '0928281119', 'tAn1122@gmail.com', 'Ha Noi', NULL, NULL, '2003-04-14', 0, '2023-04-14', '2022-04-14', 0),
	(45, 3, 3, 'tHang', 'tHang123', 0, 'Luu', 'Thu', 'Hang', 'Female', '0894847473', 'tHang123@gmail.com', 'Bac Ninh', NULL, NULL, '2001-04-14', 0, '2023-04-14', '2023-04-14', 0),
	(46, 3, 3, 'tmQuan', 'mQuan123', 0, 'Tran', 'Minh', 'Quan', 'Female', '0989887787', 'mQuan123!@gmail.com', 'Bac Ninh', NULL, NULL, '2000-04-14', 0, '2023-02-14', NULL, 0),
	(47, 3, 3, 'qHuy', 'nHuy', 0, 'Nguyen ', 'Ngoc', 'Huy', 'Female', '0993847554', 'nHuy@gmail.com', 'Ha Noi', NULL, NULL, '2001-04-14', 0, '2023-01-14', NULL, 0),
	(48, 3, 3, 'cThanh', 'cThanh123', 0, 'Nguyen', 'Chi', 'Thanh', 'Male', '0928475666', 'cThanh@gmail.com', 'Ha Noi', NULL, NULL, '2001-11-14', 0, '2022-12-14', NULL, 0),
	(49, 4, 3, 'dLim', 'dLim1231', 0, 'Nguyen', 'Dang', 'Lim', 'Male', '0656577488', 'dLim@gmail.com', 'Ha Noi', NULL, NULL, '1995-03-14', 0, '2023-02-14', NULL, 0),
	(50, 4, 3, 'mDat', 'mDat1231', 0, 'Nguyen ', 'Minh', 'Dat', 'Male', '0992288333', 'mDat@gmail.com', 'Ha Noi', NULL, NULL, '1999-04-14', 0, '2023-02-14', NULL, 0),
	(51, 4, 3, 'dHieu', 'dHieu1231', 0, 'Nguyen', 'Duc', 'Hieu', 'Female', '0658846668', 'dHieu@gmail.com', 'Ha Noi', NULL, NULL, '1998-04-14', 0, '2023-02-14', NULL, 0),
	(52, 4, 3, 'tPhuc', 'tPhuc123', 0, 'Nguyen', 'Thien', 'Phuc', 'Male', '0999848498', 'tPhuc@gmail.com', 'Ha Noi', NULL, NULL, '2002-04-14', 0, '2023-02-14', NULL, 0),
	(53, 4, 3, 'bNgoc', 'bNgoc123', 0, 'Nguyen', 'Bao ', 'Ngoc', 'Female', '0998866434', 'bNgoc@gmail.com', 'Ha Noi', NULL, NULL, '2003-04-14', 0, '2023-01-14', NULL, 0),
	(54, 4, 3, 'hHa', 'hHa123', 0, 'Nguyen', 'Hong', 'Ha', 'Male', '0365984658', 'nhHa@gmail.com', 'Ha Noi', NULL, NULL, '2002-04-14', 0, '2023-02-14', NULL, 0),
	(55, 4, 3, 'hTruong', 'hTruong123', 0, 'Nguyen ', 'Huu', 'Truong', 'Male', '0656498946', 'hTruong@gmail.com', 'Ha Noi', NULL, NULL, '1988-04-15', 0, '2023-03-15', NULL, 0),
	(56, 4, 3, 'mQuang', 'mQuang123', 0, 'Tran', 'Minh', 'Quang', 'Female', '0354645313', 'mQuang@gmail.com', 'Ha Noi', NULL, NULL, '2000-04-15', 0, '2023-03-15', NULL, 0),
	(57, 5, 3, 'pQuy', 'pQuy123', 0, 'Nguyen', 'Phuc', 'Quy', 'Male', '0369866869', 'pQuy@gmail.com', 'Ha Noi', NULL, NULL, '2002-03-15', 0, '2023-03-15', NULL, 0),
	(58, 5, 3, 'qAn', 'qAn123', 0, 'Nguyen', 'Quang', 'An', 'Female', '0366868699', 'qAn@gmail.com', 'Ha Noi', NULL, NULL, '2001-04-15', 0, '2023-03-15', NULL, 0),
	(59, 5, 3, 'qCanh', 'qCanh123', 0, 'Nguyen ', 'Quang', 'Canh', 'Male', '0654989365', 'qCanh@gmai.com', 'Ha Noi', NULL, NULL, '1997-04-15', 0, '2023-03-15', NULL, 0),
	(60, 5, 3, 'tLan', 'tLan123', 0, 'Nguyen ', 'Thi ', 'Lan', 'Female', '0336986869', 'tLan@gmail.com', 'Ha Noi', NULL, NULL, '2000-04-15', 0, '2023-02-15', NULL, 0),
	(61, 5, 3, 'pLy', 'pLy12312', 0, 'Nguyen', 'Phuong', 'Ly', 'Female', '0989843468', 'pLy@gmail.com', 'Ha Noi ', NULL, NULL, '2001-04-15', 0, '2023-02-15', NULL, 0),
	(62, 5, 3, 'tHoang', 'tHoang123', 0, 'Nguyen', 'Tuan', 'Hoang', 'Male', '0356598666', 'tHoang@gmail.com', 'Ha Noi', NULL, NULL, '2002-04-15', 0, '2023-03-15', NULL, 0),
	(63, 5, 3, 'tTam', 'tTam1231', 0, 'Ninh', 'Thanh', 'Tam', 'Female', '0366548964', 'tTam@gmai.com', 'Ha Noi', NULL, NULL, '2000-04-15', 0, '2023-03-15', NULL, 0),
	(64, 5, 3, 'qBinh', 'qBinh123', 0, 'Nong', 'Quang', 'Binh', 'Male', '0546846899', 'qBinh@gmail.com', 'Ha Noi', NULL, NULL, '2005-04-15', 0, '2023-02-15', NULL, 0),
	(65, 5, 3, 'tTung', 'tTung123', 0, 'Pham', 'The', 'Tung', 'Male', '0998865456', 'tTung@gmail.com', 'Ha Noi', NULL, NULL, '2002-04-15', 0, '2023-01-15', NULL, 0),
	(66, 6, 3, 'hMen', 'hMen123', 0, 'Pham', 'Hong', 'Men', 'Female', '0333556668', 'hMen@gmail.com', 'Ha Noi', NULL, NULL, '1997-04-15', 0, '2023-02-15', NULL, 0),
	(67, 6, 3, 'vBac', 'vBac1231', 0, 'Pham', 'Van', 'Bac', 'Male', '0999898999', 'vBac@gmail.com', 'Ha Noi', NULL, NULL, '1996-04-15', 0, '2023-03-15', NULL, 0),
	(68, 6, 3, 'qAnh', 'qAnh1231', 0, 'Phung', 'Quang', 'Anh', 'Male', '0333458669', 'qAnh@gmail.com', 'Ha Noi', NULL, NULL, '2000-04-15', 0, '2023-02-15', NULL, 0),
	(69, 6, 3, 'pThu', 'pThu1232', 0, 'Tran', 'Phuong', 'Thu', 'Female', '0365964648', 'pThu@gmail.com', 'Ha Noi', NULL, NULL, '1998-04-15', 0, '2023-01-15', NULL, 0),
	(70, 6, 3, 'dNam', 'dNam12323', 0, 'Tran', 'Duc ', 'Name', 'Male', '0222133666', 'dNam@gmail.com', 'Ha Noi', NULL, NULL, '2001-04-15', 0, '2023-01-15', NULL, 0),
	(71, 6, 3, 'tSon', 'tSon12323', 0, 'Tran', 'Truong', 'Son', 'Female', '0333547556', 'tSon@gmail.com', 'Ha Noi', NULL, NULL, '2004-04-15', 0, '2023-01-15', NULL, 0),
	(72, 6, 3, 'tUyen', 'tUyen1233', 0, 'Vu', 'Thu', 'Uyen', 'Female', '0334658665', 'tUyen@gmail.com', 'Ha Noi', NULL, NULL, '2001-04-15', 0, '2023-02-15', NULL, 0),
	(73, 7, 3, 'vAn', 'vAn1211', 0, 'Truong', 'Van', 'An', 'Male', '0887787252', 'vAn@gmail.com', 'Ha Noi', NULL, NULL, '2003-04-15', 0, '2023-01-15', NULL, 0),
	(74, 7, 3, 'mChau', 'mChau1231', 0, 'Nguyen', 'Minh', 'Chau', 'Male', '0233548866', 'mChau@gmail.com', 'Ha Noi', NULL, NULL, '2001-04-15', 0, '2023-02-15', NULL, 0),
	(75, 7, 3, 'aNgoc', 'aNgoc12314', 0, 'Nguyen', 'Anh', 'Ngoc', 'Female', '0366879986', 'aNgoc@gmail.com', 'Bac Ninh', NULL, NULL, '2002-04-15', 0, '2023-03-15', NULL, 0),
	(76, 7, 3, 'tNghia', 'tNghia534', 0, 'Lam', 'Tuan', 'Nghia', 'Male', '0225466869', 'tNghia@gmail.com', 'Bac Ninh', NULL, NULL, '2005-04-15', 0, '2023-02-15', '2023-04-15', 0),
	(77, 7, 3, 'pCuong', 'pCuong123', 0, 'Phan', 'Phuong', 'Cuong', 'Male', '0335543321', 'pCuong@gmail.com', 'Ha Noi', NULL, NULL, '2004-04-15', 0, '2023-05-15', NULL, 0),
	(78, 7, 3, 'qMui', 'qMui1231', 0, 'Vu', 'Quy', 'Mui', 'Female', '0332135468', 'qMui@gmail.com', 'Ha Noi', NULL, NULL, '1993-04-15', 0, '2023-05-15', NULL, 0),
	(79, 8, 3, 'xHieu', 'xHieu1231', 0, 'Vu', 'Xuan', 'Hieu', 'Male', '0333131554', 'xHieu@gmail.com', 'Ha Noi', NULL, NULL, '1996-04-15', 0, '2023-05-15', NULL, 0),
	(80, 8, 3, 'tViet', 'tViet1122', 0, 'Vu', 'Tuan', 'Viet', 'Female', '0334646846', 'tViet@gmail.com', 'Ha Noi', NULL, NULL, '1998-04-15', 0, '2023-01-15', NULL, 0),
	(81, 8, 3, 'vChau', 'vChau1123', 0, 'Nguen', 'Vu', 'Chau', 'Female', '0334455886', 'vChau@gmail.com', 'Ha Noi', NULL, NULL, '1996-04-15', 0, '2023-01-15', NULL, 0),
	(82, 8, 3, 'kNhat', 'kNhat123', 0, 'Nguyen', 'Khanh', 'Nhat', 'Male', '0331354588', 'kNhat@gmail.com', 'Ha Noi', NULL, NULL, '2003-04-15', 0, '2023-01-15', NULL, 0),
	(83, 8, 3, 'vDuyen', 'vDuyen123', 0, 'Vu ', 'van', 'Duyen', 'Female', '0313646846', 'vDuyen@gmail.com', 'Ha Noi', NULL, NULL, '1997-04-15', 0, '2023-01-15', NULL, 0),
	(84, 8, 3, 'kHoa', 'kHoa123', 0, 'Nguyen', 'Khanh', 'Hoa', 'Female', '0334658646', 'kHoa@gmail.com', 'Bac Ninh', NULL, NULL, '1990-04-15', 0, '2023-01-15', NULL, 0),
	(85, 9, 3, 'qThanh', 'qThanh123', 0, 'Nguyen', 'Quang', 'Thanh', 'Male', '0334343535', 'qThanh@gmail.com', 'Hai Duong', NULL, NULL, '2003-04-15', 0, '2023-05-15', NULL, 0),
	(86, 9, 3, 'cPhong', 'cPhuong1122', 0, 'Nguyen', 'Cong', 'Phuong', 'Male', '0998668466', 'cPhuong@gmail.com', 'Thai Binh', NULL, NULL, '2002-04-15', 0, '2023-06-15', NULL, 0);

-- Dumping structure for table hrm.module
CREATE TABLE IF NOT EXISTS `module` (
  `id` int NOT NULL AUTO_INCREMENT,
  `module_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `flag` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table hrm.module: ~8 rows (approximately)
INSERT IGNORE INTO `module` (`id`, `module_name`, `description`, `flag`) VALUES
	(1, 'employee', NULL, 0),
	(2, 'department', NULL, 0),
	(3, 'principal', NULL, 0),
	(4, 'salary', NULL, 0),
	(5, 'dashboard', NULL, 0),
	(6, 'setting', NULL, 0),
	(7, 'task', NULL, 0),
	(8, 'position', NULL, 0);

-- Dumping structure for table hrm.module_role
CREATE TABLE IF NOT EXISTS `module_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `module_id` int NOT NULL,
  `role_id` int NOT NULL,
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `flag` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_module` (`module_id`),
  KEY `FK_role` (`role_id`),
  CONSTRAINT `FK_module` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table hrm.module_role: ~16 rows (approximately)
INSERT IGNORE INTO `module_role` (`id`, `module_id`, `role_id`, `description`, `flag`) VALUES
	(1, 1, 1, NULL, 0),
	(2, 5, 3, NULL, 0),
	(3, 3, 1, NULL, 0),
	(4, 1, 2, NULL, 0),
	(5, 2, 1, NULL, 0),
	(6, 2, 2, NULL, 0),
	(7, 4, 1, NULL, 0),
	(8, 5, 1, NULL, 0),
	(9, 7, 2, NULL, 0),
	(10, 8, 2, NULL, 0),
	(11, 7, 3, NULL, 0),
	(12, 6, 1, NULL, 0),
	(13, 7, 1, NULL, 0),
	(14, 5, 2, NULL, 0),
	(15, 8, 1, NULL, 0),
	(16, 6, 2, '', 1);

-- Dumping structure for table hrm.position
CREATE TABLE IF NOT EXISTS `position` (
  `id` int NOT NULL AUTO_INCREMENT,
  `position_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `who_create` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `flag` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table hrm.position: ~43 rows (approximately)
INSERT IGNORE INTO `position` (`id`, `position_name`, `description`, `created_at`, `who_create`, `flag`) VALUES
	(1, 'HR Manager', NULL, '2023-02-23', NULL, 0),
	(2, 'Recruiter', NULL, '2023-02-23', NULL, 0),
	(3, 'Benefits Administrator', NULL, '2023-02-23', NULL, 0),
	(4, 'Employee Relations ', NULL, '2023-02-23', NULL, 0),
	(5, 'HR Generalist', NULL, '2023-02-23', NULL, 0),
	(6, 'Chief Finance Officer', NULL, '2023-02-23', NULL, 0),
	(7, 'Controller', NULL, '2023-02-23', NULL, 0),
	(8, 'Accountant', NULL, '2023-02-23', NULL, 0),
	(9, 'Bookkeeper', NULL, '2023-02-23', NULL, 0),
	(10, 'Sales Manager', NULL, '2023-02-23', NULL, 0),
	(11, 'Marketing Manager', NULL, '2023-02-23', NULL, 0),
	(12, 'Account Executive', NULL, '2023-02-23', NULL, 0),
	(13, 'Brand Manager', NULL, '2023-02-23', NULL, 0),
	(14, 'Social Media Specialist', NULL, '2023-02-23', NULL, 0),
	(15, 'Operations Manager', NULL, '2023-02-23', NULL, 0),
	(16, 'Production Supervisor', NULL, '2023-02-23', NULL, 0),
	(17, 'Logistics Coordinator', NULL, '2023-02-23', NULL, 0),
	(18, 'Purchasing Agent', NULL, '2023-02-23', NULL, 0),
	(19, 'Inventory Control Specialist', NULL, '2023-02-23', NULL, 0),
	(20, 'Chief Information Officer', NULL, '2023-02-23', NULL, 0),
	(21, ' IT Manager', NULL, '2023-02-23', NULL, 0),
	(22, 'Network Administrator', NULL, '2023-02-23', NULL, 0),
	(23, 'Help Desk Technician', NULL, '2023-02-23', NULL, 0),
	(24, 'Software Developer', NULL, '2023-02-23', NULL, 0),
	(25, 'Customer Service Manager', NULL, '2023-02-23', NULL, 0),
	(26, 'Customer Service Representative', NULL, '2023-02-23', NULL, 0),
	(27, 'Technical Support Specialist', NULL, '2023-02-23', NULL, 0),
	(28, 'Sales Support Specialist', NULL, '2023-02-23', NULL, 0),
	(29, 'R&D Manager', NULL, '2023-02-23', NULL, 0),
	(30, 'Scientist', NULL, '2023-02-23', NULL, 0),
	(31, ' Engineer', NULL, '2023-02-23', NULL, 0),
	(32, 'Product Manager', NULL, '2023-02-23', NULL, 0),
	(33, 'General Counsel', NULL, '2023-02-23', NULL, 0),
	(34, 'Attorney', NULL, '2023-02-23', NULL, 0),
	(35, 'Legal Secretary', NULL, '2023-02-23', NULL, 0),
	(36, 'Office Manager', NULL, '2023-02-23', NULL, 0),
	(37, 'Administrative Assistant', NULL, '2023-02-23', NULL, 0),
	(38, 'Receptionist', NULL, '2023-02-23', NULL, 0),
	(39, 'Quality Assurance Manager', NULL, '2023-02-23', NULL, 0),
	(40, 'Quality Control Inspector', NULL, '2023-02-23', NULL, 0),
	(41, 'Quality Assurance Technician', NULL, '2023-02-23', NULL, 0),
	(42, 'Quality Auditor', NULL, '2023-02-23', NULL, 0),
	(44, 'General Manager', NULL, '2023-02-24', NULL, 0);

-- Dumping structure for table hrm.position_employee
CREATE TABLE IF NOT EXISTS `position_employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `position_id` int DEFAULT NULL,
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `flag` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_employee_tblemp_position` (`employee_id`),
  KEY `FK_position_tblemp_position` (`position_id`),
  CONSTRAINT `FK_employee_tblemp_position` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_position_tblemp_position` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table hrm.position_employee: ~14 rows (approximately)
INSERT IGNORE INTO `position_employee` (`id`, `employee_id`, `position_id`, `description`, `created_at`, `flag`) VALUES
	(1, 2, 20, NULL, '2023-02-26', 0),
	(2, 3, 21, NULL, '2023-03-24', 0),
	(3, 3, 22, NULL, '2023-03-24', 0),
	(4, 4, 2, NULL, '2023-03-24', 0),
	(5, 4, 3, NULL, '2023-03-24', 0),
	(6, 5, 9, NULL, '2023-03-24', 0),
	(7, 3, 37, NULL, '2023-03-24', 0),
	(8, 6, 34, NULL, '2023-03-24', 0),
	(9, 7, 21, NULL, '2023-03-24', 0),
	(10, 8, 7, NULL, '2023-03-24', 0),
	(11, 9, 16, NULL, '2023-03-24', 0),
	(12, 10, 17, NULL, '2023-03-24', 0),
	(13, 4, 27, NULL, '2023-03-24', 0),
	(14, 2, 21, NULL, '2023-03-24', 0);

-- Dumping structure for table hrm.principal
CREATE TABLE IF NOT EXISTS `principal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT '0',
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_principal` date DEFAULT NULL,
  `value_money` int NOT NULL,
  `created_at` date DEFAULT NULL,
  `flag` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_principal_employee` (`employee_id`),
  CONSTRAINT `FK_principal_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table hrm.principal: ~9 rows (approximately)
INSERT IGNORE INTO `principal` (`id`, `employee_id`, `description`, `type`, `date_principal`, `value_money`, `created_at`, `flag`) VALUES
	(11, 3, 'Dua ra y kien hay trong cuoc hop', 'bonus', '2023-02-22', 100, '2023-02-22', 0),
	(12, 3, 'Cho ra nhieu y tuong moi', 'fine', '2023-03-24', 500, '2023-03-24', 0),
	(13, 3, 'Di lam muon', 'fine', '2023-03-24', 50, '2023-03-24', 0),
	(14, 4, 'Cham chi', 'bonus', '2023-03-24', 200, '2023-03-24', 0),
	(15, 4, 'Nang dong trong cong viec', 'bonus', '2023-03-24', 30, '2023-03-24', 0),
	(16, 4, 'Di lam muon', 'fine', '2023-03-24', 70, '2023-03-24', 0),
	(17, 2, 'tot bung', 'bonus', '2023-03-24', 600, '2023-03-24', 0),
	(18, 2, 'xau tinh', 'fine', '2023-03-24', 200, '2023-03-24', 0),
	(19, 13, '', 'bonus', '2023-04-09', 1000, '2023-04-09', 1);

-- Dumping structure for table hrm.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `flag` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table hrm.role: ~3 rows (approximately)
INSERT IGNORE INTO `role` (`id`, `role_name`, `description`, `created_at`, `flag`) VALUES
	(1, 'admin', NULL, '2023-02-23', 0),
	(2, 'manager', NULL, '2023-02-23', 0),
	(3, 'staff', NULL, '2023-02-23', 0);

-- Dumping structure for table hrm.salary
CREATE TABLE IF NOT EXISTS `salary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `value_money` int DEFAULT NULL,
  `time_to_pay` date DEFAULT NULL,
  `who_pay` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'Nghia',
  `value_money_reward` int DEFAULT '0',
  `working_day` int DEFAULT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `flag` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_salary_employee` (`employee_id`),
  CONSTRAINT `FK_salary_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table hrm.salary: ~58 rows (approximately)
INSERT IGNORE INTO `salary` (`id`, `employee_id`, `value_money`, `time_to_pay`, `who_pay`, `value_money_reward`, `working_day`, `status`, `description`, `created_at`, `flag`) VALUES
	(6, 2, 1200, '2023-01-08', 'Nghia', 50, 25, 'New', NULL, '2023-01-08', 0),
	(7, 3, 800, '2023-01-08', 'Nghia', 100, 25, 'New', NULL, '2023-01-08', 0),
	(8, 4, 500, '2023-01-08', 'Nghia', 50, 25, 'New', NULL, '2023-01-08', 0),
	(9, 2, 1300, '2023-02-08', 'Nghia', 0, 30, 'New', NULL, '2023-02-08', 0),
	(10, 3, 800, '2023-01-08', 'Nghia', 200, 28, 'New', NULL, '2023-02-08', 0),
	(11, 2, 1500, '2023-03-08', 'Nghia', 500, 30, 'New', NULL, '2023-03-08', 0),
	(12, 3, 800, '2023-03-08', 'Nghia', 200, 30, 'New', NULL, '2023-03-08', 0),
	(13, 4, 500, '2023-03-08', 'Nghia', 0, 30, 'New', NULL, '2023-03-08', 0),
	(14, 5, 1000, '2023-04-14', 'nghia', 500, 30, 'New', ' ', '2023-04-14', 0),
	(15, 5, 1000, '2023-04-14', 'Nghia', 300, 30, 'Update', NULL, '2023-04-14', 0),
	(16, 6, 800, '2023-04-14', 'Nghia', 200, 30, 'New', NULL, '2023-04-14', 0),
	(17, 7, 1000, '2023-04-14', 'Nghia', 300, 30, 'New', NULL, '2023-04-14', 0),
	(18, 8, 900, '2023-04-14', 'Nghia', 200, 28, 'New', NULL, '2023-04-14', 0),
	(19, 9, 100, '2023-04-14', 'Nghia', 200, 1, 'New', NULL, '2023-04-14', 0),
	(20, 10, 1000, '2023-04-14', 'Nghia', 300, 30, 'New', NULL, '2022-04-14', 0),
	(21, 11, 800, '2023-03-14', 'Nghia', 100, 31, 'New', NULL, '2023-03-14', 0),
	(22, 11, 1000, '2023-04-14', 'Nghia', 0, 30, 'New', NULL, '2023-04-14', 0),
	(23, 12, 1000, '2023-04-14', 'Nghia', 300, 30, 'New', NULL, '2023-04-14', 0),
	(24, 13, 1000, '2023-04-14', 'Nghia', 200, 30, 'New', NULL, '2023-04-14', 0),
	(25, 14, 1000, '2023-04-14', 'Nghia', 100, 30, 'New', NULL, '2023-04-14', 0),
	(26, 15, 1700, '2023-04-14', 'Nghia', 200, 29, 'New', NULL, '2023-04-14', 0),
	(27, 16, 1000, '2023-04-14', 'Nghia', 0, 29, 'New', NULL, '2023-04-14', 0),
	(28, 17, 1000, '2023-04-14', 'Nghia', 0, 30, 'New', NULL, '2023-04-14', 0),
	(29, 18, 800, '2023-04-14', 'Nghia', 0, 25, 'New', NULL, '2023-02-14', 0),
	(30, 19, 900, '2023-04-14', 'Nghia', 100, 29, 'New', NULL, '2023-04-14', 0),
	(31, 20, 100, '2023-04-14', 'Nghia', 100, 30, 'New', NULL, '2023-04-14', 0),
	(32, 21, 1000, '2023-05-15', 'Nghia', 200, 29, 'New', NULL, '2023-05-15', 0),
	(33, 22, 1000, '2023-05-15', 'Nghia', 100, 28, 'New', NULL, '2023-05-15', 0),
	(34, 23, 1000, '2023-01-15', 'Nghia', 0, 30, 'New', NULL, '2023-01-15', 0),
	(35, 24, 1000, '2023-01-15', 'Nghia', 100, NULL, 'New', NULL, '2023-01-15', 0),
	(36, 25, 900, '2023-01-15', 'Nghia', 300, NULL, 'New', NULL, '2023-01-15', 0),
	(37, 26, 950, '2023-01-15', 'Nghia', 250, NULL, 'New', NULL, '2023-01-15', 0),
	(38, 27, 1000, '2023-01-15', 'Nghia', 0, 30, 'New', NULL, '2023-01-15', 0),
	(39, 28, 900, '2023-02-15', 'Nghia', 100, 30, 'New', NULL, '2023-02-15', 0),
	(40, 29, 1000, '2023-02-15', 'Nghia', 200, NULL, 'New', NULL, '2023-02-15', 0),
	(41, 30, 1000, '2023-02-15', 'Nghia', 100, NULL, 'New', NULL, '2023-02-15', 0),
	(42, 31, 800, '2023-02-15', 'Nghia', 0, NULL, 'New', NULL, '2023-02-15', 0),
	(43, 32, 1000, '2023-02-15', 'Nghia', 0, NULL, 'New', NULL, '2023-02-15', 0),
	(44, 33, 900, '2023-03-15', 'Nghia', 0, NULL, 'New', NULL, '2023-03-15', 0),
	(45, 34, 1500, '2023-03-15', 'Nghia', 0, NULL, 'New', NULL, '2023-03-15', 0),
	(46, 35, 1200, '2023-03-15', 'Nghia', 0, NULL, 'New', NULL, '2023-03-15', 0),
	(47, 36, 1300, '2023-03-15', 'Nghia', 0, NULL, 'New', NULL, '2023-03-15', 0),
	(48, 37, 1000, '2023-03-15', 'Nghia', 0, NULL, 'New', NULL, '2023-03-15', 0),
	(49, 38, 1800, '2023-03-15', 'Nghia', 200, NULL, 'New', NULL, '2023-03-15', 0),
	(50, 39, 1000, '2023-01-15', 'Nghia', 100, NULL, 'New', NULL, '2023-01-15', 0),
	(51, 40, 800, '2023-01-15', 'Nghia', 0, NULL, 'New', NULL, '2023-01-15', 0),
	(52, 41, 900, '2023-05-15', 'Nghia', 500, NULL, 'New', NULL, '2023-05-15', 0),
	(53, 42, 400, '2023-05-15', 'Nghia', 400, NULL, 'New', NULL, '2023-05-15', 0),
	(54, 43, 900, '2023-05-15', 'Nghia', 500, NULL, 'New', NULL, '2023-05-15', 0),
	(55, 44, 1000, '2023-05-15', 'Nghia', 800, NULL, 'New', NULL, '2023-05-15', 0),
	(56, 45, 1200, '2023-05-15', 'Nghia', 0, NULL, 'New', NULL, '2023-05-15', 0),
	(57, 46, 1600, '2023-06-15', 'Nghia', 200, NULL, 'New', NULL, '2023-06-15', 0),
	(58, 47, 1100, '2023-06-15', 'Nghia', 0, NULL, 'New', NULL, '2023-06-15', 0),
	(59, 48, 1200, '2023-07-15', 'Nghia', 500, NULL, 'New', NULL, '2023-06-15', 0),
	(60, 49, 1000, '2023-06-15', 'Nghia', 100, NULL, 'New', NULL, '2023-06-15', 0),
	(61, 50, 700, '2023-07-15', 'Nghia', 0, NULL, 'New', NULL, '2023-07-15', 0),
	(62, 51, 1000, '2023-08-15', 'Nghia', 0, NULL, 'New', NULL, '2023-08-15', 0),
	(63, 52, 6000, '2023-08-15', 'Nghia', 100, NULL, 'New', NULL, '2023-08-15', 0);

-- Dumping structure for table hrm.task
CREATE TABLE IF NOT EXISTS `task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `assignee` int DEFAULT '2',
  `deadline` date DEFAULT NULL,
  `priority` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `flag` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK__employee` (`assignee`),
  CONSTRAINT `FK__employee` FOREIGN KEY (`assignee`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table hrm.task: ~64 rows (approximately)
INSERT IGNORE INTO `task` (`id`, `title`, `description`, `assignee`, `deadline`, `priority`, `status`, `created_at`, `flag`) VALUES
	(5, 'quet nha', NULL, 2, '2023-04-02', 'Medium', 'Todo', '2023-03-27', 0),
	(6, 'rua bat', NULL, 2, '2023-04-01', 'High', 'In progress', '2023-05-28', 0),
	(7, 'trong con', NULL, 2, '2023-04-10', 'Low', 'Complete', '2023-03-27', 0),
	(8, 'lau nhau', NULL, 2, '2023-04-05', 'High', 'Complete', '2023-03-31', 0),
	(9, 'dua con di hoc', NULL, 2, '2023-04-03', 'Medium', 'Complete', '2023-04-01', 0),
	(10, 'dua con di tam', NULL, 2, '2023-04-01', 'Low', 'Todo', '2023-03-31', 0),
	(11, 'di boi', NULL, 2, '2023-04-03', 'High', 'Complete', '2023-03-31', 0),
	(12, 'choi game', NULL, 2, '2023-05-12', 'High', 'Todo', '2023-04-06', 0),
	(13, 'di ca phe', '', 2, '2023-04-12', 'High', 'Todo', '2023-04-09', 0),
	(14, 'Top 40 routine jobs of an ordinary company', NULL, 2, '2023-04-15', 'Medium', 'Todo', '2023-04-15', 0),
	(15, 'Scheduling appointments and meetings', NULL, 2, '2023-04-16', 'Medium', 'In progress', '2023-05-16', 0),
	(16, 'Organizing and maintaining physical and digital records', NULL, 2, '2023-05-15', 'High', 'In progress', '2023-05-17', 0),
	(17, 'Drafting and sending out memos, letters, and other business correspondence', NULL, 2, '2023-04-15', 'Medium', 'Todo', '2023-06-17', 0),
	(18, 'Managing inventory and ordering office supplies', NULL, 2, '2023-04-15', 'Low', 'Todo', '2023-06-15', 0),
	(19, 'Preparing and processing invoices and other financial documents', NULL, 2, '2023-04-14', 'Medium', 'In progress', '2023-07-15', 0),
	(20, 'Conducting research and compiling data for reports', NULL, 2, '2023-04-15', 'High', 'Complete', '2023-06-15', 0),
	(21, 'Handling payroll and employee benefits', NULL, 2, '2023-04-15', 'Medium', 'Todo', '2023-07-14', 0),
	(22, 'Managing social media accounts and online presence', NULL, 2, '2023-04-15', 'Medium', 'In progress', '2023-07-14', 0),
	(23, 'Conducting background checks', NULL, 2, '2023-04-15', 'High', 'Complete', '2023-06-13', 0),
	(24, 'Coordinating travel arrangements', NULL, 2, '2023-04-15', 'Medium', 'Complete', '2023-03-16', 0),
	(25, 'Filing and paying taxes', NULL, 2, '2023-04-15', 'Low', 'In progress', '2023-03-15', 0),
	(26, 'maintaining office equipment and technology', NULL, 2, '2023-04-15', 'High', 'In progress', '2023-03-15', 0),
	(27, 'Assisting with onboarding ', NULL, 2, '2023-04-15', 'Low', 'Todo', '2023-03-14', 0),
	(28, 'Coordinating and facilitating team meetings', NULL, 2, '2023-04-15', 'Medium', 'In progress', '2023-04-16', 0),
	(29, 'Conducting customer service and support', NULL, 2, '2023-04-15', 'High', 'Complete', '2023-04-17', 0),
	(30, 'Preparing and distributing meeting agendas', NULL, 2, '2023-04-15', 'Medium', 'Todo', '2023-04-13', 0),
	(31, 'Updating and maintaining company websites', NULL, 2, '2023-04-14', 'Medium', 'Complete', '2023-04-12', 0),
	(32, 'Developing and implementing marketing', NULL, 2, '2023-04-15', 'Medium', 'In progress', '2023-03-11', 0),
	(33, 'Researching and analyzing industry trends ', NULL, 2, '2023-04-15', 'Low', 'Todo', '2023-05-20', 0),
	(34, 'Developing and maintaining vendor', NULL, 2, '2023-04-15', 'High', 'In progress', '2023-03-21', 0),
	(35, 'Planning and executing fundraising ', NULL, 2, '2023-04-15', 'High', 'In progress', '2023-05-16', 0),
	(36, 'Managing company databases', NULL, 2, '2023-04-15', 'Medium', 'In progress', '2023-05-16', 0),
	(37, 'Creating and distributing promotional materials', NULL, 2, '2023-04-15', 'High', 'Todo', '2023-05-17', 0),
	(38, 'Conducting performance evaluations', NULL, 2, '2023-04-15', 'Low', 'Complete', '2023-05-18', 0),
	(39, 'Developing and maintaining company policies', NULL, 2, '2023-04-15', 'Medium', 'In progress', '2023-05-15', 0),
	(40, 'Managing contracts', NULL, 2, '2023-04-15', 'Low', 'Todo', '2023-05-21', 0),
	(41, 'Conducting market research', NULL, 2, '2023-04-15', 'High', 'In progress', '2023-03-08', 0),
	(42, 'Developing and maintaining budgets', NULL, 2, '2023-04-14', 'Medium', 'Complete', '2023-03-07', 0),
	(43, 'Coordinating and facilitating ', NULL, 2, '2023-04-14', 'High', 'In progress', '2023-04-12', 0),
	(44, 'Conducting safety and security inspections', NULL, 2, '2023-04-15', 'Low', 'Complete', '2023-04-11', 0),
	(45, 'Planning and executing employee', NULL, 2, '2023-04-15', 'Medium', 'Todo', '2023-04-01', 0),
	(46, 'Assisting with the development ', NULL, 2, '2023-04-15', 'Low', 'Todo', '2023-04-01', 0),
	(47, 'Managing company vehicles', NULL, 2, '2023-04-15', 'High', 'Complete', '2023-04-02', 0),
	(48, 'Conducting maintenance', NULL, 2, '2023-04-15', 'Medium', 'In progress', '2023-04-03', 0),
	(49, 'Developing and maintaining relationships', NULL, 2, '2023-04-15', 'High', 'Complete', '2023-04-04', 0),
	(50, 'Coordinating and facilitating company', NULL, 2, '2023-04-15', 'Medium', 'Todo', '2023-04-05', 0),
	(51, 'Assisting with the development', NULL, 2, '2023-04-15', 'Low', 'Complete', '2023-04-06', 0),
	(52, 'Conducting market and customer ', NULL, 2, '2023-04-15', 'High', 'In progress', '2023-04-06', 0),
	(53, 'Managing company assets and investments.', NULL, 2, '2023-04-15', 'High', 'Complete', '2023-04-07', 0),
	(54, 'Answering phone calls ', NULL, 2, '2023-04-15', 'High', 'Todo', '2023-04-08', 0),
	(55, 'Providing administrative support', NULL, 2, '2023-04-15', 'High', 'In progress', '2023-04-09', 0),
	(56, 'Conducting training sessions for employees', NULL, 2, '2023-04-13', 'High', 'Complete', '2023-04-26', 0),
	(57, 'Conducting performance reviews for employees', NULL, 2, '2023-04-16', 'High', 'In progress', '2023-04-27', 0),
	(58, 'Managing and resolving employee conflicts', NULL, 2, '2023-04-17', 'Medium', 'In progress', '2023-04-28', 0),
	(59, 'Developing and implementing employee', NULL, 2, '2023-04-17', 'High', 'In progress', '2023-04-29', 0),
	(60, 'Coordinating with local authorities', NULL, 2, '2023-04-17', 'Low', 'Complete', '2023-04-29', 0),
	(61, 'Conducting industry benchmarking', NULL, 2, '2023-04-16', 'High', 'In progress', '2023-04-30', 0),
	(62, 'Coordinating with regulatory bodies', NULL, 2, '2023-04-16', 'Low', 'Complete', '2023-05-01', 0),
	(63, 'Developing and implementing employee', NULL, 2, '2023-04-16', 'Low', 'Todo', '2023-05-02', 0),
	(64, 'Conducting internal audits and reviews', NULL, 2, '2023-04-16', 'High', 'Complete', '2023-05-25', 0),
	(65, 'Developing and implementing environmental ', NULL, 2, '2023-04-16', 'High', 'In progress', '2023-03-29', 0),
	(66, 'Conducting competitor analysis', NULL, 2, '2023-04-16', 'High', 'Complete', '2023-05-05', 0),
	(67, 'Coordinating with third-party auditors', NULL, 2, '2023-04-16', 'High', 'Todo', '2023-03-28', 0),
	(68, 'Conducting internal investigations and reviews', NULL, 2, '2023-04-19', 'Low', 'Complete', '2023-05-05', 0);

-- Dumping structure for table hrm.task_department
CREATE TABLE IF NOT EXISTS `task_department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_id` int DEFAULT NULL,
  `department_id` int DEFAULT NULL,
  `flag` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_task` (`task_id`),
  KEY `FK_department` (`department_id`),
  CONSTRAINT `FK_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_task` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table hrm.task_department: ~54 rows (approximately)
INSERT IGNORE INTO `task_department` (`id`, `task_id`, `department_id`, `flag`) VALUES
	(1, 5, 1, 0),
	(2, 6, 2, 0),
	(3, 7, 7, 0),
	(4, 8, 10, 0),
	(5, 9, 1, 0),
	(6, 10, 3, 0),
	(7, 11, 6, 0),
	(8, 13, 2, 0),
	(9, 10, 1, 0),
	(10, 11, 4, 0),
	(11, 12, 4, 0),
	(12, 13, 4, 0),
	(13, 14, 4, 0),
	(14, 15, 4, 0),
	(15, 16, 4, 0),
	(16, 17, 4, 0),
	(17, 18, 4, 0),
	(18, 19, 4, 0),
	(19, 20, 4, 0),
	(20, 21, 4, 0),
	(21, 22, 4, 0),
	(22, 23, 4, 0),
	(23, 24, 4, 0),
	(24, 25, 4, 0),
	(25, 26, 4, 0),
	(26, 27, 4, 0),
	(27, 28, 4, 0),
	(28, 29, 4, 0),
	(29, 30, 4, 0),
	(30, 31, 4, 0),
	(31, 32, 3, 0),
	(32, 33, 3, 0),
	(33, 34, 3, 0),
	(34, 35, 3, 0),
	(35, 36, 3, 0),
	(36, 37, 3, 0),
	(37, 38, 3, 0),
	(38, 39, 3, 0),
	(39, 40, 3, 0),
	(40, 41, 3, 0),
	(41, 42, 3, 0),
	(42, 43, 3, 0),
	(43, 44, 5, 0),
	(44, 45, 5, 0),
	(45, 46, 5, 0),
	(46, 47, 6, 0),
	(47, 48, 6, 0),
	(48, 49, 6, 0),
	(49, 50, 6, 0),
	(50, 51, 6, 0),
	(51, 27, 6, 0),
	(52, 32, 7, 0),
	(53, 19, 8, 0),
	(54, 8, 9, 0);

-- Dumping structure for view hrm.value_money_principal
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `value_money_principal` (
	`employee_id` INT(10) NULL,
	`value_money_update` DECIMAL(41,0) NULL
) ENGINE=MyISAM;

-- Dumping structure for view hrm.all_position_name
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `all_position_name`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `all_position_name` AS select `employee`.`id` AS `employee_id`,group_concat(`position`.`position_name` separator ', ') AS `position_name` from ((`position_employee` join `position` on((`position`.`id` = `position_employee`.`position_id`))) join `employee` on((`employee`.`id` = `position_employee`.`employee_id`))) group by `employee`.`id`;

-- Dumping structure for view hrm.value_money_principal
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `value_money_principal`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `value_money_principal` AS select `p`.`employee_id` AS `employee_id`,sum(`p`.`current_money`) AS `value_money_update` from (select `p`.`employee_id` AS `employee_id`,(case when (`p`.`type` = 'bonus') then `p`.`value_money` when (`p`.`type` = 'fine') then -(`p`.`value_money`) end) AS `current_money` from `principal` `p`) `p` group by `p`.`employee_id`;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
