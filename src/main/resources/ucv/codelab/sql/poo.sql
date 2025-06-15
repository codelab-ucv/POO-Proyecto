START TRANSACTION;

-- ============================================
-- CREACIÓN DE TABLAS CON TODAS LAS RESTRICCIONES
-- ============================================
-- Tabla persona (tabla base, sin dependencias)
CREATE TABLE
    IF NOT EXISTS `persona` (
        `id` int (11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
        `nombre` varchar(100) NOT NULL,
        `apellido` varchar(100) NOT NULL,
        `dni` varchar(12) NOT NULL UNIQUE,
        `fecha_nacimiento` date NOT NULL,
        `sexo` enum ('M', 'F', 'O') NOT NULL,
        `direccion` varchar(100) DEFAULT NULL,
        `telefono` varchar(9) DEFAULT NULL
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- Tabla condicion (para alergias y enfermedades cronicas)
CREATE TABLE
    IF NOT EXISTS `condicion` (
        `id` int (11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
        `tipo` varchar(50) NOT NULL,
        `condicion` varchar(100) NOT NULL,
        `gravedad` enum ('LEVE', 'MODERADA', 'SEVERA') DEFAULT 'LEVE',
        UNIQUE KEY `unique_tipo_condicion` (`tipo`, `condicion`)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- Tabla medico (depende de persona)
CREATE TABLE
    IF NOT EXISTS `medico` (
        `id` int (11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
        `id_persona` int (11) NOT NULL UNIQUE,
        `area` varchar(255) NOT NULL,
        `email` varchar(255) NOT NULL UNIQUE,
        `experiencia` int (11) NOT NULL,
        `colegiatura` varchar(20) NOT NULL UNIQUE,
        `universidad` varchar(255) NOT NULL,
        `grado` varchar(20) NOT NULL,
        FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- Tabla paciente (depende de persona)
CREATE TABLE
    IF NOT EXISTS `paciente` (
        `id` int (11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
        `id_persona` int (11) NOT NULL UNIQUE,
        `tipo_sangre` enum ('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-') NOT NULL,
        `antecedentes` varchar(255) DEFAULT NULL,
        FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- Tabla paciente_condicion (tabla de relación muchos a muchos)
CREATE TABLE
    IF NOT EXISTS `paciente_condicion` (
        `id` int (11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
        `id_paciente` int (11) NOT NULL,
        `id_condicion` int (11) NOT NULL,
        UNIQUE KEY (`id_paciente`, `id_condicion`),
        FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY (`id_condicion`) REFERENCES `condicion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- Tabla cita (depende de paciente y medico)
CREATE TABLE
    IF NOT EXISTS `cita` (
        `id` int (11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
        `id_paciente` int (11) NOT NULL,
        `id_medico` int (11) NOT NULL,
        `talla` decimal(3, 2) DEFAULT NULL,
        `peso` decimal(5, 2) DEFAULT NULL,
        `fecha_atencion` date NOT NULL,
        `estado_paciente` varchar(50) DEFAULT NULL,
        `sintomas` text DEFAULT NULL,
        `diagnostico` text DEFAULT NULL,
        `tratamiento` text DEFAULT NULL,
        `observacion` text DEFAULT NULL,
        FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

--Tabla usuario
CREATE TABLE
    IF NOT EXISTS `usuario` (
        `id` int (11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
        `id_medico` int (11) NOT NULL UNIQUE,
        `username` varchar(20) NOT NULL UNIQUE,
        `password` varchar(255) NOT NULL,
        FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- Índices para mejor rendimiento
CREATE INDEX IF NOT EXISTS `idx_persona_dni` ON `persona` (`dni`);

CREATE INDEX IF NOT EXISTS `idx_cita_fecha` ON `cita` (`fecha_atencion`);

CREATE INDEX IF NOT EXISTS `idx_cita_paciente` ON `cita` (`id_paciente`);

COMMIT;