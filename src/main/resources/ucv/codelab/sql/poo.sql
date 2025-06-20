START TRANSACTION;

-- Tabla de Especialidad
CREATE TABLE
    IF NOT EXISTS especialidad (
        id_especialidad INT PRIMARY KEY AUTO_INCREMENT,
        especialidad VARCHAR(100) NOT NULL,
        descripcion TEXT
    );

-- Tabla de Médicos (incluye especialidad)
CREATE TABLE
    IF NOT EXISTS medico (
        id_medico INT PRIMARY KEY AUTO_INCREMENT,
        id_especialidad INT NOT NULL,
        nombre VARCHAR(100) NOT NULL,
        apellido VARCHAR(100) NOT NULL,
        dni VARCHAR(20) UNIQUE NOT NULL,
        fecha_nacimiento DATE NOT NULL,
        sexo ENUM ('masculino', 'femenino') NOT NULL,
        telefono VARCHAR(20),
        email VARCHAR(100),
        colegiatura VARCHAR(50) NOT NULL UNIQUE,
        grado_academico VARCHAR(50) NOT NULL,
        fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        estado BOOLEAN NOT NULL DEFAULT TRUE,
        FOREIGN KEY (id_especialidad) REFERENCES especialidad (id_especialidad) ON DELETE RESTRICT
    );

-- Tabla de Pacientes
CREATE TABLE
    IF NOT EXISTS paciente (
        id_paciente INT PRIMARY KEY AUTO_INCREMENT,
        nombre VARCHAR(100) NOT NULL,
        apellido VARCHAR(100) NOT NULL,
        dni VARCHAR(20) UNIQUE NOT NULL,
        fecha_nacimiento DATE NOT NULL,
        sexo ENUM ('masculino', 'femenino') NOT NULL,
        direccion TEXT,
        telefono VARCHAR(20),
        tipo_sangre enum ('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-') NOT NULL,
        estado BOOLEAN NOT NULL DEFAULT TRUE,
        INDEX idx_dni (dni),
        INDEX idx_nombre_apellido (nombre, apellido)
    );

-- Tabla Principal de Historias Clínicas (depende de paciente y medico)
CREATE TABLE
    IF NOT EXISTS historia_clinica (
        id_historia INT PRIMARY KEY AUTO_INCREMENT,
        id_paciente INT NOT NULL,
        id_medico INT NOT NULL,
        fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        motivo_consulta TEXT NOT NULL,
        antecedentes TEXT,
        tiempo_enfermedad VARCHAR(100),
        observaciones TEXT,
        FOREIGN KEY (id_paciente) REFERENCES paciente (id_paciente) ON DELETE RESTRICT,
        FOREIGN KEY (id_medico) REFERENCES medico (id_medico) ON DELETE RESTRICT,
        INDEX idx_paciente (id_paciente)
    );

-- Tabla de Exámenes Físicos (depende de historia clinica)
CREATE TABLE
    IF NOT EXISTS examen_fisico (
        id_examen INT PRIMARY KEY AUTO_INCREMENT,
        id_historia INT NOT NULL,
        peso DECIMAL(5, 2),
        talla DECIMAL(5, 2),
        presion_arterial VARCHAR(20),
        temperatura DECIMAL(4, 2),
        frecuencia_cardiaca INT,
        frecuencia_respiratoria INT,
        FOREIGN KEY (id_historia) REFERENCES historia_clinica (id_historia) ON DELETE CASCADE
    );

-- Tabla de Diagnósticos (depende de historia clinica)
CREATE TABLE
    IF NOT EXISTS diagnostico (
        id_diagnostico INT PRIMARY KEY AUTO_INCREMENT,
        id_historia INT NOT NULL,
        tipo ENUM ('presuntivo', 'definitivo', 'diferencial'),
        descripcion TEXT,
        codigo_cie10 VARCHAR(20),
        FOREIGN KEY (id_historia) REFERENCES historia_clinica (id_historia) ON DELETE CASCADE
    );

-- Tabla de Tratamientos (depende de historia clinica)
CREATE TABLE
    IF NOT EXISTS tratamiento (
        id_tratamiento INT PRIMARY KEY AUTO_INCREMENT,
        id_historia INT NOT NULL,
        descripcion TEXT,
        indicaciones TEXT,
        FOREIGN KEY (id_historia) REFERENCES historia_clinica (id_historia) ON DELETE CASCADE
    );

--Tabla de Usuarios (depende de medico)
CREATE TABLE
    IF NOT EXISTS usuario (
        id_usuario INT PRIMARY KEY AUTO_INCREMENT,
        id_medico INT NOT NULL UNIQUE,
        username VARCHAR(20) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        estado BOOLEAN NOT NULL DEFAULT TRUE,
        FOREIGN KEY (id_medico) REFERENCES medico (id_medico) ON DELETE CASCADE
    );

COMMIT;