IF OBJECT_ID('dbo.notes', 'U') IS NULL
BEGIN
    CREATE TABLE dbo.notes (
        id INT IDENTITY(1,1) PRIMARY KEY,
        title NVARCHAR(200) NOT NULL,
        content NVARCHAR(4000) NOT NULL DEFAULT '',
        created_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME()
    );
END;
