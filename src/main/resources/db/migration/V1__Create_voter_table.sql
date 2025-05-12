-- id uuid pk, studentid varchar, name varchar, dateOfBirth date, gender enum, publickeyfd
CREATE TABLE students (
    id BINARY(16) PRIMARY KEY,
    student_id VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
    public_key TEXT NOT NULL
);
